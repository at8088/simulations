package immigration;

import evenement.Event;

public class CellImmSimuEvent extends Event {

	private CellulesImmigrantesSimulator sim;
	public CellImmSimuEvent(long date , CellulesImmigrantesSimulator s) {
		super(date);
		sim = s;
		
	}
	@Override
	public void execute() {
		for (int i = 0; i < sim.getCellules().getTab().length; i++) {
			sim.getBuffer()[i] = sim.etat_suivant(i);
		}
		
		/* mise a jour du tableau des etats */
		for (int i = 0; i < sim.getCellules().getTab().length; i++) {
			sim.getCellules().getEtats()[i] = sim.getBuffer()[i];
		}
		
		/* affichage*/
		sim.getGui().reset();
		sim.affiche();
		
	}

}
