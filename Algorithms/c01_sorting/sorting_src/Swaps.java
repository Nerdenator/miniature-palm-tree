package sorting_src;

public class Swaps {

	public static void swapXOR(int[] a, int i, int j) {// in place with XOR
		if (i != j) {
			a[i] = a[i] ^ a[j];
			a[j] = a[i] ^ a[j];
			a[i] = a[i] ^ a[j];
		}
	}

	public static void swapTemp(int[] a, int i, int j) {// the normal way to do
														// it
		if (i != j) {
			int temp = a[i];
			a[i] = a[j];
			a[j] = temp;
		}
	}

	public static void swapMath(int[] a, int i, int j) {// in place with
														// arithmetics
		if (i != j) {
			a[i] = a[i] + a[j];
			a[j] = a[i] - a[j];
			a[i] = a[i] - a[j];
		}
	}

}
