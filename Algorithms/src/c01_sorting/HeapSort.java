package c01_sorting;

import java.util.PriorityQueue;

/**
 * Using a Java PriorityQueue, which is a minimum heap.
 * The head of the pq is always the minimum element in the heap.
 * Time: O(n logn), Space: O(n)
 * 
 * 
 * @author adina
 *
 */

public class HeapSort {

	public static void sort(int[] A) {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		// build the heap: O(nlogn)
		for (int i = 0; i < A.length; i++) //O(n)
			pq.add(A[i]); //O(logn)

		//now, the minimum element is always the first dequeued
		int i = 0;
		// O(n)
		while (!pq.isEmpty())
			A[i++] = pq.poll();
	}
}
