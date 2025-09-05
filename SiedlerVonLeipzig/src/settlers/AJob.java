package settlers;

import java.util.ArrayList;

import items.IItems;

public abstract class AJob implements Jobbable{
	
	protected String jobName;
	
	public String getJobName() {
		return jobName;
	}
	
	public static Jobbable createJob(String job) {
		switch(job) {
			case "Normal":
				return new Normal(job);
			case "Forester":
				return new Gardener(job);
			default:
				return new Normal("Normal");
		}
	}
	
	public ArrayList<IItems> returnList(ArrayList<IItems> inventory, int index) {
		inventory.remove(index);
		return inventory;
	}
}
