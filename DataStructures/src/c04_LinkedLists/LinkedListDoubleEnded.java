package c04_LinkedLists;

/**
 * This is a special linked list in which we have a node, tail, which points at
 * the last node in the list, which makes addLast run in O(1)
 * 
 * @author adina
 *
 * @param <MyType>
 */
public class LinkedListDoubleEnded<MyType> implements ListInterface<MyType> {
	private Node<MyType> head;
	private Node<MyType> tail;

	// empty constructor
	public LinkedListDoubleEnded() {
		head = null;
		tail = null;
	}

	// constructor for one node
	public LinkedListDoubleEnded(MyType data) {
		head = new Node<>(data);
		tail = head;
	}

	// constructor from an array of nodes
	public LinkedListDoubleEnded(MyType[] array) {
		for (MyType data : array)
			addFirst(data);
	}

	/**
	 * Add to the beginning of the list in O(1)
	 * 
	 * @param data
	 */
	@Override
	public void addFirst(MyType data) {
		if (head == null) {
			head = new Node<>(data);
			tail = head;
			return;
		}
		Node<MyType> temp = new Node<>(data);
		temp.next = head;
		head = temp;
	}

	/**
	 * Remove from the beginning of the list in O(1)
	 * 
	 * @return the removed node
	 */
	@Override
	public Node<MyType> removeFirst() {
		// no nodes
		if (head == null)
			return null;
		// the node to return
		Node<MyType> temp = head;
		// only one element in the list, need to adjust tail
		if (head.next == null) {
			head = null;
			tail = null;
			return temp;
		}
		head = head.next;
		return temp;
	}

	/**
	 * Add to the end of the list in O(1)
	 * 
	 * @param data
	 */
	@Override
	public void addLast(MyType data) {
		if (head == null) {
			head = new Node<>(data);
			tail = head;
			return;
		}
		// add node to the end
		tail.next = new Node<>(data);
		// advance tail to last element
		tail = tail.next;
	}

	/**
	 * Remove the last node in O(n) because we need go through the list until
	 * we find the previous node to tail
	 * 
	 * @return the node removed
	 */
	@Override
	public Node<MyType> removeLast() {
		if (head == null)
			return null;
		if (head.next == null) {
			Node<MyType> temp = head;
			head = null;
			tail = null;
			return temp;
		}
		// find the prev node to last
		Node<MyType> prev = head;
		while (prev.next != tail)
			prev = prev.next;
		// save the last node
		Node<MyType> temp = tail;
		prev.next = null;
		tail = prev;
		return temp;
	}

	/**
	 * Find the first node containing value in O(n) time
	 * 
	 * @param value
	 * @return the node containing value
	 */
	@Override
	public Node<MyType> find(MyType value) {
		// find the node (null if head is null or value isn't found)

		Node<MyType> cur = head;
		while (cur != null && !cur.data.equals(value))
			cur = cur.next;

		return cur;
	}

	/**
	 * Delete the node that contains value in O(n)
	 * 
	 * @param value
	 * @return the deleted Node
	 */
	@Override
	public Node<MyType> delete(MyType value) {
		// if empty list, no node to delete
		if (head == null)
			return null;
		// if the value is at the head
		if (head.data.equals(value)) {
			// save head for returning
			Node<MyType> temp = head;
			// if only one node, adjust tail too
			if (tail == head) {
				tail = null;
			}
			// if multiple nodes, just advance head (works for null too)
			head = head.next;
			return temp;
		}

		//current node
		Node<MyType> cur = head;
		//previous node
		Node<MyType> prev = null;
		// find the node containing data (cur) or null
		// and the node immediately before it (prev)
		while (cur != null && !cur.data.equals(value)) {
			//save previous node
			prev = cur;
			//advance current node
			cur = cur.next;
		}
		// if we didn't find anything
		if (cur == null)
			return null;
		// if last element, need to adjust tail
		if (cur == tail) {
			tail = prev;
			tail.next = null;
			return cur;
		}
		// otherwise, skip over cur
		prev.next = prev.next.next;
		return cur;
	}

	/**
	 * Convert the list to a string, starting at head
	 */
	@Override
	public String toString() {
		if (head == null)
			return "[]";

		StringBuilder sb = new StringBuilder();

		// iterate from head to node before tail
		Node<MyType> cur = head;
		while (cur.next != null) {
			sb.append(cur.data + " -> ");
			cur = cur.next;
		}
		// append tail
		sb.append(cur.data);
		return "[" + sb + "]";
	}

	/**
	 * Get the tail for tests
	 * 
	 * @return the tail node
	 */
	protected Node<MyType> getTail() {
		return tail;
	}
}
