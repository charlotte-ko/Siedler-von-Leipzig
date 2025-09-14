package items;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import logic.Vector2D;

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


