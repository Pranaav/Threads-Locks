// This class implements the Queue
public class Queue<V> implements QueueInterface<V>{

    //TODO Complete the Queue implementation
    private Object[] queue;
    private int capacity, currentSize, front, rear;
	
    public Queue(int capacity) {    
    	this.capacity = capacity;
    	queue = new Object[capacity];
    	this.currentSize = 0;
    	this.front = 0;
    	this.rear = 0;
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

    public void enqueue(Node<V> node) {
    	if(isFull()) {
    		return;
    	}
    	queue[rear] = node;
    	rear = (rear +1)%capacity;
    	currentSize++;
    }

    public NodeBase<V> dequeue() {
    	if(isEmpty()) {
    		return null;
    	}
    	@SuppressWarnings("unchecked")
		NodeBase<V> nod = (NodeBase<V>) queue[front];
    	queue[front] = null;
    	front = (front + 1)%capacity;
    	currentSize--;
    	return nod;
    }

}

