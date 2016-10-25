package c06_BinaryTrees;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import c06_BinaryTrees.BinaryTree;
import c06_BinaryTrees.BinaryTreeExtra;
import c06_BinaryTrees.Node;

public class BinaryTreeExtraTest {
	BinaryTreeExtra empty;
	BinaryTreeExtra rootOnly;
	BinaryTreeExtra one;
	BinaryTreeExtra tree;

	@Before
	public void setUp() {
		empty = new BinaryTreeExtra();
		rootOnly = new BinaryTreeExtra(10);
		one = new BinaryTreeExtra(new int[] { 10, 15 });
		tree = new BinaryTreeExtra(new int[] { 10, 8, 12, 6, 9, 11, 13, 14, 15, 16, 17, 7 });
	}

	@Test
	public void testGetTreeHeight_Recursive() {
		// No data, height = -1
		assertEquals(-1, empty.getTreeHeight_Recursive());

		// Root only, height = 0
		assertEquals(0, rootOnly.getTreeHeight_Recursive());

		// One child, height = 1
		assertEquals(1, one.getTreeHeight_Recursive());

		// Multiple elements, height = 6
		assertEquals(6, tree.getTreeHeight_Recursive());
	}

	@Test
	public void testGetTreeHeight_Iterative() {
		// No data, height = -1
		assertEquals(-1, empty.getTreeHeight_Iterative());

		// Root only, height = 0
		assertEquals(0, rootOnly.getTreeHeight_Iterative());

		// One child, height = 1
		assertEquals(1, one.getTreeHeight_Iterative());

		// Multiple elements, height = 6
		assertEquals(6, tree.getTreeHeight_Iterative());
	}

	@Test
	public void testGetKeyDepth_Recursive() {
		// No data
		assertEquals(-1, empty.getKeyDepth_Recursive(10));

		// Root only, value at root
		assertEquals(0, rootOnly.getKeyDepth_Recursive(10));
		// Root only, value notat root
		assertEquals(-1, rootOnly.getKeyDepth_Recursive(20));

		// One child
		assertEquals(0, one.getKeyDepth_Recursive(10));
		assertEquals(1, one.getKeyDepth_Recursive(15));
		assertEquals(-1, one.getKeyDepth_Recursive(20));

		// Multiple elements
		assertEquals(0, tree.getKeyDepth_Recursive(10));
		assertEquals(2, tree.getKeyDepth_Recursive(6));
		assertEquals(-1, tree.getKeyDepth_Recursive(20));
	}

	@Test
	public void testSumOfKeys_Recursive() {
		// No data, sum = -1
		assertEquals(-1, empty.sumOfKeys_Recursive());

		// Root only, sum = 10
		assertEquals(10, rootOnly.sumOfKeys_Recursive());

		// One child, sum = 25
		assertEquals(25, one.sumOfKeys_Recursive());

		// Multiple elements, sum = 138
		assertEquals(138, tree.sumOfKeys_Recursive());
	}

	@Test
	public void testSumOfKeysInOrder_Iterative() {
		// No data, sum = -1
		assertEquals(-1, empty.sumOfKeysInOrder_Iterative());

		// Root only, sum = 10
		assertEquals(10, rootOnly.sumOfKeysInOrder_Iterative());

		// One child, sum = 25
		assertEquals(25, one.sumOfKeysInOrder_Iterative());

		// Multiple elements, sum = 138
		assertEquals(138, tree.sumOfKeysInOrder_Iterative());
	}

	@Test
	public void testGetMinAndMaxValues() {
		// No data
		assertEquals(Integer.MIN_VALUE, empty.getMaxValue());
		assertEquals(Integer.MAX_VALUE, empty.getMinValue());

		// Root only
		assertEquals(10, rootOnly.getMaxValue());
		assertEquals(10, rootOnly.getMinValue());

		// One child
		assertEquals(15, one.getMaxValue());
		assertEquals(10, one.getMinValue());

		// Multiple elements
		assertEquals(17, tree.getMaxValue());
		assertEquals(6, tree.getMinValue());
	}

	@Test
	public void testIsTreeBST() {
		// empty tree
		BinaryTree<Integer> tree = new BinaryTree<>();
		assertTrue(BinaryTreeExtra.isTreeBST(tree));

		// root only
		tree.insert(10);
		assertTrue(BinaryTreeExtra.isTreeBST(tree));

		// not a BST
		tree.clear();
		ArrayList<Node<Integer>> H = new ArrayList<>();
		int[] arrayNotBST = { 1, 2, 3, 4, 5 };
		for (int elem : arrayNotBST)
			tree.insertCompleteArray(elem, H);
		assertFalse(BinaryTreeExtra.isTreeBST(tree));

		// BST tree
		tree.clear();
		H.clear();
		int[] arrayBST = { 10, 5, 13, 4, 6 };
		for (int elem : arrayBST)
			tree.insertCompleteArray(elem, H);
		assertTrue(BinaryTreeExtra.isTreeBST(tree));

		// BST tree with tricky non-direct child violating property
		tree.clear();
		H.clear();
		int[] arrayNotBST2 = { 7, 4, 9, 1, 8 };
		for (int elem : arrayNotBST2)
			tree.insertCompleteArray(elem, H);
		assertFalse(BinaryTreeExtra.isTreeBST(tree));

		// BST tree with duplicates
		tree.clear();
		H.clear();
		int[] arrayBST2 = { 7, 4, 8, 4, 5, 8 };
		for (int elem : arrayBST2)
			tree.insertCompleteArray(elem, H);
		assertTrue(BinaryTreeExtra.isTreeBST(tree));
	}

