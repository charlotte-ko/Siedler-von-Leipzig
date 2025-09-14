package items;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import logic.Vector2D;

public class Log extends Items {
	
	private static String name = "Log";
	static BufferedImage logImg;
	
	static {
		try {
			
			logImg = ImageIO.read(Items.class.getResourceAsStream("/resources/Log.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @brief Konstuktor für Log
	 * @param pos
	 */
	public Log(Vector2D pos) {
		super(name, pos);
		img = logImg;
	}

	@Override
	public void reloadImg() {
		img = logImg;
	}
}
