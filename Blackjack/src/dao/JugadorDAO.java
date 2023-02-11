/**
 * 
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
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
			res = consulta.executeQuery("select * from datos_jugador");
			
			// Bucle para recorrer todas las filas que devuelve la consulta
			while(res.next()) {
				Jugador j = new Jugador();
				j.setIdJugador(res.getInt("id_jugador"));
				j.setNombre(res.getString("nombre"));
				j.setFechaRegistro(res.getDate("fecha_registro"));
				j.setVictorias(res.getInt("victorias"));
				j.setEmpates(res.getInt("empates"));
				j.setDerrotas(res.getInt("derrotas"));
				j.setPartidasTotales(res.getInt("partidas_totales"));
				
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

    
    public Jugador obtenerDatosJugador(String nombre) {
    	// Obtenemos una conexion a la base de datos.
		Connection con = conexion.getConexion();
		PreparedStatement consulta = null;
		ResultSet res = null;
		Jugador j = new Jugador();
		
		try {
			consulta = con.prepareStatement(
					"select * fron datos_jugador\r\n"
					+ "	   where nombre like ? \r\n"
					+ "    group by id_jugador");
			consulta.setString(1, nombre);
			res=consulta.executeQuery();
			
			// Bucle para recorrer todas las filas que devuelve la consulta
			if (res.next()) {
				j = new Jugador();
				j.setIdJugador(res.getInt("id_jugador"));
				j.setNombre(res.getString("nombre"));
				j.setFechaRegistro(res.getDate("fecha_registro"));
				j.setVictorias(res.getInt("victorias"));
				j.setEmpates(res.getInt("empates"));
				j.setDerrotas(res.getInt("derrotas"));
				j.setPartidasTotales(res.getInt("partidas_totales"));
			}
			
		} catch (SQLException e) {
			System.out.println("Error al intentar obtener jugador: "
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
		return j;
    }

    public Jugador obtenerNuevoJugador(String nombre) {
    	// Obtenemos una conexion a la base de datos.
		Connection con = conexion.getConexion();
		PreparedStatement consulta = null;
		ResultSet res = null;
		Jugador j = new Jugador();
		
		try {
			consulta = con.prepareStatement(
					"select id_jugador,fecha_registro\r\n"
					+ "    from jugadores\r\n"
					+ "	   where nombre like ? ");
			consulta.setString(1, nombre);
			res=consulta.executeQuery();
			
			// Bucle para recorrer todas las filas que devuelve la consulta
			if (res.next()) {
				j.setIdJugador(res.getInt("id_jugador"));
				j.setNombre(nombre);
				j.setFechaRegistro(res.getDate("fecha_registro"));
			}
			
		} catch (SQLException e) {
			System.out.println("Error al intentar obtener la ID del jugador: "
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
		return j;
    }
    
    
    public int insertarJugador(String nombre) throws SQLIntegrityConstraintViolationException, SQLException {
    	// Obtenemos una conexion a la base de datos.
		Connection con = conexion.getConexion();
		PreparedStatement consulta = null;
		int resultado=0;
		
		try {
			consulta = con.prepareStatement("INSERT INTO jugadores (nombre)"
					+ " VALUES (?)");
			
			consulta.setString(1, nombre);
			resultado=consulta.executeUpdate();
		}catch(SQLIntegrityConstraintViolationException e) {
			System.out.println("Error de clave duplicada al realizar la inserción del jugador: "
			        +e.getMessage());
			throw e;
		} catch (SQLException e) {
			System.out.println("Error al realizar la inserción del jugador: "
		        +e.getMessage());
			throw e;
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
