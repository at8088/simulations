package schelling;
import java.awt.Point;

import cellules.Cellules;
public class Schelling extends Cellules {
	
	private int nbrRaces;
	
	public Schelling(Point[] tab, int[] etats , int nbrraces) {
		super(tab, etats);
		setNbrRaces(nbrraces);
	}
	@Override
	public int isInState(int indice ,int k) {
		if(this.getEtats()[indice] == k || this.getEtats()[indice] == 0){
			return 1;
		}else{
			return 0;
		}
	}

	public int getNbrRaces() {
		return nbrRaces;
	}

	public void setNbrRaces(int nbrRaces) {
		this.nbrRaces = nbrRaces;
	}
	

}
