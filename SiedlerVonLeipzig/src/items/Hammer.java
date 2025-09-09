package items;

import java.io.IOException;

import javax.imageio.ImageIO;

import logic.Vector2D;

public class Hammer extends Items {

	public Hammer(Vector2D pos) {
		super(pos);
		this.name = "Hammer";
		try {
			this.img = ImageIO.read(getClass().getResourceAsStream("/resources/Hammer.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
