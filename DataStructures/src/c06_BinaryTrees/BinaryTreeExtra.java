package c06_BinaryTrees;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinaryTreeExtra extends BinarySearchTree {
	public BinaryTreeExtra(int[] arr) {
		insert(arr);
	}

	public BinaryTreeExtra(int data) {
		insert(data);
	}

	public BinaryTreeExtra() {
	}

	/**
	 * Get the height of the tree recursively
	 * 
	 * @return the tree height
	 */
	public int getTreeHeight_Recursive() {
		//root is at height 0, so if no nodes height is -1
		if (root == null)
			return -1;

		return getTreeHeight_Recursive(root, 0);
	}

	/**
	 * Get the height of the tree recursively
	 * 
	 * @param cur
	 *            the current node
	 * @param height
	 *            the current height at node cur
	 * @return the tree height
	 */
	private int getTreeHeight_Recursive(Node<Integer> cur, int height) {
		// no child nodes, this is the final height
		if (cur.left == null && cur.right == null)
			return height;
		int leftHeight = 0;
		int rightHeight = 0;
		// height in right subtree
		if (cur.right != null)
			rightHeight = getTreeHeight_Recursive(cur.right, height + 1);
		// height in left subtree
		if (cur.left != null)
			leftHeight = getTreeHeight_Recursive(cur.left, height + 1);
		// the height is the maximum between the height of the left subtree and the height of the left subtree
		return Math.max(leftHeight, rightHeight);
	}

	/**
	 * Get the height of the tree iteratively
	 * 
	 * @return the tree height
	 */
	public int getTreeHeight_Iterative() {
		// if the tree is empty
		if (root == null)
			return -1;

		// current level, start at -1
		int level = -1;

		// do a level-order traversal
		Node<Integer> cur = root;
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
			//done with this level
			if (numNodesCurLev == 0) {
				numNodesCurLev = numNodesNextLev;
				numNodesNextLev = 0;
				level++;
			}
		}
		return level - 1;
	}

	/**
	 * Get the depth of a key recursively
	 * 
	 * @param key
	 * @return depth of key
	 */
	public int getKeyDepth_Recursive(int key) {
		return getKeyDepth_Recursive(key, root, 0);
	}

	/**
	 * Get the depth of a key recursively
	 * 
	 * @param key
	 *            the value we're looking for
	 * @param cur
	 *            the current node
	 * @param the
	 *            height until the current node
	 * @return depth of key
	 */
	private int getKeyDepth_Recursive(int val, Node<Integer> cur, int height) {
		// if the value is not in the tree
		if (cur == null)
			return -1;
		// if we found the value at the current node
		if (cur.data == val)
			return height;
		//look in the left subtree
		int level = getKeyDepth_Recursive(val, cur.left, height + 1);
		//if it's not there, look in the right subtree
		if (level == -1)
			level = getKeyDepth_Recursive(val, cur.right, height + 1);
		return level;
	}

	/**
	 * Recursively calculate the sum of the nodes in the tree
	 * 
	 * @return the sum of all nodes in the tree
	 */
	public int sumOfKeys_Recursive() {
		if (root == null)
			return -1;
		return sumOfKeys_Recursive(root, 0);
	}

	/**
	 * Recursively calculate the sum of the nodes in the tree
	 * 
	 * @param cur
	 *            current node
	 * @param sum
	 *            sum until current node
	 * @return the sum of all nodes in the tree
	 */
	private int sumOfKeys_Recursive(Node<Integer> cur, int sum) {
		// if the current node is null, reached the end of branch
		if (cur == null)
			return sum;
		// data + sum of left subtree + sum of right subtree
		return cur.data + sumOfKeys_Recursive(cur.left, sum) + sumOfKeys_Recursive(cur.right, sum);
	}

	/**
	 * Use in-order traversal to sum the nodes in the tree
	 * 
	 * @return the sum of all nodes in the tree
	 */
	public int sumOfKeysInOrder_Iterative() {
		if (root == null)
			return -1;

		int sum = 0;
		Node<Integer> cur = root;
		Stack<Node<Integer>> stack = new Stack<Node<Integer>>();
		stack.push(cur);

		while (!stack.isEmpty()) {
			// add left children until there are no more children
			if (cur.left != null) {
				cur = cur.left;
				stack.push(cur);
			}
			// keep popping the top adding to the traversal list, 
			// until we find a node that has a right child, and push this child to the stack 
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

	/**
	 * Recursively check if the current tree is equal to tree
	 * 
	 * @param tree
	 * @return true if trees are equal, false otherwise
	 */
	public boolean isEqualTo_Recursive(BinaryTreeExtra tree) {
		return isEqualTo_Recursive(root, tree.root);
	}

	/**
	 * Recursively check if the tree starting at node1 is equal to the tree
	 * starting at node2
	 * 
	 * @param node1
	 * @param node2
	 * @return true if trees are equal, false otherwise
	 */
	private boolean isEqualTo_Recursive(Node<Integer> node1, Node<Integer> node2) {
		// both are empty trees
		if (node1 == null && node2 == null)
			return true;
		// if only one is null
		if (node1 == null || node2 == null)
			return false;
		// different information in node at same position
		if (node1.data != node2.data)
			return false;
		// otherwise, look further
		return isEqualTo_Recursive(node1.left, node2.left) && isEqualTo_Recursive(node1.right, node2.right);
	}

	/**
	 * Get the max value in the tree (rightmost leaf)
	 * O(log n) average, O(n) worst
	 * 
	 * @return max value, or Integer.MIN_VALUE if tree is empty
	 */
	public int getMaxValue() {
		if (root == null)
			return Integer.MIN_VALUE;
		Node<Integer> cur = root;
		while (cur.right != null)
			cur = cur.right;
		return cur.data;
	}

	/**
	 * Get the min value in the tree (leftmost leaf)
	 * O(log n) average, O(n) worst
	 * 
	 * @return min value, or Integer.MAX_VALUE if tree is empty
	 */
	public int getMinValue() {
		if (root == null)
			return Integer.MAX_VALUE;
		Node<Integer> cur = root;
		while (cur.left != null)
			cur = cur.left;
		return cur.data;
	}

	/**
	 * Recursively count the number of duplicates in a binary tree
	 * by looking at the values of current vs previous nodes
	 * 
	 * @return number of duplicates, discounting the first appearance
	 */
	public int countDuplicates_Recursive() {
		return countDuplicatesRecursive(root, null, 0);
	}

	/**
	 * Recursively count the number of duplicates in a binary tree
	 * by looking at the values of current vs previous nodes
	 * 
	 * @param cur
	 *            current node
	 * @param prev
	 *            previous node
	 * @return number of duplicates, discounting the first appearance
	 */
	private int countDuplicatesRecursive(Node<Integer> cur, Node<Integer> prev, int count) {
		// reached the end, return count
		if (cur == null)
			return count;
		// if the current node is a duplicate of the previous, increase count
		if (prev != null && cur.data == prev.data)
			count++;
		// save the number of duplicates in the left tree to prevent duplicate counts
		count = countDuplicatesRecursive(cur.left, cur, count);
		// get the number of duplicates in the right tree
		return countDuplicatesRecursive(cur.right, cur, count);
	}

	/**
	 * Traverse the tree in order, and keep track of previous node seen
	 * If current node is the same as the previous one, increase number of
	 * duplicates
	 * O(n) time, O(n) space for stack
	 * 
	 * @return number of duplicates, discounting the first appearance
	 */
	public int countDuplicatesInOrder_Iterative() {
		if (root == null)
			return 0;

		int count = 0;

		//traverse in-order iteratively and see how many identical values in a row
		LinkedList<Node<Integer>> stack = new LinkedList<>();
		Node<Integer> cur = root;
		stack.add(cur);

		int lastValue = -1;

		while (!stack.isEmpty()) {
			// add all left children
			if (cur.left != null) {
				cur = cur.left;
				stack.push(cur);
			}
			// pop 
			else {
				cur = stack.pop();
				if (cur.data == lastValue)
					count++;
				// keep track of new last value
				lastValue = cur.data;

				// keep popping until there is a right child to add
				while (cur.right == null && !stack.isEmpty()) {
					cur = stack.pop();
					if (cur.data == lastValue)
						count++;
					// keep track of new last value
					lastValue = cur.data;
				}
				// add the right child
				if (cur.right != null) {
					cur = cur.right;
					stack.push(cur);
				}
			}
		}
		return count;
	}

	/**
	 * Traverse the tree in any order (preorder in this case), and use a
	 * hashtable (HashSet)
	 * to keep track of previous node seen. If it's already in the table,
	 * increase the number of duplicates
	 * O(n) time, O(2n) space for stack + hashmap
	 * 
	 * @return number of duplicates, discounting the first appearance
	 */
	public int countDuplicatesHashtablePreOrderRec() {
		return countDuplicatesHashtablePreOrderRec(root, new HashSet<Integer>(), 0);
	}

	/**
	 * Traverse the tree in any order (preorder in this case), and use a
	 * hashtable (HashSet)
	 * to keep track of previous node seen. If it's already in the table,
	 * increase the number of duplicates
	 * O(n) time, O(2n) space for stack + hashmap
	 * 
	 * @param cur
	 *            the current node
	 * @param map
	 *            the hashset that keeps track of the values
	 * @return number of duplicates, discounting the first appearance
	 */
	public int countDuplicatesHashtablePreOrderRec(Node<Integer> cur, HashSet<Integer> map, int count) {
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
		count = countDuplicatesHashtablePreOrderRec(cur.left, map, count);
		// count the duplicates in the right sub-tree
		return countDuplicatesHashtablePreOrderRec(cur.right, map, count);
	}

	/**
	 * Do an in-order traversal, adding to an array
	 * and then check that the array is sorted
	 * Time O(n), space O(n)
	 * 
	 * @param tree
	 * @return true if tree is a BST
	 */
	public static boolean isTreeBST_InOrderSorted(BinaryTree<Integer> tree) {
		ArrayList<Integer> A = new ArrayList<>();
		getInOrderArray(tree.root, A);
		for (int i = 0; i < A.size() - 1; i++)
			if (A.get(i) > A.get(i + 1))
				return false;
		return true;
	}

	/**
	 * Do in-order traversal, adding traversed nodes to A
	 * 
	 * @param cur
	 * @param A
	 */
	private static void getInOrderArray(Node<Integer> cur, ArrayList<Integer> A) {
		// it was BST up to here
		if (cur == null)
			return;

		// do an in-order recursive traversal, first left sub-tree:
		getInOrderArray(cur.left, A);
		// append current node to A
		A.add(cur.data);
		// then right subtree
		getInOrderArray(cur.right, A);
	}

	/**
	 * Is the given binary tree a binary search tree
	 * 
	 * @param tree
	 * @return true if it's BST, false otherwise
	 */
	public static boolean isTreeBST(BinaryTree<Integer> tree) {
		//MAX_VALUE - 1 to prevent overflow
		return isTreeBST(tree.root, Integer.MIN_VALUE, Integer.MAX_VALUE - 1);
	}

	/**
	 * Is the binary tree starting at cur a binary search tree
	 * - maximum in left subtree is <= cur
	 * - minimum in right subtree is > cur
	 * Runs in O(n)
	 * 
	 * @param cur
	 *            the current node
	 * @param max
	 *            maximum in left subtree
	 * @param min
	 *            minimum in right subtree
	 * 
	 * @return true if it's BST, false otherwise
	 */
	private static boolean isTreeBST(Node<Integer> cur, int min, int max) {
		// if was BST until here
		if (cur == null)
			return true;

		// max+1 to allow duplicates on left side
		if (cur.data < min || cur.data > max + 1)
			return false;

		//check the subtrees recursively tightening the min/max constraints
		return isTreeBST(cur.left, min, cur.data - 1) && isTreeBST(cur.right, cur.data + 1, max);
	}
}
