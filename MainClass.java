import java.util.Scanner;

public class MainClass {
	public static void main(String args[]) {
		Scanner _key = new Scanner(System.in);
		double a;
		double b;
		String t1;
		String t2;
		a = _key.nextDouble();
		b = _key.nextDouble();
		a = 1 + 2 * 3 / b;
		t1 = "banana" + "cacau";
		t2 = "teste";
		if ("banana" + " cacau" == t1 || 2 == 3 && t2 != "jujuba") {
			System.out.println(a);
		} else {
			System.out.println(b);
		}

		if (a <= b) {
			System.out.println(t1);
		}
		while (a == 20.0) {
			System.out.println(15);
		}

	}
}