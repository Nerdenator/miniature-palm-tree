package queues_src;

import java.util.LinkedList;

/**
 * Implement a stack using a Java Linked List
 * FIFO: items added at the end and removed from the beginning of the list
 * 
 * @author adina
 *
 * @param <MyType>
 */
public class QueueList<MyType> implements InterfaceQueue<MyType> {
	// underlying object is a Java Linked List
	private LinkedList<MyType> list = new LinkedList<MyType>();

	/**
	 * Add an item to the end of the list
	 * 
	 * @param item: the item to add
	 */
	@Override
	public void add(MyType item) {
		// add to the end of the list
		list.addLast(item);
	}

	/**
	 * Remove the first item in the list
	 * 
	 * @return the item at the beginning of the queue or null
	 */
	@Override
	public MyType remove() {
		if (isEmpty())
			return null;

		// remove from the beginning of the list
		return list.removeFirst();
	}

	/**
	 * Show the first element without removing it
	 * 
	 * @return the item at the beginning of the queue
	 */
	@Override
	public MyType peek() {
		if (isEmpty())
			return null;

		// show item at the beginning of the list
		return list.peek();
	}

	/**
	 * Get the queue size
	 * 
	 * @return the size of the list
	 */
	@Override
	public int size() {
		return list.size();
	}

	/**
	 * Is the queue empty
	 * 
	 * @return true if list is empty, false otherwise
	 */
	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	/**
	 * Get a string representation of the queue
	 * 
	 * @return a string representation of the queue
	 */
	@Override
	public String toString() {
		return list.toString();
	}
}
