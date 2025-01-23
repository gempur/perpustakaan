package com.kotabontang.perpustakaan;

import java.awt.*;
import java.awt.event.ComponentAdapter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class View {

    public static void Buku(JDesktopPane desktopPane){
        DefaultTableModel modelBuku = Model.GetBookAll();

        JInternalFrame frame = new JInternalFrame("Manajemen Buku", true, true, true, true);
        frame.setMinimumSize(new Dimension(500, 400));
        frame.setMaximumSize(new Dimension(800, 700));
        frame.setPreferredSize(new Dimension(800,700));
        JPanel panelBuku = new JPanel();
        panelBuku.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JPanel panelForm = new JPanel();
        JPanel panelFormulir = new JPanel();
        JPanel panelButton = new JPanel();
        JPanel panelTable = new JPanel();
        panelTable.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelTable.setBorder(BorderFactory.createTitledBorder("Daftar Buku"));

        panelBuku.setLayout(new BorderLayout());
        panelBuku.add(panelForm, BorderLayout.NORTH);
        panelBuku.add(panelTable, BorderLayout.CENTER);

    

        panelForm.setLayout(new BorderLayout());
        panelForm.setBorder(BorderFactory.createTitledBorder("Form Buku"));
        panelForm.add(panelFormulir, BorderLayout.CENTER);

        panelFormulir.setLayout(new GridLayout(4, 2));

        JLabel labelKodeBuku = new JLabel("Kode Buku");
        JTextField textKodeBuku = new JTextField();
        textKodeBuku.setEditable(false);
        panelFormulir.add(labelKodeBuku);
        panelFormulir.add(textKodeBuku);

        JLabel labelJudulBuku = new JLabel("Judul Buku");
        JTextArea textJudulBuku = new JTextArea();
        panelFormulir.add(labelJudulBuku);
        panelFormulir.add(textJudulBuku);

        JLabel labelPengarang = new JLabel("Pengarang");
        JTextField textPengarang = new JTextField();
        panelFormulir.add(labelPengarang);
        panelFormulir.add(textPengarang);

        JLabel labelPenerbit = new JLabel("Penerbit");
        JComboBox comboPenerbit = new JComboBox();
        try {
            ResultSet rs = Model.GetPenerbit();
            while (rs.next()) {
                comboPenerbit.addItem(rs.getString("nama_penerbit"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        panelFormulir.add(labelPenerbit);
        panelFormulir.add(comboPenerbit);


        panelForm.add(panelButton, BorderLayout.SOUTH);

        JButton buttonTambah = new JButton("Tambah");
        JButton buttonEdit = new JButton("Edit");
        JButton buttonHapus = new JButton("Hapus");
        JButton buttonSimpan = new JButton("Simpan");

        panelButton.setLayout(new FlowLayout());
        panelButton.add(buttonTambah);
        panelButton.add(buttonEdit);
        panelButton.add(buttonHapus);
        panelButton.add(buttonSimpan);

        JTable tableBuku = new JTable();
        tableBuku.setAutoCreateRowSorter(true);
        tableBuku.setRowSelectionAllowed(true);
        tableBuku.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableBuku.setModel(modelBuku);
        tableBuku.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
      
        JScrollPane scrollPane = new JScrollPane(tableBuku);
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent evt) {
                scrollPane.setPreferredSize(new Dimension(panelTable.getWidth()-10, panelTable.getHeight()-30));
                scrollPane.revalidate();
            }
        });

        panelTable.add(scrollPane);

        buttonTambah.addActionListener(e -> {
            Controller.ResetFormBuku(textKodeBuku, textJudulBuku, textPengarang, comboPenerbit);
        });

        buttonSimpan.addActionListener(e -> {
            Controller.saveBuku(textKodeBuku.getText(), textJudulBuku.getText(), textPengarang.getText(), comboPenerbit.getSelectedItem().toString());
            Controller.ResetFormBuku(textKodeBuku, textJudulBuku, textPengarang, comboPenerbit);
            tableBuku.setModel(Model.GetBookAll());
        });

        buttonEdit.addActionListener(e -> {
            int row = tableBuku.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Pilih data yang akan di edit");
            } else {
                String kode_buku = tableBuku.getValueAt(row, 0).toString();
                String judul = tableBuku.getValueAt(row, 1).toString();
                String pengarang = tableBuku.getValueAt(row, 2).toString();
                String penerbit = tableBuku.getValueAt(row, 3).toString();
                Controller.EditFormBuku(textKodeBuku, textJudulBuku, textPengarang, comboPenerbit, kode_buku, judul, pengarang, penerbit);
            }
        });

        buttonHapus.addActionListener(e -> {
            int row = tableBuku.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Pilih data yang akan di hapus");
            } else {
                String kode_buku = tableBuku.getValueAt(row, 0).toString();
                Controller.hapusBuku(kode_buku);
                tableBuku.setModel(Model.GetBookAll());
            }
        });

        frame.add(panelBuku);
        frame.setSize(500, 600);
        frame.setVisible(true);
        desktopPane.add(frame);
    }

    public static void Penerbit(JDesktopPane desktopPane){
        DefaultTableModel modelPenerbit = Model.GetPenerbitAll();

        JInternalFrame frame = new JInternalFrame("Manajemen Penerbit", true, true, true, true);
        frame.setMinimumSize(new Dimension(500, 400));
        frame.setMaximumSize(new Dimension(800, 700));
        frame.setPreferredSize(new Dimension(800,700));
        JPanel panelPenerbit = new JPanel();
        panelPenerbit.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JPanel panelForm = new JPanel();
        JPanel panelFormulir = new JPanel();
        JPanel panelButton = new JPanel();
        JPanel panelTable = new JPanel();
        panelTable.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelTable.setBorder(BorderFactory.createTitledBorder("Daftar Penerbit"));

        panelPenerbit.setLayout(new BorderLayout());
        panelPenerbit.add(panelForm, BorderLayout.NORTH);
        panelPenerbit.add(panelTable, BorderLayout.CENTER);

    

        panelForm.setLayout(new BorderLayout());
        panelForm.setBorder(BorderFactory.createTitledBorder("Form Penerbit"));
        panelForm.add(panelFormulir, BorderLayout.CENTER);

        panelFormulir.setLayout(new GridLayout(2, 2));

        JLabel labelKodePenerbit = new JLabel("Kode Penerbit");
        JTextField textKodePenerbit = new JTextField();
        textKodePenerbit.setEditable(false);
        panelFormulir.add(labelKodePenerbit);
        panelFormulir.add(textKodePenerbit);

        JLabel labelNamaPenerbit = new JLabel("Nama Penerbit");
        JTextField textNamaPenerbit = new JTextField();
        panelFormulir.add(labelNamaPenerbit);
        panelFormulir.add(textNamaPenerbit);

        panelForm.add(panelButton, BorderLayout.SOUTH);

        JButton buttonTambah = new JButton("Tambah");
        JButton buttonEdit = new JButton("Edit");
        JButton buttonHapus = new JButton("Hapus");
        JButton buttonSimpan = new JButton("Simpan");

        panelButton.setLayout(new FlowLayout());
        panelButton.add(buttonTambah);
        panelButton.add(buttonEdit);
        panelButton.add(buttonHapus);
        panelButton.add(buttonSimpan);

        JTable tablePenerbit = new JTable();
        tablePenerbit.setAutoCreateRowSorter(true);
        tablePenerbit.setRowSelectionAllowed(true);
        tablePenerbit.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablePenerbit.setModel(modelPenerbit);
        tablePenerbit.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        JScrollPane scrollPane = new JScrollPane(tablePenerbit);
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent evt) {
                scrollPane.setPreferredSize(new Dimension(panelTable.getWidth()-10, panelTable.getHeight()-30));
                scrollPane.revalidate();
            }
        });

        panelTable.add(scrollPane);

        buttonTambah.addActionListener(e -> {
            Controller.ResetFormPenerbit(textKodePenerbit, textNamaPenerbit);
        });

        buttonSimpan.addActionListener(e -> {
            Controller.savePenerbit(textKodePenerbit.getText(), textNamaPenerbit.getText());
            Controller.ResetFormPenerbit(textKodePenerbit, textNamaPenerbit);
            tablePenerbit.setModel(Model.GetPenerbitAll());
        });

        buttonEdit.addActionListener(e -> {
            int row = tablePenerbit.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Pilih data yang akan di edit");
            } else {
                String kode_penerbit = tablePenerbit.getValueAt(row, 0).toString();
                String nama_penerbit = tablePenerbit.getValueAt(row, 1).toString();
                Controller.EditFormPenerbit(textKodePenerbit, textNamaPenerbit, kode_penerbit, nama_penerbit);
            }
        });

        buttonHapus.addActionListener(e -> {
            int row = tablePenerbit.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Pilih data yang akan di hapus");
            } else {
                String kode_penerbit = tablePenerbit.getValueAt(row, 0).toString();
                Controller.hapusPenerbit(kode_penerbit);
                tablePenerbit.setModel(Model.GetPenerbitAll());
            }
        });

        frame.add(panelPenerbit);
        frame.setSize(500, 600);
        frame.setVisible(true);
        desktopPane.add(frame);
        
    }
   
    public static void Peminjam(JDesktopPane desktopPane) {
        DefaultTableModel modelPeminjam = Model.GetPeminjamAll();

        JInternalFrame frame = new JInternalFrame("Manajemen Peminjam", true, true, true, true);
        frame.setMinimumSize(new Dimension(500, 400));
        frame.setMaximumSize(new Dimension(800, 700));
        frame.setPreferredSize(new Dimension(800,700));
        JPanel panelPeminjam = new JPanel();
        panelPeminjam.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JPanel panelForm = new JPanel();
        JPanel panelFormulir = new JPanel();
        JPanel panelButton = new JPanel();
        JPanel panelTable = new JPanel();
        panelTable.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelTable.setBorder(BorderFactory.createTitledBorder("Daftar Peminjam"));

        panelPeminjam.setLayout(new BorderLayout());
        panelPeminjam.add(panelForm, BorderLayout.NORTH);
        panelPeminjam.add(panelTable, BorderLayout.CENTER);

    

        panelForm.setLayout(new BorderLayout());
        panelForm.setBorder(BorderFactory.createTitledBorder("Form Peminjam"));
        panelForm.add(panelFormulir, BorderLayout.CENTER);

        panelFormulir.setLayout(new GridLayout(3, 2));

        JLabel labelKodePeminjam = new JLabel("Kode Peminjam");
        JTextField textKodePeminjam = new JTextField();
        textKodePeminjam.setEditable(false);
        panelFormulir.add(labelKodePeminjam);
        panelFormulir.add(textKodePeminjam);

        JLabel labelNamaPeminjam = new JLabel("Nama Peminjam");
        JTextField textNamaPeminjam = new JTextField();
        panelFormulir.add(labelNamaPeminjam);
        panelFormulir.add(textNamaPeminjam);

        JLabel labelAlamat = new JLabel("Alamat");
        JTextArea textAlamat = new JTextArea();
        panelFormulir.add(labelAlamat);
        panelFormulir.add(textAlamat);

        panelForm.add(panelButton, BorderLayout.SOUTH);

        JButton buttonTambah = new JButton("Tambah");
        JButton buttonEdit = new JButton("Edit");
        JButton buttonHapus = new JButton("Hapus");
        JButton buttonSimpan = new JButton("Simpan");

        panelButton.setLayout(new FlowLayout());
        panelButton.add(buttonTambah);
        panelButton.add(buttonEdit);
        panelButton.add(buttonHapus);
        panelButton.add(buttonSimpan);

        JTable tablePeminjam = new JTable();
        tablePeminjam.setAutoCreateRowSorter(true);
        tablePeminjam.setRowSelectionAllowed(true);
        tablePeminjam.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablePeminjam.setModel(modelPeminjam);

        JScrollPane scrollPane = new JScrollPane(tablePeminjam);
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent evt) {
                scrollPane.setPreferredSize(new Dimension(panelTable.getWidth()-10, panelTable.getHeight()-30));
                scrollPane.revalidate();
            }
        });

        panelTable.add(scrollPane);

        buttonTambah.addActionListener(e -> {
            Controller.ResetFormPeminjam(textKodePeminjam, textNamaPeminjam, textAlamat);
        });

        buttonSimpan.addActionListener(e -> {
            Controller.savePeminjam(textKodePeminjam.getText(), textNamaPeminjam.getText(), textAlamat.getText());
            Controller.ResetFormPeminjam(textKodePeminjam, textNamaPeminjam, textAlamat);
            tablePeminjam.setModel(Model.GetPeminjamAll());
        });

        buttonEdit.addActionListener(e -> {
            int row = tablePeminjam.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Pilih data yang akan di edit");
            } else {
                String kode_peminjam = tablePeminjam.getValueAt(row, 0).toString();
                String nama_peminjam = tablePeminjam.getValueAt(row, 1).toString();
                String alamat = tablePeminjam.getValueAt(row, 2).toString();
                Controller.EditFormPeminjam(textKodePeminjam, textNamaPeminjam, textAlamat, kode_peminjam, nama_peminjam, alamat);
            }
        });

        buttonHapus.addActionListener(e -> {
            int row = tablePeminjam.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Pilih data yang akan di hapus");
            } else {
                String kode_peminjam = tablePeminjam.getValueAt(row, 0).toString();
                Controller.hapusPeminjam(kode_peminjam);
                tablePeminjam.setModel(Model.GetPeminjamAll());
            }
        });

        frame.add(panelPeminjam);
        frame.setSize(500, 600);
        frame.setVisible(true);
        desktopPane.add(frame);

    }

    public static void Rak(JDesktopPane desktopPane) {
        DefaultTableModel modelRak = Model.GetRakAll();

        JInternalFrame frame = new JInternalFrame("Manajemen Rak", true, true, true, true);
        frame.setMinimumSize(new Dimension(500, 400));
        frame.setMaximumSize(new Dimension(800, 700));
        frame.setPreferredSize(new Dimension(800,700));
        JPanel panelRak = new JPanel();
        panelRak.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JPanel panelForm = new JPanel();
        JPanel panelFormulir = new JPanel();
        JPanel panelButton = new JPanel();
        JPanel panelTable = new JPanel();
        panelTable.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelTable.setBorder(BorderFactory.createTitledBorder("Daftar Rak"));

        panelRak.setLayout(new BorderLayout());
        panelRak.add(panelForm, BorderLayout.NORTH);
        panelRak.add(panelTable, BorderLayout.CENTER);

    

        panelForm.setLayout(new BorderLayout());
        panelForm.setBorder(BorderFactory.createTitledBorder("Form Rak"));
        panelForm.add(panelFormulir, BorderLayout.CENTER);

        panelFormulir.setLayout(new GridLayout(2, 2));

        JLabel labelKodeRak = new JLabel("Kode Rak");
        JTextField textKodeRak = new JTextField();
        textKodeRak.setEditable(false);
        panelFormulir.add(labelKodeRak);
        panelFormulir.add(textKodeRak);

        JLabel labelNamaRak = new JLabel("Nama Rak");
        JTextField textNamaRak = new JTextField();
        panelFormulir.add(labelNamaRak);
        panelFormulir.add(textNamaRak);

        panelForm.add(panelButton, BorderLayout.SOUTH);

        JButton buttonTambah = new JButton("Tambah");
        JButton buttonEdit = new JButton("Edit");
        JButton buttonHapus = new JButton("Hapus");
        JButton buttonSimpan = new JButton("Simpan");

        panelButton.setLayout(new FlowLayout());
        panelButton.add(buttonTambah);
        panelButton.add(buttonEdit);
        panelButton.add(buttonHapus);
        panelButton.add(buttonSimpan);

        JTable tableRak = new JTable();
        tableRak.setAutoCreateRowSorter(true);
        tableRak.setRowSelectionAllowed(true);
        tableRak.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableRak.setModel(modelRak);

        JScrollPane scrollPane = new JScrollPane(tableRak);
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent evt) {
                scrollPane.setPreferredSize(new Dimension(panelTable.getWidth()-10, panelTable.getHeight()-30));
                scrollPane.revalidate();
            }
        });

        panelTable.add(scrollPane);

        buttonTambah.addActionListener(e -> {
            Controller.ResetFormRak(textKodeRak, textNamaRak);
        });

        buttonSimpan.addActionListener(e -> {
            Controller.saveRak(textKodeRak.getText(), textNamaRak.getText());
            Controller.ResetFormRak(textKodeRak, textNamaRak);
            tableRak.setModel(Model.GetBookAll());
        });

        buttonEdit.addActionListener(e -> {
            int row = tableRak.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Pilih data yang akan di edit");
            } else {
                String kode_rak = tableRak.getValueAt(row, 0).toString();
                String nama_rak = tableRak.getValueAt(row, 1).toString();
                Controller.EditFormRak(textKodeRak, textNamaRak, kode_rak, nama_rak);
            }
        });

        buttonHapus.addActionListener(e -> {
            int row = tableRak.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Pilih data yang akan di hapus");
            } else {
                String kode_rak = tableRak.getValueAt(row, 0).toString();
                Controller.hapusRak(kode_rak);
                tableRak.setModel(modelRak);
            }
        });

        frame.add(panelRak);
        frame.setSize(500, 600);
        frame.setVisible(true);
        desktopPane.add(frame);
        
    }
    


    public  JPanel StaffForm() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        panel.setBorder(BorderFactory.createTitledBorder("Staff Form"));

        JLabel labelID = new JLabel("ID");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(labelID, gbc);
        JTextField textID = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(textID, gbc);

        JLabel labelName = new JLabel("Name");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(labelName, gbc);
        JTextField textName = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(textName, gbc);

        return panel;
    }

    public  JPanel StaffTable() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Staff Table"));
        String[] columnNames = {"ID", "Name", "Address", "Phone"};
        String[][] data = {
            {"1", "John Doe", "Jl. Jend. Sudirman", "08123456789"},
            {"2", "Jane Doe", "Jl. Jend. Gatot Subroto", "08123456789"},
            {"3", "John Smith", "Jl. Jend. A. Yani", "08123456789"},
            {"4", "Jane Smith", "Jl. Jend. S. Parman", "08123456789"},
            {"5", "John Doe", "Jl. Jend. Sudirman", "08123456789"},
            {"6", "Jane Doe", "Jl. Jend. Gatot Subroto", "08123456789"},
            {"7", "John Smith", "Jl. Jend. A. Yani", "08123456789"},
            {"8", "Jane Smith", "Jl. Jend. S. Parman", "08123456789"},
            {"9", "John Doe", "Jl. Jend. Sudirman", "08123456789"},
            {"10", "Jane Doe", "Jl. Jend. Gatot Subroto", "08123456789"},
            {"11", "John Smith", "Jl. Jend. A. Yani", "08123456789"},
            {"12", "Jane Smith", "Jl. Jend. S. Parman", "08123456789"},
            {"13", "John Doe", "Jl. Jend. Sudirman", "08123456789"},
            {"14", "Jane Doe", "Jl. Jend. Gatot Subroto", "08123456789"},
            {"15", "John Smith", "Jl. Jend. A. Yani", "08123456789"},
            {"16", "Jane Smith", "Jl. Jend. S. Parman", "08123456789"},
            {"17", "John Doe", "Jl. Jend. Sudirman", "08123456789"},
            {"18", "Jane Doe", "Jl. Jend. Gatot Subroto", "08123456789"},
            {"19", "John Smith", "Jl. Jend. A. Yani", "08123456789"}
        };
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    public static void Staff(JDesktopPane desktopPane) {
        JInternalFrame frame = new JInternalFrame("Staff", true, true, true, true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new FlowLayout());
        JPanel staffForm = new View().StaffForm();  
        JPanel staffTable = new View().StaffTable();

        frame.add(staffForm);
        frame.add(staffTable);
        frame.setVisible(true);
        desktopPane.add(frame);


    }
    
}
