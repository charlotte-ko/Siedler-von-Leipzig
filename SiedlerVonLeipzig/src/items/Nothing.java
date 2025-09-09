package items;

import java.io.IOException;

import javax.imageio.ImageIO;

import logic.Vector2D;

public class Nothing extends Items{
	
	public Nothing(Vector2D pos) {
		super(pos);
		this.name = "Nothing";
		try {
			this.img = ImageIO.read(getClass().getResourceAsStream("/resources/Cross.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
