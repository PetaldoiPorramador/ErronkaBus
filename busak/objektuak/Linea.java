package busak.objektuak;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Linea objektua
 */
public class Linea {

	/**
	 * Linearen kodea
	 */
	private int kodea;
	/**
	 * Linearen izena
	 */
	private String izena;
	/**
	 * Linearen hasierako ordua gorako norantzan
	 */
	private LocalTime hasOrdGor;
	/**
	 * Linearen bukaerako ordua gorako norantzan
	 */
	private LocalTime bukOrdGor;
	/**
	 * Linearen hasierako ordua beherako norantzan
	 */
	private LocalTime hasOrdBer;
	/**
	 * Linearen bukaerako ordua beherako norantzan
	 */
	private LocalTime bukOrdBer;
	/**
	 * Linearen prezioa geltoki bakoitzeko
	 */
	private float pvpu;
	/**
	 * Linearen maiztasuna minututan
	 */
	private int maiztasuna;
	/**
	 * Linearen geltokiak
	 */
	private ArrayList<Geltoki> geltokiak;

	/**
	 * Eraikitzailea datu guztiekin
	 * 
	 * @param kodea      Linearen kodea
	 * @param izena      Linearen izena
	 * @param hasOrdGor  Linearen hasierako ordua gorako norantzan
	 * @param bukOrdGor  Linearen bukaerako ordua gorako norantzan
	 * @param hasOrdBer  Linearen hasierako ordua beherako norantzan
	 * @param bukOrdBer  Linearen bukaerako ordua beherako norantzan
	 * @param pvpu       Linearen prezioa geltoki bakoitzeko
	 * @param maiztasuna Linearen maiztasuna minututan
	 * @param geltokiak  Linearen geltokiak
	 */
	public Linea(int kodea, String izena, LocalTime hasOrdGor, LocalTime bukOrdGor, LocalTime hasOrdBer,
			LocalTime bukOrdBer, float pvpu, int maiztasuna, ArrayList<Geltoki> geltokiak) {
		this.kodea = kodea;
		this.izena = izena;
		this.hasOrdGor = hasOrdGor;
		this.bukOrdGor = bukOrdGor;
		this.hasOrdBer = hasOrdBer;
		this.bukOrdBer = bukOrdBer;
		this.pvpu = pvpu;
		this.maiztasuna = maiztasuna;
		this.geltokiak = geltokiak;
	}

	/**
	 * Eraikitzaile hutsa
	 */
	public Linea() {
		this.kodea = -1;
		this.izena = null;
		this.hasOrdGor = null;
		this.bukOrdGor = null;
		this.hasOrdBer = null;
		this.bukOrdBer = null;
		this.pvpu = -1;
		this.maiztasuna = -1;
		this.geltokiak = new ArrayList<Geltoki>();
	}

	// GETTERRAK

	/**
	 * Kodea atributua bueltatzen du
	 * 
	 * @return int Linearen kodea
	 */
	public int getKodea() {
		return kodea;
	}

	/**
	 * Izena atributua bueltatzen du
	 * 
	 * @return String Linearen izena
	 */
	public String getIzena() {
		return izena;
	}

	/**
	 * HasOrdGor atributua bueltatzen du
	 * 
	 * @return LocalTime Linearen hasierako ordua gorako norantzan
	 */
	public LocalTime getHasOrdGor() {
		return hasOrdGor;
	}

	/**
	 * BukOrdGor atributua bueltatzen du
	 * 
	 * @return LocalTime Linearen bukaerako ordua gorako norantzan
	 */
	public LocalTime getBukOrdGor() {
		return bukOrdGor;
	}

	/**
	 * HasOrdBer atributua bueltatzen du
	 * 
	 * @return LocalTime Linearen hasierako ordua beherako norantzan
	 */
	public LocalTime getHasOrdBer() {
		return hasOrdBer;
	}

	/**
	 * BukOrdBer atributua bueltatzen du
	 * 
	 * @return LocalTime Linearen bukaerako ordua beherako norantzan
	 */
	public LocalTime getBukOrdBer() {
		return bukOrdBer;
	}

	/**
	 * Pvpu atributua bueltatzen du
	 * 
	 * @return float Linearen prezioa geltoki bakoitzeko
	 */
	public float getPvpu() {
		return pvpu;
	}

	/**
	 * Maiztasuna atributua bueltatzen du
	 * 
	 * @return int Linearen maiztasuna minututan
	 */
	public int getMaiztasuna() {
		return maiztasuna;
	}

	/**
	 * Geltokiak atributua bueltatzen du
	 * 
	 * @return ArrayList<Geltoki> Linearen geltokiak
	 */
	public ArrayList<Geltoki> getGeltokiak() {
		return geltokiak;
	}

	/**
	 * Linearen geltoki bat bueltatzen du
	 * 
	 * @param ordena Geltokiaren ordena
	 * @return Geltoki Linearen geltoki bat
	 */
	public Geltoki getGeltoki(int ordena) {
		return this.geltokiak.get(ordena);
	}

	// SETTERRAK

	/**
	 * Kodea atributua ezartzen du
	 * 
	 * @param kodea Linearen kodea
	 */
	public void setKodea(int kodea) {
		this.kodea = kodea;
	}

	/**
	 * Izena atributua ezartzen du
	 * 
	 * @param izena Linearen izena
	 */
	public void setIzena(String izena) {
		this.izena = izena;
	}

	/**
	 * HasOrdGor atributua ezartzen du
	 * 
	 * @param hasOrdGor Linearen hasierako ordua gorako norantzan
	 */
	public void setHasOrdGor(LocalTime hasOrdGor) {
		this.hasOrdGor = hasOrdGor;
	}

