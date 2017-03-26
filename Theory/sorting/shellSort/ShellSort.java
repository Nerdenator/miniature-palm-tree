package shellSort;

/**
 * Modification to insertion sort: does insertion sort on elements situated at
 * gap-distance apart. Idea is to start with a large gap value, and decrease
 * the gap upon each iteration, until gap = 1.
 * 
 * Best case: O(n log n).
 * Worst case: depends on gap sequence, around O(n^(3/2)).
 * 
 * @author adina
 */

public class ShellSort {

	/**
	 * Sort array using shell sort. Start with large value of gap and end at 1.
	 * 
	 * @param array - the array to sort
	 */
	public static void sort(int[] array) {
		// the distance between the elements (gap)
		int gap = 1;
		// increase gap to the original size
		while (gap < array.length / 3)
			gap = 3 * gap + 1;

		// decrease gap after each subsequent sort, until it's 1
		while (gap >= 1) {
			// this is insertion sort, but with distance gap between elements
			for (int i = gap; i < array.length; i++) {
				// want to insert val = array[i] in the
				// correct position between 0 and i-gap
				int val = array[i];
				int j = i - gap;
				// move all greater elements to the right
				while (j >= 0 && array[j] > val) {
					array[j + gap] = array[j];
					j -= gap;
				}
				// move val to the correct identified spot
				array[j + gap] = val;
			}
			// decrease the value of gap
			gap = (gap - 1) / 3;
		}
	}

}
