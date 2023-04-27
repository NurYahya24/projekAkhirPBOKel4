/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projekakhirkel4;

public class Karyawan extends Akun{
    private int gaji;
    private String shift;

    public Karyawan(int gaji, String shift, String nama, String alamat, String password, String email, int id) {
        super(nama, alamat, password, email, id);
        this.gaji = gaji;
        this.shift = shift;
    }

    public int getGaji() {
        return gaji;
    }

    public void setGaji(int gaji) {
        this.gaji = gaji;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }
    
    
}
