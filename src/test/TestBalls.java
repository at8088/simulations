package test;
import java.awt.Point;

import balls.Balls;

public class TestBalls {
	public static void main(String[] args) {
		Point[] tab = new Point[3];
		tab[0] = new Point(0, 0);
		tab[1] = new Point(1, 1);
		tab[2] = new Point(2, 2);

		Balls test = new Balls(tab);

		System.out.println(test);

		test.translater(1, 1, 0, 0);
		System.out.println(test);

		test.reInit();
		System.out.println(test);

		test.translater(10, 5, 0, 0);
		System.out.println(test);

		test.reInit();
		System.out.println(test);

	}
}
