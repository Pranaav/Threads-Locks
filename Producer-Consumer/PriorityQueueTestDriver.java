// Use this driver for the testing the correctness of your priority queue implementation
// You can change the add, remove sequence to test for various scenarios.
public class PriorityQueueTestDriver {
    public static void main(String[] args) {
	PriorityQueue<String> pq = new PriorityQueue<String>(5);
	Node<String> nod = new Node<String>(4, "A");
	pq.enqueue(nod);
	nod = new Node<String>(10, "B");
	pq.enqueue(nod);
	nod = new Node<String>(3, "C");
	pq.enqueue(nod);
	nod = new Node<String>(5, "E");
	pq.enqueue(nod);
	nod = new Node<String>(2, "F");
	pq.enqueue(nod);
	
	//pq.display();
	int size = pq.size();
	for (int i=0; i<size; i++) {
            Node<String> n = (Node<String>) pq.dequeue();
            n.show();
	}
    }
}
