package logic;

import java.util.Stack;


public class Settlers {
	Vector2D position;
	boolean isWorking;
	//links fürs tragen, rechts für arbeit
	Items rightHand,leftHand;
	
	//Aufgabenspeicher
	Stack<Vector2D> taskWay;	//weg zur Aufgabe
	Stack<SettlersAktion> task;	//aufgabe
	
	public Settlers(Vector2D startPosition) {
		this.position = startPosition;
		this.isWorking = false;
	}
	
	/**
	 * @brief Arbeitet seine Aufgaben ab oder tut nichts falls er keine hat
	 * @param delta time für ausführen zeitgekoppelter aktionen
	 */
	public void work(float delta) {
		if(!isWorking) {
			return;
		}
		//weg
		//task
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
