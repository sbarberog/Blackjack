package gui;
import jaco.mp3.player.MP3Player;
import java.io.File;

public class Sonido {
	
	private static String[] barajar= {"barajar1","barajar2","barajar3"};
	private static String[] naipe= {"naipe1","naipe2","naipe3"};
	
//	 public static void main(String[] args) {
////		   sonidoBarajar();
//	}
	 
	 public static void sonidoBarajar() {
		 int n=(int) Math.floor(Math.random() *barajar.length);
		 new MP3Player(new File(barajar[n]+".mp3")).play();
	 }
	 
	 public static void sonidoNaipe() {
		 int n=(int) Math.floor(Math.random() *naipe.length);
		 new MP3Player(new File(naipe[n]+".mp3")).play();
	 }
	 
	 public static void sonidoVictoria() {
		 new MP3Player(new File("victoria2.mp3")).play();
	 }
	 
	 public static void sonidoDerrota() {
		 new MP3Player(new File("derrota1.mp3")).play();
	 }
	 
	 public static void sonidoEmpate() {
		 new MP3Player(new File("empate1.mp3")).play();
	 }
}