package hashTable_test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import hashTable_src.HashTableOpenAddressing;
import hashTable_src.HashTableOpenAddressing.PROBING_TYPE;

public class HashTableOpenAddressingTest {

	int startsz = 4;
	int capacityLinear = 5;
	int capacityQuadratic = 5;
	int capacityDouble = 5;

	HashTableOpenAddressing<Integer> tableLinear = new HashTableOpenAddressing<>(startsz, PROBING_TYPE.LINEAR);
	double numEntriesLinear;
	HashTableOpenAddressing<Integer> tableQuadratic = new HashTableOpenAddressing<>(startsz, PROBING_TYPE.QUADRATIC);
	double numEntriesQuadratic;
	HashTableOpenAddressing<Integer> tableDouble = new HashTableOpenAddressing<>(startsz, PROBING_TYPE.DOUBLE);
	double numEntriesDouble;
	double eps = 0.0000000001;

	@Before
	public void setUp() throws Exception {
		tableLinear.insert(11); // 11 % 5 = 1
		tableLinear.insert(22); // 22 % 5 = 2
		tableLinear.insert(33); // 33 % 5 = 3
		numEntriesLinear = 3;

		tableQuadratic.insert(11); // 11 % 5 = 1 => 1
		tableQuadratic.insert(22); // 22 % 5 = 2 => 2
		tableQuadratic.insert(33); // 33 % 5 = 0 => 3
		numEntriesQuadratic = 3;

		tableDouble.insert(11); // 11 % 5 = 1 => 1
		tableDouble.insert(22); // 22 % 5 = 2 => 2
		tableDouble.insert(33); // 33 % 5 = 0 => 3
		numEntriesDouble = 3;
	}

	@Test
	public void testSetUp() {
		Assert.assertEquals("[ ~~ 11 22 33 ~~ ]", tableLinear.toString());
		Assert.assertEquals("[ ~~ 11 22 33 ~~ ]", tableLinear.toString());
		Assert.assertEquals("[ ~~ 11 22 33 ~~ ]", tableDouble.toString());
	}

	@Test
	public void testGetLoadFactor() {
		Assert.assertEquals(numEntriesLinear / capacityLinear, tableLinear.getLoadFactor(), eps);
		Assert.assertEquals(numEntriesQuadratic / capacityQuadratic, tableQuadratic.getLoadFactor(), eps);
		Assert.assertEquals(numEntriesDouble / capacityDouble, tableDouble.getLoadFactor(), eps);
	}

	@Test
	public void testFind() throws Exception {
		tableLinear.insert(23);
		tableLinear.insert(61); // 61 % 11 = 6
		tableLinear.insert(22); // 22 % 11 = 0
		Assert.assertEquals("[ 11 22 33 23 22 ~~ 61 ~~ ~~ ~~ ~~ ]", tableLinear.toString());
		Assert.assertEquals(-1, tableLinear.find(10));
		Assert.assertEquals(0, tableLinear.find(11));
		Assert.assertEquals(1, tableLinear.find(22));
		Assert.assertEquals(6, tableLinear.find(61));

		tableQuadratic.insert(44);
		tableQuadratic.insert(61); // 61 % 11 = 6
		tableQuadratic.insert(55); // 55 % 11 = 0 => (1 + 2*2 + 3*3 + 4*4) % 11 = 8
		Assert.assertEquals("[ 11 22 ~~ 44 ~~ 33 61 ~~ 55 ~~ ~~ ]", tableQuadratic.toString());
		Assert.assertEquals(-1, tableQuadratic.find(10));
		Assert.assertEquals(0, tableQuadratic.find(11));
		Assert.assertEquals(6, tableQuadratic.find(61));
		Assert.assertEquals(8, tableQuadratic.find(55));

		tableDouble.insert(44);
		tableDouble.insert(61); // 61 % 11 = 6; 61 % 7 = 5; 7 - 5 = 2 => 6 + 2 = 8
		tableDouble.insert(55); // 55 % 11 = 0; 55 % 7 = 6; 7 - 6 = 1 => 0 + 1 = 1
		Assert.assertEquals("[ 11 55 33 ~~ ~~ 44 22 ~~ 61 ~~ ~~ ]", tableDouble.toString());
		Assert.assertEquals(-1, tableDouble.find(10));
		Assert.assertEquals(0, tableDouble.find(11));
		Assert.assertEquals(8, tableDouble.find(61));
	}

