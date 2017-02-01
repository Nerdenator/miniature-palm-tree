package hashTables_src;

import linkedLists_src.LinkedListSinglyLinked;

/**
 * Use an array of linked lists at each bucket. The time for hash table
 * operations is the time to find the bucket (which is constant) plus the time
 * for the list operation.
 * 
 * ----------Average --Worst case
 * Space -----O(n) ------O(n)
 * Search ----O(1) ------O(n)
 * Insert ----O(1) ------O(n)
 * Delete ----O(1) ------O(n)
 * 
 * @author adina
 *
 * @param <MyType>
 */
public class HashTableSeparateChaining<MyType> implements HashTableInterface<MyType> {
	//every bucket has a list
	LinkedListSinglyLinked<MyType>[] table;//space is O(n)
	int capacity;
	int numEntries;

	@SuppressWarnings("unchecked")
	public HashTableSeparateChaining(int capacity) {
		//set the capacity to the previous prime
		this.capacity = HashTableUtil.getNextPrime(capacity);
		//instantiate the array of lists
		table = new LinkedListSinglyLinked[this.capacity];
		//instantiate the list in every bucket
		for (int i = 0; i < this.capacity; i++)
			table[i] = new LinkedListSinglyLinked<>();

		//for the load factor we need
		numEntries = 0;
	}

	public int getCapacity() {
		return capacity;
	}

	/**
	 * Display the hash table as
	 * 0: {list0}
	 * 1: {list1}
	 * ...
	 * 
	 * @return
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < capacity - 1; i++)
			sb.append(i + ": " + table[i].toString() + "\n");
		sb.append((capacity - 1) + ": " + table[capacity - 1].toString());
		return sb.toString();
	}

	/**
	 * Calculate the hash value (the bucket val falls into)
	 * 
	 * @param val
	 * @return the hash value of val
	 * @throws Exception
	 *             for types we didn't define it for
	 */
	private int hashFunc(MyType val) throws Exception {
		if (val instanceof Integer)
			return hashInt((Integer) val);

		else if (val instanceof Character)
			return hashChar((Character) val);

		else if (val instanceof String)
			return hashString((String) val);

		throw new Exception("Only support Integer and String");
	}

	// For Integer: val % capacity
	private int hashInt(int val) {
		return val % capacity;
	}

	// For Character: (ASCII value of val) % capacity
	private int hashChar(char ch) {
		return ch % capacity;
	}

	/* For Strings:
	 * --If var_i is the ASCII code of letter and n is the number of  characters in alphabet:
	 * -----var_4 * n^4 + var_3 * n^3 + var_2 * n^2 + var_1 * n^1 + var_0 * n^0
	 * -----------------is equivalent to
	 * -----(((var_4 * n + var_3) * n + var_2) * n + var_1) * n + var_0
	 * 
	 * Simplified formula: n = 31, start with hash = 7
	 */
	private int hashString(String str) {
		int hash = 7;
		for (int i = 0; i < str.length(); i++)
			// mod on the way prevents overflow
			hash = (hash * 31 + str.charAt(i)) % capacity;
		return hash;
	}

	/**
	 * Insert the value to the beginning of the list
	 * at the location computed by hashFunc
	 * 
	 * @param val
	 * @return the bucket number
	 * @throws Exception
	 */
	@Override
	public int insert(MyType val) throws Exception {
		//compute the bucket to insert into
		int bucketNumber = hashFunc(val);
		//add to the beginning of the list at bucket
		table[bucketNumber].addFirst(val);

		//for load factor
		numEntries++;
		return bucketNumber;
	}

	/**
	 * Find val's bucket in the table
	 * 
	 * @param val
	 * @return bucket of val, -1 otherwise
	 * @throws Exception
	 */
	@Override
	public int find(MyType val) throws Exception {
		// compute the bucket the value would be in
		int hashval = hashFunc(val);
		// find the value in the linked list at the bucket, if not null it was found
		if (table[hashval].find(val) != null)
			return hashval;

		return -1;
	}

	/**
	 * Delete the value from the table
	 * 
	 * @param val
	 * @return true if value was in table, false otherwise
	 * @throws Exception
	 */
	@Override
	public boolean delete(MyType val) throws Exception {
		// compute the bucket the value would be in
		int hashval = hashFunc(val);
		// delete the value from the linked list at the bucket, if not null it was found
		if (table[hashval].delete(val) != null) {
			numEntries--;
			return true;
		}

		return false;
	}

	@Override
	public double getLoadFactor() {
		return (double) numEntries / capacity;
	}
}
