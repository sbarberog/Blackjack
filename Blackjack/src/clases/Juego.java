package clases;

import java.util.Scanner;

import excepciones.NoHayCartasException;

public class Juego {

	public static void main(String[] args) {

		@SuppressWarnings("resource")
		Scanner teclado = new Scanner(System.in);
		Mazo mazo = new Mazo();
		mazo.barajar();
		System.out.println(mazo);

		Mano mano = new Mano();
		boolean sigue = true;

		do {
			System.out.println("¿Quiere una carta? S/N");
			String respuesta = teclado.nextLine().toLowerCase();
			if (respuesta.equals("s")) {
				try {
					mano.pedirCarta(mazo);
				} catch (NoHayCartasException e) {
					System.out.println("No quedan más cartas en la baraja");
					sigue=false;
				}
				System.out.println(mano);
			} else if (respuesta.equals("n")) {
				sigue = false;
				System.out.println("Te has plantado. Valor de la mano: " + mano.valorMano());
			} else {
				System.out.println("Debe introducir S o N");
			}
		} while (sigue && !mano.finDeJuego());

		if (mano.finDeJuego()) {
			System.out.println("Has perdido... ");
		}

	}

}
