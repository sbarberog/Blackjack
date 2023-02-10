package excepciones;

import controlador.Controlador;

public class FinTurnoJugadorException extends Exception {

	private Controlador controlador;
	
	public static void main(String[] args) {
		
	}
	
	public void setControlador(Controlador c) {
		this.controlador=c;
	}
}

