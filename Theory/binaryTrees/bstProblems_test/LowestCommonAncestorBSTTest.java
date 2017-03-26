package bstProblems_test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import binaryTree_src.BinarySearchTree;
import bstProblems_src.LowestCommonAncestorBST;

public class LowestCommonAncestorBSTTest {
	BinarySearchTree empty;
	BinarySearchTree bst;

	@Before
	public void setUp() throws Exception {
		empty = new BinarySearchTree();
		bst = new BinarySearchTree(new int[] { 10, 8, 12, 6, 9, 11, 13, 14, 15, 16, 17, 7 });
	}

	@Test
	public void testLcaBSTRecursive() {
		// in empty
		Assert.assertNull(LowestCommonAncestorBST.lcaBSTRecursive(empty, 10, 27));
		// none in tree
		Assert.assertNull(LowestCommonAncestorBST.lcaBSTRecursive(bst, 1, 27));
		// first not in tree
		Assert.assertNull(LowestCommonAncestorBST.lcaBSTRecursive(bst, 1, 10));
		// second not in tree
		Assert.assertNull(LowestCommonAncestorBST.lcaBSTRecursive(bst, 10, 1));

		// root is LCA
		Assert.assertEquals((Integer) 10, LowestCommonAncestorBST.lcaBSTRecursive(bst, 7, 17).data);
		// LCA is one of the nodes
		Assert.assertEquals((Integer) 15, LowestCommonAncestorBST.lcaBSTRecursive(bst, 15, 17).data);
		// LCA in a left subtree
		Assert.assertEquals((Integer) 8, LowestCommonAncestorBST.lcaBSTRecursive(bst, 7, 9).data);
		// LCA in a right subtree
		Assert.assertEquals((Integer) 12, LowestCommonAncestorBST.lcaBSTRecursive(bst, 11, 16).data);
	}

	@Test
	public void testLcaBSTIterative() {
		// in empty
		Assert.assertNull(LowestCommonAncestorBST.lcaBSTIterative(empty, 10, 27));
		// none in tree
		Assert.assertNull(LowestCommonAncestorBST.lcaBSTIterative(bst, 1, 27));
		// first not in tree
		Assert.assertNull(LowestCommonAncestorBST.lcaBSTIterative(bst, 1, 10));
		// second not in tree
		Assert.assertNull(LowestCommonAncestorBST.lcaBSTIterative(bst, 10, 1));

		// root is LCA
		Assert.assertEquals((Integer) 10, LowestCommonAncestorBST.lcaBSTIterative(bst, 7, 17).data);
		// LCA is one of the nodes
		Assert.assertEquals((Integer) 15, LowestCommonAncestorBST.lcaBSTIterative(bst, 15, 17).data);
		// LCA in a left subtree
		Assert.assertEquals((Integer) 8, LowestCommonAncestorBST.lcaBSTIterative(bst, 7, 9).data);
		// LCA in a right subtree
		Assert.assertEquals((Integer) 12, LowestCommonAncestorBST.lcaBSTIterative(bst, 11, 16).data);
	}

}
