package busak;

import busak.dao.DAOUdalerri;
import busak.objektuak.Udalerri;

public class MainPruebas {
	
	public static void main(String[] args) {
		DAOUdalerri dao = new DAOUdalerri();
		Udalerri udalerri = new Udalerri(4638, "izena");
		Udalerri udalerri2 = new Udalerri(4639, "izena2");
		dao.insert(udalerri);
		dao.insert(udalerri2);
		dao.delete(udalerri.getKode());
		udalerri = dao.getByKode(udalerri2.getKode());
		System.out.println(udalerri.getIzena());
	}

}
