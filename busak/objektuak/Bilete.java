package busak.objektuak;

import java.sql.Timestamp;

public class Bilete {
    private int kode;
    private float ordaintzekoa;
    private Timestamp hasData;
    private Timestamp bukData;
    private Geltoki hasGeltoki;
    private Geltoki bukGeltoki;

    public Bilete(int kode, float ordaintzekoa, Timestamp hasData, Timestamp bukData, Geltoki hasGeltoki, Geltoki bukGeltoki) {
        this.kode = kode;
        this.ordaintzekoa = ordaintzekoa;
        this.hasData = hasData;
        this.bukData = bukData;
        this.hasGeltoki = hasGeltoki;
        this.bukGeltoki = bukGeltoki;
    }

    public Bilete() {
        this.kode = -1;
        this.ordaintzekoa = -1;
        this.hasData = null;
        this.bukData = null;
        this.hasGeltoki = null;
        this.bukGeltoki = null;
    }

    public int getKode() {
        return kode;
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

    public Timestamp getHasData() {
        return hasData;
    }

    public void setHasData(Timestamp hasData) {
        this.hasData = hasData;
    }

    public Timestamp getBukData() {
        return bukData;
    }

    public void setBukData(Timestamp bukData) {
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

}
