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
import modelo.Partida;

/**
 * Clase que implementa un CRUD de la base batos (Create, Read, update y delete)
 */
public class PartidaDAO {

	private ConexionBD conexion;

	public PartidaDAO() {
		this.conexion = new ConexionBD();
	}

	public ArrayList<Partida> obtenerPartidas() {
		// Obtenemos una conexion a la base de datos.
		Connection con = conexion.getConexion();
		Statement consulta = null;
		ResultSet res = null;
		ArrayList<Partida> lista = new ArrayList<Partida>();

		try {
			consulta = con.createStatement();
			res = consulta.executeQuery("select * from partidas");

			// Bucle para recorrer todas las filas que devuelve la consulta
			while (res.next()) {
				Partida p = new Partida();
				p.setIdPartida(res.getInt("id_partida"));
				p.setIdJugador(res.getInt("id_jugador"));
				p.setPuntosJ(res.getInt("puntos_jugador"));
				p.setPuntosB(res.getInt("puntos_banca"));
				p.setTimestamp(res.getTimestamp("time_stamp"));

				lista.add(p);
			}

		} catch (SQLException e) {
			System.out.println("Error al realizar la consulta sobre partidas: " + e.getMessage());
		} finally {
			try {
				res.close();
				consulta.close();
				conexion.desconectar();
			} catch (SQLException e) {
				System.out.println("Error al liberar recursos: " + e.getMessage());
			} catch (Exception e) {

			}
		}
		return lista;
	}

//    public Centro obtenerCentro(int cod_centro) {
//    	// Obtenemos una conexion a la base de datos.
//		Connection con = conexion.getConexion();
//		PreparedStatement consulta = null;
//		ResultSet resultado = null;
//		Centro c=null;
//		
//		try {
//			consulta = con.prepareStatement("select * from centros"
//					+ " where cod_centro = ?");
//			consulta.setInt(1, cod_centro);
//			resultado=consulta.executeQuery();
//			
//			// Bucle para recorrer todas las filas que devuelve la consulta
//			if (resultado.next()) {
//				String nombre = resultado.getString("nombre");
//				String direccion = resultado.getString("direccion");
//				
//				c = new Centro(cod_centro, nombre,direccion);
//			}
//			
//		} catch (SQLException e) {
//			System.out.println("Error al realizar la consulta sobre centros: "
//		         +e.getMessage());
//		} finally {
//			try {
//				resultado.close();
//				consulta.close();
//				conexion.desconectar();
//			} catch (SQLException e) {
//				System.out.println("Error al liberar recursos: "+e.getMessage());
//			} catch (Exception e) {
//				
//			}
//		}
//		return c;
//    }
//
//
	public int insertarPartida(Partida p) {
		// Obtenemos una conexion a la base de datos.
		Connection con = conexion.getConexion();
		PreparedStatement consulta = null;
		int resultado = 0;

		try {
			consulta = con.prepareStatement(
					"INSERT INTO partidas (id_partida, id_jugador, puntos_jugador, puntos_banca, resultado)"
							+ " VALUES (?,?,?,?,?)");

			consulta.setInt(1, p.getIdPartida());
			consulta.setInt(2, p.getIdJugador());
			consulta.setInt(3, p.getPuntosJ());
			consulta.setInt(4, p.getPuntosB());
			consulta.setString(5, p.getResultadoEnum());
			resultado = consulta.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error al realizar la inserción de la partida: " + e.getMessage());
		} finally {
			try {
				consulta.close();
				conexion.desconectar();
			} catch (SQLException e) {
				System.out.println("Error al liberar recursos: " + e.getMessage());
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
