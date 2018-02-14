/**
 * Created by christophergill on 2/14/18.
 */
public class Node
{
    protected String name;
    protected int priority;
    protected Node next;
//    protected Node prev;


    /*  Constructor  */
    public Node(String name, int priority)
    {
        next = null;
//        prev = null;
        this.name = name;
        this.priority = priority;
    }


    /*  Function to set link to next Node  */
    public void setNext(Node n)
    {
        this.next = n;
    }
    /*  Function to set data to current Node  */
    public void setData(String name, int priority)
    {
        this.name = name;
        this.priority = priority;
    }
    /*  Function to set data to current Node  */
    public void setName(String name)
    {
        this.name = name;
    }
    /*  Function to set data to current Node  */
    public void setPriority(int priority)
    {
        this.priority = priority;
    }
    /*  Function to get link to next node  */
    public Node getNext()
    {
        return this.next;
    }

    /*  Function to get data from current Node  */
    public String getName()
    {
        return this.name;
    }
    public int getPriority()
    {
        return this.priority;
    }
}
