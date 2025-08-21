package logic;

import java.util.ArrayList;

public class Tree extends Items {
	
	private static int ListIndex;
	
	public Tree(Vector2D pos) {
		super(pos);
		this.name = "Tree";
		//image muss in GUI auf die Position gesetzt werden
	}
	
	public Tree FindTree(Vector2D current_pos, ArrayList<Items> ItemList) {
		
		Tree closestTree = null;
		float min_distance = 10000; //Random großer Wert, muss vielleicht angepasst werden
		
		for(Items item : ItemList) {
			if(isTree(item)) {
				float distance = current_pos.getDistance(item.position);
				if(distance <= min_distance) {
					min_distance = distance;
					closestTree = (Tree) item;
				}
			}
		} return closestTree;
	}
	
	public boolean isTree(Items ListItem) {
		
		if(ListItem.name.equals("Tree")) {
			return true;
		}
		return false;
	}
}


