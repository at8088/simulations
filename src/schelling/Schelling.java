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
	public boolean VoisinsVides(int indice) {
		int compteur = 0;
		int i = indice / this.getEtats().length;
		int j = indice % this.getEtats().length;
		int i_inf = i == 0 ? this.getEtats().length - 1 : i - 1;
		int j_inf = j == 0 ? this.getEtats().length - 1 : j -1;
		int i_sup = i == this.getEtats().length - 1 ? 0 : i + 1;
		int j_sup = j == this.getEtats().length - 1 ? 0 : j + 1;
		boolean var = false;
		if(this.getEtats()[])
	}

}
