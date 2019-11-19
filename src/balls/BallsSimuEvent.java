package balls;
import java.awt.Color;
import java.awt.Point;

import evenement.*;
import gui.Oval;
public class BallsSimuEvent extends Event {
	private BallsSimulator sim ;
	public BallsSimuEvent(long date , BallsSimulator s) {
		super(date);
		sim=s;
	}
	@Override
	public void execute() {
		sim.getBalls().translater(10, 10, sim.getTaille_x(), sim.getTaille_y());
		sim.getGui().reset();
		for (Point ball : sim.getBalls().getTab()) {
			sim.getGui().addGraphicalElement(new Oval(ball.x, ball.y, Color.decode("#1f77b4"), Color.decode("#1f77b4"), 10));
		 }
		
	}
}
