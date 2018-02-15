import sun.nio.ch.ThreadPool;

import java.util.concurrent.ThreadPoolExecutor;

public class LinkedListTester implements Runnable{


    static PriorityQueue queue = new PriorityQueue(5);
    Node a;

    LinkedListTester(Node a) {
        this.a = a;

    }

    public static void main(String[] args) throws InterruptedException {

        Node h = new Node("a", 7);
        Node w = new Node("x", 8);
        Node y = new Node("x", 3);
        Node m = new Node("m", 9);
        Node d = new Node("c", 2);
        Node p = new Node("p", 4);
        Node z = new Node("b", 5);

        Thread t1 = new Thread((new LinkedListTester(h)));
        Thread t2 = new Thread(new LinkedListTester(w));
        Thread t3 = new Thread(new LinkedListTester(y));
        Thread t4 = new Thread(new LinkedListTester(m));
        Thread t5 = new Thread(new LinkedListTester(d));
        Thread t6 = new Thread(new LinkedListTester(p));
        Thread t7 = new Thread(new LinkedListTester(z));

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();


        t1.join();
        t2.join();
        t3.join();
        t4.join();
        t5.join();
        t6.join();
        t7.join();
        int x = queue.search("x");
        String name = queue.getFirst();
        String name2 = queue.getFirst();
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
