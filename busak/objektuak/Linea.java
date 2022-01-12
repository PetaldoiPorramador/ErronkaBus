package busak.objektuak;

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
			System.out.println(geltoki.getOrden() + " " + geltoki.getIzena() + ": " + geltoki.getKalea());
		}
	}

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
			desfase = bidaiDenbora(geltokiak.size()-1, geltoki);
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

	public int bidaiDenbora(int hasiera, int bukaera) {
		int denbora = 0;
		if (hasiera < bukaera) {
			for (int i = hasiera; i < bukaera; i++) {
				denbora += geltokiak.get(i).getDenboraBzBs();
			}
		} else {
			for (int i = hasiera; i > bukaera; i--) {
				denbora += geltokiak.get(i - 1).getDenboraBzBs();
			}
		}
		return denbora;
	}

	@Override
	public String toString() {
		return "Linea [bukOrdBer=" + bukOrdBer + ", bukOrdGor=" + bukOrdGor + ", geltokiak=" + geltokiak
				+ ", hasOrdBer=" + hasOrdBer + ", hasOrdGor=" + hasOrdGor + ", izena=" + izena + ", kodea=" + kodea
				+ ", maiztasuna=" + maiztasuna + ", pvpu=" + pvpu + "]";
	}

}
