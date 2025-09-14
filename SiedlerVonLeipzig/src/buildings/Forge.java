package buildings;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import items.Items;
import logic.Vector2D;
import settlers.ASettlers;
import settlers.SettlersAktion;
import settlers.SettlersJobs;


public class Forge extends Buildings{
	
	protected static int price = 7;
	protected static String priceType = "Planks";
	private static String name = "Forge";
	
	private double passedTime = 0.0;
	private int nextTool = 0;
	
	private static BufferedImage forgeImg;
	

	static {
		try {
			
			forgeImg = ImageIO.read(Buildings.class.getResourceAsStream("/resources/Forge.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @brief Konstruktor für Forge
	 * @param pos
	 */
	public Forge(Vector2D pos) {
		super(name, pos);
		img = forgeImg;
	}
	
	/**
	 * @brief Siedler wird gesucht, um Iron in die Forge zu bringen
	 * @param settlerList
	 * @param itemList
	 */
	private void getIron(ArrayList<ASettlers>settlerList, ArrayList<Items>itemList) {
		
		if(this.input_inventory.size() >= 10) return;
		ASettlers worker = this.closestSettler(settlerList); //Zeitarbeiter
		
		if(worker == null) return;
		
		Items target = Items.closestItem(worker.getPosition(), itemList, "Iron");
		if(target == null) return;
		
		worker.giveSettlerTask(target.getPosition(), SettlersAktion.GO_PICKUP_LEFT, target);
		worker.giveSettlerTask(this.getPosition(), SettlersAktion.GO_BRING_LEFT, target);
	}
	
	@Override
	public void update(double delta, ArrayList<ASettlers> settlerList, ArrayList<Buildings> BuildingsList, ArrayList<Items> itemList) {
		
		if(this.isConstructed) this.getIron(settlerList, itemList);
		
		if(!this.updateStartCheck(delta, settlerList, itemList, BuildingsList, price, priceType, forgeImg, forgeImg, SettlersJobs.JOB_SMITH, "Hammer")) return;
		
		passedTime += delta;
		
		if(passedTime >= 12.0 && this.output_inventory.size() <= 10 && this.input_inventory.size() > 0) {
			Items targetItem;
			Items.deleteItemFromList(this.input_inventory.remove(0), itemList);
			
			switch(nextTool) {
				case 0:targetItem = Items.createItem("Axe", position); break;
				case 1:targetItem = Items.createItem("Saw", position); break;
				case 2:targetItem = Items.createItem("Pickaxe", position); break;
				case 3:targetItem = Items.createItem("Hammer", position); break;
				default:targetItem = Items.createItem("Hammer", position); break;
			}
			
			targetItem.setInInventoryOutput(true);
			itemList.add(targetItem);
			this.output_inventory.add(targetItem);
			nextTool++;
			nextTool %= 4;
			passedTime = 0.0;
			this.employee.giveSettlerTask(getPosition().add(new Vector2D(1.0f,1.0f)), SettlersAktion.GO_FREE, null);
			this.employee.giveSettlerTask(getPosition().add(new Vector2D(-0.1f,-0.1f)), SettlersAktion.GO_FREE, null);
		}
	}
	
	public static int getPrice() {
		return price;
	}
	
	public static String getPriceType() {
		return priceType;
	}
}
