package buildings;

import java.util.ArrayList;


import items.Items;
import settlers.ASettlers;
import settlers.SettlersAktion;
import settlers.SettlersJobs;
import logic.Vector2D;

public class Sawmill extends Buildings {
	
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
	public void makePlanks() {
		Items log = this.input_inventory.get(input_inventory.size() - 1);
		employee.giveSettlerTask(this.getPosition(), SettlersAktion.GO_PICKUP_LEFT, log);
		employee.giveSettlerTask(this.getPosition(), SettlersAktion.GO_MAKE, null);
	}
	
	/**
	 * @brief nimmt den nächsten Settler und schickt ihn nächsten Log holen, Log wird auch gebracht
	 * @param itemList
	 * @param settlerList
	 * @note hier müssen wir evtl. noch anpassen, damit der Settler nicht nem anderen Settler den Log klaut
	 */
	public void getLogs(ArrayList<Items> itemList, ArrayList<ASettlers> settlerList) {
		ASettlers worker = this.closestSettler(settlerList);
		Items log = Items.closestItem(this.getPosition(), itemList, "Log");
		
		if(log != null) {
			worker.giveSettlerTask(log.getPosition(), SettlersAktion.GO_PICKUP_LEFT, log);
			worker.giveSettlerTask(this.getPosition(), SettlersAktion.GO_BRING_LEFT, log);
		}
		else return;
	}
	
	/**
	 * @brief schickt Settler die nächste Saw holen, hält er schon ein Item wird das fallengelassen
	 * @param ItemList
	 */
	protected void getSaw(ArrayList<Items> ItemList) {
		Items saw = Items.closestItem(employee.getPosition(), ItemList, "Saw");
		if (saw == null){
			return;
		}
		else if (this.employee.getJobName().equals(SettlersJobs.JOB_NORMAL)){
			employee.giveSettlerTask(saw.getPosition(), SettlersAktion.GO_PICKUP_RIGHT, saw);
		}
		else {
			employee.giveSettlerTask(employee.getPosition(), SettlersAktion.GO_BRING_RIGHT, employee.getItem(false));
			employee.giveSettlerTask(saw.getPosition(), SettlersAktion.GO_PICKUP_RIGHT, saw);
		}
	}
	
	/**
	 * @brief Updatemethode, überprüft ob es employee gibt und "stellt ein", überprüft Inventare und lässt diese füllen
	 * @param settlerList
	 * @param itemList
	 * @note der zeitliche Aspekt fehlt hier noch, Marie und ich wissen nicht genau wie wir das einbauen sollen
	 */
	public void update(ArrayList<ASettlers> settlerList, ArrayList<Items> itemList) {
		
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
		if(this.employee.getJobName().equals(SettlersJobs.JOB_SAWYER) && this.output_inventory.size() < 10) {
			makePlanks();
		}
		else getSaw(itemList);
	}
}
