package Balls;
import java.awt.Color;
import java.awt.Point;

import gui.GUISimulator;
import gui.Oval;
import gui.Simulable;

public class BallsSimulator implements Simulable {
	private Balls balls;
	private GUISimulator gui;

	 public BallsSimulator(GUISimulator gui){
		 Point[] tab = new Point[3];
		 for (int i = 0; i < 3; i++) {
			 tab[i] = new Point(100 + i*10, 100 + i*10);
		 }
		 this.balls = new Balls(tab);
		 this.gui = gui;
		 for (Point ball : this.balls.getBalls_tab()) {
			 gui.addGraphicalElement(new Oval(ball.x, ball.y, Color.decode("#1f77b4"), Color.decode("#1f77b4"), 10));
		 }
	 }

	@Override
	public void next() {
		balls.translater(10, 10);
		gui.reset();
		for (Point ball : this.balls.getBalls_tab()) {
			gui.addGraphicalElement(new Oval(ball.x, ball.y, Color.decode("#1f77b4"), Color.decode("#1f77b4"), 10));
		 }
	}

	@Override
	public void restart() {
		balls.reInit();
		gui.reset();
		for (Point ball : this.balls.getBalls_tab()) {
			gui.addGraphicalElement(new Oval(ball.x, ball.y, Color.decode("#1f77b4"), Color.decode("#1f77b4"), 10));
		 }
	}

}
