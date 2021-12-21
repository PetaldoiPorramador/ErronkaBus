package busak.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DAOKale {

	Connection conn;

	/**
	 * Constructor vacio
	 */
	public DAOKale() {
		super();
		conn = ConnectionManager.getConnection();
	}

	public void insert(String kale) {
		try {
			String sql = "INSERT INTO Calle (Calle, CP, CodMun) VALUES (?,?,?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, kale.getIzena());
			pst.setInt(2, kale.getPk());
			pst.setInt(3, kale.getUdalerria().getKode());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
