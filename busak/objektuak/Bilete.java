package busak.objektuak;

import java.time.LocalDateTime;

import busak.Utilities;

/**
 * Bilete objektua
 */
public class Bilete {

    /**
     * Biletearen kodea
     */
    private int kode;
    /**
     * Biletea erosi duen erabiltzailearen NAN edo AIZ
     */
    private String nan;
    /**
     * Biletearen prezioa
     */
    private float ordaintzekoa;
    /**
     * Bidaiaren hasierako eguna eta ordua
     */
    private LocalDateTime hasData;
    /**
     * Bidaiaren amaierako eguna eta ordua
     */
    private LocalDateTime bukData;
    /**
     * Bidaiaren hasierako geltokia
     */
    private Geltoki hasGeltoki;
    /**
     * Bidaiaren amaierako geltokia
     */
    private Geltoki bukGeltoki;

    /**
     * Eraikitzailea datu guztiekin
     * 
     * @param kode         Biletearen kodea(bakarra)
     * @param ordaintzekoa Bileteak zenbat balio duen
     * @param nan          Biletea erosi duen erabiltzailearen NAN edo AIZ
     * @param hasData      Bidaiaren hasierako eguna eta ordua
     * @param bukData      Bidaiaren amaierako eguna eta ordua
     * @param hasGeltoki   Bidaiaren hasierako geltokia
     * @param bukGeltoki   Bidaiaren amaierako geltokia
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

    /**
     * Eraikitzaile hutsa
     */
    public Bilete() {
        this.kode = -1;
        this.ordaintzekoa = -1;
        this.nan = null;
        this.hasData = null;
        this.bukData = null;
        this.hasGeltoki = null;
        this.bukGeltoki = null;
    }

    // GETERRAK

    /**
     * Kode atributua bueltatzen du
     *
     * @return int Biletearen kodea
     */
    public int getKode() {
        return kode;
    }

    /**
     * Nan atributua bueltatzen du
     * 
     * @return String Biletea erosi duen erabiltzailearen NAN edo AIZ
     */
    public String getNan() {
        return nan;
    }

    /**
     * Ordaintzekoa atributua bueltatzen du
     * 
     * @return float Biletearen prezioa
     */
    public float getOrdaintzekoa() {
        return ordaintzekoa;
    }

    /**
     * HasData atributua bueltatzen du
     * 
     * @return LocalDateTime Bidaiaren hasierako eguna eta ordua
     */
    public LocalDateTime getHasData() {
        return hasData;
    }

    /**
     * HasData atributua bueltatzen du
     * 
     * @return LocalDateTime Bidaiaren amaierako eguna eta ordua
     */
    public LocalDateTime getBukData() {
        return bukData;
    }

    /**
     * HasGeltoki atributua bueltatzen du
     * 
     * @return Geltoki Bidaiaren hasierako geltokia
     */
    public Geltoki getHasGeltoki() {
        return hasGeltoki;
    }

    /**
     * BukGeltoki atributua bueltatzen du
     * 
     * @return Geltoki Bidaiaren amaierako geltokia
     */
    public Geltoki getBukGeltoki() {
        return bukGeltoki;
    }

    // SETERRAK

    /**
     * Kode atributua ezartzen du
     * 
     * @param kode Biletearen kodea
     */
    public void setKode(int kode) {
        this.kode = kode;
    }

    /**
     * Nan atributua ezartzen du
     * 
     * @param nan Biletea erosi duen erabiltzailearen NAN edo AIZ
     */
    public void setNan(String nan) {
        this.nan = nan;
    }

    /**
     * Ordaintzekoa atributua ezartzen du
     * 
     * @param ordaintzekoa Biletearen prezioa
     */
    public void setOrdaintzekoa(float ordaintzekoa) {
        this.ordaintzekoa = ordaintzekoa;
    }

    /**
     * HasData atributua ezartzen du
     * 
     * @param hasData Bidaiaren hasierako eguna eta ordua
     */
    public void setHasData(LocalDateTime hasData) {
        this.hasData = hasData;
    }

    /**
     * BukData atributua ezartzen du
     * 
     * @param bukData Bidaiaren amaierako eguna eta ordua
     */
    public void setBukData(LocalDateTime bukData) {
        this.bukData = bukData;
    }

    /**
     * HasGeltoki atributua ezartzen du
     * 
     * @param hasGeltoki Bidaiaren hasierako geltokia
     */
    public void setHasGeltoki(Geltoki hasGeltoki) {
        this.hasGeltoki = hasGeltoki;
    }

    /**
     * BukGeltoki atributua ezartzen du
     * 
     * @param bukGeltoki Bidaiaren amaierako geltokia
     */
    public void setBukGeltoki(Geltoki bukGeltoki) {
        this.bukGeltoki = bukGeltoki;
    }

    @Override
    public String toString() {
        if (kode != -1)
            return "Kodea: " + kode + " ordaintzekoa: " + ordaintzekoa + " Eroslearen NAN/AIZ-a: " + nan
                    + "\n\tBidaiaren hasierako eguna eta ordua: " + Utilities.dateTimeToString(hasData)
                    + "\n\tEstimatutako bidaiaren amaierako eguna eta ordua: " + Utilities.dateTimeToString(bukData)
                    + "\n\t" + hasGeltoki + "-tik \n\t" + bukGeltoki + "-ra";
        else
            return "\tOrdaintzekoa: " + ordaintzekoa + " Eroslearen NAN/AIZ-a: " + nan
                    + "\n\tBidaiaren hasierako eguna eta ordua: " + Utilities.dateTimeToString(hasData)
                    + "\n\tEstimatutako bidaiaren amaierako eguna eta ordua: " + Utilities.dateTimeToString(bukData)
                    + "\n\t" + hasGeltoki + "-tik \n\t" + bukGeltoki + "-ra";

    }

    /**
     * Biletearen datu guztiak string baten ematen ditu, geltokien izenak bakarrik
     * ematen ditu eta ez beraien datu guztiak
     * 
     * @return String Biletearen informazioa
     * @see toString()
     */
    public String toStringIzenak() {
        return "Ordaintzekoa: " + ordaintzekoa + " Eroslearen NAN/AIZ-a: " + nan
                + "\nBidaiaren hasierako eguna eta ordua: " + Utilities.dateTimeToString(hasData)
                + "\nEstimatutako bidaiaren amaierako eguna eta ordua: " + Utilities.dateTimeToString(bukData) + " "
                + hasGeltoki.getIzena() + "-tik "
                + bukGeltoki.getIzena() + "-ra";
    }

}
