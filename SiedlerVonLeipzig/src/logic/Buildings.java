package logic;

import java.util.Vector;

public abstract class Buildings {
	
	//Attribute
	public static Vector<Buildings> placedBuildings = new Vector<>();
	protected final String variation;
	private float x;
	private float y;
	public static Vector<Items> inventory = new Vector<>();
	public static Vector<Settlers> sInventory = new Vector<>(); //Entweder zwei Arten von Inventar für Siedler und Items, oder Siedler sind Items
	private final int price; //Ich weiß nicht mehr ob das in die GUI reinkommt oder hier
	private boolean state = false; //Ist erstmal nicht voll, ich weiß noch nicht, ob das wichtig ist und was die Bedingung zum Überprüfen ist
	
	//Konstruktor
	public Buildings(String variation, int price) {
		this.variation = variation;
		this.price = price;	
	}
	
	//Factory zur Erstellung der Gebäude
	public static Buildings createBuilding(String Buildings) {
		switch(Buildings) {
		case "House" : placedBuildings.add(new House());
					   return new House();
		case "Forester" : placedBuildings.add(new House());
						  return new Forester();
		case "Sawmill" : placedBuildings.add(new House());
		 				 return new Sawmill();
		default: throw new IllegalArgumentException("Fehler beim erstellen des Gebeaudes.");
		}
	}
	
	//Zur Fehlerfindung
	public abstract void produce();
	
	//Ausgabe der erstellten Gebäude im Vektor
	public void printBuildings() {
		for(Buildings b : placedBuildings) {
			System.out.println("Art: " + variation + " Position: " + b.getX() + ", " + b.getY() + "Inventar: " + inventory + sInventory + " Preis: " + b.getPrice() + " Zustand: " + state);
		}
	}
	
	//Getter
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public int getPrice() {
		return price;
	}
	
	public boolean getState() {
		return state;
	}
	
	//Setter
	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.y = y;
	}
}
