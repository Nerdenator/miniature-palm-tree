package c04_LinkedLists;

import c02_Queues.QueueInterface;
import c02_Queues.QueueList;
import c03_Stacks.StackInterface;
import c03_Stacks.StackList;

public class ReverseSinglyLinkedList<MyType> extends LinkedListSinglyLinked<MyType> {
	//empty constructor
	public ReverseSinglyLinkedList() {
		super();
	}

	// Constructor for one node
	public ReverseSinglyLinkedList(MyType data) {
		super(data);
	}

	// Constructor to create a linked list from an array
	public ReverseSinglyLinkedList(MyType[] array) {
		super(array);
	}

	/**
	 * Reverse a singly linked list using a stack (LIFO)
	 * Requires O(2n) space
	 * 
	 * @throws Exception
	 */
	public void reverseUsingStack() throws Exception {
		// empty or one-element list, nothing to reverse
		if (head == null || head.next == null)
			return;
		StackInterface<Node<MyType>> stack = new StackList<>();
		Node<MyType> cur = head;
		while (cur != null) {
			stack.push(cur);
			cur = cur.next;
		}
		head = stack.pop();
		cur = head;
		while (!stack.isEmpty()) {
			cur.next = stack.pop();
			cur = cur.next;
		}
		cur.next = null;
	}

	/**
	 * Reverse a singly linked list using a queue (FIFO)
	 * Also requires O(2n) space
	 * 
	 * @throws Exception
	 */
	public void reverseUsingQueue() throws Exception {
		// empty or one-element list, nothing to reverse
		if (head == null || head.next == null)
			return;
		QueueInterface<Node<MyType>> queue = new QueueList<>();
		while (head != null) {
			queue.add(head);
			removeFirst();
		}
		while (!queue.isEmpty()) {
			Node<MyType> cur = queue.remove();
			addFirst(cur.data);
		}
	}

	/**
	 * Reverse a singly linked list by using another singly linked list
	 * Also requires O(2n) space
	 */
	public void reverseUsingOtherList() {
		// empty or one-element list, nothing to reverse
		if (head == null || head.next == null)
			return;
		ReverseSinglyLinkedList<MyType> list2 = new ReverseSinglyLinkedList<>();
		Node<MyType> cur = head;
		while (cur != null) {
			// remove from head to tail
			Node<MyType> node = this.removeFirst();
			// add to head 
			list2.addFirst(node.data);
			// set cur at the beginning of list
			cur = head;
		}
		//set head at the beginning of the newly created list
		head = list2.head;
	}

	/**
	 * Use the same list: repeatedly add current head after original last
	 * element
	 * Requires O(n) space, O(n) time in singly linked
	 */
	public void reverseListMoveHeadToEndRepeatedly() {
		// empty or one-element list, nothing to reverse
		if (head == null || head.next == null)
			return;

		// find the original last node in the list
		Node<MyType> last = head;
		while (last.next != null) {
			last = last.next;
		}

		// repeatedly remove head and add after original last node
		Node<MyType> cur = head;
		while (cur != last) {
			// remove the head
			removeFirst();
			// save the current successor of last
			Node<MyType> temp = last.next;
			// add it after cur
			cur.next = temp;
			// add cur after last
			last.next = cur;
			// set cur to next head
			cur = head;
		}
	}

	/**
	 * Repeatedly swap connections between nodes so that n1 -> n2
	 * becomes n2 -> n1; iteratively
	 * Requires O(n) space and O(n) time
	 */
	public void reverseListSwapConnectionsIterative() {
		// empty or one-element list, nothing to reverse
		if (head == null || head.next == null)
			return;
		// go through the list reversing connections
		Node<MyType> cur = head;
		Node<MyType> prev = null;
		while (cur != null) {
			//save the successor
			Node<MyType> temp = cur.next;
			//point next to prev
			cur.next = prev;
			//save the current element in prev for the next iteration
			prev = cur;
			//move cur to saved successor
			cur = temp;
		}
		//at the end prev points to last/first element and cur points to null
		head = prev;
	}

	/**
	 * Repeatedly swap connections between nodes so that n1 -> n2
	 * becomes n2 -> n1; recursively
	 * Requires O(n) space and O(n) time
	 */
	public void reverseListSwapConnectionsRecursive() {
		head = reverseListSwapConnectionsRecursive(head, null);
	}

	private Node<MyType> reverseListSwapConnectionsRecursive(Node<MyType> cur, Node<MyType> prev) {
		// if cur is null, we're done
		if (cur == null)
			return null;
		// if at the end of list, point cur to prev
		if (cur.next == null) {
			cur.next = prev;
			return cur;
		}
		//save the successor
		Node<MyType> temp = cur.next;
		//point next to prev
		cur.next = prev;
		// prev is current cur in the next iteration
		// cur is current temp in the next iteration 
		return reverseListSwapConnectionsRecursive(temp, cur);
	}
}
