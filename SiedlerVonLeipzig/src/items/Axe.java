package items;

import java.io.IOException;

import javax.imageio.ImageIO;

import logic.Vector2D;

public class Axe extends Items {
	
	public static int price = 5;
	
	public Axe(Vector2D pos) {
		super(pos);
		this.name = "Axe";
		try {
			this.img = ImageIO.read(getClass().getResourceAsStream("/resources/Axe.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
