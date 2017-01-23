package sorting_src;

public class Swaps {

	/**
	 * Using additional auxiliary variable (normal way to do it)
	 * 
	 * @param a array
	 * @param i first index to swap
	 * @param j second index to swap
	 */
	public static void swapTemp(int[] a, int i, int j) {
		if (i != j) {
			int temp = a[i];
			a[i] = a[j];
			a[j] = temp;
		}
	}

	/**
	 * In-place using arithmetic
	 * 
	 * @param a array
	 * @param i first index to swap
	 * @param j second index to swap
	 */
	public static void swapMath(int[] a, int i, int j) {
		if (i != j) {
			a[i] = a[i] + a[j];
			a[j] = a[i] - a[j];
			a[i] = a[i] - a[j];
		}
	}

	/**
	 * In-place using XOR (true only when inputs differ)
	 * 
	 * @param a array
	 * @param i first index to swap
	 * @param j second index to swap
	 */
	public static void swapXOR(int[] a, int i, int j) {
		if (i != j) {
			a[i] = a[i] ^ a[j];
			a[j] = a[i] ^ a[j];
			a[i] = a[i] ^ a[j];
		}
	}

}
