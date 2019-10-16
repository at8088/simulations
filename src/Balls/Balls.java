package Balls;
import java.awt.Point;

public class Balls {
	private Point[] balls_init_tab;
	private Point[] balls_tab;

	public Balls(Point[] tab) {
		this.balls_init_tab = new Point[tab.length];
		this.balls_tab = new Point[tab.length];
		for (int i = 0; i < tab.length; i++) {
			this.balls_tab[i] = tab[i].getLocation();
			this.balls_init_tab[i] = tab[i].getLocation();
		}
	}

	public void translater(int dx, int dy) {
		for (Point Ball : this.balls_tab) {
			Ball.translate(dx, dy);
		}
	}

	public void reInit() {
		for (int i = 0; i < this.balls_tab.length; i++) {
			this.balls_tab[i] = this.balls_init_tab[i].getLocation();
		}
	}


	@Override
	public String toString() {
		String result = "";
		for (Point Ball : this.balls_tab) {
			result += Ball.toString() + " ";
		}
		return result;
	}
	
	
	public Point[] getBalls_init_tab() {
		return balls_init_tab;
	}

	public Point[] getBalls_tab() {
		return balls_tab;
	}

	public void print() {
		System.out.println(this.toString());
	}

}
