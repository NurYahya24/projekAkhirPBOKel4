package projekakhirkel4;


public abstract class Barang {
    protected String nama;
    protected int stok, harga, id; 


    public Barang(String nama, int stok, int harga, int id) {
        this.nama = nama;
        this.stok = stok;
        this.harga = harga;
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    abstract Object displayBarang();
    
    
}
