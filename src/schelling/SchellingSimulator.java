package schelling;

import java.awt.Color;

import gui.GUISimulator;
import gui.Simulable;
import immigration.CellImmSimuEvent;

import java.util.LinkedList;
import java.util.Random;

import evenement.EventManager;
import gui.Rectangle;

public class SchellingSimulator implements Simulable {

	private Schelling habitations;
	private int[] buffer;
	private GUISimulator gui;
	private int taille; 
	private Color races[];   
	private int Seuil;
	private LinkedList<Integer> non_satisfaits = new LinkedList<Integer>();
	private LinkedList<Integer> cellules_vides = new LinkedList<Integer>();
	private EventManager manager;

	public SchellingSimulator(GUISimulator gui, int taille, Schelling habitations, LinkedList<Integer> cell_vides,int seuil) {
		this.habitations = habitations;
		this.gui = gui;
		this.Seuil = seuil;
		this.taille = taille;
		this.manager = new EventManager();
		this.cellules_vides = cell_vides;
		buffer = new int[habitations.getTab().length];
		races = new Color[this.habitations.getNbrRaces() + 1];
		races[0]=Color.WHITE;
		Random rand = new Random();
		float r,b,g;
		for(int i=1; i<this.habitations.getNbrRaces()+1;i++) {
			r = rand.nextFloat();
			g = rand.nextFloat();
			b = rand.nextFloat();
			races[i]= new Color(r,g,b);
		}
		affiche();
	}
	public void affiche() {
		for (int i = 0; i < this.habitations.getEtats().length; i++) {
			this.gui.addGraphicalElement(new Rectangle((int)(this.habitations.getTab()[i].getX()),
					(int)(this.habitations.getTab()[i].getY()), races[this.habitations.getEtats()[i]], 
					races[this.habitations.getEtats()[i]], 10));
		}
	}
	@Override
	public void next() {
//		for (int i = 0; i < this.habitations.getTab().length; i++) {
//			if(this.habitations.getEtats()[i]!=0 ){
//				buffer[i] = etat_suivant(i);
//				if (buffer[i]==0 ) {
//					non_satisfaits.addFirst(i);
//				}		
//			}
//		}
//		
//		System.out.println("nbr de familles non satisfaites = "+non_satisfaits.size());
//		int a,j;
//		while(cellules_vides.size() > 0 && non_satisfaits.size() > 0) {
//			j=cellules_vides.remove((int)Math.random()*cellules_vides.size());
//			a=non_satisfaits.remove();
//			buffer[j]=this.habitations.getEtats()[a];
//			cellules_vides.add(a);
//		}
//
//		for (int i = 0; i < this.habitations.getEtats().length; i++) {
//			this.habitations.getEtats()[i] = buffer[i];
//		}
//		this.gui.reset();
//		affiche();		
		this.manager.addEvent(new SchellingSimuEvent(this.manager.getCurrentDate()+1,this));
		this.manager.next();
		
		
	}

