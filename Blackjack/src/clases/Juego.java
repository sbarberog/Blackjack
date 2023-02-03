package clases;
import javax.swing.JOptionPane;

import excepciones.NoHayCartasException;
import gui.GuiBlackjack;

public class Juego {
	
	static GuiBlackjack frame;
	public static Mazo baraja;
	public static Mano jugador;
	public static Mano banca;

	public static void main(String[] args) {

		try {
			frame = new GuiBlackjack();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void nuevoJuego() {
		baraja=new Mazo();
		baraja.barajar();
		jugador=new Mano();
		banca=new Mano();
	}
	
	public static void pideCarta() throws NoHayCartasException {
		try {
			jugador.pedirCarta(baraja);
			frame.muestraCartaJ(jugador.ultimaCarta(),jugador.cartas.size()-1);
			frame.actualizaPuntos();
			if(jugador.finDeJuego()) {
				frame.turnoBanca();
				juegaBanca();
			}
		} catch (NoHayCartasException e) {
			System.err.println("No hay más cartas en la baraja");
		}
		
	}
	
	public static void juegaBanca() throws NoHayCartasException {
		do {
			banca.pedirCarta(baraja);
			frame.muestraCartaB(banca.ultimaCarta(),banca.cartas.size()-1);
		} while (banca.valorMano()<17 && !banca.finDeJuego());
		frame.actualizaPuntos();
		quienGana();
	}
	
	public static void quienGana() {
		if (banca.valorMano()>=jugador.valorMano() && banca.valorMano() <= 21) {
			ganaBanca();
		} else if(jugador.valorMano()<=21){
			ganasTu();
		} else {
			ganaNadie();
		}
//		frame.finDePartida();
	}	
	
	private static void ganaNadie() {
		JOptionPane.showMessageDialog(frame, "Nadie gana...", "Empate", JOptionPane.INFORMATION_MESSAGE);
	}

	private static void ganaBanca() {
		// TODO Auto-generated method stub
		
	}

	private static void ganasTu() {
		// TODO Auto-generated method stub
		
	}
	}
	
//	@SuppressWarnings("resource")
//	Scanner teclado = new Scanner(System.in);
//	Mazo mazo = new Mazo();
//	mazo.barajar();
//	System.out.println(mazo);
//
//	Mano jugador = new Mano();
//	boolean sigue = true;
//
//	do {
//		System.out.println("¿Quiere una carta? S/N");
//		String respuesta = teclado.nextLine().toLowerCase();
//		if (respuesta.equals("s")) {
//			jugador.pedirCarta(mazo);
//			System.out.println(jugador);
//		} else if (respuesta.equals("n")) {
//			sigue = false;
//			System.out.println("Te has plantado. Puntuación final: " + jugador.valorMano());
//		}
//	} while (sigue && !jugador.finDeJuego());
//
//	if (jugador.finDeJuego()) {
//		System.out.println("Has perdido... ");
//	}
//
//}


