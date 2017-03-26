package hashTable_util;

/**
 * Utilities for use in hashTable pertaining to prime numbers:
 * - find next prime
 * - find previous prime
 * - is given number prime
 * 
 * @author adina
 */
public class PrimeNumberOps {

	/**
	 * Get the next prime number
	 * 
	 * @param x: the number before which we want a prime
	 * @return a prime number > x
	 */
	public static int getNextPrime(int x) {
		if (x == 1)
			return 2;
		// if x is prime, still get the next number
		if (isPrime(x))
			x++;
		// if x is even, start with next number
		if (x % 2 == 0)
			x++;

		// look at odd numbers greater than x
		while (!isPrime(x))
			x += 2;

		return x;
	}

	/**
	 * Get the previous prime. If the current number is too small, return 2
	 * 
	 * @param x: the number before which we want a prime
	 * @return a prime number < x, or 2 if x is too small
	 */
	public static int getPreviousPrime(int x) {
		if (isPrime(x))
			x--;
		// if it's even and > 2, start with number before
		if (x % 2 == 0)
			x--;
		for (; x >= 2; x -= 2) {
			// if x is prime, return it
			if (isPrime(x))
				return x;
		}
		// 2 is the default
		return 2;
	}

	/**
	 * Is the current number prime?
	 * 
	 * @param x: the current number
	 * @return true if x is prime, false otherwise
	 */
	public static boolean isPrime(int x) {
		// 2 is the first prime
		if (x < 2)
			return false;
		// 2 is the first prime
		if (x == 2)
			return true;
		// even numbers are not prime (except 2)
		if (x % 2 == 0)
			return false;

		// check if x is divisible by i (i <= sqrt(x))
		for (int i = 2; i * i <= x; i++)
			// if x is divisible by i, then x is not prime
			if (x % i == 0)
				return false;
		// otherwise x is prime
		return true;
	}

}