	/**
	 * BukOrdGor atributua ezartzen du
	 * 
	 * @param bukOrdGor Linearen bukaerako ordua gorako norantzan
	 */
	public void setBukOrdGor(LocalTime bukOrdGor) {
		this.bukOrdGor = bukOrdGor;
	}

	/**
	 * HasOrdBer atributua ezartzen du
	 * 
	 * @param hasOrdBer Linearen hasierako ordua beherako norantzan
	 */
	public void setHasOrdBer(LocalTime hasOrdBer) {
		this.hasOrdBer = hasOrdBer;
	}

	/**
	 * BukOrdBer atributua ezartzen du
	 * 
	 * @param bukOrdBer Linearen bukaerako ordua beherako norantzan
	 */
	public void setBukOrdBer(LocalTime bukOrdBer) {
		this.bukOrdBer = bukOrdBer;
	}

	/**
	 * Pvpu atributua ezartzen du
	 * 
	 * @param pvpu Linearen prezioa geltoki bakoitzeko
	 */
	public void setPvpu(float pvpu) {
		this.pvpu = pvpu;
	}

	/**
	 * Maiztasuna atributua ezartzen du
	 * 
	 * @param maiztasuna Linearen maiztasuna minututan
	 */
	public void setMaiztasuna(int maiztasuna) {
		this.maiztasuna = maiztasuna;
	}

	/**
	 * Geltokiak atributua ezartzen du
	 * 
	 * @param geltokiak Linearen geltokiak
	 */
	public void setGeltokiak(ArrayList<Geltoki> geltokiak) {
		this.geltokiak = geltokiak;
	}

	/**
	 * Linearen geltoki guztien informazioa pantailatik inprimatzen du
	 */
	public void printGeltoki() {
		for (Geltoki geltoki : geltokiak) {
			System.out.println(geltoki);
		}
	}

	/**
	 * Egun eta ordu bat, geltoki bat eta bidaiaren norantza emanda, busa hartzeko
	 * denbora egokiak bueltatzen ditu bi orduko denbora tarte batean
	 * 
	 * @param bilaketa Eguna eta ordua
	 * @param geltoki  Geltokiaren ordena
	 * @param gora     Bidaiaren norantza, true gora, false behera
	 * @return ArrayList<LocalTime> Bidaiaren hasierako orduen lista
	 */
	public ArrayList<LocalDateTime> getOrduEgoki(LocalDateTime bilaketa, int geltoki, boolean gora) {
		LocalDateTime hasiera;
		LocalDateTime bukaera;
		int desfase;
		if (gora) {
			hasiera = LocalDateTime.of(bilaketa.getYear(), bilaketa.getMonth(), bilaketa.getDayOfMonth(),
					hasOrdGor.getHour(), hasOrdGor.getMinute());
			bukaera = LocalDateTime.of(bilaketa.getYear(), bilaketa.getMonth(), bilaketa.getDayOfMonth(),
					bukOrdGor.getHour(), bukOrdGor.getMinute());
			desfase = bidaiDenbora(0, geltoki);
		} else {
			hasiera = LocalDateTime.of(bilaketa.getYear(), bilaketa.getMonth(), bilaketa.getDayOfMonth(),
					hasOrdBer.getHour(), hasOrdBer.getMinute());
			bukaera = LocalDateTime.of(bilaketa.getYear(), bilaketa.getMonth(), bilaketa.getDayOfMonth(),
					bukOrdBer.getHour(), bukOrdBer.getMinute());
			desfase = bidaiDenbora(geltokiak.size() - 1, geltoki);
		}
		hasiera = hasiera.plusMinutes(desfase);
		bukaera = bukaera.plusMinutes(desfase);
		LocalDateTime ordua = hasiera;
		LocalDateTime beheMuga = bilaketa.minusHours(1);
		LocalDateTime goiMuga = bilaketa.plusHours(1);
		ArrayList<LocalDateTime> orduak = new ArrayList<LocalDateTime>();
		while (ordua.isBefore(bukaera) && ordua.isBefore(goiMuga)) {
			if (ordua.isAfter(beheMuga) && ordua.isBefore(goiMuga)) {
				orduak.add(ordua);
			}
			ordua = ordua.plusMinutes(maiztasuna);
		}
		return orduak;
	}

	/**
	 * Linearen geltoki batetik beste geltoki batera joateko behar den denbora
	 * kalkulatzen du
	 * 
	 * @param hasiera Bidaiaren hasierako geltokiaren ordena
	 * @param bukaera Bidaiaren bukaerako geltokiaren ordena
	 * @return int Bidaiaren denbora minututan
	 */
	public int bidaiDenbora(int hasiera, int bukaera) {
		int denbora = 0;
		if (hasiera > bukaera) {
			int aux = hasiera;
			hasiera = bukaera;
			bukaera = aux;
		}
		for (int i = hasiera; i < bukaera; i++) {
			denbora += geltokiak.get(i).getDenboraBzBs();
		}
		return denbora;
	}

	@Override
	public String toString() {
		return "Linearen izena: " + izena + " kodea: " + kodea +
				"\n\tHasierako ordua(G): " + hasOrdGor + " bukaerako ordua: " + bukOrdGor
				+ "\n\tHasierako ordua(B): " + hasOrdBer + " bukaerako ordua: " + bukOrdBer
				+ "\n\tMaiztasuna " + maiztasuna + " eta geltokiko PSN-a: " + pvpu;
	}

}
