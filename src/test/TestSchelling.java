package test;

import java.awt.Color;
import java.awt.Point;
import java.util.LinkedList;

import gui.GUISimulator;
import schelling.*;

public class TestSchelling {
	public static void main(String[] args) {
		int WIDTH = 500 , HEIGHT = 500;
		int nbr_races = 2;
		int seuil = 7;
		LinkedList <Integer> cell_vides = new LinkedList<Integer>();
		GUISimulator gui = new GUISimulator(WIDTH, HEIGHT, Color.WHITE);
		int taille = WIDTH / 10;
		
		Point[] tab = new Point[taille * taille]; // on cree le tableau de point
		for (int i = 0; i < taille; i++) {
			for (int j = 0; j < taille; j++) {
				tab[taille * i + j] = new Point(i * 10, j * 10);
			}
		}
		int etats[] = new int[taille * taille]; // on genere le tableau des etats aleatoirement
		int random;
		for (int i = 0; i < etats.length; i++) {
			random = (int)(Math.random() * (nbr_races+1));
			
			etats[i] = random;
			if (etats[i] == 0) {
				cell_vides.addFirst(i);
			
			}
		}

		Schelling cellules = new Schelling(tab,etats,nbr_races);
		
		gui.setSimulable(new SchellingSimulator(gui, taille, cellules,cell_vides,seuil));

	}
}
