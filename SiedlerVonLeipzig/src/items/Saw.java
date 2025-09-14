package items;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import logic.Vector2D;

public class Saw extends Items {
	
	private static String name = "Saw";
	static BufferedImage sawImg;
	
	static {
		try {
			
			sawImg = ImageIO.read(Items.class.getResourceAsStream("/resources/Saw.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @brief Konstruktor für Saw
	 * @param pos
	 */
	public Saw(Vector2D pos) {
		super(name, pos);
		img = sawImg;
	}
	
	@Override
	public void reloadImg() {
		img = sawImg;
	}
}
