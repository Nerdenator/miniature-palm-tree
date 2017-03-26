package bstProblems_src;

import binaryTree_util.Node;
import bst_src.BinarySearchTree;

/**
 * Get the depth of a key (the level it,s on; root is level 0)
 * 
 * @author adina
 */
public class GetKeyDepth {
	/**
	 * Get the depth of a key recursively
	 * 
	 * @param bst: the binary search tree
	 * @param key: the value we're looking for
	 * @return the depth of key in bst
	 */
	public static int getKeyDepthRecursive(BinarySearchTree bst, int key) {
		return getKeyDepthRecursive(key, bst.root, 0);
	}

	/**
	 * Get the depth of a key recursively
	 * 
	 * @param key: the value we're looking for
	 * @param cur: the current node
	 * @param depth: the depth until the current node
	 * @return depth of key
	 */
	private static int getKeyDepthRecursive(int key, Node<Integer> cur, int depth) {
		// if the value is not in the tree
		if (cur == null)
			return -1;

		// if we found the value at the current node
		if (cur.data == key)
			return depth;

		return Math.max(getKeyDepthRecursive(key, cur.left, depth + 1), //
				getKeyDepthRecursive(key, cur.right, depth + 1));
	}
}
