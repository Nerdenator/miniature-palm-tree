package binaryTrees_src;

import java.util.ArrayList;
import java.util.Stack;

public class BinaryTree<MyType> extends AbstractBinaryTree<MyType> {

	/* INSERTION */

	/**
	 * This insert uses a random number to decide which
	 * direction to go from a given node
	 * Could generate a very unbalanced tree
	 */
	@Override
	public void insert(MyType data) {
		Node<MyType> node = new Node<MyType>(data);
		if (root == null)
			root = node;
		else
			insert(node, root);
	}

	/**
	 * This insert uses a random number to decide which
	 * direction to go from a given node
	 * Could generate a very unbalanced tree
	 * 
	 * @param data:
	 *            Node to insert
	 * @param node:
	 *            Node to insert after
	 */
	private void insert(Node<MyType> data, Node<MyType> node) {
		int score = (int) (Math.random() * 100);
		// even goes left
		if (score % 2 == 0) {
			if (node.left == null) {
				node.left = data;
				return;
			}
			insert(data, node.left);
		}
		// odd goes right
		else {
			if (node.right == null) {
				node.right = data;
				return;
			}
			insert(data, node.right);
		}
	}

	/**
	 * This creates a complete graph using a backup array
	 * In a complete graph backed by an array, we have an array of size
	 * SUM(Math.pow(2, numLevels)) for numLevels = 0 to n
	 * (using ArrayList though and not passing numLevels)
	 * Also, the index of the left child is at 2*p+1 and
	 * the index of the right child is at 2*p+2, where p is the parent index
	 * 
	 * @param data
	 * @param backupArray
	 */
	public void insertCompleteArray(MyType data, ArrayList<Node<MyType>> backupArray) {
		if (root == null) {
			root = new Node<MyType>(data);
			backupArray.add(root);
		}
		//
		else {
			Node<MyType> cur = new Node<MyType>(data);
			/*
			 * If using an array, this
			 * should increase by size + Math.pow(2, numLevels)
			 * if the last element has already been filled (numLevels++)
			 */
			int numNodes = backupArray.size() - 1;
			backupArray.add(cur);

			int parentIdx = numNodes / 2;
			Node<MyType> par = backupArray.get(parentIdx);

			// was left child, so insert left
			if (2 * parentIdx == numNodes)
				par.left = cur;
			// was right child, so insert right
			else
				par.right = cur;
		}
	}

	/**
	 * Convert codeVal into its binary representation, ignoring the first "1"
	 * Special cases for root, root.left, root.right, then start at 4.
	 * Starting with the 1st digit, move current node left on 0 and right on 1
	 * Look at the last digit: if it's a 0 add to left child, if it's a 1 add
	 * to right child of current
	 * 
	 * @param data
	 *            the data to be inserted
	 * @param codeVal
	 *            the number of nodes already in the tree
	 * @return
	 */
	public int insertUsingBinaryCode(MyType data, int codeVal) {
		// special case to insert root
		if (root == null) {
			root = new Node<MyType>(data);
			return 0;
		}
		// special case for left child of root
		if (codeVal == 0) {
			root.left = new Node<MyType>(data);
			return 1;
		}
		// special case for right child of root; start algorithm with code for 4
		if (codeVal == 1) {
			root.right = new Node<MyType>(data);
			return 4;
		}

		// the binary representation of codeValue, starting at 4, except the
		// leading 1
		String binaryString = convertToBinary(codeVal).substring(1);

		Node<MyType> cur = root;
		Node<MyType> node = new Node<MyType>(data);

		// Make left on 0 and right on 1 until the last character
		for (int i = 0; i < binaryString.length() - 1; i++) {
			if (binaryString.charAt(i) == '0')
				cur = cur.left;
			else
				cur = cur.right;
		}

		// Insert based on the last character (left on 0, right on 1)
		if (binaryString.charAt(binaryString.length() - 1) == '0')
			cur.left = node;
		else
			cur.right = node;

		return codeVal + 1;

	}

	/**
	 * Convert data to binary representation
	 * 
	 * @param numNodes
	 * @return
	 */
	public static String convertToBinary(int numNodes) {
		if (numNodes == 0)
			return "0";
		StringBuilder binarySb = new StringBuilder();
		while (numNodes != 1) {
			binarySb.insert(0, (numNodes % 2) + "");
			numNodes = numNodes / 2;
		}
		return "1" + binarySb;
	}

	/**
	 * This is arbitrary on arbitrary binary trees, more specific on BST
	 */
	@Override
	public Node<MyType> delete(MyType data) {
		if (root == null)
			return null;
		if (root.data == data) {
			Node<MyType> rootBk = root;
			// no children
			if (root.left == null && root.right == null)
				root = null;
			// left child only
			else if (root.right == null)
				root = root.left;
			// right child only
			else if (root.left == null)
				root = root.right;
			// two children
			else {
				root = root.left;
				Node<MyType> cur = root;
				while (cur.right != null)
					cur = cur.right;
				cur.right = rootBk.right;
			}
			return rootBk;
		}
		Node<MyType> parent = findParent(data);
		if (parent == null)
			return null;
		Node<MyType> node;
		if (parent.left.data == data) {
			node = parent.left;
			// if node is a leaf simply set it to null
			if (node.left == null && node.right == null)
				parent.left = null;
			// if it only has a left child, set that child as a child of the
			// parent
			else if (node.left == null)
				parent.left = node.right;
			// if it only has a right child, set that child as a child of the
			// parent
			else if (node.right == null)
				parent.left = node.left;
			// if it has 2 children, make the left child of the node the left
			// child
			// of the parent, and the right child of the node the rightmost
			// child
			else {
				parent.left = node.left;
				Node<MyType> cur = parent;
				while (cur.right != null)
					cur = cur.right;
				cur.right = node.right;
			}

		} else {
			node = parent.right;
			// is node a leaf? then simply set it to null
			if (node.left == null && node.right == null)
				parent.right = null;
			// if it only has a left child, set that child as a child of the
			// parent
			else if (node.left == null)
				parent.right = node.right;
			// if it only has a right child, set that child as a child of the
			// parent
			else if (node.right == null)
				parent.right = node.left;
			// if it has 2 children, make the right child of the node the right
			// child
			// of the parent, and the left child of the node the leftmost child
			else {
				parent.right = node.right;
				Node<MyType> cur = parent;
				while (cur.left != null)
					cur = cur.left;
				cur.left = node.left;
			}
		}
		return node;
	}

	private Node<MyType> findParent(MyType data) {
		if (root == null || root.data == data)
			return null;
		Stack<Node<MyType>> stack = new Stack<>();
		Node<MyType> cur = root;
		stack.push(cur);
		/*
		 * Go through the tree in one of the traversal orders
		 * and if the data at the current node matches, then delete it
		 * Use pre-order traversal
		 */
		while (!stack.isEmpty()) {
			cur = stack.pop();
			// is this the parent of the node we were looking for?
			if ((cur.left != null && cur.left.data == data) || (cur.right != null && cur.right.data == data)) {
				// exit the while loop, we're done
				return cur;
			} else {
				if (cur.right != null)
					stack.push(cur.right);
				if (cur.left != null)
					stack.push(cur.left);
			}
		}
		return null;
	}

	@Override
	public Node<MyType> find(MyType data) {
		if (root == null)
			return null;
		if (root.data == data)
			return root;

		Node<MyType> parent = findParent(data);
		Node<MyType> node;
		if (parent.left.data == data)
			node = parent.left;
		else
			node = parent.right;
		return node;
	}

}
