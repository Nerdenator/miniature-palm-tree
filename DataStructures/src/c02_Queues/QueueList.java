package c02_Queues;

import java.util.LinkedList;

public class QueueList<MyType> implements QueueInterface<MyType> {
	//FIFO: items added at the end and removed from the beginning

	private LinkedList<MyType> list = new LinkedList<MyType>();

	@Override
	public void add(MyType item) {
		// add to the end of the list
		list.add(item);
	}

	@Override
	public MyType remove() {
		if (isEmpty())
			return null;

		// remove from the beginning of the list
		return list.remove();
	}

	@Override
	public MyType peek() {
		if (isEmpty())
			return null;

		// remove from the beginning of the list
		return list.peek();
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public String toString() {
		return list.toString();
	}
}
