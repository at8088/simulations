package test;

import java.awt.Color;
import java.awt.Point;

import cellules.Cellules;
import cellules.CellulesSimulator;
import gui.GUISimulator;

public class TestCellulesSimulator {
	public static void main(String[] args) {
		GUISimulator gui = new GUISimulator(500, 500, Color.BLACK);
		
		/* les rectangles font 10 de largeur et de hauteur donc il y a 
		 * 500/10 rectangles par lignes et par colonnes
		 */
		int taille = 500 / 10;
		
		Point[] tab = new Point[taille * taille]; // on cree le tableau de point
		for (int i = 0; i < taille; i++) {
			for (int j = 0; j < taille; j++) {
				tab[taille * i + j] = new Point(i * 10, j * 10);
			}
		}
		
		int etats[] = new int[taille * taille]; // on genere le tableau des etats aleatoirement
		int random;
		for (int i = 0; i < etats.length; i++) {
			random = (int)(Math.random() * 7);
			etats[i] = random == 0 ? 1 : 0;
		}
		
		Cellules cellules = new Cellules(tab, etats);
		 
		gui.setSimulable(new CellulesSimulator(gui, taille, cellules));
	}
}
