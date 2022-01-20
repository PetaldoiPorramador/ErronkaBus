package busak;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class Utilities {

	private Utilities() {
	}
	/**
	 * Kontsolatik zenbaki bat eskatzen eta bueltatzen duen metodoa
	 * @param max <b>int</b> zenbakiaren balio maximoa
	 * @return <b>int</b> 0 eta max arteko zenbaki bat
	 */
	public static int eskatuInt(int max) {
		int num = 0;
		boolean ok = false;
		while (!ok) {
			try {
				num = Integer.parseInt(System.console().readLine());
				if (num > 0 && num <= max) {
					ok = true;
				} else {
					System.out.println("Sartu 1 eta " + max + " tartean");
				}
			} catch (NumberFormatException e) {
				System.out.println("Sartu zenbaki bat");
			}
		}
		return num;
	}

	/**
	 * Hamartarrak izan dezakeen zenbaki bat eskatzen eta bueltatzen duen metodoa
	 * @return <b>double</b> 0 baino handiagoa den zenbaki bat
	 */
	public static double eskatuDouble() {
		double num = 0;
		boolean ok = false;
		while (!ok) {
			try {
				num = Double.parseDouble(System.console().readLine());
				if (num > 0) {
					ok = true;
				} else {
					System.out.println("Sartu zenbaki positibo bat");
				}
			} catch (NumberFormatException e) {
				System.out.println("Sartu zenbaki bat");
			}
		}
		return num;
	}

	/**
	 * Une honetatik ahurrera dauden eguna eta hordua sartzeko eskatzen du kontsolatik lehenengo eguna ondoren ordua
	 * @return <b>LocalDateTime</b> uuuu-hh-eeThh:mm formatua duen data bat
	 */
	public static LocalDateTime eskatuOrdua() {
		LocalDateTime data = null;
		LocalDate eguna = null;
		LocalTime ordua = null;
		System.out.println("Sartu bidaiaren eguna(uuuu-hh-ee):");
		boolean ok = false;
		while (!ok) {
			try {
				eguna = LocalDate.parse(System.console().readLine());
				ok = true;
			} catch (DateTimeParseException e) {
				System.out.println("Sartu egun formatu egokia(uuuu-hh-ee):");
			}
		}
		ok = false;
		System.out.println("Sartu bidaiatzeko ordu(hh:mm) desiratua:");
		while (!ok) {
			try {
				ordua = LocalTime.parse(System.console().readLine());
				ok = true;
			} catch (DateTimeParseException e) {
				System.out.println("Sartu ordu formatu egokia (hh:mm):");
			}
		}
		data = LocalDateTime.of(eguna, ordua);
		return data;
	}

	/**
	 * Kontsolatik karaktere kate bat eskatzen eta bueltatzen duen metodoa
	 * @param min <b>int</b> sartutako karaktere katearen luzera minimoa
	 * @return <b></b>String min baino handiagoa edo berdina den luzera izanda
	 */
	public static String eskatuString(int min) {
		String str = System.console().readLine();
		if (str.length() < min) {
			System.out.println("Sartu " + min + " karaktere edo gehiago");
			return eskatuString(min);
		}
		return str;
	}

	/**
	 * Metodo honek pasahitza eskatzen du kontsolatik (hizkiak ez dira kontsolan ikusiko)
	 * @return <b>String</b> luzeera 4 baino handiagoa duen karaktere katea
	 */
	public static String eskatuPass() {
		String pass = String.valueOf(System.console().readPassword());
		if (pass.length() < 5) {
			System.out.println("Sartu 5 karaktere edo gehiago");
			return eskatuPass();
		}
		return pass;
	}

	/**
	 * Metodo honek NAN baten zenbaki zatia eta hizkia egokiak badira esaten du
	 * @param nanZbk <b>int</b> NAN-aren zenbakizko zatia
	 * @param letra  <b>char</b> NAN-aren hizkia
	 * @return <b>boolean</b> NAN-aren hizkia eta zenbakia egokiak badira <b>true</b> beztela <b>false</b>
	 */
	private static boolean nanKonprobatu(int nanZbk, char letra) {
		char[] letrak = { 'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H',
				'L', 'C', 'K', 'E' };
		return letrak[nanZbk % 23] == letra;
	}

	/**
	 * Kontsolatik NAN bat eskatzen du horren formatua egokia dela zihurtatzen da eta bueltatzen du
	 * @return <b>String</b> NAN bat bueltatzen du
	 */
	public static String eskatuNan() {
		String nan = null;
		boolean ok = false;
		do {
			nan = System.console().readLine().toUpperCase();
			if (nan.length() == 9) {
				try {
					int nanZbk = Integer.parseInt(nan.substring(0, 8));
					char letra = nan.charAt(8);
					if (nanKonprobatu(nanZbk, letra)) {
						ok = true;
					} else {
						System.out.println("Nan-a ez da zuzena");
					}
				} catch (NumberFormatException e) {
					System.out.println("Sartu nan formatu egokia");
				}
			} else {
				System.out.println("Sartu nan formatu egokia");
			}
		} while (!ok);
		return nan;
	}

	/**
	 * Kontsolatik 'b' edo 'e' hizkiak sartzeko eskatzen du
	 * @return <b>boolean</b> 'b' sartu bada <b>true</b> eta 'e' sartu bada <b>false</b>
	 */
	public static boolean eskatuBaiEz() {
		do {
			String str = System.console().readLine();
			if (str.equalsIgnoreCase("e")) {
				return false;
			} else if (str.equalsIgnoreCase("b")) {
				return true;
			} else {
				System.out.print("GOGORATU! bai(b), ez(e)");
			}
		} while (true);
	}
}
