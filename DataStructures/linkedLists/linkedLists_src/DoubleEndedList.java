package linkedLists_src;

import linkedLists_util.InterfaceLinkedList;
import linkedLists_util.Node;

/**
 * This is a special (singly) linked list in which we have a node, tail, which
 * points at the last node in the list, which makes addLast also run in O(1)
 * 
 * @author adina
 *
 * @param <MyType>
 */
public class DoubleEndedList<MyType> implements InterfaceLinkedList<MyType> {
	// reference to first node in list
	private Node<MyType> head;
	// reference to last node in list
	private Node<MyType> tail;

	/**
	 * Empty constructor
	 */
	public DoubleEndedList() {
		head = null;
		tail = null;
	}

	/**
	 * Constructor for one node
	 * 
	 * @param data: the data in the node
	 */
	public DoubleEndedList(MyType data) {
		head = new Node<>(data);
		tail = head;
	}

	/**
	 * Constructor from an array of nodes
	 * 
	 * @param array: array to create the list from, not that the list will be in
	 *            reverse order
	 */
	public DoubleEndedList(MyType[] array) {
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
		// if the list was empty, create it
		if (head == null) {
			head = new Node<>(data);
			// the end is the same as the beginning
			tail = head;
			return;
		}
		// create the new node
		Node<MyType> temp = new Node<>(data);
		// add the original head node after the new node
		temp.next = head;
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
		// if the list was empty
		if (head == null)
			return null;
		// the node to return is the head
		Node<MyType> temp = head;
		// if only one element in the list
		if (head.next == null) {
			// both head and tail point to null
			head = null;
			tail = null;
			// return the saved node
			return temp;
		}
		// otherwise, just advance head
		head = head.next;
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
		// otherwise, add the new node after tail
		tail.next = new Node<>(data);
		// advance tail to the last element
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
		// either null or last node, the node to return
		Node<MyType> temp = tail;

		// if the list was empty or has only one element
		if (head == null || head.next == null) {
			// empty the list
			head = null;
			tail = null;
			// return the saved node
			return temp;
		}

		// otherwise, go to the node before the last
		Node<MyType> prev = head;
		while (prev.next != tail)
			prev = prev.next;
		// remove the last node
		prev.next = null;
		// make tail point to new last node
		tail = prev;
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
		// if empty list, no node to delete
		if (head == null)
			return null;
		// if the value is at the head, advance head
		if (head.data.equals(value)) {
			// save original head node
			Node<MyType> temp = head;
			// if only one node list is empty, adjust tail
			if (tail == head) {
				tail = null;
			}
			// advance head (works for one-element list too, head becomes null)
			head = head.next;
			// return saved node
			return temp;
		}

		// find the previous node to the node containing value O(n)
		// Note: the value is not at the head, so prev != null at the end

		// start at head, cur will be the element to delete
		Node<MyType> cur = head;
		// previous node is originally null
		Node<MyType> prev = null;
		// find the node containing data (cur) or null
		// and the node immediately before it (prev)
		while (cur != null && !cur.data.equals(value)) {
			// save previous node
			prev = cur;
			// advance current node
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
	 * 
	 * @return a string representation of the list
	 */
	@Override
	public String toString() {
		if (head == null)
			return "[]";

		StringBuilder sb = new StringBuilder();

		// iterate from head to node before tail (for "->")
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
	public Node<MyType> getTail() {
		return tail;
	}
}
