package busak.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;

import busak.objektuak.Geltoki;
import busak.objektuak.Linea;

/**
 * Linea objektuak datu basearekin erlazionatzen duen klasea
 */
public class DAOLinea {

	/**
	 * Datu basearekiko konexioa
	 */
	Connection conn;

	/**
	 * Eraikitzailea
	 */
	public DAOLinea() {
		super();
		conn = ConnectionManager.getConnection();
	}

	/**
	 * Linea bat datu basean sartzeko metodoa
	 * 
	 * @param linea Sartu nahi dugun linea
	 * @return boolean True erroreak ez badaude, false erroreak badaude
	 */
	public boolean insert(Linea linea) {
		String sql = "INSERT INTO Linea (CodLin, Nombre, HoraInicioAsc, HoraFinAsc, HoraInicioDsc, HoraFinDsc, PVPU, Frecuencia) VALUES (?,?,?,?,?,?,?,?)";
		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setInt(1, linea.getKodea());
			pst.setString(2, linea.getIzena());
			pst.setTime(3, Time.valueOf(linea.getHasOrdGor()));
			pst.setTime(4, Time.valueOf(linea.getBukOrdGor()));
			pst.setTime(5, Time.valueOf(linea.getHasOrdBer()));
			pst.setTime(6, Time.valueOf(linea.getBukOrdBer()));
			pst.setFloat(7, linea.getPvpu());
			pst.setInt(8, linea.getMaiztasuna());
			pst.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Linea bat datu basetik ezabatzeko metodoa
	 * 
	 * @param kodea Ezabatu nahi dugun linearen kodea
	 * @return boolean True erroreak ez badaude, false erroreak badaude
	 */
	public boolean delete(int kodea) {
		String sql = "DELETE FROM Linea WHERE CodLin=?";
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
	 * Linea bat datu basetik eskuratzeko metodoa
	 * 
	 * @param kode Eskuratu nahi dugun linearen kodea
	 * @return Linea Eskuratu den linea, null ez bada aurkitu
	 */
	public Linea getByKode(int kode) {
		Linea linea = null;
		DAOGeltoki daoGeltoki = new DAOGeltoki();
		String sql = "SELECT * FROM Linea WHERE CodLin=?";
		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setInt(1, kode);
			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					String izena = rs.getString("Nombre");
					LocalTime hasOrdGor = rs.getTime("HoraInicioAsc").toLocalTime();
					LocalTime bukOrdGor = rs.getTime("HoraFinAsc").toLocalTime();
					LocalTime hasOrdBer = rs.getTime("HoraInicioDsc").toLocalTime();
					LocalTime bukOrdBer = rs.getTime("HoraFinDsc").toLocalTime();
					float pvpu = rs.getFloat("PVPU");
					int maiztasuna = rs.getInt("Frecuencia");
					ArrayList<Geltoki> geltokienLista = daoGeltoki.getAll(kode);
					linea = new Linea(kode, izena, hasOrdGor, bukOrdGor, hasOrdBer, bukOrdBer, pvpu, maiztasuna,
							geltokienLista);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return linea;
	}

	/**
	 * Linea guztiak datu basetik eskuratzeko metodoa
	 * 
	 * @return ArrayList<Linea> Linea guztiak
	 */
	public ArrayList<Linea> getAll() {
		ArrayList<Linea> lineak = new ArrayList<Linea>();
		DAOGeltoki daoGeltoki = new DAOGeltoki();
		String sql = "SELECT * FROM Linea";
		try (PreparedStatement pst = conn.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {
			while (rs.next()) {
				Linea linea = new Linea();
				linea.setKodea(rs.getInt("CodLin"));
				linea.setIzena(rs.getString("Nombre"));
				linea.setHasOrdGor(rs.getTime("HoraInicioAsc").toLocalTime());
				linea.setBukOrdGor(rs.getTime("HoraFinAsc").toLocalTime());
				linea.setHasOrdBer(rs.getTime("HoraInicioDsc").toLocalTime());
				linea.setBukOrdBer(rs.getTime("HoraFinDsc").toLocalTime());
				linea.setPvpu(rs.getFloat("PVPU"));
				linea.setMaiztasuna(rs.getInt("Frecuencia"));
				linea.setGeltokiak(daoGeltoki.getAll(linea.getKodea()));
				lineak.add(linea);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lineak;
	}

	/**
	 * Linea bat datu basean aldatzeko metodoa
	 * 
	 * @param linea Linea datu berriekin
	 * @return boolean True erroreak ez badaude, false erroreak badaude
	 */
	public boolean update(Linea linea) {
		if (this.getByKode(linea.getKodea()) == null) {
			System.out.println("Linea ez da existitzen");
		} else {
			String sql = "UPDATE Linea SET CodLin=? Nombre=?, HoraInicioAsc=?, HoraFinAsc=?, HoraInicioDsc=?, HoraFinDsc=?, PVPU=?, Frecuencia=? WHERE CodLin=?";
			try (PreparedStatement pst = conn.prepareStatement(sql)) {
				pst.setInt(1, linea.getKodea());
				pst.setString(2, linea.getIzena());
				pst.setTime(3, Time.valueOf(linea.getHasOrdGor()));
				pst.setTime(4, Time.valueOf(linea.getBukOrdGor()));
				pst.setTime(5, Time.valueOf(linea.getHasOrdBer()));
				pst.setTime(6, Time.valueOf(linea.getBukOrdBer()));
				pst.setFloat(7, linea.getPvpu());
				pst.setInt(8, linea.getMaiztasuna());
				pst.setInt(9, linea.getKodea());
				pst.executeUpdate();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}
