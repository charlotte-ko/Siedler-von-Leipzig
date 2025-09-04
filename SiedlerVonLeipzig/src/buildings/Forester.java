package buildings;

import java.util.ArrayList;
import java.util.Random;

import items.IItems;
import items.Items;
import items.Tree;
import logic.Vector2D;
import settlers.ISettlers;
import settlers.SettlersAktion;

public class Forester extends Buildings {

	public Forester(Vector2D pos) {
		super("Forester", 10, pos);
	}
	

	/**
	 * Wir brauchen noch eine Methode die eine random Position in einem Umkreis zurückgibt, wo der Siedler den Baum pflanzt
	 */
	public void plantTree(ISettlers worker, Vector2D plantPos) {
		
		//Hier muss jetzt sowas wie worker.work hin oder sowas.
		
		/**
		 * Das ist schon mal gut nur muss alles andere in den Siedler geschrieben werden.
		 * (Kann ich machen)
		 * Die zufällige Punkt generierung ist scheiße. Muss übarbeitet werden.
		 */
		
		//muss dann noch zum Häuschen zurück
	}
	
	public void update(ArrayList<ISettlers> settlerList, ArrayList<Buildings> BuildingsList, ArrayList<Items> ItemList) {
		
		if(this.getOutput_Inventory().size() < 5) {
			this.output_inventory.add(new Tree(this.getPosition()));
		}
		
		ISettlers worker = this.findSettler(settlerList, ItemList, "NormalSettler", null);
		Vector2D plantPos = new Vector2D();
		do {
			plantPos = this.getPosition().genPos(5.0f);
		}while(!plantPos.testPos(BuildingsList, ItemList));
		
		
		if(!worker.isWorking()) {
			this.plantTree(worker, plantPos);
		}
		//zeitliche aspekt fehlt noch
	}
	
	//ArrayList<ISettlers> settlerList, ArrayList<IItems> ItemList, String job, IItems target

	@Override
	public void createBuilding() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ISettlers makeSettlers(ArrayList<ISettlers> settlerList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ISettlers findSettler(Vector2D current_pos, ArrayList<ISettlers> settlerList, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ISettlers findSettler(ArrayList<ISettlers> settlerList, String job) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ISettlers findSettler(ArrayList<ISettlers> settlerList, String job, IItems target) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void getLogs(ISettlers worker, ArrayList<IItems> itemList) {
		// TODO Auto-generated method stub
		
	}
	
	
 }
