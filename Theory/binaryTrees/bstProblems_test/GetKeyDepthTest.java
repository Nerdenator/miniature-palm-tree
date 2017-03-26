package bstProblems_test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import binaryTree_src.BinarySearchTree;
import bstProblems_src.GetKeyDepth;

public class GetKeyDepthTest {
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
	public void testGetKeyDepthRecursive() {
		// find in empty tree
		Assert.assertEquals(-1, GetKeyDepth.getKeyDepthRecursive(empty, 10));
		// find a value not in the tree
		Assert.assertEquals(-1, GetKeyDepth.getKeyDepthRecursive(t2, 3));

		// find root
		Assert.assertEquals(0, GetKeyDepth.getKeyDepthRecursive(t0, 10));
		Assert.assertEquals(0, GetKeyDepth.getKeyDepthRecursive(t1, 10));
		Assert.assertEquals(0, GetKeyDepth.getKeyDepthRecursive(t2, 10));

		// find other
		// t2.displayTree();
		Assert.assertEquals(1, GetKeyDepth.getKeyDepthRecursive(t1, 15));
		Assert.assertEquals(4, GetKeyDepth.getKeyDepthRecursive(t2, 15));
		Assert.assertEquals(5, GetKeyDepth.getKeyDepthRecursive(t2, 16));
		Assert.assertEquals(2, GetKeyDepth.getKeyDepthRecursive(t2, 11));
		Assert.assertEquals(3, GetKeyDepth.getKeyDepthRecursive(t2, 7));
		Assert.assertEquals(2, GetKeyDepth.getKeyDepthRecursive(t2, 9));
	}

}
