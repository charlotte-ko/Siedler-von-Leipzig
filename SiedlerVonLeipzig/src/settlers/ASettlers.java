package settlers;

import logic.Vector2D;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

import buildings.IBuildings;
import items.IItems;
import items.Items;

public abstract class ASettlers implements ISettlers {
	private Vector2D position;
	private float speed;
	private boolean working;
	private IItems rightHand;
	private IItems leftHand;
	//Aufgabenspeicher (deque besser als stack)
	private Deque<Vector2D> taskWay = new ArrayDeque<>(); //Weg zur Aufgabe
	private Deque<SettlersAktion> task = new ArrayDeque<>();	//aufgabe
	
	
	private Deque<IItems> taskTarget = new ArrayDeque<>();
	
	protected IJob job;
	
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
	
	
	/**
	 * @brief Factory Methode zum Erstellen von Siedlern, welche direkt in die globale settlerList gespeichert werden.
	 * @param settlerList
	 * @param position
	 * @return erstelltes Objekt wird zurückgegeben (steht aber auch in der liste)
	 * @note Es wird nur ein Settler erstellt. Job muss direkt oder indirekt vergeben werden.
	 */
	public static ISettlers createSettlerInList(ArrayList<ISettlers> settlerList, Vector2D position) {
		ISettlers newSettler = new Settler(position);
		settlerList.add(newSettler);
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
	public IItems getItemHand(boolean leftHand) {
		if(leftHand) {
			return this.leftHand;
		}else {
			return this.rightHand;
		}
	}
	
	public String getJob() {
		return this.job.getJobName();
	}
	
	public void setJob(String jobName) {
		this.job = AJob.creatJob(jobName);
	}
	

	public void setItemHand(IItems item,boolean leftHand,ArrayList<ISettlers> settlersList) {
		if(leftHand) {
			this.leftHand = item;
			return;
		}
		//Job abfrage und falls nötig neu vergeben
	}
	
	public IItems takeItemHand(boolean leftHand,ArrayList<ISettlers> settlersList) {
		if(leftHand) {
			IItems ret = this.leftHand;
			this.leftHand = null;
			return ret;
		}
		
		IItems ret = this.rightHand;
		this.rightHand = null;
		//Job wegnehmen
		return ret;
	}
	

	public void giveSettlerTask(Vector2D punkt, SettlersAktion aufgabe, IItems target) {
		this.taskWay.addLast(punkt);
		this.task.addLast(aufgabe);
		if(target == null) {
			this.taskTarget.addLast(Items.createItem("Axe", new Vector2D()));
		}else{
			this.taskTarget.addLast(target);
		}
	}
	
	public IItems closestItem(Vector2D current_pos, ArrayList<IItems> ItemList, String target) {
		
		float min_distance = Float.POSITIVE_INFINITY;
		IItems finalItem = null;
		//darf nicht gehalten werden
		for(IItems item : ItemList) {
			if(item.getName().equals(target)) {
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
			case GO_WORK:
				this.job.work(this); //ruft job auf
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
	/**
	 * @brief gibt Job zurück welches zum Item passt
	 * @param tem (undertail anspielung)
	 * @return
	 * 
	 * AUF JOBS ANPASSEN
	 */
	private static String getJobFromItem(IItems tem) {
		switch(tem.getName()) {
			case "Hammer":
				return "Builder";
			case "Saw":
				return "Sawyer";
			case "Axe":
				return "Woodcutter";
			default:
				return "Normal";
		}
	}
}
