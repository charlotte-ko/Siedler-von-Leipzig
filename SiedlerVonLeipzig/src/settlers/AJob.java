package settlers;

public abstract class AJob {
	protected String jobName;
	
	public String getJobName() {
		return jobName;
	}
	
	public static AJob createJob(String job) {
		switch(job) {
			case "Normal":
				return new Normal(job);
			case "Forester":
				return new Gardener(job);
			default:
				return new Normal("Normal");
		}
	}
}
