package buildings;

import java.util.ArrayList;

import items.Log;
import items.Tree;
import logic.Vector2D;
import settlers.ISettlers;
import settlers.SettlersAktion;

public class Forester extends Buildings {

	public Forester(Vector2D pos) {
		super("Forester", 10, pos);
	}

	/**
	 * Wir brauchen noch eine Methode die eine random Position in einem Umkreis zurückgibt, wo der Siedler den Baum pflanzt
	 */
	public void plantTree(ISettlers worker) {
		
		worker.giveSettlerTask(this.getPosition(), SettlersAktion.GO_PICKUP_LEFT, this.output_inventory.get(output_inventory.size() - 1));
		worker.giveSettlerTask(this.getPosition().generatePos(5), SettlersAktion.GO_WORK, null);
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
	public ISettlers findSettler(Vector2D current_pos, ArrayList<ISettlers> settlerList, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ISettlers findSettler(ArrayList<ISettlers> settlerList, String job) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void produce() {
		// TODO Auto-generated method stub
		
	}
	
	
 }
