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
}
