package busak.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import busak.objektuak.Linea;

public class DAOLinea {

	Connection conn;

	/**
	 * Constructor vacio
	 */
	public DAOLinea() {
		super();
		conn = ConnectionManager.getConnection();
	}

	public void insert(Linea linea) {
		try {
			String sql = "INSERT INTO Linea (CodLin, Nombre, HoraInicioAsc, HoraFinAsc, HoraInicioDsc, HoraFinDsc, PVPU, Frecuencia) VALUES (?,?,?,?,?,?,?,?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, linea.getKodea());
			pst.setString(2, linea.getIzena());
			pst.setTime(3, Time.valueOf(linea.getHasOrdGor()));
			pst.setTime(4, Time.valueOf(linea.getBukOrdGor()));
			pst.setTime(5, Time.valueOf(linea.getHasOrdBer()));
			pst.setTime(6, Time.valueOf(linea.getBukOrdBer()));
			pst.setFloat(7, linea.getPvpu());
			pst.setInt(8, linea.getMaiztasuna());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(int kodea) {
		try {
			String sql = "DELETE FROM Linea WHERE CodLin=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, kodea);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Linea getByKode(int kode) {
		Linea linea = new Linea();
		try {
			String sql = "SELECT * FROM Linea WHERE CodLin=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, kode);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				linea.setKodea(kode);
				linea.setIzena(rs.getString("Nombre"));
				linea.setHasOrdGor(rs.getTime("HoraInicioAsc").toLocalTime());
				linea.setBukOrdGor(rs.getTime("HoraFinAsc").toLocalTime());
				linea.setHasOrdBer(rs.getTime("HoraInicioDsc").toLocalTime());
				linea.setBukOrdBer(rs.getTime("HoraFinDsc").toLocalTime());
				linea.setPvpu(rs.getFloat("PVPU"));
				linea.setMaiztasuna(rs.getInt("Frecuencia"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return linea;
	}

	public void update(Linea linea) {
		if (this.getByKode(linea.getKodea()) == null) {
			System.out.println("Linea ez da existitzen");
		} else {
			try {
				String sql = "UPDATE Linea SET CodLin=? Nombre=?, HoraInicioAsc=?, HoraFinAsc=?, HoraInicioDsc=?, HoraFinDsc=?, PVPU=?, Frecuencia=? WHERE CodLin=?";
				PreparedStatement pst = conn.prepareStatement(sql);
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
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
