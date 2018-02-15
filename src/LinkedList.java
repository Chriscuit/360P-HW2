import java.util.concurrent.atomic.AtomicInteger;

public class LinkedList {

    protected Node head;
    public AtomicInteger size;
    public AtomicInteger maxSize;

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
    public boolean isEmpty()
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
        if(isEmpty())
        {
            this.head = newNode;
            return 0;
        }
        else
        {
            head.lock();
            AtomicInteger positionCount = new AtomicInteger();
            positionCount.set(0);
            Node prev = head;
            Node curr = head.getNext();
            curr.lock();
            while(curr.getPriority() > priority){
                prev.unlock();
                prev = curr;
                curr = curr.getNext();
                curr.lock();
                positionCount.getAndIncrement();
            }
            if(curr.getPriority() == priority) {
                return -1;
            }
            newNode.setNext(curr);
            prev.setNext(newNode);
            curr.unlock();
            prev.unlock();

            return positionCount.get();
        }
    }
}