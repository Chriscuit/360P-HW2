import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by christophergill on 2/13/18.
 */

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


    /* TO DO: Implement insertion that orders placement by priority*/

    public int insert(String name, int priority) {

        // check if full
        if(isFull()) return -1;


        // insert concurrency here


        return 1;
    }
}