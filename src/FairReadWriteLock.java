// sik269 and crg2957

import java.util.ArrayList;

public class FairReadWriteLock {

	int readers = 0;
	int writers = 0;

	ArrayList<Boolean> queue = new ArrayList<>();

    public synchronized void beginRead() throws InterruptedException {

        queue.add(true);
        while(writers > 0 || queue.get(0) == false) {
			wait();
        }
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
        while(writers > 0 || readers > 0 || queue.get(0) == true) {
			wait();
        }
        queue.remove(0);
		writers++;
        notifyAll();
	}

	public synchronized void endWrite() throws InterruptedException {

        writers--;
        notifyAll();
	}
}
	
