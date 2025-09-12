
package buildings;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import items.Items;
import logic.Purchasable;
import logic.Vector2D;
import settlers.ASettlers;
import settlers.SettlersAktion;
import settlers.SettlersJobs;

public class Lodge extends Buildings{
	private static BufferedImage lodgeImg1;
	private static BufferedImage lodgeImg2;
	public static int price = 20;
	public static String name = "Lodge";
	static {
		try {
			lodgeImg1 = ImageIO.read(Buildings.class.getResourceAsStream("/resources/Lodge.png"));
			lodgeImg2 = ImageIO.read(Buildings.class.getResourceAsStream("/resources/Lodge_!.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @brief Konstruktor für Lodge, lagert Logs
	 * @param pos
	 */
	public Lodge(Vector2D pos) {
		super("Lodge", pos);
		img = lodgeImg1;
	}

	/**
	 * @brief beschafft nächsten Tree vom Settler aus und macht daraus Log
	 * @param worker
	 * @param itemList
	 */
	public void getLogs(ASettlers worker, ArrayList<Items> itemList) {
		Items settlerHand = worker.getItem(true);
		if(!settlerHand.getName().equals("Tree")) {
			Items tree = Items.closestItem(worker.getPosition(), itemList, "Tree");
			if(tree == null)return;
			worker.giveSettlerTask(tree.getPosition(), SettlersAktion.GO_PICKUP_LEFT, tree);
			return;
		}
		Items.dellItemFromList(settlerHand, itemList);
		Items newLog = Items.createItem("Log", worker.getPosition());
		itemList.add(newLog);
		worker.setItem(true, newLog);
		worker.giveSettlerTask(this.getPosition(), SettlersAktion.GO_BRING_LEFT, worker.getItem(true));
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
			employee.setJobName(SettlersJobs.JOB_LUMBERJACK);
			employee.giveSettlerTask(axe.getPosition(), SettlersAktion.GO_PICKUP_RIGHT, axe);
			//employee.giveSettlerTask(this.getPosition(), SettlersAktion.GO_FREE, null);
		}
		else {
			employee.setJobName(SettlersJobs.JOB_LUMBERJACK);
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
	 * @note e.v muss das input_inventar direkt in das output_inventar überführt werden
	 */
	public void update(double delta, ArrayList<ASettlers> settlerList, ArrayList<Buildings> BuildingsList, ArrayList<Items> ItemList) {
		if(this.updateConstruction(delta, settlerList, BuildingsList, ItemList, this.price, "Log")){
			img = this.lodgeImg1;
		}else {return;}
		
		if(!this.hireEmployee(settlerList, this.lodgeImg2))return;
		
		if(employee.isWorking())return;
		
		if(!this.employee.getJobName().equals(SettlersJobs.JOB_LUMBERJACK)) {
			this.getAxe(ItemList);
			return;
		}
		
		this.getLogs(employee, ItemList);
		if(this.input_inventory.size() > 0) {
			Items i = this.input_inventory.get(0);
			this.input_inventory.remove(0);
			i.setInInventoryIn(false);
			i.setInInventory(true);
			this.output_inventory.add(i);
		}
		
	}
	
	public static int getPrice() {
		return price;
	}
}
