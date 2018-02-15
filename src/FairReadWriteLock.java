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
	
