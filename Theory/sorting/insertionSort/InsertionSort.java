package insertionSort;

/**
 * Starting with the second element, take each element out and insert it in its
 * correct location while moving all elements that are larger than it one
 * position to the right.
 * 
 * Worst-case runtime: O(n^2)
 * 
 * @author adina
 */

public class InsertionSort {
	/**
	 * Start with empty sorted array and insert items in the appropriate place
	 * -> insert to the left of the current index
	 * 
	 * @param array - array to sort
	 */
	public static void sort(int[] array) {
		for (int i = 1; i < array.length; i++) {
			// want to insert val = array[i] in the
			// correct position between 0 and i-1
			int val = array[i];
			// move all greater elements to the right
			int j = i - 1;
			while (j >= 0 && array[j] > val) {
				array[j + 1] = array[j];
				j--;
			}
			// move val to the correct identified spot
			array[j + 1] = val;
		}
	}
}
