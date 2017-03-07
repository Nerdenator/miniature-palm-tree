package bstProblems_test;

import org.junit.Before;
import org.junit.Test;

import binaryTree_src.BinaryTreeInsertRandomDirection;
import bstProblems_src.ArithmeticExpressionTree;

public class ArithmeticExpressionTreeTest {

	BinaryTreeInsertRandomDirection<String> tree;

	@Before
	public void setUp() throws Exception {
		tree = new BinaryTreeInsertRandomDirection<>();// 7+3*4-5
		tree.insert("-");
		tree.insertLeft(tree.root, "+");
		tree.insertRight(tree.root, "5");
		tree.insertLeft(tree.root.left, "7");
		tree.insertRight(tree.root.left, "*");
		tree.insertLeft(tree.root.left.right, "3");
		tree.insertRight(tree.root.left.right, "4");
		tree.displayTree();
	}

	@Test
	public void test() {
		System.out.println(ArithmeticExpressionTree.evaluateTree(tree));
	}

}
