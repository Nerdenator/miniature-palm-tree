package sorting_src;

/**
 * Compare consecutive elements and swap if necessary.
 * Requires O(n^2) swaps in worst case.
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

		// repeat as long as items have been swapped
		while (swapped) {
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

	}
}
