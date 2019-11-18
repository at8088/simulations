package test;
import gui.GUISimulator;
import java.awt.Color;
import java.awt.Point;

import balls.Balls;
import balls.BallsSimulator;

public class TestBallsSimulator {
	public static void main(String[] args) {
		int WIDTH = 600 , HEIGHT = 600;
		GUISimulator gui = new GUISimulator(WIDTH, HEIGHT, Color.BLACK);
		

		 Point[] tab = new Point[5];
		 for (int i = 0; i < 5; i++) {
			 tab[i] = new Point(10+i*10, 50+i*10);
		 }
		 Balls balls = new Balls(tab);
		 balls.setDirections(1,-1 , 1);
		 balls.setDirections(0, 1 , 1);	
		 balls.setDirections(2,1 , 1);
		 balls.setDirections(3,1 , -1);
		 balls.setDirections(4,-1 , -1);

		gui.setSimulable(new BallsSimulator(gui, 500, 500, balls ));
	}
}
