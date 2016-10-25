package c06_BinaryTrees;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import c06_BinaryTrees.BinarySearchTree;
import c06_BinaryTrees.Node;

public class BinarySearchTreeTest {
	BinarySearchTree treeInsert;
	BinarySearchTree tree;
	int[] arr = new int[] { 10, 15, 5, 4, 12, 8, 17, 1, 3, 20, 22, 16 };

	@Before
	public void setUp() {
		/*
		 *               			  10                                                              
		 *               5                              15                              
		 *       4              8              12              17              
		 *   1      --      --      --      --      --      16      20      
		 * --  3  --  --  --  --  --  --  --  --  --  --  --  --  --  22  
		 */
		treeInsert = new BinarySearchTree();

		tree = new BinarySearchTree(arr);
	}

	@Test
	public void testInsertIter() {
		for (int i = 0; i < arr.length; i++)
			treeInsert.insert(arr[i]);
		Assert.assertEquals("[ 1 3 4 5 8 10 12 15 16 17 20 22 ]", treeInsert.inOrderTraversal_Recursive());
	}

	@Test
	public void testInsertRec() {
		for (int i = 0; i < arr.length; i++)
			treeInsert.insertRecursive(arr[i]);
		Assert.assertEquals("[ 1 3 4 5 8 10 12 15 16 17 20 22 ]", treeInsert.inOrderTraversal_Recursive());
	}

	@Test
	public void testFindParentIterative() {
		Node<Integer> par = null;

		treeInsert.findParentIterative(10);
		Assert.assertNull(par);

		par = tree.findParentIterative(10);
		Assert.assertNull(par);

		par = tree.findParentIterative(11);
		Assert.assertNull(par);

		par = tree.findParentIterative(5);
		Assert.assertEquals((Integer) 10, par.data);

		par = tree.findParentIterative(15);
		Assert.assertEquals((Integer) 10, par.data);

		par = tree.findParentIterative(3);
		Assert.assertEquals((Integer) 1, par.data);

		par = tree.findParentIterative(22);
		Assert.assertEquals((Integer) 20, par.data);
	}

	@Test
	public void testFindParentRecursive() {
		Node<Integer> par = null;

		treeInsert.findParentRecursive(10);
		Assert.assertNull(par);

		par = tree.findParentRecursive(11);
		Assert.assertNull(par);

		par = tree.findParentRecursive(5);
		Assert.assertEquals((Integer) 10, par.data);

		par = tree.findParentRecursive(15);
		Assert.assertEquals((Integer) 10, par.data);

		par = tree.findParentRecursive(3);
		Assert.assertEquals((Integer) 1, par.data);

		par = tree.findParentRecursive(22);
		Assert.assertEquals((Integer) 20, par.data);
	}

	@Test
	public void testFindIterative() {
		Node<Integer> found = null;

		found = treeInsert.find(3);
		Assert.assertNull(found);

		found = tree.find(11);
		Assert.assertNull(found);

		found = tree.find(10);
		Assert.assertEquals((Integer) 10, found.data);

		found = tree.find(5);
		Assert.assertEquals((Integer) 5, found.data);

		found = tree.find(15);
		Assert.assertEquals((Integer) 15, found.data);

		found = tree.find(3);
		Assert.assertEquals((Integer) 3, found.data);

		found = tree.find(22);
		Assert.assertEquals((Integer) 22, found.data);
	}

	@Test
	public void testFindRecursive() {
		Node<Integer> found = null;

		found = treeInsert.findRecursive(3);
		Assert.assertNull(found);

		found = tree.findRecursive(11);
		Assert.assertNull(found);

		found = tree.findRecursive(10);
		Assert.assertEquals((Integer) 10, found.data);

		found = tree.findRecursive(5);
		Assert.assertEquals((Integer) 5, found.data);

		found = tree.findRecursive(15);
		Assert.assertEquals((Integer) 15, found.data);

		found = tree.findRecursive(3);
		Assert.assertEquals((Integer) 3, found.data);

		found = tree.findRecursive(22);
		Assert.assertEquals((Integer) 22, found.data);
	}

	@Test
	public void testDeleteLeafNodesOrNone() {
		Node<Integer> del;
		//Deleting the root in a one-node tree
		Assert.assertEquals("[ ]", treeInsert.inOrderTraversal_Recursive());
		treeInsert.insert(10);
		Assert.assertEquals("[ 10 ]", treeInsert.inOrderTraversal_Recursive());
		del = treeInsert.delete(10);
		Assert.assertEquals("[ ]", treeInsert.inOrderTraversal_Recursive());
		Assert.assertEquals((Integer) 10, del.data);

		// Deleting leaves
		Assert.assertEquals("[ 1 3 4 5 8 10 12 15 16 17 20 22 ]", tree.inOrderTraversal_Recursive());
		del = tree.delete(3);
		Assert.assertEquals("[ 1 4 5 8 10 12 15 16 17 20 22 ]", tree.inOrderTraversal_Recursive());
		Assert.assertEquals((Integer) 3, del.data);
		del = tree.delete(8);
		Assert.assertEquals("[ 1 4 5 10 12 15 16 17 20 22 ]", tree.inOrderTraversal_Recursive());
		Assert.assertEquals((Integer) 8, del.data);
		del = tree.delete(12);
		Assert.assertEquals("[ 1 4 5 10 15 16 17 20 22 ]", tree.inOrderTraversal_Recursive());
		Assert.assertEquals((Integer) 12, del.data);
		del = tree.delete(16);
		Assert.assertEquals("[ 1 4 5 10 15 17 20 22 ]", tree.inOrderTraversal_Recursive());
		Assert.assertEquals((Integer) 16, del.data);
		del = tree.delete(22);
		Assert.assertEquals("[ 1 4 5 10 15 17 20 ]", tree.inOrderTraversal_Recursive());
		Assert.assertEquals((Integer) 22, del.data);

		// Deleting a node that doesn't exist
		del = tree.delete(22);
		Assert.assertEquals("[ 1 4 5 10 15 17 20 ]", tree.inOrderTraversal_Recursive());
		Assert.assertNull(del);
		del = tree.delete(16);
		Assert.assertEquals("[ 1 4 5 10 15 17 20 ]", tree.inOrderTraversal_Recursive());
		Assert.assertNull(del);
	}

