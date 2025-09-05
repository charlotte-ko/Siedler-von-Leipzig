package buildings;

import java.util.ArrayList;
import settlers.ASettlers;
import logic.Vector2D;

public class House extends Buildings {

	
	private int counter = 0;
	
	/**
	 * Konstruktor des Wohnhauses, übergibt Namen und Preis an Super
	 */
	public House(Vector2D pos) {
		super("House", 10, pos);
	}
	
	/**
	 * Methode um Siedler zu produzieren und diesen direkt in die globale Liste einzutragen
	 */
	public void makeSettlers(ArrayList<ASettlers> settlerList) {
	
		settlerList.add(ASettlers.createSettler(this.getPosition()));
	}
	
	public void update(ArrayList<ASettlers> settlerList) {
		
		if(this.counter < 10) {
			makeSettlers(settlerList);
		}
	}
}
