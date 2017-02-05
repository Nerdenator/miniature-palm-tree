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
	 * Start with empty sorted list and insert items in the appropriate place
	 * -> insert to the left of the current index
	 * 
	 * @param A array to sort
	 */
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
