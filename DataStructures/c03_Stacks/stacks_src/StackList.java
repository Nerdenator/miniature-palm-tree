package stacks_src;

import java.util.LinkedList;

//LIFO: items added at the beginning and removed from the beginning

public class StackList<MyType> implements StackInterface<MyType> {

	LinkedList<MyType> list = new LinkedList<MyType>();

	@Override
	public void push(MyType item) {
		// add first element
		list.addFirst(item);
	}

	@Override
	public MyType pop() {
		// check if it's empty
		if (isEmpty())
			return null;

		// otherwise remove and return first element
		return list.removeFirst();
	}

	@Override
	public MyType peek() {
		// check if it's empty
		if (isEmpty())
			return null;

		// otherwise show first element
		return list.peek();
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public String toString() {
		return list.toString();
	}
}
