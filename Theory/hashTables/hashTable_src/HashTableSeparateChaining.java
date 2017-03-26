package hashTable_src;

import java.util.LinkedList;

import hashTable_util.HashFunctions;
import hashTable_util.InterfaceHashTable;
import hashTable_util.PrimeNumberOps;

/**
 * Use an array of linked lists at each bucket for separate chaining.
 * Average time: O(1) | Worst time: O(n)
 * 
 * @author adina
 *
 * @param <MyType>
 */
public class HashTableSeparateChaining<MyType> implements InterfaceHashTable<MyType> {
	// every bucket is a list
	LinkedList<MyType>[] table;// space is O(n)

	// the size of the table
	int capacity;
	// number of entries, used to calculate loadFactor
	int numEntries;

	/**
	 * Constructor: create a hash table given capacity
	 * 
	 * @param capacity: table capacity is the next prime number after this
	 */
	@SuppressWarnings("unchecked")
	public HashTableSeparateChaining(int capacity) {
		// set the capacity to the next prime
		this.capacity = PrimeNumberOps.getNextPrime(capacity);
		// initialize the number of entries
		this.numEntries = 0;

		// instantiate the array of lists
		this.table = new LinkedList[this.capacity];
		// instantiate each list
		for (int i = 0; i < this.capacity; i++)
			this.table[i] = new LinkedList<>();
	}

	/**
	 * Retrieve the size of the table
	 * 
	 * @return the hash table's capacity
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * Display the hash table as
	 * 0: {list0}
	 * 1: {list1}
	 * ...
	 * 
	 * @return a string representation of the hash table
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < capacity - 1; i++)
			sb.append("{" + i + ": " + table[i].toString() + "}, ");

		sb.append("{" + (capacity - 1) + ": " + table[capacity - 1].toString() + "}");
		return sb.toString();
	}

	/**
	 * Calculate the hash value (the bucket val falls into)
	 * 
	 * @param val: the value we're hashing
	 * @return the hash value of val
	 * @throws Exception: only defined for Char, String and Integer
	 */
	private int hashFunc(MyType val) throws Exception {
		if (val instanceof Integer)
			return HashFunctions.hashInt((Integer) val, capacity);

		else if (val instanceof Character)
			return HashFunctions.hashChar((Character) val, capacity);

		else if (val instanceof String)
			return HashFunctions.hashString((String) val, capacity);

		throw new Exception("Only support Integer, Character and String");
	}

	/**
	 * Insert the value to the beginning of the list in the bucket computed by
	 * hashFunc in O(1)
	 * 
	 * @param val: value to insert into the table
	 * @return the bucket number into which we inserted
	 * @throws Exception
	 */
	@Override
	public int insert(MyType val) throws Exception {
		// increase number of entries
		numEntries++;

		// compute the bucket to insert into
		int bucketNumber = hashFunc(val);

		// add to the beginning of the list at bucketNumber
		table[bucketNumber].addFirst(val);

		// return the location where we inserted
		return bucketNumber;
	}

	/**
	 * Find the bucket in which val exists in the table in O(n)
	 * 
	 * @param val: value to look for in the table
	 * @return bucket val is in, -1 if val is not in table
	 * @throws Exception
	 */
	@Override
	public int find(MyType val) throws Exception {
		// compute the bucket the value would be in
		int hashval = hashFunc(val);

		// does the linked list contain val at the hashed bucket O(n)
		if (table[hashval].contains(val))
			return hashval;

		// if val is not in the table, return -1
		return -1;
	}

	/**
	 * Delete the value val from the table if it exists
	 * 
	 * @param val: value to delete from the table
	 * @return true if val was in the table, false otherwise
	 * @throws Exception
	 */
	@Override
	public boolean delete(MyType val) throws Exception {
		// compute the bucket the value would be in
		int hashval = hashFunc(val);

		// delete val from the linked list at the bucket O(n)
		if (table[hashval].remove(val) == true) {
			numEntries--;
			return true;
		}

		// return false if val was not in the table to begin with
		return false;
	}

	/**
	 * The load factor is the number of entries / capacity of the table
	 * 
	 * @return the load factor of the table: in this case it can be a positive
	 *         number
	 */
	@Override
	public double getLoadFactor() {
		return (double) numEntries / capacity;
	}
}
