package clases;
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
//			nuevoJuego();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void nuevoJuego() {
		baraja=new Mazo();
		baraja.barajar();
		jugador=new Mano();
		banca=new Mano();
		frame.empiezaJuego();
	}
	
	public static void pideCarta() throws NoHayCartasException {
		try {
			jugador.pedirCarta(baraja);
			frame.muestraCartaJ(jugador.ultimaCarta(),jugador.cartas.size()-1);
			frame.actualizaPuntos();
			if(jugador.finDeJuego()) {
				frame.turnoBanca();
			}
		} catch (NoHayCartasException e) {
			System.err.println("No hay más cartas en la baraja");
		}
		
	}
	
	public static void juegaBanca() throws NoHayCartasException {
		do {
			try {
				banca.pedirCarta(baraja);
				frame.muestraCartaB(banca.ultimaCarta(),banca.cartas.size()-1);
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.err.println("Error al esperar.");
			}
		} while (banca.valorMano()<17 && !banca.finDeJuego());
		quienGana();
	}
	
	public static void quienGana() {
		frame.actualizaPuntos();
		frame.puntuacionFinal();
		if (banca.valorMano() <= 21 && (jugador.valorMano()<=banca.valorMano() || jugador.valorMano()>21)) {
			frame.ganaBanca();
		} else if(jugador.valorMano()<=21 && (banca.valorMano()>21 || banca.valorMano()<jugador.valorMano())){
			frame.ganasTu();
		} else {
			frame.ganaNadie();
		}
		frame.finDePartida();
	}	
	
	
	}
