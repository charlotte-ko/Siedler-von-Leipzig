package items;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import logic.Vector2D;

public class Log extends Items {
	static BufferedImage logImg;
	static {
		try {
			logImg = ImageIO.read(Items.class.getResourceAsStream("/resources/Log.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Log(Vector2D pos) {
		super(pos);
		this.name = "Log";
		img = logImg;
	}
}
