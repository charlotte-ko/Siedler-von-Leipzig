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
	private static BufferedImage[] settlerMinerImg;
	private static BufferedImage[] settlerSmithImg;
	
	/**
	 * Mehr als 20 Zeilen, da nur Bilder geladen werden!!!
	 */
	static {
	    settlerNormalImg = new BufferedImage[8];
	    settlerGardenerImg = new BufferedImage[8];
	    settlerLumberjackImg = new BufferedImage[8];
	    settlerSawyerImg = new BufferedImage[8];
	    settlerSmithImg = new BufferedImage[8];
	    settlerMinerImg = new BufferedImage[8];
	    try {
	    	settlerNormalImg[0] = ImageIO.read(Settler.class.getResourceAsStream("/Settler/Normal_1.png"));
	    	settlerNormalImg[1] = ImageIO.read(Settler.class.getResourceAsStream("/Settler/Normal_2.png"));
	    	settlerNormalImg[2] = ImageIO.read(Settler.class.getResourceAsStream("/Settler/Normal_1.png"));
	    	settlerNormalImg[3] = ImageIO.read(Settler.class.getResourceAsStream("/Settler/Normal_3.png"));
	    	settlerNormalImg[4] = ImageIO.read(Settler.class.getResourceAsStream("/Settler/Normal_Back_1.png"));
	    	settlerNormalImg[5] = ImageIO.read(Settler.class.getResourceAsStream("/Settler/Normal_Back_2.png"));
	    	settlerNormalImg[6] = ImageIO.read(Settler.class.getResourceAsStream("/Settler/Normal_Back_1.png"));
	    	settlerNormalImg[7] = ImageIO.read(Settler.class.getResourceAsStream("/Settler/Normal_Back_3.png"));
	    	
	    	settlerGardenerImg[0] = ImageIO.read(Settler.class.getResourceAsStream("/Settler/Gardener_1.png"));
	    	settlerGardenerImg[1] = ImageIO.read(Settler.class.getResourceAsStream("/Settler/Gardener_2.png"));
	    	settlerGardenerImg[2] = ImageIO.read(Settler.class.getResourceAsStream("/Settler/Gardener_1.png"));
	    	settlerGardenerImg[3] = ImageIO.read(Settler.class.getResourceAsStream("/Settler/Gardener_3.png"));
	    	settlerGardenerImg[4] = ImageIO.read(Settler.class.getResourceAsStream("/Settler/Gardener_Back_1.png"));
	    	settlerGardenerImg[5] = ImageIO.read(Settler.class.getResourceAsStream("/Settler/Gardener_Back_2.png"));
	    	settlerGardenerImg[6] = ImageIO.read(Settler.class.getResourceAsStream("/Settler/Gardener_Back_1.png"));
	    	settlerGardenerImg[7] = ImageIO.read(Settler.class.getResourceAsStream("/Settler/Gardener_Back_3.png"));
	    	
	    	settlerLumberjackImg[0] = ImageIO.read(Settler.class.getResourceAsStream("/Settler/Lumberjack_1.png"));
	    	settlerLumberjackImg[1] = ImageIO.read(Settler.class.getResourceAsStream("/Settler/Lumberjack_2.png"));
	    	settlerLumberjackImg[2] = ImageIO.read(Settler.class.getResourceAsStream("/Settler/Lumberjack_1.png"));
	    	settlerLumberjackImg[3] = ImageIO.read(Settler.class.getResourceAsStream("/Settler/Lumberjack_3.png"));
	    	settlerLumberjackImg[4] = ImageIO.read(Settler.class.getResourceAsStream("/Settler/Lumberjack_Back_1.png"));
	    	settlerLumberjackImg[5] = ImageIO.read(Settler.class.getResourceAsStream("/Settler/Lumberjack_Back_2.png"));
	     	settlerLumberjackImg[6] = ImageIO.read(Settler.class.getResourceAsStream("/Settler/Lumberjack_Back_1.png"));
	    	settlerLumberjackImg[7] = ImageIO.read(Settler.class.getResourceAsStream("/Settler/Lumberjack_Back_3.png"));
	    	
	    	settlerSawyerImg[0] = ImageIO.read(Settler.class.getResourceAsStream("/Settler/Sawyer_1.png"));
	    	settlerSawyerImg[1] = ImageIO.read(Settler.class.getResourceAsStream("/Settler/Sawyer_2.png"));
	    	settlerSawyerImg[2] = ImageIO.read(Settler.class.getResourceAsStream("/Settler/Sawyer_1.png"));
	    	settlerSawyerImg[3] = ImageIO.read(Settler.class.getResourceAsStream("/Settler/Sawyer_3.png"));
	    	settlerSawyerImg[4] = ImageIO.read(Settler.class.getResourceAsStream("/Settler/Sawyer_Back_1.png"));
	    	settlerSawyerImg[5] = ImageIO.read(Settler.class.getResourceAsStream("/Settler/Sawyer_Back_2.png"));
	    	settlerSawyerImg[6] = ImageIO.read(Settler.class.getResourceAsStream("/Settler/Sawyer_Back_1.png"));
	    	settlerSawyerImg[7] = ImageIO.read(Settler.class.getResourceAsStream("/Settler/Sawyer_Back_3.png"));
	    	
	    	settlerMinerImg[0] = ImageIO.read(Settler.class.getResourceAsStream("/Settler/Miner_1.png"));
	    	settlerMinerImg[1] = ImageIO.read(Settler.class.getResourceAsStream("/Settler/Miner_2.png"));
	    	settlerMinerImg[2] = ImageIO.read(Settler.class.getResourceAsStream("/Settler/Miner_1.png"));
	    	settlerMinerImg[3] = ImageIO.read(Settler.class.getResourceAsStream("/Settler/Miner_3.png"));
	    	settlerMinerImg[4] = ImageIO.read(Settler.class.getResourceAsStream("/Settler/Miner_Back_1.png"));
	    	settlerMinerImg[5] = ImageIO.read(Settler.class.getResourceAsStream("/Settler/Miner_Back_2.png"));
	    	settlerMinerImg[6] = ImageIO.read(Settler.class.getResourceAsStream("/Settler/Miner_Back_1.png"));
	    	settlerMinerImg[7] = ImageIO.read(Settler.class.getResourceAsStream("/Settler/Miner_Back_3.png"));
	    	
	    	settlerSmithImg[0] = ImageIO.read(Settler.class.getResourceAsStream("/Settler/Smith_1.png"));
	    	settlerSmithImg[1] = ImageIO.read(Settler.class.getResourceAsStream("/Settler/Smith_2.png"));
	    	settlerSmithImg[2] = ImageIO.read(Settler.class.getResourceAsStream("/Settler/Smith_1.png"));
	    	settlerSmithImg[3] = ImageIO.read(Settler.class.getResourceAsStream("/Settler/Smith_3.png"));
	    	settlerSmithImg[4] = ImageIO.read(Settler.class.getResourceAsStream("/Settler/Smith_Back_1.png"));
	    	settlerSmithImg[5] = ImageIO.read(Settler.class.getResourceAsStream("/Settler/Smith_Back_2.png"));
	    	settlerSmithImg[6] = ImageIO.read(Settler.class.getResourceAsStream("/Settler/Smith_Back_1.png"));
	    	settlerSmithImg[7] = ImageIO.read(Settler.class.getResourceAsStream("/Settler/Smith_Back_3.png"));
	    	
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	
	Settler(Vector2D position){
		this.setSpeed(5.0f);
		this.setPosition(new Vector2D(position));
		this.setIsWorking(false);
		this.setJobName(SettlersJobs.JOB_NORMAL);
		img = settlerNormalImg[0];
	}

	private double updateImgTimer = 0.0;
	@Override
	protected void update_img(double delta) {
		updateImgTimer += delta;
		updateImgTimer %= 1.5;
		int frameCounter = 0;
		if(updateImgTimer > 0.33 && updateImgTimer <= 0.66)frameCounter = 1;
		if(updateImgTimer > 0.66 && updateImgTimer <= 1.0)frameCounter = 2;
		if(updateImgTimer > 0.99 && updateImgTimer <= 1.5)frameCounter = 3;
		
		if(this.isGoingUp)frameCounter += 4;
		
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
			case JOB_SMITH:
				this.img = this.settlerSmithImg[frameCounter];
				break;
			case JOB_MINER:
				this.img = this.settlerMinerImg[frameCounter];
				break;
			default:
				this.img = this.settlerNormalImg[frameCounter];
				break;
		}
	}
}

