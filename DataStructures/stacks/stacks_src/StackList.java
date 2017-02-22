package stacks_src;

import java.util.LinkedList;

/**
 * Implement a stack using a Java Linked List
 * LIFO: items are both added and removed from the beginning of the list
 * 
 * @author adina
 *
 * @param <MyType>
 */
public class StackList<MyType> implements InterfaceStack<MyType> {
	// underlying object is a Java Linked List
	LinkedList<MyType> list = new LinkedList<MyType>();

	/**
	 * Add an item to the beginning of the list
	 * 
	 * @param item: the item to add
	 */
	@Override
	public void push(MyType item) {
		// add first element
		list.addFirst(item);
	}

	/**
	 * Remove the first item in the list
	 * 
	 * @return the item at the top of the stack or null
	 */
	@Override
	public MyType pop() {
		// check if it's empty
		if (isEmpty())
			return null;

		// otherwise remove and return first element
		return list.removeFirst();
	}

	/**
	 * Return the element from the top of the stack without removing it from the
	 * list
	 * 
	 * @return the item at the top of the stack
	 */
	@Override
	public MyType peek() {
		// check if it's empty
		if (isEmpty())
			return null;

		// otherwise show first element
		return list.peek();
	}

	/**
	 * Is the stack empty
	 * 
	 * @return true if list is empty, fals otherwise
	 */
	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	/**
	 * Get the stack size
	 * 
	 * @return the size of the list
	 */
	@Override
	public int size() {
		return list.size();
	}

	/**
	 * Get a string representation of the stack
	 * 
	 * @return a string representation of the stack
	 */
	@Override
	public String toString() {
		return list.toString();
	}
}
