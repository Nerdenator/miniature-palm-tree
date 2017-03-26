package binarySearch;

/**
 * Search algorithm that finds position of a target value in a sorted array.
 * 
 * Worst-case performance O(log n)
 * 
 * @author Adina
 *
 */
public class BinarySearch {

	/**
	 * Search recursively in O(log n) time
	 * 
	 * @param array - array to search in
	 * @param x - element to search for
	 * @return position of x in array or -1 if not found
	 */
	public static int binarySearchRecursive(int[] array, int x) {
		return binarySearchRecursive(array, 0, array.length - 1, x);
	}

	/**
	 * Search recursively in progressively smaller halves of the array
	 * 
	 * @param array - array to search in
	 * @param left - left-index in array we're looking past
	 * @param right - right-index in array we're looking up to
	 * @param x - element to search for
	 * @return position of x in array or -1 if not found
	 */
	private static int binarySearchRecursive(int[] array, int left, int right, int x) {
		// element not found
		if (left > right)
			return -1;

		// middle location
		int mid = (right - left) / 2 + left; // (left + right) / 2

		// found the element in the middle
		if (array[mid] == x)
			return mid;

		// look in first half
		if (array[mid] > x)
			return binarySearchRecursive(array, left, mid - 1, x);

		// look in second half
		if (x > array[mid])
			return binarySearchRecursive(array, mid + 1, right, x);

		// element not found
		return -1;
	}

	/**
	 * Search iteratively in O(log n) time
	 * 
	 * @param array - array to search in
	 * @param x - element to search for
	 * @return position of x in array or -1 if not found
	 */
	public static int binarySearchIterative(int[] array, int x) {
		int left = 0;
		int right = array.length - 1;

		while (left <= right) {
			// middle element
			int mid = (right - left) / 2 + left; // (left + right) / 2

			// continue looking in the first half
			if (x < array[mid])
				right = mid - 1;

			// continue looking in the second half
			else if (x > array[mid])
				left = mid + 1;

			// found the element in the middle
			else
				return mid;
		}
		return -1;
	}
}
