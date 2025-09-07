package buildings;


import java.awt.Graphics2D;
import java.util.ArrayList;
import items.IItems;
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
	protected ArrayList<IItems> input_inventory = new ArrayList<>(); 
	protected ArrayList<IItems> output_inventory = new ArrayList<>();
	private final int price; //Ich weiß nicht mehr ob das in die GUI reinkommt oder hier
	
	protected ASettlers employee;
	protected boolean hasEmployee;
	
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
	 * Methode welche Objekte vom Typ Building durch das Factory Pattern erstellt
	 * 
	 * @param Buildings
	 * @return eine Instanz des erstellten Buildings
	 * @throws IllegalArgumentsException wenn der erstellte Gebäudetyp nicht existiert (sollte eiegntlich nicht passieren, aber sicher ist sicher)
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
	 * @brief Sucht aus einer Liste den nähst gelegenen Siedler
	 * @param current_pos	von der aus gesucht wird
	 * @param settlerList
	 * @return closest Settler oder null bei nicht finden
	 * @note es findet keine prüfung statt ob der Siedler schon verwendet wird
	 */
	public ASettlers closestSettler(ArrayList<ASettlers> settlerList) {
		ArrayList<ASettlers> availableSettlers = new ArrayList<>();
		
		if(settlerList.isEmpty()) return null;
		
		float min_distance = Float.POSITIVE_INFINITY;
		ASettlers closest = null;
		
		for(ASettlers s : settlerList) {
			
			if(!s.getEmployed() && !s.isWorking()) {
				availableSettlers.add(closest);
			}
		}
		if(availableSettlers.isEmpty()) return null;
		
		for(ASettlers s : availableSettlers) {
			float distance = this.getPosition().getDistance(s.getPosition());
			
			if(distance < min_distance) {
				min_distance = distance;
				closest = s;
			}
		} return closest;
	}
	
	
	/**
	 * @param settlerList
	 * @param ItemList
	 * @param job gewünschter Job
	 * @return einen Siedler mit gewünschtem Job oder einen normalen Siedler
	 */
	public ASettlers findSettler(ArrayList<ASettlers> settlerList, ArrayList<Items> ItemList, SettlersJobs job) {
		
		ArrayList<ASettlers> wanted = new ArrayList<>();
		
		for(ASettlers s : settlerList) {
			if(s.getJobName().equals(job) && !s.getEmployed() && !s.isWorking()) {
				wanted.add(s);
			}
		}
		
		if(!wanted.isEmpty()) {
			return this.closestSettler(wanted);
		}
	
		else return null;
	}
	
	public ASettlers findEmployee(ArrayList<ASettlers> settlerList, ArrayList<Items> ItemList, SettlersJobs job) {
		
		this.employee = this.findSettler(settlerList, ItemList, job);
		
		if(this.employee == null) {
			return null;
		}
		else if(this.employee.getJobName().equals(SettlersJobs.JOB_NORMAL)) {
			this.employee.setJobName(job);
		}
		this.hasEmployee = true;
		return employee;
	}
	
	public Vector2D getPosition() {
		return this.position;
	}
	
	public ArrayList<IItems> getOutput_Inventory() {
		return this.output_inventory;
	}
	
	public void setOutput_Inventory(ArrayList<IItems> newInventory) {
		this.output_inventory = newInventory;
	}
	
	public ArrayList<IItems> getInput_Inventory() {
		return this.input_inventory;
	}
	
	public void setInput_Inventory(ArrayList<IItems> newInventory) {
		this.input_inventory = newInventory;
	}

	protected abstract void draw(Graphics2D g2, int tileSize);
}
