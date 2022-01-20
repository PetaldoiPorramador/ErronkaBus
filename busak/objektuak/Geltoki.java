package busak.objektuak;

/**
 * Geltoki objektua
 */
public class Geltoki {

    /**
     * Geltokiaren linearen kodea
     */
    private int lineaKode;
    /**
     * Geltokiaren ordena linean
     */
    private int orden;
    /**
     * Geltokiaren izena
     */
    private String izena;
    /**
     * Geltokiaren kalea
     */
    private Kale kalea;
    /**
     * Geltokiaren kalearen zenbakia
     */
    private int zenbakia;
    /**
     * Hurrengo geltokira joateko denbora minututan
     */
    private int denboraBzBs;

    /**
     * Eraikitzailea datu guztiekin
     * 
     * @param lineaKode   Geltokiaren linearen kodea
     * @param orden       Geltokiaren ordena linean
     * @param izena       Geltokiaren izena
     * @param zenbakia    Geltokiaren kalearen zenbakia
     * @param denboraBzBs Hurrengo geltokira joateko denbora minututan
     * @param kalea       Geltokiaren kalea
     */
    public Geltoki(int lineaKode, int orden, String izena, int zenbakia, int denboraBzBs, Kale kalea) {
        this.lineaKode = lineaKode;
        this.orden = orden;
        this.izena = izena;
        this.zenbakia = zenbakia;
        this.denboraBzBs = denboraBzBs;
        this.kalea = kalea;
    }

    /**
     * Eraikitzaile hutsa
     */
    public Geltoki() {
        this.orden = -1;
        this.izena = null;
        this.zenbakia = -1;
        this.denboraBzBs = -1;
        this.kalea = null;
    }
    
    // GETERRAK

    /**
     * LineaKode atributua bueltatzen du
     * 
     * @return int Geltokiaren linearen kodea
     */
    public int getLineaKode() {
        return lineaKode;
    }

    /**
     * Orden atributua bueltatzen du
     * 
     * @return int Geltokiaren ordena linean
     */
    public int getOrden() {
        return orden;
    }
    
    /**
     * Izena atributua bueltatzen du
     * 
     * @return String Geltokiaren izena
     */
    public String getIzena() {
        return izena;
    }

    /**
     * Kalea atributua bueltatzen du
     * 
     * @return Kale Geltokiaren kalea
     */
    public Kale getKalea() {
        return kalea;
    }

    /**
     * Zenbakia atributua bueltatzen du
     * 
     * @return int Geltokiaren kalearen zenbakia
     */
    public int getZenbakia() {
        return zenbakia;
    }

    /**
     * DenboraBzBs atributua bueltatzen du
     * 
     * @return int Hurrengo geltokira joateko denbora minututan
     */
    public int getDenboraBzBs() {
        return denboraBzBs;
    }

    // SETERRAK

    /**
     * LineaKode atributua ezartzen du
     * 
     * @param lineaKode int Geltokiaren linearen kodea
     */
    public void setLineaKode(int lineaKode) {
        this.lineaKode = lineaKode;
    }

    /**
     * Orden atributua ezartzen du
     * 
     * @param orden int Geltokiaren ordena linean
     */
    public void setOrden(int orden) {
        this.orden = orden;
    }

    /**
     * Izena atributua ezartzen du
     * 
     * @param izena String Geltokiaren izena
     */
    public void setIzena(String izena) {
        this.izena = izena;
    }

    /**
     * Kalea atributua ezartzen du
     * 
     * @param kalea Kale Geltokiaren kalea
     */
    public void setKalea(Kale kalea) {
        this.kalea = kalea;
    }

    /**
     * Zenbakia atributua ezartzen du
     * 
     * @param zenbakia int Geltokiaren kalearen zenbakia
     */
    public void setZenbakia(int zenbakia) {
        this.zenbakia = zenbakia;
    }

    /**
     * DenboraBzBs atributua ezartzen du
     * 
     * @param denboraBzBs int Hurrengo geltokira joateko denbora minututan
     */
    public void setDenboraBzBs(int denboraBzBs) {
        this.denboraBzBs = denboraBzBs;
    }

    @Override
    public String toString() {
        if (zenbakia > 0) {
            return " lineako " + orden + ". geltokia " + izena + " kalea: " + kalea + " zenbakia: " + zenbakia;
        } else {
            return " lineako " + orden + ". geltokia " + izena + " kalea: " + kalea;
        }
    }

}
