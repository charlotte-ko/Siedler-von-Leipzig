package items;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import logic.Vector2D;
import settlers.Settler;

public class Tree extends Items {
	private static BufferedImage treeImg;
	static {
		try {
			treeImg = ImageIO.read(Tree.class.getResourceAsStream("/resources/Tree.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Tree(Vector2D pos) {
		super(pos);
		this.name = "Tree";
		//image muss in GUI auf die Position gesetzt werden
		this.img = treeImg;
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
	
	public void draw(Graphics2D g2,int tileSize,Vector2D camPos) {
		//normal
		int posX = (int)((position.x)*(float)tileSize) - (int)((camPos.x)*(float)tileSize);
		int posY = (int)((position.y)*(float)tileSize) - (int)((camPos.y)*(float)tileSize);
		
		if(!this.held && !this.inInventory) {
			g2.drawImage(img, posX, posY, tileSize,tileSize,null);
		}else {
			g2.drawImage(img, posX, posY, tileSize/2,tileSize/2,null);
		}
		
	}
}


