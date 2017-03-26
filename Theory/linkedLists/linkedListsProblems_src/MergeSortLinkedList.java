package linkedListsProblems_src;

import linkedLists_src.SinglyLinkedList;
import linkedLists_util.Node;

/**
 * Merge sort two singly linked lists of integers
 * 
 * @author adina
 */
public class MergeSortLinkedList {
	/**
	 * Merge sort method (which is recursive) for the list
	 * Utilizes recursive merge if isRecursiveMerge = true and iterative merge
	 * otherwise
	 * 
	 * @param list: the list to sort
	 * @param isRecursiveMerge: use recursive merge(true) or iterative
	 *            merge(false)?
	 */
	public static void mergeSort(SinglyLinkedList<Integer> list, boolean isRecursiveMerge) {
		list.head = mergeSort(list.head, isRecursiveMerge);
	}

	/**
	 * Merge sort method (which is recursive) for the list starting at node cur
	 * Utilizes recursive merge if isRecursiveMerge = true and iterative merge
	 * otherwise
	 * 
	 * @param cur: the starting node of list to recursively merge
	 * @param isRecursiveMerge: use recursive merge(true) or iterative
	 *            merge(false)?
	 * @return the head of the sorted list
	 */
	private static Node<Integer> mergeSort(Node<Integer> cur, boolean isRecursiveMerge) {
		// base cases, empty or one-element list
		if (cur == null || cur.next == null)
			return cur;

		// split the list into two parts
		// keep track of the head of the first half of the list
		Node<Integer> n1 = cur;

		// this is the middle node, second list starts at middle.next
		Node<Integer> middle = findListMiddle(cur);

		// keep track of the head of the second half of the list
		Node<Integer> n2 = middle.next;

		// sever the list in 2 by cutting after middle
		middle.next = null;

		// sort first half
		n1 = mergeSort(n1, isRecursiveMerge);
		// sort second half
		n2 = mergeSort(n2, isRecursiveMerge);

		// merge
		if (isRecursiveMerge)
			// recursively
			return mergeRecursive(n1, n2);
		else
			// iteratively
			return mergeIterative(n1, n2);
	}

	/**
	 * Find the middle node of the list starting at cur
	 * 
	 * @param cur: the node to start at
	 * @return the node that is in the middle between cur and end of the list
	 */
	private static Node<Integer> findListMiddle(Node<Integer> cur) {
		// use two pointers, slow moves one-by-one, fast skips one
		Node<Integer> slow = cur;
		Node<Integer> fast = cur;

		// since fast moves faster, stop when the list ends
		while (fast.next != null) {
			// advance fast by 1
			fast = fast.next;
			// if there are still nodes in the list
			if (fast.next != null) {
				// advance fast by 1 (adding up to 2)
				fast = fast.next;
				// advance slow by 1
				slow = slow.next;
			}
		}
		// slow points at last node in first half of list starting at cur
		return slow;
	}

	/**
	 * Merge the two lists starting at nodes n1 and n2, iteratively
	 * 
	 * @param n1: start of first list
	 * @param n2: start of second list
	 * @return the head of the merged list
	 */
	private static Node<Integer> mergeIterative(Node<Integer> n1, Node<Integer> n2) {
		// nothing to merge
		if (n1 == null)
			return n2;
		// nothing to merge
		if (n2 == null)
			return n1;

		// the head of the merged list
		Node<Integer> head;

		if (n1.data <= n2.data) {
			// the head starts at first
			head = n1;
			// advance first
			n1 = n1.next;
		}

		else {
			// the head starts at second
			head = n2;
			// advance second
			n2 = n2.next;
		}

		// use to iterate
		Node<Integer> cur = head;

		// merge until we exhaust one of the lists
		while (n1 != null && n2 != null) {
			// select next from first list
			if (n1.data <= n2.data) {
				cur.next = n1;
				n1 = n1.next;
			}
			// select next from second list
			else {
				cur.next = n2;
				n2 = n2.next;
			}
			// advance cur
			cur = cur.next;
		}

		// done with first list, attach rest of second
		if (n1 == null)
			cur.next = n2;
		// done with second list, attach rest of first
		if (n2 == null)
			cur.next = n1;

		// the head is the start of the merged list
		return head;
	}

	/**
	 * Merge the two lists starting at n1 and at n2, recursively
	 * 
	 * @param n1: start of first list
	 * @param n2: start of second list
	 * @return the head of the merged list
	 */
	private static Node<Integer> mergeRecursive(Node<Integer> n1, Node<Integer> n2) {
		// nothing to merge
		if (n1 == null)
			return n2;
		// nothing to merge
		if (n2 == null)
			return n1;

		// the head of the recursively merged list
		Node<Integer> head;
		// select next from first list
		if (n1.data <= n2.data) {
			head = n1;
			// recurse on rest of list
			head.next = mergeRecursive(n1.next, n2);
		}
		// select next from second list
		else {
			head = n2;
			// recurse on rest of list
			head.next = mergeRecursive(n1, n2.next);
		}
		// the head of the merged list
		return head;
	}

}
