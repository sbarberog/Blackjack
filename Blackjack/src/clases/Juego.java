package clases;
import excepciones.NoHayCartasExcepcion;
import gui.GuiBlackjack;

public class Juego {
	
	static GuiBlackjack frame;
	private Mazo baraja;
	private Mano jugador;
	private Mano banca;

	public static void main(String[] args) {

		try {
			frame = new GuiBlackjack();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void nuevoJuego() {
		baraja=new Mazo();
		jugador=new Mano();
		banca=new Mano();
	}
	
	public void pedirCarta() {
		try {
			jugador.pedirCarta(baraja);
		} catch (NoHayCartasExcepcion e) {
			System.out.println("No hay más cartas en la baraja");
		}
		if(jugador.finDeJuego()) {
			juegaBanca();
		}
	}
	
	public void juegaBanca() {
		
	}
	
//	@SuppressWarnings("resource")
//	Scanner teclado = new Scanner(System.in);
//	Mazo mazo = new Mazo();
//	mazo.barajar();
//	System.out.println(mazo);
//
//	Mano mano = new Mano();
//	boolean sigue = true;
//
//	do {
//		System.out.println("¿Quiere una carta? S/N");
//		String respuesta = teclado.nextLine().toLowerCase();
//		if (respuesta.equals("s")) {
//			mano.pedirCarta(mazo);
//			System.out.println(mano);
//		} else if (respuesta.equals("n")) {
//			sigue = false;
//			System.out.println("Te has plantado. Puntuación final: " + mano.valorMano());
//		}
//	} while (sigue && !mano.finDeJuego());
//
//	if (mano.finDeJuego()) {
//		System.out.println("Has perdido... ");
//	}
//
//}

}
