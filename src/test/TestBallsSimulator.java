package test;
import gui.GUISimulator;
import java.awt.Color;
import java.awt.Point;

import balls.Balls;
import balls.BallsSimulator;

public class TestBallsSimulator {
	public static void main(String[] args) {
		GUISimulator gui = new GUISimulator(500, 500, Color.BLACK);
		
		 Point[] tab = new Point[3];
		 for (int i = 0; i < 3; i++) {
			 tab[i] = new Point(100 + i*10, 100 + i*10);
		 }
		 Balls balls = new Balls(tab);
		 balls.setDirection(0, 1, 1);
		 balls.setDirection(1, -1, 1);
		 balls.setDirection(2, 0, 1);
		 
		gui.setSimulable(new BallsSimulator(gui, 500, 500, balls ));
	}
}
