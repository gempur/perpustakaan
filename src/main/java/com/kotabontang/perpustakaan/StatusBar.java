package com.kotabontang.perpustakaan;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author gempur
 */
public class StatusBar {

    /**
     *
     * @param frame
     * 
     * @startuml
     * class StatusBar {
     * + SetStatusBar(JFrame frame)
     * }
     * StatusBar --> Controller
     * @enduml
     */
    public static void SetStatusBar(JFrame frame) {
        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new BorderLayout());
        // statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        statusPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JLabel statusBar = new JLabel("Status: Ready | ");
        if (Perpustakaan.conn != null) {
            statusBar.setText(statusBar.getText() + "Connected to Database |");
        } else {
            statusBar.setText(statusBar.getText() + "Not Connected to Database |");
        }
        statusPanel.add(statusBar, BorderLayout.WEST);

        JLabel statusDatetime = new JLabel();
        statusPanel.add(statusDatetime, BorderLayout.EAST);
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tanggal = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new java.util.Date());
                statusDatetime.setText(tanggal);
            }
        });
        timer.start();

        frame.add(statusPanel, BorderLayout.SOUTH);
    }
}
