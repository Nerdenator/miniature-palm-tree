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
	 * Public method to call with only array parameter
	 * Total runtime: O(n log n)
	 * 
	 * @param array - the array to sort
	 */
	public static void sort(int[] array) {
		sort(array, 0, array.length - 1);
	}

	/**
	 * Recursive merge sort method that runs in O(n log n),
	 * including the merge method
	 * 
	 * @param array - the array to sort
	 * @param low - the index to start at
	 * @param high - the index to end at
	 */
	private static void sort(int[] array, int low, int high) {
		// should ideally stop in the middle
		if (low >= high)
			return;

		// index of array middle (prevent overflow)
		int mid = (high - low) / 2 + low;// (low + high) / 2;

		// sort first half
		sort(array, low, mid);

		// sort second half
		sort(array, mid + 1, high);

		// merge the two halves
		merge(array, low, mid, high);
	}

	/**
	 * Merge sorted arrays array[low ... mid] and array[mid+1 ... high] so that
	 * the new array is sorted. Takes O(n) time and O(n) space
	 * 
	 * @param array - the array to sort
	 * @param low - the index to start at
	 * @param mid - middle index
	 * @param high - the index to end at
	 */
	private static void merge(int[] array, int low, int mid, int high) {
		// index that goes through first array partition
		int idx1 = low;
		// index that goes through second array partition
		int idx2 = mid + 1;
		// index that goes through the entire array to get it in order
		int idx = low;

		// copy the array so that past changes in place
		// don't affect future changes
		int[] copyArray = array.clone();

		// sort array given copyArray and the indices
		// iterate in parallel until we run out of elements in
		// either array[low ... mid] or array[mid+1 ... high]
		while (idx1 <= mid && idx2 <= high) {
			// if the smaller value is in first half
			if (copyArray[idx1] <= copyArray[idx2]) {
				array[idx] = copyArray[idx1];
				idx1++;
			}
			// if the smaller value is in second half
			else {
				array[idx] = copyArray[idx2];
				idx2++;
			}
			// fill out next element of array
			idx++;
		}

		// copy what's left over from array[low ... mid]
		while (idx1 <= mid) {
			array[idx] = copyArray[idx1];
			idx1++;
			idx++;
		}

		// copy what's left over from array[mid+1 ... high]
		while (idx2 <= high) {
			array[idx] = copyArray[idx2];
			idx2++;
			idx++;
		}
	}

}
