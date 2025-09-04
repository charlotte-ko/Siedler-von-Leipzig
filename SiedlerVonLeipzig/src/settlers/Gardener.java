package settlers;

import buildings.Buildings;
import logic.Settlers;
import logic.Vector2D;

public class Gardener extends AJob {

	public Gardener(String job) {
		this.jobName = job;
	}
	

	public void work(Buildings workspace, ISettlers worker, Vector2D plantpos) {
		worker.giveSettlerTask(workspace.getPosition(), SettlersAktion.GO_PICKUP_RIGHT, workspace.getOutput_Inventory().get(workspace.getOutput_Inventory().size() - 1));
		
		worker.giveSettlerTask(plantpos, SettlersAktion.GO_BRING_RIGHT, null);
		this.changePositionList(index);
		
	}
}
