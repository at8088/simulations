package balls;
import java.awt.Point;

public class Balls {
	private Point[] tab_ini;
	private Point[] tab;
	private int[][] directions;

	public Balls(Point[] tab) {
		this.tab_ini = new Point[tab.length];
		this.tab = new Point[tab.length];
		this.directions = new int[tab.length][2];
		for (int i = 0; i < tab.length; i++) {
			this.tab[i] = tab[i].getLocation();
			this.tab_ini[i] = tab[i].getLocation();
		}
	}
	
	public void setDirections(int indice, int x, int y) {
		this.directions[indice][0] = x;
		this.directions[indice][1] = y;
	}
	
	public void translater(int dx, int dy, int taille_x, int taille_y) {
		for (int i = 0; i < this.tab.length; i++){
			if ((int)tab[i].getX() == taille_x || (int)tab[i].getX() == 0){
				this.directions[i][0] = -this.directions[i][0];
			}
			if ((int)tab[i].getY() == taille_y || (int)tab[i].getY() == 0){
				this.directions[i][1] = -this.directions[i][1];
			}
			
			tab[i].translate(dx * this.directions[i][0], dy * this.directions[i][1]);
		}
	}

	public void reInit() {
		for (int i = 0; i < this.tab.length; i++) {
			this.tab[i] = this.tab_ini[i].getLocation();
		}
	}


	@Override
	public String toString() {
		String result = "";
		for (Point ball : this.tab) {
			result += ball.toString() + " ";
		}
		return result;
	}
	
	
	public Point[] getTab_ini() {
		return tab_ini;
	}

	public Point[] getTab() {
		return tab;
	}

	public int[][] getDirections() {
		return directions;
	}
	
	
}
