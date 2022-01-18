package busak.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import busak.objektuak.Geltoki;
import busak.objektuak.Kale;

public class DAOGeltoki {

	Connection conn;

	/**
	 * Constructor vacio
	 */
	public DAOGeltoki() {
		super();
		conn = ConnectionManager.getConnection();
	}

	public void insert(Geltoki geltoki) {
		DAOLinea lineaDao = new DAOLinea();
		if (lineaDao.getByKode(geltoki.getLineaKode()) == null) {
			System.out.println("Linea ez da existitzen");
		} else {
			String sql = "INSERT INTO Parada (CodLin, Orden, Nombre, Numero, TiempoE, Calle) VALUES (?,?,?,?,?,?)";
			try (PreparedStatement pst = conn.prepareStatement(sql)) {
				pst.setInt(1, geltoki.getLineaKode());
				pst.setInt(2, geltoki.getOrden());
				pst.setString(3, geltoki.getIzena());
				pst.setInt(4, geltoki.getZenbakia());
				pst.setInt(5, geltoki.getDenboraBzBs());
				pst.setString(6, geltoki.getKalea().getIzena());
				pst.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void delete(int lineaKode, int orden) {
		String sql = "DELETE FROM Parada WHERE CodLin=? AND Orden=?";
		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setInt(1, lineaKode);
			pst.setInt(2, orden);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Geltoki getByKode(int lineaKode, int orden) {
		Geltoki geltoki = null;
		String sql = "SELECT * FROM Parada WHERE CodLin=? AND Orden=?";
		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setInt(1, lineaKode);
			pst.setInt(2, orden);
			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					DAOKale kaleDao = new DAOKale();
					String izena = rs.getString("Nombre");
					int zenbakia = rs.getInt("Numero");
					int denboraBzBs = rs.getInt("TiempoE");
					Kale kale = kaleDao.getByIzena(rs.getString("Calle"));
					geltoki = new Geltoki(lineaKode, orden, izena, zenbakia, denboraBzBs, kale);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return geltoki;
	}

	public ArrayList<Geltoki> getAll(int lineaKode) {
		DAOKale kaleDao = new DAOKale();
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
					geltoki.setKalea(kaleDao.getByIzena(rs.getString("Calle")));
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

	public void update(Geltoki geltoki) {
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
				pst.setString(6, geltoki.getKalea().getIzena());
				pst.setInt(7, geltoki.getLineaKode());
				pst.setInt(8, geltoki.getOrden());
				pst.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
