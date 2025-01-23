package com.kotabontang.perpustakaan;

import java.awt.event.ActionEvent;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;


public class Menu {

    // Set Menu Bar
    public static void SetMenuBar(JFrame frame, JDesktopPane desktopPane) {
        javax.swing.JMenuBar menuBar = new javax.swing.JMenuBar();
        frame.setJMenuBar(menuBar);

        menuBar.add(SetMenuFile(frame, desktopPane));
        // menuBar.add(SetMenuStaff(frame, desktopPane));
        menuBar.add(SetMenuBuku(frame, desktopPane));
        menuBar.add(SetMenuRak(frame, desktopPane));
        menuBar.add(SetMenuPeminjam(frame, desktopPane));
        menuBar.add(SetMenuLaf(frame, desktopPane));
    }

    // Menu Staff
    public static JMenu SetMenuStaff(JFrame frame, JDesktopPane desktopPane) {
        JMenu menuStaff = new JMenu("Staff");
        JMenuItem mTambahStaff = new JMenuItem("Tambah Staff");
        menuStaff.add(mTambahStaff);
        mTambahStaff.addActionListener((ActionEvent e) -> {
            View.Staff(desktopPane);
            desktopPane.getDesktopManager().activateFrame(desktopPane.getSelectedFrame());
        });
        
        return menuStaff;
    }
    
    // Menu Buku
    public static JMenu SetMenuBuku(JFrame frame, JDesktopPane desktopPane) {
        JMenu menuBuku = new JMenu("Buku");
        JMenuItem mManajemenBuku = new JMenuItem("Manajemen Buku");
        menuBuku.add(mManajemenBuku);
        mManajemenBuku.addActionListener((ActionEvent e) -> {
            View.Buku(desktopPane);
            desktopPane.getDesktopManager().activateFrame(desktopPane.getSelectedFrame());
        }); 

        JMenuItem mManajemenPenerbit = new JMenuItem("Manajemen Penerbit");
        menuBuku.add(mManajemenPenerbit);
        mManajemenPenerbit.addActionListener((ActionEvent e) -> {
            View.Penerbit(desktopPane);
            desktopPane.getDesktopManager().activateFrame(desktopPane.getSelectedFrame());
        });
    
        return menuBuku;
    }

    // Menu Rak
    public static JMenu SetMenuRak(JFrame frame, JDesktopPane desktopPane) {
        JMenu menuRak = new JMenu("Rak");
        JMenuItem mTambahRak = new JMenuItem("Manajemen Rak");
        menuRak.add(mTambahRak);
        mTambahRak.addActionListener((ActionEvent e) -> {
            View.Rak(desktopPane);
            desktopPane.getDesktopManager().activateFrame(desktopPane.getSelectedFrame());
        });

        return menuRak;
    }

    public static JMenu SetMenuPeminjam(JFrame frame, JDesktopPane desktopPane) {
        JMenu menuPeminjam = new JMenu("Peminjam");
        JMenuItem mTambahPeminjam = new JMenuItem("Manajemen Peminjam");
        menuPeminjam.add(mTambahPeminjam);
        mTambahPeminjam.addActionListener((ActionEvent e) -> {
            View.Peminjam(desktopPane);
            desktopPane.getDesktopManager().activateFrame(desktopPane.getSelectedFrame());
        });        

        return menuPeminjam;
    }

    // Menu Look and Feel
    public static JMenu SetMenuLaf(JFrame frame, JDesktopPane desktopPane) {
        JMenu menuLaf = new JMenu("Look and Feel");
        JMenuItem mMetal = new JMenuItem("Metal");
        JMenuItem mNimbus = new JMenuItem("Nimbus");
        JMenuItem mBasic = new JMenuItem("Basic");
        menuLaf.add(mMetal);
        menuLaf.add(mNimbus);
        menuLaf.add(mBasic);
        mMetal.addActionListener((ActionEvent e) -> {
            try {
                javax.swing.UIManager.setLookAndFeel(new javax.swing.plaf.metal.MetalLookAndFeel());
                javax.swing.SwingUtilities.updateComponentTreeUI(frame);
            } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                ex.printStackTrace();
            }
        });
        mNimbus.addActionListener((ActionEvent e) -> {
            try {
                javax.swing.UIManager.setLookAndFeel(new javax.swing.plaf.nimbus.NimbusLookAndFeel());
                javax.swing.SwingUtilities.updateComponentTreeUI(frame);
            } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                ex.printStackTrace();
            }
        });
        mBasic.addActionListener((ActionEvent e) -> {
            try {
                javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
                javax.swing.SwingUtilities.updateComponentTreeUI(frame);
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
                ex.printStackTrace();
            }
        });
    
        return menuLaf;
    }

    // Menu File
    public static JMenu SetMenuFile(JFrame frame, JDesktopPane desktopPane) {
        JMenu menuFile = new JMenu("File");
        JMenuItem mAbout = new JMenuItem("About");
        JMenuItem mTestKoneksiDB = new JMenuItem("Test Koneksi DB");
        JMenuItem mTruncaItem = new JMenuItem("Truncate Data");
        JMenuItem mDummy = new JMenuItem("Generate Dummy Data");
        JMenuItem mExit = new JMenuItem("Exit");

        menuFile.add(mAbout);
        menuFile.add(mTestKoneksiDB);
        menuFile.add(mTruncaItem);
        menuFile.add(mDummy);
        menuFile.addSeparator();
        menuFile.add(mExit);

        mAbout.addActionListener((ActionEvent e) -> {
            javax.swing.JOptionPane.showMessageDialog(null, "Perpustakaan Gempurs\nVersi 1.0\n\nCreated by:\nKota Bontang");
        });
        mTestKoneksiDB.addActionListener((ActionEvent e) -> {
            if (Perpustakaan.conn != null) {
                javax.swing.JOptionPane.showMessageDialog(null, "Koneksi Berhasil");
            } else {
                javax.swing.JOptionPane.showMessageDialog(null, "Koneksi Gagal");
            }
        });

        mTruncaItem.addActionListener((ActionEvent e) -> {
            try {
                Controller.TruncateAll();
                javax.swing.JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
            } catch (Exception ex) {
                // ex.printStackTrace();
                javax.swing.JOptionPane.showMessageDialog(null, "Data Gagal Dihapus");
            }
        });

        mDummy.addActionListener((ActionEvent e) -> {
            try {
                Controller.GenerateDummy();
                javax.swing.JOptionPane.showMessageDialog(null, "Data Dummy Berhasil Dibuat");
            } catch (Exception ex) {
                // ex.printStackTrace();
                javax.swing.JOptionPane.showMessageDialog(null, "Data Dummy Gagal Dibuat");
            }
        });

        mExit.addActionListener((ActionEvent e) -> {
            Perpustakaan.exit_app();
        });
    
        return menuFile;
    }
}
