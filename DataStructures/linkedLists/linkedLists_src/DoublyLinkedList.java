package linkedLists_src;

import linkedLists_util.InterfaceLinkedList;
import linkedLists_util.Node;

/**
 * 
 * In a doubly linked list each node in the list stores the contents of the
 * node, a reference to the previous node and a reference to the next node in
 * the list.
 * Note that this is also a double-ended with tail pointing to end of list.
 * 
 * @author adina
 *
 * @param <MyType>
 */
public class DoublyLinkedList<MyType> implements InterfaceLinkedList<MyType> {
	// reference to first node in list
	private Node<MyType> head;
	// reference to last node in list
	private Node<MyType> tail;

	/**
	 * Empty constructor
	 */
	public DoublyLinkedList() {
		head = null;
		tail = null;
	}

	/**
	 * Constructor for one node
	 * 
	 * @param data: the data in the node
	 */
	public DoublyLinkedList(MyType data) {
		head = new Node<>(data);
		tail = head;
	}

	/**
	 * Constructor from an array of nodes
	 * 
	 * @param array: array to create the list from, not that the list will be in
	 *            reverse order
	 */
	public DoublyLinkedList(MyType[] array) {
		for (MyType data : array)
			addFirst(data);
	}

	/**
	 * Add to the beginning of the list in O(1)
	 * 
	 * @param data: the data to add
	 */
	@Override
	public void addFirst(MyType data) {
		// if the list was empty
		if (head == null) {
			head = new Node<>(data);
			// the end is the same as the beginning
			tail = head;
			return;
		}
		// otherwise, creaye the new node
		Node<MyType> temp = new Node<>(data);
		// add the original head node after the new node
		temp.next = head;
		// add the original head node before the new node
		head.prev = temp;
		// the new node is now the head node
		head = temp;
	}

	/**
	 * Remove from the beginning of the list in O(1)
	 * 
	 * @return the removed node
	 */
	@Override
	public Node<MyType> removeFirst() {
		// if the list was empty, no changes needed
		if (head == null)
			return null;
		// the node to return is the head
		Node<MyType> temp = head;
		// if only one node in the list, clear list
		if (head.next == null) {
			// both head and tail point to null
			head = null;
			tail = null;
			// return the saved node
			return temp;
		}
		// otherwise, advance head
		head = head.next;
		// and sever connection to prev
		head.prev = null;

		// return the saved node
		return temp;
	}

	/**
	 * Add to the end of the list in O(1)
	 * 
	 * @param data: the data to add
	 */
	@Override
	public void addLast(MyType data) {
		// if the list was empty, create it
		if (head == null) {
			// both head and tail point to the new node
			head = new Node<>(data);
			tail = head;
			return;
		}
		// otherwise, create the new node
		Node<MyType> temp = new Node<>(data);
		// add new node after the tail
		tail.next = temp;
		// move the new node before the tail
		temp.prev = tail;
		// set the tail to the last (new) node
		tail = temp;
	}

	/**
	 * Remove the last node in O(1) time (because we have link to prev)
	 * 
	 * @return the node removed
	 */
	@Override
	public Node<MyType> removeLast() {
		// either null or last node, the node to return
		Node<MyType> temp = tail;

		// if the list was empty or has only one element
		if (head == null || tail == head) {
			// empty the list
			tail = null;
			head = null;
			// return the saved node
			return temp;
		}
		// otherwise, move tail to next-to-last element
		tail = tail.prev;
		// sever connection to removed node
		tail.next = null;
		// return the saved node
		return temp;
	}

	/**
	 * Find the first node containing value in O(n) time
	 * 
	 * @param value: the value to find
	 * @return the node containing value or null if none found
	 */
	@Override
	public Node<MyType> find(MyType value) {
		// start at the head
		Node<MyType> cur = head;

		// continue until either the current node is null or the value is found
		while (cur != null && !cur.data.equals(value))
			cur = cur.next;

		// cur is either what we're looking for or null
		return cur;
	}

	/**
	 * Delete the node that contains value in O(n)
	 * 
	 * @param value: the value to delete
	 * @return the deleted node or null if not found
	 */
	@Override
	public Node<MyType> delete(MyType value) {
		// use find to retrieve the node to delete
		Node<MyType> cur = find(value);
		// if not found, return null
		if (cur == null)
			return null;

		// if it's at the head
		if (cur == head) {
			// if there's only one element in the list
			if (head == tail) {
				// empty list
				head = null;
				tail = null;
				// return the retrieved node
				return cur;
			}
			// otherwise advance head
			head = head.next;
			// cut connection to previous node
			head.prev = null;
			// return the retrieved node
			return cur;
		}
		// if it's at the tail
		if (cur == tail) {
			// move tail to node before it
			tail = tail.prev;
			// cut connection to the next node
			tail.next = null;
			// return the retrieved node
			return cur;
		}
		// otherwise if it's not at the head
		// skip it one way
		cur.prev.next = cur.next;
		// skip it the other way
		cur.next.prev = cur.prev;
		// return the retrieved node
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
			sb.append(cur.data + " <-> ");
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
	public Node<MyType> getTail() {
		return tail;
	}

	/**
	 * Get the head for tests
	 * 
	 * @return the head node
	 */
	public Node<MyType> getHead() {
		return head;
	}
}
