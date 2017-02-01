package sorting_src;

/*
 * Choose a pivot value from a data set and split the set into two subsets: 
 * s1 < pivot and s2 >= pivot. The pivot/split process is recursively applied to each subset 
 * until there are no more subsets to split. 
 * The results are combined to form the final sorted set.
 * 
 * Analysis: depends on choice of pivot.
 * If pivot is close to middle, halves the array on every recursive call, 
 * so best runtime is O(nlogn)
 * If pivot is MINIMUM element, then every successive call is on n-1 elements so 
 * runtime is O(n^2)
 * 
 * Average case is O(nlogn)
 * Space O(1) constant
 * 
 * NOT STABLE
 * Can be either in place or not (depends on partition)
 */
public class QuickSort {

	// basic quicksort, uses 2 extra arrays
	// requires O(n) extra space
	public static void sortSimple(int[] A) {
		sortSimple(A, A.length);
	}

	private static void sortSimple(int[] A, int length) {
		if (length <= 1)
			return;

		int pivot_ind = length / 2;
		int pivot_val = A[pivot_ind];

		// add the elements < the pivot to aSmall
		int[] aSmall = new int[length];
		int aSmallLen = 0;

		// and the elements >= the pivot to aLarge
		int[] aLarge = new int[length];
		int aLargeLen = 0;

		for (int i = 0; i < length; i++) {
			if (i != pivot_ind) {
				if (A[i] <= pivot_val)
					aSmall[aSmallLen++] = A[i];
				else
					aLarge[aLargeLen++] = A[i];
			}
		}

		// sort the subsets
		sortSimple(aSmall, aSmallLen);
		sortSimple(aLarge, aLargeLen);

		// copy everything back:

		// left side
		for (int i = 0; i < aSmallLen; i++)
			A[i] = aSmall[i];

		// pivot
		A[aSmallLen] = pivot_val;

		// right side
		for (int i = 0; i < aLargeLen; i++)
			A[i + aSmallLen + 1] = aLarge[i];
	}

	// This is an improved version of quicksort
	// which does the sorting in place (no additional space required)
	public static void sort(int[] A) {
		sort(A, 0, A.length - 1);
	}

	private static void sort(int[] A, int left, int right) {
		if (left >= right)
			return;

		// index at which we're partitioning, middle of array
		// same as (left + right) / 2; prevents overflow
		int pivot_ind = (right - left) / 2 + left;

		// partition first so we have
		// A = {[items < pivot_val], pivot_val, [items >= pivot_val]}
		int p = partition(A, left, right, pivot_ind);

		// sort left side
		sort(A, left, p - 1);
		// sort right side
		sort(A, p + 1, right);
	}

	// given a pivot move things that are >= pivot to the right and
	// things that are < pivot to the left
	// in place, no extra arrays
	private static int partition(int[] A, int left, int right, int pivot_ind) {

		int pivot_val = A[pivot_ind];

		int pivot_index = left;

		// move the pivot value to the rightmost position
		Swaps.swapTemp(A, pivot_ind, right);

		// move everything smaller than pivot_val to the first part of the array
		// find the correct pivot index by counting how many moved
		for (int i = left; i < right; i++)
			if (A[i] < pivot_val) {
				Swaps.swapTemp(A, pivot_index, i);
				pivot_index++;
			}

		// switch the pivot with the current index
		Swaps.swapTemp(A, pivot_index, right);

		// return the actual position of the pivot
		return pivot_index;
	}
}
