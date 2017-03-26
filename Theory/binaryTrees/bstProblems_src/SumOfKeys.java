package bstProblems_src;

import java.util.Stack;

import binaryTree_src.BinarySearchTree;
import binaryTree_util.Node;

/**
 * Calculate the sum of all nodes in tree
 * 
 * @author adina
 */
public class SumOfKeys {

	/**
	 * Recursively calculate the sum of the nodes in the tree
	 * 
	 * @param bst: the binary search tree
	 * @return the sum of all nodes in the tree
	 */
	public static int sumOfKeysRecursive(BinarySearchTree bst) {
		if (bst.root == null)
			return -1;

		return sumOfKeysRecursive(bst.root, 0);
	}

	/**
	 * Recursively calculate the sum of the nodes in the tree
	 * 
	 * @param cur: current node
	 * @param sum: sum until current node
	 * @return the sum of all nodes in the tree
	 */
	private static int sumOfKeysRecursive(Node<Integer> cur, int sum) {
		// if the current node is null, reached the end of branch
		if (cur == null)
			return sum;

		// data + sum of left subtree + sum of right subtree
		return cur.data + sumOfKeysRecursive(cur.left, sum) //
				+ sumOfKeysRecursive(cur.right, sum);
	}

	/**
	 * Use in-order traversal to sum the nodes in the tree
	 * 
	 * @param bst: the binary search tree
	 * @return the sum of all nodes in the tree
	 */
	public static int sumOfKeysIterative(BinarySearchTree bst) {
		if (bst.root == null)
			return -1;

		int sum = 0;

		Node<Integer> cur = bst.root;
		Stack<Node<Integer>> stack = new Stack<Node<Integer>>();
		stack.push(cur);

		while (!stack.isEmpty()) {
			// add left children until there are no more children
			if (cur.left != null) {
				cur = cur.left;
				stack.push(cur);
			}
			// keep popping the top adding to the traversal list,
			// until we find a node that has a right child, and push this child
			// to the stack
			else {
				cur = stack.pop();
				sum += cur.data;
				// keep popping until there is a right child to add
				while (cur.right == null && !stack.isEmpty()) {
					cur = stack.pop();
					sum += cur.data;
				}
				// add the right child
				if (cur.right != null) {
					cur = cur.right;
					stack.push(cur);
				}
			}
		}
		return sum;
	}
}
