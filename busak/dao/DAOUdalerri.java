package busak.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import busak.objektuak.Udalerri;

/**
 * Udalerri objektuak datu basearekin erlazionatzen duen klasea
 */
public class DAOUdalerri {

	/**
	 * Datu basearekiko konexioa
	 */
	Connection conn;

	/**
	 * Eraikiakitzailea
	 */
	public DAOUdalerri() {
		super();
		conn = ConnectionManager.getConnection();
	}

	/**
	 * Udalerri bat datu basean sartzeko metodoa
	 *
	 * @param udalerri Sartu nahi dugun udalerria
	 * @return boolean True erroreak ez badaude, false erroreak badaude
	 */
	public boolean insert(Udalerri udalerri) {
		String sql = "INSERT INTO Municipio (CodMun, Nombre) VALUES (?,?)";
		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setInt(1, udalerri.getKode());
			pst.setString(2, udalerri.getIzena());
			pst.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Udalerri bat datu basetik ezabatzeko metodoa
	 *
	 * @param kode Ezabatu nahi dugun udalerriaren kodea
	 * @return boolean True erroreak ez badaude, false erroreak badaude
	 */
	public boolean delete(int kode) {
		String sql = "DELETE FROM Municipio WHERE CodMun=?";
		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setInt(1, kode);
			pst.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Udalerri bat datu basetik eskuratzeko metodoa
	 * 
	 * @param kode Eskuratu nahi dugun udalerriaren kodea
	 * @return Udalerri Eskuratu den udalerria, null ez bada aurkitu
	 */
	public Udalerri getByKode(int kode) {
		Udalerri udalerri = null;
		String sql = "SELECT * FROM Municipio WHERE CodMun=?";
		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setInt(1, kode);
			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					String izena = rs.getString("Nombre");
					udalerri = new Udalerri(kode, izena);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return udalerri;
	}

	/**
	 * Udalerri bat datu basean aldatzeko metodoa
	 * 
	 * @param udalerri Udalerria datu berriekin
	 * @return boolean True erroreak ez badaude, false erroreak badaude
	 */
	public boolean update(Udalerri udalerri) {
		if (getByKode(udalerri.getKode()) == null) {
			System.out.println("Udalerria ez da existitzen");
		} else {
			String sql = "UPDATE Municipio SET Nombre=? WHERE CodMun=?";
			try (PreparedStatement pst = conn.prepareStatement(sql)) {
				pst.setString(1, udalerri.getIzena());
				pst.setInt(2, udalerri.getKode());
				pst.executeUpdate();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

}
