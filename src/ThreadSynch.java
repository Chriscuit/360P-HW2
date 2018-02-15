/*
 * // sik269 and crg2957
 * 
 */
import java.util.concurrent.Semaphore; // for implementation using Semaphores

public class ThreadSynch {

	Semaphore semaphore;
	Semaphore guard;
	Semaphore guard2;
	int parties;
	int index;
	
	public ThreadSynch(int parties) {

		this.semaphore = new Semaphore(0);
		this.parties = parties;
		this.guard = new Semaphore(1);
		this.guard2 = new Semaphore(1);
		this.index = 0;
	}
	
	public int await() throws InterruptedException {


		guard.acquire();

		index += 1;

		if (index == parties) {
			index = 0;
			semaphore.release(parties);
		}

		guard.release();



		guard2.acquire();

		semaphore.acquire();

		guard2.release();



		return index;
	}
}
