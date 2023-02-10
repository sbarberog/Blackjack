package controlador;

import javax.swing.JOptionPane;

import dao.JugadorDAO;
import dao.PartidaDAO;
import excepciones.NoHayCartasException;
import modelo.Jugador;
import modelo.JugadorOffline;
import modelo.Mano;
import modelo.Mazo;
import vista.GuiBlackjack;
import vista.GuiNuevoJugador;

public class Controlador {

	private static GuiBlackjack ventanaPpal;
	private static GuiNuevoJugador guiNuevoJugador;
	private static JugadorDAO jugadorDAO;
	private static PartidaDAO partidaDAO;
	private static Mazo baraja;
	private static Mano manoJ;
	private static Mano manoB;
	private static boolean turnoJugador;
	private static boolean efectos;
	private static Audio audio;
	private static JugadorOffline jugadorOffline;
	private boolean musica;
	private Jugador jugador;

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
			guiNuevoJugador=new GuiNuevoJugador();
			audio = new Audio();
			jugadorOffline = new JugadorOffline();
			jugador= new Jugador();

			ventanaPpal.setControlador(this);
			guiNuevoJugador.setControlador(this);
			
			jugadorDAO=new JugadorDAO();
			partidaDAO= new PartidaDAO();
			
			ventanaPpal.actualizaContador();
			ventanaPpal.actualizaCheckboxes();
			ventanaPpal.setVisible(true);
			setTurnoJugador(false);
			setEfectos(true);
			setMusica(true);
			nuevoJuego();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void nuevoJuego() {
		do {
			while (!isTurnoJugador()) {
				Thread.onSpinWait();
			}
			baraja = new Mazo();
			baraja.barajar();
			manoJ = new Mano();
			manoB = new Mano();
			ventanaPpal.empiezaJuego(jugador);
			if (isEfectos())
				audio.sonidoBarajar();
			while (isTurnoJugador()) {
				Thread.onSpinWait();
			}
			ventanaPpal.turnoBanca();
			juegaBanca();
			quienGana();
			ventanaPpal.actualizaContador();
			ventanaPpal.finDePartida();
			while (!isTurnoJugador()) {
				Thread.onSpinWait();
			}
		} while (isTurnoJugador());
	}

	public void pideCarta() throws NoHayCartasException {
		manoJ.pedirCarta(baraja);
		if (isEfectos())
			audio.sonidoNaipe();
		ventanaPpal.muestraCartaJ(manoJ.ultimaCarta(), manoJ.getCartas().size() - 1);
		ventanaPpal.actualizaPuntos();
		ventanaPpal.actualizaMazo();
		if (manoJ.finDeJuego()) {
			setTurnoJugador(false);
		}
	}

	public static void juegaBanca() {
		do {
			try {
				manoB.pedirCarta(baraja);
				if (efectos)
					audio.sonidoNaipe();
				ventanaPpal.muestraCartaB(manoB.ultimaCarta(), manoB.getCartas().size() - 1);
				ventanaPpal.actualizaPuntos();
				ventanaPpal.actualizaMazo();
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.err.println("Error al esperar.");
			} catch (NoHayCartasException e) {
				JOptionPane.showMessageDialog(ventanaPpal, "No quedan cartas en la baraja", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		} while (manoB.valorMano() < 17 && !manoB.finDeJuego());
	}

	public static void quienGana() {
//		frame.actualizaPuntos();
		ventanaPpal.puntuacionFinal();
		jugadorOffline.setPartidasJ(jugadorOffline.getPartidasJ() + 1);
		if (manoB.valorMano() <= 21 && (manoJ.valorMano() < manoB.valorMano() || manoJ.valorMano() > 21)) {
			jugadorOffline.setDerrotas(jugadorOffline.getDerrotas() + 1);
			if (efectos)
				audio.sonidoDerrota();
			ventanaPpal.ganaBanca();
		} else if (manoJ.valorMano() <= 21 && (manoB.valorMano() > 21 || manoB.valorMano() < manoJ.valorMano())) {
			jugadorOffline.setVictorias(jugadorOffline.getVictorias() + 1);
			if (efectos)
				audio.sonidoVictoria();
			ventanaPpal.ganasTu();
		} else {
			jugadorOffline.setEmpates(jugadorOffline.getEmpates() + 1);
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

	public void setEfectos(boolean s) {
		if (s) {
			efectos = true;
		} else {
			efectos = false;
			audio.efectosOff();
		}
	}

	public boolean isEfectos() {
		return efectos;
	}

	public void setMusica(boolean b) {
		musica = b;
		if (b) {
			audio.musicaOn();
		} else {
			audio.musicaOff();
		}
	}

//	public void efectosOff() {
//		audio.soundOff();
//	}
//
//	public void efectosOn() {
//		audio.musicaOn();
//	}
//
//	public void musicaOff() {
//		musica=false;
//		audio.musicaOff();
//	}
//
//	public void musicaOn() {
//		musica=true;
//		audio.musicaOn();
//		
//	}

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

		return jugadorOffline.getVictorias();
	}

	public int getEmpates() {

		return jugadorOffline.getEmpates();
	}

	public int getDerrotas() {

		return jugadorOffline.getDerrotas();
	}

	public int getPartidasJ() {

		return jugadorOffline.getPartidasJ();
	}

	public boolean isMusica() {
		return musica;
	}

	public void insertarJugador(String nombre) {
		jugador.setNombre(nombre);
		jugadorDAO.insertarJugador(jugador);
		jugador.setIdJugador(jugadorDAO.obtenerIdJugador(nombre));
		setTurnoJugador(true);
		
	}

	public void ventanaAnadirJugador() {
		guiNuevoJugador.setVisible(true);
	}
}