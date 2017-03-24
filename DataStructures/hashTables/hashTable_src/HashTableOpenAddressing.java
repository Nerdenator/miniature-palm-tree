package hashTable_src;

import hashTable_util.HashFunctions;
import hashTable_util.InterfaceHashTable;
import hashTable_util.PrimeNumberOps;

/**
 * Implement a hash table using the open addressing scheme.
 * 
 * @author adina
 *
 * @param <MyType>
 */
public class HashTableOpenAddressing<MyType> implements InterfaceHashTable<MyType> {

	/**
	 * Class that contains the data as well as a flag on whether the cell is
	 * occupied (valid) or not
	 */
	class Cell {
		MyType data;
		boolean occupied;
	}

	/**
	 * Enum for the types of probing
	 *
	 */
	public enum PROBING_TYPE {
		LINEAR, QUADRATIC, DOUBLE
	}

	// the size of the table
	int capacity;

	// the type of probing
	PROBING_TYPE probingType;

	// the underlying table; each cell has data and a validity flag
	Cell[] table;

	// number of entries, used to calculate loadFactor
	int numEntries;

	/**
	 * Constructor for a hashtable
	 * 
	 * @param capacity: table capacity is the next prime number after this
	 * @param probingType: the type of probing (from the enum)
	 */
	@SuppressWarnings("unchecked")
	public HashTableOpenAddressing(int capacity, PROBING_TYPE probingType) {
		this.probingType = probingType;
		this.capacity = PrimeNumberOps.getNextPrime(capacity);
		this.numEntries = 0;

		// initialize the table
		this.table = new HashTableOpenAddressing.Cell[this.capacity];
		// initialize each cell
		for (int i = 0; i < this.capacity; i++)
			this.table[i] = new Cell();
	}

	/**
	 * Insert val into the table. If the load factor is > 0.6, resize table.
	 * O(1) average, O(n) worst case
	 * 
	 * @param val: value to insert into the table
	 * @return the cell index where val was inserted
	 * @throws Exception
	 */
	@Override
	public int insert(MyType val) throws Exception {
		// increase number of entries
		numEntries++;

		// Check new load factor first, and resize if too high
		if (getLoadFactor() > 0.6)
			resizeTable();

		// find the hash key value based on the probing type
		int hashKey = hashFunc(val); // the index to start at

		// probe the table to find the final location
		switch (probingType) {
		case QUADRATIC:
			hashKey = probeQuadratic(hashKey);
			break;
		case DOUBLE:
			// step size (for double hashing) is the second hash function
			int stepSz = hashFunc2(val);
			hashKey = probeDouble(hashKey, stepSz);
			break;
		default: // linear
			hashKey = probeLinear(hashKey);
			break;
		}

		// insert data
		table[hashKey].data = val;
		// mark as occupied
		table[hashKey].occupied = true;

		// return the location where we inserted
		return hashKey;
	}

	/**
	 * Linear probing of the table to find the first empty slot
	 * - look at hashKey, hashKey+1, hashKey+2... until empty spot
	 * 
	 * @param hashKey: starting index
	 * @return: index of first empty slot after hashKey
	 */
	private int probeLinear(int hashKey) {
		while (table[hashKey].occupied == true)
			hashKey = (hashKey + 1) % capacity;

		return hashKey;
	}

	/**
	 * Quadratic probing of the table to find the first empty slot
	 * - look at hashkey, hashkey += 1^2, hashkey += 2^2, hashkey+=3^2... until
	 * empty spot
	 * 
	 * @param hashKey: starting index
	 * @return: index of first empty slot after hashKey
	 */
	private int probeQuadratic(int hashKey) {
		// step size
		int stepSz = 1;

		while (table[hashKey].occupied == true) {
			hashKey = (hashKey + stepSz * stepSz) % capacity;
			stepSz++;
		}
		return hashKey;
	}

	/**
	 * Double probing of the table to find the first empty slot
	 * - look at hashkey, hashkey+stepSz, hahskey+2*stepSz, hashkey+3*stepSz...
	 * until empty spot
	 * 
	 * @param hashKey: starting index
	 * @param stepSz: the step size to use (based on second hash function)
	 * @return: index of first empty slot after hashKey
	 * @throws Exception
	 */
	private int probeDouble(int hashKey, int stepSz) {
		while (table[hashKey].occupied == true)
			hashKey = (hashKey + stepSz) % capacity;

		return hashKey;
	}

	/**
	 * If the load factor drops below 0.6, resize the table
	 * and re-insert all of the information in O(n)
	 * 
	 * @throws Exception
	 */
	private void resizeTable() throws Exception {
		// create a new tab;e
		int newCapacity = capacity * 3 / 2;
		HashTableOpenAddressing<MyType> newTable = new HashTableOpenAddressing<>(newCapacity, probingType);

		// insert all information in the old table (at new locations)
		for (int i = 0; i < capacity; i++)
			if (table[i].occupied)
				newTable.insert(table[i].data);

		// set the old table to the new table
		table = newTable.table;
		capacity = newTable.capacity;
	}

