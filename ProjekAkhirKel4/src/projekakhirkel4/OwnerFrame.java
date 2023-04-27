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
public class OwnerFrame extends javax.swing.JFrame {
    CardLayout cards;
    boolean kondisi;
    boolean ubah;
    int posisi = 1, pemasukan;
    String idEdit, mailEdit;
    static ArrayList<Karyawan> dataKar = new ArrayList<>();
    static ArrayList<Alat> dataAlat = new ArrayList<>();
    static ArrayList<Bahan> dataBahan = new ArrayList<>();
    static ArrayList<Pesanan> dataPesan = new ArrayList<>();
    
    
    
    void bersihForm(){
        txtNamaKar.setText("");
        txtMailKar.setText("");
        txtAlamatKar.setText("");
        txtGajiKar.setText("");
    }
    
    void cekAngka(){
        if(kondisi){
            if(Integer.parseInt(txtGajiKar.getText())<0){
                kondisi = false;
            }    
        }
    }
    
    void cekForm(){
        kondisi = true;
        try{
            Integer.parseInt(txtGajiKar.getText());
        }catch(NumberFormatException e){
            kondisi = false;
        }
        if(txtNamaKar.getText().isEmpty()){
            kondisi = false;
        }else if(txtMailKar.getText().isEmpty()){
            kondisi = false;
        }else if(txtAlamatKar.getText().isEmpty()){
            kondisi = false;
        }else if(txtGajiKar.getText().isEmpty()){
            kondisi = false;
        }
        cekAngka();
    }
    
    private void dbToTb(){
        ((DefaultTableModel)tbDataBar.getModel()).setRowCount(0);
        ((DefaultTableModel)tbDataKar.getModel()).setRowCount(0);
        ((DefaultTableModel)tbTrans.getModel()).setRowCount(0);
        for(int i = 0; i<dataKar.size();i++){
            int id = dataKar.get(i).getId();
            String nama = dataKar.get(i).getNama();
            String mail = dataKar.get(i).getEmail();
            String alamat = dataKar.get(i).getAlamat();
            int gaji =  dataKar.get(i).getGaji();
            String shift = dataKar.get(i).getShift();
            
            Object[] data = {id, nama, mail, alamat,"Rp. " + gaji, shift};
            ((DefaultTableModel)tbDataKar.getModel()).addRow(data);
        }
        //=========================//
        //DISPLAY DATA BARANG
        //=========================//
        if(ccDataBar.getSelectedItem().equals("Semua")){
                for(int i = 0; i<dataAlat.size();i++){
                    Object data = dataAlat.get(i).displayBarang();
                    ((DefaultTableModel)tbDataBar.getModel()).addRow((Object[]) data);
                }
                for(int i = 0; i<dataBahan.size();i++){
                    Object data = dataBahan.get(i).displayBarang();
                    ((DefaultTableModel)tbDataBar.getModel()).addRow((Object[]) data);
                }
        }else if(ccDataBar.getSelectedItem().equals("Alat")){
            for(int i = 0; i<dataAlat.size();i++){
                    Object data = dataAlat.get(i).displayBarang();
                    ((DefaultTableModel)tbDataBar.getModel()).addRow((Object[]) data);
                }
        }else if(ccDataBar.getSelectedItem().equals("Bahan")){
            for(int i = 0; i<dataBahan.size();i++){
                    Object data = dataBahan.get(i).displayBarang();
                    ((DefaultTableModel)tbDataBar.getModel()).addRow((Object[]) data);
                }
        }
        if(ccDataTrans.getSelectedItem().equals("Semua")){
            for(int i = 0; i<dataPesan.size();i++){
                String nama = dataPesan.get(i).getNamaPel();
                String alamat = dataPesan.get(i).getAlamat();
                String item = dataPesan.get(i).getNamaBar();
                int qts = dataPesan.get(i).getQty();
                int harga = dataPesan.get(i).getHarga();
                int total = dataPesan.get(i).getTotal();
                String status = dataPesan.get(i).getStatus();
                Object[] data = {nama, alamat, item, qts, harga, total, status};
                ((DefaultTableModel)tbTrans.getModel()).addRow(data);
            }    
        }else if(ccDataTrans.getSelectedItem().equals("Sedang Diproses")){
            for(int i = 0; i<dataPesan.size();i++){
                if(dataPesan.get(i).getStatus().equals("Sedang Diproses")){
                    String nama = dataPesan.get(i).getNamaPel();
                    String alamat = dataPesan.get(i).getAlamat();
                    String item = dataPesan.get(i).getNamaBar();
                    int qts = dataPesan.get(i).getQty();
                    int harga = dataPesan.get(i).getHarga();
                    int total = dataPesan.get(i).getTotal();
                    String status = dataPesan.get(i).getStatus();
                    Object[] data = {nama, alamat, item, qts, harga, total, status};
                    ((DefaultTableModel)tbTrans.getModel()).addRow(data);
                }
            }
        }else if(ccDataTrans.getSelectedItem().equals("Selesai")){
            for(int i = 0; i<dataPesan.size();i++){
                if(dataPesan.get(i).getStatus().equals("Selesai")){
                    String nama = dataPesan.get(i).getNamaPel();
                    String alamat = dataPesan.get(i).getAlamat();
                    String item = dataPesan.get(i).getNamaBar();
                    int qts = dataPesan.get(i).getQty();
                    int harga = dataPesan.get(i).getHarga();
                    int total = dataPesan.get(i).getTotal();
                    String status = dataPesan.get(i).getStatus();
                    Object[] data = {nama, alamat, item, qts, harga, total, status};
                    ((DefaultTableModel)tbTrans.getModel()).addRow(data);
                }
            }
        }else if(ccDataTrans.getSelectedItem().equals("Lainnya")){
            for(int i = 0; i<dataPesan.size();i++){
                if(!dataPesan.get(i).getStatus().equals("Sedang Diproses") && !dataPesan.get(i).getStatus().equals("Selesai")){
                    String nama = dataPesan.get(i).getNamaPel();
                    String alamat = dataPesan.get(i).getAlamat();
                    String item = dataPesan.get(i).getNamaBar();
                    int qts = dataPesan.get(i).getQty();
                    int harga = dataPesan.get(i).getHarga();
                    int total = dataPesan.get(i).getTotal();
                    String status = dataPesan.get(i).getStatus();
                    Object[] data = {nama, alamat, item, qts, harga, total, status};
                    ((DefaultTableModel)tbTrans.getModel()).addRow(data);
                }
            }
        }
    }
    
