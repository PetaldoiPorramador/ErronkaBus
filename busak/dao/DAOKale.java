package busak.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import busak.objektuak.Kale;
import busak.objektuak.Udalerri;

/**
 * Kale objektuak datu basearekin erlazionatzen duen klasea
 */
public class DAOKale {

	/**
	 * Datu basearekiko konexioa
	 */
	Connection conn;

	/**
	 * Eraikitzailea
	 */
	public DAOKale() {
		super();
		conn = ConnectionManager.getConnection();
	}

	/**
	 * Kale bat datu basean sartzeko metodoa
	 *
	 * @param kale Sartu nahi dugun kalea
	 * @return boolean True erroreak ez badaude, false erroreak badaude
	 */
	public boolean insert(Kale kale) {
		DAOUdalerri udalDao = new DAOUdalerri();
		if (udalDao.getByKode(kale.getUdalerria().getKode()) == null) {
			System.out.println("Udalerria ez da existitzen");
		} else {
			String sql = "INSERT INTO Calle (Calle, CP, CodMun) VALUES (?,?,?)";
			try (PreparedStatement pst = conn.prepareStatement(sql)) {
				pst.setString(1, kale.getIzena());
				pst.setInt(2, kale.getPk());
				pst.setInt(3, kale.getUdalerria().getKode());
				pst.executeUpdate();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * Kale bat datu basetik ezabatzeko metodoa
	 *
	 * @param izena Ezabatu nahi dugun kalearen izena
	 * @return boolean True erroreak ez badaude, false erroreak badaude
	 */
	public boolean delete(String izena) {
		String sql = "DELETE FROM Calle WHERE Calle=?";
		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setString(1, izena);
			pst.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Kale bat datu basetik eskuratzeko metodoa
	 * 
	 * @param izena Eskuratu nahi dugun kalearen izena
	 * @return Kale Eskuratu den kalea, null ez bada aurkitu
	 */
	public Kale getByIzena(String izena) {
		Kale kale = null;
		String sql = "SELECT * FROM Calle WHERE Calle=?";
		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setString(1, izena);
			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					DAOUdalerri udalDao = new DAOUdalerri();
					int pk = rs.getInt("CP");
					int codMun = rs.getInt("CodMun");
					Udalerri udalerri = udalDao.getByKode(codMun);
					kale = new Kale(izena, pk, udalerri);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return kale;
	}

	/**
	 * Kale bat datu basean aldatzeko metodoa
	 * 
	 * @param kale Kalea datu berriekin
	 * @return boolean True erroreak ez badaude, false erroreak badaude
	 */
	public boolean update(Kale kale) {
		if (this.getByIzena(kale.getIzena()) == null) {
			System.out.println("Kalea ez da existitzen");
		} else {
			String sql = "UPDATE Calle SET Calle=?, CP=?, CodMun=? WHERE Calle=?";
			try (PreparedStatement pst = conn.prepareStatement(sql)) {
				pst.setString(1, kale.getIzena());
				pst.setInt(2, kale.getPk());
				pst.setInt(3, kale.getUdalerria().getKode());
				pst.setString(4, kale.getIzena());
				pst.executeUpdate();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

}
