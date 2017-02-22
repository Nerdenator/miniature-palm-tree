package stacks_src;

import java.util.Arrays;

/**
 * Implement a stack using an array
 * LIFO: items are both added and removed from the top (end of the array)
 * 
 * @author adina
 *
 * @param <MyType>
 */
public class StackArray<MyType> implements InterfaceStack<MyType> {
	// underlying object is an array of size capacity
	private MyType[] array;
	private int capacity;

	// size of the stack
	private int size = 0;

	/**
	 * Constructor: create an array of size capacity to hold the stack
	 * 
	 * @param capacity: stack capacity
	 */
	@SuppressWarnings("unchecked")
	public StackArray(int capacity) {
		this.capacity = capacity;
		array = (MyType[]) new Object[capacity];
	}

	/**
	 * Add to the top of the stack (end of array)
	 * 
	 * @param item: the element to push
	 */
	@Override
	public void push(MyType item) throws Exception {
		// check if we've reached capacity
		if (size == capacity)
			throw new Exception("Stack size is out of bounds!");

		// add item to the end of the array and increase stack size
		array[size++] = item;
	}

	/**
	 * Remove from the top of the stack (end of array)
	 * 
	 * @return: the element at the top of the stack or null
	 */
	@Override
	public MyType pop() {
		// check if stack is empty
		if (isEmpty())
			return null;

		// if not, return the last element and decrease the size
		return array[--size];
	}

	/**
	 * Show the top (last) element without removing it
	 * 
	 * @return: the element at the top of the stack
	 */
	@Override
	public MyType peek() {
		// check if stack is empty
		if (isEmpty())
			return null;

		// return the last element without removing it
		return array[size - 1];
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
	 * Get the size of the array
	 * 
	 * @return size of the array
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Get a string representation of the stack (array excluding nulls)
	 * 
	 * @return a string representation of the stack
	 */
	@Override
	public String toString() {
		return Arrays.toString(Arrays.copyOf(array, size));
	}
}
