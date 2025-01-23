package com.kotabontang.perpustakaan;

import java.sql.*;

/**
 *
 * @author gempur
 */
public class Perpustakaan {
    static Connection conn;

    /**
     *
     * @param args
     * 
     * @startuml
     * class Perpustakaan {
     * + conn
     * + main(String[] args)
     * + exit_app()
     * }
     * Perpustakaan --> Model
     * Perpustakaan --> Main
     * Main --> Menu
     * Main --> StatusBar
     * Main --> Model
     * Model --> Perpustakaan
     * Menu --> Perpustakaan
     * StatusBar --> Perpustakaan
     * @enduml
     */
    public static void main(String[] args) throws ClassNotFoundException {    
        conn = Model.connect();
        Main.MainWindow(args);

    }

    /**
     *
     */
    public static void exit_app() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.exit(0);
    }
}
