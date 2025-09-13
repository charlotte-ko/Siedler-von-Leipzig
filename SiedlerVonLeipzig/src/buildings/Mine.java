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
	private static BufferedImage mineImg;
	protected static int price = 7;
	protected static String priceType = "Plank";
	protected static String name = "Mine";

	static {
		try {
			mineImg = ImageIO.read(Buildings.class.getResourceAsStream("/resources/Mine.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public Mine(Vector2D pos) {
		super(name,pos);
		this.hasEmployee = false;
		img = mineImg;
	}

	private double passedTime = 0.0;
	
	@Override
	public void update(double delta, ArrayList<ASettlers> settlerList, ArrayList<Buildings> BuildingsList,ArrayList<Items> itemList) {
		if(!this.updatStartCheck(delta, settlerList, itemList, BuildingsList, price, priceType, mineImg, mineImg, SettlersJobs.JOB_MINER, "Pickaxe"))return;
		passedTime += delta;
		if(passedTime >= 12.0 && this.output_inventory.size() <= 10) {
			Items tem = Items.createItem("Iron", this.position);
			tem.setInInventory(true);
			this.output_inventory.add(tem);
			passedTime = 0.0;
		}
	}
	
	public static int getPrice() {
		return price;
	}

}
