package busak.objektuak;

public class Geltoki {

    private int lineaKode;
    private int orden;
    private String izena;
    private int zenbakia;
    private int denboraBzBs;
    private Kale kalea;

    public Geltoki(int lineaKode, int orden, String izena, int zenbakia, int denboraBzBs, Kale kalea) {
        this.lineaKode = lineaKode;
        this.orden = orden;
        this.izena = izena;
        this.zenbakia = zenbakia;
        this.denboraBzBs = denboraBzBs;
        this.kalea = kalea;
    }

    public Geltoki() {
        this.orden = -1;
        this.izena = null;
        this.zenbakia = -1;
        this.denboraBzBs = -1;
        this.kalea = null;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public String getIzena() {
        return izena;
    }

    public void setIzena(String izena) {
        this.izena = izena;
    }

    public int getZenbakia() {
        return zenbakia;
    }

    public void setZenbakia(int zenbakia) {
        this.zenbakia = zenbakia;
    }

    public int getDenboraBzBs() {
        return denboraBzBs;
    }

    public void setDenboraBzBs(int denboraBzBs) {
        this.denboraBzBs = denboraBzBs;
    }

    public Kale getKalea() {
        return kalea;
    }

    public void setKalea(Kale kalea) {
        this.kalea = kalea;
    }

    public int getLineaKode() {
        return lineaKode;
    }

    public void setLineaKode(int lineaKode) {
        this.lineaKode = lineaKode;
    }

    @Override
    public String toString() {
        if(zenbakia>0){
            return izena + " kalea: " + kalea + " zenbakia: " + zenbakia + " lineako " + orden + ". geltokia";
        } else {
            return izena + " kalea: " + kalea + " lineako " + orden + ". geltokia";
        }
    }

}
