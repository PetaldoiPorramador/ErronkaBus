package busak.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
		if (lineaDao.getByKode(geltoki.getLineaKode()) == null) {
			System.out.println("Linea ez da existitzen");
		} else {
			try {
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

	public void delete(int lineaKode, int orden) {
		try {
			String sql = "DELETE FROM Parada WHERE CodLin=? AND Orden=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, lineaKode);
			pst.setInt(2, orden);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Geltoki getByKode(int lineaKode, int orden) {
		Geltoki geltoki = new Geltoki();
		try {
			String sql = "SELECT * FROM Parada WHERE CodLin=? AND Orden=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, lineaKode);
			pst.setInt(2, orden);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				DAOKale kaleDao = new DAOKale();
				geltoki.setLineaKode(lineaKode);
				geltoki.setOrden(orden);
				geltoki.setIzena(rs.getString("Nombre"));
				geltoki.setZenbakia(rs.getInt("Numero"));
				geltoki.setKalea(kaleDao.getByIzena(rs.getString("Calle")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return geltoki;
	}

	public void update(Geltoki geltoki) {
		if (this.getByKode(geltoki.getLineaKode(), geltoki.getOrden()) == null) {
			System.out.println("Geltokia ez da existitzen");
		} else {
			try {
				String sql = "UPDATE Parada SET CodLin=?, Orden=?, Nombre=?, Numero=?, Calle=? WHERE CodLin=? AND Orden=?";
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setInt(1, geltoki.getLineaKode());
				pst.setInt(2, geltoki.getOrden());
				pst.setString(3, geltoki.getIzena());
				pst.setInt(4, geltoki.getZenbakia());
				pst.setString(5, geltoki.getKalea().getIzena());
				pst.setInt(6, geltoki.getLineaKode());
				pst.setInt(7, geltoki.getOrden());
				pst.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