	@Test
	public void testDeleteOneChildNodes() {
		Node<Integer> del;

		treeInsert.insert(new int[] { 10, 20, 21 });
		Assert.assertEquals("[ 10 20 21 ]", treeInsert.inOrderTraversal_Recursive());

		// Deleting the root node that doesn't have a left child
		del = treeInsert.delete(10);
		Assert.assertEquals("[ 20 21 ]", treeInsert.inOrderTraversal_Recursive());
		Assert.assertEquals((Integer) 10, del.data);

		treeInsert.clear();
		treeInsert.insert(new int[] { 10, 9, 8 });
		Assert.assertEquals("[ 8 9 10 ]", treeInsert.inOrderTraversal_Recursive());

		// Deleting the root node that doesn't have a right child
		del = treeInsert.delete(10);
		Assert.assertEquals("[ 8 9 ]", treeInsert.inOrderTraversal_Recursive());
		Assert.assertEquals((Integer) 10, del.data);

		// Deleting a node that doesn't have a left child
		Assert.assertEquals("[ 1 3 4 5 8 10 12 15 16 17 20 22 ]", tree.inOrderTraversal_Recursive());
		del = tree.delete(1);
		Assert.assertEquals("[ 3 4 5 8 10 12 15 16 17 20 22 ]", tree.inOrderTraversal_Recursive());
		Assert.assertEquals((Integer) 1, del.data);

		// Deleting a node that doesn't have a right child
		del = tree.delete(4);
		Assert.assertEquals("[ 3 5 8 10 12 15 16 17 20 22 ]", tree.inOrderTraversal_Recursive());
		Assert.assertEquals((Integer) 4, del.data);
	}

	@Test
	public void testDeleteComplex() {
		Node<Integer> del;

		Assert.assertEquals("[ 1 3 4 5 8 10 12 15 16 17 20 22 ]", tree.inOrderTraversal_Recursive());

		// Deleting the root node with two children
		del = tree.delete(10);
		Assert.assertEquals("[ 1 3 4 5 8 12 15 16 17 20 22 ]", tree.inOrderTraversal_Recursive());
		Assert.assertEquals((Integer) 10, del.data);

		// Deleting a node whose in-order successor is its immediate right child (20)
		treeInsert.insert(new int[] { 18, 5, 4, 20, 30, 12, 15, 10, 6, 9, 11 });
		Assert.assertEquals("[ 4 5 6 9 10 11 12 15 18 20 30 ]", treeInsert.inOrderTraversal_Recursive());
		del = treeInsert.delete(20);
		Assert.assertEquals("[ 4 5 6 9 10 11 12 15 18 30 ]", treeInsert.inOrderTraversal_Recursive());
		Assert.assertEquals((Integer) 20, del.data);
		treeInsert.displayTree();

		//Deleting a node whose successor has right children and is the leftmost child of its right subtree (5)
		treeInsert.clear();
		treeInsert.insert(new int[] { 18, 5, 4, 20, 30, 12, 15, 10, 6, 9, 11 });
		treeInsert.displayTree();
		Assert.assertEquals("[ 4 5 6 9 10 11 12 15 18 20 30 ]", treeInsert.inOrderTraversal_Recursive());
		del = treeInsert.delete(5);
		Assert.assertEquals("[ 4 6 9 10 11 12 15 18 20 30 ]", treeInsert.inOrderTraversal_Recursive());
		Assert.assertEquals((Integer) 5, del.data);
		treeInsert.displayTree();
	}

	@Test
	public void testTraversals() {
		BinarySearchTree tree = new BinarySearchTree(new int[] { 10, 15, 5, 4, 12, 8, 17, 1, 3, 20, 22, 16 });
		Assert.assertEquals("[ 1 3 4 5 8 10 12 15 16 17 20 22 ]", tree.inOrderTraversal_Recursive());
		Assert.assertEquals("[ 1 3 4 5 8 10 12 15 16 17 20 22 ]", tree.inOrderTraversal_Iterative());
		Assert.assertEquals("[ 10 5 4 1 3 8 15 12 17 16 20 22 ]", tree.preOrderTraversal_Recursive());
		Assert.assertEquals("[ 10 5 4 1 3 8 15 12 17 16 20 22 ]", tree.preOrderTraversal_Iterative());
		Assert.assertEquals("[ 3 1 4 8 5 12 16 22 20 17 15 10 ]", tree.postOrderTraversal_Recursive());
		Assert.assertEquals("[ 3 1 4 8 5 12 16 22 20 17 15 10 ]", tree.postOrderTraversal_Iterative());
		Assert.assertEquals(
				"[\n10\t\n5\t15\t\n4\t8\t12\t17\t\n1\t-\t-\t-\t-\t-\t16\t20\t\n-\t3\t-\t-\t-\t22\t\n-\t-\t-\t-\t\n]",
				tree.levelOrderTraversal());
	}
}
