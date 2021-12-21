package busak.objektuak;

public class Erabiltzaile {

    private String nan;
    private String izenAbizenak;
    private String pasahitza;

    public Erabiltzaile(String nan, String izenAbizenak, String pasahitza) {
        this.nan = nan;
        this.izenAbizenak = izenAbizenak;
        this.pasahitza = pasahitza;
    }

    public Erabiltzaile() {

        this.nan = null;
        this.izenAbizenak = null;
        this.pasahitza = null;

    }

    public String getNan() {
        return nan;
    }

    public void setNan(String nan) {
        this.nan = nan;
    }

    public String getIzenAbizenak() {
        return izenAbizenak;
    }

    public void setIzenAbizenak(String izenAbizenak) {
        this.izenAbizenak = izenAbizenak;
    }

    public String getPasahitza() {
        return pasahitza;
    }

    public void setPasahitza(String pasahitza) {
        this.pasahitza = pasahitza;
    }

}
