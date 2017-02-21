package bstProblems_src;

import binaryTree_util.InterfaceBinaryTree;
import binaryTree_util.Node;

public class IsTreeSymmetric {
	public static <MyType> boolean isTreeSymmetric(InterfaceBinaryTree<MyType> tree) {
		if (tree.root() == null)
			return true;
		return isSymmetric(tree.root().left, tree.root().right);
	}

	private static <MyType> boolean isSymmetric(Node<MyType> n1, Node<MyType> n2) {
		if (n1 == null && n2 == null)
			return true;
		if (n1 == null)
			return false;
		if (n2 == null)
			return false;
		// if trees are mirror images then:
		// 1) root node is the same
		if (!n1.data.equals(n2.data))
			return false;
		// 2) left subtree of left node and right subtree of right node are the
		// same
		if (!isSymmetric(n1.left, n2.right))
			return false;
		// 3) right subtree of left node and left subtree of right node are the
		// same
		if (!isSymmetric(n1.right, n2.left))
			return false;
		return true;
	}
}
