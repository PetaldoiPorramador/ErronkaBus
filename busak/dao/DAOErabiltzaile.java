package busak.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import busak.objektuak.Erabiltzaile;

/**
 * Erabiltzaile objektuak datu basearekin erlazionatzen duen klasea
 */
public class DAOErabiltzaile {

	/**
	 * Datu basearekiko konexioa
	 */
	Connection conn;

	/**
	 * Eraikitzailea
	 */
	public DAOErabiltzaile() {
		super();
		conn = ConnectionManager.getConnection();
	}

	/**
	 * Erabiltzaile bat datu basean sartzeko metodoa
	 *
	 * @param erabiltzaile Sartu nahi dugun metodoa
	 * @return boolean True erroreak ez badaude, false erroreak badaude
	 */
	public boolean insert(Erabiltzaile erabiltzaile) {
		String sql = "INSERT INTO Cliente (DNI, NomApe, Pass) VALUES (?,?,MD5(?))";
		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setString(1, erabiltzaile.getNanAiz());
			pst.setString(2, erabiltzaile.getIzenAbizenak());
			pst.setString(3, erabiltzaile.getPasahitza());
			pst.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Erabiltzaile bat datu basetik ezabatzeko metodoa
	 * 
	 * @param nan Ezabatu nahi dugun erabiltzailearen nan
	 * @return boolean True erroreak ez badaude, false erroreak badaude
	 */
	public boolean delete(String nan) {
		String sql = "DELETE FROM Cliente WHERE DNI=?";
		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setString(1, nan);
			pst.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Erabiltzaile bat datu basetik eskuratzeko metodoa
	 * 
	 * @param nan Eskuratu nahi dugun erabiltzailearen nan
	 * @return Erabiltzaile Eskuratu den erabiltzailea, null ez bada aurkitu
	 */
	public Erabiltzaile getByNan(String nan) {
		Erabiltzaile erabiltzaile = null;
		String sql = "SELECT * FROM Cliente WHERE DNI=?";
		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setString(1, nan);
			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					String izena = rs.getString("NomApe");
					String pasahitza = rs.getString("Pass");
					erabiltzaile = new Erabiltzaile(nan, izena, pasahitza);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return erabiltzaile;
	}

	/**
	 * Erabiltzaile bat datu basetik eskuratzeko metodoa
	 * 
	 * @param nan Eskuratu nahi dugun erabiltzailearen nan
	 * @param pasahitza Eskuratu nahi dugun erabiltzailearen pasahitza
	 * @return Erabiltzaile Eskuratu den erabiltzailea, null ez bada aurkitu
	 */
	public Erabiltzaile getByNanPass(String nan, String pasahitza) {
		Erabiltzaile erabiltzaile = null;
		String sql = "SELECT * FROM Cliente WHERE DNI=? AND Pass=MD5(?)";
		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setString(1, nan);
			pst.setString(2, pasahitza);
			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					String izena = rs.getString("NomApe");
					erabiltzaile = new Erabiltzaile(nan, izena, pasahitza);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return erabiltzaile;
	}

	/**
	 * Erabiltzaile bat datu basean aldatzeko metodoa
	 * 
	 * @param erabilzaile Erabiltzailea datu berriekin
	 * @return boolean True erroreak ez badaude, false erroreak badaude
	 */
	public boolean update(Erabiltzaile erabiltzaile) {
		if (this.getByNan(erabiltzaile.getNanAiz()) == null) {
			System.out.println("Erabiltzailea ez da existitzen");
		} else {
			String sql = "UPDATE Cliente SET NomApe=?, Pass=MD5(?) WHERE DNI=?";
			try (PreparedStatement pst = conn.prepareStatement(sql)) {
				pst.setString(1, erabiltzaile.getIzenAbizenak());
				pst.setString(2, erabiltzaile.getPasahitza());
				pst.setString(3, erabiltzaile.getNanAiz());
				pst.executeUpdate();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

}
