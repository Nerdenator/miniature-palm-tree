package dynamicArrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DynamicArrayTest {

	DynamicArray dArr;
	DynamicArray dArr2;
	DynamicArray dArr3;

	@Before
	public void setUp() {
		try {
			dArr = new DynamicArray(2);
			dArr2 = new DynamicArray(new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7 });
			dArr3 = new DynamicArray(new Integer[] { 0, 1 });
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAdd() {
		Assert.assertEquals("[]", dArr.toString());
		Assert.assertEquals(2, dArr.getArraySize());
		Assert.assertEquals(-1, dArr.getLastIndex());

		dArr.add(2);
		Assert.assertEquals("[2]", dArr.toString());
		Assert.assertEquals(2, dArr.getArraySize());
		Assert.assertEquals(0, dArr.getLastIndex());

		dArr.add(3);
		Assert.assertEquals("[2, 3]", dArr.toString());
		Assert.assertEquals(2, dArr.getArraySize());
		Assert.assertEquals(1, dArr.getLastIndex());

		dArr.add(4);
		Assert.assertEquals("[2, 3, 4]", dArr.toString());
		Assert.assertEquals(4, dArr.getArraySize());
		Assert.assertEquals(2, dArr.getLastIndex());

		dArr.add(5);
		Assert.assertEquals("[2, 3, 4, 5]", dArr.toString());
		Assert.assertEquals(4, dArr.getArraySize());
		Assert.assertEquals(3, dArr.getLastIndex());

		dArr.add(6);
		Assert.assertEquals("[2, 3, 4, 5, 6]", dArr.toString());
		Assert.assertEquals(8, dArr.getArraySize());
		Assert.assertEquals(4, dArr.getLastIndex());
	}

	@Test
	public void testSet() throws Exception {
		Assert.assertEquals("[0, 1, 2, 3, 4, 5, 6, 7]", dArr2.toString());
		dArr2.set(2, 20);
		Assert.assertEquals("[0, 1, 20, 3, 4, 5, 6, 7]", dArr2.toString());
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testSetErrorLow() throws Exception {
		Assert.assertEquals("[0, 1, 2, 3, 4, 5, 6, 7]", dArr2.toString());
		dArr2.set(-1, 20);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testSetErrorHigh() throws Exception {
		Assert.assertEquals("[0, 1, 2, 3, 4, 5, 6, 7]", dArr2.toString());
		dArr2.set(10, 20);
	}

	@Test
	public void testGet() throws Exception {
		Assert.assertEquals("[0, 1, 2, 3, 4, 5, 6, 7]", dArr2.toString());
		Assert.assertEquals(2, dArr2.get(2));
		Assert.assertEquals(5, dArr2.get(5));
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetErrorLow() throws Exception {
		Assert.assertEquals("[0, 1, 2, 3, 4, 5, 6, 7]", dArr2.toString());
		dArr2.get(-1);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetErrorHigh() throws Exception {
		Assert.assertEquals("[0, 1, 2, 3, 4, 5, 6, 7]", dArr2.toString());
		dArr2.get(20);
	}

	@Test
	public void testDelete() {
		Assert.assertEquals("[0, 1]", dArr3.toString());
		Assert.assertEquals(1, dArr3.getLastIndex());
		dArr3.delete();
		Assert.assertEquals("[0]", dArr3.toString());
		Assert.assertEquals(0, dArr3.getLastIndex());
		dArr3.delete();
		Assert.assertEquals("[]", dArr3.toString());
		Assert.assertEquals(-1, dArr3.getLastIndex());
		dArr3.delete();
		Assert.assertEquals("[]", dArr3.toString());
		Assert.assertEquals(-1, dArr3.getLastIndex());
	}

	@Test
	public void testInsertAt() {
		Assert.assertEquals(2, dArr3.getArraySize());
		Assert.assertEquals(1, dArr3.getLastIndex());
		Assert.assertEquals("[0, 1]", dArr3.toString());
		dArr3.insertAt(0, -1);
		Assert.assertEquals("[-1, 0, 1]", dArr3.toString());
		Assert.assertEquals(4, dArr3.getArraySize());
		Assert.assertEquals(2, dArr3.getLastIndex());
		dArr3.insertAt(1, 20);
		Assert.assertEquals("[-1, 20, 0, 1]", dArr3.toString());
		Assert.assertEquals(4, dArr3.getArraySize());
		Assert.assertEquals(3, dArr3.getLastIndex());
		dArr3.insertAt(3, 40);
		Assert.assertEquals("[-1, 20, 0, 40, 1]", dArr3.toString());
		Assert.assertEquals(8, dArr3.getArraySize());
		Assert.assertEquals(4, dArr3.getLastIndex());
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testInsertAtEmptyArrayError() {
		dArr.insertAt(0, 10l);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testInsertAtNegativeIndexError() {
		dArr2.insertAt(-1, 10l);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testInsertAtBigIndexError() {
		dArr2.insertAt(20, 10l);
	}

	@Test
	public void testDeleteAt() {
		Assert.assertEquals("[0, 1, 2, 3, 4, 5, 6, 7]", dArr2.toString());
		Assert.assertEquals(7, dArr2.getLastIndex());
		dArr2.deleteAt(2);
		Assert.assertEquals("[0, 1, 3, 4, 5, 6, 7]", dArr2.toString());
		Assert.assertEquals(6, dArr2.getLastIndex());
		dArr2.deleteAt(6);
		Assert.assertEquals("[0, 1, 3, 4, 5, 6]", dArr2.toString());
		Assert.assertEquals(5, dArr2.getLastIndex());
		dArr2.deleteAt(0);
		Assert.assertEquals("[1, 3, 4, 5, 6]", dArr2.toString());
		Assert.assertEquals(4, dArr2.getLastIndex());
		dArr2.deleteAt(2);
		Assert.assertEquals("[1, 3, 5, 6]", dArr2.toString());
		Assert.assertEquals(3, dArr2.getLastIndex());
		dArr2.deleteAt(2);
		Assert.assertEquals("[1, 3, 6]", dArr2.toString());
		Assert.assertEquals(2, dArr2.getLastIndex());
		dArr2.deleteAt(1);
		Assert.assertEquals("[1, 6]", dArr2.toString());
		Assert.assertEquals(1, dArr2.getLastIndex());
		dArr2.deleteAt(1);
		Assert.assertEquals("[1]", dArr2.toString());
		Assert.assertEquals(0, dArr2.getLastIndex());
		dArr2.deleteAt(0);
		Assert.assertEquals("[]", dArr2.toString());
		Assert.assertEquals(-1, dArr2.getLastIndex());
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testDeleteAtEmptyArrayError() {
		dArr.deleteAt(0);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testDeleteAtNegativeIndexError() {
		dArr2.deleteAt(-1);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testDeleteAtBigIndexError() {
		dArr2.deleteAt(20);
	}
}