	@Test
	public void testDelete() throws Exception {
		tableLinear.insert(23); //3
		tableLinear.insert(61); // 61 % 11 = 6
		tableLinear.insert(22); // 22 % 11 = 0
		Assert.assertEquals("[ 11 22 33 23 22 ~~ 61 ~~ ~~ ~~ ~~ ]", tableLinear.toString());
		Assert.assertFalse(tableLinear.delete(10));
		Assert.assertTrue(tableLinear.delete(22));
		Assert.assertEquals("[ 11 ~~ 33 23 22 ~~ 61 ~~ ~~ ~~ ~~ ]", tableLinear.toString());
		Assert.assertEquals(4, tableLinear.find(22));
		Assert.assertTrue(tableLinear.delete(33));
		Assert.assertEquals("[ 11 ~~ ~~ 23 22 ~~ 61 ~~ ~~ ~~ ~~ ]", tableLinear.toString());
		Assert.assertFalse(tableLinear.delete(33));
		Assert.assertEquals("[ 11 ~~ ~~ 23 22 ~~ 61 ~~ ~~ ~~ ~~ ]", tableLinear.toString());

		tableQuadratic.insert(44);
		tableQuadratic.insert(61); // 61 % 11 = 6
		tableQuadratic.insert(55); // 55 % 11 = 0 => (1 + 2*2 + 3*3 + 4*4) % 11 = 8
		Assert.assertEquals("[ 11 22 ~~ 44 ~~ 33 61 ~~ 55 ~~ ~~ ]", tableQuadratic.toString());
		Assert.assertFalse(tableQuadratic.delete(10));
		Assert.assertTrue(tableQuadratic.delete(11));
		Assert.assertEquals("[ ~~ 22 ~~ 44 ~~ 33 61 ~~ 55 ~~ ~~ ]", tableQuadratic.toString());
		Assert.assertEquals(1, tableQuadratic.find(22));
		Assert.assertFalse(tableQuadratic.delete(11));
		Assert.assertEquals("[ ~~ 22 ~~ 44 ~~ 33 61 ~~ 55 ~~ ~~ ]", tableQuadratic.toString());

		tableDouble.insert(44);
		tableDouble.insert(61); // 61 % 11 = 6; 61 % 7 = 5; 7 - 5 = 2 => 6 + 2 = 8
		tableDouble.insert(55); // 55 % 11 = 0; 55 % 7 = 6; 7 - 6 = 1 => 0 + 1 = 1
		Assert.assertEquals("[ 11 55 33 ~~ ~~ 44 22 ~~ 61 ~~ ~~ ]", tableDouble.toString());
		Assert.assertFalse(tableQuadratic.delete(10));
		Assert.assertTrue(tableDouble.delete(55));
		Assert.assertEquals("[ 11 ~~ 33 ~~ ~~ 44 22 ~~ 61 ~~ ~~ ]", tableDouble.toString());
		Assert.assertEquals(2, tableDouble.find(33));
		Assert.assertFalse(tableDouble.delete(55));
		Assert.assertEquals("[ 11 ~~ 33 ~~ ~~ 44 22 ~~ 61 ~~ ~~ ]", tableDouble.toString());
	}

