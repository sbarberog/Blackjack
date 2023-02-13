package controlador;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import dao.JugadorDAO;
import dao.PartidaDAO;
import excepciones.NoHayCartasException;
import modelo.Jugador;
import modelo.Mano;
import modelo.Mazo;
import modelo.Partida;
import vista.GuiBlackjack;
import vista.GuiElegirJugador;
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
	private static GuiElegirJugador guiElegirJugador;
	private static Partida partida;
	private static Jugador jugador;
	private ArrayList<Jugador> listaJugadores;

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
			guiElegirJugador=new GuiElegirJugador();
			audio = new Audio();
			jugador= new Jugador();
			partida= new Partida();
			listaJugadores=new ArrayList<Jugador>();

			ventanaPpal.setControlador(this);
			guiNuevoJugador.setControlador(this);
			guiElegirJugador.setControlador(this);
			
			jugadorDAO=new JugadorDAO();
			partidaDAO= new PartidaDAO();
			
			ventanaPpal.actualizaDatosJugador();
			ventanaPpal.actualizaCheckboxes();
			ventanaPpal.setVisible(true);
			setTurnoJugador(false);
			audio.iniciaMusica();
			setEfectos(true);
			setMusica(true);
			nuevoJuego();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void nuevoJuego() throws ClassNotFoundException, SQLException, NoHayCartasException, InterruptedException {
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
			setTurnoJugador(false);
			Thread.sleep(1000);
			pideCarta();
			Thread.sleep(1000);
			setTurnoJugador(true);
			pideCarta();
			Thread.sleep(1000);
			while (isTurnoJugador()) {
				ventanaPpal.muestraBotonesJ(true);
				Thread.onSpinWait();
			}
			if(manoJ.esBlackjack()) {
				ventanaPpal.muestraBlackjack();
			}
			ventanaPpal.turnoBanca();
			juegaBanca();
			if(manoB.esBlackjack()) {
				ventanaPpal.muestraBlackjack();
			}
			ventanaPpal.puntuacionFinal(mensajePuntuacion());
			quienGana();
			partidaDAO.insertarPartida(partida);
			ventanaPpal.actualizaDatosJugador();
			ventanaPpal.finDePartida();
			while (!isTurnoJugador()) {
				Thread.onSpinWait();
			}
		} while (isTurnoJugador());
	}
	
	
	/**
	 * Calcula el mensaje que deberá mostrarse en la ventana del resultado final, teniendo en cuenta las manos con un blackjack.
	 * @return String con el texto del resultado de la partida
	 */
	private String mensajePuntuacion() {
		String blackjackJ="";
		String blackjackB="";
		if(manoJ.esBlackjack())
			blackjackJ="\n¡Has sacado blackjack!";
		if(manoB.esBlackjack())
			blackjackB="\n¡Ha sacado blackjack!";
		
		return "Puntos de tu mano: " + valorManoJ() + blackjackJ
			+ "\n\nPuntos de la banca: " + valorManoB() + blackjackB;
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
		partida.setIdJugador(jugador.getIdJugador());
		partida.setPuntosJ(manoJ.valorMano());
		partida.setPuntosB(manoB.valorMano());
		jugador.setPartidasTotales(jugador.getPartidasTotales() + 1);
		
		int valorJ=manoJ.valorMano();
		int valorB=manoB.valorMano();
		
		if((valorJ==valorB && valorJ<=21 && valorB<=21 && manoJ.esBlackjack()==manoB.esBlackjack()) || (valorJ>21 && valorB>21)) {
			jugador.setEmpates(jugador.getEmpates() + 1);
			partida.setResultado(0);
			if (efectos)
				audio.sonidoEmpate();
			ventanaPpal.ganaNadie();
		} else if ((valorJ <= 21 && (valorB > 21 || valorB < valorJ)) || (manoJ.esBlackjack() && !manoB.esBlackjack())) {
			jugador.setVictorias(jugador.getVictorias() + 1);
			partida.setResultado(1);
			if (efectos)
				audio.sonidoVictoria();
			ventanaPpal.ganaJugador();
		} else {
			jugador.setDerrotas(jugador.getDerrotas() + 1);
			partida.setResultado(-1);
			if (efectos)
				audio.sonidoDerrota();
			ventanaPpal.ganaBanca();
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
	
	public void setJugador(Jugador j) {
		jugador=j;
	}

	public void insertarNuevoJugador(String nombre) throws SQLIntegrityConstraintViolationException, SQLException, ClassNotFoundException {
		jugadorDAO.insertarJugador(nombre);
		jugador=jugadorDAO.obtenerNuevoJugador(nombre);
		ventanaPpal.limpiaMesas();
		ventanaPpal.actualizaDatosJugador();
		guiNuevoJugador.setVisible(false);
		guiElegirJugador.setVisible(false);
		setTurnoJugador(true);
		
	}

	public void ventanaAnadirJugador() {
		guiNuevoJugador.limpiar();
		guiNuevoJugador.setVisible(true);
	}
	
	public void ventanaElegirJugador() throws ClassNotFoundException, SQLException {
		listaJugadores=jugadorDAO.obtenerJugadores();
		guiElegirJugador.setListaJugadores(listaJugadores);
		guiElegirJugador.setVisible(true);
	}
}