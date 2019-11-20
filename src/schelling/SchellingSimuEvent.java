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
		
		/**Calcul de l'etat suivant de chaque habitation occupée
		 * Et si la famille occupante est non satisfaite  
		 * on l'ajoute dans une liste
		 * */
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
		
		/**on prend une habitations vides aleatoirement d'une liste
		 *  et on la donne a une famille non satisfaite tant qu'il en reste 
		 * */
		while(sim.getCellules_vides().size() > 0 && sim.getNon_satisfaits().size() > 0) {
			j=sim.getCellules_vides().remove((int)Math.random()*sim.getCellules_vides().size());
			a=sim.getNon_satisfaits().remove();
			sim.getBuffer()[j]= sim.getHabitations().getEtats()[a];
			sim.getCellules_vides().add(a);
		}

		/**Mise a jour des etats des cellules*/
		for (int i = 0; i <  sim.getHabitations().getEtats().length; i++) {
			 sim.getHabitations().getEtats()[i] = sim.getBuffer()[i];
		}
		sim.getGui().reset();
		sim.affiche();	
		
	}

}
