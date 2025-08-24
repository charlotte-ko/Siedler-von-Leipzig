package buildings;


import java.util.ArrayList;

import items.Plank;
import settlers.ISettlers;
import logic.Vector2D;

public class Sawmill extends Buildings {

	private static Vector2D spawn =  new Vector2D();
	private static ArrayList<ISettlers> hasSaw = new ArrayList<>();
	
	/**
	 * Konstruktor, übergibt Namen und Preis
	 */
	public Sawmill(Vector2D pos) {
		super("Sawmill", 10, spawn);
	}

	/**
	 * Methode fügt ein Brett dem Inventar zu wenn Siedler ankommt
	 */
	public void makePlanks(ISettlers worker) {
		
		if(this.getPosition().getDistance(worker.getPosition()) <= 1.0f) {
			inventory.add(new Plank(this.getPosition()));
		}	
	}
	
	/**
	 * Methode um nächsten Siedler mit Säge zu finden, sollte die Liste hasSaw lieber in der Methode initialisiert werden?
	 */
	@Override
	public ISettlers findSettler(Vector2D current_pos, ArrayList<ISettlers> settlerList) {
		
		for(ISettlers s : settlerList) {
			if(s.getItemHand(false).getName() == "Saw") {
				hasSaw.add(s);
			}
		}
		return closestSettler(current_pos, hasSaw);
	}
	
	/**
	 * Kontrollausgabe um Inventar auszugeben
	 */
	public void produce() {System.out.println(inventory);}

	@Override
	public void createBuilding() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void makeSettlers() {
		// TODO Auto-generated method stub
		
	}
}
