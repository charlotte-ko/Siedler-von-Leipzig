package buildings;

import java.util.ArrayList;


import items.IItems;
import items.Plank;
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
	 * Bin mir hier sehr unsicher bezüglich des korrekten Übergabe Parameters für die giveSettlerTask Methode...
	 * So ist es zumindest erstmal nicht rot
	 */
	public void makePlanks(ArrayList<ISettlers> settlerList) {
		
		ISettlers worker = null;
		
			worker = findSettler(this.getPosition(), settlerList, "Saw");
			worker.giveSettlerTask(this.getPosition(), SettlersAktion.GO_WORK, worker.getItemHand(false));
			output_inventory.remove(input_inventory.size() - 1);
			worker.setItemHand(new Plank(worker.getPosition()), true, settlerList);
			worker.giveSettlerTask(this.getPosition(), SettlersAktion.GO_BRING_LEFT, worker.getItemHand(true));
			input_inventory.add(worker.getItemHand(true));
	}
	
	/**
	 * Methode um Baumstämme zu beschaffen, wenn keine da sind
	 * Ich bin mir unsicher, ob man die Position des Logs nehmen muss bei Go (probably schon)
	 * Eventuell Übergabeparameter wieder zu IItems ändern
	 */
	public void getLogs(ISettlers worker, ArrayList<IItems> itemList) {
		
			IItems log = worker.closestItem(this.getPosition(), itemList, "Log");
			worker.giveSettlerTask(log.getPosition(), SettlersAktion.GO_PICKUP_LEFT, log);
			worker.giveSettlerTask(this.getPosition(), SettlersAktion.GO_BRING_LEFT, log);
			output_inventory.add(log);
			//attribute regeln
	}

	public void update(ArrayList<ISettlers> settlerList, ArrayList<IItems> itemList) {
		//suche arbeiter
		if(this.input_inventory.size() < 10) {
			this.makePlanks(settlerList);
		}
		//hole arbeiter mit säge
		//alle 12 sek. ein ding sägen
		//falls 
		if(this.output_inventory.size() < 10) {
			ISettlers worker = findSettler(settlerList, itemList, "NormalSettler", null);
			this.getLogs(worker, itemList);
		}
	}
}
