/*
 * // sik269 and crg2957
 * 
 */

import java.util.concurrent.Semaphore;

public class MonitorThreadSynch {

	int parties;
	int index;
	
	public MonitorThreadSynch(int parties) {

		this.parties = parties;
		this.index = 0;
	}
	
	public synchronized int await() throws InterruptedException {

		index += 1;

		if (index == parties) {
			index = 0;
			notifyAll();
		}

		else wait();

		return index;
	}
}
