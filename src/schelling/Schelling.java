package schelling;
import java.awt.Point;

import cellules.Cellules;
public class Schelling extends Cellules {
	
	/**Nombre de races ie couleurs. Modifiable*/
	private int nbrRaces;
	
	public Schelling(Point[] tab, int[] etats , int nbrraces) {
		super(tab, etats);
		nbrRaces = nbrraces;
	}
	
	
	/**Retourne 1 si l'habitation d'indice "indice" est dans l'etat k ou vide 
	 * 0 sinon.
	 * */
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

}
