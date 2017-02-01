package linkedLists_src;

public class LinkedListSinglyLinked<MyType> implements ListInterface<MyType> {
	// Node pointing to beginning of the list
	protected Node<MyType> head;

	// Empty constructor
	public LinkedListSinglyLinked() {
		head = null;
	}

	// Constructor for one node
	public LinkedListSinglyLinked(MyType data) {
		head = new Node<MyType>(data);
	}

	// Constructor to create a linked list from an array
	public LinkedListSinglyLinked(MyType[] array) {
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
			return;
		}
		Node<MyType> temp = new Node<>(data);
		temp.next = head;
		head = temp;
	}

	/**
	 * Remove from the beginning of the list in O(1)
	 * 
	 * @return the removed Node
	 */
	@Override
	public Node<MyType> removeFirst() {
		if (head == null)
			return null;

		// save node to return
		Node<MyType> temp = head;

		// advance head of list
		head = head.next;

		return temp;
	}

	/**
	 * Add to the end of the list in O(n)
	 * 
	 * @param data
	 */
	@Override
	public void addLast(MyType data) {
		if (head == null) {
			head = new Node<>(data);
			return;
		}

		// find the end of the list
		Node<MyType> cur = head;
		while (cur.next != null)
			cur = cur.next;

		// add the new node at the end
		cur.next = new Node<>(data);
	}

	/**
	 * Remove from the end of list in O(n)
	 * 
	 * @return the removed Node
	 */
	@Override
	public Node<MyType> removeLast() {
		// no Node in list
		if (head == null)
			return null;
		// only one Node in list
		if (head.next == null) {
			Node<MyType> temp = head;
			head = null;
			return temp;
		}

		// advance to node before end of list
		Node<MyType> cur = head;
		while (cur.next != null && cur.next.next != null)
			cur = cur.next;
		// save last node
		Node<MyType> temp = cur.next;

		// remove last node from list
		cur.next = null;

		//return last node
		return temp;
	}

	/**
	 * Delete the node that contains value in O(n)
	 * 
	 * @param value
	 * @return the deleted Node or null if not found
	 */
	@Override
	public Node<MyType> delete(MyType value) {
		// empty list
		if (head == null)
			return null;
		// if the value's at the head, advance head
		if (head.data.equals(value)) {
			Node<MyType> temp = head;
			head = head.next;
			return temp;
		}

		// Find the node that is previous to the node containing value
		Node<MyType> prev = findPrevNode(value);
		// If no such node exists, there's nothing to delete
		if (prev == null)
			return null;

		// Save the node to return
		Node<MyType> cur = prev.next;
		// Skip over the node containing the value
		prev.next = cur.next;

		return cur;
	}

	/**
	 * Find the first node containing value in O(n) time
	 * 
	 * @param value
	 * @return the node containing value
	 */
	@Override
	public Node<MyType> find(MyType value) {

		Node<MyType> cur = head;

		while (cur != null && !cur.data.equals(value))
			cur = cur.next;

		// cur is either what we're looking for or null
		return cur;
	}

	/**
	 * Find the node before value in O(n)
	 * 
	 * @param value
	 */
	private Node<MyType> findPrevNode(MyType value) {
		// if there are less than 2 items in the list, return null
		if (head == null || head.next == null)
			return null;

		// iterate until either find the value or end of list
		Node<MyType> cur = head;
		while (cur.next != null) {
			if (cur.next.data.equals(value))
				return cur;
			cur = cur.next;
		}
		// no node was found
		return null;
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

}
