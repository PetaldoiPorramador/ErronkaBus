package busak.objektuak;

import java.sql.Date;

public class Bilete {
    private int kode;
    private float ordaintzekoa;
    private Date hasData;
    private Date bukData;
    private Geltoki hasGeltoki;
    private Geltoki bukGeltoki;
    
    public Bilete(int kode, float ordaintzekoa, Date hasData, Date bukData, Geltoki hasGeltoki, Geltoki bukGeltoki) {
        this.kode = kode;
        this.ordaintzekoa = ordaintzekoa;
        this.hasData = hasData;
        this.bukData = bukData;
        this.hasGeltoki = hasGeltoki;
        this.bukGeltoki = bukGeltoki;
    }

    
}
