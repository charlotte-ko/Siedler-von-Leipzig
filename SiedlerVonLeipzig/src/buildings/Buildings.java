package buildings;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;


import items.IItems;
import settlers.ISettlers;
import settlers.SettlersAktion;
import logic.Vector2D;

public abstract class Buildings implements IBuildings{
	
	/**
	 * Attribute
	 */
	protected final String name;
	private Vector2D position;
	protected ArrayList<IItems> input_inventory = new ArrayList<>(); 
	protected ArrayList<IItems> output_inventory = new ArrayList<>();
	private final int price; //Ich weiß nicht mehr ob das in die GUI reinkommt oder hier
	protected boolean state = false; //Ist erstmal nicht voll, ich weiß noch nicht, ob das wichtig ist und was die Bedingung zum Überprüfen ist
	protected int task_counter = 0;
	
	/**
	 * Konstruktor
	 * @param name
	 * @param price
	 * @param position
	 */
	public Buildings(String name, int price, Vector2D pos) {
		this.name = name;
		this.price = price;	
		position = pos;
		this.setPosition(new Vector2D());
	}
	
	/**
	 * Methode welche Objekte vom Typ Building durch das Factory Pattern erstellt
	 * 
	 * @param Buildings
	 * @return eine Instanz des erstellten Buildings
	 * @throws IllegalArgumentsException wenn der erstellte Gebäudetyp nicht existiert (sollte eiegntlich nicht passieren, aber sicher ist sicher)
	 */
	public static IBuildings createBuilding(String building, Vector2D pos) {
		switch(building) {
		case "House" : return new House(pos);
		case "Forester" : return new Forester(pos);
		case "Sawmill" : return new Sawmill(pos);
		default: throw new IllegalArgumentException("Unexpected Value: " + building);
		}
	}
	
	/**
	 * Methode um aus einer Liste von Siedlern den nächsten zu finden
	 * @param current_pos
	 * @param settlerList
	 * @return closest Settler
	 */
	protected ISettlers closestSettler(ArrayList<ISettlers> settlerList) {
		
		if(settlerList.isEmpty()) {
			return null;
		}
		
		float min_distance = Float.POSITIVE_INFINITY;
		ISettlers closest = null;
		
		for(ISettlers s : settlerList) {
			float distance = this.getPosition().getDistance(s.getPosition());
			
			if(distance < min_distance) {
				min_distance = distance;
				closest = s;
			}
		}
		return closest;
	}
	public ISettlers findSettler(ArrayList<ISettlers> settlerList) {
		
		ISettlers available;
		
		do {
			
			 available = closestSettler(settlerList);
			 
		}while(!available.getJob().isEmpty());
		
		available.giveSettlerTask(available.getPosition(), SettlersAktion.GO_BRING_RIGHT, available.getItemHand(false));
		return available;
	}
	
	/**
	 * Methode sucht einen Siedler mit gebrauchtem Job, gibt es keinen Siedler mit diesem Job,
	 * geben wir einem Normalem Siedler einen Job, indem wir ihm sagen -> Hol Item für Job
	 * 
	 */
	public ISettlers findSettler(ArrayList<ISettlers> settlerList, ArrayList<IItems> ItemList, String job, IItems target) {
		
		ArrayList<ISettlers> wanted = new ArrayList<>();
		
		for(ISettlers s : settlerList) {
			if(s.getJob().equals(job)) {
				wanted.add(s);
			}
		}
		if(!wanted.isEmpty()) {return closestSettler(wanted);}
		
		ISettlers jobless = findSettler(settlerList, ItemList, "Normal", null);
			
		if(jobless == null) {
			
			jobless = findSettler(settlerList);
			jobless.giveSettlerTask(jobless.closestItem(jobless.getPosition(), ItemList ,target.getName()).getPosition(), SettlersAktion.GO_PICKUP_RIGHT, target);
			
		}return jobless;
	}
	
	/**
	 * Getter Methode um den Preis eines Objektes zu bekommen
	 * @return price
	 */
	public int getPrice() {
		return price;
	}
	
	/**
	 * Getter Methode um den Zustand eines Objektes zu bekommen
	 * @return state
	 */
	public boolean getState() {
		return state;
	}

	/**
	 * Getter Methode um die Position eines Objektes zu bekommen
	 * @return Vector2D  position 
	 */
	public Vector2D getPosition() {
		return position;
	}
	
	/**
	 * Getter Methode für das Input_Inventar des Gebäudes
	 * @return inventory
	 */
	public ArrayList<IItems> getInput_Inventory() {
		return input_inventory;
	}
	
	/**
	 * Getter für Output_Inventar
	 * @return inventory
	 */
	public ArrayList<IItems> getOutput_Inventory() {
		return output_inventory;
	}
	
	/**
	 * Setter Methode für das Input_Inventar eines Gebäudes zu bestimmen
	 * @param inventory
	 */
	public void setInventory(ArrayList<IItems> input_inventory) {
		this.input_inventory = input_inventory;
	}
	
	/**
	 * Setter für Output_Inventar
	 * @param output_inventory
	 */
	public void setOutput_Inventory(ArrayList<IItems> output_inventory) {
		this.output_inventory = output_inventory;
	}
	/**
	 * Setter Methode um die Position eines Objektes zu bestimmen
	 * @param position
	 */
	public void setPosition(Vector2D position) {
		this.position = position;
	}
	
	public void draw(Graphics2D g2,int tileSize) {
		//g2.drawRect((int)this.position.x, (int)this.position.y, 8, 8);
		g2.setColor(Color.white);
		g2.fillRect((int)this.position.x, (int)this.position.y, tileSize,tileSize);
	}
	
	public void update(double delta) {}

	public String getName() {
		return this.name;
	}

}
