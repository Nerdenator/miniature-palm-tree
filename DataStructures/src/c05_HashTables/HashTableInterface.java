package c05_HashTables;

public interface HashTableInterface<MyType> {

	/* Inserts val and returns the index */
	int insert(MyType val) throws Exception;

	/* Returns the index of the location val is at, -1 otherwise */
	int find(MyType val) throws Exception;

	/* Returns false if val is not in the table; 
	 * otherwise, removes val and returns true */
	boolean delete(MyType val) throws Exception;

	/* Return the load factor of the table*/
	double getLoadFactor();
}
