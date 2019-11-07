package schelling;

import java.awt.Color;

import gui.GUISimulator;
import gui.Simulable;

import java.util.LinkedList;
import java.util.Random;
import gui.Rectangle;

public class SchellingSimulator implements Simulable {

	private Schelling habitations;
	private int[] buffer;
	private GUISimulator gui;
	private int taille; 
	private Color races[];   
	private int Seuil;
	private LinkedList<Integer> non_satisfait = new LinkedList<Integer>();
	 // races = blanc = etat 0 = habitaion vacante
	
	public SchellingSimulator(GUISimulator gui, int taille, Schelling habitations, int seuil) {
		this.habitations = habitations;
		this.gui = gui;
		this.Seuil = seuil;
		this.taille = taille;
		buffer = new int[habitations.getTab().length];
		races = new Color[this.habitations.getNbrRaces() + 1];
		races[0]=Color.WHITE;
	
		for(int i=1; i<this.habitations.getNbrRaces()+1;i++) {
			Random rand = new Random();
			float r = rand.nextFloat();
			float g = rand.nextFloat();
			float b = rand.nextFloat();
			races[i]= new Color(r,g,b);
		}
		affiche();
	}
	private void affiche() {
		for (int i = 0; i < this.habitations.getEtats().length; i++) {
			this.gui.addGraphicalElement(new Rectangle((int)(this.habitations.getTab()[i].getX()),
					(int)(this.habitations.getTab()[i].getY()), races[this.habitations.getEtats()[i]], 
					races[this.habitations.getEtats()[i]], 10));
		}
	}
	@Override
	public void next() {
		int c=0;

		for (int i = 0; i < this.habitations.getTab().length; i++) {
			buffer[i] = etat_suivant(i);
			if (etat_suivant(i)==0 && this.habitations.getEtats()[i]!=0 ) {
				non_satisfait.addFirst(i);
			}		
			if(this.habitations.getEtats()[i]==0) c++;
		}
		System.out.println(c);

		c=0;
		int j=0;
		for(Integer i:non_satisfait) {
			j=getRandomCelluleVide();
			buffer[j] = this.habitations.getEtats()[i];	
		}
		for (int i = 0; i < this.habitations.getEtats().length; i++) {
			this.habitations.getEtats()[i] = buffer[i];
			
		}
		for (int i = 0; i < this.habitations.getEtats().length; i++) {
			if(this.habitations.getEtats()[i]==0) c++;
			
		}
		System.out.println(c);
		non_satisfait.clear();


		this.gui.reset();
		affiche();		
	}
	
	int getRandomCelluleVide() {
		boolean cellule_trouvee=false;
		int indice=0;
		while(!cellule_trouvee) {

			indice = (int)(Math.random() * this.habitations.getEtats().length);
			if(this.habitations.getEtats()[indice] ==0 ) {
				cellule_trouvee=true;	
			}
		}
		return indice;
	}

	private int etat_suivant(int indice) {
		int compteur = 0;
		int i = indice / this.taille;
		int j = indice % this.taille;
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

		
		return compteur >= Seuil ? 0  : this.habitations.getEtats()[indice]  ; 
		
	}
	@Override
	public void restart() {
		habitations.reInit(this.taille);
		this.gui.reset();
		affiche();		
	}
	public int getSeuil() {
		return Seuil;
	}
	public void setSeuil(int seuil) {
		Seuil = seuil;
	}

}
