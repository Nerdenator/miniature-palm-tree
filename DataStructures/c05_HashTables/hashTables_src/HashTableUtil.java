package hashTables_src;

/**
 * Utilities for use in hashTable. These are functions used in both open
 * addressing and separate chaining, including:
 * - hash functions
 * - find next prime
 * - find previous prime
 * - is prime
 * 
 * @author adina
 *
 */
public class HashTableUtil {

	////////////////// PRIME NUMBERS
	/**
	 * Get the previous prime. If the current number is too small, return 2
	 * 
	 * @param x
	 * @return
	 */
	public static int getPreviousPrime(int x) {
		if (isPrime(x))
			x--;
		// if it's even > 2, start with number before
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
	 * Get the next prime.
	 * 
	 * @param x
	 * @return
	 */
	public static int getNextPrime(int x) {
		if (x == 1)
			return 2;
		// if x is prime, still get the next number
		if (isPrime(x))
			x++;
		// if x is now even, start with next number
		if (x % 2 == 0)
			x++;

		// look at odd numbers greater than x
		while (!isPrime(x))
			x += 2;

		return x;
	}

	/**
	 * Is the current number prime?
	 * 
	 * @param x
	 * @return true if it's prime, false otherwise
	 */
	public static Boolean isPrime(int x) {
		// 2 is the first prime
		if (x < 2)
			return false;
		// 2 is the first prime
		if (x == 2)
			return true;
		// even numbers are not prime (except 2)
		if (x % 2 == 0)
			return false;

		// check if x is divisible by i, and if so it's not prime (check until
		// sqrt(x))
		for (int i = 2; i * i <= x; i++)
			if (x % i == 0)
				return false;

		return true;
	}

	///////////////// HASH FUNCTIONS

	// For Integer: val % capacity
	protected static int hashInt(int val, int capacity) {
		return val % capacity;
	}

	// stepSize = constant - (key % constant) where constant < capacity
	protected static int hashInt2(int val, int capacity) {
		int primeNumber = HashTableUtil.getPreviousPrime(capacity);
		return primeNumber - (val % primeNumber);
	}

	// For Character: (ASCII value of val) % capacity
	protected static int hashChar(char ch, int capacity) {
		return ch % capacity;
	}

	// stepSize = constant - (key % constant) where constant < capacity
	protected static int hashChar2(char ch, int capacity) {
		int primeNumber = HashTableUtil.getPreviousPrime(capacity);
		return primeNumber - (ch % primeNumber);
	}

	/*
	 * For Strings:
	 * --If var_i is the ASCII code of letter and n is the number of characters
	 * in alphabet:
	 * -----var_4 * n^4 + var_3 * n^3 + var_2 * n^2 + var_1 * n^1 + var_0 * n^0
	 * -----------------is equivalent to
	 * -----(((var_4 * n + var_3) * n + var_2) * n + var_1) * n + var_0
	 * 
	 * Simplified formula: n = 31, start with hash = 7
	 */
	protected static int hashString(String str, int capacity) {
		int hash = 7;
		for (int i = 0; i < str.length(); i++)
			// mod on the way prevents overflow
			hash = (hash * 31 + str.charAt(i)) % capacity;
		return hash;
	}

	/*
	 * hashvalue = prime - sum of (29*charAti) % prime
	 * where prime is a prime < capacity
	 */
	protected static int hashString2(String str, int capacity) {
		// store the sum of ascii numerical values
		int hashVal = 0;

		int primeNumber = HashTableUtil.getPreviousPrime(capacity);

		// add the values of all chars while multiplying each one with a prime
		// number
		for (int i = 0; i < str.length(); i++)
			hashVal = (29 * hashVal + str.charAt(i)) % primeNumber;

		// mod the hashed value with a prime smaller than the table size,
		// subtract that number
		// with the prime just used and return that value
		return primeNumber - hashVal;
	}
}
