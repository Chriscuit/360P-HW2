import com.sun.org.apache.xpath.internal.operations.Bool;

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
    /*  Function to insert an element at begining  */
    public void insertAtStart(int val)
    {
        if(isFull()) return;
        Node nptr = new Node(val, null);
        size++ ;
        if(start == null)
        {
            start = nptr;
            end = start;
        }
        else
        {
            nptr.setLink(start);
            start = nptr;
        }
    }
    /*  Function to insert an element at end  */
    public void insertAtEnd(int val)
    {
        if(isFull()) return;
        Node nptr = new Node(val,null);
        size++ ;
        if(start == null)
        {
            start = nptr;
            end = start;
        }
        else
        {
            end.setLink(nptr);
            end = nptr;
        }
    }
    /*  Function to insert an element at position  */
    public void insertAtPos(int val , int pos)
    {
        if(isFull()) return;
        Node nptr = new Node(val, null);
        Node ptr = start;
        pos = pos - 1 ;
        for (int i = 1; i < size; i++)
        {
            if (i == pos)
            {
                Node tmp = ptr.getLink() ;
                ptr.setLink(nptr);
                nptr.setLink(tmp);
                break;
            }
            ptr = ptr.getLink();
        }
        size++ ;
    }
    /*  Function to delete an element at position  */
    public void deleteAtPos(int pos)
    {
        if (pos == 1)
        {
            start = start.getLink();
            size--;
            return ;
        }
        if (pos == size)
        {
            Node s = start;
            Node t = start;
            while (s != end)
            {
                t = s;
                s = s.getLink();
            }
            end = t;
            end.setLink(null);
            size --;
            return;
        }
        Node ptr = start;
        pos = pos - 1 ;
        for (int i = 1; i < size - 1; i++)
        {
            if (i == pos)
            {
                Node tmp = ptr.getLink();
                tmp = tmp.getLink();
                ptr.setLink(tmp);
                break;
            }
            ptr = ptr.getLink();
        }
        size-- ;
    }
    /*  Function to display elements  */
    public void display()
    {
        System.out.print("\nSingly Linked List = ");
        if (size == 0)
        {
            System.out.print("empty\n");
            return;
        }
        if (start.getLink() == null)
        {
            System.out.println(start.getData() );
            return;
        }
        Node ptr = start;
        System.out.print(start.getData()+ "->");
        ptr = start.getLink();
        while (ptr.getLink() != null)
        {
            System.out.print(ptr.getData()+ "->");
            ptr = ptr.getLink();
        }
        System.out.print(ptr.getData()+ "\n");
    }

    public class Node
    {
        protected int data;
        protected Node link;

        /*  Constructor  */
        public Node()
        {
            link = null;
            data = 0;
        }
        /*  Constructor  */
        public Node(int d,Node n)
        {
            data = d;
            link = n;
        }
        /*  Function to set link to next Node  */
        public void setLink(Node n)
        {
            link = n;
        }
        /*  Function to set data to current Node  */
        public void setData(int d)
        {
            data = d;
        }
        /*  Function to get link to next node  */
        public Node getLink()
        {
            return link;
        }
        /*  Function to get data from current Node  */
        public int getData()
        {
            return data;
        }
    }
}