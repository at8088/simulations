package Boids;

import cellules.Cellules;
import evenement.Event;

public class BoidsSimuEvent extends Event{

	private BoidsSimulator sim;
	public BoidsSimuEvent(long date , BoidsSimulator s) {
		super(date);
		sim = s;
	}
	@Override
	public void execute() {
		
		Cellules cellules = sim.getCellules();
		int[] new_direction;
		
		/* Calcul des nouvelles directions de chaque agents */
		for(int i = 0; i < cellules.getTab().length; i++) {
			new_direction = sim.new_dir(i);
			cellules.setDirections(i, new_direction[0], new_direction[1]);
		}
		
		/* On translate tous les agents */
		cellules.translater(10, 10, sim.getTaille(), sim.getTaille());
		
		/* affichage */
		sim.getGui().reset();
		sim.affiche();
	}

}