	@Test
	public void testIsTreeBST_InOrderSorted() {
		// empty tree
		BinaryTree<Integer> tree = new BinaryTree<>();
		assertTrue(BinaryTreeExtra.isTreeBST_InOrderSorted(tree));

		// root only
		tree.insert(10);
		assertTrue(BinaryTreeExtra.isTreeBST_InOrderSorted(tree));

		// not a BST
		tree.clear();
		ArrayList<Node<Integer>> H = new ArrayList<>();
		int[] arrayNotBST = { 1, 2, 3, 4, 5 };
		for (int elem : arrayNotBST)
			tree.insertCompleteArray(elem, H);
		assertFalse(BinaryTreeExtra.isTreeBST_InOrderSorted(tree));

		// BST tree
		tree.clear();
		H.clear();
		int[] arrayBST = { 10, 5, 13, 4, 6 };
		for (int elem : arrayBST)
			tree.insertCompleteArray(elem, H);
		assertTrue(BinaryTreeExtra.isTreeBST_InOrderSorted(tree));

		// BST tree with tricky non-direct child violating property
		tree.clear();
		H.clear();
		int[] arrayNotBST2 = { 7, 4, 9, 1, 8 };
		for (int elem : arrayNotBST2)
			tree.insertCompleteArray(elem, H);
		assertFalse(BinaryTreeExtra.isTreeBST_InOrderSorted(tree));

		// BST tree with duplicates
		tree.clear();
		H.clear();
		int[] arrayBST2 = { 7, 4, 8, 4, 5, 8 };
		for (int elem : arrayBST2)
			tree.insertCompleteArray(elem, H);
		assertTrue(BinaryTreeExtra.isTreeBST_InOrderSorted(tree));
	}

	@Test
	public void testIsEqualTo_Recursive() {
		assertTrue(empty.isEqualTo_Recursive(empty));
		assertFalse(empty.isEqualTo_Recursive(one));
		assertFalse(empty.isEqualTo_Recursive(rootOnly));
		assertFalse(empty.isEqualTo_Recursive(tree));

		assertTrue(one.isEqualTo_Recursive(one));
		assertFalse(one.isEqualTo_Recursive(empty));
		assertFalse(one.isEqualTo_Recursive(rootOnly));
		assertFalse(one.isEqualTo_Recursive(tree));

		assertTrue(rootOnly.isEqualTo_Recursive(rootOnly));
		assertFalse(rootOnly.isEqualTo_Recursive(one));
		assertFalse(rootOnly.isEqualTo_Recursive(empty));
		assertFalse(rootOnly.isEqualTo_Recursive(tree));

		assertTrue(tree.isEqualTo_Recursive(tree));
		assertFalse(tree.isEqualTo_Recursive(one));
		assertFalse(tree.isEqualTo_Recursive(rootOnly));
		assertFalse(tree.isEqualTo_Recursive(empty));
	}

	@Test
	public void testCountDuplicatesInOrder() {
		// one duplicate value
		BinaryTreeExtra treeDups = new BinaryTreeExtra(new int[] { 10, 10 });
		assertEquals(1, treeDups.countDuplicates_Recursive());
		assertEquals(1, treeDups.countDuplicatesInOrder_Iterative());

		// add two more duplicate values
		treeDups.insert(new int[] { 12, 10, 12, 13, 14 });
		assertEquals(3, treeDups.countDuplicates_Recursive());
		assertEquals(3, treeDups.countDuplicatesInOrder_Iterative());

		// No data
		assertEquals(0, empty.countDuplicates_Recursive());
		assertEquals(0, empty.countDuplicatesInOrder_Iterative());

		// Root only
		assertEquals(0, rootOnly.countDuplicates_Recursive());
		assertEquals(0, rootOnly.countDuplicatesInOrder_Iterative());

		// One child
		assertEquals(0, one.countDuplicates_Recursive());
		assertEquals(0, one.countDuplicatesInOrder_Iterative());

		// Multiple elements
		assertEquals(0, tree.countDuplicates_Recursive());
		assertEquals(0, tree.countDuplicatesInOrder_Iterative());
	}
}
