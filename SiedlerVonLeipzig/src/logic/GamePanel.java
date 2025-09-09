package logic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import buildings.Buildings;
import items.Items;
import settlers.ASettlers;
import settlers.SettlersAktion;
import settlers.SettlersJobs;


public class GamePanel extends JPanel implements Runnable{
	//Screen settings
	final int originalTileSize = 16; //16x16 Size
	final int scale = 3;
	
	public int tileSize = originalTileSize * scale; //48x48
	final int maxScreenCol = 16;
	final int maxScreenRow = 12;
	final int screenWidth = tileSize * maxScreenCol;//768pxl
	final int screenHeight = tileSize * maxScreenRow;//576pxl
	
	public int grid_zoom = 8;
	public Vector2D camPos = new Vector2D();
	KeyHandler keyH = new KeyHandler();
	int FPS = 60;
	double drawIntervalNanos = 1_000_000_000.0 /FPS;
	
	Thread gameThread;
	
	ArrayList<Items> itemList = new ArrayList<>();
	ArrayList<Buildings> buildingList = new ArrayList<>();
	ArrayList<ASettlers> settlerList = new ArrayList<>();
	
	private BufferedImage backgroundImg1;
	private BufferedImage backgroundImg2;
	private BufferedImage backgroundImg;
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth,screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
		
