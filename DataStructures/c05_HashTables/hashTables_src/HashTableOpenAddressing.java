package hashTables_src;

public class HashTableOpenAddressing<MyType> implements HashTableInterface<MyType> {

	/**
	 * Class that contains the data as well as a flag on whether the data is
	 * occupied (valid) or not
	 */
	class Info {
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

	int capacity;
	PROBING_TYPE probingType;

	// the underlying table; each cell has data and a flag to say if it's valid or not
	Info[] table;

	// number of entries, used for loadFactor
	int numEntries;

	@SuppressWarnings("unchecked")
	public HashTableOpenAddressing(int capacity, PROBING_TYPE probingType) {
		this.probingType = probingType;
		this.capacity = HashTableUtil.getNextPrime(capacity);
		this.table = new HashTableOpenAddressing.Info[this.capacity];
		for (int i = 0; i < this.capacity; i++)
			this.table[i] = new Info();
	}

	/**
	 * O(1) average, O(n) worst case
	 * Check if the load factor is >0.6 and if so resize table
	 * 
	 * @param val
	 * @return the bucket number
	 */
	@Override
	public int insert(MyType val) throws Exception {
		//increase number of entries
		numEntries++;

		// Check new load factor first, and resize if too high
		if (getLoadFactor() > 0.6)
			resizeTable();

		// find hashkey value based on the probing type
		int hashKey = hashFunc(val); //start at

		// for linear
		if (probingType == PROBING_TYPE.LINEAR) {
			// look at hashKey, hashKey+1, hashKey+2... until empty spot
			while (table[hashKey].occupied == true)
				hashKey = (hashKey + 1) % capacity;
		}
		// for quadratic
		if (probingType == PROBING_TYPE.QUADRATIC) {
			// step size
			int stepSz = 1;

			// look at hashkey, hashkey+1*1, hashkey+1*1+2*2, hashkey+1*1+2*2+3*3....
			while (table[hashKey].occupied == true) {
				hashKey = (hashKey + stepSz * stepSz) % capacity;
				stepSz++;
			}
		}
		// for double
		else if (probingType == PROBING_TYPE.DOUBLE) {
			// step size is the second hash function
			int stepSz = hashFunc2(val);

			// look at hashkey, hashkey+stepSz, hahskey+2*stepSz, hashkey+3*stepSz...
			while (table[hashKey].occupied == true)
				hashKey = (hashKey + stepSz) % capacity;
		}

		//insert data
		table[hashKey].data = val;
		//mark as occupied
		table[hashKey].occupied = true;

		return hashKey;
	}

	/**
	 * If the load factor drops below 0.6, resize the table
	 * and re-insert all of the information
	 * 
	 * @throws Exception
	 */
	private void resizeTable() throws Exception {
		int newCapacity = capacity * 3 / 2;
		HashTableOpenAddressing<MyType> newTable = new HashTableOpenAddressing<>(newCapacity, probingType);
		for (int i = 0; i < capacity; i++)
			if (table[i].occupied)
				newTable.insert(table[i].data);

		table = newTable.table;
		capacity = newTable.capacity;
	}

	/**
	 * O(1) average, O(n) worst case
	 * 
	 * @param val
	 * @return the bucket number
	 */
	@Override
	public int find(MyType val) throws Exception {
		// find hashkey value based on the probing type
		int hashKey = hashFunc(val); //start at
		int initial = hashKey; // end condition for while loop

		// for linear
		if (probingType == PROBING_TYPE.LINEAR) {
			// look at hashKey, hashKey+1, hashKey+2... until numEntries
			do {
				if (table[hashKey].occupied && table[hashKey].data.equals(val))
					return hashKey;
				hashKey = (hashKey + 1) % capacity;
			} while (initial != hashKey);
		}
		// for quadratic
		if (probingType == PROBING_TYPE.QUADRATIC) {
			// step size
			int stepSz = 1;

			// look at hashkey, hashkey+1*1, hashkey+1*1+2*2, hashkey+1*1+2*2+3*3....
			do {
				if (table[hashKey].occupied && table[hashKey].data.equals(val))
					return hashKey;
				hashKey = (hashKey + stepSz * stepSz) % capacity;
				stepSz++;
			} while (initial != hashKey);
		}
		// for double
		else if (probingType == PROBING_TYPE.DOUBLE) {
			// step size is the second hash function
			int stepSz = hashFunc2(val);

			// look at hashkey, hashkey+stepSz, hahskey+2*stepSz, hashkey+3*stepSz...
			do {
				if (table[hashKey].occupied && table[hashKey].data.equals(val))
					return hashKey;
				hashKey = (hashKey + stepSz) % capacity;
			} while (initial != hashKey);
		}

		return -1;
	}

	@Override
	public boolean delete(MyType val) throws Exception {
		int index = find(val);
		if (index == -1)
			return false;

		table[index].occupied = false;
		table[index].data = null;
		numEntries--;
		return true;
	}

	@Override
	public double getLoadFactor() {
		return (double) numEntries / capacity;
	}

	/**
	 * Calculate the hash value (the bucket val falls into)
	 * 
	 * @param val
	 * @return the hash value of val
	 * @throws Exception
	 *             for types we didn't define it for
	 */
	protected int hashFunc(MyType val) throws Exception {
		if (val instanceof Integer)
			return HashTableUtil.hashInt((Integer) val, capacity);

		else if (val instanceof Character)
			return HashTableUtil.hashChar((Character) val, capacity);

		else if (val instanceof String)
			return HashTableUtil.hashString((String) val, capacity);

		throw new Exception("Only support Integer and String");
	}

	/**
	 * Calculate the hash value (the bucket val falls into) for double hashing
	 * 
	 * @param val
	 * @return the hash value of val
	 * @throws Exception
	 *             for types we didn't define it for
	 */
	protected int hashFunc2(MyType val) throws Exception {
		if (val instanceof Integer)
			return HashTableUtil.hashInt2((Integer) val, capacity);

		else if (val instanceof Character)
			return HashTableUtil.hashChar2((Character) val, capacity);

		else if (val instanceof String)
			return HashTableUtil.hashString2((String) val, capacity);

		throw new Exception("Only support Integer and String");
	}

	public Object getCapacity() {
		return capacity;
	}

	/**
	 * Display the hash table as
	 * [list_of_items] where each occupied spot shows the data
	 * and each empty spot is displayed as "~~" and items are separated by space
	 * [ item0 item1 ~~ item4 ....]
	 * 
	 * @return
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
