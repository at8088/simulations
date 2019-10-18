package balls;
import java.awt.Color;
import java.awt.Point;

import gui.GUISimulator;
import gui.Oval;
import gui.Simulable;

public class BallsSimulator implements Simulable {
	private Balls balls;
	private GUISimulator gui;
	private int taille_x;
	private int taille_y;

	 public BallsSimulator(GUISimulator gui, int taille_x, int taille_y, Balls balls){
		 this.balls = balls;
		 this.gui = gui;
		 this.taille_x = taille_x;
		 this.taille_y = taille_y;
		 
		 afficher_ovale();
	 }
	 
	private void afficher_ovale() {
		for (Point ball : this.balls.getTab()) {
			 gui.addGraphicalElement(new Oval(ball.x, ball.y, Color.decode("#1f77b4"), Color.decode("#1f77b4"), 10));
		 }
	}
	
	public Balls getBalls() {
		return balls;
	}
	
	public GUISimulator getGui() {
		return gui;
	}

	public int getTaille_x() {
		return taille_x;
	}

	public int getTaille_y() {
		return taille_y;
	}

	public void setBalls(Balls balls) {
		this.balls = balls;
	}



	@Override
	public void next() {
		balls.translater(10, 10, this.taille_x, this.taille_y);
		gui.reset();
		for (Point ball : this.balls.getTab()) {
			gui.addGraphicalElement(new Oval(ball.x, ball.y, Color.decode("#1f77b4"), Color.decode("#1f77b4"), 10));
		 }
	}

	@Override
	public void restart() {
		balls.reInit();
		gui.reset();
		for (Point ball : this.balls.getTab()) {
			gui.addGraphicalElement(new Oval(ball.x, ball.y, Color.decode("#1f77b4"), Color.decode("#1f77b4"), 10));
		 }
	}

}
