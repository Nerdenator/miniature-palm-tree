package bstProblems_test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import bstProblems_src.SumOfKeys;
import bst_src.BinarySearchTree;

public class SumOfKeysTest {

	BinarySearchTree empty;
	BinarySearchTree t0;
	BinarySearchTree t1;
	BinarySearchTree t2;

	@Before
	public void setUp() throws Exception {
		empty = new BinarySearchTree();
		t0 = new BinarySearchTree(10);
		t1 = new BinarySearchTree(new int[] { 10, 15 });
		t2 = new BinarySearchTree(new int[] { 10, 8, 12, 6, 9, 11, 13, 14, 15, 16, 17, 7 });
	}

	@Test
	public void testSumOfKeysRecursive() {
		// empty tree
		Assert.assertEquals(-1, SumOfKeys.sumOfKeysRecursive(empty));
		// other
		Assert.assertEquals(10, SumOfKeys.sumOfKeysRecursive(t0));
		Assert.assertEquals(25, SumOfKeys.sumOfKeysRecursive(t1));
		Assert.assertEquals(138, SumOfKeys.sumOfKeysRecursive(t2));
	}

	@Test
	public void testSumOfKeysIterative() {
		// empty tree
		Assert.assertEquals(-1, SumOfKeys.sumOfKeysIterative(empty));
		// other
		Assert.assertEquals(10, SumOfKeys.sumOfKeysIterative(t0));
		Assert.assertEquals(25, SumOfKeys.sumOfKeysIterative(t1));
		Assert.assertEquals(138, SumOfKeys.sumOfKeysIterative(t2));
	}

}
