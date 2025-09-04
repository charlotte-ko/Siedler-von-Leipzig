package settlers;

import buildings.Buildings;
import logic.Settlers;
import logic.Vector2D;

public class Gardener extends AJob {

	public Gardener(String job) {
		this.jobName = job;
	}
	
	/**
	 * Gardener hat die AUfgabe Trees zu pflanzen, er eght zuerst zu einer random Position und kommt dann zurück (kann das GO_FREE??)
	 */
	public void work(Buildings workspace, ISettlers worker, Vector2D plantpos) {
		worker.giveSettlerTask(workspace.getPosition(), SettlersAktion.GO_PICKUP_RIGHT, workspace.getOutput_Inventory().get(workspace.getOutput_Inventory().size() - 1));
		worker.giveSettlerTask(plantpos, SettlersAktion.GO_BRING_RIGHT, null);
		worker.giveSettlerTask(workspace.getPosition(), SettlersAktion.GO_FREE, null);
	}
}
