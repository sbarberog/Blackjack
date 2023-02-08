package modelo;

import java.util.Arrays;

public class Carta {

	public enum Palo {
		TRÉBOLES, DIAMANTES, CORAZONES, PICAS
	};

	private int valor[] = { 11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10 };
	private String numCarta[] = { "AS", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };
	private String[] suits = { "_of_clubs", "_of_diamonds", "_of_hearts", "_of_spades" };
	private String[] numbers = { "ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king" };

	private int numero;
	private Palo palo;

	Carta(int numero, Palo palo) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(valor);
		return result;
	}

//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Carta other = (Carta) obj;
//		return Arrays.equals(valor, other.valor);
//	}

	
}
