package samplellist;

import java.util.HashSet;
import java.util.logging.Logger;

public class LinkedList {
    private static final Logger logger = Logger.getLogger(LinkedList.class.getName());

    private Simpul pertama = null;
    private HashSet<String> nimSet = new HashSet<>();

    public void isiData(String nim, String nama, int nilai) {
        if (nimSet.contains(nim)) {
            logger.warning("NIM sudah ada dalam linked list");
            return;
        }

        Simpul ptrBaru = new Simpul(nim, nama, nilai);
        nimSet.add(nim);

        if (pertama == null) {
            pertama = ptrBaru;
        } else {
            Simpul penunjuk = pertama;
            while (penunjuk.berikutnya != null) {
                penunjuk = penunjuk.berikutnya;
            }
            penunjuk.berikutnya = ptrBaru;
        }
    }

    public void tampilKandunganData() {
        logger.info("Isi Linked List");
        Simpul penunjuk = pertama;
        while (penunjuk != null) {
            penunjuk.infoData();
            penunjuk = penunjuk.berikutnya;
        }
    }

    public Simpul findDataByNim(String nim) {
        Simpul penunjuk = pertama;
        while (penunjuk != null) {
            if (penunjuk.getNim().equals(nim)) {
                return penunjuk;
            }
            penunjuk = penunjuk.berikutnya;
        }
        return null;
    }

    public boolean hapusDataByNim(String nim) {
        if (!nimSet.contains(nim)) {
            logger.warning("Data yang akan dihapus tidak ditemukan");
            return false;
        }

        nimSet.remove(nim);

        if (pertama != null && pertama.getNim().equals(nim)) {
            pertama = pertama.berikutnya;
            return true;
        }

        Simpul penunjuk = pertama;
        while (penunjuk != null && penunjuk.berikutnya != null) {
            if (penunjuk.berikutnya.getNim().equals(nim)) {
                penunjuk.berikutnya = penunjuk.berikutnya.berikutnya;
                return true;
            }
            penunjuk = penunjuk.berikutnya;
        }
        return false;
    }

    public Simpul getPertama() {
        return pertama;
    }
}