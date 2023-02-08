package controlador;

public class Test {

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			int rand1 = (int) (Math.random() * 8) + 2;
			int rand2 = 11 - rand1;
			System.out.println(rand1 + " + "+rand2+" = "+(rand1+rand2));
		}

	}

}
