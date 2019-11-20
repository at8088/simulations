package Boids;

import java.awt.Color;

import balls.Balls;
import cellules.Cellules;
import cellules.CellulesSimulator;
import gui.GUISimulator;
import gui.Oval;

public class BoidsSimulator extends CellulesSimulator {


	public BoidsSimulator(GUISimulator gui, int taille, Cellules cellules) {
		super(gui, taille, cellules);
		this.getManager().reInitCurrentDate();
	}
	
	public enum Relation {
		NON_VOISIN,
		VOISIN,
		VOISIN_IMMEDIAT
	};
	
	public enum Relation_espece {
		MEME,
		INFERIEURE,
		SUPERIEURE
	};
	
	/* Determine la relation entre i et j : meme espece, i espece inferieur, i espece superieure */
	public Relation_espece est_espece(int i, int j) {
		if (this.getCellules().getEtats()[i] == this.getCellules().getEtats()[j]) return Relation_espece.MEME;
		if (this.getCellules().getEtats()[i] < this.getCellules().getEtats()[j]) return Relation_espece.INFERIEURE;
		else return Relation_espece.SUPERIEURE;
	}
	
	/* Renvoie la valeur absolue de a*/
	public int abs(int a) {
		if (a < 0) return -a;
		return a;
	}
	
	/* Normalise une composante d'un vecteur */
	public int normalise_vecteur(int a) {
		if (a > 0) a = 1;
		else if (a < 0) a = -1;
		else a = 0;
		
		return a;
	}
	
	/* L'agent d'indice i mange l'agent d'indice j (il le transforme a vrai dire) */
	public void manger(int i, int j) {
		this.getCellules().getEtats()[j] = this.getCellules().getEtats()[i];
	}
	
	/* Determine la relation entre i et j : NON_VOISIN, VOISIN ou VOISIN_IMMEDIAT */
	public Relation est_voisin(int i, int j) {
		int p1_x = (int)this.getCellules().getTab()[i].getX();
		int p1_y = (int)this.getCellules().getTab()[i].getY();
		int p2_x = (int)this.getCellules().getTab()[j].getX();
		int p2_y = (int)this.getCellules().getTab()[j].getY();
		
		if (p1_x == p2_x && p1_y == p2_y) return Relation.NON_VOISIN;
		if (abs(p1_x - p2_x) <= 20 && abs(p1_y - p2_y) <= 20) return Relation.VOISIN_IMMEDIAT;
		if (abs(p1_x - p2_x) <= 70 && abs(p1_y - p2_y) <= 70) return Relation.VOISIN; // A FAIRE VARIER
		return Relation.NON_VOISIN; 
	}
	