	@Test
	public void testInsertLinear() throws Exception {
		// will cause a table resize
		tableLinear.insert(23);
		/*
		 * 11 % 11 = 0 => 0
		 * 22 % 11 = 0 => 1
		 * 33 % 11 = 0 => 2
		 * 23 % 11 = 1 => 3
		 */
		capacityLinear = 11;
		numEntriesLinear++;
		Assert.assertEquals(capacityLinear, tableLinear.getCapacity());
		Assert.assertEquals(numEntriesLinear / capacityLinear, tableLinear.getLoadFactor(), eps);
		Assert.assertEquals("[ 11 22 33 23 ~~ ~~ ~~ ~~ ~~ ~~ ~~ ]", tableLinear.toString());

		tableLinear.insert(61); // 61 % 11 = 6
		tableLinear.insert(22); // 22 % 11 = 0
		numEntriesLinear += 2;
		Assert.assertEquals("[ 11 22 33 23 22 ~~ 61 ~~ ~~ ~~ ~~ ]", tableLinear.toString());
		Assert.assertEquals(numEntriesLinear / capacityLinear, tableLinear.getLoadFactor(), eps);
	}

	@Test
	public void testInsertQuadratic() throws Exception {
		// will cause a table resize
		tableQuadratic.insert(44);
		/*
		 * 11 % 11 = 0 => 0
		 * 22 % 11 = 0 => 0 + 1*1 = 1
		 * 33 % 11 = 0 => 1 + 2*2 = 5
		 * 44 % 11 = 0 => 1 + 2*2 + 3*3 = 14 % 11 = 3
		 */
		capacityQuadratic = 11;
		numEntriesQuadratic++;
		Assert.assertEquals(capacityQuadratic, tableQuadratic.getCapacity());
		Assert.assertEquals(numEntriesQuadratic / capacityQuadratic, tableQuadratic.getLoadFactor(), eps);
		Assert.assertEquals("[ 11 22 ~~ 44 ~~ 33 ~~ ~~ ~~ ~~ ~~ ]", tableQuadratic.toString());

		tableQuadratic.insert(61); // 61 % 11 = 6
		tableQuadratic.insert(55); // 55 % 11 = 0 => (1 + 2*2 + 3*3 + 4*4) % 11 = 8
		numEntriesQuadratic += 2;
		Assert.assertEquals("[ 11 22 ~~ 44 ~~ 33 61 ~~ 55 ~~ ~~ ]", tableQuadratic.toString());
		Assert.assertEquals(numEntriesQuadratic / capacityQuadratic, tableQuadratic.getLoadFactor(), eps);
	}

	@Test
	public void testInsertDouble() throws Exception {
		// will cause a table resize
		tableDouble.insert(44);
		/*
		 * previousPrime = 7
		 * 11 % 11 = 0 => 0
		 * 22 % 11 = 0; 22 % 7 = 1; 7 - 1 = 6 => 0 + 6 = 6
		 * 33 % 11 = 0; 33 % 7 = 5; 7 - 5 = 2 => 0 + 2 = 2
		 * 44 % 11 = 0; 44 % 7 = 2; 7 - 2 = 5 => 0 + 5 = 5
		 */
		capacityDouble = 11;
		numEntriesDouble++;
		Assert.assertEquals(capacityDouble, tableDouble.getCapacity());
		Assert.assertEquals(numEntriesDouble / capacityDouble, tableDouble.getLoadFactor(), eps);
		Assert.assertEquals("[ 11 ~~ 33 ~~ ~~ 44 22 ~~ ~~ ~~ ~~ ]", tableDouble.toString());

		tableDouble.insert(61); // 61 % 11 = 6; 61 % 7 = 5; 7 - 5 = 2 => 6 + 2 = 8
		tableDouble.insert(55); // 55 % 11 = 0; 55 % 7 = 6; 7 - 6 = 1 => 0 + 1 = 1
		numEntriesDouble += 2;
		Assert.assertEquals("[ 11 55 33 ~~ ~~ 44 22 ~~ 61 ~~ ~~ ]", tableDouble.toString());
		Assert.assertEquals(numEntriesDouble / capacityDouble, tableDouble.getLoadFactor(), eps);
	}

}
