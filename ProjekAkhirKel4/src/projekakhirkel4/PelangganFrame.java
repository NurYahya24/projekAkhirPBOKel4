/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package projekakhirkel4;
import java.sql.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 *
 * @author Yha
 */
public class PelangganFrame extends javax.swing.JFrame {
    CardLayout cards;
    int posisi =1, qty;
    static int idPel;
    boolean kondisi;
    static ArrayList<Alat> dataAlat= new ArrayList<>();
    static ArrayList<Bahan> dataBahan = new ArrayList<>();
    static ArrayList<Pesanan> dataPesan = new ArrayList<>();
    /**
     * Creates new form PelangganFrame
     */
    
    void cekForm(){
        kondisi = true;
        if(txtNamaPel.getText().isEmpty()){
            kondisi = false;
        }else if(txtMailPel.getText().isEmpty()){
            kondisi = false;
        }else if(txtAlamatPel.getText().isEmpty()){
            kondisi = false;
        }else if(txtPassPel.getText().isEmpty()){
            kondisi = false;
        }
    }
        
    void readDB(){
        try{
            dataAlat.clear();
            dataBahan.clear();
            dataPesan.clear();
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbashwani", "root", "");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from tbakun where id='"+idPel+"'");
            if(!rs.next()){
                System.out.println("No Data Yet");
            }else{
                do{
                    String nama = rs.getString("nama");
                    String mail = rs.getString("email");
                    String alamat = rs.getString("alamat");
                    String pass = rs.getString("password");
                    txtNamaPel.setText(nama);
                    txtMailPel.setText(mail);
                    txtAlamatPel.setText(alamat);
                    txtPassPel.setText(pass);
                }while(rs.next());
            }
            rs.close();
            rs = stmt.executeQuery("select * from tbbarang where tipe ='Alat'");
            if(!rs.next()){
                System.out.println("No Data Yet");
            }else{
                do{
                    String nama = rs.getString("nama");
                    String jenis = rs.getString("jenis");
                    int stok = rs.getInt("stok");
                    int harga = rs.getInt("harga");
                    int id = rs.getInt("id");
                    Alat newAlat = new Alat(jenis, nama, stok, harga, id);
                    dataAlat.add(newAlat);
                }while(rs.next());
            }
            rs.close();
            rs = stmt.executeQuery("select * from tbbarang where tipe ='Bahan'");
            if(!rs.next()){
                System.out.println("No Data Yet");
            }else{
                do{
                    String nama = rs.getString("nama");
                    String jenis = rs.getString("jenis");
                    int stok = rs.getInt("stok");
                    int harga = rs.getInt("harga");
                    int id = rs.getInt("id");
                    String desk = rs.getString("desk");
                    Bahan newBahan = new Bahan(jenis, desk, nama, stok, harga, id);
                    dataBahan.add(newBahan);
                }while(rs.next());
            }
            rs.close();
            rs = stmt.executeQuery("select idPesan, tbakun.nama as 'nama', tbbarang.nama as 'item', qty, tbbarang.harga as 'harga', tbakun.alamat as 'alamat', status from tbpesan join tbakun join tbbarang on tbpesan.idPel = tbakun.id and tbpesan.idBar = tbbarang.id where tbpesan.idPel = '"+idPel+"'");
            if(!rs.next()){
                System.out.println("No Data Yet");
            }else{
                do{
                    String nama = rs.getString("nama");
                    String item = rs.getString("item");
                    String alamat = rs.getString("alamat");
                    String status = rs.getString("status");
                    int qts = rs.getInt("qty");
                    int harga = rs.getInt("harga");
                    int id = rs.getInt("idPesan");
                    Pesanan newPesan = new Pesanan(nama, item, alamat, status, qts, id, harga);
                    dataPesan.add(newPesan);
                }while(rs.next());
            }
            rs.close();
            rs = stmt.executeQuery("select * from tbriwayat where id='"+idPel+"'");
            if(!rs.next()){
                System.out.println("No Data Yet");
            }else{
                do{
                    String nama = rs.getString("nama");
                    String item = rs.getString("item");
                    String alamat = rs.getString("alamat");
                    String status = rs.getString("status");
                    int qts = rs.getInt("qty");
                    int harga = rs.getInt("harga");
                    Pesanan newPesan = new Pesanan(nama, item, alamat, status, qts, harga);
                    dataPesan.add(newPesan);
                }while(rs.next());
            }
            rs.close();
            stmt.close();
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    void showDesk(){
        if(tbDataBar.getSelectedRow() >= 0){
            if(tbDataBar.getModel().getValueAt(tbDataBar.getSelectedRow(), 1).equals("Alat")){
                labelDeskPesan.setVisible(false);
                labelDeskPesan1.setVisible(false);
                lineDeskPesan.setVisible(false);
            }else if(tbDataBar.getModel().getValueAt(tbDataBar.getSelectedRow(), 1).equals("Bahan")){
                labelDeskPesan.setVisible(true);
                labelDeskPesan1.setVisible(true);
                lineDeskPesan.setVisible(true);
                labelDeskPesan.setText(tbDataBar.getModel().getValueAt(tbDataBar.getSelectedRow(), 6).toString());
            }
            btnPesan.setVisible(true);
        }else{
            labelDeskPesan.setVisible(false);
            labelDeskPesan1.setVisible(false);
            lineDeskPesan.setVisible(false);
            btnPesan.setVisible(false);
        }
        if(tbTrans.getSelectedRow()>=0){
            int row = tbTrans.getSelectedRow();           
            String stats = tbTrans.getModel().getValueAt(row, 7).toString();
            if(stats.equals("Sedang Diproses")){
                btnBatalPesan.setVisible(true);
            }else{
                btnBatalPesan.setVisible(false);
            }
        }else{
            btnBatalPesan.setVisible(false);
        }
    }
    
    void dbToTb(){
        ((DefaultTableModel)tbDataBar.getModel()).setRowCount(0);
        ((DefaultTableModel)tbTrans.getModel()).setRowCount(0);
        if(ccDataBar.getSelectedItem().equals("Semua")){
                for(int i = 0; i<dataAlat.size();i++){
                    
                    String nama = dataAlat.get(i).getNama();
                    String tipe = "Alat";
                    String jenis = dataAlat.get(i).getJenis();
                    int stok = dataAlat.get(i).getStok();
                    int harga = dataAlat.get(i).getHarga();
                    int id = dataAlat.get(i).getId();

                    Object[] data = {nama, tipe, jenis, stok,"Rp. " +harga, id};
                    ((DefaultTableModel)tbDataBar.getModel()).addRow(data);
                }
                for(int i = 0; i<dataBahan.size();i++){
                    
                    String nama = dataBahan.get(i).getNama();
                    String tipe = "Bahan";
                    String jenis = dataBahan.get(i).getJenis();
                    int stok = dataBahan.get(i).getStok();
                    int harga = dataBahan.get(i).getHarga();
                    int id = dataBahan.get(i).getId();
                    String desk = dataBahan.get(i).getDeskripsi();

                    Object[] data = {nama, tipe, jenis, stok,"Rp. " +harga, id, desk};
                    ((DefaultTableModel)tbDataBar.getModel()).addRow(data);
                }
        }else if(ccDataBar.getSelectedItem().equals("Alat")){
            for(int i = 0; i<dataAlat.size();i++){

                String nama = dataAlat.get(i).getNama();
                String tipe = "Alat";
                String jenis = dataAlat.get(i).getJenis();
                int stok = dataAlat.get(i).getStok();
                int harga = dataAlat.get(i).getHarga();
                int id = dataAlat.get(i).getId();

                Object[] data = {nama, tipe, jenis, stok,"Rp. " +harga, id};
                ((DefaultTableModel)tbDataBar.getModel()).addRow(data);
            }
        }else if(ccDataBar.getSelectedItem().equals("Bahan")){
            for(int i = 0; i<dataBahan.size();i++){
                    
                    String nama = dataBahan.get(i).getNama();
                    String tipe = "Bahan";
                    String jenis = dataBahan.get(i).getJenis();
                    int stok = dataBahan.get(i).getStok();
                    int harga = dataBahan.get(i).getHarga();
                    int id = dataBahan.get(i).getId();
                    String desk = dataBahan.get(i).getDeskripsi();

                    Object[] data = {nama, tipe, jenis, stok,"Rp. " +harga, id, desk};
                    ((DefaultTableModel)tbDataBar.getModel()).addRow(data);
                }
        }
        if(ccTableTrans.getSelectedItem().equals("Semua")){
            for(int i = 0; i<dataPesan.size();i++){
                int id = dataPesan.get(i).getId();
                String nama = dataPesan.get(i).getNamaPel();
                String alamat = dataPesan.get(i).getAlamat();
                String item = dataPesan.get(i).getNamaBar();
                int qts = dataPesan.get(i).getQty();
                int harga = dataPesan.get(i).getHarga();
                int total = dataPesan.get(i).getTotal();
                String status = dataPesan.get(i).getStatus();
                Object[] data = {id, nama, alamat, item, qts,"Rp. " +harga,"Rp. " +total, status};
                ((DefaultTableModel)tbTrans.getModel()).addRow(data);
            }    
        }else if(ccTableTrans.getSelectedItem().equals("Sedang Diproses")){
            for(int i = 0; i<dataPesan.size();i++){
                if(dataPesan.get(i).getStatus().equals("Sedang Diproses")){
                    int id = dataPesan.get(i).getId();
                    String nama = dataPesan.get(i).getNamaPel();
                    String alamat = dataPesan.get(i).getAlamat();
                    String item = dataPesan.get(i).getNamaBar();
                    int qts = dataPesan.get(i).getQty();
                    int harga = dataPesan.get(i).getHarga();
                    int total = dataPesan.get(i).getTotal();
                    String status = dataPesan.get(i).getStatus();
                    Object[] data = {id, nama, alamat, item, qts,"Rp. " +harga,"Rp. " +total, status};
                    ((DefaultTableModel)tbTrans.getModel()).addRow(data);
                }
            }
        }else if(ccTableTrans.getSelectedItem().equals("Selesai")){
            for(int i = 0; i<dataPesan.size();i++){
                if(dataPesan.get(i).getStatus().equals("Selesai")){
                    int id = dataPesan.get(i).getId();
                    String nama = dataPesan.get(i).getNamaPel();
                    String alamat = dataPesan.get(i).getAlamat();
                    String item = dataPesan.get(i).getNamaBar();
                    int qts = dataPesan.get(i).getQty();
                    int harga = dataPesan.get(i).getHarga();
                    int total = dataPesan.get(i).getTotal();
                    String status = dataPesan.get(i).getStatus();
                    Object[] data = {id, nama, alamat, item, qts,"Rp. " +harga,"Rp. " +total, status};
                    ((DefaultTableModel)tbTrans.getModel()).addRow(data);
                }
            }
        }else if(ccTableTrans.getSelectedItem().equals("Lainnya")){
            for(int i = 0; i<dataPesan.size();i++){
                if(!dataPesan.get(i).getStatus().equals("Sedang Diproses") && !dataPesan.get(i).getStatus().equals("Selesai")){
                    int id = dataPesan.get(i).getId();
                    String nama = dataPesan.get(i).getNamaPel();
                    String alamat = dataPesan.get(i).getAlamat();
                    String item = dataPesan.get(i).getNamaBar();
                    int qts = dataPesan.get(i).getQty();
                    int harga = dataPesan.get(i).getHarga();
                    int total = dataPesan.get(i).getTotal();
                    String status = dataPesan.get(i).getStatus();
                    Object[] data = {id, nama, alamat, item, qts,"Rp. " +harga,"Rp. " +total, status};
                    ((DefaultTableModel)tbTrans.getModel()).addRow(data);
                }
            }
        }
    }
    
    public PelangganFrame(int idLog) {
        idPel = idLog;
        System.out.println(idPel);
        initComponents();
        ((JSpinner.DefaultEditor)txtQtyPesan.getEditor()).getTextField().setEditable(false);
        ccDataBar.add("Semua");
        ccDataBar.add("Alat");
        ccDataBar.add("Bahan");
        ccTableTrans.add("Semua");
        ccTableTrans.add("Sedang Diproses");
        ccTableTrans.add("Selesai");
        ccTableTrans.add("Lainnya");
        cards = (CardLayout)(CardsPanes.getLayout());
        tbTrans.removeColumn(tbTrans.getColumnModel().getColumn(0));
        tbDataBar.removeColumn(tbDataBar.getColumnModel().getColumn(6));
        tbDataBar.removeColumn(tbDataBar.getColumnModel().getColumn(5));
        readDB();
        dbToTb();
        showDesk();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnBarang = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        btnTransaksi = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btnPorfil = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btnExit = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        CardsPanes = new javax.swing.JPanel();
        BarangPane = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        tbPane = new javax.swing.JScrollPane();
        tbDataBar = new javax.swing.JTable();
        btnPesan = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        ccDataBar = new java.awt.Choice();
        labelDeskPesan1 = new javax.swing.JLabel();
        lineDeskPesan = new javax.swing.JSeparator();
        jLabel21 = new javax.swing.JLabel();
        labelDeskPesan = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        txtQtyPesan = new javax.swing.JSpinner();
        TransaksiPane = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbTrans = new javax.swing.JTable();
        ccTableTrans = new java.awt.Choice();
        btnBatalPesan = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        KaryawanForm = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        txtMailPel = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        txtNamaPel = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        txtAlamatPel = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        btnSavePel = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jSeparator11 = new javax.swing.JSeparator();
        jLabel19 = new javax.swing.JLabel();
        txtPassPel = new javax.swing.JPasswordField();
        FormBarang = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jSeparator12 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        txtHargaBar = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jSeparator13 = new javax.swing.JSeparator();
        txtNamaBar = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jSeparator14 = new javax.swing.JSeparator();
        txtStokBar = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        labelDesk = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        btnSaveBar = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        ccJenisBar = new java.awt.Choice();
        btnBatalKar = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        txtDeskBar = new javax.swing.JTextField();
        lineDeskBar = new javax.swing.JSeparator();
        jLabel32 = new javax.swing.JLabel();
        ccTipeBar = new java.awt.Choice();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setSize(new java.awt.Dimension(802, 500));

        jPanel1.setPreferredSize(new java.awt.Dimension(802, 500));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(51, 0, 102));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnBarang.setBackground(new java.awt.Color(102, 0, 153));
        btnBarang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBarangMouseClicked(evt);
            }
        });

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/barang.png"))); // NOI18N

        jLabel12.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Barang");

        javax.swing.GroupLayout btnBarangLayout = new javax.swing.GroupLayout(btnBarang);
        btnBarang.setLayout(btnBarangLayout);
        btnBarangLayout.setHorizontalGroup(
            btnBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnBarangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel12)
                .addContainerGap())
        );
        btnBarangLayout.setVerticalGroup(
            btnBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnBarangLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(btnBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel2.add(btnBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 220, 40));

        btnTransaksi.setBackground(new java.awt.Color(102, 0, 153));
        btnTransaksi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTransaksiMouseClicked(evt);
            }
        });

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/transaksi.png"))); // NOI18N

        jLabel14.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Transaksi");

        javax.swing.GroupLayout btnTransaksiLayout = new javax.swing.GroupLayout(btnTransaksi);
        btnTransaksi.setLayout(btnTransaksiLayout);
        btnTransaksiLayout.setHorizontalGroup(
            btnTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnTransaksiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel14)
                .addContainerGap())
        );
        btnTransaksiLayout.setVerticalGroup(
            btnTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnTransaksiLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(btnTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel2.add(btnTransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 220, 40));

        btnPorfil.setBackground(new java.awt.Color(102, 0, 153));
        btnPorfil.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPorfil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPorfilMouseClicked(evt);
            }
        });

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/gawai.png"))); // NOI18N

        jLabel10.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Profil");

        javax.swing.GroupLayout btnPorfilLayout = new javax.swing.GroupLayout(btnPorfil);
        btnPorfil.setLayout(btnPorfilLayout);
        btnPorfilLayout.setHorizontalGroup(
            btnPorfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnPorfilLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addContainerGap())
        );
        btnPorfilLayout.setVerticalGroup(
            btnPorfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnPorfilLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(btnPorfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel2.add(btnPorfil, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 220, 40));

        btnExit.setBackground(new java.awt.Color(102, 0, 153));
        btnExit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnExitMouseClicked(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logout.png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Log Out");

        javax.swing.GroupLayout btnExitLayout = new javax.swing.GroupLayout(btnExit);
        btnExit.setLayout(btnExitLayout);
        btnExitLayout.setHorizontalGroup(
            btnExitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnExitLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addContainerGap())
        );
        btnExitLayout.setVerticalGroup(
            btnExitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnExitLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(btnExitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel2.add(btnExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 220, 40));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/banner.png"))); // NOI18N
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 180, 80));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 210, 500));

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        CardsPanes.setLayout(new java.awt.CardLayout());

        BarangPane.setBackground(new java.awt.Color(204, 204, 204));
        BarangPane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(102, 102, 255));

        jLabel6.setFont(new java.awt.Font("Candara Light", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Pesan Barang");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(193, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(47, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(28, 28, 28))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))))
        );

        BarangPane.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 590, -1));

        tbDataBar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nama", "Tipe", "Jenis", "Stok", "Harga", "id", "desk"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbDataBar.getTableHeader().setReorderingAllowed(false);
        tbDataBar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDataBarMouseClicked(evt);
            }
        });
        tbDataBar.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                tbDataBarCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        tbDataBar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbDataBarKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbDataBarKeyReleased(evt);
            }
        });
        tbPane.setViewportView(tbDataBar);

        BarangPane.add(tbPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 550, 290));

        btnPesan.setBackground(new java.awt.Color(102, 102, 255));
        btnPesan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        btnPesan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPesanMouseClicked(evt);
            }
        });

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Pesan");

        javax.swing.GroupLayout btnPesanLayout = new javax.swing.GroupLayout(btnPesan);
        btnPesan.setLayout(btnPesanLayout);
        btnPesanLayout.setHorizontalGroup(
            btnPesanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnPesanLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel4)
                .addContainerGap(45, Short.MAX_VALUE))
        );
        btnPesanLayout.setVerticalGroup(
            btnPesanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnPesanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                .addContainerGap())
        );

        BarangPane.add(btnPesan, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 160, 120, 40));

        ccDataBar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ccDataBarItemStateChanged(evt);
            }
        });
        BarangPane.add(ccDataBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 160, -1));

        labelDeskPesan1.setText("Deskripsi");
        BarangPane.add(labelDeskPesan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 150, -1, -1));

        lineDeskPesan.setBackground(new java.awt.Color(0, 0, 0));
        lineDeskPesan.setForeground(new java.awt.Color(0, 0, 0));
        BarangPane.add(lineDeskPesan, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 190, 100, 10));

        jLabel21.setText("Qty");
        BarangPane.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 150, -1, -1));

        labelDeskPesan.setText("lbDesk");
        BarangPane.add(labelDeskPesan, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 170, 120, -1));

        jSeparator9.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator9.setForeground(new java.awt.Color(0, 0, 0));
        BarangPane.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 190, 100, 10));

        txtQtyPesan.setModel(new javax.swing.SpinnerNumberModel(0L, 0L, null, 1L));
        txtQtyPesan.setEditor(new javax.swing.JSpinner.NumberEditor(txtQtyPesan, ""));
        BarangPane.add(txtQtyPesan, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 170, 100, -1));

        CardsPanes.add(BarangPane, "card2");

        TransaksiPane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(new java.awt.Color(102, 102, 255));

        jLabel8.setFont(new java.awt.Font("Candara Light", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Riwayat Transaksi");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(164, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(47, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(28, 28, 28))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))))
        );

        TransaksiPane.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 590, -1));

        tbTrans.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Nama", "Alamat", "Item", "Qty", "Harga", "Total", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbTrans.getTableHeader().setReorderingAllowed(false);
        tbTrans.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTransMouseClicked(evt);
            }
        });
        tbTrans.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbTransKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tbTrans);
        if (tbTrans.getColumnModel().getColumnCount() > 0) {
            tbTrans.getColumnModel().getColumn(4).setPreferredWidth(15);
        }

        TransaksiPane.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 530, 290));

        ccTableTrans.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ccTableTransItemStateChanged(evt);
            }
        });
        TransaksiPane.add(ccTableTrans, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 160, -1));

        btnBatalPesan.setBackground(new java.awt.Color(102, 102, 255));
        btnBatalPesan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        btnBatalPesan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBatalPesanMouseClicked(evt);
            }
        });

        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Batalkan!");

        javax.swing.GroupLayout btnBatalPesanLayout = new javax.swing.GroupLayout(btnBatalPesan);
        btnBatalPesan.setLayout(btnBatalPesanLayout);
        btnBatalPesanLayout.setHorizontalGroup(
            btnBatalPesanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnBatalPesanLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel15)
                .addContainerGap(38, Short.MAX_VALUE))
        );
        btnBatalPesanLayout.setVerticalGroup(
            btnBatalPesanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnBatalPesanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                .addContainerGap())
        );

        TransaksiPane.add(btnBatalPesan, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 160, 130, 40));

        CardsPanes.add(TransaksiPane, "card2");

        KaryawanForm.setBackground(new java.awt.Color(255, 255, 255));
        KaryawanForm.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel10.setBackground(new java.awt.Color(102, 102, 255));

        jLabel17.setFont(new java.awt.Font("Candara Light", 0, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Profile");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(249, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap(47, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(28, 28, 28))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))))
        );

        KaryawanForm.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 590, -1));

        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));
        KaryawanForm.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 330, 270, -1));

        txtMailPel.setBorder(null);
        KaryawanForm.add(txtMailPel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, 270, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setText("E-mail");
        KaryawanForm.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, -1, -1));

        jSeparator6.setForeground(new java.awt.Color(0, 0, 0));
        KaryawanForm.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 270, -1));

        txtNamaPel.setBorder(null);
        KaryawanForm.add(txtNamaPel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 270, -1));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel16.setText("Nama");
        KaryawanForm.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, -1, -1));

        jSeparator8.setForeground(new java.awt.Color(0, 0, 0));
        KaryawanForm.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, 270, -1));

        txtAlamatPel.setBorder(null);
        KaryawanForm.add(txtAlamatPel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, 270, -1));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel18.setText("Alamat");
        KaryawanForm.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, -1, -1));

        btnSavePel.setBackground(new java.awt.Color(102, 102, 255));
        btnSavePel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        btnSavePel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSavePel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSavePelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSavePelMouseEntered(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Simpan");

        javax.swing.GroupLayout btnSavePelLayout = new javax.swing.GroupLayout(btnSavePel);
        btnSavePel.setLayout(btnSavePelLayout);
        btnSavePelLayout.setHorizontalGroup(
            btnSavePelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnSavePelLayout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addComponent(jLabel5)
                .addContainerGap(86, Short.MAX_VALUE))
        );
        btnSavePelLayout.setVerticalGroup(
            btnSavePelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnSavePelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                .addContainerGap())
        );

        KaryawanForm.add(btnSavePel, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 430, 220, 50));

        jSeparator11.setForeground(new java.awt.Color(0, 0, 0));
        KaryawanForm.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 400, 270, -1));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel19.setText("Password");
        KaryawanForm.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 340, -1, -1));

        txtPassPel.setBorder(null);
        txtPassPel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPassPelActionPerformed(evt);
            }
        });
        KaryawanForm.add(txtPassPel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 370, 270, 20));

        CardsPanes.add(KaryawanForm, "card2");

        FormBarang.setBackground(new java.awt.Color(255, 255, 255));
        FormBarang.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel11.setBackground(new java.awt.Color(102, 102, 255));

        jLabel22.setFont(new java.awt.Font("Candara Light", 0, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Form Input Barang");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(154, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap(47, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addGap(28, 28, 28))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))))
        );

        FormBarang.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 590, -1));

        jSeparator5.setForeground(new java.awt.Color(0, 0, 0));
        FormBarang.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 190, 270, -1));

        txtHargaBar.setBorder(null);
        FormBarang.add(txtHargaBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 160, 240, 20));

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel23.setText("Harga");
        FormBarang.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 130, -1, -1));

        jSeparator13.setForeground(new java.awt.Color(0, 0, 0));
        FormBarang.add(jSeparator13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 270, -1));

        txtNamaBar.setBorder(null);
        FormBarang.add(txtNamaBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 270, -1));

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel25.setText("Nama");
        FormBarang.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, -1));

        jSeparator14.setForeground(new java.awt.Color(0, 0, 0));
        FormBarang.add(jSeparator14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 270, -1));

        txtStokBar.setBorder(null);
        FormBarang.add(txtStokBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 270, -1));

        jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel26.setText("Stok");
        FormBarang.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, -1));

        labelDesk.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        labelDesk.setText("Deskripsi");
        FormBarang.add(labelDesk, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, -1, -1));

        jLabel28.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel28.setText("Jenis");
        FormBarang.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 270, -1, -1));

        btnSaveBar.setBackground(new java.awt.Color(102, 102, 255));
        btnSaveBar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        btnSaveBar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSaveBar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSaveBarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSaveBarMouseEntered(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("Simpan");

        javax.swing.GroupLayout btnSaveBarLayout = new javax.swing.GroupLayout(btnSaveBar);
        btnSaveBar.setLayout(btnSaveBarLayout);
        btnSaveBarLayout.setHorizontalGroup(
            btnSaveBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnSaveBarLayout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addComponent(jLabel29)
                .addContainerGap(86, Short.MAX_VALUE))
        );
        btnSaveBarLayout.setVerticalGroup(
            btnSaveBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnSaveBarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                .addContainerGap())
        );

        FormBarang.add(btnSaveBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 420, 220, 50));

        jLabel30.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel30.setText("Rp.");
        FormBarang.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 160, -1, -1));

        ccJenisBar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ccJenisBarItemStateChanged(evt);
            }
        });
        FormBarang.add(ccJenisBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 300, 270, 30));

        btnBatalKar.setBackground(new java.awt.Color(255, 255, 255));
        btnBatalKar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 255)));
        btnBatalKar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBatalKar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBatalKarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBatalKarMouseEntered(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(102, 102, 255));
        jLabel31.setText("Batal");

        javax.swing.GroupLayout btnBatalKarLayout = new javax.swing.GroupLayout(btnBatalKar);
        btnBatalKar.setLayout(btnBatalKarLayout);
        btnBatalKarLayout.setHorizontalGroup(
            btnBatalKarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnBatalKarLayout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addComponent(jLabel31)
                .addContainerGap(97, Short.MAX_VALUE))
        );
        btnBatalKarLayout.setVerticalGroup(
            btnBatalKarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnBatalKarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                .addContainerGap())
        );

        FormBarang.add(btnBatalKar, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 420, 220, -1));

        txtDeskBar.setBorder(null);
        FormBarang.add(txtDeskBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 260, 20));

        lineDeskBar.setForeground(new java.awt.Color(0, 0, 0));
        FormBarang.add(lineDeskBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 270, 10));

        jLabel32.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel32.setText("Tipe");
        FormBarang.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 200, -1, -1));

        ccTipeBar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ccTipeBarItemStateChanged(evt);
            }
        });
        FormBarang.add(ccTipeBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 230, 270, 30));

        CardsPanes.add(FormBarang, "card2");

        jPanel3.add(CardsPanes, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 590, 500));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 0, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBarangMouseClicked
        cards.first(CardsPanes);
        posisi = 1;
    }//GEN-LAST:event_btnBarangMouseClicked

    private void btnTransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTransaksiMouseClicked
        if(posisi==1){
            cards.next(CardsPanes);
        }else if(posisi==2){

        }else if(posisi==3){
            cards.previous(CardsPanes);
        }else if(posisi==4){
            cards.previous(CardsPanes);
            cards.previous(CardsPanes);
        }
        posisi = 2;
    }//GEN-LAST:event_btnTransaksiMouseClicked

    private void btnPorfilMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPorfilMouseClicked
        if(posisi==1){
            cards.next(CardsPanes);
            cards.next(CardsPanes);
        }else if(posisi==2){
            cards.next(CardsPanes);
        }else if(posisi==3){

        }else if(posisi==4){
            cards.previous(CardsPanes);
        }
        posisi = 3;
    }//GEN-LAST:event_btnPorfilMouseClicked

    private void btnExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseClicked
        this.setVisible(false);
        JOptionPane.showMessageDialog(null , "Logging Out");
        Main mn = new Main();
        mn.setVisible(true);
    }//GEN-LAST:event_btnExitMouseClicked

    private void btnPesanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPesanMouseClicked
        qty = Integer.parseInt(txtQtyPesan.getModel().getValue().toString());
        int stok = Integer.parseInt(tbDataBar.getModel().getValueAt(tbDataBar.getSelectedRow(), 3).toString()) - qty;
        if(qty > 0 && stok >=0){
            try{
                int idBar = Integer.parseInt(tbDataBar.getModel().getValueAt(tbDataBar.getSelectedRow(), 5).toString());
                String status = "Sedang Diproses";
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbashwani", "root", "");
                Statement stmt = conn.createStatement();
                stmt.executeUpdate("insert into tbpesan (idPesan, idPel, idBar, qty, status) values ('0', '"+idPel+"', '"+idBar+"', '"+qty+"', '"+status+"')");
                stmt.executeUpdate("update tbbarang set stok='"+stok+"' where id='"+idBar+"'");
                JOptionPane.showMessageDialog(null, "Data Pesanan Sudah Tersimpan");
                conn.close();
                stmt.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }else{
            JOptionPane.showMessageDialog(null, "Gagal Membuat Pesanan");
        }
        readDB();
        dbToTb();
    }//GEN-LAST:event_btnPesanMouseClicked

    private void ccDataBarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ccDataBarItemStateChanged
        readDB();
        dbToTb();
    }//GEN-LAST:event_ccDataBarItemStateChanged

    private void btnSavePelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSavePelMouseClicked
        cekForm();
        if(kondisi){
            try{
                String nama = txtNamaPel.getText();
                String email = txtMailPel.getText();
                String alamat = txtAlamatPel.getText();
                String pass = txtPassPel.getText();
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbashwani", "root", "");
                Statement stmt = conn.createStatement();
                stmt.executeUpdate("update tbakun set nama='"+nama+"', email='"+email+"', alamat='"+alamat+"', password='"+pass+"' where id='"+idPel+"'");
                JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
                conn.close();
                stmt.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }readDB();
        dbToTb();

    }//GEN-LAST:event_btnSavePelMouseClicked

    private void btnSavePelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSavePelMouseEntered

    }//GEN-LAST:event_btnSavePelMouseEntered

    private void txtPassPelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPassPelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPassPelActionPerformed

    private void btnSaveBarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveBarMouseClicked

    }//GEN-LAST:event_btnSaveBarMouseClicked

    private void btnSaveBarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveBarMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSaveBarMouseEntered

    private void ccJenisBarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ccJenisBarItemStateChanged

    }//GEN-LAST:event_ccJenisBarItemStateChanged

    private void btnBatalKarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBatalKarMouseClicked
        cards.first(CardsPanes);
        posisi = 1;
    }//GEN-LAST:event_btnBatalKarMouseClicked

    private void btnBatalKarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBatalKarMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBatalKarMouseEntered

    private void ccTipeBarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ccTipeBarItemStateChanged

    }//GEN-LAST:event_ccTipeBarItemStateChanged

    private void tbDataBarCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_tbDataBarCaretPositionChanged
        showDesk();
    }//GEN-LAST:event_tbDataBarCaretPositionChanged

    private void tbDataBarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDataBarMouseClicked
        showDesk();
    }//GEN-LAST:event_tbDataBarMouseClicked

    private void tbDataBarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDataBarKeyPressed

    }//GEN-LAST:event_tbDataBarKeyPressed

    private void tbDataBarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDataBarKeyReleased
        showDesk();
    }//GEN-LAST:event_tbDataBarKeyReleased

    private void ccTableTransItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ccTableTransItemStateChanged
        readDB();
        dbToTb();
        showDesk();
    }//GEN-LAST:event_ccTableTransItemStateChanged

    private void tbTransMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTransMouseClicked
        showDesk();
    }//GEN-LAST:event_tbTransMouseClicked

    private void tbTransKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbTransKeyReleased
        showDesk();
    }//GEN-LAST:event_tbTransKeyReleased

    private void btnBatalPesanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBatalPesanMouseClicked
        int row = tbTrans.getSelectedRow();           
        String idBatal = tbTrans.getModel().getValueAt(row, 0).toString();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbashwani", "root", "");
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("update tbpesan set status='Minta Batal' where idPesan='"+idBatal+"'");
            JOptionPane.showMessageDialog(null, "Silahkan Tunggu Konfirmasi Karyawan");
            conn.close();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        readDB();
        dbToTb();
        showDesk();
    }//GEN-LAST:event_btnBatalPesanMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PelangganFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PelangganFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PelangganFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PelangganFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PelangganFrame(idPel).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BarangPane;
    private javax.swing.JPanel CardsPanes;
    private javax.swing.JPanel FormBarang;
    private javax.swing.JPanel KaryawanForm;
    private javax.swing.JPanel TransaksiPane;
    private javax.swing.JPanel btnBarang;
    private javax.swing.JPanel btnBatalKar;
    private javax.swing.JPanel btnBatalPesan;
    private javax.swing.JPanel btnExit;
    private javax.swing.JPanel btnPesan;
    private javax.swing.JPanel btnPorfil;
    private javax.swing.JPanel btnSaveBar;
    private javax.swing.JPanel btnSavePel;
    private javax.swing.JPanel btnTransaksi;
    private java.awt.Choice ccDataBar;
    private java.awt.Choice ccJenisBar;
    private java.awt.Choice ccTableTrans;
    private java.awt.Choice ccTipeBar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JLabel labelDesk;
    private javax.swing.JLabel labelDeskPesan;
    private javax.swing.JLabel labelDeskPesan1;
    private javax.swing.JSeparator lineDeskBar;
    private javax.swing.JSeparator lineDeskPesan;
    private javax.swing.JTable tbDataBar;
    private javax.swing.JScrollPane tbPane;
    private javax.swing.JTable tbTrans;
    private javax.swing.JTextField txtAlamatPel;
    private javax.swing.JTextField txtDeskBar;
    private javax.swing.JTextField txtHargaBar;
    private javax.swing.JTextField txtMailPel;
    private javax.swing.JTextField txtNamaBar;
    private javax.swing.JTextField txtNamaPel;
    private javax.swing.JPasswordField txtPassPel;
    private javax.swing.JSpinner txtQtyPesan;
    private javax.swing.JTextField txtStokBar;
    // End of variables declaration//GEN-END:variables
}
