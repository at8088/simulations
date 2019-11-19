package evenement;


import java.util.LinkedList;

public class EventManager {
	
	private long currentDate;
	private LinkedList<Event> EventList;
	private Comparateur comparateur ;
	public EventManager() {
		this.currentDate = 0;
		EventList = new LinkedList<Event>();
		comparateur = new Comparateur();
	}
	
	public void addEvent(Event e) {
		EventList.addLast(e);
		
	}
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

	public boolean isFinished() {
		return EventList.isEmpty();
	}
	public void restart() {
		
	}
}
