package evenement;

import java.util.Comparator;

/**Comparateur des evenements. Il est utilisé pour le tri de la liste des evenements à executer.
 * Le ciritere de comparaison est la date.
 * */

public class Comparateur implements Comparator<Event>{

	@Override
	public int compare(Event e1, Event e2) {
		if(e1.getDate() < e2.getDate()) {
			return -1;
		}else {
			return 1;
		}
	}

}
