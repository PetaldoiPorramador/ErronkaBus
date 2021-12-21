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

}
