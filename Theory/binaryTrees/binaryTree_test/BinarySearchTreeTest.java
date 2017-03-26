package binaryTree_test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import binaryTree_src.BinarySearchTree;
import binaryTree_util.Node;

public class BinarySearchTreeTest {
	BinarySearchTree t1, t2;
	int[] arr = new int[] { 10, 15, 5, 4, 12, 8, 17, 1, 3, 20, 22, 16 };

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
	public void testInsertIterative() {
		// from empty
		t1.insertIterative(10);
		Assert.assertEquals((Integer) 10, t1.root.data);

		// add left child
		t1.insertIterative(5);
		Assert.assertEquals((Integer) 5, t1.root.left.data);
		// add left.left
		t1.insertIterative(2);
		Assert.assertEquals((Integer) 2, t1.root.left.left.data);
		// add left.right
		t1.insertIterative(7);
		Assert.assertEquals((Integer) 7, t1.root.left.right.data);

		// add right child
		t1.insertIterative(25);
		Assert.assertEquals((Integer) 25, t1.root.right.data);
		// add right.left
		t1.insertIterative(12);
		Assert.assertEquals((Integer) 12, t1.root.right.left.data);
		// add right.right
		t1.insertIterative(27);
		Assert.assertEquals((Integer) 27, t1.root.right.right.data);
	}

	@Test
	public void testInsertRecursive() {
		// from empty
		t1.insertRecursive(10);
		Assert.assertEquals((Integer) 10, t1.root.data);

		// add left child
		t1.insertRecursive(5);
		Assert.assertEquals((Integer) 5, t1.root.left.data);
		// add left.left
		t1.insertRecursive(2);
		Assert.assertEquals((Integer) 2, t1.root.left.left.data);
		// add left.right
		t1.insertRecursive(7);
		Assert.assertEquals((Integer) 7, t1.root.left.right.data);

		// add right child
		t1.insertRecursive(25);
		Assert.assertEquals((Integer) 25, t1.root.right.data);
		// add right.left
		t1.insertRecursive(12);
		Assert.assertEquals((Integer) 12, t1.root.right.left.data);
		// add right.right
		t1.insertRecursive(27);
		Assert.assertEquals((Integer) 27, t1.root.right.right.data);
	}

	@Test
	public void testDeleteLeafNodesOrNone() {
		Node<Integer> del;

		// Deleting the root in a one-node tree
		Assert.assertNull(t1.root);
		t1.insert(10);
		Assert.assertEquals((Integer) 10, t1.root.data);
		del = t1.delete(10);
		Assert.assertNull(t1.root);
		Assert.assertEquals((Integer) 10, del.data);

		// Deleting leaves
		// t2.displayTree();
		Assert.assertEquals((Integer) 3, t2.root.left.left.left.right.data);
		del = t2.delete(3);
		Assert.assertNull(t2.root.left.left.left.right);
		Assert.assertEquals((Integer) 3, del.data);

		Assert.assertEquals((Integer) 8, t2.root.left.right.data);
		del = t2.delete(8);
		Assert.assertNull(t2.root.left.right);
		Assert.assertEquals((Integer) 8, del.data);

		Assert.assertEquals((Integer) 12, t2.root.right.left.data);
		del = t2.delete(12);
		Assert.assertNull(t2.root.right.left);
		Assert.assertEquals((Integer) 12, del.data);

		Assert.assertEquals((Integer) 16, t2.root.right.right.left.data);
		del = t2.delete(16);
		Assert.assertNull(t2.root.right.right.left);
		Assert.assertEquals((Integer) 16, del.data);

		Assert.assertEquals((Integer) 22, t2.root.right.right.right.right.data);
		del = t2.delete(22);
		Assert.assertNull(t2.root.right.right.right.right);
		Assert.assertEquals((Integer) 22, del.data);

		// Deleting a node that doesn't exist
		del = t2.delete(22);
		Assert.assertNull(del);

		del = t2.delete(16);
		Assert.assertNull(del);
	}

