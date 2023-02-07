package controlador;

import excepciones.NoHayCartasException;
import modelo.Jugador;
import modelo.Mano;
import modelo.Mazo;
import vista.GuiBlackjack;

public class Controlador {

	static GuiBlackjack ventanaPpal;
	private static Mazo baraja;
	private static Mano manoJ;
	private static Mano manoB;
	private static boolean turnoJugador;
	private static boolean efectos;
	private static Audio audio;
	private static Jugador jugador;

//	public static void main(String[] args) {
//
//		try {
//			setSonido(true);
//			victorias = 0;
//			empates = 0;
//			derrotas = 0;
//			frame = new GuiBlackjack();
//			frame.setVisible(true);
//			frame.actualizaContador();
//			nuevoJuego();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}

	public void iniciarPrograma() {
		try {
			ventanaPpal = new GuiBlackjack();
			audio = new Audio();
			jugador = new Jugador();

			ventanaPpal.setControlador(this);
			ventanaPpal.actualizaContador();
			ventanaPpal.actualizaCheckboxes();
			ventanaPpal.setVisible(true);
			musicaOn();
			setSonido(true);
			nuevoJuego();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void nuevoJuego() throws NoHayCartasException {
		do {
			setTurnoJugador(true);
			baraja = new Mazo();
			baraja.barajar();
			manoJ = new Mano();
			manoB = new Mano();
			ventanaPpal.empiezaJuego();
			if (getSonido())
				audio.sonidoBarajar();
			while (isTurnoJugador()) {
				Thread.onSpinWait();
			}
			;
			ventanaPpal.turnoBanca();
			juegaBanca();
			quienGana();
			ventanaPpal.finDePartida();
			while (!isTurnoJugador()) {
				Thread.onSpinWait();
			}
			;
		} while (isTurnoJugador());
	}

	public void pideCarta() throws NoHayCartasException {
		manoJ.pedirCarta(baraja);
		if (getSonido())
			audio.sonidoNaipe();
		ventanaPpal.muestraCartaJ(manoJ.ultimaCarta(), manoJ.getCartas().size() - 1);
		ventanaPpal.actualizaPuntos();
		if (manoJ.finDeJuego()) {
			setTurnoJugador(false);
		}
	}

	public static void juegaBanca() throws NoHayCartasException {
		do {
			try {
				manoB.pedirCarta(baraja);
				if (efectos)
					audio.sonidoNaipe();
				ventanaPpal.muestraCartaB(manoB.ultimaCarta(), manoB.getCartas().size() - 1);
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.err.println("Error al esperar.");
			}
		} while (manoB.valorMano() < 17 && !manoB.finDeJuego());
	}

	public static void quienGana() {
//		frame.actualizaPuntos();
		ventanaPpal.puntuacionFinal();
		jugador.setPartidasJ(jugador.getPartidasJ() + 1);
		if (manoB.valorMano() <= 21 && (manoJ.valorMano() < manoB.valorMano() || manoJ.valorMano() > 21)) {
			jugador.setDerrotas(jugador.getDerrotas() + 1);
			if (efectos)
				audio.sonidoDerrota();
			ventanaPpal.ganaBanca();
		} else if (manoJ.valorMano() <= 21 && (manoB.valorMano() > 21 || manoB.valorMano() < manoJ.valorMano())) {
			jugador.setVictorias(jugador.getVictorias() + 1);
			if (efectos)
				audio.sonidoVictoria();
			ventanaPpal.ganasTu();
		} else {
			jugador.setEmpates(jugador.getEmpates() + 1);
			if (efectos)
				audio.sonidoEmpate();
			ventanaPpal.ganaNadie();
		}
	}

	public boolean isTurnoJugador() {
		return turnoJugador;
	}

	public void setTurnoJugador(boolean turnoJugador) {
		Controlador.turnoJugador = turnoJugador;
	}

	public void setSonido(boolean s) {
		if (s)
			efectos = true;
		else
			efectos = false;
	}

	public boolean getSonido() {
		if (efectos)
			return true;
		else
			return false;
	}

	public void soundOff() {
		audio.soundOff();
	}

	public void musicaOn() {
		audio.musicaOn();
	}

	public void musicaOff() {
		audio.musicaOff();
	}

	public String getBaraja() {

		return baraja.toString();
	}

	public int valorManoJ() {

		return manoJ.valorMano();
	}

	public int valorManoB() {

		return manoB.valorMano();
	}

	public int getVictorias() {

		return jugador.getVictorias();
	}

	public int getEmpates() {

		return jugador.getEmpates();
	}

	public int getDerrotas() {

		return jugador.getDerrotas();
	}

	public int getPartidasJ() {

		return jugador.getPartidasJ();
	}
}
