package test;

import java.awt.Color;
import java.awt.Point;


import immigration.*;
import gui.GUISimulator;

public class TestCellulesImmigrantes {
	public static void main(String[] args) {
		GUISimulator gui = new GUISimulator(500, 500, Color.WHITE);
		int taille = 500 / 10;
		
		Point[] tab = new Point[taille * taille]; // on cree le tableau de point
		for (int i = 0; i < taille; i++) {
			for (int j = 0; j < taille; j++) {
				tab[taille * i + j] = new Point(i * 10, j * 10);
			}
		}
		int nbrEtats=4;
		
		int etats[] = new int[taille * taille]; // on genere le tableau des etats aleatoirement
		int random;
		for (int i = 0; i < etats.length; i++) {
			random = (int)(Math.random() * nbrEtats);
			
			etats[i] = random;
		}
		
		CellulesImmigrantes cellules = new CellulesImmigrantes(tab,etats,nbrEtats);
		
		gui.setSimulable(new CellulesImmigrantesSimulator(gui, taille, cellules));
		
		
		
		
		
	}
}
