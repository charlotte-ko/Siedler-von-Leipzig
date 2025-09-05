package settlers;

import buildings.Buildings;
import buildings.Forester;
import logic.Settlers;
import logic.Vector2D;

public class Gardener extends AJob implements Assignable {
	
	protected Forester workplace;
	protected boolean assigned;
	
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
	
	/**
	 * gibt true zurück, wenn ein Gardener einen Arbeitsplatz hat
	 * gibt ein false zurück, wenn ein Gardener keinen Arbeitsplatz hat
	 */
	public boolean bound() {
		return(!(workplace==null));
	}
	
	public void bind(Forester workspace) {
		this.workplace = workspace;
		this.assigned = true;
	}

	@Override
	public void bind(ISettlers worker) {
		// TODO Auto-generated method stub
		
	}
	
	//bound
	//employee initialisieren
}
