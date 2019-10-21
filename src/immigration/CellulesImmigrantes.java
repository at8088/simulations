package immigration;

import java.awt.Point;

import cellules.Cellules;
public class CellulesImmigrantes extends Cellules {
	private final int nbrEtats = 4;
	
	public CellulesImmigrantes(Point[] tab , int etats[]) {
		super(tab,etats);
	}
	
	public int getNbrEtats() {
		return nbrEtats;
	}
	
	

}
