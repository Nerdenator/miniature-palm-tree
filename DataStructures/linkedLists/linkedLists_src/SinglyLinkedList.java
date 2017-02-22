package linkedLists_src;

import linkedLists_util.InterfaceLinkedList;
import linkedLists_util.Node;

/**
 * In a singly linked list each node in the list stores the contents of the node
 * and a reference to the next node in the list. It does not store a reference
 * to the previous node.
 * Note that this is also a single-ended list, meaning that we do not also save
 * a pointer to the end of the list (just to the head)
 * 
 * @author adina
 *
 * @param <MyType>
 */
public class SinglyLinkedList<MyType> implements InterfaceLinkedList<MyType> {
	// Node pointing to the beginning of the list
	protected Node<MyType> head;

	/**
	 * Empty constructor
	 */
	public SinglyLinkedList() {
		head = null;
	}

	/**
	 * Constructor for one node
	 * 
	 * @param data: the data in the node
	 */
	public SinglyLinkedList(MyType data) {
		head = new Node<MyType>(data);
	}

	/**
	 * Constructor to create a linked list from an array
	 * 
	 * @param array: array to create the list from, not that the list will be in
	 *            reverse order
	 */
	public SinglyLinkedList(MyType[] array) {
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
			return;
		}
		// create a new node
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
		// save the node to return after modifying list
		Node<MyType> temp = head;
		// advance head of list
		head = head.next;
		// return saved node
		return temp;
	}

	/**
	 * Add to the end of the list in O(n)
	 * 
	 * @param data: the data to add
	 */
	@Override
	public void addLast(MyType data) {
		// if the list was empty, create it
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
	 * @return the removed node
	 */
	@Override
	public Node<MyType> removeLast() {
		// if the list was empty
		if (head == null)
			return null;
		// only one node in list, new list is empty
		if (head.next == null) {
			// save node to return
			Node<MyType> temp = head;
			// the head is null
			head = null;
			// return the saved node
			return temp;
		}

		// go to the node before the last
		Node<MyType> cur = head;
		while (cur.next != null && cur.next.next != null)
			cur = cur.next;
		// save last node
		Node<MyType> temp = cur.next;
		// remove last node from list
		cur.next = null;
		// return last node
		return temp;
	}

	/**
	 * Delete the node that contains value in O(n)
	 * 
	 * @param value: the value to delete
	 * @return the deleted node or null if not found
	 */
	@Override
	public Node<MyType> delete(MyType value) {
		// if the list was empty
		if (head == null)
			return null;
		// if the value is at the head, advance head
		if (head.data.equals(value)) {
			// save original head node
			Node<MyType> temp = head;
			// advance to new head node
			head = head.next;
			// return saved node
			return temp;
		}

		// find the previous node to the node containing value O(n)
		Node<MyType> prev = findPrevNode(value);
		// If no such node exists, there's nothing to delete
		if (prev == null)
			return null;

		// Save the node to return
		Node<MyType> temp = prev.next;
		// Skip over the node containing the value
		prev.next = temp.next;
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
	 * Find the node before the given value in O(n)
	 * 
	 * @param value: the value of the node after the node we're searching for
	 * @return the node before the node containing value or null
	 */
	private Node<MyType> findPrevNode(MyType value) {
		// if there are less than 2 items in the list, return null, since there
		// cannot be a previous node
		if (head == null || head.next == null)
			return null;

		// iterate until either we find the value or we hit the end of the list
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
	 * Convert the list to a string, starting at the head
	 * 
	 * @return a string representation of the list
	 */
	@Override
	public String toString() {
		if (head == null)
			return "[]";

		StringBuilder sb = new StringBuilder();

		// iterate from head to the node before tail (for "->")
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
