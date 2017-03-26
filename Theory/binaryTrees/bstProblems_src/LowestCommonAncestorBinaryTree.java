package bstProblems_src;

import java.util.Stack;

import binaryTree_util.InterfaceBinaryTree;
import binaryTree_util.Node;

/**
 * Get the Lowest Common Ancestor given two nodes in a binary tree. If
 * given values, ensure they exist in the tree
 * 
 * @author adina
 */
public class LowestCommonAncestorBinaryTree {
	/**
	 * Get the LCA in any binary tree recursively
	 * 
	 * @param tree: the binary tree
	 * @param n1: the value of first node
	 * @param n2: the value of second node
	 * @return: a reference to the lowest common ancestor
	 */
	public static <MyType> Node<MyType> lcaBinaryTree(InterfaceBinaryTree<MyType> tree, MyType v1, MyType v2) {
		// get the first node
		Node<MyType> n1 = findNodeBinaryTree(tree.root(), v1);
		// get the second node
		Node<MyType> n2 = findNodeBinaryTree(tree.root(), v2);

		if (n1 == null || n2 == null)
			return null;

		return lcaBinaryTree(tree.root(), n1, n2);
	}

	/**
	 * Get the LCA in any binary tree recursively
	 * 
	 * @param tree: the binary tree
	 * @param n1: the value of first node
	 * @param n2: the value of second node
	 * @return: a reference to the lowest common ancestor
	 */
	private static <MyType> Node<MyType> lcaBinaryTree(Node<MyType> cur, Node<MyType> n1, Node<MyType> n2) {
		if (cur == null)
			return null;
		// if any of the values is at the current node, then the current node is
		// the LCA
		if (cur.data.equals(n1.data) || cur.data.equals(n2.data))
			return cur;
		// find the lca in the left and the right subtrees
		Node<MyType> left = lcaBinaryTree(cur.left, n1, n2);
		Node<MyType> right = lcaBinaryTree(cur.right, n1, n2);

		// if both left and right aren't null, then one node is in left subtree,
		// and one node is in right subtree, so the LCA is cur
		if (left != null && right != null)
			return cur;

		// if right is null, then the LCA is the one in the left subtree
		if (right == null)
			return left;

		// otherwise it's the one in the right subtree
		return right;
	}

	/**
	 * Get the node with value val starting at the current node cur
	 * 
	 * @param cur: current node
	 * @param val: value to search
	 * @return the node that contains val, or null
	 */
	private static <MyType> Node<MyType> findNodeBinaryTree(Node<MyType> cur, MyType val) {
		if (cur == null)
			return null;

		// go through the nodes in pre-order and check if found
		Stack<Node<MyType>> stack = new Stack<>();
		stack.push(cur);
		while (!stack.isEmpty()) {
			cur = stack.pop();
			if (cur.data.equals(val))
				return cur;
			if (cur.left != null)
				stack.push(cur.left);
			if (cur.right != null)
				stack.push(cur.right);
		}
		return null;
	}
}
