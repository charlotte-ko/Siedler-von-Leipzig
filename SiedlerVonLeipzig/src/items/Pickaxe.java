package items;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import logic.Vector2D;

public class Pickaxe extends Items{
	static BufferedImage pickaxeImg;
	static {
		try {
			pickaxeImg = ImageIO.read(Items.class.getResourceAsStream("/resources/Pickaxe.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public Pickaxe(Vector2D pos) {
		super(pos);
		this.name = "Pickaxe";
		img = pickaxeImg;
	}
	@Override
	public void reloadImg() {
		img = pickaxeImg;
	}
}

