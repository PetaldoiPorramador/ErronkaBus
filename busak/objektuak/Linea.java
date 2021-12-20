package busak.objektuak;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class Linea {

	private int kodea;
	private String izena;
	private LocalTime hasOrdGor;
	private LocalTime bukOrdGor;
	private LocalTime hasOrdBer;
	private LocalTime bukOrdBer;
	private float pvpu;
	private int maiztasuna;
	private ArrayList<Geltoki> geltokiak;

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

	public int getKodea() {
		return kodea;
	}

	public void setKodea(int kodea) {
		this.kodea = kodea;
	}

	public String getIzena() {
		return izena;
	}

	public void setIzena(String izena) {
		this.izena = izena;
	}

	public LocalTime getHasOrdGor() {
		return hasOrdGor;
	}

	public void setHasOrdGor(LocalTime hasOrdGor) {
		this.hasOrdGor = hasOrdGor;
	}

	public LocalTime getBukOrdGor() {
		return bukOrdGor;
	}

	public void setBukOrdGor(LocalTime bukOrdGor) {
		this.bukOrdGor = bukOrdGor;
	}

	public LocalTime getHasOrdBer() {
		return hasOrdBer;
	}

	public void setHasOrdBer(LocalTime hasOrdBer) {
		this.hasOrdBer = hasOrdBer;
	}

	public LocalTime getBukOrdBer() {
		return bukOrdBer;
	}

	public void setBukOrdBer(LocalTime bukOrdBer) {
		this.bukOrdBer = bukOrdBer;
	}

	public float getPvpu() {
		return pvpu;
	}

	public void setPvpu(float pvpu) {
		this.pvpu = pvpu;
	}

	public int getMaiztasuna() {
		return maiztasuna;
	}

	public void setMaiztasuna(int maiztasuna) {
		this.maiztasuna = maiztasuna;
	}

	public ArrayList<Geltoki> getGeltokiak() {
		return geltokiak;
	}

	public void setGeltokiak(ArrayList<Geltoki> geltokiak) {
		this.geltokiak = geltokiak;
	}

	public void printGeltoki() {
		System.out.println("Geltokiak:");
		for (Geltoki geltoki : geltokiak) {
			System.out.println(geltoki.getIzena() + ": " + geltoki.getKalea());
		}
	}

	public ArrayList<Timestamp> getOrduEgoki(Timestamp data) {
		// TODDO implementar
		LocalDateTime bilaketa = data.toLocalDateTime();
		LocalDateTime hasiera = LocalDateTime.of(bilaketa.getYear(), bilaketa.getMonth(), bilaketa.getDayOfMonth(),
				hasOrdGor.getHour(), hasOrdGor.getMinute());
		LocalDateTime bukaera = LocalDateTime.of(bilaketa.getYear(), bilaketa.getMonth(), bilaketa.getDayOfMonth(),
				bukOrdGor.getHour(), bukOrdGor.getMinute());
		ArrayList<LocalDateTime> orduak = new ArrayList<LocalDateTime>();
		LocalDateTime ordua = hasiera;
		LocalDateTime beheMuga = bilaketa.minusHours(1);
		LocalDateTime goiMuga = bilaketa.plusHours(1);
		while (ordua.isBefore(bukaera) && ordua.isBefore(goiMuga)) {
			if (ordua.isAfter(beheMuga) && ordua.isBefore(goiMuga)) {
				orduak.add(ordua);
			}
			ordua = ordua.plusMinutes(maiztasuna);
		}
		return orduak;
	}

}
