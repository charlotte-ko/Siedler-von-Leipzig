package buildings;

import java.util.ArrayList;

import items.Items;
import logic.Settlers;
import logic.Vector2D;

public abstract class Buildings implements Buildable{
	
	/**
	 * Attribute
	 */
	protected final String name;
	private Vector2D position;
	public static ArrayList<Items> inventory = new ArrayList<>(); 
	private final int price; //Ich weiß nicht mehr ob das in die GUI reinkommt oder hier
	private boolean state = false; //Ist erstmal nicht voll, ich weiß noch nicht, ob das wichtig ist und was die Bedingung zum Überprüfen ist
	
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
	public static Buildable createBuilding(String building, Vector2D pos) {
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
	protected static Settlers closestSettler(Vector2D current_pos, ArrayList<Settlers> settlerList) {
		
		if(current_pos == null || settlerList == null || settlerList.isEmpty()) {
			return null;
		}
		
		float min_distance = Float.POSITIVE_INFINITY;
		Settlers closest = null;
		
		for(Settlers s : settlerList) {
			float distance = current_pos.getDistance(s.getPos());
			
			if(distance < min_distance) {
				min_distance = distance;
				closest = s;
			}
		}
		return closest;
	}
	
	/**
	 * Hilfsmethode zur Produktion von Gebäuden, kann später evtl. gelöscht werden
	 */
	public abstract void produce();

	
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
	 * Setter Methode um die Position eines Objektes zu bestimmen
	 * @param position
	 */
	public void setPosition(Vector2D position) {
		Buildings.position = position;
	}
}
