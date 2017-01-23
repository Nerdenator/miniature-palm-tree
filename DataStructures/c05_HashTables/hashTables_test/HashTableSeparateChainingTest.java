package hashTables_test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import hashTables_src.HashTableSeparateChaining;

public class HashTableSeparateChainingTest {

	int startsz = 4;
	int capacity = 5;

	HashTableSeparateChaining<Integer> tableInt = new HashTableSeparateChaining<>(startsz);
	HashTableSeparateChaining<String> tableStr = new HashTableSeparateChaining<>(startsz);
	HashTableSeparateChaining<Character> tableChr = new HashTableSeparateChaining<>(startsz);

	double numEntriesInt;
	double numEntriesStr;
	double numEntriesChr;
	double eps = 0.0000000001;

	@Before
	public void setUp() throws Exception {
		tableInt.insert(10); // 10 % 5 = 0
		tableInt.insert(20); // 20 % 5 = 0
		tableInt.insert(87); // 87 % 5 = 2
		tableInt.insert(18); // 18 % 5 = 3

		tableStr.insert("ab"); // (a*31 + b + 7) % 5 = (97*31^2 + 98*31 + 7) % 5 = 96262 % 5 = 2
		tableStr.insert("abc"); // (97*31^3 + 98*31^2 + 99*31 + 7) % 5 = 2986981 % 5 = 1
		tableStr.insert("abcd"); // (97*31^4 + 98*31^3 + 99*31^2 + 100*31 + 7) % 5 = 92599301 % 5 = 1

		tableChr.insert('a'); // 'a' = 97; 97 % 5 = 2
		tableChr.insert('1'); // '1' = 49; 49 % 5 = 4
		tableChr.insert('c'); // 'c' = 99; 99 % 5 = 4
		tableChr.insert('5'); // '5' = 53; 53 % 5 = 3

		numEntriesChr = 4;
		numEntriesInt = 4;
		numEntriesStr = 3;
	}

	@Test
	public void testSetUp() {
		Assert.assertEquals("0: [20 -> 10]\n1: []\n2: [87]\n3: [18]\n4: []", tableInt.toString());
		Assert.assertEquals("0: []\n1: [abcd -> abc]\n2: [ab]\n3: []\n4: []", tableStr.toString());
		Assert.assertEquals("0: []\n1: []\n2: [a]\n3: [5]\n4: [c -> 1]", tableChr.toString());
	}

	@Test
	public void testGetLoadFactor() {
		Assert.assertEquals(numEntriesInt / capacity, tableInt.getLoadFactor(), eps);
		Assert.assertEquals(numEntriesStr / capacity, tableStr.getLoadFactor(), eps);
		Assert.assertEquals(numEntriesChr / capacity, tableChr.getLoadFactor(), eps);
	}

	@Test
	public void testFind() throws Exception {
		Assert.assertEquals(0, tableInt.find(10));
		Assert.assertEquals(0, tableInt.find(20));
		Assert.assertEquals(3, tableInt.find(18));
		Assert.assertEquals(2, tableInt.find(87));
		Assert.assertEquals(-1, tableInt.find(80));

		Assert.assertEquals(2, tableStr.find("ab"));
		Assert.assertEquals(1, tableStr.find("abc"));
		Assert.assertEquals(1, tableStr.find("abcd"));
		Assert.assertEquals(-1, tableStr.find("a"));

		Assert.assertEquals(2, tableChr.find('a'));
		Assert.assertEquals(4, tableChr.find('1'));
		Assert.assertEquals(4, tableChr.find('c'));
		Assert.assertEquals(3, tableChr.find('5'));
		Assert.assertEquals(-1, tableChr.find('2'));
	}

