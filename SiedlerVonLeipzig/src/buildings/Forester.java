package buildings;

import java.util.ArrayList;
import java.util.Random;

import items.IItems;
import items.Items;
import items.Tree;
import logic.Vector2D;
import settlers.Assignable;
import settlers.Gardener;
import settlers.ISettlers;
import settlers.SettlersAktion;
import settlers.SettlersJobs;

/**
 * Förster Gebäude.
 * Pflanzt Bäume.
 */
public class Forester extends Buildings implements Assignable {

	protected ISettlers employee;	//Pro gebäude nur ein Arbeiter
	protected boolean hasEmployee;
	
	public Forester(Vector2D pos) {
		super("Forester", 10, pos);
		this.hasEmployee = false;
	}
	

	/**
	 * @brief Was zum teufel macht die Mehtode?
	 * @param worker
	 * @param plantPos
	 */
	public void plantTree(ISettlers worker, Vector2D plantPos) {
		worker.accesJob().work(this, worker, plantPos);
	}
	
	/**
	 * @brief Methode die immer aufgerufen wird
	 * 
	 * @param Delta
	 * @param settlerList
	 * @param BuildingsList
	 * @param ItemList
	 */
	public void update(double Delta, ArrayList<ISettlers> settlerList, ArrayList<Buildings> BuildingsList, ArrayList<IItems> ItemList) {
		
		//---suche nach employee---
		if(!this.hasEmployee) {//Arbeiter muss erst gefunden werden
			this.employee = this.findSettler(settlerList, ItemList, SettlersJobs.JOB_GARDENER, null);
			if(this.employee == null) {
				this.employee = this.findSettler(settlerList, ItemList, "Normall", null);
				if(this.employee == null) {
					return;	//fehlende Siedler
				}
				//gib dem employee passenden job
			}
			this.hasEmployee = true;
			this.employee.giveSettlerTask(getPosition(), null, null);//komm zum 
		}
		
		
		//---Inventar füllen
		if(this.getOutput_Inventory().size() < 10) {
			this.output_inventory.add(new Tree(this.getPosition()));
		}
		//position von nächsten Baum gesucht
		Vector2D plantPos = new Vector2D();
		do {
			plantPos = this.getPosition().genPos(5.0f);
		}while(!plantPos.testPos(BuildingsList, ItemList));
		
		//beauftrage Siedler zum 
		this.plantTree(employee, plantPos);
	}
	
	public void bind(ISettlers worker) {
		this.employee = worker;
		this.hasEmployee = true;
		
		if(employee instanceof Gardener) {
			((Gardener) employee).bind(this);
		}
		
	}
	
	public boolean bound() {
		return this.hasEmployee;
	}
	
	/**
	 * 
	 * @param settlerList
	 * @return einen neuen Gardener für genau dieses Förstergebäude
	 * Sucht zuerst einen Siedler, der bereits Gardener ist und an noch kein Förstergebäude gebunden ist
	 * Falls keiner vorhanden ist, wird ein anderer Siedler genommen
	 */
	private ISettlers findGardener(ArrayList<ISettlers> settlerList) {
		
		for(ISettlers s : settlerList) {
			
			if(s.getJob().equals("Gardener") && !((Gardener) s).bound()) {
				return s;
			}
		}
		ISettlers newGardener = findSettler(settlerList);
		return newGardener;
	}
 }
