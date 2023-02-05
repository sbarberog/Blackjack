package clases;

import clases.Carta.Palo;

public class Testeo {

	public static void main(String[] args) {
		Carta c = new Carta(13, Palo.TRÉBOLES);
		System.out.println(c);
		System.out.println(c.imagenCarta());
	}

}
