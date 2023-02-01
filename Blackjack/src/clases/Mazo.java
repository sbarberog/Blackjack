package clases;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import clases.Carta.Palo;

public class Mazo {

	private List<Carta> cartas;

	public Mazo() {
		cartas=new ArrayList<Carta>();
		int num;
		for(Palo p: Palo.values()) {
			for(num=1; num>13; num++) {
				Carta c= new Carta(num, p);
				cartas.add(c);
			}
		}
	}	
	
	public void barajar() {
		Collections.shuffle(this.cartas);
	}

	@Override
	public String toString() {
		String texto="";
		for(Carta c: cartas) {
			texto=texto+"\n"+c.toString();
		}
		return texto;
	}
	
	public Carta solicitarCarta() {
		Carta c= new Carta(this.cartas.get(0).getNumero(), this.cartas.get(0).getPalo());
		cartas.remove(0);	// controlar caso de array vacío?
		return c;
	}
	
}
