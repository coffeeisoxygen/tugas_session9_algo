package samplellist;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Simpul {
    private static final Logger logger = Logger.getLogger(Simpul.class.getName());

    String nim;
    String nama;
    int nilai;
    Simpul berikutnya;

    public Simpul(String nim, String nama, int nilai) {
        this.nim = nim;
        this.nama = nama;
        this.nilai = nilai;
        this.berikutnya = null;
    }

    public void infoData() {
        if (logger.isLoggable(Level.INFO)) {
            logger.info(String.format("NIM : %s", nim));
            logger.info(String.format("NAMA : %s", nama));
            logger.info(String.format("NILAI : %d", nilai));
            logger.info(String.format("Pointer Next : %s", berikutnya));
        }
        logger.info("---------------------------------");
    }

    @Override
    public String toString() {
        return "NIM: " + nim + ", Nama: " + nama + ", Nilai: " + nilai + ", Pointer Next: "
                + (berikutnya != null ? berikutnya.hashCode() : "null");
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getNilai() {
        return nilai;
    }

    public void setNilai(int nilai) {
        this.nilai = nilai;
    }

    public Simpul getBerikutnya() {
        return berikutnya;
    }

    public void setBerikutnya(Simpul berikutnya) {
        this.berikutnya = berikutnya;
    }
}