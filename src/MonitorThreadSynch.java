/*
 * EID's of group members
 * 
 */

import java.util.concurrent.Semaphore;

public class MonitorThreadSynch {

	Semaphore semaphore;
	int parties;
	
	public MonitorThreadSynch(int parties) {

		this.semaphore = new Semaphore(0);
		this.parties = parties;
	}
	
	public int await() throws InterruptedException {

		synchronized(this) {


		}

		return 1;
	}
}
