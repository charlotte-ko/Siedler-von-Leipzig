package logic;

import java.util.Vector;

public class House extends Buildings {

	public static Vector<Settlers> sInventory = new Vector<>();
	
	public House() {
		super("House", 10);
	}
	
	//Methode um Siedler zu produzieren, Bedingung fehlt noch
	public void makeSettlers() {
		sInventory.add(new Settlers(new Vector2D()));
	}
	
	//Kontrollausgabe
	public void produce() {System.out.println(inventory);}
}
