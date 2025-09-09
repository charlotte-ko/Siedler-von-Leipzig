package settlers;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import settlers.SettlersJobs;
import logic.Vector2D;

public class Settler extends ASettlers{
	private static BufferedImage[] settlerNormalImg;
	private static BufferedImage[] settlerGardenerImg;
	private static BufferedImage[] settlerLumberjackImg;
	private static BufferedImage[] settlerSawyerImg;
	
	/**
	 * Mehr als 20 Zeilen, da nur Bilder geladen werden!!!
	 */
	static {
	    settlerNormalImg = new BufferedImage[6];
	    settlerGardenerImg = new BufferedImage[6];
	    settlerLumberjackImg = new BufferedImage[6];
	    settlerSawyerImg = new BufferedImage[6];
	    try {
	    	settlerNormalImg[0] = ImageIO.read(Settler.class.getResourceAsStream("/Settler/Normal_1.png"));
	    	settlerNormalImg[1] = ImageIO.read(Settler.class.getResourceAsStream("/Settler/Normal_2.png"));
	    	settlerNormalImg[2] = ImageIO.read(Settler.class.getResourceAsStream("/Settler/Normal_3.png"));
	    	settlerNormalImg[3] = ImageIO.read(Settler.class.getResourceAsStream("/Settler/Normal_Back_1.png"));
	    	settlerNormalImg[4] = ImageIO.read(Settler.class.getResourceAsStream("/Settler/Normal_Back_2.png"));
	    	settlerNormalImg[5] = ImageIO.read(Settler.class.getResourceAsStream("/Settler/Normal_Back_3.png"));
	    	
	    	settlerGardenerImg[0] = ImageIO.read(Settler.class.getResourceAsStream("/Settler/Gardener_1.png"));
	    	settlerGardenerImg[1] = ImageIO.read(Settler.class.getResourceAsStream("/Settler/Gardener_2.png"));
	    	settlerGardenerImg[2] = ImageIO.read(Settler.class.getResourceAsStream("/Settler/Gardener_3.png"));
	    	settlerGardenerImg[3] = ImageIO.read(Settler.class.getResourceAsStream("/Settler/Gardener_Back_1.png"));
	    	settlerGardenerImg[4] = ImageIO.read(Settler.class.getResourceAsStream("/Settler/Gardener_Back_2.png"));
	    	settlerGardenerImg[5] = ImageIO.read(Settler.class.getResourceAsStream("/Settler/Gardener_Back_3.png"));
	    	
	    	settlerLumberjackImg[0] = ImageIO.read(Settler.class.getResourceAsStream("/Settler/Lumberjack_1.png"));
	    	settlerLumberjackImg[1] = ImageIO.read(Settler.class.getResourceAsStream("/Settler/Lumberjack_2.png"));
	    	settlerLumberjackImg[2] = ImageIO.read(Settler.class.getResourceAsStream("/Settler/Lumberjack_3.png"));
	    	settlerLumberjackImg[3] = ImageIO.read(Settler.class.getResourceAsStream("/Settler/Lumberjack_Back_1.png"));
	    	settlerLumberjackImg[4] = ImageIO.read(Settler.class.getResourceAsStream("/Settler/Lumberjack_Back_2.png"));
	    	settlerLumberjackImg[5] = ImageIO.read(Settler.class.getResourceAsStream("/Settler/Lumberjack_Back_3.png"));
	    	
	    	settlerSawyerImg[0] = ImageIO.read(Settler.class.getResourceAsStream("/Settler/Sawyer_1.png"));
	    	settlerSawyerImg[1] = ImageIO.read(Settler.class.getResourceAsStream("/Settler/Sawyer_2.png"));
	    	settlerSawyerImg[2] = ImageIO.read(Settler.class.getResourceAsStream("/Settler/Sawyer_3.png"));
	    	settlerSawyerImg[3] = ImageIO.read(Settler.class.getResourceAsStream("/Settler/Sawyer_Back_1.png"));
	    	settlerSawyerImg[4] = ImageIO.read(Settler.class.getResourceAsStream("/Settler/Sawyer_Back_2.png"));
	    	settlerSawyerImg[5] = ImageIO.read(Settler.class.getResourceAsStream("/Settler/Sawyer_Back_3.png"));
	    	
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	
	Settler(Vector2D position){
		this.setSpeed(0.4f);
		this.setPosition(position);
		this.setIsWorking(false);
		this.setJobName(SettlersJobs.JOB_NORMAL);
		img = settlerNormalImg[0];
	}

	private double updateImgTimer = 0.0;
	@Override
	protected void update_img(double delta) {
		updateImgTimer += delta;
		updateImgTimer %= 1.0;
		int frameCounter = 0;
		if(updateImgTimer > 0.33 && updateImgTimer <= 0.66)frameCounter = 1;
		if(updateImgTimer > 0.66 && updateImgTimer <= 1.0)frameCounter = 2;
		
		if(this.isGoingUp)frameCounter += 3;
		
		switch(this.jobName) {
			case JOB_NORMAL:
				this.img = this.settlerNormalImg[frameCounter];
				break;
			case JOB_GARDENER:
				this.img = this.settlerGardenerImg[frameCounter];
				break;
			case JOB_LUMBERJACK:
				this.img = this.settlerLumberjackImg[frameCounter];
				break;
			case JOB_SAWYER:
				this.img = this.settlerSawyerImg[frameCounter];
				break;
			default:
				this.img = this.settlerNormalImg[frameCounter];
				break;
		}
	}
}

