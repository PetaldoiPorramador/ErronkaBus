package busak.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import busak.objektuak.Bilete;

/**
 * Bilete objektuak datu basearekin erlazionatzen duen klasea
 */
public class DAOBilete {

	/**
	 * Datu basearekiko konexioa
	 */
	Connection conn;

	/**
	 * Eraikitzailea
	 */
	public DAOBilete() {
		super();
		conn = ConnectionManager.getConnection();
	}

	/**
	 * Bilete bat datu basean sartzeko metodoa
	 *
	 * @param bilete Sartu nahi dugun biletea
	 * @return int Sartutako biletearen kodea, -1 ez bada sartzen
	 */
	public int insert(Bilete bilete) {
		String sql = "INSERT INTO Billete (FechaInicio, DNI, CodLin, OrdenEmp, OrdenTer) VALUES (?,?,?,?,?)";
		try (PreparedStatement pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
			pst.setTimestamp(1, Timestamp.valueOf(bilete.getHasData()));
			pst.setString(2, bilete.getNan());
			pst.setInt(3, bilete.getHasGeltoki().getLineaKode());
			pst.setInt(4, bilete.getHasGeltoki().getOrden());
			pst.setInt(5, bilete.getBukGeltoki().getOrden());
			pst.executeUpdate();
			try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					return generatedKeys.getInt("CodBil");
				} else {
					System.out.println("No se ha podido generar el codigo");
				}
			} catch (SQLException e) {
				System.out.println("Error al generar el codigo");
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * Bilete bat datu basetik ezabatzeko metodoa
	 * 
	 * @param kode Ezabatu nahi dugun biletearen kodea
	 * @return boolean True erroreak ez badaude, false erroreak badaude
	 */
	public boolean delete(int kode) {
		String sql = "DELETE FROM Billete WHERE CodBil=?";
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
	 * Bilete bat datu basetik eskuratzeko metodoa
	 * 
	 * @param kode Eskuratu nahi dugun biletearen kodea
	 * @return Bilete Eskuratu den biletea, null ez bada aurkitu
	 */
	public Bilete getByKode(int kode) {
		Bilete bilete = null;
		String sql = "SELECT FechaInicio, DNI, CodLin, OrdenEmp, OrdenTer, PVP, FechaFin FROM VistaBillete WHERE CodBil=?;";
		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setInt(1, kode);
			try (ResultSet rs = pst.executeQuery()) {

				if (rs.next()) {
					bilete = new Bilete();
					bilete.setKode(kode);
					bilete.setHasData(rs.getTimestamp("FechaInicio").toLocalDateTime());
					bilete.setNan(rs.getString("DNI"));
					bilete.setOrdaintzekoa(rs.getFloat("PVP"));
					bilete.setBukData(rs.getTimestamp("FechaFin").toLocalDateTime());

					int kodeLin = rs.getInt("CodLin");
					int ordenEmp = rs.getInt("OrdenEmp");
					int ordenTer = rs.getInt("OrdenTer");

					DAOGeltoki daoGeltoki = new DAOGeltoki();
					bilete.setHasGeltoki(daoGeltoki.getByKode(kodeLin, ordenEmp));
					bilete.setBukGeltoki(daoGeltoki.getByKode(kodeLin, ordenTer));

				}
			} catch (SQLException e) {
				bilete = null;
				e.printStackTrace();
			}
		} catch (SQLException e) {
			bilete = null;
			e.printStackTrace();
		}
		return bilete;
	}

	/**
	 * Erabiltzaile baten bilete guztiak datu basetik eskuratzeko metodoa
	 * 
	 * @param nan Erabiltzailearen nan
	 * @return ArrayList<Bilete> Erabiltzailearen bilete guztiak
	 */
	public ArrayList<Bilete> getAll(String nan) {
		ArrayList<Bilete> bileteak = new ArrayList<>();
		String sql = "SELECT CodBil, FechaInicio, CodLin, OrdenEmp, OrdenTer, PVP, FechaFin FROM VistaBillete WHERE DNI=?;";
		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setString(1, nan);
			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()) {
					Bilete bilete = new Bilete();
					bilete.setKode(rs.getInt("CodBil"));
					bilete.setHasData(rs.getTimestamp("FechaInicio").toLocalDateTime());
					bilete.setNan(nan);
					bilete.setOrdaintzekoa(rs.getFloat("PVP"));
					bilete.setBukData(rs.getTimestamp("FechaFin").toLocalDateTime());

					int kodeLin = rs.getInt("CodLin");
					int ordenEmp = rs.getInt("OrdenEmp");
					int ordenTer = rs.getInt("OrdenTer");

					DAOGeltoki daoGeltoki = new DAOGeltoki();
					bilete.setHasGeltoki(daoGeltoki.getByKode(kodeLin, ordenEmp));
					bilete.setBukGeltoki(daoGeltoki.getByKode(kodeLin, ordenTer));

					bileteak.add(bilete);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bileteak;
	}

	/**
	 * Bilete bat datu basean aldatzeko metodoa
	 * 
	 * @param bilete Biletea datu berriekin
	 * @return boolean True erroreak ez badaude, false erroreak badaude
	 */
	public boolean update(Bilete bilete) {
		if (this.getByKode(bilete.getKode()) == null) {
			System.out.println("Biletea ez da existitzen");
		} else {
			String sql = "UPDATE Bilete SET CodBil=?, FechaInicio=?, DNI=?, CodLin=?, OrdenEmp=?, OrdenTer=?, WHERE CodBil=?";
			try (PreparedStatement pst = conn.prepareStatement(sql)) {
				pst.setInt(1, bilete.getKode());
				pst.setTimestamp(2, Timestamp.valueOf(bilete.getHasData()));
				pst.setString(3, bilete.getNan());
				pst.setInt(4, bilete.getBukGeltoki().getLineaKode());
				pst.setInt(5, bilete.getHasGeltoki().getOrden());
				pst.setInt(6, bilete.getBukGeltoki().getOrden());
				pst.setInt(7, bilete.getKode());
				pst.executeUpdate();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

}
