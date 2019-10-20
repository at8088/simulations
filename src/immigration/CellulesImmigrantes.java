package immigration;

import java.awt.Point;

import balls.Balls;
public class CellulesImmigrantes extends Balls {
	private int nbrEtats;
	private int etats[];
	private int etats_ini[];
	
	public CellulesImmigrantes(Point[] tab , int etats[],int nbretats) {
		super(tab);
		this.etats = new int[etats.length];
		this.etats_ini = new int[etats.length];
		System.arraycopy(etats, 0, this.etats, 0, etats.length);
		System.arraycopy(etats, 0, this.etats_ini, 0, etats.length);
		this.nbrEtats = nbretats;
	}
	
	public int getNbrEtats() {
		return nbrEtats;
	}

	public void setNbrEtats(int nbrEtats) {
		this.nbrEtats = nbrEtats;
	}

	public int[] getEtats() {
		return etats;
	}

	public void setEtats(int[] etats) {
		this.etats = etats;
	}

	public int[] getEtats_ini() {
		return etats_ini;
	}

	public void setEtats_ini(int[] etats_ini) {
		this.etats_ini = etats_ini;
	}
	
	public int isInState(int indice ,int k) {
		int var = (etats[indice]==k) ? 1:0 ;
		return var;
	}
	
	public void reInit(int taille) {
		super.reInit();
		System.arraycopy(this.etats_ini, 0, this.etats, 0, taille * taille);
	}



}
