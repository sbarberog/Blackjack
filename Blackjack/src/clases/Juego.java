package clases;
import excepciones.NoHayCartasException;
import gui.GuiBlackjack;

public class Juego {
	
	static GuiBlackjack frame;
	public static Mazo baraja;
	private static Mano jugador;
	private static Mano banca;

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
	
	public static void pedirCarta() throws NoHayCartasException {
		try {
			jugador.pedirCarta(baraja);
			frame.muestraCartaJ(jugador.ultimaCarta());
		} catch (NoHayCartasException e) {
			System.out.println("No hay más cartas en la baraja");
		}
		if(jugador.finDeJuego()) {
			juegaBanca();
		}
	}
	
	public static void juegaBanca() throws NoHayCartasException {
		do {
			banca.pedirCarta(baraja);
		} while (banca.valorMano()<17 && (!banca.finDeJuego() || banca.valorMano() < jugador.valorMano()
				|| jugador.valorMano()<=21));
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
	}	
	
	private static void ganaNadie() {
		
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


