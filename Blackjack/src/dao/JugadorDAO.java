/**
 * 
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conexion.ConexionBD;
import modelo.Jugador;

/**
 * Clase que implementa un CRUD de la base batos
 * (Create, Read, update y delete)
 */
public class JugadorDAO {

	private ConexionBD conexion;
	
    public JugadorDAO() {
        this.conexion = new ConexionBD();
    }


    public ArrayList<Jugador> obtenerJugadores() {
    	// Obtenemos una conexion a la base de datos.
		Connection con = conexion.getConexion();
		Statement consulta = null;
		ResultSet res = null;
		ArrayList<Jugador> lista = new ArrayList<Jugador>();
		
		try {
			consulta = con.createStatement();
			res = consulta.executeQuery("select * from jugadores");
			
			// Bucle para recorrer todas las filas que devuelve la consulta
			while(res.next()) {
				Jugador j = new Jugador();
				j.setIdJugador(res.getInt("id_jugador"));
				j.setNombre(res.getString("nombre"));
				j.setFechaInicial(res.getDate("fecha_inicial"));
				
				lista.add(j);
			}
			
		} catch (SQLException e) {
			System.out.println("Error al realizar la consulta sobre jugadores: "+e.getMessage());
		} finally {
			try {
				res.close();
				consulta.close();
				conexion.desconectar();
			} catch (SQLException e) {
				System.out.println("Error al liberar recursos: "+e.getMessage());
			} catch (Exception e) {
				
			}
		}
		return lista;
    }

    
    public Jugador obtenerJugador(String nombre) {
    	// Obtenemos una conexion a la base de datos.
		Connection con = conexion.getConexion();
		PreparedStatement consulta = null;
		ResultSet res = null;
		Jugador c = new Jugador();
		
		try {
			consulta = con.prepareStatement(
					"select id_jugador, nombre, fecha_inicial, sum(resultado='V') victorias, "
							+ "sum(resultado='E') empates, sum(resultado='D') derrotas, count (id_partida) partidas_totales\n"
					+ " from jugadores join partidas using (id_jugador)\n"
					+ " where nombre like ? \n"
					+ " group by id_jugador");
			consulta.setString(1, nombre);
			res=consulta.executeQuery();
			
			// Bucle para recorrer todas las filas que devuelve la consulta
			if (res.next()) {
				c = new Jugador();
				c.setIdJugador(res.getInt("id_jugador"));
				c.setNombre(res.getString("nombre"));
				c.setFechaInicial(res.getDate("fecha_inicial"));
				c.setVictorias(res.getInt("victorias"));
				c.setEmpates(res.getInt("empates"));
				c.setDerrotas(res.getInt("derrotas"));
				c.setPartidasTotales(res.getInt("partidas_totales"));
			}
			
		} catch (SQLException e) {
			System.out.println("Error al realizar la consulta sobre centros: "
		         +e.getMessage());
		} finally {
			try {
				res.close();
				consulta.close();
				conexion.desconectar();
			} catch (SQLException e) {
				System.out.println("Error al liberar recursos: "+e.getMessage());
			} catch (Exception e) {
				
			}
		}
		return c;
    }
//
//
    public int insertarJugador(Jugador j) {
    	// Obtenemos una conexion a la base de datos.
		Connection con = conexion.getConexion();
		PreparedStatement consulta = null;
		int resultado=0;
		
		try {
			consulta = con.prepareStatement("INSERT INTO jugadores (nombre)"
					+ " VALUES (?)");
			
			consulta.setString(1, j.getNombre());
			resultado=consulta.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error al realizar la inserción del jugador: "
		        +e.getMessage());
		} finally {
			try {
				consulta.close();
				conexion.desconectar();
			} catch (SQLException e) {
				System.out.println("Error al liberar recursos: "+e.getMessage());
			} catch (Exception e) {
				
			}
		}
		return resultado;
    }

//    public int actualizarAutor(Centro centro) {
//    	// Obtenemos una conexion a la base de datos.
//		Connection con = conexion.getConexion();
//		PreparedStatement consulta = null;
//		int resultado=0;
//		
//		try {
//			consulta = con.prepareStatement("UPDATE centros \r\n"
//					+ "SET nombre = ?, direccion = ?\r\n"
//					+ "WHERE cod_centro = ?;");
//			
//			consulta.setString(1, centro.getNombre());
//			consulta.setString(2, centro.getDireccion());
//			consulta.setInt(3, centro.getCod_centro());
//			resultado=consulta.executeUpdate();
//
//		} catch (SQLException e) {
//			System.out.println("Error al realizar la actualizacion de centros: "
//					+e.getMessage());
//		} finally {
//			try {
//				consulta.close();
//				conexion.desconectar();
//			} catch (SQLException e) {
//				System.out.println("Error al liberar recursos: "+e.getMessage());
//			} catch (Exception e) {
//				
//			}
//		}
//		return resultado;
//    }
//
//
//    public int eliminarAutores(Centro centro) {
//    	// Obtenemos una conexion a la base de datos.
//		Connection con = conexion.getConexion();
//		PreparedStatement consulta = null;
//		int resultado=0;
//		
//		try {
//			consulta = con.prepareStatement("DELETE FROM centros\r\n"
//					+ "WHERE cod_centro = ?");
//			
//			consulta.setInt(1, centro.getCod_centro());
//			resultado=consulta.executeUpdate();
//
//		} catch (SQLException e) {
//			System.out.println("Error al realizar el borrado de Centros: "+e.getMessage());
//		} finally {
//			try {
//				consulta.close();
//				conexion.desconectar();
//			} catch (SQLException e) {
//				System.out.println("Error al liberar recursos: "+e.getMessage());
//			} catch (Exception e) {
//				
//			}
//		}
//		return resultado;
//    }

}
