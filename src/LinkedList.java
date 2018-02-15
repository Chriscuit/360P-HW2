import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class LinkedList {

    protected Node head;
    public AtomicInteger size = new AtomicInteger();
    public AtomicInteger maxSize = new AtomicInteger();
    private ReentrantLock csLock = new ReentrantLock();
    private Condition isFull = csLock.newCondition();
    private Condition isEmpty = csLock.newCondition();

    /*  Constructor  */
    public LinkedList(int maxSize) {

        this.head = null;
        this.size.set(0);
        this.maxSize.set(maxSize);
    }

    public Boolean isFull() {
        csLock.lock();
        if(this.size == maxSize) {
            csLock.unlock();
            return true;
        }
        else {
            csLock.unlock();
            return false;
        }
    }
    /*  Function to check if list is empty  */
    public boolean isEmpty() {
        csLock.lock();
        if (head == null)
        {
            csLock.unlock();
            return true;
        }
        csLock.unlock();
        return false;
    }


    public int insert(String name, int priority) throws InterruptedException {
        // check if full
        if(isFull()){
            csLock.lock();
            isFull.await();
            csLock.unlock();
        }
        Node newNode = new Node(name, priority);
        if(isEmpty() || priority > head.getPriority())
        {
            newNode.setNext(head);
            head = newNode;
            size.getAndIncrement();
            csLock.lock();
            isEmpty.signal();
            csLock.unlock();
            return 0;
        }
        else if (head.getName().equals(name)){
            return -1;
        }
        else if(size.get() == 1) {
            head.lock();
            if(head.getName().equals(name)) {
                head.unlock();
                return -1;
            }
            head.setNext(newNode);
            newNode.lock();
            newNode.setNext(null);
            head.unlock();
            newNode.unlock();
            size.getAndIncrement();
            return 1;
        }
        else {
            head.lock();
            AtomicInteger positionCount = new AtomicInteger();
            positionCount.set(1);
            Node prev = head;
            Node curr = head.getNext();
            curr.lock();
            while(curr.getPriority() > priority && curr.hasNext()){
                prev.unlock();
                prev = curr;
                curr = curr.getNext();
                curr.lock();
                positionCount.getAndIncrement();
            }
            if(curr.getName().equals(name) || prev.getName().equals(name)) {
                return -1;
            }
            // if putting in between
            if(curr.hasNext() || curr.getPriority() < priority) {
                newNode.setNext(curr);
                prev.setNext(newNode);
                curr.unlock();
                prev.unlock();
                size.getAndIncrement();
                return positionCount.get();
            }
            // if adding to end
            else {
                curr.setNext(newNode);
                newNode.setNext(null);
                curr.unlock();
                prev.unlock();
                return size.getAndIncrement();
            }
        }
    }

    public int search(String name) {
        if(isEmpty()) {
            return -1;
        }
        else if(size.get() == 1) {
            if(head.getName().equals(name))
                return 0;
            return -1;
        }
        else if(head.getName().equals(name)) {
            return 0;
        }
        else {
            head.lock();
            AtomicInteger positionCount = new AtomicInteger();
            positionCount.set(1);
            Node prev = head;
            Node curr = head.getNext();
            curr.lock();
            while(!curr.getName().equals(name) && curr.hasNext()){
                prev.unlock();
                prev = curr;
                curr = curr.getNext();
                curr.lock();
                positionCount.getAndIncrement();
            }
            if(!curr.getName().equals(name)) return -1;
            if (curr.hasNext()) {
                curr.unlock();
                return positionCount.get();
            }
            else {
                curr.unlock();
                return size.get() - 1;
            }
        }
    }

    public String first() throws InterruptedException {
        if(isEmpty()) {
            isEmpty.await();
        }
        else if(size.get() == 1) {
            head.lock();
            String result = head.getName();
            csLock.lock();
            isFull.signal();
            csLock.unlock();
            size.getAndDecrement();
            head = null;
            return result;
        }
        head.lock();
        head.getNext().lock();
        String result = head.getName();
        head = head.getNext();
        csLock.lock();
        isFull.signal();
        csLock.unlock();
        head.unlock();
        size.getAndDecrement();
        return result;
    }
}