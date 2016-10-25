package c07_Heaps;
/*
 *  Improves selection sort by using a logarithmic-time priority queue rather than a linear-time search. 
 *  Two steps: 
 *  (1) build a heap out of the data; 
 *  (2) extract max-heap, add it to the sorted array and re-heapify the data
 *  Can be done in place: the array can be split into two parts, the sorted array and the heap. 
 *  Advantage over quicksort: worst-case O(n log n) runtime. 
 *  
 *  IN-PLACE or NOT
 *  NOT STABLE
 */

public class MaxHeapSort {

	public static void sort(int[] A) {
		// uses a max-heap and it sorts in ascending order
		MaxHeap heapMax = new MaxHeap(A.length);
		// create heap: O(nlogn)
		heapMax.createHeapIterativeBubbleDown(A);

		int i = A.length - 1;
		//remove each item in sorted order in O(n log n)
		while (!heapMax.isEmpty()) {//O(n)
			A[i--] = heapMax.delete();//O(logn)
		}
	}

}
