package busak.objektuak;

public class Udalerri {
    private int kode;
    private String izena;
    
    public Udalerri(int kode, String izena) {
        this.kode = kode;
        this.izena = izena;
    }

    public Udalerri() {
        this.kode = -1;
        this.izena = null;
    }

    public int getKode() {
        return kode;
    }

    public void setKode(int kode) {
        this.kode = kode;
    }

    public String getIzena() {
        return izena;
    }

    public void setIzena(String izena) {
        this.izena = izena;
    }

    @Override
    public String toString() {
        return izena + " (" + kode + " kodea)";
    }

}
