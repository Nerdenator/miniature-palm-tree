package c01_sorting;

/**
 * Compare consecutive elements and swap if necessary.
 * Best algorithm observes that elements after last swap never get moved
 * again so it only looks up to that point in subsequent swaps.
 * Requires O(n^2) swaps in worst case.
 * 
 * Worst-case performance: O(n^2) - array in reverse order
 * Best-case performance: O(n) - already sorted array
 * Average performance: O(n^2)
 * Worst-case space complexity: O(1) constant
 *
 * STABLE - maintains relative order of items with equal keys
 * IN-PLACE - transforms input using a data structure with a small, constant
 * amount of extra storage space
 * 
 * Invariant = everything after the last swap is sorted. In the simpler
 * algorithm, everything up to the last element is sorted.
 * 
 * @author adina
 */

public class BubbleSort {

	/**
	 * This is the non-optimized version that doesn't check for a stop.
	 * Each outer loop bubbles up the highest element of the array to the end.
	 * 
	 * @param array - array to sort
	 */
	public static void sortBasic(int[] array) {
		int n = array.length;
		for (int t = 0; t < n; t++) {
			for (int i = 0; i < n - 1; i++) {
				if (array[i] > array[i + 1]) {
					// swap array elements between i and i+1
					Swaps.swapTemp(array, i, i + 1);
				}
			}
		}
	}

	/**
	 * This version checks if no swap occurs during the last run, in which case
	 * it stops since the list is sorted
	 * 
	 * @param array - array to sort
	 */
	public static void sortImproved(int[] array) {
		int n = array.length;
		boolean swapped = true;
		while (swapped) {
			// no swap in this iteration, yet
			swapped = false;
			for (int i = 0; i < n - 1; i++) {
				if (array[i] > array[i + 1]) {
					// swap items
					Swaps.swapTemp(array, i, i + 1);
					swapped = true;
				}
			}
			// we've already placed the largest element at the end so we don't
			// need to look there
			n--;
		}
	}

	/**
	 * This version updates the n to the location of the last swap, since no
	 * swap will occur after last swap in subsequent iterations
	 * 
	 * @param array - array to sort
	 */
	public static void sortBest(int[] array) {
		int n = array.length;
		int newn = n;
		boolean swapped = true;

		do {
			// no swap occurred yet
			swapped = false;
			for (int i = 0; i < n - 1; i++) {
				// num_comparisons <= (n-1)+(n-1)+..+1 = n*(n-1)/2
				if (array[i] > array[i + 1]) {
					// swap elements and keep track of current location
					Swaps.swapTemp(array, i, i + 1);
					newn = i + 1;
					swapped = true;
				}
			}
			// nothing past the last swap needs to move
			n = newn;
		}
		// as long as items have been swapped
		while (swapped);
	}
}
