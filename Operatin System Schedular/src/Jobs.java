public class Jobs implements Comparable {

	public String job;
	public int time;
	public String jobCopy;
	public int timeCopy1;
	public int timeCopy2;
	
	
	public Jobs(String job, int time) {
		this.job = job;
		this.time = time;
		this.jobCopy = job;
		this.timeCopy1 = time;
		this.timeCopy2 = time;
	}
	
	public Jobs(Jobs job) {
		this.job = job.getJob();
		this.time = job.getTime();
		this.jobCopy = job.getJob();
		this.timeCopy1 = job.getTime();
		this.timeCopy2 = job.getTime();
	}
	
	public void rrb3Decrement(int decrement) {
		timeCopy1 = timeCopy1 - decrement;
	}
	
	public void rrb4Decrement(int decrement) {
		timeCopy2 = timeCopy2 - decrement;
	}
	
	public String getJobCopy() {
		return jobCopy;
	}

	public int getTimeCopy2() {
		return timeCopy2;
	}
	
	public int getTimeCopy1() {
		return timeCopy1;
	}

	public String getJob() {
		return job;
	}
	
	public int getTime() {
		return time;
	}
	
	public void setJob(String job) {
		this.job = job;
	}
	
	public void setTime(int time) {
		this.time = time;
	}

	@Override
	public int compareTo(Object job) {
		int compareAge = ((Jobs) job).getTime();
		return this.time-compareAge;
	}

}
