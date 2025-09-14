package items;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import logic.Vector2D;

public class Saw extends Items {
	static BufferedImage sawImg;
	static {
		try {
			sawImg = ImageIO.read(Items.class.getResourceAsStream("/resources/Saw.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Saw(Vector2D pos) {
		super(pos);
		this.name = "Saw";
		img = sawImg;
	}

	@Override
	public void reloadImg() {
		img = sawImg;
	}

}
