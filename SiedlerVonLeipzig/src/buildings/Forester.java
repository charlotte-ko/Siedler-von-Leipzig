package buildings;

import java.util.ArrayList;
import java.util.Vector;

import items.Log;
import logic.Vector2D;
import settlers.ISettlers;

public class Forester extends Buildings {
	
	private static Vector2D spawn = new Vector2D();
	private static ArrayList<ISettlers> hasAxe = new ArrayList<>();

	public Forester(Vector2D pos) {
		super("Forester", 10, spawn);
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

	/**
	 * Methode, die nächstgelegenen Siedler zum Bäume fällen sucht, wir brauchen noch einen Getter für die Items die von Siedlern gehalten werden
	 */
	@Override
	public ISettlers findSettler(Vector2D current_pos, ArrayList<ISettlers> settlerList) {
		
		for(ISettlers s : settlerList) {
			if(s.getItemHand(false).getName() == "Axe") {
				hasAxe.add(s);
			}
		}
		return closestSettler(current_pos, hasAxe);
	}
	

	@Override
	public void makeSettlers() {
		// TODO Auto-generated method stub
		
	}
 }
