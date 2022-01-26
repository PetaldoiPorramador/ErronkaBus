package busak.objektuak;

import java.util.ArrayList;

/**
 * Erabiltzaile objektua
 */
public class Erabiltzaile {

	/**
	 * Erabiltzailearen NAN edo AIZ
	 */
	private String nanAiz;
	/**
	 * Erabiltzailearen izena eta abizenak
	 */
	private String izenAbizenak;
	/**
	 * Erabiltzailearen pasahitza
	 */
	private String pasahitza;
	/**
	 * Erabiltzailearen bileteak
	 */
	private ArrayList<Bilete> bileteak;

	/**
	 * Eraikitzailea datu guztiekin
	 * 
	 * @param EnanAiz       Erabiltzailearen NAN edo AIZ
	 * @param EizenAbizenak Erabiltzailearen izena eta abizenak
	 * @param Epasahitza    Erabiltzailearen pasahitza
	 */
	public Erabiltzaile(String EnanAiz, String EizenAbizenak, String Epasahitza) {
		this.nanAiz = EnanAiz;
		this.izenAbizenak = EizenAbizenak;
		this.pasahitza = Epasahitza;
		bileteak = new ArrayList<Bilete>();
	}

	/**
	 * Eraikitzaile hutsa
	 */
	public Erabiltzaile() {
		nanAiz = null;
		izenAbizenak = null;
		pasahitza = null;
		bileteak = new ArrayList<Bilete>();
	}

	// GETERRAK

	/**
	 * NanAiz atributua bueltatzen du
	 * 
	 * @return String Erabiltzailearen NAN edo AIZ
	 */
	public String getNanAiz() {
		return nanAiz;
	}

	/**
	 * IzenAbizenak atributua bueltatzen du
	 * 
	 * @return String Erabiltzailearen izena eta abizenak
	 */
	public String getIzenAbizenak() {
		return izenAbizenak;
	}

	/**
	 * Pasahitza atributua bueltatzen du
	 * 
	 * @return String Erabiltzailearen pasahitza
	 */
	public String getPasahitza() {
		return pasahitza;
	}

	/**
	 * Bileteak atributua bueltatzen du
	 * 
	 * @return {@code ArrayList<Bilete>} Erabiltzailearen bileteak
	 */
	public ArrayList<Bilete> getBileteak() {
		return bileteak;
	}

	// SETERRAK

	/**
	 * NanAiz atributua ezartzen du
	 * 
	 * @param nan String Erabiltzailearen NAN edo AIZ
	 */
	public void setNanAiz(String nan) {
		this.nanAiz = nan;
	}

	/**
	 * IzenAbizenak atributua ezartzen du
	 * 
	 * @param izenAbiz String Erabiltzailearen izena eta abizenak
	 */
	public void setIzenAbizenak(String izenAbiz) {
		this.izenAbizenak = izenAbiz;
	}

	/**
	 * Pasahitza atributua ezartzen du
	 * 
	 * @param pass String Erabiltzailearen pasahitza
	 */
	public void setPasahitza(String pass) {
		this.pasahitza = pass;
	}

	/**
	 * Bileteak atributua ezartzen du
	 * 
	 * @param bileteak {@code ArrayList<Bilete>} Erabiltzailearen bileteak
	 */
	public void setBileteak(ArrayList<Bilete> bileteak) {
		this.bileteak = bileteak;
	}

	/**
	 * Bilete guztien informazioa pantailatik erakusten du
	 */
	public void ikusiBileteak() {
		// erakutsi erabiltzailearen bilete guztiak
		for (int i = 0; i < bileteak.size(); i++) {
			System.out.println(bileteak.get(i));
		}
	}
}
