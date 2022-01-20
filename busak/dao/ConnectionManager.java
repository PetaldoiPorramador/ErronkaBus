package busak.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Datu basearekiko conexioa kudeatzeko clasea
 */
public class ConnectionManager {

	/**
	 * Conexioa egiteko erabili behar den drivera
	 */
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	/**
	 * Datu baseko URL
	 */
	private static final String URL = "jdbc:mysql://erronkabus.ddns.net:3306/ErronkaBus";
	/**
	 * Datu baseko erabiltzaile izena
	 */
	private static final String USER = "dam";
	/**
	 * Datu baseko pasahitza
	 */
	private static final String PASS = "elorrieta";

	static private Connection conn = null;

	/**
	 * Eraikitzaile pribatua, ezin da kanpotik erabili
	 */
	private ConnectionManager() {
		super();
	}

	/**
	 * Datu basearekiko konexioa hartzeko metodoa
	 * 
	 * @return Connection Datu basearekiko konexioa
	 */
	static public Connection getConnection() {
		if (conn == null) {
			try {
				Class.forName(DRIVER);
			} catch (ClassNotFoundException e) {
				System.out.println("Drivera ez da aurkitu");
			}
			// Conexioa sortzen da
			try {
				conn = DriverManager.getConnection(URL, USER, PASS);
			} catch (SQLException e) {
				System.out.println("Ezin izan da konexioa ezarri");
			}
		}
		return conn;
	}

	/**
	 * Datu basearekiko konexia ixten du
	 */
	public static void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
