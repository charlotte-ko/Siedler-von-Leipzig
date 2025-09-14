package items;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import logic.Vector2D;

public class Pickaxe extends Items{
	
	private static String name = "Pickaxe";
	static BufferedImage pickaxeImg;
	
	static {
		try {
			
			pickaxeImg = ImageIO.read(Items.class.getResourceAsStream("/resources/Pickaxe.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @brief Konstruktor für Pickaxe
	 * @param pos
	 */
	public Pickaxe(Vector2D pos) {
		
		super(name, pos);
		img = pickaxeImg;
	}
	
	@Override
	public void reloadImg() {
		img = pickaxeImg;
	}
}

