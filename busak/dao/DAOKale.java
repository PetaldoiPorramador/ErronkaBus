package busak.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import busak.objektuak.Kale;
import busak.objektuak.Udalerri;

public class DAOKale {

	Connection conn;

	/**
	 * Constructor vacio
	 */
	public DAOKale() {
		super();
		conn = ConnectionManager.getConnection();
	}

	public void insert(Kale kale) {
		DAOUdalerri udalDao = new DAOUdalerri();
		if (udalDao.getByKode(kale.getUdalerria().getKode()) == null) {
			System.out.println("Udalerria ez da existitzen");
		} else {
			try {
				String sql = "INSERT INTO Calle (Calle, CP, CodMun) VALUES (?,?,?)";
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setString(1, kale.getIzena());
				pst.setInt(2, kale.getPk());
				pst.setInt(3, kale.getUdalerria().getKode());
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

	public Kale getByIzena(String izena) {
		Kale kale = new Kale();
		try {
			String sql = "SELECT * FROM Calle WHERE Calle=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, izena);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				DAOUdalerri udalDao = new DAOUdalerri();
				kale.setIzena(izena);
				kale.setPk(rs.getInt("CP"));
				int codMun = rs.getInt("CodMun");
				Udalerri udalerri = udalDao.getByKode(codMun);
				kale.setUdalerria(udalerri);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return kale;
	}
	
	public void update(Kale kale) {
		if (this.getByIzena(kale.getIzena()) == null) {
			System.out.println("Kalea ez da existitzen");
		} else {
			try {
				String sql = "UPDATE Calle SET Calle=?, CP=?, CodMun=? WHERE Calle=?";
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setString(1, kale.getIzena());
				pst.setInt(2, kale.getPk());
				pst.setInt(3, kale.getUdalerria().getKode());
				pst.setString(4, kale.getIzena());
				pst.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
