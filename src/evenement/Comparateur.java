package evenement;

import java.util.Comparator;

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
