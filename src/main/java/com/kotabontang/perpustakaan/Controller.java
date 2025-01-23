package com.kotabontang.perpustakaan;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Controller {

    public static void GenerateDummy(){
        // Generate dummy book
        String[] judul_arr = {"Buku A", "Buku B", "Buku C", "Buku D", "Buku E"};
        String[] penerbit_arr = {"Penerbit A", "Penerbit B", "Penerbit C", "Penerbit D", "Penerbit E"};
        String[] pengarang_arr = {"Pengarang A", "Pengarang B", "Pengarang C", "Pengarang D", "Pengarang E"};
        String[] rak_arr = {"Rak A", "Rak B", "Rak C", "Rak D", "Rak E"};
        String[] alamat_arr = {"Alamat A", "Alamat B", "Alamat C", "Alamat D", "Alamat E"};
        Integer[] jumlah_arr = {12,14,23,34,45,56,67,78,89,90};
        Integer dummy_buku = 30;

        // generate dummy penerbit
        for (int i = 0; i < penerbit_arr.length; i++) {
            String alamat =  alamat_arr[(int) (Math.random() * alamat_arr.length)];
            Model.InsertPenerbit(penerbit_arr[i], alamat);
        }

        // generate dummy book
        for (int i = 0; i < dummy_buku; i++) {
            String penerbit = penerbit_arr[(int) (Math.random() * penerbit_arr.length)];
            String kode_penerbit = Model.GetKodePenerbit(penerbit);
            String pengarang = pengarang_arr[(int) (Math.random() * pengarang_arr.length)];
            String judul = judul_arr[(int) (Math.random() * judul_arr.length)];

            Model.InsertBook(judul + i, pengarang, kode_penerbit);
        }

        // generate dummy rak
        for (int i = 0; i < rak_arr.length; i++) {
            Model.InsertRak(rak_arr[i]);
        }

        // generate dummy rak buku
        for (int i = 0; i < judul_arr.length; i++) {
            String kode_buku = Model.GetKodeBuku(judul_arr[(int) (Math.random() * judul_arr.length)]);
            String kode_rak = Model.GetKodeRak(rak_arr[(int) (Math.random() * rak_arr.length)]);
            Model.InsertRakBuku(kode_rak, kode_buku, jumlah_arr[i]);
        }

    }

    public static void TruncateAll(){
        Model.TruncateAll();
    }

    public static void saveBuku(String kode_buku, String judul, String pengarang, String penerbit){
        String kode_penerbit = Model.GetKodePenerbit(penerbit);
        Boolean checkrecord = Model.CheckRecord("buku", "kode_penerbit", kode_penerbit);

        if (checkrecord){
            Model.UpdateBook(kode_buku, judul, pengarang, kode_penerbit);
        } else {
            Model.InsertBook(judul, pengarang, kode_penerbit);
        }

        JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
    }

    public static void hapusBuku(String kode_buku){
        Model.DeleteBook(kode_buku);
        JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
    }

    public static void ResetFormBuku(JTextField textKodeBuku, JTextArea textJudulBuku, JTextField textPengarang, JComboBox comboPenerbit){
        textKodeBuku.setText("");
        textJudulBuku.setText("");
        textPengarang.setText("");
        comboPenerbit.setSelectedIndex(0);
        textKodeBuku.setEditable(false);
        textJudulBuku.requestFocus();
    }

    public static void EditFormBuku(JTextField textKodeBuku, JTextArea textJudulBuku, JTextField textPengarang, JComboBox comboPenerbit, String kode_buku, String judul, String pengarang, String penerbit){
        textKodeBuku.setText(kode_buku);
        textJudulBuku.setText(judul);
        textPengarang.setText(pengarang);
        comboPenerbit.setSelectedItem(penerbit);
        textKodeBuku.setEditable(false);
        textJudulBuku.requestFocus();
    }

    public static void hapusPenerbit(String kode_penerbit) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'hapusPenerbit'");
    }

    public static void EditFormPenerbit(JTextField textKodePenerbit, JTextField textNamaPenerbit, String kode_penerbit,
            String nama_penerbit) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'EditFormPenerbit'");
    }

    public static void ResetFormPenerbit(JTextField textKodePenerbit, JTextField textNamaPenerbit) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ResetFormPenerbit'");
    }

    public static void savePenerbit(String text, String text2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'savePenerbit'");
    }

    public static void EditFormPeminjam(JTextField textKodePeminjam, JTextField textNamaPeminjam, JTextArea textAlamat,
            String kode_peminjam, String nama_peminjam, String alamat) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'EditFormPeminjam'");
    }

    public static void ResetFormPeminjam(JTextField textKodePeminjam, JTextField textNamaPeminjam,
            JTextArea textAlamat) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ResetFormPeminjam'");
    }

    public static void savePeminjam(String text, String text2, String text3) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'savePeminjam'");
    }

    public static void hapusPeminjam(String kode_peminjam) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'hapusPeminjam'");
    }

    public static void hapusRak(String kode_rak) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'hapusRak'");
    }

    public static void EditFormRak(JTextField textKodeRak, JTextField textNamaRak, String kode_rak, String nama_rak) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'EditFormRak'");
    }

    public static void ResetFormRak(JTextField textKodeRak, JTextField textNamaRak) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ResetFormRak'");
    }

    public static void saveRak(String text, String text2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveRak'");
    }
    
}
