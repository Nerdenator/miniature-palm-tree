package stacks_src;

// LIFO: items added at the top and removed from the top
// the top is the end of the array

public class StackArray<MyType> implements StackInterface<MyType> {
	// underlying (static) array
	private MyType[] A;
	// constant, the array size
	private int capacity;
	//size of the stack
	private int stack_size = 0;

	@SuppressWarnings("unchecked")
	public StackArray(int capacity) {
		this.capacity = capacity;
		A = (MyType[]) new Object[capacity];
	}

	/* Add to the top of the stack (end of array)
	 * (non-Javadoc)
	 * @see c05_stacks.StackInterface#push(java.lang.Object)
	 */
	@Override
	public void push(MyType item) throws Exception {
		// check if we've reached capacity
		if (stack_size == capacity)
			throw new Exception("Stack size is out of bounds!");

		// add item to the end of the array and increase stack size
		A[stack_size++] = item;
	}

	/* Remove from the top of the stack (end of array)
	 * (non-Javadoc)
	 * @see c05_stacks.StackInterface#pop()
	 */
	@Override
	public MyType pop() {
		// check if stack is empty
		if (isEmpty())
			return null;

		// if not, return the last element and decrease the size
		return A[--stack_size];
	}

	/* Show the top (last) element without removing it
	 * (non-Javadoc)
	 * @see c05_stacks.StackInterface#peek()
	 */
	@Override
	public MyType peek() {
		// check if stack is empty
		if (isEmpty())
			return null;

		// return the last element without removing it
		return A[stack_size - 1];
	}

	@Override
	public boolean isEmpty() {
		return stack_size == 0;
	}

	@Override
	public int size() {
		return stack_size;
	}

	@Override
	public String toString() {
		if (isEmpty())
			return "[]";
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < stack_size - 1; i++) {
			sb.append(A[i] + ", ");
		}
		return "[" + sb + A[stack_size - 1] + "]";
	}
}
