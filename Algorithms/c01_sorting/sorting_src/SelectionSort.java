package sorting_src;
/*
 * Go through the entire array 
 * Find the minimum element in the rest of the array and swap it with the current element
 * 
 * Analysis: two fors, from 0 to n-1 => O(n^2)
 * Space: O(1) CONSTANT
 * 
 * NOT STABLE (unless instead of swapping min, move all elements up and insert min in first position
 *    which would be O(n^2) for arrays (but better if insertion is easy like in lists) )
 * IN-PLACE
 * Need to scan entire array even if input is perfectly sorted
 * Advantage: requires at most n-1 swaps
 * 
 * Invariant = everything before the current position (j) is sorted
 * Minimizes the number of swaps, but the number of comparisons is still high. 
 * 
 * This sort might be useful when the amount of data is small and swapping 
 * data items is very time-consuming compared with comparing them.
 */

public class SelectionSort {

	public static void sort(int[] A) {
		int min_val;
		int min_ind;

		for (int j = 0; j < A.length - 1; j++) {
			// find the minimum value and index in the remainder of the array
			// (to the right of j)
			min_val = A[j];
			min_ind = j;
			for (int i = j + 1; i < A.length; i++) {
				// num_comparisons = (n-1) + (n-1) +..+ 1 = n * (n-1) / 2
				if (min_val > A[i]) {
					min_val = A[i];
					min_ind = i;
				}
			}
			// move the minimum to the current position
			Swaps.swapTemp(A, min_ind, j); // num_swaps < n
		}
	}

}
