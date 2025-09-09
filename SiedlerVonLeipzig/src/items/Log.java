package items;

import java.io.IOException;

import javax.imageio.ImageIO;

import logic.Vector2D;

public class Log extends Items {
	
	public Log(Vector2D pos) {
		super(pos);
		this.name = "Log";
		try {
			this.img = ImageIO.read(getClass().getResourceAsStream("/resources/Log.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
