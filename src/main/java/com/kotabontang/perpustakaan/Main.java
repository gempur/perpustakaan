package com.kotabontang.perpustakaan;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author gempur
 */
public class Main {

    /**
     *
     * @param args
     */
    public static void MainWindow(String[] args) {
        JFrame frame = new JFrame("Perpustakaan Gempurs");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.setSize(300, 300);
        frame.setLayout(new BorderLayout());
        
        JPanel panel = new JPanel(new FlowLayout());
        frame.add(panel, BorderLayout.CENTER);

        JDesktopPane desktopPane = new JDesktopPane();
        frame.add(desktopPane, BorderLayout.CENTER);
        
        Menu.SetMenuBar(frame, desktopPane);
        StatusBar.SetStatusBar(frame);
        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

    }

   
    
}
