package binaryTrees_test;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import binaryTrees_src.BinaryTree;
import binaryTrees_src.Node;

public class BinaryTreeTest {
	private final int treeSize = 8;

	@Test
	public void testBinaryCodeTree() {
		BinaryTree<Integer> treeBinaryCode = new BinaryTree<>();
		int codeVal = -1;
		codeVal = treeBinaryCode.insertUsingBinaryCode(-1, codeVal);
		for (int i = 0; i < 8; i++)
			codeVal = treeBinaryCode.insertUsingBinaryCode(i, codeVal);

		Assert.assertEquals(treeBinaryCode.inOrderTraversal_Recursive(), treeBinaryCode.inOrderTraversal_Iterative());
		Assert.assertEquals(treeBinaryCode.preOrderTraversal_Recursive(), treeBinaryCode.preOrderTraversal_Iterative());
		Assert.assertEquals(treeBinaryCode.postOrderTraversal_Recursive(), treeBinaryCode.postOrderTraversal_Iterative());

		String treeBinaryCodeLevelOrder = "[\n-1\t\n0\t1\t\n2\t3\t4\t5\t\n6\t7\t-\t-\t-\t-\t-\t-\t\n-\t-\t-\t-\t\n]";
		String treeBinaryCodeInOrder = "[ 6 2 7 0 3 -1 4 1 5 ]";
		String treeBinaryCodePreOrder = "[ -1 0 2 6 7 3 1 4 5 ]";
		String treeBinaryCodePostOrder = "[ 6 7 2 3 0 4 5 1 -1 ]";

		Assert.assertEquals(treeBinaryCodeLevelOrder, treeBinaryCode.levelOrderTraversal());
		Assert.assertEquals(treeBinaryCodeInOrder, treeBinaryCode.inOrderTraversal_Recursive());
		Assert.assertEquals(treeBinaryCodeInOrder, treeBinaryCode.inOrderTraversal_Iterative());
		Assert.assertEquals(treeBinaryCodePreOrder, treeBinaryCode.preOrderTraversal_Recursive());
		Assert.assertEquals(treeBinaryCodePreOrder, treeBinaryCode.preOrderTraversal_Iterative());
		Assert.assertEquals(treeBinaryCodePostOrder, treeBinaryCode.postOrderTraversal_Recursive());
		Assert.assertEquals(treeBinaryCodePostOrder, treeBinaryCode.postOrderTraversal_Iterative());

		Node<Integer> n = treeBinaryCode.find(3);
		Assert.assertEquals((Integer) 3, n.data);
		n = treeBinaryCode.find(0);
		Assert.assertEquals((Integer) 0, n.data);

		n = treeBinaryCode.delete(0);
		Assert.assertEquals((Integer) 0, n.data);
		n = treeBinaryCode.delete(1);
		Assert.assertEquals((Integer) 1, n.data);
		n = treeBinaryCode.delete(6);
		Assert.assertEquals((Integer) 6, n.data);
		n = treeBinaryCode.delete(1);
		Assert.assertNull(n);
	}

	@Test
	public void testConvertToBinary() {
		Assert.assertEquals("0", BinaryTree.convertToBinary(0));
		Assert.assertEquals("1", BinaryTree.convertToBinary(1));
		Assert.assertEquals("10", BinaryTree.convertToBinary(2));
		Assert.assertEquals("11", BinaryTree.convertToBinary(3));
		Assert.assertEquals("100", BinaryTree.convertToBinary(4));
		Assert.assertEquals("101", BinaryTree.convertToBinary(5));
		Assert.assertEquals("110", BinaryTree.convertToBinary(6));
		Assert.assertEquals("111", BinaryTree.convertToBinary(7));
	}

	@Test
	public void testRandomTree() {
		BinaryTree<Integer> treeRandom = new BinaryTree<Integer>();
		for (int i = 0; i < treeSize; i++)
			treeRandom.insert(i);
		Assert.assertEquals(treeRandom.inOrderTraversal_Recursive(), treeRandom.inOrderTraversal_Iterative());
		Assert.assertEquals(treeRandom.preOrderTraversal_Recursive(), treeRandom.preOrderTraversal_Iterative());
		Assert.assertEquals(treeRandom.postOrderTraversal_Recursive(), treeRandom.postOrderTraversal_Iterative());
	}

	@Test
	public void testCompleteArray() {

		BinaryTree<Integer> treeCompleteArray = new BinaryTree<Integer>();
		ArrayList<Node<Integer>> H = new ArrayList<>();
		for (int i = 0; i < treeSize; i++)
			treeCompleteArray.insertCompleteArray(i, H);

		String treeCompleteArrayInOrder = "[ 7 3 1 4 0 5 2 6 ]";
		String treeCompleteArrayPreOrder = "[ 0 1 3 7 4 2 5 6 ]";
		String treeCompleteArrayPostOrder = "[ 7 3 4 1 5 6 2 0 ]";
		String treeCompleteArrayLevelOrder = "[\n0\t\n1\t2\t\n3\t4\t5\t6\t\n7\t-\t-\t-\t-\t-\t-\t-\t\n-\t-\t\n]";

		Assert.assertEquals(treeCompleteArrayLevelOrder, treeCompleteArray.levelOrderTraversal());
		Assert.assertEquals(treeCompleteArray.inOrderTraversal_Recursive(), treeCompleteArray.inOrderTraversal_Iterative());
		Assert.assertEquals(treeCompleteArray.preOrderTraversal_Recursive(), treeCompleteArray.preOrderTraversal_Iterative());
		Assert.assertEquals(treeCompleteArray.postOrderTraversal_Recursive(), treeCompleteArray.postOrderTraversal_Iterative());
		Assert.assertEquals(treeCompleteArrayPreOrder, treeCompleteArray.preOrderTraversal_Recursive());
		Assert.assertEquals(treeCompleteArrayPreOrder, treeCompleteArray.preOrderTraversal_Iterative());
		Assert.assertEquals(treeCompleteArrayPostOrder, treeCompleteArray.postOrderTraversal_Recursive());
		Assert.assertEquals(treeCompleteArrayPostOrder, treeCompleteArray.postOrderTraversal_Iterative());
		Assert.assertEquals(treeCompleteArrayInOrder, treeCompleteArray.inOrderTraversal_Recursive());
		Assert.assertEquals(treeCompleteArrayInOrder, treeCompleteArray.inOrderTraversal_Iterative());

		Node<Integer> n = treeCompleteArray.find(3);
		Assert.assertEquals((Integer) 3, n.data);
		n = treeCompleteArray.find(0);
		Assert.assertEquals((Integer) 0, n.data);

		n = treeCompleteArray.delete(0);
		Assert.assertEquals((Integer) 0, n.data);
		n = treeCompleteArray.delete(1);
		Assert.assertEquals((Integer) 1, n.data);
		n = treeCompleteArray.delete(6);
		Assert.assertEquals((Integer) 6, n.data);
		n = treeCompleteArray.delete(1);
		Assert.assertNull(n);
	}

}
