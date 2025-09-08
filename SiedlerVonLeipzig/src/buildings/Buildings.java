package buildings;


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import items.Items;
import settlers.ASettlers;
import settlers.SettlersJobs;
import logic.Vector2D;

public abstract class Buildings {
	
	/**
	 * Attribute
	 */
	protected final String name;
	protected Vector2D position;
	protected ArrayList<Items> input_inventory = new ArrayList<>(); 
	protected ArrayList<Items> output_inventory = new ArrayList<>();
	private final int price; //Ich weiß nicht mehr ob das in die GUI reinkommt oder hier
	
	protected ASettlers employee;
	protected boolean hasEmployee;
	protected BufferedImage img;
	public void draw(Graphics2D g2, int tileSize,Vector2D camPos) {
		int posX = (int)((position.x-camPos.x)*tileSize);
		int posY = (int)((position.y-camPos.y)*tileSize);
		
		g2.drawImage(img, posX, posY, tileSize,tileSize,null);
	}
	public abstract void update(double Delta, ArrayList<ASettlers> settlerList, ArrayList<Buildings> BuildingsList, ArrayList<Items> itemList);
	
	/**
	 * @brief Konstruktor
	 * @param name
	 * @param price
	 * @param position
	 */
	public Buildings(String name, int price, Vector2D pos) {
		this.name = name;
		this.price = price;	
		this.position = pos;
	}
	
	/**
	 * @brief Factory Methode, erstellt Buildings nach Typ
	 * @param building
	 * @param pos
	 * @return
	 */
	public static Buildings createBuilding(String building, Vector2D pos) {
		switch(building) {
		case "House" : return new House(pos);
		case "Forester" : return new Forester(pos);
		case "Lodge" : return new Lodge(pos);
		case "Sawmill" : return new Sawmill(pos);
		default: throw new IllegalArgumentException("Unexpected Value: " + building);
		}
	}
	
	/**
	 * @brief sucht nächsten freien Settler und gibt ihn zurück
	 * @param settlerList
	 * @return closest (Settler), oder null bei nicht finden
	 */
	public ASettlers closestSettler(ArrayList<ASettlers> settlerList) {
		if(settlerList.isEmpty()) return null;
		
		float min_distance = Float.POSITIVE_INFINITY;
		ASettlers closest = null;
		
		for(ASettlers s : settlerList) {
			float distance = this.getPosition().getDistance(s.getPosition());
			if(!s.getEmployed() && !s.isWorking() && distance <= min_distance) {
				closest = s;
			}
		}
		return closest;
	}
	
	/**
	 * @brief sucht nächsten Settler mit passenden Job
	 * @param settlerList
	 * @param job (gesuchter)
	 * @return Settler mit Job, oder null
	 * @note verwendet closestSettler()
	 */
	public ASettlers findSettler(ArrayList<ASettlers> settlerList, SettlersJobs job) {
		
		ArrayList<ASettlers> wanted = new ArrayList<>();
		
		for(ASettlers s : settlerList) {
			if(s.getJobName().equals(job)) {
				wanted.add(s);
			}
		}
		
		if(!wanted.isEmpty()) {
			return this.closestSettler(wanted);
		} 
		return null;
	}
	
	/**
	 * @brief weist einem Building einen employee zu, falls Job nicht vorhanden wird normaler Settler gesucht,
	 * 		  falls kein Settler vorhanden wird der nächste freie genommen
	 * @param settlerList
	 * @param job
	 * @return employee (Settler)
	 * @note nutzt closestSettler() und findSettler()
	 */
	public ASettlers findEmployee(ArrayList<ASettlers> settlerList, SettlersJobs job) {
		
		this.employee = this.findSettler(settlerList, job);
		
		if(this.employee == null) {
			this.employee = this.findSettler(settlerList, SettlersJobs.JOB_NORMAL);
		}
		
		if(this.employee == null) {
			this.employee = this.closestSettler(settlerList);
		}
	
		return employee;
	}
	
	/**
	 * @brief Getter für Building Position
	 * @return this.position
	 */
	public Vector2D getPosition() {
		return this.position;
	}
	
	/**
	 * @brief Getter für Building output_inventory
	 * @return this.output_inventory
	 */
	public ArrayList<Items> getOutput_Inventory() {
		return this.output_inventory;
	}
	
	/**
	 * @brief Getter für Building input_inventory
	 * @return this.input_inventory
	 */
	public ArrayList<Items> getInput_Inventory() {
		return this.input_inventory;
	}
	
	/**
	 * @brief Setter für Building output_inventory
	 * @param newInventory
	 */
	public void setOutput_Inventory(ArrayList<Items> newInventory) {
		this.output_inventory = newInventory;
	}
	
	/**
	 * Setter für Building input_inventory
	 * @param newInventory
	 */
	public void setInput_Inventory(ArrayList<Items> newInventory) {
		this.input_inventory = newInventory;
	}
}
