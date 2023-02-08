package controlador;

public class Test {

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			int rand1 = (int) (Math.random() * 8) + 2;
			int rand2 = 11 - rand1;
			System.out.print(rand1 + " ");
		}

	}

}
