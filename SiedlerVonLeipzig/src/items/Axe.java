package items;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import logic.Vector2D;

public class Axe extends Items {
	static BufferedImage axeImg;
	static {
		try {
			axeImg = ImageIO.read(Items.class.getResourceAsStream("/resources/Axe.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Axe(Vector2D pos) {
		super(pos);
		this.name = "Axe";
		img = axeImg;
	}

	@Override
	public void reloadImg() {
		img = axeImg;
	}

}
