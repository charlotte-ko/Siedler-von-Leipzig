package buildings;

import java.util.ArrayList;

import items.IItems;
import items.Tree;
import logic.Vector2D;
import settlers.ASettlers;
import settlers.SettlersAktion;
import settlers.SettlersJobs;

/**
 * Förster Gebäude.
 * Pflanzt Bäume.
 */
public class Forester extends Buildings {
	
	public Forester(Vector2D pos) {
		super("Forester", 10, pos);
		this.hasEmployee = false;
	}
	

	/**
	 * @brief Was zum teufel macht die Mehtode?
	 * @param worker
	 * @param plantPos
	 */
	public void plantTree(ASettlers worker, Vector2D plantPos) {
		worker.giveSettlerTask(this.getPosition(), SettlersAktion.GO_PICKUP_RIGHT, this.getOutput_Inventory().get(this.getOutput_Inventory().size()-1));
		worker.giveSettlerTask(plantPos, SettlersAktion.GO_BRING_RIGHT, null);
		worker.giveSettlerTask(this.getPosition(), SettlersAktion.GO_FREE, null);
	}
	
	/**
	 * @brief Methode die immer aufgerufen wird
	 * 
	 * @param Delta
	 * @param settlerList
	 * @param BuildingsList
	 * @param ItemList
	 */
	public void update(double Delta, ArrayList<ASettlers> settlerList, ArrayList<Buildings> BuildingsList, ArrayList<IItems> ItemList) {
		
		//---suche nach employee---
		if(!hasEmployee) {
			
			this.employee = this.findEmployee(settlerList, ItemList, SettlersJobs.JOB_GARDENER);
			
			if(employee == null) {
				return;
			}
			//this.employee.giveSettlerTask(getPosition(), null, null);//komm zum 
		}

		if(this.getOutput_Inventory().size() < 10) {
			this.output_inventory.add(new Tree(this.getPosition()));
		}
		
		//muss auf jeden Fall noch geändert werden
		Vector2D plantPos = new Vector2D();
		do {
			plantPos = this.getPosition().genPos(5.0f);
		}while(!plantPos.testPos(BuildingsList, ItemList));
		
		//beauftrage Siedler zum 
		this.plantTree(employee, plantPos);
	}

	/**
	 * 
	 * @param settlerList
	 * @param ItemList
	 * @return einen Siedler, der bereits Gärtner war pder jetzt zu einem umgewandelt wurde oder null
	 */
	/*private ASettlers findGardener(ArrayList<ASettlers> settlerList, ArrayList<IItems> ItemList) {
		
		this.employee = this.findSettler(settlerList, ItemList, SettlersJobs.JOB_GARDENER);
		
		if(this.employee == null) {
			return null;
		}
		else if(this.employee.getJobName().equals(SettlersJobs.JOB_NORMAL)) {
			this.employee.setJobName(SettlersJobs.JOB_GARDENER);
		}
		this.hasEmployee = true;
		return employee;
	}*/
 }
