package settlers;

import logic.Vector2D;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

import buildings.IBuildings;
import items.IItems;
import items.Items;



/**
 * setJob methode ist nötig !!!
 * 
 * GO_WAIT implimentieren !!!
 * 
 * take item hadn updaten etc...
 * 
 * ISettlers raus nehmen
 */

public abstract class ASettlers{
	private Vector2D position;
	private float speed;
	protected boolean working;
	protected boolean employed;
	
	SettlersJobs jobName;
	
	private IItems rightHand;
	private IItems leftHand;
	
	//Aufgabenspeicher (deque besser als stack)
	private Deque<Vector2D> taskWay = new ArrayDeque<>(); //Weg zur Aufgabe
	private Deque<SettlersAktion> task = new ArrayDeque<>();	//aufgabe
	protected Deque<IItems> taskTarget = new ArrayDeque<>();
	
	//nötigkeit prüfen!!!
	public Deque<Vector2D> getTaskWay() {
		return taskWay;
	}
	public void setTaskWay(Deque<Vector2D> taskWay) {
		this.taskWay = taskWay;
	}
	public Deque<SettlersAktion> getTask() {
		return task;
	}
	public void setTask(Deque<SettlersAktion> task) {
		this.task = task;
	}
	public Deque<IItems> getTaskTarget() {
		return taskTarget;
	}
	public void setTaskTarget(Deque<IItems> taskTarget) {
		this.taskTarget = taskTarget;
	}

	public SettlersJobs getJobName() {
		return this.jobName;
	}
	
	public void setJobName(SettlersJobs newJob) {
		this.jobName = newJob;
	}
	
	public IItems getItem(boolean leftHand) {
		if(leftHand) {
			return this.leftHand;
		}else {
			return this.rightHand;
		}
	}
	public void setItem(boolean leftHand,IItems tem) {
		if(leftHand) {
			this.leftHand = tem;
		}else {
			this.rightHand = tem;
		}
	}
	
	public void setEmployed(boolean isEmployed) {
		this.employed = isEmployed;
	}
	
	public boolean getEmployed() {
		return this.employed;
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
		return newSettler;
	}
	
		/*
		//items übertragen
		s.setItem(true, this.getItem(true));
		s.setItem(false, this.getItem(false));
		//Aufgaben übertragen
		while(!this.task.isEmpty()) {
			s.giveSettlerTask(this.taskWay.pollFirst(), this.task.pollFirst(),this.taskTarget.pollFirst());
		}
		*/
	
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
	
	public void giveSettlerTask(Vector2D punkt, SettlersAktion aufgabe, IItems target) {
		this.taskWay.addLast(punkt);
		this.task.addLast(aufgabe);
		if(target == null) {
			//dummy item wechseln
			this.taskTarget.addLast(Items.createItem("Axe", new Vector2D()));
		}else{
			this.taskTarget.addLast(target);
		}
	}
	
	public static IItems closestItem(Vector2D current_pos, ArrayList<IItems> ItemList, String target) {
		
		float min_distance = Float.POSITIVE_INFINITY;
		IItems finalItem = null;
		//darf nicht gehalten werden
		for(IItems item : ItemList) {
			if(!item.getName().equals(target)) {
				continue;
			}
			float distance = current_pos.getDistance(item.getPosition());
			if(distance < min_distance) {
				min_distance = distance;
				finalItem = item;
			}
		}return finalItem;
	}
	
	public void update(double delta, ArrayList<IItems> itemList, ArrayList<IBuildings> buildingList, ArrayList<ISettlers> settlerList) {
		if(this.task.isEmpty()) {
			this.working = false;
			return;
		}
		//bewege dich methode
		if(this.position.getDistance(this.taskWay.peekFirst()) > 1.0f) {
			walk((float)delta,this.taskWay.peekFirst());
			//items mit bewegen
			if(this.leftHand != null)
				this.leftHand.setPosition(this.getPosition());
			if(this.rightHand != null)
				this.rightHand.setPosition(this.getPosition());
			return;	
		}
		this.taskWay.pollFirst();
		IItems target = this.taskTarget.pollFirst();
		switch(this.task.pollFirst()) {
			case GO_PICKUP_LEFT:
				this.setItemHand(target, true, settlerList);
				target.setHeld(true);
				break;
			case GO_PICKUP_RIGHT:
				this.setItemHand(target, false, settlerList);
				target.setHeld(true);
				break;
			case GO_BRING_LEFT:
				this.takeItemHand(true, settlerList);
				target.setHeld(false);
				break;
			case GO_BRING_RIGHT:
				this.takeItemHand(false, settlerList);
				target.setHeld(false);
				break;
			case GO_FREE://tut nix
				break;
			default:
				break;
		}
	}
	/**
	 * @brief geht in die gewünschte richtung
	 * @param delta
	 * @param target wo es hingehen soll
	 */
	private void walk(float delta,Vector2D target) {
		Vector2D direction = this.position.getTwoPointVector(target);
		direction = direction.normalize();
		direction = direction.scale(this.getSpeed()*delta);
		this.position = this.position.add(direction);
	}
}
