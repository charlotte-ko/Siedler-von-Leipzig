package items;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import logic.Vector2D;
import settlers.Settler;

public class Tree extends Items {
	
	private static String name = "Tree";
	private static BufferedImage treeImg;
	
	static {
		try {
			
			treeImg = ImageIO.read(Tree.class.getResourceAsStream("/resources/Tree.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @brief Konstruktor für Tree
	 * @param pos
	 */
	public Tree(Vector2D pos) {
		super(name, pos);
		this.img = treeImg;
	}
	
	/**
	 * @brief sucht nach dem nächsten Baum zu einer bestimmten Position
	 * @param current_pos	relative Position
	 * @param ItemList
	 * @return den nächsten Baum
	 */
	public static Tree FindTree(Vector2D current_pos, ArrayList<Items> ItemList) {
		
		Tree closestTree = null;
		float min_distance = Float.POSITIVE_INFINITY;
		
		for(Items item : ItemList) {
			
			if(!isTree(item)) {
				continue;
			}
			
			float distance = current_pos.getDistance(item.position);
			
			if(distance <= min_distance) {
				min_distance = distance;
				closestTree = (Tree) item;
			}
			
		}return closestTree;
	}
	
	/**
	 * @brief prüft, ob es sich bei Item um Tree handelt
	 * @param ListItem
	 * @return true = wenn Item ein Tree ist, false = wenn Item kein Tree ist
	 */
	public static boolean isTree(Items ListItem) {
		
		if(ListItem.name.equals("Tree")) {
			return true;
			
		}return false;
	}
	
	public void draw(Graphics2D g2,int tileSize,Vector2D camPos) {
		//normal
		int posX = (int)((position.x)*(float)tileSize) - (int)((camPos.x)*(float)tileSize);
		int posY = (int)((position.y)*(float)tileSize) - (int)((camPos.y)*(float)tileSize);
		
		if(!this.held && !this.inInventoryOutput) {
			g2.drawImage(img, posX, posY, tileSize,tileSize,null);
		}else {
			g2.drawImage(img, posX, posY, tileSize/2,tileSize/2,null);
		}	
	}

	@Override
	public void reloadImg() {
		img = treeImg;
	}
}


