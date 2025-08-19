package logic;

public class Forester extends Buildings {

	public Forester() {
		super("Forester", 10);
	}

	//Methode um Baumstämme zu produzieren und Inventar zuzufügen -> Zugriff auf Siedler fehlt noch, kommt später
	public void makeLogs() {
		inventory.add(new Log(new Vector2D()));
	}
	
	//Kontrollausgabe
	public void produce() {System.out.println(inventory);};
 }
