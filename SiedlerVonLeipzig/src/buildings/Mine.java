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


public class Mine extends Buildings{
	
	protected static int price = 7;
	protected static String priceType = "Planks";
	private static String name = "Mine";
	
	private double passedTime = 0.0;
	
	private static BufferedImage mineImg;
	

	static {
		try {
			
			mineImg = ImageIO.read(Buildings.class.getResourceAsStream("/resources/Mine.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @brief Konstrukto für Mine
	 * @param pos
	 */
	public Mine(Vector2D pos) {
		super(name,pos);
		img = mineImg;
	}

	
	
	@Override
	public void update(double delta, ArrayList<ASettlers> settlerList, ArrayList<Buildings> BuildingsList, ArrayList<Items> itemList) {
		
		if(!this.updateStartCheck(delta, settlerList, itemList, BuildingsList, price, priceType, mineImg, mineImg, SettlersJobs.JOB_MINER, "Pickaxe")) return;
		
		passedTime += delta;
		
		if(passedTime >= 12.0 && this.output_inventory.size() <= 10) {
			
			Items newIron = Items.createItem("Iron", this.position);
			newIron.setInInventoryOutput(true);
			itemList.add(newIron);
			this.output_inventory.add(newIron);
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
