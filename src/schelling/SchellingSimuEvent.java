package schelling;

import evenement.Event;

public class SchellingSimuEvent extends Event{

	private SchellingSimulator sim;
	public SchellingSimuEvent(long date , SchellingSimulator s) {
		super(date);
		sim = s;
	}
	@Override
	public void execute() {
		for (int i = 0; i < sim.getHabitations().getTab().length; i++) {
			if( sim.getHabitations().getEtats()[i]!=0 ){
				sim.getBuffer()[i] = sim.etat_suivant(i);
				if (sim.getBuffer()[i]==0 ) {
					sim.getNon_satisfaits().addFirst(i);
				}		
			}
		}
		
		System.out.println("nbr de familles non satisfaites = "+sim.getNon_satisfaits().size());
		int a,j;
		while(sim.getCellules_vides().size() > 0 && sim.getNon_satisfaits().size() > 0) {
			j=sim.getCellules_vides().remove((int)Math.random()*sim.getCellules_vides().size());
			a=sim.getNon_satisfaits().remove();
			sim.getBuffer()[j]= sim.getHabitations().getEtats()[a];
			sim.getCellules_vides().add(a);
		}

		for (int i = 0; i <  sim.getHabitations().getEtats().length; i++) {
			 sim.getHabitations().getEtats()[i] = sim.getBuffer()[i];
		}
		sim.getGui().reset();
		sim.affiche();	
		
	}

}
