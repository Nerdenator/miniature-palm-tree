package selectionSort;

/**
 * For each current element of the array, find the minimum element to the right
 * of it and swap it with the current element
 * 
 * Worst-case runtime: O(n^2)
 * 
 * @author adina
 */

public class SelectionSort {

	/**
	 * Sort the array in O(n^2) time by swapping the minimum element to the
	 * right with current element
	 * 
	 * @param array - the array to be sorted
	 */
	public static void sort(int[] array) {
		// keep track of the minimum value and the index of it
		int minVal;
		int minInd;

		for (int j = 0; j < array.length - 1; j++) {
			// find the minimum value and index in the remainder of the array
			// (to the right of j)
			minVal = array[j];
			minInd = j;
			for (int i = j + 1; i < array.length; i++) {
				// num_comparisons = (n-1) + (n-1) +..+ 1 = n * (n-1) / 2
				if (minVal > array[i]) {
					minVal = array[i];
					minInd = i;
				}
			}
			// move the minimum to the current position
			// num_swaps < n
			int aux = array[minInd];
			array[minInd] = array[j];
			array[j] = aux;
		}
	}
}
