package modelo;

import java.sql.Timestamp;
import java.util.Objects;

public class Partida {

	private int idPartida;
	private int idJugador;
	private int puntosJ;
	private int puntosB;
	private int resultado;
	private Timestamp timestamp;
	
	public Partida() {
		this.idPartida = 0;
		this.idJugador = 0;
		this.puntosJ = 0;
		this.puntosB = 0;
		this.resultado = 0;
		this.timestamp = null;
	}
	
	public Partida(int idPartida, int idJugador, int puntosJ, int puntosB, int resultado, Timestamp timestamp) {
		this.idPartida = idPartida;
		this.idJugador = idJugador;
		this.puntosJ = puntosJ;
		this.puntosB = puntosB;
		this.setResultado(resultado);
		this.timestamp = timestamp;
	}
	
	public Partida(int idJugador, int puntosJ, int puntosB, int resultado) {
		this.idJugador = idJugador;
		this.puntosJ = puntosJ;
		this.puntosB = puntosB;
		this.setResultado(resultado);
	}

	public int getIdPartida() {
		return idPartida;
	}

	public void setIdPartida(int idPartida) {
		this.idPartida = idPartida;
	}

	public int getIdJugador() {
		return idJugador;
	}

	public void setIdJugador(int idJugador) {
		this.idJugador = idJugador;
	}

	public int getPuntosJ() {
		return puntosJ;
	}

	public void setPuntosJ(int puntosJ) {
		this.puntosJ = puntosJ;
	}

	public int getPuntosB() {
		return puntosB;
	}

	public void setPuntosB(int puntosB) {
		this.puntosB = puntosB;
	}

	public int getResultado() {
		return resultado;
	}

	public void setResultado(int resultado) {
		if(resultado==-1 || resultado==0 || resultado==1)
			this.resultado = resultado;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idPartida);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Partida other = (Partida) obj;
		return idPartida == other.idPartida;
	}

	@Override
	public String toString() {
		return "Partida [idPartida=" + idPartida + ", idJugador=" + idJugador + ", puntosJ=" + puntosJ + ", puntosB="
				+ puntosB + ", resultado=" + resultado + ", timestamp=" + timestamp + "]";
	}
	
	public String getResultadoEnum() {
		if(this.getResultado()==-1) {
			return "D";
		}else if(this.getResultado()==0) {
			return "E";
		}else {
			return "V";
		}
	}
}
