package bstProblems_test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import binaryTree_src.BinarySearchTree;
import bstProblems_src.GetMinAndMaxValues;

public class GetMinAndMaxValuesTest {

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
	public void testGetMinAndMaxValues() {
		// in empty tree
		Assert.assertEquals(Integer.MAX_VALUE, GetMinAndMaxValues.getMinValue(empty));
		Assert.assertEquals(Integer.MIN_VALUE, GetMinAndMaxValues.getMaxValue(empty));

		// one-node tree
		Assert.assertEquals(10, GetMinAndMaxValues.getMinValue(t0));
		Assert.assertEquals(10, GetMinAndMaxValues.getMaxValue(t0));

		// other
		Assert.assertEquals(10, GetMinAndMaxValues.getMinValue(t1));
		Assert.assertEquals(15, GetMinAndMaxValues.getMaxValue(t1));
		Assert.assertEquals(6, GetMinAndMaxValues.getMinValue(t2));
		Assert.assertEquals(17, GetMinAndMaxValues.getMaxValue(t2));

	}

}
