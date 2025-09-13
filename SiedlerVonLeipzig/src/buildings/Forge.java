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
	private static BufferedImage forgeImg;
	protected static int price = 7;
	protected static String priceType = "Plank";
	protected static String name = "Forge";

	static {
		try {
			forgeImg = ImageIO.read(Buildings.class.getResourceAsStream("/resources/Forge.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public Forge(Vector2D pos) {
		super(name,pos);
		this.hasEmployee = false;
		img = forgeImg;
	}

	private void getIron(ArrayList<ASettlers>settlerList,ArrayList<Items>itemList) {
		if(this.input_inventory.size() >= 10)return;
		ASettlers worker = this.closestSettler(settlerList);//Zeitarbeiter
		
		if(worker == null)return;
		Items tem = Items.closestItem(worker.getPosition(), itemList, "Iron");
		if(tem == null)return;
		
		worker.giveSettlerTask(tem.getPosition(), SettlersAktion.GO_PICKUP_LEFT, tem);
		worker.giveSettlerTask(this.getPosition(), SettlersAktion.GO_BRING_LEFT, tem);
	}
	
	private double passedTime = 0.0;
	private int nextTool = 0;
	
	@Override
	public void update(double delta, ArrayList<ASettlers> settlerList, ArrayList<Buildings> BuildingsList,ArrayList<Items> itemList) {
		if(this.isContructed)this.getIron(settlerList, itemList);
		
		if(!this.updatStartCheck(delta, settlerList, itemList, BuildingsList, price, priceType, forgeImg, forgeImg, SettlersJobs.JOB_SMITH, "Hammer"))return;
		
		passedTime += delta;
		if(passedTime >= 12.0 && this.output_inventory.size() <= 10 && this.input_inventory.size() > 0) {
			Items tem;
			Items.dellItemFromList(this.input_inventory.remove(0),itemList);
			switch(nextTool) {
				case 0:tem = Items.createItem("Axe", position);break;
				case 1:tem = Items.createItem("Saw", position);break;
				case 2:tem = Items.createItem("Pickaxe", position);break;
				case 3:tem = Items.createItem("Hammer", position);break;
				default:tem = Items.createItem("Hammer", position);break;
			}
			tem.setInInventory(true);
			this.output_inventory.add(tem);
			nextTool++;
			nextTool %= 4;
			passedTime = 0.0;
		}
	}

}