   void readDB(){
        try{
            pemasukan = 0;
            dataAlat.clear();
            dataBahan.clear();
            dataKar.clear();
            dataPesan.clear();
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbashwani", "root", "");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from tbakun join tbgawai on tbakun.email = tbgawai.email where level='KARYAWAN'");
            if(!rs.next()){
                System.out.println("No Data Yet");
            }else{
                do{
                    int gaji = rs.getInt("gaji");
                    String shift = rs.getString("shift");
                    int id = rs.getInt("id");
                    String nama = rs.getString("nama");
                    String mail = rs.getString("email");
                    String alamat = rs.getString("alamat");
                    String pass = rs.getString("password");
                    Karyawan newDataKar = new Karyawan(gaji, shift, nama, alamat, pass, mail, id);
                    dataKar.add(newDataKar);
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
            rs = stmt.executeQuery("select idPesan, tbakun.nama as 'nama', tbbarang.nama as 'item', qty, tbbarang.harga as 'harga', tbakun.alamat as 'alamat', status from tbpesan join tbakun join tbbarang on tbpesan.idPel = tbakun.id and tbpesan.idBar = tbbarang.id");
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
            rs = stmt.executeQuery("select * from tbriwayat");
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
            for(int i=0;i<dataPesan.size();i++){
                pemasukan = pemasukan + dataPesan.get(i).getPemasukan();
            }
            lbMasuk.setText(Integer.toString(pemasukan));
            rs.close();
            stmt.close();
            conn.close();
            System.out.println(dataKar.size());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

   void showButton(){
       if(tbDataKar.getSelectedRow() >= 0){
           btnHapusDataKar.setVisible(true);
           btnEditDataKar.setVisible(true);
       }else{
           btnHapusDataKar.setVisible(false);
           btnEditDataKar.setVisible(false);
       }
   }
    /**
     * Creates new form OwnerFrame
     */
    public OwnerFrame() {
        initComponents();
        cards = (CardLayout)(CardsPanes.getLayout());
        readDB();
        choice1.add("Siang");
        choice1.add("Malam");
        ccDataBar.add("Semua");
        ccDataBar.add("Alat");
        ccDataBar.add("Bahan");
        ccDataTrans.add("Semua");
        ccDataTrans.add("Sedang Diproses");
        ccDataTrans.add("Selesai");
        ccDataTrans.add("Lainnya");
        dbToTb();
        showButton();
        
        
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
        btnExit = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btnKaryawan = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btnBarang = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        btnTransaksi = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        CardsPanes = new javax.swing.JPanel();
        KaryawanPane = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbDataKar = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        btnTambahDataKar = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        btnEditDataKar = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        btnHapusDataKar = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        BarangPane = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbDataBar = new javax.swing.JTable();
        ccDataBar = new java.awt.Choice();
        TransaksiPane = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbTrans = new javax.swing.JTable();
        lbMasuk = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        ccDataTrans = new java.awt.Choice();
        KaryawanForm = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        txtMailKar = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        txtNamaKar = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        txtAlamatKar = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        txtGajiKar = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        choice1 = new java.awt.Choice();
        btnSaveKar = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        btnCancelKar = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(51, 0, 102));
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(51, 0, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        jPanel1.add(btnExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 220, 40));

        btnKaryawan.setBackground(new java.awt.Color(102, 0, 153));
        btnKaryawan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnKaryawan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnKaryawanMouseClicked(evt);
            }
        });

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/gawai.png"))); // NOI18N

