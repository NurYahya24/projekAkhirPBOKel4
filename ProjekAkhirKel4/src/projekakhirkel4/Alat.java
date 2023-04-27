
package projekakhirkel4;

/**
 *
 * @author Yha
 */
public class Alat extends Barang{
    String jenis;

    public Alat(String jenis, String nama, int stok, int harga, int id) {
        super(nama, stok, harga, id);
        this.jenis = jenis;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }
    

    @Override
    Object displayBarang(){
        Object[] data = {super.nama, "Alat", this.jenis, super.stok, "Rp. "+this.harga};
        return data;
    }
    
}
