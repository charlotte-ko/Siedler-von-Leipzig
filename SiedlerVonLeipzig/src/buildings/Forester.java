package buildings;

import java.util.ArrayList;
import java.util.Random;

import items.IItems;
import items.Tree;
import logic.Vector2D;
import settlers.ISettlers;
import settlers.SettlersAktion;

public class Forester extends Buildings {

	public Forester(Vector2D pos) {
		super("Forester", 10, pos);
	}
	
	private ArrayList<Vector2D> positionList = this.getPosition().genPos(4.0f);
	
	public void changePositionList(int used) {
		ArrayList<Vector2D> List = this.positionList;
		for(int i = 0; i < List.size(); i++) {
			
			if(List.get(used).getDistance(List.get(i)) <= 2.0f) {
				List.remove(i);
			}
		}
		List.remove(used);
		
	}

	/**
	 * Wir brauchen noch eine Methode die eine random Position in einem Umkreis zurückgibt, wo der Siedler den Baum pflanzt
	 */
	public void plantTree(ISettlers worker) {
		
		Random rnd = new Random();
		int index = rnd.nextInt(this.positionList.size()-1);
		
		/**
		 * Das ist schon mal gut nur muss alles andere in den Siedler geschrieben werden.
		 * (Kann ich machen)
		 * Die zufällige Punkt generierung ist scheiße. Muss übarbeitet werden.
		 */
		worker.giveSettlerTask(this.getPosition(), SettlersAktion.GO_PICKUP_LEFT, this.output_inventory.get(output_inventory.size() - 1));
		
		worker.giveSettlerTask(this.positionList.get(index), SettlersAktion.GO_WORK, null);
		this.changePositionList(index);
	}
	
	public void update(ArrayList<ISettlers> settlerList, ArrayList<IItems> ItemList) {
		
		if(this.getOutput_Inventory().size() < 5) {
			this.output_inventory.add(new Tree(this.getPosition()));
		}
		
		ISettlers worker = this.findSettler(settlerList, ItemList, "NormalSettler", null);
		
		if(!worker.isWorking()) {
			this.plantTree(worker);
		}
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
