package clases;

import jaco.mp3.player.MP3Player;
import java.io.File;

public class Sonido {

	private static String[] barajar = { "barajar1", "barajar2", "barajar3" };
	private static String[] naipe = { "naipe1", "naipe2", "naipe3", "naipe4", "naipe5" };
	private static String path = "sonidos/";
	private static MP3Player sBarajar;
	private static MP3Player sNaipe;
	private static MP3Player sVictoria;
	private static MP3Player sDerrota;
	private static MP3Player sEmpate;
	private static MP3Player sMusica;

//	 public static void main(String[] args) {
////		   sonidoBarajar();
//	}

	public static void musicaOn() {
		sMusica=new MP3Player(new File(path + "seduction-jazz" + ".mp3"));
		sMusica.play();
	}
	
	public static void musicaOff() {
		try {
			sMusica.stop();
		}catch(NullPointerException e) {
			System.err.println("Música nula");
		}
	}
	
	public static void soundOff(){
		try{
			sBarajar.stop();
			sNaipe.stop();
			sVictoria.stop();
			sDerrota.stop();
			sEmpate.stop();
		}catch(NullPointerException e) {
			System.err.println("Sonido nulo");
		}
	}
	
	public static void sonidoBarajar() {
		int n = (int) Math.floor(Math.random() * barajar.length);
		sBarajar=new MP3Player(new File(path + barajar[n] + ".mp3"));
		sBarajar.play();

	}

	public static void sonidoNaipe() {
		int n = (int) Math.floor(Math.random() * naipe.length);
		sNaipe=new MP3Player(new File(path + naipe[n] + ".mp3"));
		sNaipe.play();
	}

	public static void sonidoVictoria() {
		sVictoria=new MP3Player(new File(path + "victoria2.mp3"));
		sVictoria.play();
	}

	public static void sonidoDerrota() {
		sDerrota=new MP3Player(new File(path + "derrota1.mp3"));
		sDerrota.play();
	}

	public static void sonidoEmpate() {
		sEmpate=new MP3Player(new File(path + "empate1.mp3"));
		sEmpate.play();
	}
}