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

public class Forester extends Buildings{
	
	protected static int price = 20;
	protected static String priceType = "Log";
	private static String name = "Forester";
	
	private static BufferedImage foresterImg1;
	private static BufferedImage foresterImg2;
	
	private double passtime = 0.0;
	
	
	static {
		try {
			foresterImg1 = ImageIO.read(Buildings.class.getResourceAsStream("/resources/Forester.png"));
			foresterImg2 = ImageIO.read(Buildings.class.getResourceAsStream("/resources/Forester_!.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @brief Konstruktor für Forester, pflanzt Trees
	 * @param pos
	 */
	public Forester(Vector2D pos) {
		super(name, pos);
		img = foresterImg1;
	}
	
	/**
	 * @brief nimmt Tree aus dem Inventar des Buildings und lässt ihn an random Position pflanzen, danach geht Siedler zum Building zurück
	 * @param worker
	 * @param plantPos
	 */
	public void plantTree(ASettlers worker, Vector2D plantPos) {
		
		if(this.getOutput_Inventory().isEmpty()) return;
		
		Items target = this.getOutput_Inventory().get(this.getOutput_Inventory().size()-1);
		worker.giveSettlerTask(this.getPosition(), SettlersAktion.GO_PICKUP_RIGHT, target);
		worker.giveSettlerTask(plantPos, SettlersAktion.GO_BRING_RIGHT, target);
		worker.giveSettlerTask(this.getPosition(), SettlersAktion.GO_FREE, null);
	}
	
	/**
	 * @brief Updatemethode, sucht Gardener, fügt Tree dem Inventar hinzu, wenn leer, lässt employee Tree an random Position pflanzen
	 * @param delta
	 * @param settlerList
	 * @param BuildingsList
	 * @param ItemList
	 */
	public void update(double delta, ArrayList<ASettlers> settlerList, ArrayList<Buildings> BuildingsList, ArrayList<Items> itemList) {
		
		if(this.getOutput_Inventory().size() < 10 && this.isConstructed) {
			
			passtime += delta;
			if(passtime >= 6.0) {
				
				Items newTree = Items.createItem("Tree", this.position);
				newTree.setInInventoryOutput(true);
				itemList.add(newTree);
				this.output_inventory.add(newTree);
				passtime = 0.0;
			}
		}

		if(!this.updateStartCheck(delta, settlerList, itemList, BuildingsList, price, priceType, foresterImg1, foresterImg2, SettlersJobs.JOB_GARDENER, null)) return;

		Vector2D plantPos = new Vector2D();
		
		float moreRange = 0.0f;
		do {
			
			plantPos = this.getPosition().genPosInCircle(3.1f+moreRange);
			moreRange += 0.1;
			
		}while(!plantPos.testPos(BuildingsList, itemList, 2.0f));
		
		this.plantTree(employee, plantPos);
	}

	public static int getPrice() {
		return price;
	}
	
	public static String getPriceType() {
		return priceType;
	}
 }