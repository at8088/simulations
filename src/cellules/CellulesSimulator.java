package cellules;

import java.awt.Color;
import gui.GUISimulator;
import gui.Rectangle;
import gui.Simulable;

public class CellulesSimulator implements Simulable {
	private Cellules cellules;
	private int[] buffer; // pour stocker l'etat futur, buffer[i] contient l'etat futur de la ieme cellule
	private GUISimulator gui;
	private int taille_x; // nombre de cellules sur une ligne
	private int taille_y; // sur une colonne

	public CellulesSimulator(GUISimulator gui, int taille_x, int taille_y, Cellules cellules) {
		this.cellules = cellules;
		this.gui = gui;
		this.taille_x = taille_x;
		this.taille_y = taille_y;
		buffer = new int[cellules.getTab().length]; // on alloue la memoire pour le buffer 
		
		affiche(); // affichage initial
	}

	/** Realise l'affichage initial */
	private void affiche() {
		for (int i = 0; i < this.cellules.getEtats().length; i++) {
			if (this.cellules.getEtats()[i] == 1) {
				this.gui.addGraphicalElement(new Rectangle((int)(this.cellules.getTab()[i].getX()),
						(int)(this.cellules.getTab()[i].getY()), Color.decode("#1f77b4"), 
						Color.decode("#1f77b4"), 10));
			}
		 }
	}

	
	/** Renvoie l'etat suivant de la cellule correspondant a cette indice */
	private int etat_suivant(int indice) { 
		int compteur = 0;
		int i = indice / this.taille_x;
		int j = indice % this.taille_x;
		int i_inf = i == 0 ? this.taille_x - 1 : i - 1;
		int j_inf = j == 0 ? this.taille_y - 1 : j -1;
		int i_sup = i == this.taille_x - 1 ? 0 : i + 1;
		int j_sup = j == this.taille_x - 1 ? 0 : j + 1;
		
		compteur += this.cellules.getEtats()[i_inf * this.taille_x + j_inf];
		compteur += this.cellules.getEtats()[i * this.taille_x+ j_inf];
		compteur += this.cellules.getEtats()[i_sup * this.taille_x+ j_inf];
		compteur += this.cellules.getEtats()[i_inf * this.taille_x + j];
		compteur += this.cellules.getEtats()[i_sup * this.taille_x + j];
		compteur += this.cellules.getEtats()[i_inf * this.taille_x + j_sup];
		compteur += this.cellules.getEtats()[i * this.taille_x + j_sup];
		compteur += this.cellules.getEtats()[i_sup * this.taille_x + j_sup];
		
		return compteur >= 3 ? 1 : 0; // si plus de 3 voisins vivant alors, l'etat futur est vivante, sinon morte
	}
	
	@Override
	public void next() {
		/* calcul de l'etat suivant */
		for (int i = 0; i < this.cellules.getTab().length; i++) {
			buffer[i] = etat_suivant(i);
		}
		
		/* mise a jour du tableau des etats */
		for (int i = 0; i < this.cellules.getTab().length; i++) {
			this.cellules.getEtats()[i] = buffer[i];
		}
		
		/* affichage*/
		this.gui.reset();
		for (int i = 0; i < this.cellules.getEtats().length; i++) {
			if (this.cellules.getEtats()[i] == 1) {
				this.gui.addGraphicalElement(new Rectangle((int)(this.cellules.getTab()[i].getX()),
						(int)(this.cellules.getTab()[i].getY()), Color.decode("#1f77b4"), 
						Color.decode("#1f77b4"), 10));
			}
		 }
	}

	@Override
	public void restart() {
		cellules.reInit();
		this.gui.reset();
		for (int i = 0; i < this.cellules.getEtats().length; i++) {
			if (this.cellules.getEtats()[i] == 1) {
				this.gui.addGraphicalElement(new Rectangle((int)(this.cellules.getTab()[i].getX()),
						(int)(this.cellules.getTab()[i].getY()), Color.decode("#1f77b4"), 
						Color.decode("#1f77b4"), 10));
			}
		 }
	}
	
}
