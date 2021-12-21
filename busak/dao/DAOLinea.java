package busak.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import busak.objektuak.Linea;

public class DAOLinea {
	
	Connection conn;

	/**
	 * Constructor vacio
	 */
	public DAOLinea() {
		super();
		conn = ConnectionManager.getConnection();
	}

	public void insert(Linea linea) {
		DAOUdalerri udalDao = new DAOUdalerri();
		if (udalDao.getByKode(linea.getUdalerria().getKode()) == null) {
			System.out.println("Udalerria ez da existitzen");
		} else {
			try {
				String sql = "INSERT INTO Calle (Calle, CP, CodMun) VALUES (?,?,?)";
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setString(1, linea.getIzena());
				pst.setInt(2, linea.getPk());
				pst.setInt(3, linea.getUdalerria().getKode());
				pst.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void delete(String izena) {
		try {
			String sql = "DELETE FROM Calle WHERE Calle=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, izena);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Linea getByKode(String izena) {
		Linea linea = new Linea();
		try {
			String sql = "SELECT * FROM Calle WHERE Calle=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, izena);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				DAOUdalerri udalDao = new DAOUdalerri();
				linea.setIzena(rs.getString("Calle"));
				linea.setPk(rs.getInt("CP"));
				linea.setUdalerria(udalDao.getByKode(rs.getInt("CodMun")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return linea;
	}

	public void update(Linea linea) {
		DAOUdalerri udalDao = new DAOUdalerri();
		if (udalDao.getByKode(linea.getUdalerria().getKode()) == null) {
			System.out.println("Udalerria ez da existitzen");
		} else {
			try {
				String sql = "UPDATE Calle SET Calle=?, CP=?, CodMun=? WHERE Calle=?";
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setString(1, linea.getIzena());
				pst.setInt(2, linea.getPk());
				pst.setInt(3, linea.getUdalerria().getKode());
				pst.setString(4, linea.getIzena());
				pst.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
