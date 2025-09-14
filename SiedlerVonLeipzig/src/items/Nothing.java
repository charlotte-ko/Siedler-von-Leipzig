package items;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import logic.Vector2D;

public class Nothing extends Items{
	
	private static String name = "Nothing";
	static BufferedImage nothingImg;
	
	static {
		try {
			
			nothingImg = ImageIO.read(Items.class.getResourceAsStream("/resources/Cross.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @brief erstellt dummy Item
	 * @param pos
	 */
	public Nothing(Vector2D pos) {
		super(name, pos);
		img = nothingImg;
	}
	
	@Override
	public void reloadImg() {
		img = nothingImg;
	}
}
