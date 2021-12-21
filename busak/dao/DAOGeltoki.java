package busak.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import busak.objektuak.Geltoki;

public class DAOGeltoki {

	Connection conn;

	/**
	 * Constructor vacio
	 */
	public DAOGeltoki() {
		super();
		conn = ConnectionManager.getConnection();
	}

	public void insert(Geltoki geltoki) {
		DAOLinea lineaDao = new DAOLinea();
		if(lineaDao.getByKode(geltoki.getLineaKode()) == null){
			System.out.println("Linea ez da existitzen");}
		else {try {
			String sql = "INSERT INTO Parada (CodLin, Orden, Nombre, Numero, Calle) VALUES (?,?,?,?,?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, geltoki.getLineaKode());
			pst.setInt(2, geltoki.getOrden());
			pst.setString(3, geltoki.getIzena());
			pst.setInt(4, geltoki.getZenbakia());
			pst.setString(5, geltoki.getKalea().getIzena());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

}
