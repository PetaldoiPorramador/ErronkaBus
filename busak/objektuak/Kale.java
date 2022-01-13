package busak.objektuak;

public class Kale {
    private String izena;
    private int pk;
    private Udalerri udalerria;

    public Kale(String izena, int pk, Udalerri udalerria) {
        this.izena = izena;
        this.pk = pk;
        this.udalerria = udalerria;
    }

    public Kale() {
        this.izena = null;
        this.pk = -1;
        this.udalerria = null;
    }

    public String getIzena() {
        return izena;
    }

    public void setIzena(String izena) {
        this.izena = izena;
    }

    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }

    public Udalerri getUdalerria() {
        return udalerria;
    }

    public void setUdalerria(Udalerri udalerria) {
        this.udalerria = udalerria;
    }

    @Override
    public String toString() {
        return izena + "Posta Kodea:" + pk + ", udalerria: " + udalerria;
    }
}
