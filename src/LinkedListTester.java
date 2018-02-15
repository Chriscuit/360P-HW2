public class LinkedListTester implements Runnable{


    static PriorityQueue queue = new PriorityQueue(5);
    Node a;

    LinkedListTester(Node a) {
        this.a = a;

    }

    public static void main(String[] args) {

        Node h = new Node("a", 4);
        Node w = new Node("y", 7);
        Node y = new Node("x", 6);
        Node m = new Node("b", 2);
        Node d = new Node("c", 3);

        new Thread((new LinkedListTester(h))).start();
        new Thread(new LinkedListTester(w)).start();
        new Thread(new LinkedListTester(y)).start();
        new Thread(new LinkedListTester(m)).start();
        new Thread(new LinkedListTester(d)).start();
    }

    @Override
    public void run() {
        try {
            queue.add(a.getName(), a.getPriority());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
