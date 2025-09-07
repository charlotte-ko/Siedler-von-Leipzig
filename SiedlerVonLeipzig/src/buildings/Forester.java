package buildings;

import java.awt.Graphics2D;
import java.util.ArrayList;

import items.Items;
import items.Tree;
import logic.Vector2D;
import settlers.ASettlers;
import settlers.SettlersAktion;
import settlers.SettlersJobs;

public class Forester extends Buildings {
	
	/**
	 * @brief Konstruktor für Forester, pflanzt Trees
	 * @param pos
	 */
	public Forester(Vector2D pos) {
		super("Forester", 10, pos);
		this.hasEmployee = false;
	}
	
	/**
	 * @brief nimmt Tree aus dem Inventar des Buildings und lässt ihn an random Position pflanzen, danach geht er zum Building zurück
	 * @param worker
	 * @param plantPos
	 */
	public void plantTree(ASettlers worker, Vector2D plantPos) {
		worker.giveSettlerTask(this.getPosition(), SettlersAktion.GO_PICKUP_RIGHT, this.getOutput_Inventory().get(this.getOutput_Inventory().size()-1));
		worker.giveSettlerTask(plantPos, SettlersAktion.GO_BRING_RIGHT, null);
		worker.giveSettlerTask(this.getPosition(), SettlersAktion.GO_FREE, null);
	}
	
	/**
	 * @brief Updatemethode, sucht Gardener, fügt Tree dem Inventar hinzu wenn leer, läst employee Tree an random Position pflanzen
	 * @param Delta
	 * @param settlerList
	 * @param BuildingsList
	 * @param ItemList
	 */
	public void update(double Delta, ArrayList<ASettlers> settlerList, ArrayList<Buildings> BuildingsList, ArrayList<Items> ItemList) {
	
		if(!hasEmployee) {
			
			this.employee = this.findEmployee(settlerList, SettlersJobs.JOB_GARDENER);
			if(employee == null) return;
			else this.hasEmployee = true;
		}

		if(this.getOutput_Inventory().size() < 10) {
			this.output_inventory.add(new Tree(this.getPosition()));
		}
		
		Vector2D plantPos = new Vector2D();
		do {
			plantPos = this.getPosition().genPos(5.0f);
		}while(!plantPos.testPos(BuildingsList, ItemList));
		
		this.plantTree(employee, plantPos);
	}

	/**
	 * @note muss noch angepasst werden
	 */
	@Override
	public void draw(Graphics2D g2, int tileSize) {
		// TODO Auto-generated method stub
		
	}
 }