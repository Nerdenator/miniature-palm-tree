package heaps_src;

/**
 * Improvement upon selection sort by using a heap. Two steps:
 * (1) build a heap out of the data;
 * (2) extract max-heap, add it to the sorted array and re-heapify the data
 * Time: O(n log n), Space: O(1)
 * 
 * @author adina
 *
 */
public class MaxHeapSort {
	/**
	 * Sort by heapifying the array and then moving the max elements around to
	 * get a sorted array
	 * O(n log n)
	 * 
	 * @param array - the array to sort
	 */
	public static void sort(int[] array) {
		// use iterative approach everywhere
		char type = 'i';
		// uses a max-heap and it sorts in ascending order
		MaxHeap heapMax = new MaxHeap(array.length);
		// create heap: O(n) if using heapDown!
		heapMax.createHeapIterative_HeapDown(array, type);

		// start from the last element
		int i = array.length - 1;
		// remove each item in sorted order in O(n log n) and set it to the end
		// of the array
		while (!heapMax.isEmpty()) {// O(n)
			array[i--] = heapMax.delete(type);// O(log n)
		}
	}

}
