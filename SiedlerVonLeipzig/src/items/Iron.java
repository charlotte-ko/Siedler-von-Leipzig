package items;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import logic.Vector2D;

public class Iron extends Items{
	static BufferedImage ironImg;
	static {
		try {
			ironImg = ImageIO.read(Items.class.getResourceAsStream("/resources/Iron.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public Iron(Vector2D pos) {
		super(pos);
		this.name = "Iron";
		img = ironImg;
	}
}
