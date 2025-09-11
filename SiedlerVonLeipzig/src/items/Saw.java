package items;

import java.io.IOException;

import javax.imageio.ImageIO;

import logic.Vector2D;

public class Saw extends Items {
	
	public static int price = 5;
	
	public Saw(Vector2D pos) {
		super(pos);
		this.name = "Saw";
		try {
			this.img = ImageIO.read(getClass().getResourceAsStream("/resources/Saw.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
