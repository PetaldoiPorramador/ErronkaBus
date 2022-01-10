package busak.objektuak;

import java.time.LocalTime;


public class Bilete {
    private int kode;
    private float ordaintzekoa;
    private String nan;
    private LocalTime hasData;
    private LocalTime bukData;
    private Geltoki hasGeltoki;
    private Geltoki bukGeltoki;

    public Bilete(int kode, float ordaintzekoa,String nan, LocalTime hasData, LocalTime bukData, Geltoki hasGeltoki, Geltoki bukGeltoki) {
        this.kode = kode;
        this.ordaintzekoa = ordaintzekoa;
        this.nan = nan;
        this.hasData = hasData;
        this.bukData = bukData;
        this.hasGeltoki = hasGeltoki;
        this.bukGeltoki = bukGeltoki;
    }

    public Bilete() {
        this.kode = -1;
        this.ordaintzekoa = -1;
        this.nan = null;
        this.hasData = null;
        this.bukData = null;
        this.hasGeltoki = null;
        this.bukGeltoki = null;
    }

    public int getKode() {
        return kode;
    }

    public String getNan() {
        return nan;
    }

    public void setNan(String nan) {
        this.nan = nan;
    }

    public void setKode(int kode) {
        this.kode = kode;
    }

    public float getOrdaintzekoa() {
        return ordaintzekoa;
    }

    public void setOrdaintzekoa(float ordaintzekoa) {
        this.ordaintzekoa = ordaintzekoa;
    }

    public LocalTime getHasData() {
        return hasData;
    }

    public void setHasData(LocalTime hasData) {
        this.hasData = hasData;
    }

    public LocalTime getBukData() {
        return bukData;
    }

    public void setBukData(LocalTime bukData) {
        this.bukData = bukData;
    }

    public Geltoki getHasGeltoki() {
        return hasGeltoki;
    }

    public void setHasGeltoki(Geltoki hasGeltoki) {
        this.hasGeltoki = hasGeltoki;
    }

    public Geltoki getBukGeltoki() {
        return bukGeltoki;
    }

    public void setBukGeltoki(Geltoki bukGeltoki) {
        this.bukGeltoki = bukGeltoki;
    }

    @Override
    public String toString() {
        return "Kodea: " + kode + " ordaintzekoa: " + ordaintzekoa + " Eroslearen NAN/AIZ-a: " + nan + " Bidaiaren hasierako eguna eta ordua: "
                + hasData + " estimatutako bidaiaren amaierako eguna eta ordua" + bukData + " " + hasGeltoki + "-tik " + bukGeltoki + "-ra";
    }

    public boolean berdinaDa(int kode, String nan) {
       if(this.kode == kode && this.nan.equalsIgnoreCase(nan)){
           return true;
       } else {
           return false;
       }
    }

}
