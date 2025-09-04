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
	
	public Forester(Vector2D pos, ArrayList<ISettlers> settlerList, ArrayList<IItems> itemList) {
		super("Forester", 10, pos);
		this.bind(findSettler(settlerList, itemList, "Gardener", null));
	}
	

	/**
	 * Wir brauchen noch eine Methode die eine random Position in einem Umkreis zurückgibt, wo der Siedler den Baum pflanzt
	 */
	public void plantTree(ISettlers worker, Vector2D plantPos) {
		worker.accesJob().work(this, worker, plantPos);
	}
	
	public void update(double Delta, ArrayList<ISettlers> settlerList, ArrayList<Buildings> BuildingsList, ArrayList<IItems> ItemList) {
		
		if(this.getOutput_Inventory().size() < 10) {
			this.output_inventory.add(new Tree(this.getPosition()));
		}
		
		ISettlers worker = this.findSettler(settlerList, ItemList, "Gardener", null);
		Vector2D plantPos = new Vector2D();
		do {
			plantPos = this.getPosition().genPos(5.0f);
		}while(!plantPos.testPos(BuildingsList, ItemList));
		
		
		if(!worker.isWorking()) {
			this.plantTree(worker, plantPos);
		}
	}
	
	public void bind(ISettlers worker) {
		this.employee.equals(worker);
	}
	
	private ISettlers findGardener(ArrayList<ISettlers> settlerList) {
		
		for(ISettlers s : settlerList) {
			if(s.getJob().equals("Gardener") && ((Gardener) s).bound()) {
				return s;
			}
		}
	}

	@Override
	public void createBuilding() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ISettlers makeSettlers(ArrayList<ISettlers> settlerList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void getLogs(ISettlers worker, ArrayList<IItems> itemList) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void update(ArrayList<ISettlers> settlerList, ArrayList<IItems> itemList) {
		// TODO Auto-generated method stub
		
	}
	
	
 }
