package bstProblems_test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import binaryTree_src.BinaryTreeInsertRandomDirection;
import bstProblems_src.IsTreeBST;
import bst_src.BinarySearchTree;

public class IsTreeBSTTest {
	BinaryTreeInsertRandomDirection<Integer> empty;
	BinaryTreeInsertRandomDirection<Integer> t0;
	BinarySearchTree t1;
	BinaryTreeInsertRandomDirection<Integer> t2;
	BinaryTreeInsertRandomDirection<Integer> t3;

	@Before
	public void setUp() throws Exception {
		// empty
		empty = new BinaryTreeInsertRandomDirection<>();

		// one element
		t0 = new BinaryTreeInsertRandomDirection<Integer>();
		t0.insert(10);

		// BST-type
		t1 = new BinarySearchTree(new int[] { 10, 8, 12, 6, 9, 11, 13, 14, 15, 16, 17, 7 });

		// non-BST-type but BST
		t2 = new BinaryTreeInsertRandomDirection<Integer>();
		t2.insert(10);
		t2.insertRight(t2.root, 15);
		t2.insertLeft(t2.root, 5);
		t2.insertLeft(t2.root.left, 4);
		t2.insertLeft(t2.root.right, 12);
		t2.insertRight(t2.root.right, 24);
		t2.insertLeft(t2.root.right.right, 17);

		// non-BST-type and not BST
		t3 = new BinaryTreeInsertRandomDirection<Integer>();
		t3.insert(10);
		t3.insertRight(t3.root, 15);
		t3.insertLeft(t3.root, 5);
		t3.insertLeft(t3.root.left, 4);
		t3.insertLeft(t3.root.right, 12);
		t3.insertRight(t3.root.right, 24);
		t3.insertRight(t3.root.left, 17);
	}

	@Test
	public void testIsTreeBST() {
		Assert.assertTrue(IsTreeBST.isTreeBST(empty));
		Assert.assertTrue(IsTreeBST.isTreeBST(t0));
		Assert.assertTrue(IsTreeBST.isTreeBST(t1));
		Assert.assertTrue(IsTreeBST.isTreeBST(t2));
		Assert.assertFalse(IsTreeBST.isTreeBST(t3));
	}

	@Test
	public void testIsTreeInOrderSortedArray() {
		Assert.assertTrue(IsTreeBST.isTreeBSTInOrderSortedArray(empty));
		Assert.assertTrue(IsTreeBST.isTreeBSTInOrderSortedArray(t0));
		Assert.assertTrue(IsTreeBST.isTreeBSTInOrderSortedArray(t1));
		Assert.assertTrue(IsTreeBST.isTreeBSTInOrderSortedArray(t2));
		Assert.assertFalse(IsTreeBST.isTreeBSTInOrderSortedArray(t3));
	}

}
