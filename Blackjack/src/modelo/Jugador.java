package modelo;

import java.sql.Date;
import java.util.Objects;

public class Jugador {

	private int idJugador;
	private String nombre;
	private Date fechaRegistro;
	private int victorias;
	private int empates;
	private int derrotas;
	private int partidasTotales;
	private int fichas;
	
	public Jugador() {
		this.setIdJugador(0);
		this.setNombre("");
		this.fechaRegistro = null;
		this.victorias=0;
		this.empates=0;
		this.derrotas=0;
		this.partidasTotales=0;
		this.fichas=100;
	}
	
	public Jugador(int idJugador, String nombre, Date fechaRegistro, int victorias, int empates, int derrotas,
			int partidasTotales, int fichas) {
		this.setIdJugador(idJugador);
		this.setNombre(nombre);
		this.fechaRegistro = fechaRegistro;
		this.victorias = victorias;
		this.empates = empates;
		this.derrotas = derrotas;
		this.partidasTotales = partidasTotales;
		this.fichas=fichas;
	}
	
	public Jugador(String nombre) {
		this.setNombre(nombre);
	}

	public Jugador(Jugador j) {
		this.setIdJugador(j.idJugador);
		this.setNombre(j.nombre);
		this.fechaRegistro = j.fechaRegistro;
		this.victorias = j.victorias;
		this.empates = j.empates;
		this.derrotas = j.derrotas;
		this.partidasTotales = j.partidasTotales;
		this.fichas=j.fichas;
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

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
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

	public int getFichas() {
		return fichas;
	}

	public void setFichas(int fichas) {
		this.fichas = fichas;
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
		return getNombre() + " - (Fichas: "+getFichas()+" - Partidas totales: "+getPartidasTotales()+")";
	}
	
	
}
