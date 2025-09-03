package settlers;

public abstract class AJob implements IJob{
	protected String jobName;
	
	public String getJobName() {
		return jobName;
	}
	
	public static IJob creatJob(String job) {
		switch(job) {
			case "Normal":
				return new Normal(job);
			default:
				return new Normal("Normal");
		}
	}
}
