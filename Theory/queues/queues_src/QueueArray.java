package queues_src;

import java.util.Arrays;

//
/**
 * Implement a queue using an array
 * FIFO: items added at the end and removed from the beginning of the array
 * - cycle around by keeping track of where the head and the tail of the queue
 * 
 * @author adina
 *
 * @param <MyType>
 */
public class QueueArray<MyType> implements InterfaceQueue<MyType> {
	// underlying object is an array of size capacity
	private MyType[] array;
	private int capacity;

	// number of elements in the queue
	private int size = 0;

	// point where the queue starts
	private int head = 0;
	// point where the queue ends
	private int tail = -1;

	/**
	 * Constructor: create an array of size capacity to hold the queue
	 * 
	 * @param capacity: queue capacity
	 */
	@SuppressWarnings("unchecked")
	public QueueArray(int capacity) {
		this.capacity = capacity;
		array = (MyType[]) new Object[capacity];
	}

	/**
	 * Add to the end of the queue
	 * 
	 * @param item: the item to add
	 */
	@Override
	public void add(MyType item) throws Exception {
		// check that we haven't run out of space in the array
		if (size == capacity)
			throw new Exception("Queue size is out of bounds!");

		// circular array, so need to (mod) capacity
		tail = (tail + 1) % capacity;
		size++;
		array[tail] = item;
	}

	/**
	 * Remove from the beginning of the queue
	 * 
	 * @return: the element at the beginning of the queue or null
	 */
	@Override
	public MyType remove() {
		// check that the array is empty
		if (isEmpty())
			return null;

		// if it's not, remove and return first element
		MyType item = array[head];
		// move the starting point over one, circularly
		head = (head + 1) % capacity;
		size--;

		return item;
	}

	/**
	 * Show the element at beginning of the queue
	 * 
	 * @return: the element at the beginning of the queue or null
	 */
	@Override
	public MyType peek() {
		// check that queue's not empty
		if (isEmpty())
			return null;

		// otherwise, return first item
		return array[head];
	}

	/**
	 * Get the size of the array
	 * 
	 * @return size of the array
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Is the array empty
	 * 
	 * @return boolean depending on the array size
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Get a string representation of the queue (array excluding nulls)
	 * 
	 * @return a string representation of the queue
	 */
	@Override
	public String toString() {
		return Arrays.toString(Arrays.copyOf(array, size));
	}
}
