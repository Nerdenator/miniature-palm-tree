package bstProblems_src;

import binaryTree_util.InterfaceBinaryTree;
import binaryTree_util.Node;

/**
 * Given a node, find the longest path for the tree centered at root
 * This is the maximum of:
 * - the path going through the node
 * - the longest path going through its left child
 * - the longest path going through its right child
 * 
 * @author adina
 */
public class GetTreeDiameter {
	public static int getTreeDiameterOpt(InterfaceBinaryTree<?> tree) {
		return getTreeDiameterOpt(tree.root(), new Height());
	}

	private static int getTreeDiameterOpt(Node<?> node, Height h) {
		if (node == null) {
			h.val = -1;
			return -1;
		}

		// keep track of height in left and right subtrees
		Height lh = new Height();
		Height rh = new Height();

		// the length in the left subtree
		lh.val++;
		int lenLeft = getTreeDiameterOpt(node.left, lh);

		// the length in the right subtree
		rh.val++;
		int lenRight = getTreeDiameterOpt(node.right, rh);

		// this is the height of node
		h.val = 1 + Math.max(lh.val, rh.val);

		// this is the length through current node
		int lenCurrent = 1 + lh.val + rh.val;

		return 1 + Math.max(lenCurrent, Math.max(lenLeft, lenRight));
	}

	public static int getTreeDiameterInefficient(InterfaceBinaryTree<?> tree) {
		return getTreeDiameterInefficient(tree.root());
	}

	/**
	 * Given a node, find the longest path for the tree centered at root
	 * 
	 * @param node the current root of the subtree
	 * @return the longest path of the subtree
	 */
	private static int getTreeDiameterInefficient(Node<?> node) {
		if (node == null)
			return -1;

		// the length in the left subtree
		int lenLeft = getTreeDiameterInefficient(node.left);

		// the length in the right subtree
		int lenRight = getTreeDiameterInefficient(node.right);

		// the length including current node
		int lenCurrent = getTreeHeight(node.left) + getTreeHeight(node.right) + 1;

		// the total length is the maximum above
		return 1 + Math.max(Math.max(lenLeft, lenRight), lenCurrent);
	}

	/**
	 * Find the height of the current node in the tree
	 * 
	 * @param node: the current node
	 * @param height: the height up to current node
	 * @return: the height including current node
	 */
	private static int getTreeHeight(Node<?> node) {
		// if the node is null, we're done
		if (node == null)
			return -1;

		// the height is the maximum between the height of the left subtree and
		// the height of the left subtree
		return 1 + Math.max(getTreeHeight(node.right), getTreeHeight(node.left));
	}
}

/**
 * Keep track of the height
 * 
 * @author adina
 */
class Height {
	int val;
}
