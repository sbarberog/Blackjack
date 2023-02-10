package controlador;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.swing.JOptionPane;

import dao.JugadorDAO;
import dao.PartidaDAO;
import excepciones.NoHayCartasException;
import modelo.Jugador;
import modelo.JugadorOffline;
import modelo.Mano;
import modelo.Mazo;
import modelo.Partida;
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
	private boolean musica;
	private static Partida partida;
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
			guiNuevoJugador=new GuiNuevoJugador();
			audio = new Audio();
			jugador= new Jugador();
			partida= new Partida();

			ventanaPpal.setControlador(this);
			guiNuevoJugador.setControlador(this);
			
			jugadorDAO=new JugadorDAO();
			partidaDAO= new PartidaDAO();
			
			ventanaPpal.actualizaDatosJugador();
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
			partidaDAO.insertarPartida(partida);
			ventanaPpal.actualizaDatosJugador();
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
		partida.setIdJugador(jugador.getIdJugador());
		partida.setPuntosJ(manoJ.valorMano());
		partida.setPuntosB(manoB.valorMano());
		jugador.setPartidasTotales(jugador.getPartidasTotales() + 1);
		
		if (manoB.valorMano() <= 21 && (manoJ.valorMano() < manoB.valorMano() || manoJ.valorMano() > 21)) {
			jugador.setDerrotas(jugador.getDerrotas() + 1);
			partida.setResultado(-1);
			if (efectos)
				audio.sonidoDerrota();
			ventanaPpal.ganaBanca();
		} else if (manoJ.valorMano() <= 21 && (manoB.valorMano() > 21 || manoB.valorMano() < manoJ.valorMano())) {
			jugador.setVictorias(jugador.getVictorias() + 1);
			partida.setResultado(1);
			if (efectos)
				audio.sonidoVictoria();
			ventanaPpal.ganasTu();
		} else {
			jugador.setEmpates(jugador.getEmpates() + 1);
			partida.setResultado(0);
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

		return jugador.getVictorias();
	}

	public int getEmpates() {

		return jugador.getEmpates();
	}

	public int getDerrotas() {

		return jugador.getDerrotas();
	}

	public int getPartidasJ() {

		return jugador.getPartidasTotales();
	}
	
	public String getNombreJugador() {
		return jugador.getNombre();
	}

	public boolean isMusica() {
		return musica;
	}

	public void insertarNuevoJugador(String nombre) throws SQLIntegrityConstraintViolationException, SQLException {
		jugadorDAO.insertarJugador(nombre);
		jugador=jugadorDAO.obtenerJugador(nombre);
		ventanaPpal.limpiaMesas();
		ventanaPpal.actualizaDatosJugador();
		
	}

	public void ventanaAnadirJugador() {
		guiNuevoJugador.setVisible(true);
	}
}