package com.kotabontang.perpustakaan;

import java.sql.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author gempur
 */
public class Model {
    static Connection conn = null;

    /**
     *
     * @return Connection 
     * @throws ClassNotFoundException
     * 
     * Method ini digunakan untuk melakukan koneksi ke database
     * 
     * @startuml
     * class Model {
     * + connect() : Connection
     * + GenerateID(table: String, field: String) : String
     * + InsertPenerbit(nama: String, alamat: String) : void
     * + CheckRecord(table: String, field: String, value: String) : Boolean
     * + InsertBook(judul: String, pengarang: String, kode_penerbit: String) : void
     * + UpdateBook(kode_buku: String, judul: String, pengarang: String, kode_penerbit: String) : void
     * + DeleteBook(kode_buku: String) : void
     * + GetBookAll() : DefaultTableModel
     * + InsertRak(nama: String) : void
     * + InsertRakBuku(kode_rak: String, kode_buku: String, jumlah: int) : void
     * + GetKodePenerbit(nama: String) : String
     * + GetKodeRak(nama: String) : String
     * + GetKodeBuku(judul: String) : String
     * + SetReffential(val: String) : void
     * + TruncateAll() : void
     * + GetPenerbit() : ResultSet
     * + GetPenerbitAll() : DefaultTableModel
     * + GetPeminjamAll() : DefaultTableModel
     * + GetRakAll() : DefaultTableModel
     * }
     * @enduml
     */
    public static Connection connect() throws ClassNotFoundException {
        String DBurl = "jdbc:mysql://localhost/perpustakaan";
        String DBusername = "root";
        String DBpassword = "";
        Class.forName("com.mysql.cj.jdbc.Driver");

        try {
            conn = DriverManager.getConnection(DBurl, DBusername, DBpassword);  
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    /**
     *
     * @param table
     * @param field
     * @return
     */
    public static String GenerateID(String table, String field) {
        String id = "";
        String sql = "SELECT MAX(cast(" + field + " as signed)) FROM " + table;
        ResultSet rs = null;
        try {
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                id = rs.getString(1);
                if (id == null) {
                    id = "1";
                } else {
                    id = String.valueOf(Integer.parseInt(id) + 1);
                    
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return id;
    }

    /**
     *
     * @param nama
     * @param alamat
     */
    public static void InsertPenerbit(String nama, String alamat) {
        String sql = "INSERT INTO penerbit (kode_penerbit, nama_penerbit)  VALUES (?, ?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, GenerateID("penerbit", "kode_penerbit"));
            stmt.setString(2, nama);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     *
     * @param table
     * @param field
     * @param value
     * @return
     */
    public static Boolean CheckRecord(String table, String field, String value) {
        Boolean result = false;
        String sql = "SELECT count(1) as jumlah FROM " + table + " WHERE " + field + " = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, value);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                result = rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    // Buku

    /**
     *
     * @param judul
     * @param pengarang
     * @param kode_penerbit
     */
    public static void InsertBook(String judul, String pengarang, String kode_penerbit) {
       
        String sql = "INSERT INTO buku (kode_buku, judul, pengarang, kode_penerbit) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, GenerateID("buku", "kode_buku"));
            stmt.setString(2, judul);
            stmt.setString(3, pengarang);
            stmt.setString(4, kode_penerbit);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     *
     * @param kode_buku
     * @param judul
     * @param pengarang
     * @param kode_penerbit
     */
    public static void UpdateBook(String kode_buku, String judul, String pengarang, String kode_penerbit) {
        String sql = "UPDATE buku SET judul = ?, pengarang = ?, kode_penerbit = ? WHERE kode_buku = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, judul);
            stmt.setString(2, pengarang);
            stmt.setString(3, kode_penerbit);
            stmt.setString(4, kode_buku);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     *
     * @param kode_buku
     */
    public static void DeleteBook(String kode_buku) {
        String sql = "DELETE FROM buku WHERE kode_buku = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, kode_buku);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     *
     * @return
     */
    public static DefaultTableModel GetBookAll(){
        ResultSet rs = null;
        DefaultTableModel model = new DefaultTableModel();
        String sql = """
            SELECT b.kode_buku, 
            b.judul,
            b.pengarang,
            b.kode_penerbit,
            p.nama_penerbit 
            FROM buku b 
            LEFT JOIN penerbit p
            ON b.kode_penerbit = p.kode_penerbit
            """;
        try {
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                String columnName = rsmd.getColumnName(i);

                model.addColumn(columnName.toUpperCase());
            }

            while (rs.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = rs.getObject(i);
                }
                model.addRow(row);
            }

            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return model;
        
    }

    /**
     *
     * @param nama
     */
    public static void InsertRak(String nama) {
        String sql = "INSERT INTO rak (id, nama) VALUES (?, ?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, GenerateID("rak", "id"));
            stmt.setString(2, nama);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     *
     * @param kode_rak
     * @param kode_buku
     * @param jumlah
     */
    public static void InsertRakBuku(String kode_rak, String kode_buku, int jumlah) {
        String sql = "INSERT INTO buku_rak (rak, buku, qty) VALUES (?, ?, ?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, kode_rak);
            stmt.setString(2, kode_buku);
            stmt.setInt(3, jumlah);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     *
     * @param nama
     * @return
     */
    public static String GetKodePenerbit(String nama) {
        String kode_penerbit = "";
        String sql = "SELECT kode_penerbit FROM penerbit WHERE nama_penerbit = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nama);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                kode_penerbit = rs.getString(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return kode_penerbit;
    }

    /**
     *
     * @param nama
     * @return
     */
    public static String GetKodeRak(String nama) {
        String kode_rak = "";
        String sql = "SELECT id FROM rak WHERE nama = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nama);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                kode_rak = rs.getString(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return kode_rak;
    }

    /**
     *
     * @param judul
     * @return
     */
    public static String GetKodeBuku(String judul) {
        String kode_buku = "";
        String sql = "SELECT kode_buku FROM buku WHERE judul like ? LIMIT 1";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            String judul_final = judul + '%';
            stmt.setString(1, judul);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                kode_buku = rs.getString(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return kode_buku;
    }
    
    /**
     *
     * @param val
     */
    public static void SetReffential(String val) {
        String sql = "SET FOREIGN_KEY_CHECKS = " + val;
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     *
     */
    public static void TruncateAll() {
        String[] tables = {"buku", "penerbit", "rak", "buku_rak"};
        String sql = "";
        SetReffential("0");
        for (String table : tables) {
            sql = "TRUNCATE TABLE " + table;
            try {
                Statement stmt = conn.createStatement();
                stmt.executeUpdate(sql);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        SetReffential("1");
    }

    /**
     *
     * @return
     */
    public static ResultSet GetPenerbit() {
        ResultSet rs = null;
        String sql = "SELECT * FROM penerbit";
        try {
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rs;
    }

    /**
     *
     * @return
     */
    public static DefaultTableModel GetPenerbitAll() {
        ResultSet rs = null;
        DefaultTableModel model = new DefaultTableModel();
        String sql = "SELECT * FROM penerbit";
        try {
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                String columnName = rsmd.getColumnName(i);

                model.addColumn(columnName.toUpperCase());
            }

            while (rs.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = rs.getObject(i);
                }
                model.addRow(row);
            }

            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return model;
    }

    /**
     *
     * @return
     */
    public static DefaultTableModel GetPeminjamAll() {
        ResultSet rs = null;
        DefaultTableModel model = new DefaultTableModel();
        String sql = "SELECT * FROM peminjam";
        try {
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                String columnName = rsmd.getColumnName(i);

                model.addColumn(columnName.toUpperCase());
            }

            while (rs.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = rs.getObject(i);
                }
                model.addRow(row);
            }

            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return model;
    }

    /**
     *
     * @return
     */
    public static DefaultTableModel GetRakAll() {
        ResultSet rs = null;
        DefaultTableModel model = new DefaultTableModel();
        String sql = "SELECT * FROM rak";
        try {
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                String columnName = rsmd.getColumnName(i);

                model.addColumn(columnName.toUpperCase());
            }

            while (rs.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = rs.getObject(i);
                }
                model.addRow(row);
            }

            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return model;
    }
    
}
