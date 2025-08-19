package logic;

import java.util.Vector;

public abstract class Buildings {
	
	/**
	 * Attribute
	 */
	protected final String name;
	private Vector2D position;
	public static Vector<Items> inventory = new Vector<>();
	private final int price; //Ich weiß nicht mehr ob das in die GUI reinkommt oder hier
	private boolean state = false; //Ist erstmal nicht voll, ich weiß noch nicht, ob das wichtig ist und was die Bedingung zum Überprüfen ist
	
	/**
	 * Konstruktor
	 * @param name
	 * @param price
	 */
	public Buildings(String name, int price) {
		this.name = name;
		this.price = price;	
		this.setPosition(new Vector2D());
	}
	
	/**
	 * Methode welche Objekte vom Typ Building durch das Factory Pattern erstellt
	 * 
	 * @param Buildings
	 * @return eine Instanz des erstellten Buildings
	 * @throws IllegalArgumentsException wenn der erstellte Gebäudetyp nicht existiert (sollte eiegntlich nicht passieren, aber sicher ist sicher)
	 */
	public static Buildings createBuilding(String Buildings) {
		switch(Buildings) {
		case "House" : return new House();
		case "Forester" : return new Forester();
		case "Sawmill" : return new Sawmill();
		default: throw new IllegalArgumentException("Fehler beim erstellen des Gebeaudes.");
		}
	}
	
	/**
	 * Methode zur Produktion von Gebäuden, kann später evtl. gelöscht werden
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
		this.position = position;
	}
}
