package c03_dynamic_programming;

import java.util.ArrayList;

//factorial
public class FactorialDP {
	public static int fact(int n) {
		ArrayList<Integer> f = new ArrayList<Integer>(n + 1);
		f.add(1);
		for (int i = 1; i < n + 1; i++) {
			f.add(f.get(i - 1) * i);
		}
		return f.get(n);
	}

	public static int factslow(int n) {
		if (n == 0)
			return 1;
		return n * factslow(n - 1);
	}

	public static void main(String[] args) {
		System.out.println(fact(10));
		System.out.println(factslow(10));
	}
}
