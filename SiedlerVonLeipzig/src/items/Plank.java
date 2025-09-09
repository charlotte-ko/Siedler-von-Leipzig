package items;

import java.io.IOException;

import javax.imageio.ImageIO;

import logic.Vector2D;

public class Plank extends Items{
	
	public Plank(Vector2D pos) {
		super(pos);
		this.name = "Plank";
		try {
			this.img = ImageIO.read(getClass().getResourceAsStream("/resources/Plank.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
