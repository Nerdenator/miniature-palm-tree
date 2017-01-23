package sorting_src;
/*
 * Starting with the second element, take each element out, and insert it in its correct location
 * while moving all elements that are larger than it one position right
 *  
 * Insertion sort is an efficient way to add new elements to a pre-sorted list
 * especially suitable for sorting small data sets 
 * often used as a building block for other, more complicated sorting algorithms
 * 
 * Best bet in most situations where the amount of data is small or the data is almost sorted.
 * 
 * Is an incremental insertion technique: build up a complicated structure on n items by first 
 * building it on n-1 items and then making the necessary changes to add the last item.
 * 
 * Insertion into a balanced search tree takes O(log n) per operation, or a total of O(n log n) 
 * to construct the tree. An in-order traversal reads through the elements in sorted order to 
 * complete the job in linear time!
 */

public class InsertionSort {
	// start with empty sorted list and insert items in the appropriate place
	// insert to the left of the current index
	public static void sort(int[] A) {
		for (int i = 1; i < A.length; i++) {
			// want to insert val = A[i] in the correct position between 0 and
			// i-1
			int val = A[i];
			// move all greater elements to the right
			int j = i - 1;
			while (j >= 0 && A[j] > val) {
				A[j + 1] = A[j];
				j--;
			}
			// move val to the correct identified spot
			A[j + 1] = val;
		}
	}
}
