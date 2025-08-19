package logic;

public class Sawmill extends Buildings {

	public Sawmill() {
		super("Sawmill", 10);
	}

	//Methode um Planken herzustellen und ins Inventar zu packen -> Siedler Inventar überprüfen wie??
	public void makePlanks() {
		for(Items item : inventory) {
			if(item.equals("Saw")) {
				inventory.add(new Plank(new Vector2D()));
			}
		}
	}
	
	//Kontrollausgabe
	public void produce() {System.out.println(inventory);};
}
