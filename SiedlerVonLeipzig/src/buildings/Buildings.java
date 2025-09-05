package buildings;


import java.util.ArrayList;
import items.IItems;
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
	
	/**
	 * Konstruktor
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
	 * Methode um aus einer Liste von Siedlern den nächsten zu finden
	 * @param current_pos
	 * @param settlerList
	 * @return closest Settler
	 */
	public ASettlers closestSettler(ArrayList<ASettlers> settlerList) {
		
		if(settlerList.isEmpty()) {
			return null;
		}
		
		float min_distance = Float.POSITIVE_INFINITY;
		ASettlers closest = null;
		
		for(ASettlers s : settlerList) {
			float distance = this.getPosition().getDistance(s.getPosition());
			
			if(distance < min_distance) {
				min_distance = distance;
				closest = s;
			}
		}
		return closest;
	}
	
	
	/**
	 * 
	 * @param settlerList
	 * @param ItemList
	 * @param job gewünschter Job
	 * @return einen Siedler mit gewünschtem Job oder einen normalen Siedler
	 * 
	 */
	public ASettlers findSettler(ArrayList<ASettlers> settlerList, ArrayList<IItems> ItemList, SettlersJobs job) {
		
		ArrayList<ASettlers> wanted = new ArrayList<>();
		
		for(ASettlers s : settlerList) {
			if(s.getJobName().equals(job)) {
				wanted.add(s);
			}
		}
		
		if(!wanted.isEmpty()) {return closestSettler(wanted);}
		
		ASettlers jobless = findSettler(settlerList, ItemList, SettlersJobs.JOB_NORMAL);
		
		if(jobless != null) {return jobless;}
		else {return null;}
	}
	
	public Vector2D getPosition() {
		return this.position;
	}
}
