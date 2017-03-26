package bstProblems_src;

import java.util.ArrayList;

import binaryTree_util.InterfaceBinaryTree;
import binaryTree_util.Node;

/**
 * Check if a given tree is a BST
 * 
 * @author adina
 */
public class IsTreeBST {
	/**
	 * Is the given binary tree a binary search tree
	 * 
	 * @param tree: the tree we're checking
	 * @return true if it's BST, false otherwise
	 */
	public static boolean isTreeBST(InterfaceBinaryTree<Integer> tree) {
		// MAX_VALUE - 1 to prevent overflow
		return isTreeBST(tree.root(), Integer.MAX_VALUE - 1, Integer.MIN_VALUE);
	}

	/**
	 * Is the binary tree starting at cur a binary search tree
	 * - maximum in left subtree is <= cur
	 * - minimum in right subtree is > cur
	 * Runs in O(n)
	 * 
	 * @param cur: the current node
	 * @param max: maximum in left subtree
	 * @param min: minimum in right subtree
	 * @return true if it's BST, false otherwise
	 */
	private static boolean isTreeBST(Node<Integer> cur, int max, int min) {
		// it was BST until here
		if (cur == null)
			return true;

		// max+1 to allow duplicates on left side
		if (cur.data < min || cur.data > max + 1)
			return false;

		// check the subtrees recursively tightening the min/max constraints
		return isTreeBST(cur.left, cur.data - 1, min) && //
				isTreeBST(cur.right, max, cur.data + 1);
	}

	/**
	 * Do an in-order traversal, adding to an array
	 * and then check that the array is sorted
	 * Time O(n), space O(n)
	 * 
	 * @param tree: the tree we're checking
	 * @return true if tree is a BST
	 */
	public static boolean isTreeBSTInOrderSortedArray(InterfaceBinaryTree<Integer> tree) {
		ArrayList<Integer> inOrderArray = new ArrayList<>();
		getInOrderArray(tree.root(), inOrderArray);

		// check that array is sorted
		for (int i = 0; i < inOrderArray.size() - 1; i++)
			if (inOrderArray.get(i) > inOrderArray.get(i + 1))
				return false;

		return true;
	}

	/**
	 * Do in-order traversal, adding traversed nodes to A
	 * 
	 * @param cur: the current node
	 * @param arr: the ArrayList we're building
	 */
	private static void getInOrderArray(Node<Integer> cur, ArrayList<Integer> arr) {
		// it was BST up to here
		if (cur == null)
			return;

		// do an in-order recursive traversal, first left sub-tree:
		getInOrderArray(cur.left, arr);

		// append current node to A
		arr.add(cur.data);

		// then right subtree
		getInOrderArray(cur.right, arr);
	}
}
