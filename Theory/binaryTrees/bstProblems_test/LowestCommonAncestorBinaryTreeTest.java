package bstProblems_test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import binaryTree_src.BinarySearchTree;
import binaryTree_src.BinaryTreeInsertRandomDirection;
import bstProblems_src.LowestCommonAncestorBinaryTree;

public class LowestCommonAncestorBinaryTreeTest {
	BinaryTreeInsertRandomDirection<Integer> empty1;
	BinarySearchTree empty2;
	BinaryTreeInsertRandomDirection<Integer> tree;
	BinarySearchTree bst;

	@Before
	public void setUp() throws Exception {
		empty1 = new BinaryTreeInsertRandomDirection<>();
		empty2 = new BinarySearchTree();

		tree = new BinaryTreeInsertRandomDirection<>();
		tree.insert(1);
		tree.insertLeft(tree.root, 2);
		tree.insertRight(tree.root, 3);
		tree.insertLeft(tree.root.left, 4);
		tree.insertRight(tree.root.left, 5);
		tree.insertLeft(tree.root.right, 6);
		tree.insertRight(tree.root.right, 7);
		tree.insertLeft(tree.root.left.left, 8);
		tree.insertRight(tree.root.left.left, 9);
		tree.insertRight(tree.root.right.right, 10);

		bst = new BinarySearchTree(new int[] { 10, 8, 12, 6, 9, 11, 13, 14, 15, 16, 17, 7 });
	}

	@Test
	public void testLcaBinaryOnBST() {
		// in empty
		Assert.assertNull(LowestCommonAncestorBinaryTree.lcaBinaryTree(empty2, 10, 27));
		// none in tree
		Assert.assertNull(LowestCommonAncestorBinaryTree.lcaBinaryTree(bst, 1, 27));
		// first not in tree
		Assert.assertNull(LowestCommonAncestorBinaryTree.lcaBinaryTree(bst, 1, 10));
		// second not in tree
		Assert.assertNull(LowestCommonAncestorBinaryTree.lcaBinaryTree(bst, 10, 1));

		// root is LCA
		Assert.assertEquals((Integer) 10, LowestCommonAncestorBinaryTree.lcaBinaryTree(bst, 7, 17).data);
		// LCA is one of the nodes
		Assert.assertEquals((Integer) 15, LowestCommonAncestorBinaryTree.lcaBinaryTree(bst, 15, 17).data);
		// LCA in a left subtree
		Assert.assertEquals((Integer) 8, LowestCommonAncestorBinaryTree.lcaBinaryTree(bst, 7, 9).data);
		// LCA in a right subtree
		Assert.assertEquals((Integer) 12, LowestCommonAncestorBinaryTree.lcaBinaryTree(bst, 11, 16).data);
	}

	@Test
	public void testLcaBinaryOnTree() {
		// in empty
		Assert.assertNull(LowestCommonAncestorBinaryTree.lcaBinaryTree(empty1, 10, 27));
		// none in tree
		Assert.assertNull(LowestCommonAncestorBinaryTree.lcaBinaryTree(tree, 20, 27));
		// first not in tree
		Assert.assertNull(LowestCommonAncestorBinaryTree.lcaBinaryTree(tree, 20, 1));
		// second not in tree
		Assert.assertNull(LowestCommonAncestorBinaryTree.lcaBinaryTree(tree, 1, 20));

		// root is LCA
		Assert.assertEquals((Integer) 1, LowestCommonAncestorBinaryTree.lcaBinaryTree(tree, 9, 7).data);
		// LCA is one of the nodes
		Assert.assertEquals((Integer) 3, LowestCommonAncestorBinaryTree.lcaBinaryTree(tree, 3, 10).data);
		// LCA in a left subtree
		Assert.assertEquals((Integer) 2, LowestCommonAncestorBinaryTree.lcaBinaryTree(tree, 4, 5).data);
		// LCA in a right subtree
		Assert.assertEquals((Integer) 3, LowestCommonAncestorBinaryTree.lcaBinaryTree(tree, 10, 6).data);
	}
}
