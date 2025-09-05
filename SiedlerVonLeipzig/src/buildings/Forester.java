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

public class Forester extends Buildings implements Assignable {

	protected ISettlers employee;
	protected boolean hasEmployee;
	
	public Forester(Vector2D pos) {
		super("Forester", 10, pos);
	}
	
	

	/**
	 * Wir brauchen noch eine Methode die eine random Position in einem Umkreis zurückgibt, wo der Siedler den Baum pflanzt
	 */
	public void plantTree(ISettlers worker, Vector2D plantPos) {
		worker.accesJob().work(this, worker, plantPos);
	}
	
	public void update(double Delta, ArrayList<ISettlers> settlerList, ArrayList<Buildings> BuildingsList, ArrayList<IItems> ItemList) {
		
		if(!this.bound() || !((Gardener) employee).bound()) {
			this.bind(findGardener(settlerList));
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
