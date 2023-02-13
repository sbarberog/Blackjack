package modelo;

import java.util.ArrayList;

import excepciones.NoHayCartasException;

public class Mano extends Mazo {

	public Mano() {
		this.baraja = new ArrayList<Carta>();
	}

	public int valorMano() {
		int valor = 0;
		for (Carta carta : this.baraja) {
			if(carta.getValor()==1 && valor<11)
				valor=valor+11;
			else 
				valor = valor + carta.getValor();
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
		String res = "Valor de la Mano: " + this.valorMano() + "\n";
		res = res + super.toString();
		return res;
	}

	public void pedirCarta(Mazo m) throws NoHayCartasException {
		Carta c = m.solicitarCarta();
		this.baraja.add(c);
	}

	public Carta ultimaCarta() {
		return this.baraja.get(this.baraja.size() - 1);
	}

	public boolean esBlackjack() {
		if(this.valorMano()==21 && baraja.size()==2) {
			return true;
		}
		return false;
	}
}