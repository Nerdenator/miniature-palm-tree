package c01_sorting;
/*
 * Compare consecutive elements and swap if necessary
 * Best algorithm observes that elements after the last swap never get moved again 
 * so it only looks up to that point in subsequent swaps
 * Requires O(n^2) swaps in worst case
 * Analysis: runs in O(n^2) time in worst case (array in reverse order), 
 *           O(n) time if array is sorted
 * Space: O(1) CONSTANT
 * STABLE
 * IN-PLACE
 * 
 * Invariant = everything after the last swap is sorted (newn)
 */

public class BubbleSort {

	// this is the non-optimized version that doesn't check for a stop
	public static void sortBasic(int[] array) {
		int n = array.length;
		for (int t = 0; t < n; t++) {
			for (int i = 0; i < n - 1; i++) {
				if (array[i] > array[i + 1]) {
					Swaps.swapTemp(array, i, i + 1);
				}
			}
		}
	}

	// this version checks and stops when you stop switching in a loop
	public static void sortImproved(int[] array) {
		int n = array.length;
		boolean swapped = true;
		while (swapped) {
			swapped = false;
			for (int i = 0; i < n - 1; i++) {
				if (array[i] > array[i + 1]) {
					Swaps.swapTemp(array, i, i + 1);
					swapped = true;
				}
			}
			// we've already placed the largest element at the end so we don't
			// need to look there
			n--;
		}
	}

	// this version updates the n to the location of the last switch
	public static void sortBest(int[] array) {
		int n = array.length;
		int newn = n;
		boolean swapped = true;

		do {
			swapped = false;
			for (int i = 0; i < n - 1; i++) {
				if (array[i] > array[i + 1]) {
					// num_comparisons <= (n-1)+(n-1)+..+1 = n*(n-1)/2
					Swaps.swapTemp(array, i, i + 1);
					newn = i + 1;
					swapped = true;
				}
			}
			// nothing past the last swap needs to move
			n = newn;
		} while (swapped);
	}
}
