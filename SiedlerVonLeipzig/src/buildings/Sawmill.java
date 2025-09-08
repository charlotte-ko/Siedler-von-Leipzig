package buildings;

import java.awt.Graphics2D;
import java.util.ArrayList;

import items.Items;
import settlers.ASettlers;
import settlers.SettlersAktion;
import settlers.SettlersJobs;
import logic.Vector2D;

public class Sawmill extends Buildings {
	private double workeTime = 0.0;
	/**
	 * @brief Konstruktor für Sawmill, zersägt Logs und erstellt Planks
	 * @param pos
	 */
	public Sawmill(Vector2D pos) {
		super("Sawmill", 10, pos); 
		this.hasEmployee = false;
	}

	/**
	 * @brief stellt Planks her, nimmt dazu einen Log aus dem Inventar
	 * @note Inventar evtl. noch zu Deque ändern, ist simpler
	 */
	public void makePlanks(double delta,ArrayList<Items> itemList) {
		workeTime += delta;
		if(workeTime >= 12.0) {
			//ein log rausnehmen und "umwandeln"
			ArrayList<Items> tems = this.getInput_Inventory();
			tems.removeLast();
			this.setInput_Inventory(tems);
			tems = this.getOutput_Inventory();
			itemList.add(Items.createItem("Plank", this.position));
			tems.addLast(itemList.getLast());
			this.setOutput_Inventory(tems);
			
			workeTime = 0.0;
		}
	}
	
	/**
	 * @brief nimmt den nächsten Settler und schickt ihn nächsten Log holen, Log wird auch gebracht
	 * @param itemList
	 * @param settlerList
	 * @note müssen noch prüfen ob employee auf dem Weg ist
	 */
	public void getLogs(ArrayList<Items> itemList, ArrayList<ASettlers> settlerList) {
		ASettlers worker = this.closestSettler(settlerList);//Zeitarbeiter
		
		Items log = Items.closestItem(this.getPosition(), itemList, "Log");
		
		if(log != null && worker != null) {
			worker.giveSettlerTask(log.getPosition(), SettlersAktion.GO_PICKUP_LEFT, log);
			worker.giveSettlerTask(this.getPosition(), SettlersAktion.GO_BRING_LEFT, log);
		}
		else return;
	}
	
	/**
	 * @brief schickt Settler die nächste Saw holen, hält er schon ein Item wird das fallengelassen
	 * @param ItemList
	 * @note müssen noch prüfen ob employee auf dem Weg ist
	 */
	protected void getSaw(ArrayList<Items> ItemList) {
		Items saw = Items.closestItem(employee.getPosition(), ItemList, "Saw");
		if (saw == null){
			return;
		}
		if(!employee.isWorking()) {
			if (this.employee.getJobName().equals(SettlersJobs.JOB_NORMAL)){
				employee.giveSettlerTask(saw.getPosition(), SettlersAktion.GO_PICKUP_RIGHT, saw);
			}else {
				employee.giveSettlerTask(employee.getPosition(), SettlersAktion.GO_BRING_RIGHT, employee.getItem(false));
			}
		}
	}
	
	/**
	 * @brief Updatemethode, überprüft ob es employee gibt und "stellt ein", überprüft Inventare und lässt diese füllen
	 * @param settlerList
	 * @param itemList
	 * @note der zeitliche Aspekt fehlt hier noch, Marie und ich wissen nicht genau wie wir das einbauen sollen
	 */
	public void update(double delta, ArrayList<ASettlers> settlerList, ArrayList<Buildings> BuildingsList, ArrayList<Items> itemList){
		
		if(!hasEmployee) {
			
			this.employee = this.findEmployee(settlerList, SettlersJobs.JOB_LUMBERJACK);
			
			if(employee == null) {
				return;
			}
			else this.hasEmployee = true;
		}

		if(this.input_inventory.size() < 10) {
			getLogs(itemList, settlerList);
		}
		//prüfe ob siedler im Gebäude ist
		if(this.employee.getJobName().equals(SettlersJobs.JOB_SAWYER) && this.output_inventory.size() < 10 && this.input_inventory.size() > 0) {
			makePlanks(delta);
		}
		else getSaw(itemList);
	}

	/**
	 * @note muss noch angepasst werden
	 */
	@Override
	public void draw(Graphics2D g2, int tileSize) {
		// TODO Auto-generated method stub
		
	}
}
