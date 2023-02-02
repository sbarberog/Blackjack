package clases;

import java.util.ArrayList;

import excepciones.NoHayCartasExcepcion;

public class Mano extends Mazo {

	public Mano() {
		this.cartas = new ArrayList<Carta>();
	}
	
	public int valorMano() {
		int valor=0;
		for (Carta carta : this.cartas) {
			valor=valor+carta.getValor();
		}
		return valor;
	}
	
	public boolean finDeJuego() {
		if (this.valorMano()>=21) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		String res="Valor de la Mano: "+this.valorMano()+"\n";
		res=res+super.toString();
		return res;
	}
	
	public void pedirCarta(Mazo m) throws NoHayCartasExcepcion {
		Carta c = m.solicitarCarta();
		this.cartas.add(c);
	}
	
	
	
}