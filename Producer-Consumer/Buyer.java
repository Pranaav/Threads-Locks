import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Buyer<V> extends BuyerBase<V> {
    public Buyer (int sleepTime, int catalogSize, Lock lock, Condition full, Condition empty, PriorityQueue<V> catalog, int iteration) {
        //TODO Complete the Buyer Constructor method
        // ...
    	this.setSleepTime(sleepTime);
    	this.lock = lock;
    	this.full = full;
    	this.empty = empty;
    	this.catalog = catalog;
    	//this.catalog. = catalogSize;
    	this.setIteration(iteration);
    }
    public void buy() throws InterruptedException {
    	lock.lock();
    	try {
            //TODO Complete the try block for consume method
            // ...
		
		NodeBase<V> n;
		
		while(catalog.size()<=0) {
			empty.await();
		}
		
		n = catalog.dequeue();
	    System.out.print("Consumed "); // DO NOT REMOVE (For Automated Testing)
            n.show(); // DO NOT REMOVE (For Automated Testing)
            // ...
            
	} catch (Exception e) {
            e.printStackTrace();
	} finally {
            //TODO Complete this block
		full.signal();
		lock.unlock();
	}
    }
}
