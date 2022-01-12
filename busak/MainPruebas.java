package busak;

import busak.dao.DAOLinea;
import busak.dao.DAOErabiltzaile;
import busak.objektuak.Erabiltzaile;

public class MainPruebas {
	
	public static void main(String[] args) {
		
		//ERREGISTRATU
		Erabiltzaile erabiltzaileBerri = new Erabiltzaile();
		
		System.out.println("ERABILTZAILE BERRIA ESKATU");
		System.out.print("Erabiltzailearen NAN: ");
		String nan = Utilities.eskatuString(9);
		erabiltzaileBerri.setNanAiz(nan);
		
		System.out.print("\n" + "Erabiltzailearen Izen Abizenak: ");
		String izenabiz = Utilities.eskatuString(3);
		erabiltzaileBerri.setIzenAbizenak(izenabiz);
		
		System.out.print("\n" + "Pasahitza sortu: ");
		String pasahitza = Utilities.eskatuString(5);
		erabiltzaileBerri.setPasahitza(pasahitza);
		
		System.out.println("\n" + "DATUAK");
		System.out.println("NAN: " + nan);
		System.out.println("Izen Abizenak: " + izenabiz);
		System.out.println("Pasahitza: " + "*".repeat(pasahitza.length()));
		
		String baiez = "";
		do {
			System.out.println("Erabiltzaile sortu nahi duzu (b/e)?");
			baiez = Utilities.eskatuString(1);
		}while(baiez.charAt(0) != 'b' && baiez.charAt(0) != 'e');
		if(baiez.charAt(0) == 'b') {
			DAOErabiltzaile erabil = new DAOErabiltzaile();
			erabil.insert(erabiltzaileBerri);
		}
		
	}

}
