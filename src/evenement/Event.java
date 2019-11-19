package evenement;



public abstract class Event extends EventManager {
	private long date;
	
	
	
	public Event(long date) {
		this.date = date ;
	}
	public long getDate() {
		return date;
	}
	abstract public void execute() ;

}
