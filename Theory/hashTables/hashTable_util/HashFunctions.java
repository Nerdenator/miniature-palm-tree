package hashTable_util;

/**
 * Utilities for use in hashTable pertaining to hash functions.
 * 
 * @author adina
 */
public class HashFunctions {

	/**
	 * Hash an Integer.
	 * 
	 * @param val: value to hash
	 * @param capacity: table size
	 * @return val % capacity
	 */
	public static int hashInt(int val, int capacity) {
		return val % capacity;
	}

	/**
	 * Hash an integer for double hashing stepSize
	 * 
	 * @param val: value to hash
	 * @param capacity: table size
	 * @return stepSize = constant - (key % constant),
	 *         where constant is a prime < capacity
	 */
	public static int hashInt2(int val, int capacity) {
		int primeNumber = PrimeNumberOps.getPreviousPrime(capacity);
		return primeNumber - (val % primeNumber);
	}

	/**
	 * Hash a character.
	 * 
	 * @param ch: character to hash
	 * @param capacity: table size
	 * @return (ASCII value of ch) % capacity
	 */
	public static int hashChar(char ch, int capacity) {
		return ch % capacity;
	}

	/**
	 * Hash a character for double hashing stepSize
	 * 
	 * @param ch: character to hash
	 * @param capacity: table size
	 * @return stepSize = constant - (key % constant),
	 *         where constant is a prime < capacity and key = ASCII value of ch
	 */
	public static int hashChar2(char ch, int capacity) {
		int primeNumber = PrimeNumberOps.getPreviousPrime(capacity);
		return primeNumber - (ch % primeNumber);
	}

	/**
	 * Hash a String.
	 * If var_i is the ASCII code of letter and n is the number of characters
	 * in alphabet: var_4 * n^4 + var_3 * n^3 + var_2 * n^2 + var_1 * n^1 +
	 * var_0 * n^0
	 * --is equivalent to
	 * (((var_4 * n + var_3) * n + var_2) * n + var_1) * n + var_0
	 * Simplified formula: n = 31, start with hash = 7
	 * 
	 * @param str: string to hash
	 * @param capacity: table size
	 * @return the hash value
	 */
	public static int hashString(String str, int capacity) {
		int hash = 7;
		for (int i = 0; i < str.length(); i++)
			// mod on the way prevents overflow
			hash = (hash * 31 + str.charAt(i)) % capacity;
		return hash;
	}

	/**
	 * Hash a string for double hashing stepSize
	 * 
	 * @param str: string to hash
	 * @param capacity: table size
	 * @return stepSize = constant - (sum for all i ((29*charAt(i))) %
	 *         constant), where constant is a prime < capacity
	 */
	public static int hashString2(String str, int capacity) {
		// store the sum of ascii numerical values
		int hashVal = 0;

		int primeNumber = PrimeNumberOps.getPreviousPrime(capacity);

		// add the values of all chars while multiplying each one with prime=29
		for (int i = 0; i < str.length(); i++)
			hashVal = (29 * hashVal + str.charAt(i)) % primeNumber;

		return primeNumber - hashVal;
	}
}
