package Boids;

import java.awt.Color;
import java.awt.Point;

import balls.Balls;
import balls.BallsSimulator;
import gui.GUISimulator;
import gui.Oval;

public class BoidsSimulator extends BallsSimulator {

	public BoidsSimulator(GUISimulator gui, int taille_x, int taille_y, Balls balls) {
		super(gui, taille_x, taille_y, balls);
	}
	
	public enum Relation {
		NON_VOISIN,
		VOISIN,
		VOISIN_IMMEDIAT
	};
	
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
	
	/* Determine la relation entre i et j : NON_VOISIN, VOISIN ou VOISIN_IMMEDIAT */
	public Relation est_voisin(int i, int j) {
		int p1_x = (int)this.getBalls().getTab()[i].getX();
		int p1_y = (int)this.getBalls().getTab()[i].getY();
		int p2_x = (int)this.getBalls().getTab()[j].getX();
		int p2_y = (int)this.getBalls().getTab()[j].getY();
		
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
		int new_x = 0;         // Nouvelle direction
		int new_y = 0;
		int nb_voisins = 0;
		Balls balls = this.getBalls();
		
		/* Calcul brut des coordonnees correspondant aux trois forces */
		for (int i = 0; i < balls.getTab().length; i++) {
			if (est_voisin(indice, i) != Relation.NON_VOISIN) {
				nb_voisins++;
				mass_center_x += (int)balls.getTab()[i].getX();
				mass_center_y += (int)balls.getTab()[i].getY();
				dir_x += balls.getDirections()[i][0];
				dir_y += balls.getDirections()[i][1];
			}
			if (est_voisin(indice, i) == Relation.VOISIN_IMMEDIAT) {
				if ((int)balls.getTab()[i].getX() > (int)balls.getTab()[indice].getX()) escape_x -= 1;
				else if ((int)balls.getTab()[i].getX() < (int)balls.getTab()[indice].getX()) escape_x += 1;
				if ((int)balls.getTab()[i].getY() > (int)balls.getTab()[indice].getY()) escape_y -= 1;
				else if ((int)balls.getTab()[i].getY() < (int)balls.getTab()[indice].getY()) escape_y += 1;
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
	
	
	@Override
	public void next() {
		Balls balls = this.getBalls();
		int[] new_direction;
		
		/* Calcul des nouvelles directions de chaque agents */
		for(int i = 0; i < balls.getTab().length; i++) {
			new_direction = new_dir(i);
			balls.setDirections(i, new_direction[0], new_direction[1]);
		}
		
		/* On translate tous les agents */
		balls.translater(10, 10, this.getTaille_x(), this.getTaille_y());
		
		/* affichage */
		this.getGui().reset();
		for (Point ball : this.getBalls().getTab()) {
			this.getGui().addGraphicalElement(new Oval(ball.x, ball.y, Color.decode("#1f77b4"), Color.decode("#1f77b4"), 10));
		 }
	}

}