	public Schelling getHabitations() {
		return habitations;
	}
	public int[] getBuffer() {
		return buffer;
	}
	public GUISimulator getGui() {
		return gui;
	}
	public Color[] getRaces() {
		return races;
	}
	public LinkedList<Integer> getNon_satisfaits() {
		return non_satisfaits;
	}
	public LinkedList<Integer> getCellules_vides() {
		return cellules_vides;
	}
	public int etat_suivant(int indice) {
		int compteur = 0;
		int i = indice / this.taille;
		int j = indice % this.taille;
		
		
		if(i!=this.taille-1 && i!=0 && j != this.taille-1 && j!=0) {
			int i_inf = i == 0 ? this.taille - 1 : i - 1;
			int j_inf = j == 0 ? this.taille - 1 : j -1;
			int i_sup = i == this.taille - 1 ? 0 : i + 1;
			int j_sup = j == this.taille - 1 ? 0 : j + 1;
			compteur += 1- this.habitations.isInState(i_inf * this.taille + j_inf ,(this.habitations.getEtats()[indice] ));
			compteur += 1- this.habitations.isInState(  i * this.taille+ j_inf  ,(this.habitations.getEtats()[indice] ));
			compteur += 1- this.habitations.isInState( i_sup * this.taille+ j_inf, (this.habitations.getEtats()[indice] ));
			compteur += 1- this.habitations.isInState( i_inf * this.taille + j   , (this.habitations.getEtats()[indice] ));
			compteur += 1- this.habitations.isInState(  i_sup * this.taille + j  , (this.habitations.getEtats()[indice] ));
			compteur += 1- this.habitations.isInState( i_inf * this.taille + j_sup , (this.habitations.getEtats()[indice] ));
			compteur += 1- this.habitations.isInState(  i * this.taille + j_sup ,(this.habitations.getEtats()[indice] ));
			compteur += 1- this.habitations.isInState( i_sup * this.taille + j_sup  ,(this.habitations.getEtats()[indice] ));
			
		}else if (i==0){
			if(j==0) {
				compteur += 1- this.habitations.isInState(0*this.taille + 1,(this.habitations.getEtats()[indice] ));
				compteur += 1- this.habitations.isInState(1*this.taille + 0,(this.habitations.getEtats()[indice] ));
				compteur += 1- this.habitations.isInState(1*this.taille + 1,(this.habitations.getEtats()[indice] ));
				
			}else if(j==this.taille-1) {
				compteur += 1- this.habitations.isInState(0*this.taille + j-1,(this.habitations.getEtats()[indice] ));
				compteur += 1- this.habitations.isInState(1*this.taille + j-1,(this.habitations.getEtats()[indice] ));
				compteur += 1- this.habitations.isInState(1*this.taille + j,(this.habitations.getEtats()[indice] ));
			}
			else {
				compteur += 1- this.habitations.isInState(i*this.taille + j+1,(this.habitations.getEtats()[indice] ));
				compteur += 1- this.habitations.isInState(i*this.taille + j-1,(this.habitations.getEtats()[indice] ));
				compteur += 1- this.habitations.isInState((i+1)*this.taille + j-1,(this.habitations.getEtats()[indice] ));
				compteur += 1- this.habitations.isInState((i+1)*this.taille + j,(this.habitations.getEtats()[indice] ));
				compteur += 1- this.habitations.isInState((i+1)*this.taille + j+1,(this.habitations.getEtats()[indice] ));
			}
			
		}else if(i==this.taille-1){
			if(j==0) {
				compteur += 1- this.habitations.isInState((i-1)*this.taille + 0,(this.habitations.getEtats()[indice] ));
				compteur += 1- this.habitations.isInState((i-1)*this.taille + 1,(this.habitations.getEtats()[indice] ));
				compteur += 1- this.habitations.isInState(i*this.taille + 1,(this.habitations.getEtats()[indice] ));
				
			}else if(j==this.taille-1) {
				compteur += 1- this.habitations.isInState(i*this.taille + j-1,(this.habitations.getEtats()[indice] ));
				compteur += 1- this.habitations.isInState((i-1)*this.taille + j-1,(this.habitations.getEtats()[indice] ));
				compteur += 1- this.habitations.isInState((i-1)*this.taille + j,(this.habitations.getEtats()[indice] ));
			}
			else {
				compteur += 1- this.habitations.isInState(i*this.taille + j+1,(this.habitations.getEtats()[indice] ));
				compteur += 1- this.habitations.isInState(i*this.taille + j-1,(this.habitations.getEtats()[indice] ));
				compteur += 1- this.habitations.isInState((i-1)*this.taille + j-1,(this.habitations.getEtats()[indice] ));
				compteur += 1- this.habitations.isInState((i-1)*this.taille + j,(this.habitations.getEtats()[indice] ));
				compteur += 1- this.habitations.isInState((i-1)*this.taille + j+1,(this.habitations.getEtats()[indice] ));
			}
		}else if(j==0) {
			compteur += 1- this.habitations.isInState(i*this.taille + j+1,(this.habitations.getEtats()[indice] ));
			compteur += 1- this.habitations.isInState((i-1)*this.taille + j,(this.habitations.getEtats()[indice] ));
			compteur += 1- this.habitations.isInState((i-1)*this.taille + j+1,(this.habitations.getEtats()[indice] ));
			compteur += 1- this.habitations.isInState((i+1)*this.taille + j,(this.habitations.getEtats()[indice] ));
			compteur += 1- this.habitations.isInState((i+1)*this.taille + j+1,(this.habitations.getEtats()[indice] ));
		}else if(j==this.taille-1) {
			compteur += 1- this.habitations.isInState(i*this.taille + j-1,(this.habitations.getEtats()[indice] ));
			compteur += 1- this.habitations.isInState((i-1)*this.taille + j,(this.habitations.getEtats()[indice] ));
			compteur += 1- this.habitations.isInState((i-1)*this.taille + j-1,(this.habitations.getEtats()[indice] ));
			compteur += 1- this.habitations.isInState((i+1)*this.taille + j,(this.habitations.getEtats()[indice] ));
			compteur += 1- this.habitations.isInState((i+1)*this.taille + j-1,(this.habitations.getEtats()[indice] ));
		}
		
		return compteur >= Seuil ? 0  : this.habitations.getEtats()[indice]  ; 
		
		
	}
	@Override
	public void restart() {
		habitations.reInit(this.taille);
		this.gui.reset();
		affiche();		
	}

}
