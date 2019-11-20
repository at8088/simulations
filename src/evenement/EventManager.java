package evenement;


import java.util.LinkedList;

public class EventManager {
	
	private long currentDate;
	/**Liste des evenemments a executer.
	 * */
	private LinkedList<Event> EventList;
	/**Comparateur d'evenements
	 * */
	private Comparateur comparateur ;
	public EventManager() {
		this.currentDate = 0;
		EventList = new LinkedList<Event>();
		comparateur = new Comparateur();
	}
	/**Ajoute un evenement en queue de la liste.
	 * */
	public void addEvent(Event e) {
		EventList.addLast(e);
	}
	
	/**Incremente la date, trie la liste des evenements, supprime et execute la tete
	 * au bon moment.
	 * */
	public void next() {
		currentDate++ ;			
		System.out.println("Next : current date = "+currentDate);
		EventList.sort(comparateur);
		Event ev;
		
		while(!isFinished() && EventList.element().getDate() == currentDate) {
				ev = EventList.removeFirst();
				ev.execute();
		}

	}
	public long getCurrentDate() {
		return currentDate;
	}

	public void reInitCurrentDate() {
		this.currentDate = 0;
	}

	/**Renvoie true si la liste des evenments est vide
	 * false sinon.
	 * */
	public boolean isFinished() {
		return EventList.isEmpty();
	}
	public void restart() {
		
	}
}
