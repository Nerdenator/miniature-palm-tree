package queues_src;

/*
 * FIFO: items added at the end and removed from the beginning of the queue
 */
public interface QueueInterface<MyType> {
	// add element to the end of the queue
	public void add(MyType obj) throws Exception;

	// remove element from the front of the queue
	public MyType remove();

	// show the element at the front of the queue without removing it
	public MyType peek();

	// size of queue
	public int size();

	// is queue empty?
	public boolean isEmpty();
}
