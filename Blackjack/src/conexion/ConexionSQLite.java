/**
 * 
 */
package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.DatabaseMetaData;

/**
 * @author David
 *
 */
public class ConexionSQLite {

	/**
	 * Conexión a la BBDD del juego de Blackjack
	 */
	private static final String database = "blackjack";
	private static final String usuario = "juego_blackjack";
	private static final String contrasena = "blackjack";
//	private static final String url="jdbc:mysql://127.0.0.1:3306/"+database;
	private static final String urlSQLite="jdbc:sqlite:sqlite/db/"+database+".db";
	
	private Connection conexion=null;
	
	
	public Connection getConexion() throws ClassNotFoundException, SQLException {
		if (conexion!=null) {
			return conexion;
		}
		
		// Registra el driver de MySQL
		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conexion = DriverManager.getConnection(urlSQLite);
			
			System.out.println("Conexion a "+database+" correcta");
		} catch (SQLException e) {
			System.out.println("Error SQLException: "+e.getMessage());
			throw e;
		}
		return conexion;
	}
	
	public void desconectar() {
		try {
			conexion.close();
			conexion=null;
		} catch (SQLException e) {
			System.out.println("Error cerrrando la conexion "+ e.getMessage());
		}
	}

}
