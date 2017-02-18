package bst_test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import bst_src.BinarySearchTree;
import bst_src.BinaryTreeTraversals;

public class BinaryTreeTraversalsTest {
	BinarySearchTree t1, t2;
	int[] arr = new int[] { 10, 15, 5, 4, 12, 8, 17, 1, 3, 20, 22, 16 };
	String pre = "[ 10 5 4 1 3 8 15 12 17 16 20 22 ]";
	String post = "[ 3 1 4 8 5 12 16 22 20 17 15 10 ]";
	String in = "[ 1 3 4 5 8 10 12 15 16 17 20 22 ]";
	String lev = "[ 10 | 5 15 | 4 8 12 17 | 1 16 20 | 3 22 | ]";

	@Before
	public void setUp() {
		t1 = new BinarySearchTree();
		t2 = new BinarySearchTree(arr);
		// t2.displayTree();
		/*
		 * ..............10
		 * .........../.......\
		 * ........../.........\
		 * ........./...........\
		 * .........5............15
		 * ....../...\........./...\
		 * .....4.....8.......12....17
		 * .../..\.../..\.../..\.../..\
		 * ..1....#.#....#.#....# 16...20
		 * ../.\................../..\./..\
		 * #...3.................#...#.#...22
		 */
	}

	@Test
	public void testPreOrder() {
		Assert.assertEquals(pre, BinaryTreeTraversals.preOrderTraversalIterative(t2));
		Assert.assertEquals("[ ]", BinaryTreeTraversals.preOrderTraversalIterative(t1));

		Assert.assertEquals(pre, BinaryTreeTraversals.preOrderTraversalRecursive(t2));
		Assert.assertEquals("[ ]", BinaryTreeTraversals.preOrderTraversalRecursive(t1));
	}

	@Test
	public void testPostOrder() {
		Assert.assertEquals(post, BinaryTreeTraversals.postOrderTraversalIterative(t2));
		Assert.assertEquals("[ ]", BinaryTreeTraversals.postOrderTraversalIterative(t1));

		Assert.assertEquals(post, BinaryTreeTraversals.postOrderTraversalRecursive(t2));
		Assert.assertEquals("[ ]", BinaryTreeTraversals.postOrderTraversalRecursive(t1));
	}

	@Test
	public void testInOrder() {
		Assert.assertEquals(in, BinaryTreeTraversals.inOrderTraversalIterative(t2));
		Assert.assertEquals("[ ]", BinaryTreeTraversals.inOrderTraversalIterative(t1));

		Assert.assertEquals(in, BinaryTreeTraversals.inOrderTraversalRecursive(t2));
		Assert.assertEquals("[ ]", BinaryTreeTraversals.inOrderTraversalRecursive(t1));
	}

	@Test
	public void testLevelOrder() {
		Assert.assertEquals(lev, BinaryTreeTraversals.levelOrderTraversal(t2));
		Assert.assertEquals("[ ]", BinaryTreeTraversals.levelOrderTraversal(t1));
	}
}
