package buildings;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import items.Items;
import items.Tree;
import logic.Vector2D;
import settlers.ASettlers;
import settlers.SettlersAktion;
import settlers.SettlersJobs;

public class Forester extends Buildings{
	private static BufferedImage foresterImg1;
	private static BufferedImage foresterImg2;
	protected static int price = 20;
	protected static String priceType = "Log";
	protected static String name = "Forester";
	
	static {
		try {
			foresterImg1 = ImageIO.read(Buildings.class.getResourceAsStream("/resources/Forester.png"));
			foresterImg2 = ImageIO.read(Buildings.class.getResourceAsStream("/resources/Forester_!.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private double passtime = 0.0;
	/**
	 * @brief Konstruktor für Forester, pflanzt Trees
	 * @param pos
	 */
	public Forester(Vector2D pos) {
		super("Forester", pos);
		this.hasEmployee = false;
		img = foresterImg1;
	}
	
	/**
	 * @brief nimmt Tree aus dem Inventar des Buildings und lässt ihn an random Position pflanzen, danach geht er zum Building zurück
	 * @param worker
	 * @param plantPos
	 */
	public void plantTree(ASettlers worker, Vector2D plantPos) {
		if(this.getOutput_Inventory().isEmpty())return;
		
		Items target = this.getOutput_Inventory().get(this.getOutput_Inventory().size()-1);
		worker.giveSettlerTask(this.getPosition(), SettlersAktion.GO_PICKUP_RIGHT, target);
		worker.giveSettlerTask(plantPos, SettlersAktion.GO_BRING_RIGHT, target);
		worker.giveSettlerTask(this.getPosition(), SettlersAktion.GO_FREE, null);
	}
	
	/**
	 * @brief Updatemethode, sucht Gardener, fügt Tree dem Inventar hinzu wenn leer, läst employee Tree an random Position pflanzen
	 * @param Delta
	 * @param settlerList
	 * @param BuildingsList
	 * @param ItemList
	 */
	public void update(double delta, ArrayList<ASettlers> settlerList, ArrayList<Buildings> BuildingsList, ArrayList<Items> itemList) {
		
		if(this.getOutput_Inventory().size() < 10 && this.isContructed) {
			passtime += delta;
			if(passtime >= 6.0) {
				Items newTree = Items.createItem("Tree", this.position);
				newTree.setInInventory(true);
				itemList.add(newTree);
				this.output_inventory.add(newTree);
				passtime = 0.0;
			}
		}

		if(!this.updatStartCheck(delta, settlerList, itemList, BuildingsList, price, priceType, foresterImg1, foresterImg2, SettlersJobs.JOB_GARDENER, null))return;

		Vector2D plantPos = new Vector2D();
		float moreRange = 0.0f;
		do {
			//plantPos = this.getPosition().genPos(1.0f+moreRange);
			plantPos = this.getPosition().genPosInCircle(3.1f+moreRange);
			moreRange += 0.1;
		}while(!plantPos.testPos(BuildingsList, itemList));
		
		this.plantTree(employee, plantPos);
	}

	public static int getPrice() {
		return price;
	}
 }