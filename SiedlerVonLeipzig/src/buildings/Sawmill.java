package buildings;

import java.util.ArrayList;

import items.Plank;
import settlers.ISettlers;
import settlers.SettlersAktion;
import logic.Vector2D;

public class Sawmill extends Buildings {
	
	/**
	 * Konstruktor, übergibt Namen und Preis
	 */
	public Sawmill(Vector2D pos) {
		super("Sawmill", 10, pos);
	}

	/**
	 * Methode erstellt Brett mit gelagertem Log, gibt Brett dem Siedler mit
	 */
	public void makePlanks(ArrayList<ISettlers> settlerList) {
		
		ISettlers worker = null;
		
		if(!inventory.isEmpty()) {
			worker = findSettler(this.getPosition(), settlerList, "Saw");
			worker.giveSettlerTask(this.getPosition(), SettlersAktion.GO_WORK);
			inventory.remove(inventory.size() - 1);
			worker.setItemHand(new Plank(worker.getPosition()), true, settlerList);
		}
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
	public ISettlers makeSettlers(ArrayList<ISettlers> settlerList) {
		// TODO Auto-generated method stub
		return null;
	}
}
