package buildings;

import java.util.ArrayList;

import items.Log;
import logic.Vector2D;
import settlers.ISettlers;

public class Forester extends Buildings {

	public Forester(Vector2D pos) {
		super("Forester", 10, pos);
	}

	/**
	 * Methode fügt einen Baumstamm dem Inventar zu sobald Arbeiter bei der Hütte ist
	 */
	public void makeLogs(ISettlers worker) {
		
		if(this.getPosition().getDistance(worker.getPosition()) <= 1.0f) {
			inventory.add(new Log(this.getPosition()));
		}
	}
	
	/**
	 * Kontrollausgabe um das aktuelle Inventar des Objektes auszugeben
	 */
	public void produce() {System.out.println("Inventar: " + inventory);}

	@Override
	public void createBuilding() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ISettlers makeSettlers(ArrayList<ISettlers> settlerList) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
 }
