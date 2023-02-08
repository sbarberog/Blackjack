package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import excepciones.NoHayCartasException;
import modelo.Carta.Palo;

public class Mazo {

	protected List<Carta> baraja;

	public Mazo() {
		this.baraja = new ArrayList<Carta>();
		for (Palo palo : Palo.values()) {
			for (int i = 1; i <= 13; i++) {
				Carta carta = new Carta(i, palo);
				this.baraja.add(carta);
			}
		}
	}

	public List<Carta> getCartas() {
		return baraja;
	}

	public void setCartas(List<Carta> cartas) {
		this.baraja = cartas;
	}

	public void barajar() {
//		int rand= (int)Math.random()*10;
//		System.out.println(rand);
		Collections.shuffle(baraja);

		if (true) {
			if (this.baraja.get(0).getValor() == 10) {
				int rand1=(int)(Math.random()*8)+2;
				int rand2=11-rand1;
				int i = 0;
				for (Carta carta1 : this.baraja) {
					if (carta1.getValor() == rand1) {
						intercambiar(carta1, i, 1);
						int j = 0;
						for (Carta carta2 : this.baraja) {
							if (carta2.getValor() == rand2)
								intercambiar(carta2, j, 2);
							j++;
						}
					}

					i++;
				}
			}
		}
	}

	@Override
	public String toString() {
		String res = "Número de cartas: " + this.numCartas() + "\nSuma total de puntos: " + this.valorMazo() + "\n";
		for (Carta carta : baraja) {
			res = res + carta.toString() + "\n";
		}
		return res;
	}

	public Carta solicitarCarta() throws NoHayCartasException {
		if (this.baraja.size() == 0) {
			throw new NoHayCartasException();
		}
		Carta carta = this.baraja.get(0);
		this.baraja.remove(0);
		return carta;
	}

	protected int numCartas() {
		return baraja.size();
	}

	final int valorMazo() {
		int valor = 0;
		for (Carta carta : this.baraja) {
			valor = valor + carta.getValor();
		}
		return valor;
	}

	final void intercambiar(Carta c, int posI, int posF) {
		Carta otra = baraja.get(posF);
		baraja.set(posF, c);
		baraja.set(posI, otra);

	}

}