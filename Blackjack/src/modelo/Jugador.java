package modelo;

import java.sql.Date;
import java.util.Objects;

public class Jugador {

	private int idJugador;
	private String nombre;
	private Date fechaInicial;
	private int victorias;
	private int empates;
	private int derrotas;
	private int partidasTotales;
	
	public Jugador() {
		this.setIdJugador(0);
		this.setNombre("");
		this.fechaInicial = null;
		this.victorias=0;
		this.empates=0;
		this.derrotas=0;
		this.partidasTotales=0;
	}
	
	public Jugador(int idJugador, String nombre, Date fechaInicial, int victorias, int empates, int derrotas,
			int partidasTotales) {
		this.setIdJugador(idJugador);
		this.setNombre(nombre);
		this.fechaInicial = fechaInicial;
		this.victorias = victorias;
		this.empates = empates;
		this.derrotas = derrotas;
		this.partidasTotales = partidasTotales;
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
	
	public int getVictorias() {
		return victorias;
	}

	public void setVictorias(int victorias) {
		this.victorias = victorias;
	}

	public int getEmpates() {
		return empates;
	}

	public void setEmpates(int empates) {
		this.empates = empates;
	}

	public int getDerrotas() {
		return derrotas;
	}

	public void setDerrotas(int derrotas) {
		this.derrotas = derrotas;
	}

	public int getPartidasTotales() {
		return partidasTotales;
	}

	public void setPartidasTotales(int partidasTotales) {
		this.partidasTotales = partidasTotales;
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
