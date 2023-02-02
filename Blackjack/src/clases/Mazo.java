package clases;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import clases.Carta.Palo;
import excepciones.NoHayCartasException;

public class Mazo {

	protected List<Carta> cartas;

	public Mazo() {
		cartas = new ArrayList<Carta>();
		for (Palo p : Palo.values()) {
			for (int num = 1; num <= 13; num++) {
				Carta c = new Carta(num, p);
				cartas.add(c);
			}
		}
	}

	public void barajar() {
		Collections.shuffle(this.cartas);
	}

	@Override
	public String toString() {
		String texto = "";
		for (Carta c : cartas) {
			texto = texto + c.toString() + "\n";
		}
		return texto;
	}

	public Carta solicitarCarta() throws NoHayCartasException {
		if (this.cartas.size() == 0) {
			throw new NoHayCartasException();
		}
		Carta c = this.cartas.get(0);
		cartas.remove(0);
		// cartas.remove(c); también funcionaría
		return c;
	}

}
