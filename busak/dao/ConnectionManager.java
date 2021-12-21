package busak.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Contiene los metodos para gestionar la conexion a base de datos
 * 
 */
public class ConnectionManager {

	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/ErronkaBus";
	private static final String USER = "root";
	private static final String PASS = "root";

	static private Connection conn = null;

	/**
	 * Constructor privado, solo accesible desde la clase
	 */
	private ConnectionManager() {
		super();
	}

	/**
	 * Devuelve la conexion a la base de datos
	 * 
	 * @return Connection la conexion a la base de datos
	 * @throws Exception
	 */
	static public Connection getConnection() {
		if (conn == null) {
			try {
				Class.forName(DRIVER);
			} catch (ClassNotFoundException e) {
				System.out.println("No se ha encontrado el driver");
			}
			// Crear conexion a la base de datos
			try {
				conn = DriverManager.getConnection(URL, USER, PASS);
			} catch (SQLException e) {
				System.out.println("No se ha podido conectar a la base de datos");
			}
		}
		return conn;
	}

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
