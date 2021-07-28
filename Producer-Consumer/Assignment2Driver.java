import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Assignment2Driver {
    public int catalogSize;
    public int numBuyers;
    public int numSellers;
    public int sellerSleepTime, buyerSleepTime;
    public Queue<Item> inventory;
    
    public static void main(String[] args) throws InterruptedException {
	
	Assignment2Driver a_driver = new Assignment2Driver();
	BufferedReader reader;
        int itemcount = 0;
	try {
	    reader = new BufferedReader(new FileReader(args[0]));
            String line = reader.readLine();
	    ArrayList<Node<Item>> list = new ArrayList<Node<Item>>();
	    String[] tokens = line.split(" ");
	    a_driver.catalogSize = Integer.parseInt(tokens[0]);
	    a_driver.numBuyers = Integer.parseInt(tokens[1]);
	    a_driver.numSellers = Integer.parseInt(tokens[2]);
	    a_driver.buyerSleepTime = Integer.parseInt(tokens[3]);
	    a_driver.sellerSleepTime = Integer.parseInt(tokens[4]);
	    
	    while (line != null) {
		line = reader.readLine();
		if (line != null) {
                    itemcount++;
		    tokens = line.split(" ");
		    
		    Item item = new Item(tokens[1], Double.parseDouble(tokens[2]));
		    Node<Item> node = new Node<Item>(Integer.parseInt(tokens[0]), item);
		    list.add(node);
		}
	    }
            a_driver.inventory = new Queue<Item>(list.size());
        
            //TODO Add all elements of the ArrayList named "list" to inventory queue
            // ...
            ///done by me
            for(int i=0;i<list.size();i++) {
            	Node<Item> node = list.get(i);
            	a_driver.inventory.enqueue(node);
            }
            ////////done by me
            reader.close();
	} catch (IOException e) {
            e.printStackTrace();
	}
	
	@SuppressWarnings("rawtypes")
	Seller[] sellers = new Seller[a_driver.numSellers];
	@SuppressWarnings("rawtypes")
	Buyer[] buyers = new Buyer[a_driver.numBuyers];
	PriorityQueue<Item> queue = new PriorityQueue<Item>(a_driver.catalogSize);
	Lock lock = new ReentrantLock();
	Condition full = lock.newCondition();
	Condition empty = lock.newCondition();
        int iteration = itemcount/a_driver.numBuyers; // No. of purchases each buyer make

	// TODO Create multiple Buyer and Seller Threads and start them.
        // ...
	//done by me
        //BuyerBase<Item> buyer = new Buyer<Item>();
        for(int i=0;i<a_driver.numSellers;i++) {
        	Seller<Item> seller = new Seller<Item>(a_driver.sellerSleepTime,a_driver.catalogSize, lock, full, empty, queue, a_driver.inventory);
            sellers[i] = seller;
            Thread selle = new Thread(seller);
            selle.start();
        }
        for(int i=0;i<a_driver.numBuyers;i++) {
	        Buyer<Item> buyer = new Buyer<Item>(a_driver.buyerSleepTime,a_driver.catalogSize, lock, full, empty, queue, iteration);
//	        SellerBase<Item> seller = new Seller<Item>(a_driver.sellerSleepTime,a_driver.catalogSize, lock, full, empty, queue, a_driver.inventory);
	        buyers[i] = buyer;
	        Thread buye = new Thread(buyer);
//	        Thread selle = new Thread(seller);
	        buye.start();
//	        selle.start();
        }
        /*for(int i=0;i<a_driver.numSellers;i++) {
        	SellerBase<Item> seller = new Seller<Item>(a_driver.sellerSleepTime,a_driver.catalogSize, lock, full, empty, queue, a_driver.inventory);
            sellers[i] = (Seller) seller;
            Thread selle = new Thread(seller);
            selle.start();
            selle.join();
        }*/
    }
}