		this.loadBackgroundImg();
	}

	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	@Override
	public void run() {
		long lastTime = System.nanoTime();
		long currentTime;
		double frameAcc = 0;
		long secAccNanos = 0;
		int drawCount = 0;
		
		long lastFrame = System.nanoTime();
		long currentFrame;
		double delta = 0.0;
		
		//prepare
		for(int i = 0; i < 10;i++) {
			for(int j = 0; j < 10;j++) {
				itemList.add(Items.createItem("Axe", new Vector2D(i,j)));
				itemList.add(Items.createItem("Hammer", new Vector2D(i+10,j)));
				itemList.add(Items.createItem("Log", new Vector2D(i,j+10)));
				//itemList.add(Items.createItem("Nothing", new Vector2D(i*2,j*2)));
				itemList.add(Items.createItem("Plank", new Vector2D(i+10,j+10)));
				itemList.add(Items.createItem("Saw", new Vector2D(i+20,j)));
				itemList.add(Items.createItem("Tree", new Vector2D(i+20,j+10)));
			}
		}
		settlerList.add(ASettlers.createSettler(new Vector2D()));
		settlerList.get(0).giveSettlerTask(new Vector2D(tileSize*10,tileSize*10), SettlersAktion.GO_FREE, null);
		settlerList.add(ASettlers.createSettler(new Vector2D()));
		settlerList.get(1).setJobName(SettlersJobs.JOB_SAWYER);
		settlerList.get(1).giveSettlerTask(new Vector2D(-tileSize*10,-tileSize*10), SettlersAktion.GO_FREE, null);
		
		buildingList.add(Buildings.createBuilding("Forester",new Vector2D(0.0f,0.0f)));
		buildingList.add(Buildings.createBuilding("Forester",new Vector2D(3.0f,0.0f)));
		buildingList.add(Buildings.createBuilding("Forester",new Vector2D(-3.0f,0.0f)));
		
		buildingList.add(Buildings.createBuilding("Sawmill",new Vector2D(0.0f,2.5f)));
		buildingList.add(Buildings.createBuilding("Sawmill",new Vector2D(3.0f,2.5f)));
		buildingList.add(Buildings.createBuilding("Sawmill",new Vector2D(-3.0f,2.5f)));
		
		buildingList.add(Buildings.createBuilding("Lodge",new Vector2D(0.0f,-2.5f)));
		buildingList.add(Buildings.createBuilding("Lodge",new Vector2D(3.0f,-2.5f)));
		buildingList.add(Buildings.createBuilding("Lodge",new Vector2D(-3.0f,-2.5f)));
		
		buildingList.add(Buildings.createBuilding("House",new Vector2D(0.0f,5.0f)));
		buildingList.add(Buildings.createBuilding("House",new Vector2D(3.0f,5.0f)));
		buildingList.add(Buildings.createBuilding("House",new Vector2D(-3.0f,5.0f)));
		
		//game loop
		while(gameThread != null) {
			currentTime = System.nanoTime();
			long frameNanos = currentTime - lastTime;
			lastTime = currentTime;
			
			frameAcc += frameNanos / drawIntervalNanos;
			secAccNanos += frameNanos;
			
			
			if(frameAcc >= 1) {
				currentFrame = System.nanoTime();
				delta = (currentFrame - lastFrame)/1_000_000_000.0;
				lastFrame = currentFrame;
				update(delta);
				repaint(); //ruft paintComponent auf
				frameAcc -= 1.0;
				drawCount++;
			}
			if(secAccNanos >= 1_000_000_000) {
				System.out.println("FPS:"+drawCount+"dt(ms):"+(delta*1000.0));
				System.out.println(camPos.x + "/" + camPos.y);
				drawCount = 0;
				secAccNanos = 0;
			}
		}
	}
	
	private boolean zoomInBlock = false;
	private boolean zoomOutBlock = false;
	private void zoomManager() {
		
		if(this.keyH.zoomIn && !zoomInBlock) {
			this.grid_zoom--;
			zoomInBlock = true;
		}
		if(this.keyH.zoomOut && !zoomOutBlock) {
			this.grid_zoom++;
			zoomOutBlock = true;
		}
		
		if(this.grid_zoom < 1)this.grid_zoom = 1;
		if(this.grid_zoom > 30)this.grid_zoom = 30;
	
		if(!this.keyH.zoomIn)zoomInBlock = false;
		if(!this.keyH.zoomOut)zoomOutBlock = false;
	}
	
	float cameraSpeed = 4.0f;
	private void cameraManager(double delta) {
		if(this.keyH.upPressed)camPos.y -= delta * cameraSpeed;
		if(this.keyH.downPressed)camPos.y += delta * cameraSpeed;
		if(this.keyH.rightPressed)camPos.x += delta * cameraSpeed;
		if(this.keyH.leftPressed)camPos.x -= delta * cameraSpeed;
	}
	
	public void update(double delta) {
		zoomManager();
		cameraManager(delta);
		updateBackGroundImg(delta);
		
		//Update funktion der Siedler und Gebäude durchgehen
		//player.update();
		for(Buildings b : buildingList) {
			//b.update(delta, settlerList, buildingList, itemList);
		}
		for(ASettlers s : settlerList) {
			s.update(delta, settlerList, buildingList, itemList);
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		//Tile size ermitteln
		tileSize = (int)((float)Math.min(this.getWidth(), this.getHeight())/(float)grid_zoom);
		
		//Draw funktion der Gebäude, Siedler und Items. Sowie terrain
		
		ArrayList<Items> itemListSnap;
		ArrayList<Buildings> buildingListSnap;
		ArrayList<ASettlers> settlerListSnap;
		synchronized(itemList) {itemListSnap = new ArrayList<>(itemList);}
		synchronized(buildingList) {buildingListSnap = new ArrayList<>(buildingList);}
		synchronized(settlerList) {settlerListSnap = new ArrayList<>(settlerList);}
		
		//hintergrund zeichnen
		this.drawbackGround(g2, tileSize, camPos, 1);
		for(Items i : itemListSnap) {
			i.draw(g2,tileSize,camPos);
		}
		for(ASettlers s: settlerListSnap) {
			s.draw(g2,tileSize,camPos);
		}
		for(Buildings b : buildingListSnap) {
			
			b.draw(g2,tileSize,camPos);
		}
	}
	private void loadBackgroundImg() {
		try {
			this.backgroundImg1 = ImageIO.read(getClass().getResourceAsStream("/resources/Grass_1.png"));
			this.backgroundImg2 = ImageIO.read(getClass().getResourceAsStream("/resources/Grass_2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private double backgroundTimer = 0.0;
	
	private void updateBackGroundImg(double delta) {
		backgroundTimer += delta;
		if(backgroundTimer > 0.5) {
			backgroundImg = backgroundImg1;
		}else {
			backgroundImg = backgroundImg2;
		}
		backgroundTimer %= 1.0;
	}
	
	private void drawbackGround(Graphics2D g2,int tileSize,Vector2D camPos,double delta) {
		for(int i = -1; i < this.getWidth()/tileSize+2;i++) {
			for(int j = -1; j < this.getHeight()/tileSize+2;j++) {
				int posX = (int)((i-camPos.x%1.0)*(float)tileSize);
				int posY = (int)((j-camPos.y%1.0)*(float)tileSize);
				g2.drawImage(this.backgroundImg, posX, posY, tileSize,tileSize,null);
			}
		}
	}
}
