package quickSort;

/**
 * Recursively partition the sub-sets by choosing a pivot value and creating
 * sub-sets by moving values < pivot left and values >= pivot right until there
 * are no more subsets to split. Combine subsets in a sorted set.
 * 
 * Takes O(n) or O(log n) space depending on implementation (naive or in-place)
 * Average case runtime is O(n log n), though worst case is O(n^2)
 */
public class QuickSort {

	/**
	 * Public method to call with only array parameter. Naive quicksort
	 * (not in-place).
	 * 
	 * @param array - the array to sort
	 */
	public static void sortNaive(int[] array) {
		sortNaive(array, array.length);
	}

	/**
	 * Naive quicksort, not in-place. Uses two extra arrays for the left and
	 * right subsets, so requires O(n) extra space.
	 * 
	 * @param array - the array to sort
	 * @param length - the length of the current subset (since using arrays of
	 *            same, original, length)
	 */
	private static void sortNaive(int[] array, int length) {
		// stop when the subset is small
		if (length <= 1)
			return;

		// choose the pivot as the value of the middle element in the array
		int pIdx = length / 2;
		int pVal = array[pIdx];

		// will add the elements < the pivot to aSmall
		int[] aSmall = new int[length];
		int smallLen = 0;

		// will add the elements >= the pivot to aLarge
		int[] aLarge = new int[length];
		int largeLen = 0;

		// create aSmall and aLarge
		for (int i = 0; i < length; i++) {
			// don't move the pivot
			if (i != pIdx) {
				// elements smaller than pivot value go to aSmall
				if (array[i] < pVal)
					aSmall[smallLen++] = array[i];
				// elements larger than or equal to pivot value go to aLarge
				else
					aLarge[largeLen++] = array[i];
			}
		}

		// sort the two subsets recursively
		sortNaive(aSmall, smallLen);
		sortNaive(aLarge, largeLen);

		// copy everything back into the main array, in sorted order
		// left side
		for (int i = 0; i < smallLen; i++)
			array[i] = aSmall[i];
		// pivot
		array[smallLen] = pVal;
		// right side
		for (int i = 0; i < largeLen; i++)
			array[i + smallLen + 1] = aLarge[i];
	}

	/**
	 * Public method to call with only array parameter. Advanced quicksort
	 * (in-place quicksort).
	 * 
	 * @param array - the array to sort
	 */
	public static void sort(int[] array) {
		sort(array, 0, array.length - 1);
	}

	/**
	 * This is an improved version of quicksort which does the sorting in place
	 * (no additional space required except for call-stack space)
	 * O(log n) call stack space on average.
	 * 
	 * @param array - the array to sort
	 * @param left - beginning of current subset in array
	 * @param right - end of current subset in array
	 */
	private static void sort(int[] array, int left, int right) {
		// reached the end of array
		if (left >= right)
			return;

		// index at which we're partitioning, middle of array
		int pIdx = (right - left) / 2 + left; // = (left+right)/2

		// partition first so we have
		// array = { [items < pivot_val], pivot_val, [items >= pivot_val] }
		int newPIdx = partition(array, left, right, pIdx);

		// sort left side (up to the new pivot)
		sort(array, left, newPIdx - 1);
		// sort right side (after the new pivot)
		sort(array, newPIdx + 1, right);
	}

	/**
	 * Given a pivot index, move things that are >= pivot to the right and
	 * things that are < pivot to the left, inside the array.
	 * This works in-place and uses no extra arrays O(1)
	 * 
	 * @param array - the array to sort
	 * @param left - beginning of current subset in array
	 * @param right - end of current subset in array
	 * @param pIdx - original index of pivot in array
	 * @return the final index of the pivot in the array, after moving elements
	 */
	private static int partition(int[] array, int left, int right, int pIdx) {
		// get the value of the pivot
		int pVal = array[pIdx];

		// move the pivot value to the rightmost position
		switchPositions(array, pIdx, right);

		// start the new pivot index search from the left
		int newPIdx = left;

		// move everything smaller than pVal to the first part of the array
		// find the correct pivot index by counting how many moved
		for (int i = left; i < right; i++)
			if (array[i] < pVal) {
				switchPositions(array, newPIdx, i);
				newPIdx++;
			}

		// switch the pivot (from last position) with the calculated index
		switchPositions(array, newPIdx, right);

		// return the actual position of the pivot
		return newPIdx;
	}

	/**
	 * Switch the element at index x with the element at index y in array
	 * 
	 * @param array - the array
	 * @param x - first index
	 * @param y - second index
	 */
	private static void switchPositions(int[] array, int x, int y) {
		int temp = array[x];
		array[x] = array[y];
		array[y] = temp;
	}
}
