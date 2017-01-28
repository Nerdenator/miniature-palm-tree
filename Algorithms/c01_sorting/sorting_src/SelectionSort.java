package sorting_src;

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
	 * Sort the array in O(n^2) time
	 * 
	 * @param A the array to be sorted
	 */
	public static void sort(int[] A) {
		int minVal;
		int minInd;

		for (int j = 0; j < A.length - 1; j++) {
			// find the minimum value and index in the remainder of the array
			// (to the right of j)
			minVal = A[j];
			minInd = j;
			for (int i = j + 1; i < A.length; i++) {
				// num_comparisons = (n-1) + (n-1) +..+ 1 = n * (n-1) / 2
				if (minVal > A[i]) {
					minVal = A[i];
					minInd = i;
				}
			}
			// move the minimum to the current position
			// num_swaps < n
			int aux = A[minInd];
			A[minInd] = A[j];
			A[j] = aux;
		}
	}

}
