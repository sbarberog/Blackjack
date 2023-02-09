package modelo;

import java.sql.Date;
import java.util.Objects;

public class Jugador {

	private int idJugador;
	private String nombre;
	private Date fechaInicial;
	
	public Jugador() {
		this.setIdJugador(0);
		this.setNombre("");
		this.fechaInicial = null;
	}
	
	public Jugador(int idJugador, String nombre, Date fechaInicial) {
		this.setIdJugador(idJugador);
		this.setNombre(nombre);
		this.fechaInicial = fechaInicial;
	}
	
	public Jugador(String nombre) {
		this.setNombre(nombre);
	}

	public int getIdJugador() {
		return idJugador;
	}

	public void setIdJugador(int idJugador) {
		if(idJugador>=0)
			this.idJugador = idJugador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		if(nombre.length()<=30)
			this.nombre = nombre;
	}

	public Date getFechaInicial() {
		return fechaInicial;
	}

	public void setFechaInicial(Date fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idJugador, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Jugador other = (Jugador) obj;
		return idJugador == other.idJugador && Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return "Jugador [idJugador=" + idJugador + ", nombre=" + nombre + ", fechaInicial=" + fechaInicial + "]";
	}
	
	
}
