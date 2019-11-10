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
	private Random rand = new Random();
	private LinkedList<Integer> non_satisfaits = new LinkedList<Integer>();
	private LinkedList<Integer> satisfaits = new LinkedList<Integer>();
	private LinkedList<Integer> cellules_vides = new LinkedList<Integer>();
	 // races = blanc = etat 0 = habitaion vacante
	
	public SchellingSimulator(GUISimulator gui, int taille, Schelling habitations, LinkedList<Integer> cell_vides,int seuil) {
		this.habitations = habitations;
		this.gui = gui;
		this.Seuil = seuil;
		this.taille = taille;
		this.cellules_vides = cell_vides;
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
			if(this.habitations.getEtats()[i]!=0 ){
				buffer[i] = etat_suivant(i);
				if (buffer[i]==0 ) {
					non_satisfaits.addFirst(i);
				}		
			}
			
		}
		int size = cellules_vides.size();
		System.out.println("sz2"+cellules_vides.size());
		System.out.println("********"+satisfaits.size());
		System.out.println("non satttttt"+non_satisfaits.size());
		c=0;
		int a,j=0;
		while(cellules_vides.size() > 0 && non_satisfaits.size() > 0) {
			
			j=cellules_vides.remove((int)Math.random()*cellules_vides.size());
			a=non_satisfaits.remove();
			buffer[j]=this.habitations.getEtats()[a];
			cellules_vides.add(a);
		}
		
		for (int i = 0; i < this.habitations.getEtats().length; i++) {
			this.habitations.getEtats()[i] = buffer[i];
			
		}
		this.gui.reset();
		affiche();		
	}
	
	// int getRandomCelluleVide() {
	// 	boolean cellule_trouvee=false;
	// 	int pp=0;
	// 	int indice=0;
	// 	while(!cellule_trouvee) {
	// 		pp++;
	// 		indice = (int)(Math.random() * this.habitations.getEtats().length);
	// 		System.out.println(indice +"  "+this.habitations.getEtats()[indice] );
	// 		if (pp==15) {
	// 			break;
	// 		}
	// 		if(this.habitations.getEtats()[indice] ==0 && !j_utilise.contains(indice) ){
	// 			cellule_trouvee=true;	
	// 		}
	// 	}
	// 	return indice;
	// }

	private int etat_suivant(int indice) {
		int compteur = 0;
		int compteur_vides = 0;
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


		compteur_vides += this.habitations.isInState(i_inf * this.taille + j_inf ,0 );
		compteur_vides += this.habitations.isInState(  i * this.taille+ j_inf  ,0);
		compteur_vides += this.habitations.isInState( i_sup * this.taille+ j_inf, 0);
		compteur_vides += this.habitations.isInState( i_inf * this.taille + j   , 0);
		compteur_vides += this.habitations.isInState(  i_sup * this.taille + j  , 0);
		compteur_vides += this.habitations.isInState( i_inf * this.taille + j_sup , 0);
		compteur_vides += this.habitations.isInState(  i * this.taille + j_sup ,0);
		compteur_vides += this.habitations.isInState( i_sup * this.taille + j_sup  ,0);
		if (compteur_vides == 8) {
			return this.habitations.getEtats()[indice];
		}else{
			return compteur >= Seuil ? 0  : this.habitations.getEtats()[indice]  ; 
		}
		
		
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
