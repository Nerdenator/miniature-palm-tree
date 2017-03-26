package bstProblems_test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import bstProblems_src.GetTreeHeight;
import bst_src.BinarySearchTree;

public class GetTreeHeightTest {

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
	public void testGetTreeHeightRecursive() {
		// empty tree
		Assert.assertEquals(-1, GetTreeHeight.getTreeHeightRecursive(empty));
		// one node
		Assert.assertEquals(0, GetTreeHeight.getTreeHeightRecursive(t0));
		// other
		Assert.assertEquals(1, GetTreeHeight.getTreeHeightRecursive(t1));
		Assert.assertEquals(6, GetTreeHeight.getTreeHeightRecursive(t2));
	}

	@Test
	public void testGetTreeHeightIterative() {
		// empty tree
		Assert.assertEquals(-1, GetTreeHeight.getTreeHeightIterative(empty));
		// one node
		Assert.assertEquals(0, GetTreeHeight.getTreeHeightIterative(t0));
		// other
		Assert.assertEquals(1, GetTreeHeight.getTreeHeightIterative(t1));
		Assert.assertEquals(6, GetTreeHeight.getTreeHeightIterative(t2));
	}
}
