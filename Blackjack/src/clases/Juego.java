package clases;
import gui.Blackjack;

public class Juego {
	
	static Blackjack frame;

	public static void main(String[] args) {

		try {
			frame = new Blackjack();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
