package buildings;

import java.util.ArrayList;

import items.IItems;
import logic.Vector2D;
import settlers.ISettlers;

public class Lodge extends Buildings{

	public Lodge(Vector2D pos) {
		super("Lumberjack", 10, pos);
	}

	private void getLogs() {
		
	}
	
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
