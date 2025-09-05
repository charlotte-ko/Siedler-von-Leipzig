package buildings;

import java.util.ArrayList;

import items.IItems;
import settlers.ISettlers;
import settlers.ASettlers;
import logic.Vector2D;

public class House extends Buildings {

	/**
	 * Vector, welcher alle Siedler des Hauses speichert, brauchen wir nicht, weil das in der globalen Liste steht
	 */
	public ArrayList<ISettlers> sInventory = new ArrayList<>();
	
	/**
	 * Konstruktor des Wohnhauses, übergibt Namen und Preis an Super
	 */
	public House(Vector2D pos) {
		super("House", 10, pos);
	}
	
	/**
	 * Methode um Siedler zu produzieren und dem Inventar zuzufügen, gibt zuletzt produzierten Siedler zurück
	 */
	public void makeSettlers(ArrayList<ISettlers> settlerList) {
	
		settlerList.add(ASettlers.createSettlerInList(settlerList, this.getPosition()));
	}
	
	public void update(ArrayList<ISettlers> settlerList) {
		
		if(sInventory.size() < 10) {
			makeSettlers(settlerList);
		}
	}
}
