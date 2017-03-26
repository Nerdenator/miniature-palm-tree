package bstProblems_src;

import java.util.LinkedList;
import java.util.Queue;

import binaryTree_src.BinarySearchTree;
import binaryTree_util.Node;

public class GetTreeHeight {
	/**
	 * Get the height of the tree recursively
	 * 
	 * @param bst: the binary search tree
	 * @return the tree height
	 */
	public static int getTreeHeightRecursive(BinarySearchTree bst) {
		return getTreeHeightRecursive(bst.root);
	}

	/**
	 * Get the height of the tree recursively
	 * 
	 * @param cur: the current node
	 * @param height: the current height at node cur
	 * @return the tree height
	 */
	private static int getTreeHeightRecursive(Node<Integer> cur) {
		// if the node is null, we're done
		if (cur == null)
			return -1;

		// the height is the maximum between the height of the left subtree and
		// the height of the left subtree
		return 1 + Math.max(getTreeHeightRecursive(cur.right), getTreeHeightRecursive(cur.left));
	}

	/**
	 * Get the height of the tree iteratively, by doing a level-order traversal
	 * and counting each level
	 * 
	 * @param bst: the binary search tree
	 * @return the tree height
	 */
	public static int getTreeHeightIterative(BinarySearchTree bst) {
		// if the tree is empty
		if (bst.root == null)
			return -1;

		// current level, start at -1
		int level = -1;

		// do a level-order traversal
		Node<Integer> cur = bst.root;
		Queue<Node<Integer>> queue = new LinkedList<>();
		queue.add(cur);

		// keep track of number of nodes on current and next levels
		int numNodesCurLev = 1;
		int numNodesNextLev = 0;

		while (!queue.isEmpty()) {
			cur = queue.remove();
			numNodesCurLev--;

			if (cur != null) {
				queue.add(cur.left);
				queue.add(cur.right);
				numNodesNextLev += 2;
			}

			// done with this level
			if (numNodesCurLev == 0 && numNodesNextLev > 0) {
				numNodesCurLev = numNodesNextLev;
				numNodesNextLev = 0;
				level++;
			}
		}
		return level;
	}
}
