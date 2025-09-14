package items;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import logic.Vector2D;

public class Iron extends Items{
	
	private static String name = "Iron";
	private static BufferedImage ironImg;
	
	static {
		try {
			
			ironImg = ImageIO.read(Items.class.getResourceAsStream("/resources/Iron.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @brief Konstruktor für Iron
	 * @param pos
	 */
	public Iron(Vector2D pos) {
		super(name, pos);
		img = ironImg;
	}
	
	@Override
	public void reloadImg() {
		img = ironImg;
	}
}
