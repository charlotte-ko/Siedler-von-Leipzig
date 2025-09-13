package items;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import logic.Vector2D;

public class Nothing extends Items{
	static BufferedImage nothingImg;
	static {
		try {
			nothingImg = ImageIO.read(Items.class.getResourceAsStream("/resources/Cross.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public Nothing(Vector2D pos) {
		super(pos);
		this.name = "Nothing";
		img = nothingImg;
	}
}
