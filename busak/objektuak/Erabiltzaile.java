package busak.objektuak;

import java.util.ArrayList;

public class Erabiltzaile {
    private String nanAiz, izenAbizenak, pasahitza;
    private ArrayList<Bilete> bileteak;

    public Erabiltzaile() {
        nanAiz = null;
        izenAbizenak = null;
        pasahitza = null;
        bileteak = new ArrayList<Bilete>();
    }

    public Erabiltzaile(String EnanAiz, String EizenAbizenak, String Epasahitza) {
        this.nanAiz = EnanAiz;
        this.izenAbizenak = EizenAbizenak;
        this.pasahitza = Epasahitza;
        bileteak = new ArrayList<Bilete>();
    }

    public void erosiBilete(Bilete bileteBerria) {
        bileteak.add(bileteBerria);
    }

    public void kantzelatuBilete(int num) {
        // lehenengo eta behin, erabiltzailearen bileteak erakutsi, gero nahi duena
        // ezabatu
        ikusiBileteak();
        bileteak.remove(num);
    }

    public void ikusiBileteak() {
        // erakutsi erabiltzailearen bilete guztiak
        for (int i = 0; i < bileteak.size(); i++) {
            // TODO: con todos los getter
            System.out.println(i + ": " + bileteak.get(i));
        }
    }

    public void setNanAiz(String nan) {
        this.nanAiz = nan;
    }

    public void setIzenAbizenak(String izenAbiz) {
        this.izenAbizenak = izenAbiz;
    }

    public void setPasahitza(String pass) {
        this.pasahitza = pass;
    }

    public String getNanAiz() {
        return nanAiz;
    }

    public String getIzenAbizenak() {
        return izenAbizenak;
    }

    public String getPasahitza() {
        return pasahitza;
    }
}
