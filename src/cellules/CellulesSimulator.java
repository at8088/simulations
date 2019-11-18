package cellules;

import java.awt.Color;
import gui.GUISimulator;
import gui.Rectangle;
import gui.Simulable;

public class CellulesSimulator implements Simulable {
	private Cellules cellules;
	private int[] buffer; // pour stocker l'etat futur, buffer[i] contient l'etat futur de la ieme cellule
	private GUISimulator gui;
	private int taille; // nombre de cellules par lignes et par colonnes

	public CellulesSimulator(GUISimulator gui, int taille, Cellules cellules) {
		this.cellules = cellules;
		this.gui = gui;
		this.taille = taille;
		buffer = new int[cellules.getTab().length]; // on alloue la memoire pour le buffer 
		
		affiche(); // affichage initial
	}

	/** Realise l'affichage */
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
		int i = indice / this.taille;
		int j = indice % this.taille;
		int i_inf = i == 0 ? this.taille - 1 : i - 1;
		int j_inf = j == 0 ? this.taille - 1 : j -1;
		int i_sup = i == this.taille - 1 ? 0 : i + 1;
		int j_sup = j == this.taille - 1 ? 0 : j + 1;
		
		compteur += this.cellules.getEtats()[i_inf * this.taille + j_inf];
		compteur += this.cellules.getEtats()[i * this.taille+ j_inf];
		compteur += this.cellules.getEtats()[i_sup * this.taille+ j_inf];
		compteur += this.cellules.getEtats()[i_inf * this.taille + j];
		compteur += this.cellules.getEtats()[i_sup * this.taille + j];
		compteur += this.cellules.getEtats()[i_inf * this.taille + j_sup];
		compteur += this.cellules.getEtats()[i * this.taille + j_sup];
		compteur += this.cellules.getEtats()[i_sup * this.taille + j_sup];
		
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
		affiche();
	}

	@Override
	public void restart() {
		cellules.reInit(this.taille);
		this.gui.reset();
		affiche();
	}
	
}
