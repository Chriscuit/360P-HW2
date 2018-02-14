/**
 * Created by christophergill on 2/13/18.
 */

public class LinkedList {

    protected Node start;
    protected Node end ;
    public int size ;
    public int maxSize;

    /*  Constructor  */
    public LinkedList(int maxSize) {

        this.start = null;
        this.end = null;
        this.size = 0;
        this.maxSize = maxSize;
    }

    private Boolean isFull() {

        if(this.size == maxSize) {
            return true;
        }
        else return false;
    }
    /*  Function to check if list is empty  */
    public boolean isEmpty()
    {
        return start == null;
    }
    /*  Function to get size of list  */
    public int getSize()
    {
        return size;
    }


    /* TO DO: Implement insertion that orders placement by priority*/

    public int insert(String name, int priority) {

        return 1;
    }
}