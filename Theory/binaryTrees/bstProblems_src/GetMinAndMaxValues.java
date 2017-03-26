package bstProblems_src;

import binaryTree_src.BinarySearchTree;
import binaryTree_util.Node;

/**
 * Get the minimum and maximum values in a BST
 * 
 * @author adina
 */
public class GetMinAndMaxValues {

	/**
	 * Get the max value in the tree (rightmost leaf)
	 * O(log n) average, O(n) worst
	 * 
	 * @param bst: the binary search tree
	 * @return max value, or Integer.MIN_VALUE if tree is empty
	 */
	public static int getMaxValue(BinarySearchTree bst) {
		if (bst.root == null)
			return Integer.MIN_VALUE;
		// start at root
		Node<Integer> cur = bst.root;
		// go right
		while (cur.right != null)
			cur = cur.right;
		// return the value
		return cur.data;
	}

	/**
	 * Get the min value in the tree (leftmost leaf)
	 * O(log n) average, O(n) worst
	 * 
	 * @param bst: the binary search tree
	 * @return min value, or Integer.MAX_VALUE if tree is empty
	 */
	public static int getMinValue(BinarySearchTree bst) {
		if (bst.root == null)
			return Integer.MAX_VALUE;
		// start at root
		Node<Integer> cur = bst.root;
		// go left
		while (cur.left != null)
			cur = cur.left;
		// return the value
		return cur.data;
	}

}
