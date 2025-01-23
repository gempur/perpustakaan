package com.kotabontang.perpustakaan;

import java.sql.Connection;

public class Perpustakaan {
    static Connection conn;

    public static void main(String[] args) throws ClassNotFoundException {    
        conn = Model.connect();
        Main.MainWindow(args);

    }

   

    public static void exit_app() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.exit(0);
    }
}
