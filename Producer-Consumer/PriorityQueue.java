
public class PriorityQueue<V> implements QueueInterface<V>{

    private Object[] queue;
    private int capacity, currentSize;

    //TODO Complete the Priority Queue implementation
    // You may create other member variables/ methods if required.
    public PriorityQueue(int capacity) {    
    	this.capacity = capacity;
    	queue = new Object[capacity];
    	currentSize =0;
    }

    public int size() {
    	return currentSize;
    }

    public boolean isEmpty() {
    	if(currentSize==0) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
	
    public boolean isFull() {
    	if(currentSize==capacity) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }

    public void enqueue(Node<V> node){
    	if(isFull()) {
    		return;
    	}
    	//queue[rear] = node;
    	queue[currentSize] = node;
    	//queue[] = node;
    	currentSize++;
    }

    // In case of priority queue, the dequeue() should 
    // always remove the element with minimum priority value
    @SuppressWarnings("unchecked")
	public NodeBase<V> dequeue() {
    	if(isEmpty()) {
    		return null;
    	}
		NodeBase<V> nod = (NodeBase<V>) queue[0];
    	int index = 0;
		int prior = ((Node<V>) queue[0]).getPriority();
    	for(int i=0;i<currentSize;i++) {
    		if(((Node<V>) queue[i]).getPriority()<prior) {
    			nod = (NodeBase<V>) queue[i];
    			prior = nod.getPriority();
    			index = i;
    		}
    	}
    	for(int i=index;i<currentSize-1;i++) {
    		queue[i] = queue[i+1];
    	}
    	queue[currentSize-1] = null;
    	currentSize--;
    	return nod;
    }

    @SuppressWarnings("unchecked")
	public void display () {
	if (this.isEmpty()) {
            System.out.println("Queue is empty");
	}
	for(int i=0; i<currentSize; i++) {
            ((NodeBase<V>) queue[i+1]).show();
	}
    }
}

