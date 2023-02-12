package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreacionSQLite {

	public static void main(String[] args) {
		
		createNewTable();

	}
	
	public static void createNewTable() {
        // SQLite connection string
        String url = "jdbc:sqlite:sqlite/db/blackjack.db";
        
        // SQL statement for creating a new table
        String sql = "create view datos_jugador\r\n"
        		+ "as select id_jugador,nombre,fecha_registro, ifnull(sum(resultado='V'),0) victorias, ifnull(sum(resultado='E'),0) empates, \r\n"
        		+ "    ifnull(sum(resultado='D'),0) derrotas, count(id_partida) partidas_totales \r\n"
        		+ "    from jugadores left join partidas using (id_jugador)\r\n"
        		+ "    group by id_jugador\r\n"
        		+ "    order by partidas_totales desc;";
        
        try {
        	Connection conn = DriverManager.getConnection(url);
        	System.out.println("Conexion a SQLite correcta");
            Statement stmt = conn.createStatement();
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}
