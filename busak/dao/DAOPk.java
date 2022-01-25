package busak.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import busak.objektuak.Pk;
import busak.objektuak.Udalerri;

/**
 * Pk objektuak datu basearekin erlazionatzen duen klasea
 */
public class DAOPk {

	/**
	 * Datu basearekiko konexioa
	 */
	Connection conn;

	/**
	 * Eraikitzailea
	 */
	public DAOPk() {
		super();
		conn = ConnectionManager.getConnection();
	}

	/**
	 * Pk bat datu basean sartzeko metodoa
	 *
	 * @param pk Sartu nahi dugun pka
	 * @return boolean True erroreak ez badaude, false erroreak badaude
	 */
	public boolean insert(Pk pk) {
		DAOUdalerri udalDao = new DAOUdalerri();
		if (udalDao.getByKode(pk.getUdalerria().getKode()) == null) {
			System.out.println("Udalerria ez da existitzen");
		} else {
			String sql = "INSERT INTO CodigoPostal (CP, CodMun) VALUES (?,?)";
			try (PreparedStatement pst = conn.prepareStatement(sql)) {
				pst.setInt(1, pk.getPk());
				pst.setInt(2, pk.getUdalerria().getKode());
				pst.executeUpdate();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * Pk bat datu basetik ezabatzeko metodoa
	 *
	 * @param kodea Ezabatu nahi dugun pkaren kodea
	 * @return boolean True erroreak ez badaude, false erroreak badaude
	 */
	public boolean delete(int kodea) {
		String sql = "DELETE FROM CodigoPostal WHERE CP=?";
		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setInt(1, kodea);
			pst.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Pk bat datu basetik eskuratzeko metodoa
	 * 
	 * @param kodea Eskuratu nahi dugun pkaren kodea
	 * @return Pk Eskuratu den pka, null ez bada aurkitu
	 */
	public Pk getByKode(int kodea) {
		Pk pk = null;
		String sql = "SELECT * FROM CodigoPostal NATURAL JOIN Municipio WHERE CP=?";
		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setInt(1, kodea);
			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					int codMun = rs.getInt("CodMun");
					String izena = rs.getString("Nombre");
					Udalerri udalerri = new Udalerri(codMun,izena);
					pk = new Pk(kodea, udalerri);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pk;
	}

	/**
	 * Pk bat datu basean aldatzeko metodoa
	 * 
	 * @param pk Pk datu berriekin
	 * @return boolean True erroreak ez badaude, false erroreak badaude
	 */
	public boolean update(Pk pk) {
		if (this.getByKode(pk.getPk()) == null) {
			System.out.println("Pk ez da existitzen");
		} else {
			String sql = "UPDATE CodigoPostal SET CP=?, CodMun=? WHERE CP=?";
			try (PreparedStatement pst = conn.prepareStatement(sql)) {
				pst.setInt(1, pk.getPk());
				pst.setInt(2, pk.getUdalerria().getKode());
				pst.setInt(3, pk.getPk());
				pst.executeUpdate();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

}
