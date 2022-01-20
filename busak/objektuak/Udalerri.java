package busak.objektuak;

/**
 * Udalerri objektua
 */
public class Udalerri {

    /**
     * Udalerriaren kodea
     */
    private int kode;
    /**
     * Udalerriaren izena
     */
    private String izena;
    
    /**
     * Eraikitzailea datu guztiekin
     * 
     * @param kode Udalerriaren kodea
     * @param izena Udalerriaren izena
     */
    public Udalerri(int kode, String izena) {
        this.kode = kode;
        this.izena = izena;
    }

    /**
     * Eraikitzaile hutsa
     */
    public Udalerri() {
        this.kode = -1;
        this.izena = null;
    }

    // GETERRAK

    /**
     * Kodea atributua bueltatzen du
     * 
     * @return int Udalerriaren kodea
     */
    public int getKode() {
        return kode;
    }

    /**
     * Izena atributua bueltatzen du
     * 
     * @return String Udalerriaren izena
     */
    public String getIzena() {
        return izena;
    }

    // SETTERRAK

    /**
     * Kodea atributura ezartzen du
     * 
     * @param kode Udalerriaren kodea
     */
    public void setKode(int kode) {
        this.kode = kode;
    }

    /**
     * Izena atributura ezartzen du
     * 
     * @param izena Udalerriaren izena
     */
    public void setIzena(String izena) {
        this.izena = izena;
    }

    @Override
    public String toString() {
        return izena + " (" + kode + " kodea)";
    }

}