        jLabel10.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Karyawan");

        javax.swing.GroupLayout btnKaryawanLayout = new javax.swing.GroupLayout(btnKaryawan);
        btnKaryawan.setLayout(btnKaryawanLayout);
        btnKaryawanLayout.setHorizontalGroup(
            btnKaryawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnKaryawanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addContainerGap())
        );
        btnKaryawanLayout.setVerticalGroup(
            btnKaryawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnKaryawanLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(btnKaryawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel1.add(btnKaryawan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 220, 40));

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

        jPanel1.add(btnBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 220, 40));

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

        jPanel1.add(btnTransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 220, 40));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/banner.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 180, 80));

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        CardsPanes.setLayout(new java.awt.CardLayout());

        KaryawanPane.setBackground(new java.awt.Color(204, 204, 204));
        KaryawanPane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbDataKar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Nama", "E-mail", "Alamat", "Gaji", "Shift"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbDataKar.getTableHeader().setReorderingAllowed(false);
        tbDataKar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDataKarMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbDataKar);
        if (tbDataKar.getColumnModel().getColumnCount() > 0) {
            tbDataKar.getColumnModel().getColumn(0).setPreferredWidth(10);
        }

        KaryawanPane.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 570, 310));

        jPanel8.setBackground(new java.awt.Color(102, 102, 255));

        jLabel15.setFont(new java.awt.Font("Candara Light", 0, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Manajemen Data Karyawan");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(89, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(47, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(28, 28, 28))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))))
        );

        KaryawanPane.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 590, -1));

        btnTambahDataKar.setBackground(new java.awt.Color(102, 102, 255));
        btnTambahDataKar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        btnTambahDataKar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTambahDataKarMouseClicked(evt);
            }
        });

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Tambah");

        javax.swing.GroupLayout btnTambahDataKarLayout = new javax.swing.GroupLayout(btnTambahDataKar);
        btnTambahDataKar.setLayout(btnTambahDataKarLayout);
        btnTambahDataKarLayout.setHorizontalGroup(
            btnTambahDataKarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnTambahDataKarLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel4)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        btnTambahDataKarLayout.setVerticalGroup(
            btnTambahDataKarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnTambahDataKarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                .addContainerGap())
        );

        KaryawanPane.add(btnTambahDataKar, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 140, -1, 40));

        btnEditDataKar.setBackground(new java.awt.Color(102, 102, 255));
        btnEditDataKar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        btnEditDataKar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEditDataKarMouseClicked(evt);
            }
        });

        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Edit");

        javax.swing.GroupLayout btnEditDataKarLayout = new javax.swing.GroupLayout(btnEditDataKar);
        btnEditDataKar.setLayout(btnEditDataKarLayout);
        btnEditDataKarLayout.setHorizontalGroup(
            btnEditDataKarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnEditDataKarLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel23)
                .addContainerGap(46, Short.MAX_VALUE))
        );
        btnEditDataKarLayout.setVerticalGroup(
            btnEditDataKarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnEditDataKarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                .addContainerGap())
        );

        KaryawanPane.add(btnEditDataKar, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 140, 110, 40));

        btnHapusDataKar.setBackground(new java.awt.Color(102, 102, 255));
        btnHapusDataKar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        btnHapusDataKar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHapusDataKarMouseClicked(evt);
            }
        });

        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Hapus");

        javax.swing.GroupLayout btnHapusDataKarLayout = new javax.swing.GroupLayout(btnHapusDataKar);
        btnHapusDataKar.setLayout(btnHapusDataKarLayout);
        btnHapusDataKarLayout.setHorizontalGroup(
            btnHapusDataKarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnHapusDataKarLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel24)
                .addContainerGap(32, Short.MAX_VALUE))
        );
        btnHapusDataKarLayout.setVerticalGroup(
            btnHapusDataKarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnHapusDataKarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                .addContainerGap())
        );

        KaryawanPane.add(btnHapusDataKar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 140, 110, -1));

        CardsPanes.add(KaryawanPane, "card2");

        BarangPane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(102, 102, 255));

        jLabel6.setFont(new java.awt.Font("Candara Light", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Lihat Data Barang");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(162, Short.MAX_VALUE))
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
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Nama", "Tipe", "Jenis", "Stok", "Harga"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbDataBar.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tbDataBar);

        BarangPane.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 540, 310));

        ccDataBar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ccDataBarItemStateChanged(evt);
            }
        });
        BarangPane.add(ccDataBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 170, -1));

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
                "Nama", "Alamat", "Item", "Qty", "Harga", "Total", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbTrans.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tbTrans);
        if (tbTrans.getColumnModel().getColumnCount() > 0) {
            tbTrans.getColumnModel().getColumn(3).setPreferredWidth(20);
        }

        TransaksiPane.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 530, 270));

        lbMasuk.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbMasuk.setText("0");
        TransaksiPane.add(lbMasuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 450, -1, -1));

        jLabel25.setText("Pemasukan : Rp. ");
        TransaksiPane.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 450, -1, -1));

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        TransaksiPane.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 480, 90, -1));

        ccDataTrans.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ccDataTransItemStateChanged(evt);
            }
        });
        TransaksiPane.add(ccDataTrans, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 160, -1));

        CardsPanes.add(TransaksiPane, "card2");

        KaryawanForm.setBackground(new java.awt.Color(255, 255, 255));
        KaryawanForm.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel10.setBackground(new java.awt.Color(102, 102, 255));

        jLabel17.setFont(new java.awt.Font("Candara Light", 0, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Form Input Karyawan");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(133, Short.MAX_VALUE))
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
        KaryawanForm.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 190, 270, -1));

        txtMailKar.setBorder(null);
        KaryawanForm.add(txtMailKar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 160, 270, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setText("E-mail");
        KaryawanForm.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 130, -1, -1));

        jSeparator6.setForeground(new java.awt.Color(0, 0, 0));
        KaryawanForm.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 270, -1));

        txtNamaKar.setBorder(null);
        KaryawanForm.add(txtNamaKar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 270, -1));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel16.setText("Nama");
        KaryawanForm.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, -1));

        jSeparator8.setForeground(new java.awt.Color(0, 0, 0));
        KaryawanForm.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 270, -1));

        txtAlamatKar.setBorder(null);
        KaryawanForm.add(txtAlamatKar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 270, -1));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel18.setText("Alamat");
        KaryawanForm.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, -1));

        jSeparator9.setForeground(new java.awt.Color(0, 0, 0));
        KaryawanForm.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 260, 270, -1));

        txtGajiKar.setBorder(null);
        KaryawanForm.add(txtGajiKar, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 230, 240, -1));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel19.setText("Rp.");
        KaryawanForm.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 230, -1, -1));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel20.setText("Shift");
        KaryawanForm.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, -1, -1));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel21.setText("Gaji");
        KaryawanForm.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 200, -1, -1));

        choice1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        KaryawanForm.add(choice1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 270, 30));

        btnSaveKar.setBackground(new java.awt.Color(102, 102, 255));
        btnSaveKar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        btnSaveKar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSaveKar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSaveKarMouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Simpan");

        javax.swing.GroupLayout btnSaveKarLayout = new javax.swing.GroupLayout(btnSaveKar);
        btnSaveKar.setLayout(btnSaveKarLayout);
        btnSaveKarLayout.setHorizontalGroup(
            btnSaveKarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnSaveKarLayout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addComponent(jLabel5)
                .addContainerGap(86, Short.MAX_VALUE))
        );
        btnSaveKarLayout.setVerticalGroup(
            btnSaveKarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnSaveKarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                .addContainerGap())
        );

        KaryawanForm.add(btnSaveKar, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 420, 220, 50));

        btnCancelKar.setBackground(new java.awt.Color(255, 255, 255));
        btnCancelKar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 255)));
        btnCancelKar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancelKar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCancelKarMouseClicked(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(102, 102, 255));
        jLabel22.setText("Batal");

        javax.swing.GroupLayout btnCancelKarLayout = new javax.swing.GroupLayout(btnCancelKar);
        btnCancelKar.setLayout(btnCancelKarLayout);
        btnCancelKarLayout.setHorizontalGroup(
            btnCancelKarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnCancelKarLayout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addComponent(jLabel22)
                .addContainerGap(86, Short.MAX_VALUE))
        );
        btnCancelKarLayout.setVerticalGroup(
            btnCancelKarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnCancelKarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                .addContainerGap())
        );

        KaryawanForm.add(btnCancelKar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 420, -1, -1));

        CardsPanes.add(KaryawanForm, "card2");

        jPanel2.add(CardsPanes, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 590, 500));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 590, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 212, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    
    
    private void btnExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseClicked
        this.setVisible(false);
        JOptionPane.showMessageDialog(null , "Logging Out");
        Main mn = new Main();
        mn.setVisible(true);
    }//GEN-LAST:event_btnExitMouseClicked

    private void btnKaryawanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKaryawanMouseClicked
        cards.first(CardsPanes);
        posisi = 1;
    }//GEN-LAST:event_btnKaryawanMouseClicked

    private void btnBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBarangMouseClicked
        if(posisi==1){
            cards.next(CardsPanes);
        }else if(posisi == 2){
            
        }else if(posisi == 3){
            cards.previous(CardsPanes);
        }else if(posisi == 4){
            cards.previous(CardsPanes);
            cards.previous(CardsPanes);
        }
        posisi = 2;
    }//GEN-LAST:event_btnBarangMouseClicked

    private void btnTransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTransaksiMouseClicked
        if(posisi==1){
            cards.next(CardsPanes);
            cards.next(CardsPanes);
        }else if(posisi == 2){
            cards.next(CardsPanes);
        }else if(posisi == 3){
            
        }else if(posisi == 4){
            cards.previous(CardsPanes);
        }
        posisi = 3;
    }//GEN-LAST:event_btnTransaksiMouseClicked

    private void btnTambahDataKarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTambahDataKarMouseClicked
        ubah = false;
        cards.last(CardsPanes);
        posisi = 4;
        bersihForm();
    }//GEN-LAST:event_btnTambahDataKarMouseClicked

    private void btnSaveKarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveKarMouseClicked
        cekForm();
        if(kondisi){
            try{
                String nama = txtNamaKar.getText();
                String email = txtMailKar.getText();
                String alamat = txtAlamatKar.getText();
                String shift = choice1.getSelectedItem();
                int gaji = Integer.parseInt(txtGajiKar.getText());
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbashwani", "root", "");
                Statement stmt = conn.createStatement();
                if(ubah){
                    stmt.executeUpdate("update tbakun set nama='"+nama+"', email='"+email+"', alamat='"+alamat+"' where id='"+idEdit+"'");
                    stmt.executeUpdate("update tbgawai set gaji='"+gaji+"', shift='"+shift+"', email='"+email+"' where email='"+mailEdit+"'");
                    JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
                }
                else{
                    stmt.executeUpdate("insert into tbakun(id, nama, email, alamat, password, level) values(0, '"+nama+"', '"+email+"', '"+alamat+"', '123', 'KARYAWAN')");
                    stmt.executeUpdate("insert into tbgawai (gaji, shift, email) values('"+gaji+"', '"+shift+"', '"+email+"')");
                    JOptionPane.showMessageDialog(null, "Data Berhasil Ditambah");
                }
                bersihForm();
                conn.close();
                stmt.close();
                cards.first(CardsPanes);
                posisi = 1;
            }catch(Exception e){
                e.printStackTrace();
            }
            readDB();
            dbToTb();
        }else{
            JOptionPane.showMessageDialog(null, "Periksa Kembali Data Yang Diinput");
        }
        showButton();
    }//GEN-LAST:event_btnSaveKarMouseClicked

    private void btnCancelKarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelKarMouseClicked
        bersihForm();
        cards.first(CardsPanes);
        posisi = 1;
    }//GEN-LAST:event_btnCancelKarMouseClicked

    private void btnEditDataKarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditDataKarMouseClicked
        ubah = true;
        int row = tbDataKar.getSelectedRow();
        idEdit = tbDataKar.getModel().getValueAt(row, 0).toString();
        for (Karyawan edit : dataKar){
            if(edit.getId() == Integer.parseInt(idEdit)){
                mailEdit = edit.getEmail();
                txtNamaKar.setText(edit.getNama());
                txtMailKar.setText(edit.getEmail());
                txtAlamatKar.setText(edit.getAlamat());
                txtGajiKar.setText(Integer.toString(edit.getGaji()));
                if(edit.getShift().equals("Siang")){
                    choice1.select(0);                    
                }else{
                    choice1.select(1);
                }
            }
        }
        cards.last(CardsPanes);
        posisi = 4;
    }//GEN-LAST:event_btnEditDataKarMouseClicked

    private void ccDataBarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ccDataBarItemStateChanged
        readDB();
        dbToTb();
        showButton();
    }//GEN-LAST:event_ccDataBarItemStateChanged

    private void ccDataTransItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ccDataTransItemStateChanged
        readDB();
        dbToTb();
        showButton();
    }//GEN-LAST:event_ccDataTransItemStateChanged

    private void btnHapusDataKarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHapusDataKarMouseClicked
        int row = tbDataKar.getSelectedRow();
        idEdit = tbDataKar.getModel().getValueAt(row, 0).toString();
        for(Karyawan hapus : dataKar){
            if(Integer.toString(hapus.getId()).equals(idEdit)){
                mailEdit = hapus.getEmail();
            }
        }
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbashwani", "root", "");
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("delete from tbakun where id='"+idEdit+"'");
            stmt.executeUpdate("delete from tbgawai where email='"+mailEdit+"'");
            JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
        }catch(Exception e){
            e.printStackTrace();
        }
        readDB();
        dbToTb();
        showButton();
    }//GEN-LAST:event_btnHapusDataKarMouseClicked

    private void tbDataKarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDataKarMouseClicked
        showButton();
    }//GEN-LAST:event_tbDataKarMouseClicked

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
            java.util.logging.Logger.getLogger(OwnerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OwnerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OwnerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OwnerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OwnerFrame().setVisible(true);
            }
        });
        
        
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BarangPane;
    private javax.swing.JPanel CardsPanes;
    private javax.swing.JPanel KaryawanForm;
    private javax.swing.JPanel KaryawanPane;
    private javax.swing.JPanel TransaksiPane;
    private javax.swing.JPanel btnBarang;
    private javax.swing.JPanel btnCancelKar;
    private javax.swing.JPanel btnEditDataKar;
    private javax.swing.JPanel btnExit;
    private javax.swing.JPanel btnHapusDataKar;
    private javax.swing.JPanel btnKaryawan;
    private javax.swing.JPanel btnSaveKar;
    private javax.swing.JPanel btnTambahDataKar;
    private javax.swing.JPanel btnTransaksi;
    private java.awt.Choice ccDataBar;
    private java.awt.Choice ccDataTrans;
    private java.awt.Choice choice1;
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
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JLabel lbMasuk;
    private javax.swing.JTable tbDataBar;
    private javax.swing.JTable tbDataKar;
    private javax.swing.JTable tbTrans;
    private javax.swing.JTextField txtAlamatKar;
    private javax.swing.JTextField txtGajiKar;
    private javax.swing.JTextField txtMailKar;
    private javax.swing.JTextField txtNamaKar;
    // End of variables declaration//GEN-END:variables
}
