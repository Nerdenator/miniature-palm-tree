package bstProblems_test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import binaryTree_util.InterfaceBinaryTree;
import bstProblems_src.AreTreesEqual;
import bst_src.BinarySearchTree;

public class AreTreesEqualTest {
	InterfaceBinaryTree<Integer> empty;
	InterfaceBinaryTree<Integer> t0;
	InterfaceBinaryTree<Integer> t1;
	InterfaceBinaryTree<Integer> t2;

	@Before
	public void setUp() throws Exception {
		empty = new BinarySearchTree();
		t0 = new BinarySearchTree(10);
		t1 = new BinarySearchTree(new int[] { 10, 15 });
		t2 = new BinarySearchTree(new int[] { 10, 8, 12, 6, 9, 11, 13, 14, 15, 16, 17, 7 });
	}

	@Test
	public void testTreesEqualRecursive() {
		// identity (same objects)
		assertTrue(AreTreesEqual.treesEqualRecursive(empty, empty));
		assertTrue(AreTreesEqual.treesEqualRecursive(t0, t0));
		assertTrue(AreTreesEqual.treesEqualRecursive(t1, t1));
		assertTrue(AreTreesEqual.treesEqualRecursive(t2, t2));

		// some false
		assertFalse(AreTreesEqual.treesEqualRecursive(t0, empty));
		assertFalse(AreTreesEqual.treesEqualRecursive(t1, t2));
		assertFalse(AreTreesEqual.treesEqualRecursive(t0, t1));

		// new objects true
		BinarySearchTree empty2 = new BinarySearchTree();
		assertTrue(AreTreesEqual.treesEqualRecursive(empty, empty2));
		BinarySearchTree t02 = new BinarySearchTree(10);
		assertTrue(AreTreesEqual.treesEqualRecursive(t0, t02));
		BinarySearchTree t12 = new BinarySearchTree(new int[] { 10, 15 });
		assertTrue(AreTreesEqual.treesEqualRecursive(t1, t12));
		BinarySearchTree t22 = new BinarySearchTree(new int[] { 10, 8, 12, 6, 9, 11, 13, 14, 15, 16, 17, 7 });
		assertTrue(AreTreesEqual.treesEqualRecursive(t2, t22));

		// new objects false
		BinarySearchTree t3 = new BinarySearchTree(new int[] { 10, 8, 15 });
		assertFalse(AreTreesEqual.treesEqualRecursive(t3, t1));
	}

	@Test
	public void testTreesEqualIterative() {
		// identity (same objects)
		assertTrue(AreTreesEqual.treesEqualIterative(empty, empty));
		assertTrue(AreTreesEqual.treesEqualIterative(t0, t0));
		assertTrue(AreTreesEqual.treesEqualIterative(t1, t1));
		assertTrue(AreTreesEqual.treesEqualIterative(t2, t2));

		// some false
		assertFalse(AreTreesEqual.treesEqualIterative(t0, empty));
		assertFalse(AreTreesEqual.treesEqualIterative(t1, t2));
		assertFalse(AreTreesEqual.treesEqualIterative(t0, t1));

		// new objects true
		BinarySearchTree empty2 = new BinarySearchTree();
		assertTrue(AreTreesEqual.treesEqualIterative(empty, empty2));
		BinarySearchTree t02 = new BinarySearchTree(10);
		assertTrue(AreTreesEqual.treesEqualIterative(t0, t02));
		BinarySearchTree t12 = new BinarySearchTree(new int[] { 10, 15 });
		assertTrue(AreTreesEqual.treesEqualIterative(t1, t12));
		BinarySearchTree t22 = new BinarySearchTree(new int[] { 10, 8, 12, 6, 9, 11, 13, 14, 15, 16, 17, 7 });
		assertTrue(AreTreesEqual.treesEqualIterative(t2, t22));

		// new objects false
		BinarySearchTree t3 = new BinarySearchTree(new int[] { 10, 8, 15 });
		assertFalse(AreTreesEqual.treesEqualIterative(t3, t1));
	}
}
