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

    public Boolean isFull() {

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

        // check if full
        if(isFull()) return -1;


        Node currNode = new Node(name, priority);

        // check if empty
        if(isEmpty()) {

            start = currNode;
            end = start;
            return 0;

        } else if(start.getPriority() > currNode.getPriority()) {

            currNode.setNext(start);
            start.setPrev(currNode);
            end = start;
            start = currNode;

        } else if(end.getPriority() > currNode.getPriority()) {

            currNode.setPrev(end);
            end.setNext(currNode);
            end = currNode;

        } else {

            Node viewNode = start;

            while(viewNode.getNext() != null) {

                if(viewNode.getNext().getPriority() < currNode.getPriority()){

                    viewNode = viewNode.getNext();
                } else {


                }
            }
        }


        return 1;
    }
}