	@Test
	public void testDeleteOneChildNodes() {
		Node<Integer> del;

		t1.insert(new int[] { 10, 20, 21 });
		// t1.displayTree();

		// Deleting the root node that doesn't have a left child
		Assert.assertEquals((Integer) 10, t1.root.data);
		del = t1.delete(10);
		Assert.assertEquals((Integer) 20, t1.root.data);
		Assert.assertEquals((Integer) 10, del.data);

		t1.clear();
		t1.insert(new int[] { 10, 9, 8 });
		// t1.displayTree();

		// Deleting the root node that doesn't have a right child
		Assert.assertEquals((Integer) 10, t1.root.data);
		del = t1.delete(10);
		Assert.assertEquals((Integer) 9, t1.root.data);
		Assert.assertEquals((Integer) 10, del.data);

		// Deleting a node that doesn't have a left child
		// t2.displayTree();
		Assert.assertEquals((Integer) 1, t2.root.left.left.left.data);
		del = t2.delete(1);
		Assert.assertEquals((Integer) 3, t2.root.left.left.left.data);
		Assert.assertEquals((Integer) 1, del.data);

		// Deleting a node that doesn't have a right child
		Assert.assertEquals((Integer) 4, t2.root.left.left.data);
		del = t2.delete(4);
		Assert.assertEquals((Integer) 3, t2.root.left.left.data);
		Assert.assertEquals((Integer) 4, del.data);
	}

	@Test
	public void testDeleteComplex() {
		Node<Integer> del;

		// Deleting the root node with two children
		// t2.displayTree();
		Assert.assertEquals((Integer) 10, t2.root.data);
		Assert.assertEquals((Integer) 12, t2.root.right.left.data);
		del = t2.delete(10);
		// t2.displayTree();
		Assert.assertEquals((Integer) 12, t2.root.data);
		Assert.assertEquals((Integer) 10, del.data);
		Assert.assertNull(t2.root.right.left);

		// Deleting a node whose in-order successor is its immediate right child
		// (20)
		t1.insert(new int[] { 18, 5, 4, 20, 30, 12, 15, 10, 6, 9, 11 });
		// t1.displayTree();
		Assert.assertEquals((Integer) 20, t1.root.right.data);
		Assert.assertEquals((Integer) 30, t1.root.right.right.data);
		del = t1.delete(20);
		// t1.displayTree();
		Assert.assertEquals((Integer) 30, t1.root.right.data);
		Assert.assertEquals((Integer) 20, del.data);
		Assert.assertNull(t1.root.right.right);

		// Deleting a node whose successor has right children and is the
		// leftmost child of its right subtree (5)
		t1.clear();
		t1.insert(new int[] { 18, 5, 4, 20, 30, 12, 15, 10, 6, 9, 11 });
		// t1.displayTree();
		Assert.assertEquals((Integer) 5, t1.root.left.data);
		Assert.assertEquals((Integer) 6, t1.root.left.right.left.left.data);
		Assert.assertEquals((Integer) 9, t1.root.left.right.left.left.right.data);
		del = t1.delete(5);
		// t1.displayTree();
		Assert.assertEquals((Integer) 6, t1.root.left.data);
		Assert.assertEquals((Integer) 9, t1.root.left.right.left.left.data);
		Assert.assertNull(t1.root.left.right.left.left.right);
		Assert.assertEquals((Integer) 5, del.data);
	}

	@Test
	public void testFindParentIterative() {
		Node<Integer> par = null;

		// in an empty tree
		t1.findParentIterative(10);
		Assert.assertNull(par);

		// the root node
		par = t2.findParentIterative(10);
		Assert.assertNull(par);

		// a node that doesn't exist
		par = t2.findParentIterative(11);
		Assert.assertNull(par);

		// nodes that have parents
		par = t2.findParentIterative(5);
		Assert.assertEquals((Integer) 10, par.data);

		par = t2.findParentIterative(15);
		Assert.assertEquals((Integer) 10, par.data);

		par = t2.findParentIterative(3);
		Assert.assertEquals((Integer) 1, par.data);

		par = t2.findParentIterative(22);
		Assert.assertEquals((Integer) 20, par.data);
	}

