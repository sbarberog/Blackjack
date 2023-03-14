/**
 * 
 */
package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Sergio
 * Conexión a la BBDD del juego de Blackjack
 */
public class ConexionBD {

	/**
	 * Conexión a la BBDD del juego de Blackjack
	 */
//	private static final String driverMysql="com.mysql.cj.jdbc.Driver";
	private static final String driverH2="org.h2.Driver";
	private static final String database = "blackjack";
//	private static final String databaseFree = "sql7599426";
//	private static final String usuario = "juego_blackjack";
//	private static final String contrasena = "blackjack";
	private static final String usuarioH2 = "sa";
	private static final String contrasenaH2 = "";
//	private static final String usuarioFree = "sql7599426";
//	private static final String contrasenaFree = "KXbGMrYgad";
//	private static final String url="jdbc:mysql://127.0.0.1:3306/"+database;
	private static final String urlH2= "jdbc:h2:./h2db/"+ database;
//	private static final String urlFree= "http://sql7.freemysqlhosting.net:3306/"+ databaseFree;
	
	private Connection conexion=null;
	
	
	public Connection getConexion() throws ClassNotFoundException, SQLException {
		if (conexion!=null) {
			return conexion;
		}
		
		// Registra el driver de MySQL
		try {
			Class.forName(driverH2);
			
			conexion = DriverManager.getConnection(urlH2, usuarioH2, contrasenaH2);
			System.out.println("Conexion a "+database+" correcta");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver no registrado");
			throw e;
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
