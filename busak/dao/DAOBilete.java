package busak.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import busak.objektuak.Bilete;

public class DAOBilete {

	Connection conn;

	/**
	 * Constructor vacio
	 */
	public DAOBilete() {
		super();
		conn = ConnectionManager.getConnection();
	}

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
					bilete.setKode(generatedKeys.getInt(1));
				} else {
					System.out.println("No se ha podido generar el codigo");
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bilete.getKode();
	}

	public void delete(int kode) {
		String sql = "DELETE FROM Billete WHERE CodBil=?";
		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setInt(1, kode);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Bilete getByKode(int kode) {
		Bilete bilete = null;
		String sql = "SELECT FechaInicio, DNI, B.CodLin, OrdenEmp, OrdenTer, round(PVPU*abs(OrdenTer-OrdenEmp), 2) 'PVP' "
				+ "FROM Billete B JOIN Parada P ON B.CodLin=P.CodLin AND B.OrdenEmp=P.Orden "
				+ "JOIN Linea L ON B.CodLin=L.CodLin "
				+ "WHERE CodBil=?;";
		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setInt(1, kode);
			try (ResultSet rs = pst.executeQuery()) {

				bilete = new Bilete();
				bilete.setKode(kode);
				bilete.setHasData(rs.getTimestamp("FechaInicio").toLocalDateTime());
				bilete.setNan(rs.getString("DNI"));
				int kodeLin = rs.getInt("CodLin");
				int ordenEmp = rs.getInt("OrdenEmp");
				int ordenTer = rs.getInt("OrdenTer");

				DAOGeltoki daoGeltoki = new DAOGeltoki();
				bilete.setHasGeltoki(daoGeltoki.getByKode(kodeLin, ordenEmp));
				bilete.setBukGeltoki(daoGeltoki.getByKode(kodeLin, ordenTer));

				DAOLinea daoLinea = new DAOLinea();

				bilete.setBukData(bilete.getHasData()
						.plusMinutes(daoLinea.getByKode(kodeLin).bidaiDenbora(ordenEmp, ordenTer)));

				bilete.setOrdaintzekoa(rs.getFloat("PVP"));
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

	public void update(Bilete bilete) {
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
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

}
