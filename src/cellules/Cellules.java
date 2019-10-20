package cellules;

import java.awt.Point;

import balls.Balls;

public class Cellules extends Balls {
	/* etat[i] contient l'etat de la ieme cellule */
	private int[] etats; // 0 si morte, 1 si vivante
	private int[] etats_ini;

	public Cellules(Point[] tab, int[] etats) {
		super(tab);
		this.etats = new int[etats.length];
		this.etats_ini = new int[etats.length];
		System.arraycopy(etats, 0, this.etats, 0, etats.length);
		System.arraycopy(etats, 0, this.etats_ini, 0, etats.length);
	}

	public int[] getEtats() {
		return etats;
	}

	public void setEtats(int[] etats) {
		this.etats = etats;
	}


	public int[] getEtats_ini() {
		return etats_ini;
	}

	public void reInit(int taille) {
		super.reInit();
		System.arraycopy(this.etats_ini, 0, this.etats, 0, taille * taille);
	}

}
