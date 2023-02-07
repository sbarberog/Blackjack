package modelo;

public class Carta {

	public enum Palo {
		TRÉBOLES, DIAMANTES, CORAZONES, PICAS
	};

	public int valor[] = { 11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10 };
	public String numCarta[] = { "AS", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };
	public String[] suits = { "_of_clubs", "_of_diamonds", "_of_hearts", "_of_spades" };
	public String[] numbers = { "ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king" };

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
		return "[ " + numero + " - " + palo + " ]";
	}

	public String imagenCarta() {
		int i = this.numero - 1;
		int j = this.getPalo().ordinal();
		return numbers[i] + suits[j];
	}

}
