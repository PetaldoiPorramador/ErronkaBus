package busak.objektuak;

/**
 * Kale objektua
 */
public class Kale {

    /**
     * Kalearen izena
     */
    private String izena;
    /**
     * Kalearen posta kodea
     */
    private int pk;
    /**
     * Kalearen udalerria
     */
    private Udalerri udalerria;

    /**
     * Eraikitzailea datu guztiekin
     * 
     * @param izena     Kalearen izena
     * @param pk        Kalearen posta kodea
     * @param udalerria Kalearen udalerria
     */
    public Kale(String izena, int pk, Udalerri udalerria) {
        this.izena = izena;
        this.pk = pk;
        this.udalerria = udalerria;
    }

    /**
     * Eraikitzaile hutsa
     */
    public Kale() {
        this.izena = null;
        this.pk = -1;
        this.udalerria = null;
    }

    // GETERRAK

    /**
     * Izena atributua bueltatzen du
     * 
     * @return String Kalearen izena
     */
    public String getIzena() {
        return izena;
    }

    /**
     * Pk atributua bueltatzen du
     * 
     * @return int Kalearen posta kodea
     */
    public int getPk() {
        return pk;
    }

    /**
     * Udalerria atributua bueltatzen du
     * 
     * @return Udalerri Kalearen udalerria
     */
    public Udalerri getUdalerria() {
        return udalerria;
    }

    // SETERRAK

    /**
     * Izena atributua ezartzen du
     * 
     * @param izena Kalearen izena
     */
    public void setIzena(String izena) {
        this.izena = izena;
    }

    /**
     * Pk atributua ezartzen du
     * 
     * @param pk Kalearen posta kodea
     */
    public void setPk(int pk) {
        this.pk = pk;
    }

    /**
     * Udalerria atributua ezartzen du
     * 
     * @param udalerria Kalearen udalerria
     */
    public void setUdalerria(Udalerri udalerria) {
        this.udalerria = udalerria;
    }

    @Override
    public String toString() {
        return izena + "Posta Kodea:" + pk + ", udalerria: " + udalerria;
    }
}
