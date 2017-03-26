package bstProblems_src;

import binaryTree_src.BinarySearchTree;
import binaryTree_util.Node;

/**
 * Get the Lowest Common Ancestor given two nodes in a binary search tree. If
 * given values, ensure they exist in the tree
 * 
 * @author adina
 */
public class LowestCommonAncestorBST {

	/**
	 * Get the LCA in any binary search tree recursively
	 * Find the nodes first to ensure they exist
	 * 
	 * @param bst: the binary search tree
	 * @param n1: the value of the first node
	 * @param n2: the value of the second node
	 * @return a reference to the lowest common ancestor
	 */
	public static Node<Integer> lcaBSTRecursive(BinarySearchTree bst, int v1, int v2) {
		// get the first node
		Node<Integer> n1 = findNodeBST(bst.root, v1);
		// get the second node
		Node<Integer> n2 = findNodeBST(bst.root, v2);

		if (n1 == null || n2 == null)
			return null;
		return lcaBSTRecursive(bst.root, n1, n2);
	}

	/**
	 * Get the LCA in any binary search tree recursively
	 * 
	 * @param bst: the binary search tree
	 * @param n1: the first node
	 * @param n2: the second node
	 * @return: a reference to the lowest common ancestor
	 */
	private static Node<Integer> lcaBSTRecursive(Node<Integer> cur, Node<Integer> n1, Node<Integer> n2) {
		if (cur == null)
			return null;
		// if the values are <= than the current node, LCA is in left subtree
		if (n1.data < cur.data && n2.data < cur.data)
			return lcaBSTRecursive(cur.left, n1, n2);
		// if the values are > than the current node, LCA is in right subtree
		if (n1.data > cur.data && n2.data > cur.data)
			return lcaBSTRecursive(cur.right, n1, n2);
		// otherwise the LCA is at the current node
		return cur;
	}

	/**
	 * Get the LCA in any binary search tree iteratively
	 * 
	 * @param bst: the binary search tree
	 * @param v1: the value of the first node
	 * @param v2: the value of the second node
	 * @return: a reference to the lowest common ancestor
	 */
	public static Node<Integer> lcaBSTIterative(BinarySearchTree bst, int v1, int v2) {
		// get the first node
		Node<Integer> n1 = findNodeBST(bst.root, v1);
		// get the second node
		Node<Integer> n2 = findNodeBST(bst.root, v2);

		if (n1 == null || n2 == null)
			return null;
		return lcaBSTIterative(bst, n1, n2);
	}

	/**
	 * Get the LCA in any binary search tree iteratively
	 * 
	 * @param bst: the binary search tree
	 * @param n1: the first node
	 * @param n2: the second node
	 * @return: a reference to the lowest common ancestor
	 */
	private static Node<Integer> lcaBSTIterative(BinarySearchTree bst, Node<Integer> n1, Node<Integer> n2) {
		Node<Integer> cur = bst.root;
		while (cur != null) {
			// the LCA is in the left subtree
			if (n1.data < cur.data && n2.data < cur.data)
				cur = cur.left;
			// the LCA is in the right subtree
			else if (n1.data > cur.data && n2.data > cur.data)
				cur = cur.right;
			else
				break;
		}
		return cur;
	}

	/**
	 * Get the node with value val starting at the current node cur
	 * 
	 * @param cur: current node
	 * @param val: value to search
	 * @return the node that contains val, or null
	 */
	private static Node<Integer> findNodeBST(Node<Integer> cur, int val) {
		if (cur == null)
			return null;
		if (cur.data == val)
			return cur;
		// search in left subtree
		if (val <= cur.data)
			return findNodeBST(cur.left, val);
		// search in right subtree
		return findNodeBST(cur.right, val);
	}

}
