package busak.objektuak;

import java.time.LocalDateTime;

/** */
public class Bilete {
    private int kode;
    private float ordaintzekoa;
    private String nan;
    private LocalDateTime hasData;
    private LocalDateTime bukData;
    private Geltoki hasGeltoki;
    private Geltoki bukGeltoki;
    
    /**@param kode biletearen kodea(bakarra)
     * @param ordaintzekoa bileteak zenbat balio duen
     * @param nan biletea erosi duen erabiltzailearen NAN edo AIZ
     * @param hasData bidaiaren hasierako eguna eta ordua
     * @param bukData bidaiaren amaierako eguna eta ordua
     * @param hasGeltoki bidaiaren hasierako geltokia
     * @param bukGeltoki bidaiaren amaierako geltokia
     */
    public Bilete(int kode, float ordaintzekoa, String nan, LocalDateTime hasData, LocalDateTime bukData,
            Geltoki hasGeltoki, Geltoki bukGeltoki) {
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

    //GETERRAK

    /**
    * kode atributua bueltatzen du
    * @return biletearen kodea
    */
    public int getKode() {
        return kode;
    }

    /**
     * nan atributua bueltatzen du
     * @return biletea erosi duen erabiltzailearen NAN edo AIZ
     */
    public String getNan() {
        return nan;
    }

    /**
     * ordaintzekoa atributua bueltatzen du
     * @return biletearen prezioa
     */
    public float getOrdaintzekoa() {
        return ordaintzekoa;
    }

    /**
     * hasData atributua bueltatzen du
     * @return bidaiaren hasierako eguna eta ordua
     */
    public LocalDateTime getHasData() {
        return hasData;
    }

     /**
     * hasData atributua bueltatzen du
     * @return bidaiaren amaierako eguna eta ordua
     */
    public LocalDateTime getBukData() {
        return bukData;
    }

    /**
     * hasGeltoki atributua bueltatzen du
     * @return bidaiaren hasierako geltokia
     */
    public Geltoki getHasGeltoki() {
        return hasGeltoki;
    }

    /**
     * bukGeltoki atributua bueltatzen du
     * @return bidaiaren amaierako geltokia
     */
    public Geltoki getBukGeltoki() {
        return bukGeltoki;
    }
    
    //SETERRAK
    
    /**
     * 
     * @param nan
     */
    public void setNan(String nan) {
        this.nan = nan;
    }

    public void setKode(int kode) {
        this.kode = kode;
    }

    public void setOrdaintzekoa(float ordaintzekoa) {
        this.ordaintzekoa = ordaintzekoa;
    }

    public void setHasData(LocalDateTime hasData) {
        this.hasData = hasData;
    }

    public void setBukData(LocalDateTime bukData) {
        this.bukData = bukData;
    }

    public void setHasGeltoki(Geltoki hasGeltoki) {
        this.hasGeltoki = hasGeltoki;
    }

    public void setBukGeltoki(Geltoki bukGeltoki) {
        this.bukGeltoki = bukGeltoki;
    }

    @Override
    public String toString() {
        if (kode != -1)
            return "Kodea: " + kode + " ordaintzekoa: " + ordaintzekoa + " Eroslearen NAN/AIZ-a: " + nan
                    + "\nBidaiaren hasierako eguna eta ordua: " + hasData
                    + "\nEstimatutako bidaiaren amaierako eguna eta ordua" + bukData + " " + hasGeltoki + "-tik "
                    + bukGeltoki + "-ra";
        else
            return "Ordaintzekoa: " + ordaintzekoa + " Eroslearen NAN/AIZ-a: " + nan
                    + "\nBidaiaren hasierako eguna eta ordua: " + hasData
                    + "\nEstimatutako bidaiaren amaierako eguna eta ordua" + bukData + " " + hasGeltoki + "-tik "
                    + bukGeltoki + "-ra";

    }

    public boolean berdinaDa(int kode, String nan) {
        if (this.kode == kode && this.nan.equalsIgnoreCase(nan)) {
            return true;
        } else {
            return false;
        }
    }

    public String toStringIzenak() {
        return "Ordaintzekoa: " + ordaintzekoa + " Eroslearen NAN/AIZ-a: " + nan
                + "\nBidaiaren hasierako eguna eta ordua: " + hasData
                + "\nEstimatutako bidaiaren amaierako eguna eta ordua" + bukData + " " + hasGeltoki.toStringBakarrikIzena() + "-tik "
                + bukGeltoki.toStringBakarrikIzena() + "-ra";
    }

}
