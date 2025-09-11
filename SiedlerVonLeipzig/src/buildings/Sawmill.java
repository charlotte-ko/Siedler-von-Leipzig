package buildings;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import items.Items;
import settlers.ASettlers;
import settlers.SettlersAktion;
import settlers.SettlersJobs;
import logic.Vector2D;

public class Sawmill extends Buildings {
	private static BufferedImage sawmillImg1;
	private static BufferedImage sawmillImg2;
	public static int price = 20;
	static {
		try {
			sawmillImg1 = ImageIO.read(Buildings.class.getResourceAsStream("/resources/Sawmill.png"));
			sawmillImg2 = ImageIO.read(Buildings.class.getResourceAsStream("/resources/Sawmill_!.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private double workeTime = 0.0;
	/**
	 * @brief Konstruktor für Sawmill, zersägt Logs und erstellt Planks
	 * @param pos
	 */
	public Sawmill(Vector2D pos) {
		super("Sawmill", pos); 
		this.hasEmployee = false;
		this.name = "Sawmill";
		this.price = 20;
		img = sawmillImg1;
	}

	/**
	 * @brief stellt Planks her, nimmt dazu einen Log aus dem Inventar
	 * @note können wir einfach das letzte Item removen? Löscht das eventuell nicht andere Items? Welche anderen Items ?
	 * @note Ist sicher gestellt das getInput_Inventory überhaupt einen Eintrag hat ?
	 */
	public void makePlanks(double delta, ArrayList<Items> itemList) {
		workeTime += delta;
		if(workeTime >= 12.0) {
			ArrayList<Items> inventoryIn = this.getInput_Inventory();
			ArrayList<Items> inventoryOut = this.getOutput_Inventory();
			
			inventoryIn.remove(inventoryIn.size() - 1);
			Items newCreation = Items.createItem("Plank", this.position);
			newCreation.setInInventory(true);
			itemList.add(newCreation);
			inventoryOut.add(newCreation);
			
			this.setInput_Inventory(inventoryIn);
			this.setOutput_Inventory(inventoryOut);
			
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
			makePlanks(delta, itemList);
		}
		else getSaw(itemList);
	}
}
