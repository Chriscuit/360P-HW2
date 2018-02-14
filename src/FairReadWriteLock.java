import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/*
	I'm pretty sure this is very wrong but rough idea is to keep track of
	both readers and writers in two arrays (keeping track of thread order with
	numThread variable)

	In beginRead, will add reader to readerlist, check if there are any writers, and if there
	are will check to see if the first/earliest writer should go before the reader that was just added.

	In endRead, will remove the reader from the readerlist (though I'm not sure I'm removing them right),
	and notify all threads that no readers exist if readerList is empty.

	If this idea actually makes sense then can code the writer stuff but I don't want to be completely off base
	here.

	 TODO: Questions to ask
	 1) Is this even a viable strategy lol
	 2) Are the conditions being used correctly?
	 3) Are readers being removed in the right order (currently just removing the first one but I don't think that's
	 	how it'll work concurrently, but logically makes sense?)
	 4) SignalAll or just signal on line 61? Pretty sure it's signalAll because you don't know what thread is next in
	 	the queue.
 */

public class FairReadWriteLock {
	private final ReentrantLock lock = new ReentrantLock();
	private final Condition writerExists = lock.newCondition();
	private final Condition readerExists = lock.newCondition();
	private ArrayList<Integer> writerList = new ArrayList<Integer>();		// keeps track of # of writers
	private ArrayList<Integer> readerList = new ArrayList<Integer>();		//  keeps track of # of readers
	private int numThread = 0;												// keeps track of numThreads in general
                        
	public synchronized void beginRead() {
		lock.lock();

		try {
			numThread++;
			readerList.add(numThread);
			while(!writerList.isEmpty() && writerList.get(0) < readerList.get(readerList.size() - 1)) {
				writerExists.await();
			}
			if(!readerList.isEmpty()) {
				readerExists.await();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public synchronized void endRead() {
		lock.lock();
		try {
			readerList.remove(0);
			numThread--;

			if(readerList.isEmpty()) {
				readerExists.signalAll();		// check this line?
			}
		} finally {
			lock.unlock();
		}
	}
	
	public synchronized void beginWrite() {
	}
	public synchronized void endWrite() {
	}
}
	
