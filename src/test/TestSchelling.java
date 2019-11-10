package test;

import java.awt.Color;
import java.awt.Point;
import java.util.LinkedList;

import gui.GUISimulator;
import schelling.*;

public class TestSchelling {
	public static void main(String[] args) {
		LinkedList <Integer> cell_vides = new LinkedList<Integer>();
		GUISimulator gui = new GUISimulator(500, 500, Color.WHITE);
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
			random = (int)(Math.random() * 3);
			
			etats[i] = random;
			if (etats[i] == 0) {
				cell_vides.addFirst(i);
			
			}
		}
		for (int i = 50; i < etats.length / 3; i++) {
			etats[i] = i%2==0 ? 2:1;
		}
		
		Schelling cellules = new Schelling(tab,etats,2);
		
		gui.setSimulable(new SchellingSimulator(gui, taille, cellules,cell_vides,3));

	}
}
