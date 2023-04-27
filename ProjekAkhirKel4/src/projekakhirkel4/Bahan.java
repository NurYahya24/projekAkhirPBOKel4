
package projekakhirkel4;

public class Bahan extends Barang{
    String jenis, deskripsi;

    public Bahan(String jenis, String deskripsi, String nama, int stok, int harga, int id) {
        super(nama, stok, harga, id);
        this.jenis = jenis;
        this.deskripsi = deskripsi;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
    
    @Override
    Object displayBarang(){
        Object[] data = {super.nama, "Bahan", this.jenis, super.stok, "Rp. "+this.harga};
        return data;
    }
    
}
