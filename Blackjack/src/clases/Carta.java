package clases;

public class Carta {

	public enum Palo {
		TRÉBOLES, DIAMANTES, CORAZONES, PICAS
	};

	public static int valor[] = { 11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10 };
	public static String numCarta[] = { "AS", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };

	private int numero;
	private Palo palo;

	public Carta(int numero, Palo palo) {
		if (numero > 0 && numero < 14) {
			this.numero = numero;
		}
		this.palo = palo;
	}

	public int getNumero() {
		return numero;
	}

	public Palo getPalo() {
		return palo;
	}

	public int getValor() {
		return valor[this.numero - 1];
	}

	public String mostrarNumero() {
		return numCarta[this.numero - 1];
	}

	@Override
	public String toString() {
		return "[" + this.mostrarNumero() + "-" + this.palo + "]";
	}

}
