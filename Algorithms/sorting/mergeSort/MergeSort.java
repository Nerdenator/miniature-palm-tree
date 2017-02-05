package mergeSort;

/**
 * 
 * Split the array down the middle recursively and merge the two sorted halves
 * 
 * Takes O(n) space on arrays ( O(1) on linked lists )
 * Worst-case runtime: O(n log n)
 * 
 * @author adina
 *
 */
public class MergeSort {
	/**
	 * Total runtime: O(n log n)
	 * 
	 * @param A the array to sort
	 */
	public static void sort(int[] A) {
		sort(A, 0, A.length - 1);
	}

	/**
	 * Recursive merge sort method that runs in O(n log n),
	 * including the merge method
	 * 
	 * @param A the array to sort
	 * @param low the index to start from
	 * @param high the index to end at
	 */
	private static void sort(int[] A, int low, int high) {
		// should ideally stop in the middle
		if (low >= high)
			return;

		// index of array middle (prevent overflow)
		int mid = (high - low) / 2 + low;// (low + high) / 2;

		// sort first half
		sort(A, low, mid);

		// sort second half
		sort(A, mid + 1, high);

		// merge the two halves
		merge(A, low, mid, high);
	}

	/**
	 * Merge sorted arrays A[low ... mid] and A[mid+1 ... high] so that they're
	 * sorted in O(n) time and O(n) space
	 * 
	 * @param A the array to sort
	 * @param low lowest index
	 * @param mid middle index
	 * @param high highest index
	 */
	private static void merge(int[] A, int low, int mid, int high) {
		// index that goes through first array partition
		int idx1 = low;
		// index that goes through second array partition
		int idx2 = mid + 1;
		// index that goes through the entire array to get it in order
		int idx = low;

		// copy the array so that past changes in place don't affect future
		// changes
		int[] copyA = A.clone();

		// sort A given copyA and the indices
		// iterate in parallel until we run out of elements in
		// either A[low ... mid] or A[mid+1 ... high]
		while (idx1 <= mid && idx2 <= high) {
			// if the smaller value is in first half
			if (copyA[idx1] <= copyA[idx2]) {
				A[idx] = copyA[idx1];
				idx1++;
			}
			// if the smaller value is in second half
			else {
				A[idx] = copyA[idx2];
				idx2++;
			}
			// fill out next element
			idx++;
		}

		// copy what's left over from A[low ... mid]
		while (idx1 <= mid) {
			A[idx] = copyA[idx1];
			idx1++;
			idx++;
		}

		// copy what's left over from A[mid+1 ... high]
		while (idx2 <= high) {
			A[idx] = copyA[idx2];
			idx2++;
			idx++;
		}
	}

}
