public class PriorityQueue {

	LinkedList linkedList;

	public PriorityQueue(int maxSize) {
        // Creates a Priority queue with maximum allowed size as capacity

		this.linkedList = new LinkedList(maxSize);
	}

	public int add(String name, int priority) throws InterruptedException {
        // Adds the name with its priority to this queue.
        // Returns the current position in the list where the name was inserted;
        // otherwise, returns -1 if the name is already present in the list.
        // This method blocks when the list is full.

		int result = linkedList.insert(name, priority);
		return result;
	}

	public int search(String name) {
        // Returns the position of the name in the list;
        // otherwise, returns -1 if the name is not found.
		return linkedList.search(name);
	}

	public String getFirst() throws InterruptedException {
        // Retrieves and removes the name with the highest priority in the list,
        // or blocks the thread if the list is empty.

		while(linkedList.isEmpty()){
			Thread.currentThread().wait();
		}

		return linkedList.first();
	}
}

