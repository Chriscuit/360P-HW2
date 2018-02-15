import java.util.concurrent.atomic.AtomicInteger;

public class LinkedList {

    protected Node head;
    public AtomicInteger size = new AtomicInteger();
    public AtomicInteger maxSize = new AtomicInteger();

    /*  Constructor  */
    public LinkedList(int maxSize) {

        this.head = null;
        this.size.set(0);
        this.maxSize.set(maxSize);
    }

    public Boolean isFull() {

        if(this.size == maxSize) {
            return true;
        }
        else return false;
    }
    /*  Function to check if list is empty  */
    public synchronized boolean isEmpty()
    {
        return head == null;
    }
    /*  Function to get size of list  */
    public AtomicInteger getSize()
    {
        return size;
    }


    public int insert(String name, int priority) throws InterruptedException {
        // check if full
        if(isFull()) wait();
        Node newNode = new Node(name, priority);
        if(isEmpty() || priority > head.getPriority())
        {
            newNode.setNext(head);
            head = newNode;
            size.getAndIncrement();
            return 0;
        }
        else if (head.getName().equals(name)){
            return -1;
        }
        else if(size.get() == 1) {
            if(head.getName().equals(name)) return -1;
            head.lock();
            head.setNext(newNode);
            head.unlock();
            newNode.setNext(null);
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
            if(curr.hasNext()) {
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
}