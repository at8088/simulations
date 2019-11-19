package test;

import java.awt.Color;
import java.awt.Point;

import Boids.Boids;
import Boids.BoidsSimulator;
import gui.GUISimulator;

public class TestBoidsSimulator {
	public static void main(String[] args) {
		int WIDTH = 600 , HEIGHT = 600;
		GUISimulator gui = new GUISimulator(WIDTH, HEIGHT, Color.WHITE);
		
		int nb_agents = 50; // A FAIRE VARIER

		 Point[] tab = new Point[nb_agents];
		 for (int i = 0; i < nb_agents; i++) {
			 tab[i] = new Point((int)(Math.random() * (500 + 1)), (int)(Math.random() * (500 + 1)));
		 }
		 int[] etats = new int[nb_agents];
		 for (int i = 0; i < nb_agents; i++) { 
			 etats[i] = (int)(Math.random() * 5); // si etats 
		 }
		 
		 Boids boids = new Boids(tab, etats);
		 for (int i = 0; i < nb_agents; i++) {
			 boids.setDirections(i, (int)(1 - Math.random() * 2), (int)(1 - Math.random() * 2));
		 }
		 
		gui.setSimulable(new BoidsSimulator(gui, 500, boids));
	}
}