	/**
	 * Find a value in the table
	 * O(1) average, O(n) worst case
	 * 
	 * @param val: the value to look for
	 * @return the bucket number in which the value is located
	 * @throws Exception
	 */
	@Override
	public int find(MyType val) throws Exception {
		// find hashkey value based on the probing type
		int hashKey = hashFunc(val); // start at

		// probe the table to find the value location
		switch (probingType) {
		case QUADRATIC:
			hashKey = findQuadratic(hashKey, val);
			break;
		case DOUBLE:
			// step size (for double hashing) is the second hash function
			int stepSz = hashFunc2(val);
			hashKey = findDouble(hashKey, val, stepSz);
			break;
		default: // linear
			hashKey = findLinear(hashKey, val);
			break;
		}

		return hashKey;
	}

	/**
	 * Find val by linear probing of the table
	 * - look at hashKey, hashKey+1, hashKey+2... until the value is found or
	 * we've circled around
	 * 
	 * @param hashKey: starting index
	 * @param val: the value to find
	 * @return the location of val or -1 if not in table
	 */
	private int findLinear(int hashKey, MyType val) {
		// end condition for while loop, mod will end up with these equal
		int initial = hashKey;

		// look for val in table
		do {
			if (table[hashKey].occupied && table[hashKey].data.equals(val))
				return hashKey;
			hashKey = (hashKey + 1) % capacity;
		}
		// until we've come full circle
		while (initial != hashKey);
		// value was not found
		return -1;
	}

	/**
	 * Find val by quadratic probing of the table
	 * - look at hashkey, hashkey += 1^2, hashkey += 2^2, hashkey+=3^2... until
	 * the value is found or we've circled around
	 * 
	 * @param hashKey: starting index
	 * @param val: the value to find
	 * @return the location of val or -1 if not in table
	 */
	private int findQuadratic(int hashKey, MyType val) {
		// end condition for while loop, mod will end up with these equal
		int initial = hashKey;

		// step size
		int stepSz = 1;

		// look for val in table
		do {
			if (table[hashKey].occupied && table[hashKey].data.equals(val))
				return hashKey;
			hashKey = (hashKey + stepSz * stepSz) % capacity;
			stepSz++;
		}
		// until we've come full circle
		while (initial != hashKey);
		// value was not found
		return -1;
	}

	/**
	 * Find val by double probing of the table
	 * - look at hashkey, hashkey+stepSz, hahskey+2*stepSz, hashkey+3*stepSz...
	 * until the value is found or we've circled around
	 * 
	 * @param hashKey: starting index
	 * @param val: the value to find
	 * @param stepSz: the step size to use (based on second hash function)
	 * @return the location of val or -1 if not in table
	 */
	private int findDouble(int hashKey, MyType val, int stepSz) {
		// end condition for while loop, mod will end up with these equal
		int initial = hashKey;

		// look for val in table
		do {
			if (table[hashKey].occupied && table[hashKey].data.equals(val))
				return hashKey;
			hashKey = (hashKey + stepSz) % capacity;
		}
		// until we've come full circle
		while (initial != hashKey);
		// value was not found
		return -1;
	}

	/**
	 * Delete a value from the table
	 * O(1) average, O(n) worst case
	 * 
	 * @param val: the value to look for
	 * @return true if the value existed and was deleted, false otherwise
	 * @throws Exception
	 */
	@Override
	public boolean delete(MyType val) throws Exception {
		// find the location of val
		int index = find(val);

		// if it's not found, return false
		if (index == -1)
			return false;

		// otherwise delete it from the table
		table[index].occupied = false;
		table[index].data = null;
		numEntries--;
		// and return success
		return true;
	}

	/**
	 * The load factor is the number of entries / capacity of the table
	 */
	@Override
	public double getLoadFactor() {
		return (double) numEntries / capacity;
	}

	/**
	 * Calculate the hash value (the bucket val falls into)
	 * 
	 * @param val: the value we're hashing
	 * @return the hash value of val
	 * @throws Exception: only defined for Char, String and Integer
	 */
	protected int hashFunc(MyType val) throws Exception {
		if (val instanceof Integer)
			return HashFunctions.hashInt((Integer) val, capacity);

		else if (val instanceof Character)
			return HashFunctions.hashChar((Character) val, capacity);

		else if (val instanceof String)
			return HashFunctions.hashString((String) val, capacity);

		throw new Exception("Only supports Integer, String and Character");
	}

	/**
	 * Calculate the hash value (the bucket val falls into) for double hashing
	 * 
	 * @param val: the value we're hashing
	 * @return the hash value of val
	 * @throws Exception: only defined for Char, String and Integer
	 */
	protected int hashFunc2(MyType val) throws Exception {
		if (val instanceof Integer)
			return HashFunctions.hashInt2((Integer) val, capacity);

		else if (val instanceof Character)
			return HashFunctions.hashChar2((Character) val, capacity);

		else if (val instanceof String)
			return HashFunctions.hashString2((String) val, capacity);

		throw new Exception("Only support Integer, Character and String");
	}

	/**
	 * @return the table capacity
	 */
	public Object getCapacity() {
		return capacity;
	}

	/**
	 * Display the hash table as a [list_of_items] where each occupied spot
	 * shows the data and each empty spot is displayed as "~~" and items are
	 * separated by space: [ item0 item1 ~~ item4 ....]
	 * 
	 * @return a string representation of the hash table
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < capacity; i++)
			if (table[i].occupied)
				sb.append(table[i].data + " ");
			//
			else
				sb.append("~~ ");
		return "[ " + sb + "]";
	}
}
