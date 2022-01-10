package busak.objektuak;

public class Erabiltzaile {
    private String nanAiz, izenAbizenak, pasahitza;
    private ArrayList<Bilete> bileteak;
    private Scanner teklatua = new Scanner(System.in);

    public Erabiltzaile(String EnanAiz, String EizenAbizenak, String Epasahitza) {
        this.nanAiz = EnanAiz;
        this.izenAbizenak = EizenAbizenak;
        this.pasahitza = Epasahitza;
        bileteak = new ArrayList<Bilete>();
    }

    public void erosiBilete(int kode, float ordaintzekoa, Timestamp hasData, Timestamp bukData, Geltoki hasGeltoki,
            Geltoki bukGeltoki) {
        Bilete bileteBerria = new Bilete(kode, ordaintzekoa, hasData, bukData, hasGeltoki, bukGeltoki);
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

    public void setNanAiz(nan) {
        this.nanAiz = nan;
    }

    public void setIzenAbizenak(izenAbiz) {
        this.izenAbizenak = izenAbiz;
    }

    public void setPasahitza(pass) {
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
