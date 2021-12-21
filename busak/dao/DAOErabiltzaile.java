package busak.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import busak.objektuak.Erabiltzaile;

public class DAOErabiltzaile {

	Connection conn;

	/**
	 * Constructor vacio
	 */
	public DAOErabiltzaile() {
		super();
		conn = ConnectionManager.getConnection();
	}

	public void insert(Erabiltzaile erabiltzaile) {
		try {
			String sql = "INSERT INTO Cliente (DNI, NomApe, Pass) VALUES (?,?,MD5(?))";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, erabiltzaile.getNan());
			pst.setString(2, erabiltzaile.getIzenAbizenak());
			pst.setString(3, erabiltzaile.getPasahitza());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(String nan) {
		try {
			String sql = "DELETE FROM Cliente WHERE DNI=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, nan);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Erabiltzaile getByNan(String nan) {
		Erabiltzaile erabiltzaile = new Erabiltzaile();
		try {
			String sql = "SELECT * FROM Cliente WHERE DNI=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, nan);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				erabiltzaile.setNan(nan);
				erabiltzaile.setIzenAbizenak(rs.getString("NomApe"));
				erabiltzaile.setPasahitza(rs.getString("Pass"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return erabiltzaile;
	}

	public Erabiltzaile getByNanPass(String nan, String pasahitza) {
		Erabiltzaile erabiltzaile = new Erabiltzaile();
		try {
			String sql = "SELECT * FROM Cliente WHERE DNI=? AND Pass=MD5(?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, nan);
			pst.setString(2, pasahitza);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				erabiltzaile.setNan(nan);
				erabiltzaile.setIzenAbizenak(rs.getString("NomApe"));
				erabiltzaile.setPasahitza(rs.getString("Pass"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return erabiltzaile;
	}

	public void update(Erabiltzaile erabiltzaile) {
		if (this.getByNan(erabiltzaile.getNan()) == null) {
			System.out.println("Erabiltzailea ez da existitzen");
		} else {
			try {
				String sql = "UPDATE Cliente SET NomApe=?, Pass=MD5(?) WHERE DNI=?";
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setString(1, erabiltzaile.getIzenAbizenak());
				pst.setString(2, erabiltzaile.getPasahitza());
				pst.setString(3, erabiltzaile.getNan());
				pst.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

}
