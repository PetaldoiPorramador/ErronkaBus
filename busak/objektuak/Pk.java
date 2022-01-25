package busak.objektuak;

/**
 * Kale objektua
 */
public class Pk {

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
     * @param pk        Kalearen posta kodea
     * @param udalerria Kalearen udalerria
     */
    public Pk(int pk, Udalerri udalerria) {
        this.pk = pk;
        this.udalerria = udalerria;
    }

    /**
     * Eraikitzaile hutsa
     */
    public Pk() {
        this.pk = -1;
        this.udalerria = null;
    }

    // GETERRAK

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
        return "Posta Kodea:" + pk + ", udalerria: " + udalerria;
    }
}
