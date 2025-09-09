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

public class Forester extends Buildings {
	private static BufferedImage foresterImg1;
	private static BufferedImage foresterImg2;
	
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
		super("Forester", 10, pos);
		this.hasEmployee = false;
		img = foresterImg1;
	}
	
	/**
	 * @brief nimmt Tree aus dem Inventar des Buildings und lässt ihn an random Position pflanzen, danach geht er zum Building zurück
	 * @param worker
	 * @param plantPos
	 */
	public void plantTree(ASettlers worker, Vector2D plantPos) {
		worker.giveSettlerTask(this.getPosition(), SettlersAktion.GO_PICKUP_RIGHT, this.getOutput_Inventory().get(this.getOutput_Inventory().size()-1));
		worker.giveSettlerTask(plantPos, SettlersAktion.GO_BRING_RIGHT, null);
		worker.giveSettlerTask(this.getPosition(), SettlersAktion.GO_FREE, null);
	}
	
	/**
	 * @brief Updatemethode, sucht Gardener, fügt Tree dem Inventar hinzu wenn leer, läst employee Tree an random Position pflanzen
	 * @param Delta
	 * @param settlerList
	 * @param BuildingsList
	 * @param ItemList
	 */
	public void update(double Delta, ArrayList<ASettlers> settlerList, ArrayList<Buildings> BuildingsList, ArrayList<Items> ItemList) {
		
		if(!hasEmployee) {			
			this.employee = this.findEmployee(settlerList, SettlersJobs.JOB_GARDENER);
			if(employee == null) return;
			else this.hasEmployee = true;
			this.employee.setEmployed(true);
			this.employee.setJobName(SettlersJobs.JOB_GARDENER);
		}

		if(this.getOutput_Inventory().size() < 10) {
			passtime += Delta;
			if(passtime >= 6.0) {
				this.output_inventory.add(new Tree(this.getPosition()));
				passtime = 0.0;
			}
		}
		
		Vector2D plantPos = new Vector2D();
		float moreRange = 0.0f;
		do {
			plantPos = this.getPosition().genPos(5.0f+moreRange);
			moreRange += 0.2;
		}while(!plantPos.testPos(BuildingsList, ItemList));
		
		this.plantTree(employee, plantPos);
	}
 }