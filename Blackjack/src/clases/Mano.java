package clases;

import java.util.ArrayList;

import excepciones.NoHayCartasException;

public class Mano extends Mazo {

	public Mano() {
		this.cartas = new ArrayList<Carta>();
	}

	public int valorMano() {
		int valor = 0;
		for (Carta c : this.cartas) {
			valor = valor + c.getValor();
		}
		return valor;
	}

	public boolean finDeJuego() {
		if (this.valorMano() >= 21) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Puntuación de la mano: " + valorMano() + "\n" + super.toString();
	}

	public void pedirCarta(Mazo m) throws NoHayCartasException {
			Carta c = m.solicitarCarta();
			this.cartas.add(c);
	}

}
