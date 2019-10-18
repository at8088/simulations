package Balls;
import java.awt.Point;

public class Balls {
	private Point[] balls_init_tab;
	private Point[] balls_tab;
	private int[][] directions;

	public Balls(Point[] tab) {
		this.balls_init_tab = new Point[tab.length];
		this.balls_tab = new Point[tab.length];
		this.directions = new int[tab.length][2];
		for (int i = 0; i < tab.length; i++) {
			this.balls_tab[i] = tab[i].getLocation();
			this.balls_init_tab[i] = tab[i].getLocation();
		}
	}
	
	public void setDirection(int indice, int x, int y) {
		this.directions[indice][0] = x;
		this.directions[indice][1] = y;
	}
	
	public void translater(int dx, int dy) {
		for (int i = 0; i < this.balls_tab.length; i++){
			if ((int)balls_tab[i].getX() == 500 || (int)balls_tab[i].getX() == 0){
				this.directions[i][0] = -this.directions[i][0];
			}
			if ((int)balls_tab[i].getY() == 500 || (int)balls_tab[i].getY() == 0){
				this.directions[i][1] = -this.directions[i][1];
			}
			
			balls_tab[i].translate(dx * this.directions[i][0], dy * this.directions[i][1]);
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
		for (Point ball : this.balls_tab) {
			result += ball.toString() + " ";
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
