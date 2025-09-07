package logic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import buildings.Buildings;
import items.IItems;
import items.Items;
import settlers.ASettlers;
import settlers.SettlersAktion;



public class GamePanel extends JPanel implements Runnable{
	//Screen settings
	final int originalTileSize = 16; //16x16 Size
	final int scale = 3;
	
	public final int tileSize = originalTileSize * scale; //48x48
	final int maxScreenCol = 16;
	final int maxScreenRow = 12;
	final int screenWidth = tileSize * maxScreenCol;//768pxl
	final int screenHeight = tileSize * maxScreenRow;//576pxl
	
	int FPS = 60;
	
	//Später hinzufügen für die Kamera Steuerung
	//KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	
	//init Siedler,Häuser,Items etc.
	//Player player = new Player(this,keyH);
	ArrayList<IItems> itemList = new ArrayList<>();
	ArrayList<Buildings> buildingList = new ArrayList<>();
	ArrayList<ASettlers> settlerList = new ArrayList<>();
	
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth,screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		//this.addKeyListener(keyH);
		this.setFocusable(true);
	}

	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	@Override
	public void run() {
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;
		
		//prepare
		for(int i = 0; i < 30;i++) {
			itemList.add(Items.createItem("Axe", new Vector2D(tileSize*i,tileSize*2)));
		}
		/*
		buildingList.add(Buildings.createBuilding("Forester",new Vector2D(0,0)));
		itemList.add(Items.createItem("Axe", new Vector2D(tileSize*2,tileSize*2)));
		settlerList.add(ASettlers.createSettler(new Vector2D(tileSize*3,tileSize*2)));
		settlerList.get(0).giveSettlerTask(new Vector2D(tileSize*10,tileSize*10), SettlersAktion.GO_FREE, null);
		settlerList.get(0).giveSettlerTask(itemList.get(0).getPosition(), SettlersAktion.GO_PICKUP_LEFT, itemList.get(0));
		settlerList.get(0).giveSettlerTask(new Vector2D(tileSize*10,tileSize*10), SettlersAktion.GO_FREE, null);
		*/
		//game loop
		while(gameThread != null) {
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;
			
			if(delta >= 1) {
				//1. UPDATE (calc)
				update(delta);
				//2. DRAW
				repaint(); //ruft paintComponent auf
				delta--;
				drawCount++;
			}
			if(timer >= 1000000000) {
				System.out.println("FPS:"+drawCount+"delta:"+delta);
				drawCount = 0;
				timer = 0;
			}
		}
	}
	
	public void update(double delta) {
		//Update funktion der Siedler und Gebäude durchgehen
		//player.update();
		for(Buildings b : buildingList) {
			//b.update(delta, settlerList, buildingList, itemList);
		}
		for(ASettlers s : settlerList) {
			//s.update(delta, settlerList, buildingList, itemList);
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		//g2.setColor(Color.white);
		//g2.fillRect(0, 0, tileSize, tileSize);
		
		//Draw funktion der Gebäude, Siedler und Items. Sowie terrain
		//player.draw(g2);
		for(IItems i : itemList) {
			i.draw(g2,tileSize);
		}
		for(ASettlers s: settlerList) {
			s.draw(g2,tileSize);
		}
		for(Buildings b : buildingList) {
			
			b.draw(g2,tileSize);
		}
		
		g2.dispose();//nicht wirklich nötig
	}
}
