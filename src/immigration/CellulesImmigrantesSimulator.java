package immigration;
import java.awt.Color;

import immigration.CellulesImmigrantes;
import gui.GUISimulator;
import gui.Rectangle;
import gui.Simulable;

public class CellulesImmigrantesSimulator implements Simulable {
	
	
	private CellulesImmigrantes cellules;
	private int[] buffer;
	private GUISimulator gui;
	private int taille; 
	private Color couleurs[];
	
	public CellulesImmigrantesSimulator(GUISimulator gui, int taille, CellulesImmigrantes cellules) {
		this.cellules = cellules;
		this.gui = gui;
		this.taille = taille;
		buffer = new int[cellules.getTab().length]; // on alloue la memoire pour le buffer 
		couleurs = new Color[this.cellules.getNbrEtats()];
		couleurs[0] = Color.WHITE;
		couleurs[1] = Color.LIGHT_GRAY;
		couleurs[2] = Color.DARK_GRAY;
		couleurs[3] = Color.BLACK;
		affiche(); // affichage initial
	}
	
	
	
	void affiche() {
		for (int i = 0; i < this.cellules.getEtats().length; i++) {
				this.gui.addGraphicalElement(new Rectangle((int)(this.cellules.getTab()[i].getX()),
						(int)(this.cellules.getTab()[i].getY()), couleurs[this.cellules.getEtats()[i]], 
						couleurs[this.cellules.getEtats()[i]], 10));
			
		 }
	}
	
	
	
	
	private int etat_suivant(int indice) { 
		int compteur = 0;
		int i = indice / this.taille;
		int j = indice % this.taille;
		int i_inf = i == 0 ? this.taille - 1 : i - 1;
		int j_inf = j == 0 ? this.taille - 1 : j -1;
		int i_sup = i == this.taille - 1 ? 0 : i + 1;
		int j_sup = j == this.taille - 1 ? 0 : j + 1;
		
		compteur += this.cellules.isInState(i_inf * this.taille + j_inf ,(this.cellules.getEtats()[indice] + 1)%this.cellules.getNbrEtats());
		compteur += this.cellules.isInState(  i * this.taille+ j_inf  ,(this.cellules.getEtats()[indice] + 1)%this.cellules.getNbrEtats());
		compteur += this.cellules.isInState( i_sup * this.taille+ j_inf, (this.cellules.getEtats()[indice] + 1)%this.cellules.getNbrEtats());
		compteur += this.cellules.isInState( i_inf * this.taille + j   , (this.cellules.getEtats()[indice] + 1)%this.cellules.getNbrEtats());
		compteur += this.cellules.isInState(  i_sup * this.taille + j  , (this.cellules.getEtats()[indice] + 1)%this.cellules.getNbrEtats());
		compteur += this.cellules.isInState( i_inf * this.taille + j_sup , (this.cellules.getEtats()[indice] + 1)%this.cellules.getNbrEtats());
		compteur += this.cellules.isInState(  i * this.taille + j_sup ,(this.cellules.getEtats()[indice] + 1)%this.cellules.getNbrEtats());
		compteur += this.cellules.isInState( i_sup * this.taille + j_sup  ,(this.cellules.getEtats()[indice] + 1)%this.cellules.getNbrEtats());

		
		return compteur >= 3 ? (this.cellules.getEtats()[indice]+1 )% this.cellules.getNbrEtats() : this.cellules.getEtats()[indice]  ; 
		// si plus de 3 voisins sont dans l'etat k+1  alors, l'etat futur est k+1, sinon c'est k
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

	public Color[] getCouleur() {
		return couleurs;
	}

	

}
