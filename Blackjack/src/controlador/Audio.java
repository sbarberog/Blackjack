package controlador;

import jaco.mp3.player.MP3Player;
import java.io.File;

public class Audio {

	private static String[] barajar = { "barajar1", "barajar2", "barajar3" };
	private static String[] naipe = { "naipe1", "naipe2", "naipe3", "naipe4", "naipe5" };
	private static String path = "sonidos/";
	private static MP3Player sBarajar;
	private static MP3Player sNaipe;
	private static MP3Player sVictoria;
	private static MP3Player sDerrota;
	private static MP3Player sEmpate;
	private static MP3Player sMusica;
//	private Controlador controlador;

//	 public static void main(String[] args) {
////		   sonidoBarajar();
//	}

	public void iniciaMusica() {
		sMusica = new MP3Player();
		sMusica.addToPlayList(new File(path + "seduction-jazz" + ".mp3"));
		sMusica.addToPlayList(new File(path + "former-102685" + ".mp3"));
		sMusica.addToPlayList(new File(path + "casino-124196" + ".mp3"));
		sMusica.setRepeat(true);
//		sMusica.setShuffle(true);
		sMusica.play();
	}
	
	public void musicaOn() {
		sMusica.skipForward();
		sMusica.play();
	}

	public void musicaOff() {
		try {
			sMusica.stop();
		} catch (NullPointerException e) {
			System.err.println("Música nula");
		}
	}

	public void efectosOff() {
		try {
			if (!sBarajar.isStopped())
				sBarajar.stop();
			if (!sNaipe.isStopped())
				sNaipe.stop();
			if (!sVictoria.isStopped())
				sVictoria.stop();
			if (!sDerrota.isStopped())
				sDerrota.stop();
			if (!sEmpate.isStopped())
				sEmpate.stop();
		} catch (NullPointerException e) {
			System.out.println("Excepción al intentar apagar los efectos.");
		}
	}

	public void sonidoBarajar() {
		int n = (int) Math.floor(Math.random() * barajar.length);
		sBarajar = new MP3Player(new File(path + barajar[n] + ".mp3"));
		sBarajar.play();

	}

	public void sonidoNaipe() {
		int n = (int) Math.floor(Math.random() * naipe.length);
		sNaipe = new MP3Player(new File(path + naipe[n] + ".mp3"));
		sNaipe.play();
	}

	public void sonidoVictoria() {
		sVictoria = new MP3Player(new File(path + "victoria2.mp3"));
		sVictoria.play();
	}

	public void sonidoDerrota() {
		sDerrota = new MP3Player(new File(path + "derrota1.mp3"));
		sDerrota.play();
	}

	public void sonidoEmpate() {
		sEmpate = new MP3Player(new File(path + "empate1.mp3"));
		sEmpate.play();
	}

//
//	public void setControlador(Controlador controlador) {
//		this.controlador=this;
//		
//	}
}