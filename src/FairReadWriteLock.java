import java.util.ArrayList;

public class FairReadWriteLock {

	int readers = 0;
	int writers = 0;
	int writeRequests = 0;

	ArrayList<Boolean> queue = new ArrayList<>();
    ArrayList<Long> tqueue = new ArrayList<>();
    int count = 0;

    public synchronized void beginRead() throws InterruptedException {

        queue.add(true);
        tqueue.add(Thread.currentThread().getId());
        while(writers > 0 || writeRequests > 0 || queue.get(0) == false || Thread.currentThread().getId() != tqueue.get(count)) {
			wait();
        }
        count++;
        queue.remove(0);
        readers++;
        notifyAll();
	}
	
	public synchronized void endRead() throws InterruptedException {

		readers--;
		notifyAll();
	}
	
	public synchronized void beginWrite() throws InterruptedException {

        queue.add(false);
        tqueue.add(Thread.currentThread().getId());
        while(writers > 0 || readers > 0 || queue.get(0) == true || Thread.currentThread().getId() != tqueue.get(count)) {
			wait();
        }
        count++;
        queue.remove(0);
		writers++;
        notifyAll();
	}

	public synchronized void endWrite() throws InterruptedException {

        writers--;
        notifyAll();
	}
}
	