	@Test
	public void testDelete() throws Exception {
		Assert.assertEquals(numEntriesInt / capacity, tableInt.getLoadFactor(), eps);
		Assert.assertTrue(tableInt.delete(10));
		Assert.assertEquals((numEntriesInt - 1) / capacity, tableInt.getLoadFactor(), eps);
		Assert.assertEquals(-1, tableInt.find(10));
		Assert.assertFalse(tableInt.delete(10));
		Assert.assertEquals((numEntriesInt - 1) / capacity, tableInt.getLoadFactor(), eps);

		Assert.assertEquals(numEntriesStr / capacity, tableStr.getLoadFactor(), eps);
		Assert.assertTrue(tableStr.delete("ab"));
		Assert.assertEquals((numEntriesStr - 1) / capacity, tableStr.getLoadFactor(), eps);
		Assert.assertEquals(-1, tableStr.find("ab"));
		Assert.assertFalse(tableStr.delete("ab"));
		Assert.assertEquals((numEntriesStr - 1) / capacity, tableStr.getLoadFactor(), eps);

		Assert.assertEquals(numEntriesChr / capacity, tableChr.getLoadFactor(), eps);
		Assert.assertTrue(tableChr.delete('c'));
		Assert.assertEquals((numEntriesChr - 1) / capacity, tableChr.getLoadFactor(), eps);
		Assert.assertEquals(-1, tableChr.find('c'));
		Assert.assertFalse(tableChr.delete('c'));
		Assert.assertEquals((numEntriesChr - 1) / capacity, tableChr.getLoadFactor(), eps);
	}

	@Test
	public void testInsertInteger() throws Exception {
		HashTableSeparateChaining<Integer> table = new HashTableSeparateChaining<>(startsz);
		Assert.assertEquals(capacity, table.getCapacity());

		table.insert(10); // 10 % 5 = 0
		table.insert(20); // 20 % 5 = 0
		table.insert(87); // 87 % 5 = 2
		Assert.assertEquals("0: [20 -> 10]\n1: []\n2: [87]\n3: []\n4: []", table.toString());

		table.insert(18); // 18 % 5 = 3
		table.insert(61); // 61 % 5 = 1
		table.insert(22); // 22 % 5 = 2
		table.insert(44); // 44 % 5 = 4
		Assert.assertEquals("0: [20 -> 10]\n1: [61]\n2: [22 -> 87]\n3: [18]\n4: [44]", table.toString());

		table.insert(53); // 53 % 5 = 3
		table.insert(8); ///  8 % 5 = 3
		Assert.assertEquals("0: [20 -> 10]\n1: [61]\n2: [22 -> 87]\n3: [8 -> 53 -> 18]\n4: [44]", table.toString());
	}

	@Test
	public void testInsertString() throws Exception {
		HashTableSeparateChaining<String> table = new HashTableSeparateChaining<>(startsz);
		Assert.assertEquals(capacity, table.getCapacity());

		table.insert("ab"); // (a*31 + b + 7) % 5 = (97*31^2 + 98*31 + 7) % 5 = 96262 % 5 = 2
		table.insert("abc"); // (97*31^3 + 98*31^2 + 99*31 + 7) % 5 = 2986981 % 5 = 1
		table.insert("abcd"); // (97*31^4 + 98*31^3 + 99*31^2 + 100*31 + 7) % 5 = 92599301 % 5 = 1
		Assert.assertEquals("0: []\n1: [abcd -> abc]\n2: [ab]\n3: []\n4: []", table.toString());
	}

	@Test
	public void testInsertCharacter() throws Exception {
		HashTableSeparateChaining<Character> table = new HashTableSeparateChaining<>(startsz);
		Assert.assertEquals(capacity, table.getCapacity());

		table.insert('a'); // 'a' = 97; 97 % 5 = 2
		table.insert('1'); // '1' = 49; 49 % 5 = 4
		table.insert('c'); // 'c' = 99; 99 % 5 = 4
		table.insert('5'); // '5' = 53; 53 % 5 = 3
		Assert.assertEquals("0: []\n1: []\n2: [a]\n3: [5]\n4: [c -> 1]", table.toString());

		table.insert('4'); // '4' = 52; 52 % 5 = 2
		table.insert('e'); // 'e' = 101; 101 % 5 = 1
		table.insert('7'); // '7' = 55; 55 % 5 = 0
		table.insert('f'); // 'f' = 102; 102 % 5 = 2
		Assert.assertEquals("0: [7]\n1: [e]\n2: [f -> 4 -> a]\n3: [5]\n4: [c -> 1]", table.toString());
	}

	@Test(expected = Exception.class)
	public void testInsertSomethingElse() throws Exception {
		HashTableSeparateChaining<Boolean> table = new HashTableSeparateChaining<>(startsz);
		Assert.assertEquals(capacity, table.getCapacity());

		table.insert(false); // throws exception
	}
}
