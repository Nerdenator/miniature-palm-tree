package c04_LinkedLists;

/**
 * Merge sort two singly linked lists of integers
 * 
 * @author adina
 *
 */

public class MergeSortLinkedList extends LinkedListSinglyLinked<Integer> {
	//empty constructor
	public MergeSortLinkedList() {
		super();
	}

	// Constructor for one node
	public MergeSortLinkedList(Integer data) {
		super(data);
	}

	// Constructor to create a linked list from an array
	public MergeSortLinkedList(Integer[] array) {
		super(array);
	}

	/**
	 * Sort using iterative merge
	 */
	public void sortIterativeMerge() {
		head = mergeSort(head, false);
	}

	/**
	 * Sort using recursive merge
	 */
	public void sortRecursiveMerge() {
		head = mergeSort(head, true);
	}

	/**
	 * Merge sort method (which is recursive) for the list starting at node cur
	 * Utilizes recursive merge if isRecursiveMerge = true and iterative merge
	 * otherwise
	 * 
	 * @param cur
	 * @param isRecursiveMerge
	 * 
	 */
	private Node<Integer> mergeSort(Node<Integer> cur, boolean isRecursiveMerge) {
		// base cases, empty or one-element list
		if (cur == null || cur.next == null)
			return cur;
		/* 
		 * Split the list into two, by keeping track of the head 
		 * of the first half of the list and the head of the second 
		 * head of the list
		 */
		Node<Integer> first = cur;
		Node<Integer> middle = findListMiddle(cur);
		// second list starts at middle.next
		Node<Integer> second = middle.next;
		// sever the lists in 2 by cutting after middle
		middle.next = null;

		// sort first half
		first = mergeSort(first, isRecursiveMerge);
		// sort second half
		second = mergeSort(second, isRecursiveMerge);

		// merge 
		if (isRecursiveMerge)
			// recursively
			return mergeRecursive(first, second);
		else
			// iteratively
			return mergeIterative(first, second);
	}

	/**
	 * Find the middle node of the list starting at curfirst
	 * 
	 * @param curfirst
	 * @return the Node that is in the middle between cur and null
	 */
	private Node<Integer> findListMiddle(Node<Integer> curfirst) {
		// use two pointers, slow moves one-by-one, fast skips one
		Node<Integer> slow = curfirst;
		Node<Integer> fast = curfirst;
		while (fast.next != null) {
			//advance fast by 1
			fast = fast.next;
			if (fast.next != null) {
				//advance both fast and slow by 1
				fast = fast.next;
				slow = slow.next;
			}
		}
		// slow points at last node in first half of list starting at curfirst
		return slow;
	}

	/**
	 * Merge the two lists starting at first and at second, iteratively
	 * 
	 * @param first
	 * @param second
	 * @return the head of the merged list
	 */
	private Node<Integer> mergeIterative(Node<Integer> first, Node<Integer> second) {
		//nothing to merge
		if (first == null)
			return second;
		//nothing to merge
		if (second == null)
			return first;

		//the head of the merged list
		Node<Integer> newHead;

		//the head starts at first
		if (first.data <= second.data) {
			newHead = first;
			first = first.next;
		}
		//the head starts at second
		else {
			newHead = second;
			second = second.next;
		}

		// the node we use to iterate
		Node<Integer> cur = newHead;

		// merge until we exhaust one of the lists
		while (first != null && second != null) {
			// select next from first list
			if (first.data <= second.data) {
				cur.next = first;
				first = first.next;
			}
			// select next from second list
			else {
				cur.next = second;
				second = second.next;
			}
			//advance cur
			cur = cur.next;
		}

		// done with first list, copy rest of second
		if (first == null)
			cur.next = second;
		//done with second list, copy rest of first
		if (second == null)
			cur.next = first;

		return newHead;
	}

	/**
	 * Merge the two lists starting at first and at second, recursively
	 * 
	 * @param first
	 * @param second
	 * @return the head of the merged list
	 */
	private Node<Integer> mergeRecursive(Node<Integer> first, Node<Integer> second) {
		//nothing to merge
		if (first == null)
			return second;
		//nothing to merge
		if (second == null)
			return first;

		Node<Integer> cur;
		// select next from first list
		if (first.data <= second.data) {
			cur = first;
			cur.next = mergeRecursive(first.next, second);
		}
		// select next from second list
		else {
			cur = second;
			cur.next = mergeRecursive(first, second.next);
		}

		return cur;

	}

}
