package bstProblems_src;

import java.util.HashSet;
import java.util.Stack;

import binaryTree_util.Node;
import bst_src.BinarySearchTree;

/**
 * Count the duplicate values in a binary tree
 * 
 * @author adina
 */
public class CountDuplicates {

	private static Node<Integer> prev;

	/**
	 * Recursively count the number of duplicates in a binary tree
	 * by looking at the values of current vs. previous nodes
	 * 
	 * Note: static variable prev, otherwise it's always the parent
	 * 
	 * @param bst: the BST
	 * @return number of duplicates, discounting the first appearance
	 */
	public static int countDuplicatesRecursive(BinarySearchTree bst) {
		prev = null;
		return countDuplicatesRecursive(bst.root);
	}

	/**
	 * Recursively count the number of duplicates in a binary tree
	 * by looking at the values of current vs. previous nodes. Need to use
	 * in-order traversal, otherwise the nodes won't be close to each other
	 * O(n) time, O(n) space for call-stack
	 * 
	 * Note: static variable prev, otherwise it's always the parent
	 * 
	 * @param cur: current node
	 * @return number of duplicates, discounting the first appearance
	 */

	private static int countDuplicatesRecursive(Node<Integer> cur) {
		// reached the end, return count
		if (cur == null)
			return 0;

		// save the number of duplicates in the left tree
		int countL = countDuplicatesRecursive(cur.left);

		// if the current node is a duplicate of the previous, increase count
		if (prev != null && cur.data == prev.data)
			countL++;

		prev = cur;
		int countR = countDuplicatesRecursive(cur.right);
		// get the number of duplicates in the right tree
		return countL + countR;
	}

	/**
	 * Traverse the tree in order, and keep track of previous node seen
	 * If current node is the same as the previous one, increase number of
	 * duplicates
	 * O(n) time, O(n) space for stack
	 * 
	 * @param bst: the BST
	 * @return number of duplicates, discounting the first appearance
	 */
	public static int countDuplicatesIterative(BinarySearchTree bst) {
		if (bst.root == null)
			return 0;

		int count = 0;

		// traverse in-order iteratively and
		// see how many identical values in a row
		Stack<Node<Integer>> stack = new Stack<>();
		Node<Integer> cur = bst.root;
		stack.add(cur);

		Node<Integer> prev = null;

		while (!stack.isEmpty()) {
			// add all left children
			if (cur.left != null) {
				cur = cur.left;
				stack.push(cur);
			}
			// pop, compare and track previous value
			else {
				cur = stack.pop();
				if (prev != null && cur.data == prev.data)
					count++;
				// keep track of new last value
				prev = cur;

				// keep popping until there is a right child to add
				while (cur.right == null && !stack.isEmpty()) {
					cur = stack.pop();
					if (cur.data == prev.data)
						count++;
					// keep track of new last value
					prev = cur;
				}
				// add the right child to the stack
				if (cur.right != null) {
					cur = cur.right;
					stack.push(cur);
				}
			}
		}
		return count;
	}

	/**
	 * Traverse the tree in any order (pre-order in this case), and use a
	 * hashtable (HashSet) to keep track of previous nodes seen. If it's already
	 * in the table, increase the number of duplicates
	 * O(n) time, O(2n) space for stack + hashmap
	 * 
	 * @param bst: the BST
	 * @return number of duplicates, discounting the first appearance
	 */
	public static int countDuplicatesHashtable(BinarySearchTree bst) {
		return countDuplicatesHashtable(bst.root, new HashSet<Integer>(), 0);
	}

	/**
	 * Traverse the tree in any order (pre-order in this case), and use a
	 * hashtable (HashSet) to keep track of previous node seen. If it's already
	 * in the table, increase the number of duplicates
	 * O(n) time, O(2n) space for stack + hashmap
	 * 
	 * @param cur: the current node
	 * @param map: the hashset that keeps track of the values
	 * @param count: the number of nodes that repeat
	 * @return number of duplicates, discounting the first appearance
	 */
	public static int countDuplicatesHashtable(Node<Integer> cur, HashSet<Integer> map, int count) {
		// ended the traversal
		if (cur == null)
			return count;

		// increase count if the data is already in the hashset
		if (map.contains(cur.data))
			count++;

		// otherwise, add it to the hashset
		else
			map.add(cur.data);

		// count the duplicates in the left sub-tree
		count = countDuplicatesHashtable(cur.left, map, count);
		// count the duplicates in the right sub-tree
		return countDuplicatesHashtable(cur.right, map, count);
	}
}
