import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Seller<V> extends SellerBase<V> {
	public int catalogSize;
    public Seller (int sleepTime, int catalogSize, Lock lock, Condition full, Condition empty, PriorityQueue<V> catalog, Queue<V> inventory) {
        //TODO Complete the constructor method by initializing the attibutes
        // ...
    	this.setSleepTime(sleepTime);
    	this.catalog = catalog;
    	this.full = full;
    	this.empty = empty;
    	this.lock = lock;
    	this.inventory = inventory;
    	this.catalogSize = catalogSize;
    }
    
    public void sell() throws InterruptedException {
    	lock.lock();
	try {
            //TODO Complete the try block for produce method
            // ...
		while(catalog.size()>=catalogSize) {
			full.await();
		}
		if(!inventory.isEmpty()) {
			NodeBase<V> nodi = inventory.dequeue();
			Node<V> nod = new Node<V>(nodi.priority,nodi.value);
			catalog.enqueue(nod);
		}
	} catch(Exception e) {
            e.printStackTrace();
	} finally {
            //TODO Complete this block
			empty.signal();
			lock.unlock();
		}
    }
}
