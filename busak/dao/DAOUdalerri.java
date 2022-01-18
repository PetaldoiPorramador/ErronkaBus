package busak.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import busak.objektuak.Udalerri;

public class DAOUdalerri {

	Connection conn;

	/**
	 * Constructor vacio
	 */
	public DAOUdalerri() {
		super();
		conn = ConnectionManager.getConnection();
	}

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

	public void delete(int kode) {
		String sql = "DELETE FROM Municipio WHERE CodMun=?";
		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setInt(1, kode);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insert(Udalerri udalerri) {
		String sql = "INSERT INTO Municipio (CodMun, Nombre) VALUES (?,?)";
		try (PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setInt(1, udalerri.getKode());
			pst.setString(2, udalerri.getIzena());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(Udalerri udalerri) {
		if (getByKode(udalerri.getKode()) == null) {
			System.out.println("Udalerria ez da existitzen");
		} else {
			String sql = "UPDATE Municipio SET Nombre=? WHERE CodMun=?";
			try (PreparedStatement pst = conn.prepareStatement(sql)) {
				pst.setString(1, udalerri.getIzena());
				pst.setInt(2, udalerri.getKode());
				pst.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
