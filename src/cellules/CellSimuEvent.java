package cellules;
import evenement.*;
public class CellSimuEvent extends Event{
	private CellulesSimulator sim;
	public CellSimuEvent(long date , CellulesSimulator s) {
		super(date);
		sim=s;
	}
	@Override
	public void execute() {
		/* calcul de l'etat suivant */
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
