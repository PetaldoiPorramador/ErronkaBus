package busak.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import busak.objektuak.Geltoki;
import busak.objektuak.Pk;
import busak.objektuak.Udalerri;

/**
 * Geltoki objektuak datu basearekin erlazionatzen duen klasea
 */
public class DAOGeltoki {

	/**
	 * Datu basearekiko konexioa
	 */
	Connection conn;

	/**
	 * Eraikitzailea
	 */
	public DAOGeltoki() {
		super();
		conn = ConnectionManager.getConnection();
	}

	/**
	 * Geltoki bat datu basean sartzeko metodoa
	 * 
	 * @param geltoki Sartu nahi dugun geltokia
	 * @return boolean True erroreak ez badaude, false erroreak badaude
	 */
	public boolean insert(Geltoki geltoki) {
		DAOLinea lineaDao = new DAOLinea();
		if (lineaDao.getByKode(geltoki.getLineaKode()) == null) {
			System.out.println("Linea ez da existitzen");
		} else {
			String sql = "INSERT INTO Parada (CodLin, Orden, Nombre, Numero, TiempoE, Calle, CP) VALUES (?,?,?,?,?,?,?)";
			try (PreparedStatement pst = conn.prepareStatement(sql)) {
				pst.setInt(1, geltoki.getLineaKode());
				pst.setInt(2, geltoki.getOrden());
				pst.setString(3, geltoki.getIzena());
				pst.setInt(4, geltoki.getZenbakia());
				pst.setInt(5, geltoki.getDenboraBzBs());
				pst.setInt(6, geltoki.getDenboraBzBs());
				pst.setInt(7, geltoki.getPk().getPk());
				pst.executeUpdate();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * Geltoki bat datu basetik ezabatzeko metodoa
	 * 
	 * @param lineaKode Ezabatu nahi dugun geltokiaren linearen kodea
	 * @param orden     Ezabatu nahi dugun geltokiaren ordena
	 * @return boolean True erroreak ez badaude, false erroreak badaude
	 */
	public boolean delete(int lineaKode, int orden) {
		String sql = "DELETE FROM Parada WHERE CodLin=? AND Orden=?";
		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setInt(1, lineaKode);
			pst.setInt(2, orden);
			pst.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Geltoki bat datu basetik eskuratzeko metodoa
	 * 
	 * @param lineaKode Eskuratu nahi dugun geltokiaren linearen kodea
	 * @param orden     Eskuratu nahi dugun geltokiaren ordena
	 * @return Geltoki Eskuratu den objektua, null ez bada aurkitu
	 */
	public Geltoki getByKode(int lineaKode, int orden) {
		Geltoki geltoki = null;
		String sql = "SELECT * FROM Parada P JOIN CodigoPostal C USING (CP) NATURAL JOIN Municipio M WHERE CodLin=? AND Orden=?";
		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setInt(1, lineaKode);
			pst.setInt(2, orden);
			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					String izena = rs.getString("P.Nombre");
					int zenbakia = rs.getInt("Numero");
					int denboraBzBs = rs.getInt("TiempoE");
					String kalea =rs.getString("Calle");
					int pKode = rs.getInt("CP");
					int udaKode = rs.getInt("CodMun");
					String udaIzen = rs.getString("M.Nombre");
					Udalerri uda = new Udalerri(udaKode, udaIzen);
					Pk pk = new Pk(pKode, uda);
					geltoki = new Geltoki(lineaKode, orden, izena, zenbakia, denboraBzBs, pk, kalea);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return geltoki;
	}

	/**
	 * Linea baten geltoki guztiak datu basetik eskuratzeko metodoa
	 * 
	 * @param lineaKode Eskuratu nahi dugun geltokien linearen kodea
	 * @return {@code ArrayList<Geltoki>} guztiak dituen ArrayLista
	 */
	public ArrayList<Geltoki> getAll(int lineaKode) {
		DAOPk kaleDao = new DAOPk();
		ArrayList<Geltoki> lGeltokiak = new ArrayList<Geltoki>();
		String sql = "SELECT * FROM Parada WHERE CodLin=? ORDER BY Orden";
		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setInt(1, lineaKode);
			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()) {
					Geltoki geltoki = new Geltoki();
					geltoki.setLineaKode(lineaKode);
					geltoki.setOrden(rs.getInt("Orden"));
					geltoki.setIzena(rs.getString("Nombre"));
					geltoki.setZenbakia(rs.getInt("Numero"));
					geltoki.setDenboraBzBs(rs.getInt("TiempoE"));
					//geltoki.setKalea(kaleDao.getByIzena(rs.getString("Calle")));
					lGeltokiak.add(geltoki);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lGeltokiak;
	}

	/**
	 * Geltoki bat datu basean aldatzeko metodoa
	 * 
	 * @param geltoki Geltokia datu berriekin
	 * @return boolean True erroreak ez badaude, false erroreak badaude
	 */
	public boolean update(Geltoki geltoki) {
		if (this.getByKode(geltoki.getLineaKode(), geltoki.getOrden()) == null) {
			System.out.println("Geltokia ez da existitzen");
		} else {
			String sql = "UPDATE Parada SET CodLin=?, Orden=?, Nombre=?, Numero=?, TiempoE=?, Calle=? WHERE CodLin=? AND Orden=?";
			try (PreparedStatement pst = conn.prepareStatement(sql)) {
				pst.setInt(1, geltoki.getLineaKode());
				pst.setInt(2, geltoki.getOrden());
				pst.setString(3, geltoki.getIzena());
				pst.setInt(4, geltoki.getZenbakia());
				pst.setInt(5, geltoki.getDenboraBzBs());
				//pst.setString(6, geltoki.getKalea().getIzena());
				pst.setInt(7, geltoki.getLineaKode());
				pst.setInt(8, geltoki.getOrden());
				pst.executeUpdate();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}
