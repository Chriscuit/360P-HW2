import java.util.ArrayList;
import java.util.concurrent.locks.Condition;

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

	int readers = 0;
	int writers = 0;
	int writeRequests = 0;

	public synchronized void beginRead() throws InterruptedException {

		while(writers > 0 || writeRequests > 0) {
			wait();
		}
		readers++;
		notifyAll();
	}
	
	public synchronized void endRead() throws InterruptedException {

		readers--;
		notifyAll();
	}
	
	public synchronized void beginWrite() throws InterruptedException {

		writeRequests++;
		while(writers > 0 || readers > 0) {
			wait();
		}
		writers++;
		writeRequests--;
		notifyAll();
	}

	public synchronized void endWrite() throws InterruptedException {

		writers--;
		notifyAll();
	}
}
	