	@Test
	public void testFindParentRecursive() {
		Node<Integer> par = null;

		// in an empty tree
		t1.findParentRecursive(10);
		Assert.assertNull(par);

		// the root node
		par = t2.findParentRecursive(10);
		Assert.assertNull(par);

		// a node that doesn't exist
		par = t2.findParentRecursive(11);
		Assert.assertNull(par);

		// nodes that have parents
		par = t2.findParentRecursive(5);
		Assert.assertEquals((Integer) 10, par.data);

		par = t2.findParentRecursive(15);
		Assert.assertEquals((Integer) 10, par.data);

		par = t2.findParentRecursive(3);
		Assert.assertEquals((Integer) 1, par.data);

		par = t2.findParentRecursive(22);
		Assert.assertEquals((Integer) 20, par.data);
	}

	@Test
	public void testFindIterative() {
		Node<Integer> found = null;

		found = t1.findIterative(3);
		Assert.assertNull(found);

		found = t2.findIterative(11);
		Assert.assertNull(found);

		// t2.displayTree();
		found = t2.findIterative(10);
		Assert.assertEquals((Integer) 10, found.data);
		Assert.assertEquals(t2.root, found);

		found = t2.findIterative(5);
		Assert.assertEquals((Integer) 5, found.data);
		Assert.assertEquals(t2.root.left, found);

		found = t2.findIterative(15);
		Assert.assertEquals((Integer) 15, found.data);
		Assert.assertEquals(t2.root.right, found);

		found = t2.findIterative(3);
		Assert.assertEquals((Integer) 3, found.data);
		Assert.assertEquals(t2.root.left.left.left.right, found);

		found = t2.findIterative(22);
		Assert.assertEquals((Integer) 22, found.data);
		Assert.assertEquals(t2.root.right.right.right.right, found);
	}

	@Test
	public void testFindRecursive() {
		Node<Integer> found = null;

		found = t1.findRecursive(3);
		Assert.assertNull(found);

		found = t2.findRecursive(11);
		Assert.assertNull(found);

		// t2.displayTree();
		found = t2.findRecursive(10);
		Assert.assertEquals((Integer) 10, found.data);
		Assert.assertEquals(t2.root, found);

		found = t2.findRecursive(5);
		Assert.assertEquals((Integer) 5, found.data);
		Assert.assertEquals(t2.root.left, found);

		found = t2.findRecursive(15);
		Assert.assertEquals((Integer) 15, found.data);
		Assert.assertEquals(t2.root.right, found);

		found = t2.findRecursive(3);
		Assert.assertEquals((Integer) 3, found.data);
		Assert.assertEquals(t2.root.left.left.left.right, found);

		found = t2.findRecursive(22);
		Assert.assertEquals((Integer) 22, found.data);
		Assert.assertEquals(t2.root.right.right.right.right, found);
	}

	@Test
	public void testFindUsingFindParentIterative() {
		Node<Integer> found = null;

		found = t1.findUsingFindParentIterative(3);
		Assert.assertNull(found);

		found = t2.findUsingFindParentIterative(11);
		Assert.assertNull(found);

		found = t2.findUsingFindParentIterative(10);
		Assert.assertEquals((Integer) 10, found.data);
		Assert.assertEquals(t2.root, found);

		found = t2.findUsingFindParentIterative(5);
		Assert.assertEquals((Integer) 5, found.data);
		Assert.assertEquals(t2.root.left, found);

		found = t2.findUsingFindParentIterative(15);
		Assert.assertEquals((Integer) 15, found.data);
		Assert.assertEquals(t2.root.right, found);

		// t2.displayTree();
		found = t2.findUsingFindParentIterative(3);
		Assert.assertEquals((Integer) 3, found.data);
		Assert.assertEquals(t2.root.left.left.left.right, found);

		found = t2.findUsingFindParentIterative(22);
		Assert.assertEquals((Integer) 22, found.data);
		Assert.assertEquals(t2.root.right.right.right.right, found);
	}
}
