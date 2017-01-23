package dynamic_programming_src;

//Print Fibonacci Series (Dynamic Programming)
public class FibonacciDP {

	public static int fib(int n) {
		int f[] = new int[n + 1];
		f[0] = 1;
		f[1] = 1;
		for (int i = 2; i < n + 1; i++) {
			f[i] = f[i - 1] + f[i - 2];
		}
		return f[n];
	}

	public static int fibslow(int n) {
		if (n == 0 || n == 1)
			return 1;
		return fibslow(n - 1) + fibslow(n - 2);
	}

	public static void main(String[] args) {
		System.out.println(fib(40));
		System.out.println(fibslow(40));
	}
}
