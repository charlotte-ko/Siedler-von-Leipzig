package items;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import logic.Vector2D;

public class Hammer extends Items {
	
	private static String name = "Hammer";
	
	static BufferedImage hammerImg;
	static {
		try {
			
			hammerImg = ImageIO.read(Items.class.getResourceAsStream("/resources/Hammer.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @brief Konstruktor für Hammer
	 * @param pos
	 */
	public Hammer(Vector2D pos) {
		super(name, pos);
		img = hammerImg;
	}
	
	@Override
	public void reloadImg() {
		img = hammerImg;
	}
}
