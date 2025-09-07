
package buildings;

import java.awt.Graphics2D;
import java.util.ArrayList;

import items.Items;
import logic.Vector2D;
import settlers.ASettlers;
import settlers.SettlersAktion;
import settlers.SettlersJobs;

public class Lodge extends Buildings{
	
	protected ASettlers employee;
	protected boolean hasEmployee;
	
	/**
	 * @brief Konstruktor für Lodge, lagert Logs
	 * @param pos
	 */
	public Lodge(Vector2D pos) {
		super("Lumberjack", 10, pos);
	}

	/**
	 * @brief beschafft nächsten Tree vom Settler aus und macht daraus Log
	 * @param worker
	 * @param itemList
	 * @note GO_MAKE ist noch nicht geschrieben
	 */
	public void getLogs(ASettlers worker, ArrayList<Items> itemList) {
		Items tree = Items.closestItem(worker.getPosition(), itemList, "Tree");
		worker.giveSettlerTask(tree.getPosition(), SettlersAktion.GO_PICKUP_LEFT, tree);
		worker.giveSettlerTask(this.getPosition(), SettlersAktion.GO_BRING_LEFT, tree);
		worker.giveSettlerTask(this.getPosition(), SettlersAktion.GO_MAKE, null);
	}
	
	/**
	 * @brief gibt employee nächste Axe, falls schon ein Item getragen wird lässt er es fallen
	 * @param ItemList
	 */
	protected void getAxe(ArrayList<Items> ItemList) {
		Items axe = Items.closestItem(employee.getPosition(), ItemList, "Axe");
		if (axe == null){
			return;
		}
		else if (this.employee.getJobName().equals(SettlersJobs.JOB_NORMAL)){
			employee.giveSettlerTask(axe.getPosition(), SettlersAktion.GO_PICKUP_RIGHT, axe);
		}
		else {
			employee.giveSettlerTask(employee.getPosition(), SettlersAktion.GO_BRING_RIGHT, employee.getItem(false));
			employee.giveSettlerTask(axe.getPosition(), SettlersAktion.GO_PICKUP_RIGHT, axe);
		}
	}
	
	/**
	 * @brief Updatemethode, überprüft ob es employee gibt und "stellt ein" wenn nicht, beschafft Log wenn Inventar leer
	 * @param Delta
	 * @param settlerList
	 * @param BuildingsList
	 * @param ItemList
	 * @note bin mir grad nicht sicher ob man mit getAxe noch was machen muss, oder ob das im nächsten Frame nen Log mit dem Bre sucht
	 */
	public void update(double Delta, ArrayList<ASettlers> settlerList, ArrayList<Buildings> BuildingsList, ArrayList<Items> ItemList) {
		
		if(!hasEmployee) {
			
			this.employee = this.findEmployee(settlerList, SettlersJobs.JOB_LUMBERJACK);
			
			if(employee == null) {
				return;
			}
			else this.hasEmployee = true;
		}
		
		if(this.employee.getJobName().equals(SettlersJobs.JOB_LUMBERJACK) && this.getInput_Inventory().size() < 10) {
			this.getLogs(employee, ItemList);
		}
		else this.getAxe(ItemList);
	}

	/**
	 * @note muss noch angepasst werden
	 */
	@Override
	public void draw(Graphics2D g2, int tileSize) {
		// TODO Auto-generated method stub
		
	}
}
