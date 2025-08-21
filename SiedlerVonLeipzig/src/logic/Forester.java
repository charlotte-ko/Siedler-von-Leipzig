package logic;

public class Forester extends Buildings {

	public Forester() {
		super("Forester", 10);
	}

	/**
	 * Methode um Baumstämme zu produzieren, Bedingung und Zuweisung fehlt noch
	 */
	public void makeLogs() {
		inventory.add(new Log(new Vector2D()));
	}
	
	/**
	 * Kontrollausgabe um das aktuelle Inventar des Objektes auszugeben
	 */
	public void produce() {System.out.println(inventory);};
 }
