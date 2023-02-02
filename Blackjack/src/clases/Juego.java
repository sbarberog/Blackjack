package clases;

import java.util.Scanner;

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
				mano.pedirCarta(mazo);
				System.out.println(mano);
			} else if (respuesta.equals("n")) {
				sigue = false;
				System.out.println("Te has plantado. Puntuación final: " + mano.valorMano());
			}
		} while (sigue && !mano.finDeJuego());

		if (mano.finDeJuego()) {
			System.out.println("Has perdido... ");
		}

	}

}
