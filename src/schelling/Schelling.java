package schelling;
import java.awt.Point;

import cellules.Cellules;
public class Schelling extends Cellules {
	
	private int nbrRaces;
	
	public Schelling(Point[] tab, int[] etats , int nbrraces) {
		super(tab, etats);
		setNbrRaces(nbrraces);
	}

	public int getNbrRaces() {
		return nbrRaces;
	}

	public void setNbrRaces(int nbrRaces) {
		this.nbrRaces = nbrRaces;
	}
	

}
