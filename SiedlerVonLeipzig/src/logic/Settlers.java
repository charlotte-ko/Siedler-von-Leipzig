package logic;

import java.util.ArrayDeque;
import java.util.Deque;


public class Settlers {
	Vector2D position;
	boolean isWorking;
	//links fürs tragen, rechts für arbeit
	Items rightHand,leftHand;
	
	private final float speed = 4.0f;
	//Aufgabenspeicher (deque besser als stack)
	private Deque<Vector2D> taskWay = new ArrayDeque<>(); //Weg zur Aufgabe
	private Deque<SettlersAktion> task = new ArrayDeque<>();	//aufgabe
	
	public Settlers(Vector2D startPosition) {
		this.position = startPosition;
		this.isWorking = false;
	}
	
	/**
	 * @brief Arbeitet seine Aufgaben ab oder tut nichts falls er keine hat
	 * @param delta time für ausführen zeitgekoppelter aktionen
	 */
	public void work(float delta) {
		if(task.isEmpty()) {
			this.isWorking = false;
			return;
		}
		this.isWorking = true;
		
		//got to funktion
		switch(task.peek()) {
			case GO_PICKUP_LEFT:
				break;
			case GO_PICKUP_RIGHT:
				break;
			case GO_BRING_LEFT:
				break;
			case GO_BRING_RIGHT:
				break;
			case GO_WORK:
				break;
			case GO_FREE:
				break;
			default:
				break;
		}
	}
	
	/**
	 * @brief interne methode um zum gewünschten Platz zu laufen
	 * 
	 * @param delta nötig für die Bewegung
	 * @return true falls angekommen, ansonsten false
	 */
	private boolean goTo(float delta) {
		Vector2D target = taskWay.peek();
		float distance = target.getDistance(target);
		
		if(distance <= 1.0f) {
			return true; //ist auf dem Punkt
		}
		Vector2D direction = this.position.getTwoPointVector(target).normalize();
		this.position.x = direction.x * this.speed;
		this.position.y = direction.y * this.speed;
		return false;
	}
	
	/**
	 * @brief speichert eine nächste Aufgabe für den Siedler ab
	 * @param go wo hin soll gegangen werden
	 * @param aktion was soll gemacht werden
	 */
	public void giveSiedlerTask(Vector2D go,SettlersAktion aktion) {
		this.taskWay.push(go);
		this.task.push(aktion);
	}
}
