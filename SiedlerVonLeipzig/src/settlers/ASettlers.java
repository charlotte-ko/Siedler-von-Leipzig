package settlers;

import logic.Vector2D;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

import buildings.Buildings;
import items.Items;


public abstract class ASettlers implements java.io.Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	private Vector2D position;
	private float speed;
	protected boolean working;
	protected boolean employed;
	private Vector2D danceStep = null;
	
	SettlersJobs jobName;
	
	private Items rightHand;
	private Items leftHand;
	
	//Aufgabenspeicher
	private Deque<Vector2D> taskWay = new ArrayDeque<>();
	private Deque<SettlersAktion> task = new ArrayDeque<>();
	protected Deque<Items> taskTarget = new ArrayDeque<>();
	
	protected transient BufferedImage img;
	protected boolean isGoingUp;
	
	
	public void draw(Graphics2D g2, int tileSize,Vector2D camPos) {
		int posX = (int)((position.x-camPos.x)*tileSize);
		int posY = (int)((position.y-camPos.y)*tileSize);
		
		g2.drawImage(img, posX, posY, tileSize,tileSize,null);
	}
	
	/**
	 * @brief Factory Methode zum Erstellen von Siedlern, welche direkt in die globale settlerList gespeichert werden.
	 * @param settlerList
	 * @param position
	 * @return erstelltes Objekt wird zurückgegeben (steht aber auch in der liste)
	 * @note Es wird nur ein Settler erstellt. Job muss direkt oder indirekt vergeben werden.
	 */
	public static ASettlers createSettler(Vector2D position) {
		
		ASettlers newSettler = new Settler(position);
		newSettler.setItem(true, null);
		newSettler.setItem(false, null);
		return newSettler;
	}
	
	/**
	 * @brief gibt Siedler eine Aufgabe die in der Deque gespeichert werden
	 * @param punkt		Wo hingegangen werden soll
	 * @param aufgabe	Was gemacht werden soll
	 * @param target	Welches Item beinflusst wird
	 */
	public void giveSettlerTask(Vector2D punkt, SettlersAktion aufgabe, Items target) {
		
		this.working = true;
		this.taskWay.addLast(new Vector2D(punkt));
		this.task.addLast(aufgabe);
		
		if(target == null) {
			
			this.taskTarget.addLast(Items.createItem("Nothing", new Vector2D()));
			
		}else{
			
			target.setTargeted(true);
			this.taskTarget.addLast(target);
		}
	}
	
	/**
	 * @brief löscht Inhalt aus allen Tasklisten eines Siedlers 
	 */
	public void deleteAllTasks() {
		
		this.task.clear();
		this.taskTarget.clear();
		this.taskWay.clear();
		this.working = false;
		
	}
	
	/**
	 * @brief lässt Siedler umherlaufen, wenn sie nichts zu tun haben
	 * @param delta
	 */
	private void dance(double delta) {
		
		if(danceStep == null || danceStep.getDistance(position) < 0.5) {
			
			danceStep = new Vector2D(this.getPosition());
			danceStep = danceStep.genPosInCircle(2.0f);
		}
		
		float bufferSpeed = this.speed;
		this.speed = 0.5f;
		walk(delta,danceStep);
		this.speed = bufferSpeed;
	}
	
	/**
	 * @brief prüft ob Siedler arbeiten, und lässt sie den ersten task in ihrer taskliste ausführen
	 * @param delta
	 * @param settlerList
	 * @param buildingList
	 * @param itemList
	 */
	public void update(double delta, ArrayList<ASettlers> settlerList, ArrayList<Buildings> buildingList, ArrayList<Items> itemList) {
		
		update_img(delta);
		
		if(this.task.isEmpty()) {
			
			this.working = false;
			
			if(!this.employed) {
				dance(delta);
			}
			return;
		}
		
		this.working = true;
		Vector2D targetPosition = move(delta, buildingList);
		
		if(targetPosition == null) return;
		
		Items target = this.taskTarget.pollFirst();
		
		switch(this.task.pollFirst()) {
			case GO_PICKUP_LEFT:
				this.setItem(true, target);
				target.setTargeted(false);
				if(target.isInInventoryOutput())takeFromBuilding(target.getPosition(),buildingList,target);
				target.setInInventoryOutput(false);
				break;
			case GO_PICKUP_RIGHT:
				this.setItem(false, target);
				target.setTargeted(false);
				if(target.isInInventoryOutput())takeFromBuilding(target.getPosition(),buildingList,target);
				target.setInInventoryOutput(false);
				break;
			case GO_BRING_LEFT:
				target.setHeld(false);
				target.setTargeted(false);
				giveBuilding(targetPosition,target,buildingList);
				this.setItem(true, null);
				break;
			case GO_BRING_RIGHT:
				target.setHeld(false);
				target.setTargeted(false);
				giveBuilding(targetPosition,target,buildingList);
				this.setItem(false, null);
				break;
			case GO_FREE://tut nix
				break;
			default:
				break;
		}
	}
	
	/**
	 * @brief passt das Inventar eines Gebäudes an, wenn etwas entnommen wird
	 * @param position
	 * @param buildingList
	 * @param target
	 */
	private void takeFromBuilding(Vector2D position,ArrayList<Buildings> buildingList, Items target) {
		
		Buildings targetBuilding = Buildings.findBuilding(target.getPosition(), buildingList);
		
		if(targetBuilding == null) return;
		
		ArrayList<Items> inventar = targetBuilding.getOutput_Inventory();
		inventar.remove(target);
		targetBuilding.setOutput_Inventory(inventar);
	}
	
	/**
	 * @brief passt das Inventar eines Gebäudes an, wenn etwas hinzugefügt wird
	 * @param position
	 * @param target
	 * @param buildingList
	 */
	private void giveBuilding(Vector2D position, Items target, ArrayList<Buildings> buildingList) {
		
		Buildings targetBuilding = Buildings.findBuilding(position, buildingList);
		
		if(targetBuilding == null) return;
		
		ArrayList<Items> inventar = targetBuilding.getInput_Inventory();
		target.setInInventoryInput(true);
		inventar.add(target);
		targetBuilding.setInput_Inventory(inventar);
	}
	
	/**
	 * @brief berechnet und bewegt die Siedler zu seinem Ziel
	 * @param delta
	 * @param buildingList
	 * @return eine Positon, die das Ziel des Siedlers darstellt
	 */
	private Vector2D move(double delta,ArrayList<Buildings> buildingList) {
		
		Vector2D targetPosition = this.taskWay.peekFirst();
		
		if(this.position.getDistance(targetPosition) < 0.1f) {
			
			return this.taskWay.pollFirst();
		}
		
		walk((float)delta,targetPosition);
		
		//items mit bewegen
		if(this.leftHand != null)this.leftHand.setPosition(this.getPosition());
		if(this.rightHand != null)this.rightHand.setPosition(this.getPosition());
		
		return null;
	}
	
	/**
	 * @brief geht in die gewünschte richtung
	 * @param delta
	 * @param target wo es hingehen soll
	 */
	private void walk(double delta,Vector2D target) {
		
		Vector2D direction = this.position.getTwoPointVector(target);
		direction = direction.normalize();
		direction = direction.scale(this.getSpeed()*(float)delta);
		
		if(direction.y < 0.0) {
			
			this.isGoingUp = true;
			
		}else {
			
			this.isGoingUp = false;
		}
		
		this.position = this.position.add(direction);
	}
	
	
	protected abstract void update_img(double delta);
	
	
	
	public SettlersJobs getJobName() {
		return this.jobName;
	}
	
	public void setJobName(SettlersJobs newJob) {
		this.jobName = newJob;
	}
	
	
	public Items getItem(boolean leftHand) {
		if(leftHand) {
			return this.leftHand;
		}else {
			return this.rightHand;
		}
	}
	
	
	public void setItem(boolean leftHand,Items item) {
		
		if(item == null) item = Items.createItem("Nothing", this.getPosition());
		
		
		item.setHeld(true);
		
		if(leftHand) {
			this.leftHand = item;
		}else {
			this.rightHand = item;
		}
	}
	
	
	public void setEmployed(boolean isEmployed) {
		this.employed = isEmployed;
	}
	
	public boolean isEmployed() {
		return this.employed;
	}
	
	public Vector2D getPosition() {
		return this.position;
	}
	public void setPosition(Vector2D pos) {
		this.position = pos;
	}
	public float getSpeed() {
		return this.speed;
	}
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	public boolean isWorking() {
		return this.working;
	}
	protected void setIsWorking(boolean working) {
		this.working = working;
	}
}
