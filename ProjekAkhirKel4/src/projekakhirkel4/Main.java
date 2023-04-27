/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package projekakhirkel4;
import java.sql.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;


public class Main extends javax.swing.JFrame {
    static ArrayList<Pelanggan> dataPel = new ArrayList<>();
    static ArrayList<Owner> dataOwn = new ArrayList<>();
    static ArrayList<Karyawan> dataKar = new ArrayList<>();
    int click;
    boolean kondisi;
    
    
    static void readDB(){
        dataPel.clear();
        dataOwn.clear();
        dataKar.clear();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbashwani", "root", "");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from tbakun where level='USER'");
            if(!rs.next()){
                System.out.println("No Data Yet");
            }else{
                do{
                    int id = rs.getInt("id");
                    String nama = rs.getString("nama");
                    String mail = rs.getString("email");
                    String alamat = rs.getString("alamat");
                    String pass = rs.getString("password");
                    Pelanggan newDataPel = new Pelanggan(nama, alamat, pass, mail, id);
                    dataPel.add(newDataPel);
                }while(rs.next());
            }
            rs.close();
            rs = stmt.executeQuery("Select * from tbakun where level='OWNER'");
            if(!rs.next()){
                System.out.println("No Data Yet");
            }else{
                do{
                    String nama = rs.getString("nama");
                    String alamat = rs.getString("alamat");
                    String mail = rs.getString("email");
                    String pass = rs.getString("password");
                    int id = rs.getInt("id");
                    dataOwn.add(new Owner(nama, alamat, pass, mail, id));
                }while(rs.next());  
            }
            rs.close();
            rs = stmt.executeQuery("Select * from tbakun join tbgawai on tbakun.email = tbgawai.email where level='KARYAWAN'");
            if(!rs.next()){
                System.out.println("No Data Yet");
            }else{
                do{
                    String nama = rs.getString("nama");
                    String alamat = rs.getString("alamat");
                    String mail = rs.getString("email");
                    String pass = rs.getString("password");
                    String shift = rs.getString("shift");
                    int gaji = rs.getInt("gaji");
                    int id = rs.getInt("id");
                    dataKar.add(new Karyawan(gaji, shift, nama, alamat, pass, mail, id));
                }while(rs.next());  
            }
            rs.close();
            stmt.close();
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }

    CardLayout clays;
    public void readAkun(){
        
    }
    
    public Main() {
        initComponents();
        click = 0;
        readDB();
        clays = (CardLayout)(panelMain.getLayout());
        
        
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelMain = new javax.swing.JPanel();
        LogIn = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtMail1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        txtPass1 = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        btnMasuk = new javax.swing.JButton();
        Regis = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        txtNama2 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        txtMail2 = new javax.swing.JTextField();
        jSeparator10 = new javax.swing.JSeparator();
        jLabel20 = new javax.swing.JLabel();
        txtAlamat2 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jSeparator11 = new javax.swing.JSeparator();
        txtPass2 = new javax.swing.JPasswordField();
        jLabel22 = new javax.swing.JLabel();
        jSeparator12 = new javax.swing.JSeparator();
        jButton2 = new javax.swing.JButton();
        btnDaftar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnExitProg = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        panelMain.setLayout(new java.awt.CardLayout());

        LogIn.setBackground(new java.awt.Color(102, 102, 255));
        LogIn.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Form Login");
        LogIn.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(215, 38, -1, -1));

        txtMail1.setBackground(new java.awt.Color(102, 102, 255));
        txtMail1.setForeground(new java.awt.Color(255, 255, 255));
        txtMail1.setBorder(null);
        LogIn.add(txtMail1, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 121, 250, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Masukkan E-mail Dan Password Anda");
        LogIn.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(113, 59, -1, -1));

        jSeparator1.setAlignmentX(1.0F);
        jSeparator1.setAlignmentY(1.0F);
        LogIn.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 143, 250, -1));

        jSeparator2.setAlignmentX(1.0F);
        jSeparator2.setAlignmentY(1.0F);
        LogIn.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 217, 250, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Password");
        LogIn.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 164, -1, -1));

        txtPass1.setBackground(new java.awt.Color(102, 102, 255));
        txtPass1.setForeground(new java.awt.Color(255, 255, 255));
        txtPass1.setBorder(null);
        txtPass1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPass1ActionPerformed(evt);
            }
        });
        LogIn.add(txtPass1, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 195, 250, -1));

        jButton1.setBackground(new java.awt.Color(102, 102, 255));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Registrasi");
        jButton1.setBorder(null);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        LogIn.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 390, 60, 37));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("E-mail");
        LogIn.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 90, -1, -1));

        btnMasuk.setBackground(new java.awt.Color(102, 102, 255));
        btnMasuk.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnMasuk.setForeground(new java.awt.Color(255, 255, 255));
        btnMasuk.setText("Masuk");
        btnMasuk.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        btnMasuk.setContentAreaFilled(false);
        btnMasuk.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMasuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMasukActionPerformed(evt);
            }
        });
        LogIn.add(btnMasuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 285, 250, 37));

        panelMain.add(LogIn, "card2");

        Regis.setBackground(new java.awt.Color(102, 102, 255));
        Regis.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Form Registrasi");
        Regis.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(189, 38, -1, -1));

        txtNama2.setBackground(new java.awt.Color(102, 102, 255));
        txtNama2.setForeground(new java.awt.Color(255, 255, 255));
        txtNama2.setBorder(null);
        Regis.add(txtNama2, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 121, 250, -1));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Nama");
        Regis.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 90, -1, -1));

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Lengkapi Data Diri Anda");
        Regis.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(174, 59, -1, -1));

        jSeparator9.setAlignmentX(1.0F);
        jSeparator9.setAlignmentY(1.0F);
        Regis.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 143, 250, -1));

        txtMail2.setBackground(new java.awt.Color(102, 102, 255));
        txtMail2.setForeground(new java.awt.Color(255, 255, 255));
        txtMail2.setBorder(null);
        Regis.add(txtMail2, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 195, 250, -1));

        jSeparator10.setAlignmentX(1.0F);
        jSeparator10.setAlignmentY(1.0F);
        Regis.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 217, 250, -1));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("E-mail");
        Regis.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 164, -1, -1));

        txtAlamat2.setBackground(new java.awt.Color(102, 102, 255));
        txtAlamat2.setForeground(new java.awt.Color(255, 255, 255));
        txtAlamat2.setBorder(null);
        Regis.add(txtAlamat2, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 263, 250, -1));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Alamat");
        Regis.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 232, -1, -1));

        jSeparator11.setAlignmentX(1.0F);
        jSeparator11.setAlignmentY(1.0F);
        Regis.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 285, 250, -1));

        txtPass2.setBackground(new java.awt.Color(102, 102, 255));
        txtPass2.setForeground(new java.awt.Color(255, 255, 255));
        txtPass2.setBorder(null);
        Regis.add(txtPass2, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 337, 250, -1));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Password");
        Regis.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 300, -1, -1));

        jSeparator12.setAlignmentX(1.0F);
        jSeparator12.setAlignmentY(1.0F);
        Regis.add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 359, 250, -1));

        jButton2.setBackground(new java.awt.Color(102, 102, 255));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Log In");
        jButton2.setBorder(null);
        jButton2.setContentAreaFilled(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        Regis.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 470, 40, 37));

        btnDaftar.setBackground(new java.awt.Color(102, 102, 255));
        btnDaftar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDaftar.setForeground(new java.awt.Color(255, 255, 255));
        btnDaftar.setText("Daftar");
        btnDaftar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        btnDaftar.setContentAreaFilled(false);
        btnDaftar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDaftar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDaftarActionPerformed(evt);
            }
        });
        Regis.add(btnDaftar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, 250, 37));

        panelMain.add(Regis, "card3");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo.png"))); // NOI18N
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        btnExitProg.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnExitProg.setForeground(new java.awt.Color(102, 102, 255));
        btnExitProg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exit.png"))); // NOI18N
        btnExitProg.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExitProg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnExitProgMouseClicked(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 255));
        jLabel7.setText("Toko Bahan Bangunan Dan Properti");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 255));
        jLabel8.setText("Serta Alat/Perkakas Terlengkap Sejagad Raya");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 255));
        jLabel9.setText("ASHWANI");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(76, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(63, 63, 63))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(75, 75, 75)))
                        .addComponent(jLabel8))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btnExitProg, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(119, 119, 119))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnExitProg, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(82, 82, 82)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addContainerGap(223, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelMain, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelMain, javax.swing.GroupLayout.PREFERRED_SIZE, 581, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    void cekRegis(){
        kondisi = true;
        if(txtNama2.getText().isEmpty()){
            kondisi = false;
        }else if(txtAlamat2.getText().isEmpty()){
            kondisi = false;
        }else if(txtMail2.getText().isEmpty()){
            kondisi = false;
        }else if(txtPass2.getText().isEmpty()){
            kondisi = false;
        }
    }
    void cekLogin(){
        kondisi = true;
        if(txtMail1.getText().isEmpty()){
            kondisi = false;
        }else if(txtPass1.getText().isEmpty()){
            kondisi = false;
        }
    }
    void bersih(){
        txtMail1.setText("");
        txtPass1.setText("");
        txtNama2.setText("");
        txtMail2.setText("");
        txtAlamat2.setText("");
        txtPass2.setText("");
    }
    
    
    private void txtPass1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPass1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPass1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        bersih();
        clays.next(panelMain);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked

    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        bersih();
        clays.next(panelMain);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnDaftarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDaftarActionPerformed
        boolean ada = false;
        cekRegis();
        if(kondisi){
            for(Pelanggan cekAda : dataPel){
                if(cekAda.getEmail().equals(txtMail2.getText())){
                    ada = true;
                    JOptionPane.showMessageDialog(null , "E-mail Sudah Terdaftar");
                }
            }
            if(!ada){
                String nama, alamat, email, password;
                nama = txtNama2.getText();
                alamat = txtAlamat2.getText();
                email = txtMail2.getText();
                password = txtPass2.getText();
                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbashwani", "root", "");
                    Statement stmt = conn.createStatement();
                    stmt.executeUpdate("insert into tbakun(id, nama, email, alamat, password, level) values(0, '"+nama+"', '"+email+"', '"+alamat+"', '"+password+"', 'USER')");
                    stmt.close();
                    conn.close();
                }catch(Exception e){
                    e.printStackTrace();
                }
                JOptionPane.showMessageDialog(null , "Data Berhasil Ditambahkan");
                clays.next(panelMain);
                bersih();
            }
            readDB();
        }else{
            JOptionPane.showMessageDialog(null , "Data Belum Lengkap");
        }
    }//GEN-LAST:event_btnDaftarActionPerformed

    private void btnMasukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMasukActionPerformed
        btnMasuk.setVisible(false);
        cekLogin();
        boolean salah = false;
        click++;
        if(click == 1){
            if(kondisi){
                for(Owner own : dataOwn){
                    if(own.getEmail().equals(txtMail1.getText()) && own.getPassword().equals(txtPass1.getText())){
                        this.dispose();
                        JOptionPane.showMessageDialog(null , "Login Sebagai Owner");
                        OwnerFrame of = new OwnerFrame();
                        of.setVisible(true);
                        salah = true;
                        break;
                    }
                }
                for(Pelanggan pel : dataPel){
                    if(pel.getEmail().equals(txtMail1.getText()) && pel.getPassword().equals(txtPass1.getText())){
                        this.dispose();
                        int logId = pel.getId();
                        PelangganFrame pl = new PelangganFrame(logId);
                        pl.setVisible(true);
                        salah = true;
                        break;
                    }
                }
                for(Karyawan kar : dataKar){
                    if(kar.getEmail().equals(txtMail1.getText()) && kar.getPassword().equals(txtPass1.getText())){
                        this.dispose();
                        JOptionPane.showMessageDialog(null , "Login Sebagai Karyawan");
                        int logId = kar.getId();
                        KaryawanFrame kf = new KaryawanFrame(logId);
                        kf.setVisible(true);
                        salah = true;
                        break;
                    }
                }
                if(!salah){
                    JOptionPane.showMessageDialog(null , "E-mail Atau Password Salah");
                    click = 0;
                }
            }else{
                JOptionPane.showMessageDialog(null , "DATA BELUM LENGKAP");
                click = 0;
            }
        }else{
            click =0;
        }
        btnMasuk.setVisible(true);
    }//GEN-LAST:event_btnMasukActionPerformed

    private void btnExitProgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitProgMouseClicked
        JOptionPane.showMessageDialog(null , "TERIMA KASIH");
        System.exit(0);
    }//GEN-LAST:event_btnExitProgMouseClicked

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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
        readDB();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel LogIn;
    private javax.swing.JPanel Regis;
    private javax.swing.JButton btnDaftar;
    private javax.swing.JLabel btnExitProg;
    private javax.swing.JButton btnMasuk;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JPanel panelMain;
    private javax.swing.JTextField txtAlamat2;
    private javax.swing.JTextField txtMail1;
    private javax.swing.JTextField txtMail2;
    private javax.swing.JTextField txtNama2;
    private javax.swing.JPasswordField txtPass1;
    private javax.swing.JPasswordField txtPass2;
    // End of variables declaration//GEN-END:variables
}
