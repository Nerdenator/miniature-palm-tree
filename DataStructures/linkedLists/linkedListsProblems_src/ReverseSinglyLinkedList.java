package linkedListsProblems_src;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import linkedLists_src.SinglyLinkedList;
import linkedLists_util.Node;

/**
 * Reverse a Singly Linked List
 * 
 * @author adina
 *
 * @param <MyType>
 */
public class ReverseSinglyLinkedList<MyType> {
	/**
	 * Reverse a singly linked list using a stack (LIFO)
	 * Requires O(2n) space
	 * 
	 * @param list: the singly linked list
	 */
	public static <MyType> void reverseUsingStack(SinglyLinkedList<MyType> list) {
		// empty or one-element list, nothing to reverse
		if (list.head == null || list.head.next == null)
			return;

		// create a stack
		Stack<Node<MyType>> stack = new Stack<>();
		Node<MyType> cur = list.head;
		// push nodes to the stack
		while (cur != null) {
			stack.push(cur);
			cur = cur.next;
		}
		// set the head to the last node on the stack
		list.head = stack.pop();
		// starting with the new head
		cur = list.head;
		while (!stack.isEmpty()) {
			// remove next node on the stack
			cur.next = stack.pop();
			// add it after current node in the list
			cur = cur.next;
		}
		// end list
		cur.next = null;
	}

	/**
	 * Reverse a singly linked list using a queue (FIFO)
	 * Also requires O(2n) space
	 *
	 * @param list: the singly linked list
	 */
	public static <MyType> void reverseUsingQueue(SinglyLinkedList<MyType> list) {
		// empty or one-element list, nothing to reverse
		if (list.head == null || list.head.next == null)
			return;

		// the queue, as a Java LinkedList
		Queue<Node<MyType>> queue = new LinkedList<>();

		// add the nodes to the queue, starting with the head
		while (list.head != null) {
			queue.add(list.head);
			list.removeFirst();
		}
		// add the elements from the queue to the list (FIFO)
		while (!queue.isEmpty()) {
			Node<MyType> cur = queue.remove();
			list.addFirst(cur.data);
		}
	}

	/**
	 * Reverse a singly linked list by using another singly linked list
	 * Also requires O(2n) space
	 * 
	 * @param list: the singly linked list
	 */
	public static <MyType> void reverseUsingOtherList(SinglyLinkedList<MyType> list) {
		// empty or one-element list, nothing to reverse
		if (list.head == null || list.head.next == null)
			return;
		// create a new singly linked list
		SinglyLinkedList<MyType> helper = new SinglyLinkedList<>();

		// start with the head of the list
		Node<MyType> cur = list.head;
		// until list ends
		while (cur != null) {
			// remove from list, head to tail
			Node<MyType> node = list.removeFirst();
			// add to beginning of helper list
			helper.addFirst(node.data);
			// move cur to the beginning of list
			cur = list.head;
		}
		// set head of the list to the head of the helper list
		list.head = helper.head;
	}

	/**
	 * Use the same list: repeatedly add current head after original tail
	 * Requires O(n) space, O(n) time in singly linked
	 * 
	 * @param list: the singly linked list
	 */
	public static <MyType> void reverseRepeatedlyMovingHead(SinglyLinkedList<MyType> list) {
		// empty or one-element list, nothing to reverse
		if (list.head == null || list.head.next == null)
			return;

		// find the original last node in the list
		Node<MyType> last = list.head;
		while (last.next != null) {
			last = last.next;
		}

		// repeatedly remove head and add after original last node
		Node<MyType> cur = list.head;
		while (cur != last) {
			// remove the head
			list.removeFirst();
			// save the current successor of last
			Node<MyType> temp = last.next;
			// add it after cur
			cur.next = temp;
			// add cur after last
			last.next = cur;
			// set cur to (next) head
			cur = list.head;
		}
	}

	/**
	 * Repeatedly flip connections between nodes so that n1 -> n2
	 * becomes n2 -> n1; iteratively
	 * Requires O(n) space and O(n) time
	 * 
	 * @param list: the singly linked list
	 */
	public static <MyType> void reverseFlippingConnectionsIterative(SinglyLinkedList<MyType> list) {
		// empty or one-element list, nothing to reverse
		if (list.head == null || list.head.next == null)
			return;

		// current node, starting at head
		Node<MyType> cur = list.head;
		// node before cur
		Node<MyType> prev = null;

		// go through the list, reversing connections
		while (cur != null) {
			// save the successor
			Node<MyType> temp = cur.next;
			// point next to prev
			cur.next = prev;
			// save the current element in prev for the next iteration
			prev = cur;
			// advance cur (to saved successor)
			cur = temp;
		}
		// at the end prev points to last/first element and cur points to null
		list.head = prev;
	}

	/**
	 * Repeatedly flip connections between nodes so that n1 -> n2
	 * becomes n2 -> n1; recursively
	 * Requires O(n) space and O(n) time
	 * 
	 * @param list: the singly linked list
	 */
	public static <MyType> void reverseFlippingConnectionsRecursive(SinglyLinkedList<MyType> list) {
		list.head = reverseFlippingConnectionsRecursive(list.head, null);
	}

	/**
	 * Repeatedly flip connections between nodes so that n1 -> n2
	 * becomes n2 -> n1; recursively
	 * Requires O(n) space and O(n) time
	 * 
	 * @param cur: the current node
	 * @param prev: the previous node
	 */
	private static <MyType> Node<MyType> reverseFlippingConnectionsRecursive(Node<MyType> cur, Node<MyType> prev) {
		// if cur is null, we're done
		if (cur == null)
			return null;
		// if at the end of list, point cur to prev
		if (cur.next == null) {
			cur.next = prev;
			return cur;
		}
		// save the successor
		Node<MyType> temp = cur.next;
		// point next to prev
		cur.next = prev;
		// recursively flip for successor (temp) and current node (cur)
		return reverseFlippingConnectionsRecursive(temp, cur);
	}
}
