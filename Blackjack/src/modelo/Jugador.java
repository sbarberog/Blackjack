package modelo;

import java.util.Objects;

public class Jugador {

	private int victorias;
	private int empates;
	private int derrotas;
	private int partidasJ;

	public Jugador() {
		this.victorias = 0;
		this.empates = 0;
		this.derrotas = 0;
		this.partidasJ = 0;
	}

	public Jugador(int victorias, int empates, int derrotas, int partidasJ) {
		this.victorias = victorias;
		this.empates = empates;
		this.derrotas = derrotas;
		this.partidasJ = partidasJ;
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

	public int getPartidasJ() {
		return partidasJ;
	}

	public void setPartidasJ(int partidasJ) {
		this.partidasJ = partidasJ;
	}

	@Override
	public int hashCode() {
		return Objects.hash(derrotas, empates, partidasJ, victorias);
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
		return derrotas == other.derrotas && empates == other.empates && partidasJ == other.partidasJ
				&& victorias == other.victorias;
	}

	@Override
	public String toString() {
		return "Jugador [victorias=" + victorias + ", empates=" + empates + ", derrotas=" + derrotas
				+ ", partidasJugadas=" + partidasJ + "]";
	}

}
