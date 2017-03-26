package bstProblems_src;

import java.util.Stack;

import binaryTree_util.InterfaceBinaryTree;
import binaryTree_util.Node;

/**
 * Given two binary trees, check if they are equal
 * 
 * @author adina
 *
 */
public class AreTreesEqual {

	/**
	 * Recursively check if the two trees are equal
	 * 
	 * @param t1: first binary tree
	 * @param t2: second binary tree
	 * @return true if trees are equal, false otherwise
	 */
	public static boolean treesEqualRecursive(InterfaceBinaryTree<Integer> t1, InterfaceBinaryTree<Integer> t2) {
		// same object?
		if (t1.root() == t2.root())
			return true;
		// same values
		return treesEqualRecursive(t1.root(), t2.root());
	}

	/**
	 * Recursively check if the subtree starting at node1 is equal
	 * to the tree starting at node2
	 * 
	 * @param node1: root of first subtree
	 * @param node2: root of second subtree
	 * @return true if subtrees are equal, false otherwise
	 */
	private static boolean treesEqualRecursive(Node<Integer> node1, Node<Integer> node2) {
		// both are empty sub-trees
		if (node1 == null && node2 == null)
			return true;

		// if only one is null
		if (node1 == null || node2 == null)
			return false;

		// different information in node at same position
		if (node1.data != node2.data)
			return false;

		// otherwise, look further
		return treesEqualRecursive(node1.left, node2.left) && //
				treesEqualRecursive(node1.right, node2.right);
	}

	/**
	 * Iteratively check if the two trees are equal
	 * 
	 * @param t1: first binary tree
	 * @param t2: second binary tree
	 * @return true if trees are equal, false otherwise
	 */
	public static boolean treesEqualIterative(InterfaceBinaryTree<Integer> t1, InterfaceBinaryTree<Integer> t2) {
		// same object?
		if (t1.root() == t2.root())
			return true;
		// if only one is null
		if (t1.root() == null || t2.root() == null)
			return false;

		// traverse both trees from the root, if they differ anywhere return
		// false
		Node<Integer> cur1 = t1.root();
		Node<Integer> cur2 = t2.root();

		// add the nodes to two stacks
		Stack<Node<Integer>> stack1 = new Stack<>();
		stack1.add(cur1);
		Stack<Node<Integer>> stack2 = new Stack<>();
		stack2.add(cur2);

		// pre-order
		while (!stack1.isEmpty() && !stack2.isEmpty()) {
			cur1 = stack1.pop();
			cur2 = stack2.pop();
			// if the nodes data is different, then the trees are not equal
			if (cur1.data != cur2.data)
				return false;

			// if they have different number of children, then they're not equal
			if ((cur1.left != null && cur2.left == null) || (cur1.left == null && cur2.left != null)
					|| (cur1.right != null && cur2.right == null) || (cur1.right == null && cur2.right != null))
				return false;

			// add the children to the stacks
			if (cur1.left != null)
				stack1.push(cur1.left);
			if (cur2.left != null)
				stack2.push(cur2.left);
			if (cur1.right != null)
				stack1.push(cur1.right);
			if (cur2.right != null)
				stack2.push(cur2.right);
		}
		return true;
	}
}
