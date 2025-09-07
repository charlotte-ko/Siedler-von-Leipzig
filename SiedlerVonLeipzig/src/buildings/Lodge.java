
package buildings;

import java.util.ArrayList;

import items.Items;
import items.Tree;
import logic.Vector2D;
import settlers.ASettlers;
import settlers.SettlersJobs;

public class Lodge extends Buildings{
	
	protected ASettlers employee;
	protected boolean hasEmployee;
	
	public Lodge(Vector2D pos) {
		super("Lumberjack", 10, pos);
	}

	public void getLogs(ASettlers worker) {
		
	}
	
	public void update(double Delta, ArrayList<ASettlers> settlerList, ArrayList<Buildings> BuildingsList, ArrayList<IItems> ItemList) {
		
		if(!hasEmployee) {
			
			this.employee = this.findEmployee(settlerList, ItemList, SettlersJobs.JOB_LUMBERJACK);
			
			if(employee == null) {
				return;
			}
			//this.employee.giveSettlerTask(getPosition(), null, null);//komm zum //
		}
		
		if(this.getInput_Inventory().size() < 10) {
			
		}
	}
	
	public Items getNearestTree(ArrayList<Items> itemList, ASettlers worker) {
		
		ArrayList<Items> treeList = new ArrayList<>();
		for(Items i : itemList) {
			if(i instanceof Tree) {
				treeList.add(i);
			}
		}
		worker.closestItem(worker.getPosition(), treeList, "Tree");
	}
}
