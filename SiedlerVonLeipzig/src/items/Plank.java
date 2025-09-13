package items;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import logic.Vector2D;

public class Plank extends Items{
	static BufferedImage plankImg;
	static {
		try {
			plankImg = ImageIO.read(Items.class.getResourceAsStream("/resources/Plank.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public Plank(Vector2D pos) {
		super(pos);
		this.name = "Plank";
		img = plankImg;
	}
}
