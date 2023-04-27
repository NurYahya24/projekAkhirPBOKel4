
package projekakhirkel4;

public class Pesanan implements Hitung{
    String namaPel, namaBar, alamat, status;
    int qty, id, harga;

    public Pesanan(String namaPel, String namaBar, String alamat, String status, int qty, int id, int harga) {
        this.namaPel = namaPel;
        this.namaBar = namaBar;
        this.alamat = alamat;
        this.status = status;
        this.qty = qty;
        this.id = id;
        this.harga = harga;
    }
    
    //overload
    public Pesanan(String namaPel, String namaBar, String alamat, String status, int qty, int harga) {
        this.namaPel = namaPel;
        this.namaBar = namaBar;
        this.alamat = alamat;
        this.status = status;
        this.qty = qty;
        this.harga = harga;
    }

    public String getNamaPel() {
        return namaPel;
    }

    public void setNamaPel(String namaPel) {
        this.namaPel = namaPel;
    }

    public String getNamaBar() {
        return namaBar;
    }

    public void setNamaBar(String namaBar) {
        this.namaBar = namaBar;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }
    
    @Override
    public int getTotal(){
        return getHarga() * getQty();
    }
    
    @Override
    public int getPemasukan(){
        if(this.getStatus().equals("Selesai")){
            return getTotal();
        }
        return 0;
    }
        
    
    
}
