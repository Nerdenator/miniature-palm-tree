package bstProblems_src;

import binaryTree_util.Node;
import bst_src.BinarySearchTree;

/**
 * Calculate the distance between two nodes. Given n1 and n2, the distance
 * between them is:
 * height(n1) + height(n2) - 2*height(lowestCommonAncestor(n1,n2))
 * 
 * @author adina
 *
 */
public class GetNodeDistance {

	/**
	 * Get the height of the value in the tree
	 * 
	 * @param val: the value
	 * @param cur: current node
	 * @param height: the height up to here
	 * @return: the height of the value node
	 */
	private static int getHeight(int val, Node<Integer> cur, int height) {
		if (cur == null)
			return -1;
		if (cur.data == val)
			return height;
		return Math.max(getHeight(val, cur.left, height + 1), getHeight(val, cur.right, height + 1));
	}

	/**
	 * Calculate the distance between two nodes. Given n1 and n2, the distance
	 * between them is:
	 * height(n1) + height(n2) - 2*height(lowestCommonAncestor(n1,n2))
	 * 
	 * @param bst: the binary search tree
	 * @param n1: first node value
	 * @param n2: second node value
	 * @return the distance between n1 and n2
	 */
	public static int getNodeDistance(BinarySearchTree bst, int n1, int n2) {
		Node<Integer> lca = getLCA(n1, n2, bst.root);
		int h1 = getHeight(n1, bst.root, 0);
		int h2 = getHeight(n2, bst.root, 0);
		if (lca == null || h1 == -1 || h2 == -1)
			return -1;
		return h1 + h2 - 2 * getHeight(lca.data, bst.root, 0);
	}

	/**
	 * Find the lowest common ancestor of nodes containing n1 and n2
	 * In a given binary tree, the low­est com­mon ances­tor of two nodes n1 and
	 * n2 will be a node X such that node X will be the low­est node who has n1
	 * and n2 as its descendants.
	 * 
	 * @param n1: first value
	 * @param n2: second value
	 * @param cur: current node
	 * @return the lowest common ancestor
	 */
	private static Node<Integer> getLCA(int n1, int n2, Node<Integer> cur) {
		// didn't find them
		if (cur == null)
			return null;

		// if cur is one of the nodes we're looking for, it's also the LCA
		if (cur.data == n1 || cur.data == n2)
			return cur;

		// call recursively
		Node<Integer> left = getLCA(n1, n2, cur.left);
		Node<Integer> right = getLCA(n1, n2, cur.right);

		// one node is in left subtree and one node is in right subtree
		if (left != null && right != null)
			return cur;
		// both nodes in left subtree
		if (left != null)
			return left;
		// both nodes in right subtree
		return right;
	}

}
