/**
 * 
 */
package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author David
 *
 */
public class ConexionBD {

	/**
	 * Conexión a la BBDD del juego de Blackjack
	 */
	private static final String database = "blackjack";
	private static final String usuario = "sa";
	private static final String contrasena = "";
//	private static final String url="jdbc:mysql://127.0.0.1:3306/"+database;
	private static final String url= "jdbc:h2:./h2db/"+ database;
	
	private Connection conexion=null;
	
	
	public Connection getConexion() throws ClassNotFoundException, SQLException {
		if (conexion!=null) {
			return conexion;
		}
		
		// Registra el driver de MySQL
		try {
			Class.forName("org.h2.Driver");
			
			conexion = DriverManager.getConnection(url, usuario, contrasena);
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
