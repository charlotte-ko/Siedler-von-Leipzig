package settlers;

import buildings.Buildings;
import logic.Vector2D;

//früher IJob
public interface Jobbable {

	public void work(Buildings workspace, ISettlers worker, Vector2D pos);

	public String getJobName();
}
