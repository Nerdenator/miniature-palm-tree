package heapSort;

import java.util.PriorityQueue;

/**
 * Use a Java PriorityQueue, which is a minimum heap. The head of the pq is
 * always the minimum element in the heap.
 * 
 * Time: O(n log n), Space: O(n) naive case
 * (O(1) best case, see MaxHeapSort in DataStructures/heaps)
 * 
 * @author adina
 */

public class HeapSort {
	/**
	 * Sort using a heap (Java Priority queue).
	 * Additional space: O(n) for pq. Runtime: O(n log n)
	 * 
	 * @param array - the array to sort
	 */
	public static void sortNaive(int[] array) {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();

		// build the heap: O(nlogn)
		for (int i = 0; i < array.length; i++) // O(n)
			pq.add(array[i]); // O(logn)

		// now, the minimum element is always the first dequeued
		int i = 0;

		// O(n), add each dequeued element to next array element
		while (!pq.isEmpty())
			array[i++] = pq.poll();
	}
}
