package items;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import logic.Vector2D;

public class Plank extends Items{
	
	private static String name = "Plank";
	static BufferedImage plankImg;
	
	static {
		try {
			
			plankImg = ImageIO.read(Items.class.getResourceAsStream("/resources/Plank.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @brief Konstruktor für Plank
	 * @param pos
	 */
	public Plank(Vector2D pos) {
		super(name, pos);
		img = plankImg;
	}
	
	@Override
	public void reloadImg() {
		img = plankImg;
	}
}
