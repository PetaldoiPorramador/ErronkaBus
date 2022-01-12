package busak;

import busak.dao.DAOLinea;

public class MainPruebas {
	
	public static void main(String[] args) {
		DAOLinea daoL = new DAOLinea();
		System.out.println(daoL.getAll());
	}

}
