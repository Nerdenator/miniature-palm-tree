package bstProblems_test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import bstProblems_src.CountDuplicates;
import bst_src.BinarySearchTree;

public class CountDuplicatesTest {

	BinarySearchTree t0;
	BinarySearchTree t1;

	@Before
	public void setUp() throws Exception {
		t0 = new BinarySearchTree();
		t1 = new BinarySearchTree(new int[] { 10, 8, 7, 14 });
	}

	@Test
	public void testCountRuplicatesRecursive() {
		// no duplicates
		assertEquals(0, CountDuplicates.countDuplicatesRecursive(t0));
		assertEquals(0, CountDuplicates.countDuplicatesRecursive(t1));

		// just two duplicates
		t0.insert(new int[] { 10, 10 });
		assertEquals(1, CountDuplicates.countDuplicatesRecursive(t0));

		// 3 duplicates
		t0.insert(10);
		assertEquals(2, CountDuplicates.countDuplicatesRecursive(t0));

		// multiple duplicates
		t1.insert(new int[] { 10 });
		assertEquals(1, CountDuplicates.countDuplicatesRecursive(t1));

		t1.insert(new int[] { 10, 7, 8, 3 });
		assertEquals(4, CountDuplicates.countDuplicatesRecursive(t1));
	}

	@Test
	public void testCountRuplicatesIterative() {
		// no duplicates
		assertEquals(0, CountDuplicates.countDuplicatesIterative(t0));
		assertEquals(0, CountDuplicates.countDuplicatesIterative(t1));

		// just two duplicates
		t0.insert(new int[] { 10, 10 });
		assertEquals(1, CountDuplicates.countDuplicatesIterative(t0));

		// 3 duplicates
		t0.insert(10);
		assertEquals(2, CountDuplicates.countDuplicatesIterative(t0));

		// multiple duplicates
		t1.insert(new int[] { 10 });
		assertEquals(1, CountDuplicates.countDuplicatesIterative(t1));

		t1.insert(new int[] { 10, 7, 8, 3 });
		assertEquals(4, CountDuplicates.countDuplicatesIterative(t1));
	}

	@Test
	public void testCountRuplicatesHashTable() {
		// no duplicates
		assertEquals(0, CountDuplicates.countDuplicatesHashtable(t0));
		assertEquals(0, CountDuplicates.countDuplicatesHashtable(t1));

		// just two duplicates
		t0.insert(new int[] { 10, 10 });
		assertEquals(1, CountDuplicates.countDuplicatesHashtable(t0));

		// 3 duplicates
		t0.insert(10);
		assertEquals(2, CountDuplicates.countDuplicatesHashtable(t0));

		// multiple duplicates
		t1.insert(new int[] { 10 });
		assertEquals(1, CountDuplicates.countDuplicatesHashtable(t1));

		t1.insert(new int[] { 10, 7, 8, 3 });
		assertEquals(4, CountDuplicates.countDuplicatesHashtable(t1));
	}
}