	/* Calcul la nouvelle direction du Point d'indice correspondant*/
	public int[] new_dir(int indice) {
		int mass_center_x = 0; // Force de cohésion
		int mass_center_y = 0;
		int dir_x = 0; 		   // Force d'alignement
		int dir_y = 0;
		int escape_x = 0;      // Force de separation
		int escape_y = 0;
		int chasse_x = 0;
		int chasse_y = 0;
		int new_x = 0;         // Nouvelle direction
		int new_y = 0;
		int nb_voisins = 0;
		Balls balls = this.getCellules();
		
		/* Calcul brut des coordonnees correspondant aux trois forces */
		for (int i = 0; i < balls.getTab().length; i++) {
			Relation relation = est_voisin(indice, i);
			Relation_espece relation_espece = est_espece(indice, i);
			/* S'ils sont voisin et indice de la meme espece que i ou d'une espece superieure */
			if (relation != Relation.NON_VOISIN && relation_espece != Relation_espece.SUPERIEURE) {
				if (this.getCellules().getEtats()[indice] <= this.getCellules().getEtats()[i]) {
					nb_voisins++;
					mass_center_x += (int)balls.getTab()[i].getX();
					mass_center_y += (int)balls.getTab()[i].getY();
					dir_x += balls.getDirections()[i][0];
					dir_y += balls.getDirections()[i][1];
				}
			}
			if (relation == Relation.VOISIN_IMMEDIAT) {
				if (relation_espece == Relation_espece.MEME) {
					if ((int)balls.getTab()[i].getX() > (int)balls.getTab()[indice].getX()) escape_x -= 1;
					else if ((int)balls.getTab()[i].getX() < (int)balls.getTab()[indice].getX()) escape_x += 1;
					if ((int)balls.getTab()[i].getY() > (int)balls.getTab()[indice].getY()) escape_y -= 1;
					else if ((int)balls.getTab()[i].getY() < (int)balls.getTab()[indice].getY()) escape_y += 1;
				}
				// Si i voisin immediat et d'espece inferieure on le mange
				else if (relation_espece == Relation_espece.INFERIEURE) {
					manger(indice, i);
				}
				// SI i voisin immediat et d'espece superieure on se fait manger
				else {
					manger(i, indice);
				}	
			}
		}
		
		/* Calcul de la nouvelle direction de l'agent en fonction de ses voisins*/
		if (nb_voisins > 0) {
			// Normalisation de la composante mass_center_x
			mass_center_x /= nb_voisins;
			if ((int)balls.getTab()[indice].getX() < mass_center_x) mass_center_x = 1;
			else if ((int)balls.getTab()[indice].getX() > mass_center_x) mass_center_x = -1;
			else mass_center_x = 0;
			
			// Normalisation de la composante dir_y
			mass_center_y /= nb_voisins;
			if ((int)balls.getTab()[indice].getY() < mass_center_y) mass_center_y = 1;
			else if ((int)balls.getTab()[indice].getY() > mass_center_y) mass_center_y = -1;
			else mass_center_y = 0;
		
			// Normalisation des vecteurs force d'alignement et force de separation
			dir_x = normalise_vecteur(dir_x);
			dir_y = normalise_vecteur(dir_y);
			escape_x = normalise_vecteur(escape_x);
			escape_y = normalise_vecteur(escape_y);
			
			// Calcul et normalisation de la nouvelle direction
			new_x = normalise_vecteur(mass_center_x + dir_x + escape_x);
			new_y = normalise_vecteur(mass_center_y + dir_y + escape_y);
		}
		/* Si l'agent n'a aucun voisin, il garde sa direction */
		else { 
			new_x = balls.getDirections()[indice][0];
			new_y = balls.getDirections()[indice][1];
		}
		/* On renvoie les composantes de la direction sous forme d'un tableau */
		int[] tab = {new_x, new_y};
		
		return tab;
	}
	
	/* Realise l'affichage */
	@Override
	public void affiche() {
		for (int i = 0; i < this.getCellules().getEtats().length; i++) {
			if (this.getCellules().getEtats()[i] == 0){
				this.getGui().addGraphicalElement(new Oval((int)this.getCellules().getTab()[i].getX(), (int)this.getCellules().getTab()[i].getY(), Color.BLUE, Color.BLUE, 10));
			}
			else this.getGui().addGraphicalElement(new Oval((int)this.getCellules().getTab()[i].getX(), (int)this.getCellules().getTab()[i].getY(), Color.RED.darker(), Color.RED.darker(), 10));
		}
	}
	
	@Override
	public void next() {
//		Cellules cellules = this.getCellules();
//		int[] new_direction;
//		
//		/* Calcul des nouvelles directions de chaque agents */
//		for(int i = 0; i < cellules.getTab().length; i++) {
//			new_direction = new_dir(i);
//			cellules.setDirections(i, new_direction[0], new_direction[1]);
//		}
//		
//		/* On translate tous les agents */
//		cellules.translater(10, 10, this.getTaille(), this.getTaille());
//		
//		/* affichage */
//		this.getGui().reset();
//		affiche();
		this.getManager().addEvent(new BoidsSimuEvent(this.getManager().getCurrentDate()+1,this));
		this.getManager().next();
	}

}
