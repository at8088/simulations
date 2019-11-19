package Boids;

import java.awt.Point;

import cellules.Cellules;

public class Boids extends Cellules {

	public Boids(Point[] tab, int[] etats) {
		super(tab, etats);
	}
	
	/* Translate tous les agents */

	public void translater(int dx, int dy, int taille_x, int taille_y) {
		for (int i = 0; i < this.getTab().length; i++){
			this.getTab()[i].translate(dx * this.getDirections()[i][0], dy * this.getDirections()[i][1]);
			
			/* Gestions des bords */
			if ((int)this.getTab()[i].getX() <= 0) this.getTab()[i].setLocation(taille_x, (int)this.getTab()[i].getY());
			else if ((int)this.getTab()[i].getX() >= taille_x) this.getTab()[i].setLocation(0, (int)this.getTab()[i].getY());
			if ((int)this.getTab()[i].getY() <= 0) this.getTab()[i].setLocation((int)this.getTab()[i].getX(), taille_y);
			else if ((int)this.getTab()[i].getY() >= taille_y) this.getTab()[i].setLocation((int)this.getTab()[i].getX(), 0);
		}
	}
	
	@Override
	public void reInit(int taille) {
		super.reInit();
		System.arraycopy(this.getEtats_ini(), 0, this.getEtats(), 0, this.getEtats().length);
	}
}