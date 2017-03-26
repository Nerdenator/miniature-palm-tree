package hashTable_util;

public interface InterfaceHashTable<MyType> {

	// insert val into the table and return the index
	int insert(MyType val) throws Exception;

	// return the index of the location val is at, or -1 if not in table
	int find(MyType val) throws Exception;

	// remove val and return true if val was in the table, false otherwise
	boolean delete(MyType val) throws Exception;

	// return the load factor of the table
	double getLoadFactor();
}
