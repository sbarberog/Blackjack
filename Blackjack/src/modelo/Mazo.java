package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import excepciones.NoHayCartasException;
import modelo.Carta.Palo;

public class Mazo {

	protected List<Carta> cartas;

	public Mazo() {
		this.cartas = new ArrayList<Carta>();
		for (Palo palo : Palo.values()) {
			for (int i = 1; i <= 13; i++) {
				Carta carta = new Carta(i, palo);
				this.cartas.add(carta);
			}
		}
	}

	public List<Carta> getCartas() {
		return cartas;
	}

	public void setCartas(List<Carta> cartas) {
		this.cartas = cartas;
	}

	public void barajar() {
		Collections.shuffle(cartas);
	}

	@Override
	public String toString() {
		String res = "";
		for (Carta carta : cartas) {
			res = res + carta.toString() + "\n";
		}
		return res;
	}

	public Carta solicitarCarta() throws NoHayCartasException {
		if (this.cartas.size() == 0) {
			throw new NoHayCartasException();
		}
		Carta carta = this.cartas.get(0);
		this.cartas.remove(0);
		return carta;
	}

}