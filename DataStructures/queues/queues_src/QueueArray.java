package queues_src;

//FIFO: items added at the end and removed from the beginning

public class QueueArray<MyType> implements QueueInterface<MyType> {
	// underlying (static) array
	private MyType[] A;
	// constant, the array size
	private int capacity;
	//number of elements in the queue
	private int queue_size = 0;
	//point where the queue starts
	private int head = 0;
	// point where the queue ends
	private int tail = -1;

	// constructor
	@SuppressWarnings("unchecked")
	public QueueArray(int capacity) {
		this.capacity = capacity;
		A = (MyType[]) new Object[capacity];
	}

	/* Add to the end of the queue
	 * (non-Javadoc)
	 * @see c04_queues.QueueInterface#add(java.lang.Object)
	 */
	@Override
	public void add(MyType item) throws Exception {
		//check that we haven't run out of space in the array
		if (queue_size == capacity)
			throw new Exception("Queue size is out of bounds!");

		// circular array, so need to (mod) capacity
		tail = (tail + 1) % capacity;
		queue_size++;
		A[tail] = item;
	}

	/* Remove from the beginning of the queue
	 * (non-Javadoc)
	 * @see c04_queues.QueueInterface#remove()
	 */
	@Override
	public MyType remove() {
		// check that the array is not empty
		if (isEmpty())
			return null;

		// if it's not, remove and return first element
		MyType item = A[head];
		// move the starting point over 
		head = (head + 1) % capacity;
		queue_size--;

		return item;
	}

	/* Return element at beginning of the queue
	 * (non-Javadoc)
	 * @see c04_queues.QueueInterface#peek()
	 */
	@Override
	public MyType peek() {
		// check that queue's not empty
		if (isEmpty())
			return null;

		// otherwise, return first item
		return A[head];
	}

	@Override
	public int size() {
		return queue_size;
	}

	@Override
	public boolean isEmpty() {
		return queue_size == 0;
	}

	@Override
	public String toString() {
		if (queue_size == 0)
			return "[]";
		StringBuilder sb = new StringBuilder();
		int i = head;
		while (i != tail) {
			sb.append(A[i] + ", ");
			i = (i + 1) % capacity;
		}

		return "[" + sb + A[tail] + "]";
	}
}
