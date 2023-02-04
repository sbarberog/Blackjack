package clases;
import excepciones.NoHayCartasException;
import gui.GuiBlackjack;

public class Juego {
	
	static GuiBlackjack frame;
	public static Mazo baraja;
	public static Mano jugador;
	public static Mano banca;
	private static boolean turnoJugador;
	public static int victorias;
	public static int empates;
	public static int derrotas;
	

	public static void main(String[] args) {

		try {
			victorias=0;
			empates=0;
			derrotas=0;
			frame = new GuiBlackjack();
			frame.setVisible(true);
			frame.actualizaContador();
//			setTurnoJugador(true);
			nuevoJuego();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void nuevoJuego() throws NoHayCartasException {
		do{
			setTurnoJugador(true);
			baraja=new Mazo();
			baraja.barajar();
			jugador=new Mano();
			banca=new Mano();
			frame.empiezaJuego();
			while(isTurnoJugador()) {
				Thread.onSpinWait();
			};
			frame.turnoBanca();
			juegaBanca();
			quienGana();
			frame.finDePartida();
			while(!isTurnoJugador()) {
				Thread.onSpinWait();
			};
		} while(isTurnoJugador());
	}
	
	public static void pideCarta() throws NoHayCartasException {
			jugador.pedirCarta(baraja);
			frame.muestraCartaJ(jugador.ultimaCarta(),jugador.cartas.size()-1);
			frame.actualizaPuntos();
			if(jugador.finDeJuego()) {
				setTurnoJugador(false);
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
	}
	
	public static void quienGana() {
//		frame.actualizaPuntos();
		frame.puntuacionFinal();
		if (banca.valorMano() <= 21 && (jugador.valorMano()<banca.valorMano() || jugador.valorMano()>21)) {
			derrotas++;
			frame.ganaBanca();
		} else if(jugador.valorMano()<=21 && (banca.valorMano()>21 || banca.valorMano()<jugador.valorMano())){
			victorias++;
			frame.ganasTu();
		} else {
			empates++;
			frame.ganaNadie();
		}
	}

	public static boolean isTurnoJugador() {
		return turnoJugador;
	}

	public static void setTurnoJugador(boolean turnoJugador) {
		Juego.turnoJugador = turnoJugador;
	}	
	
	
	}
