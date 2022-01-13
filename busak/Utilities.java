package busak;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class Utilities {

	private Utilities() {
	}

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

	public static LocalDateTime eskatuOrdua() {
		LocalDateTime data = null;
		LocalDate eguna = null;
		LocalTime ordua = null;
		System.out.println("Sartu eguna(uuuu-hh-ee):");
		boolean ok = false;
		while (!ok) {
			try {
				eguna = LocalDate.parse(System.console().readLine());
			} catch (DateTimeParseException e) {
				System.out.println("Sartu egun formatu egokia(uuuu-hh-ee):");
			}
		}
		ok = false;
		System.out.println("Sartu ordua(hh:mm):");
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

	public static String eskatuString(int min) {
		String str = System.console().readLine();
		if (str.length() < min) {
			System.out.println("Sartu " + min + " karaktere edo gehiago");
			return eskatuString(min);
		}
		return str;
	}

	public static String eskatuPass() {
		String pass = String.valueOf(System.console().readPassword());
		if (pass.length() < 5) {
			System.out.println("Sartu 5 karaktere edo gehiago");
			return eskatuPass();
		}
		return pass;
	}

	private static boolean nanKonprobatu(int nanZbk, char letra) {
		char[] letrak = { 'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H',
				'L', 'C', 'K', 'E' };
		return letrak[nanZbk % 23] == letra;
	}

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
