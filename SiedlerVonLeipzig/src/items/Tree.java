package items;

import java.util.ArrayList;

import logic.Vector2D;

public class Tree extends Items {
	
	public Tree(Vector2D pos) {
		super(pos);
		this.name = "Tree";
		//image muss in GUI auf die Position gesetzt werden
	}
	
	public static Tree FindTree(Vector2D current_pos, ArrayList<Items> ItemList) {
		
		Tree closestTree = null;
		float min_distance = Float.POSITIVE_INFINITY; //Random großer Wert, muss vielleicht angepasst werden
		
		for(Items item : ItemList) {
			if(!isTree(item)) {
				continue;
				}
			float distance = current_pos.getDistance(item.position);
			if(distance <= min_distance) {
				min_distance = distance;
				closestTree = (Tree) item;
			}
		} return closestTree;
	}
	
	public static boolean isTree(Items ListItem) {
		
		if(ListItem.name.equals("Tree")) {
			return true;
		}
		return false;
	}
}


