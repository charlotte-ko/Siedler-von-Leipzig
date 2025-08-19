package logic;

import java.util.Vector;

public abstract class Buildings {
	
	//Attribute
	protected final String name;
	private Vector2D position;
	public static Vector<Items> inventory = new Vector<>();
	private final int price; //Ich weiß nicht mehr ob das in die GUI reinkommt oder hier
	private boolean state = false; //Ist erstmal nicht voll, ich weiß noch nicht, ob das wichtig ist und was die Bedingung zum Überprüfen ist
	
	//Konstruktor
	public Buildings(String name, int price) {
		this.name = name;
		this.price = price;	
		this.setPosition(new Vector2D());
	}
	
	//Factory zur Erstellung der Gebäude
	public static Buildings createBuilding(String Buildings) {
		switch(Buildings) {
		case "House" : return new House();
		case "Forester" : return new Forester();
		case "Sawmill" : return new Sawmill();
		default: throw new IllegalArgumentException("Fehler beim erstellen des Gebeaudes.");
		}
	}
	
	//Zur Fehlerfindung
	public abstract void produce();

	
	//Getter
	public int getPrice() {
		return price;
	}
	
	public boolean getState() {
		return state;
	}

	public Vector2D getPosition() {
		return position;
	}

	//Setter
	public void setPosition(Vector2D position) {
		this.position = position;
	}
}
