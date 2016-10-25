package c01_sorting;
/*
 * Modification to insertion sort: does insertion sort on elements situated at gap-distance
 * apart
 * Decrease gap = (gap-1) / 3 up to gap = 1 (can have gap = n / 2, gap = gap / 2)
 * Important: numbers in the interval sequence are relatively prime (no common divisors except 1)
 * 
 * Analysis: faster than insertion sort and slower than quick-sort (n^(3/2) worst case?)
 * Space complexity O(1) CONSTANT
 * NOT STABLE
 * IN-PLACE
 * 
 * When gap is large, the number of items per pass is small, and items move long distances. 
 * This is very efficient. As gap grows smaller, the number of items per pass increases, 
 * but the array is almost sorted (so insertion sort is more efficient)
 */

public class ShellSort {
	public static void sort(int[] A) {
		// the distance between the elements
		int gap = 1;

		// increase gap to the original size
		while (gap < A.length / 3)
			gap = 3 * gap + 1;

		// decrease gap after each subsequent sort
		while (gap > 0) {
			// this is insertion sort, but with distance gap between elements
			for (int i = gap; i < A.length; i++) {
				int val = A[i];
				int j = i - gap;
				while (j >= 0 && A[j] > val) {
					A[j + gap] = A[j];
					j -= gap;
				}
				A[j + gap] = val;
			}
			// decrease the value of gap
			gap = (gap - 1) / 3;
		}
	}

}
