package binaryTree_src;

import java.util.Stack;

import binaryTree_util.InterfaceBinaryTree;
import binaryTree_util.Node;

public class BinaryTreeInsertRandomDirection<MyType> implements InterfaceBinaryTree<MyType> {
	// the root node of the tree
	public Node<MyType> root;

	@Override
	public Node<MyType> root() {
		return root;
	}

	/**
	 * This insert uses a random number to decide which
	 * direction to go from a given node
	 * Could generate a very unbalanced tree
	 * 
	 * @param data: the data to insert
	 */
	@Override
	public void insert(MyType data) {
		if (root == null)
			root = new Node<MyType>(data);
		else
			insert(root, data);
	}

	/**
	 * This insert uses a random number to decide which
	 * direction to go from a given node
	 * Could generate a very unbalanced tree
	 * 
	 * @param cur: current node
	 * @param data: data to insert
	 */
	private void insert(Node<MyType> cur, MyType data) {
		int score = (int) (Math.random() * 100);

		// even goes left
		if (score % 2 == 0) {
			insertLeft(cur, data);
		}
		// odd goes right
		else {
			insertRight(cur, data);
		}
	}

	/**
	 * Insert to the left
	 * 
	 * @param cur: current node
	 * @param data: data to insert
	 */
	public void insertLeft(Node<MyType> cur, MyType data) {
		Node<MyType> node = new Node<>(data);
		if (cur.left == null) {
			cur.left = node;
			return;
		}
		insert(cur.left, data);
	}

	public void insertRight(Node<MyType> cur, MyType data) {
		Node<MyType> node = new Node<>(data);
		if (cur.right == null) {
			cur.right = node;
			return;
		}
		insert(cur.right, data);
	}

	/**
	 * This is arbitrary on arbitrary binary trees, more specific on BST
	 * 
	 * @param data: the data we're looking for
	 * @return the deleted node
	 */
	@Override
	public Node<MyType> delete(MyType data) {
		// if the tree is empty
		if (root == null)
			return null;

		// if the data is at the root
		if (root.data.equals(data)) {
			// save the root (which we're deleting)
			Node<MyType> rootBk = root;

			// if there are no children, the tree is empty
			if (root.left == null && root.right == null)
				root = null;

			// if there's only a left child, make it the root
			else if (root.right == null)
				root = root.left;

			// if there's only a right child, make it the root
			else if (root.left == null)
				root = root.right;

			// if there are two children, move the left to the root
			else {
				root = root.left;
				// find the rightmost leaf of this new root
				Node<MyType> cur = root;
				while (cur.right != null)
					cur = cur.right;
				// and connect it to the right child of the root we're deleting
				cur.right = rootBk.right;
			}
			// return the saved root
			return rootBk;
		}

		// if the data is not at the root, find the parent node of the node
		// containing the data
		Node<MyType> parent = findParent(data);

		// if the parent is null, we didn't find data in tree
		if (parent == null)
			return null;

		// otherwise
		Node<MyType> node;
		// if the node containing the data is a left child
		if (parent.left != null && parent.left.data == data) {
			node = parent.left;
			// if node is a leaf simply set it to null
			if (node.left == null && node.right == null)
				parent.left = null;
			// if it only has a left child, connect it to the parent
			else if (node.left == null)
				parent.left = node.right;
			// if it only has a right child, connect it to the parent
			else if (node.right == null)
				parent.left = node.left;
			// if it has 2 children
			else {
				// make the left child of the node the left child of the parent
				parent.left = node.left;
				// find the rightmost leaf of this node
				Node<MyType> cur = parent;
				while (cur.right != null)
					cur = cur.right;
				// and connect it to the right child of the node we're deleting
				cur.right = node.right;
			}
		}

		// if the node containing the data is a right child
		else {
			node = parent.right;
			// if node is a leaf simply set it to null
			if (node.left == null && node.right == null)
				parent.right = null;
			// if it only has a left child, connect it to the parent
			else if (node.left == null)
				parent.right = node.right;
			// if it only has a right child, connect it to the parent
			else if (node.right == null)
				parent.right = node.left;
			// if it has 2 children
			else {
				// make the right child of the node the right child of the
				// parent
				parent.right = node.right;
				// find the leftmost leaf of this node
				Node<MyType> cur = parent;
				while (cur.left != null)
					cur = cur.left;
				// and connect it to the left child of the node we're deleting
				cur.left = node.left;
			}
		}
		// return the deleted node
		return node;
	}

	/**
	 * Find the parent of the node containing the data
	 * Go through the tree in one of the tree traversal orders (in this case
	 * pre-order) and if the data in one of the children of the current node
	 * matches, then return the node
	 * 
	 * @param data: the data we're looking for
	 * @return the parent node of the node containing the data
	 */
	private Node<MyType> findParent(MyType data) {
		// if tree is empty or data is at the root
		if (root == null || root.data == data)
			return null;

		// use a stack
		Stack<Node<MyType>> stack = new Stack<>();
		Node<MyType> cur = root;
		// add root to the stack
		stack.push(cur);

		// while the stack isn't empty
		while (!stack.isEmpty()) {
			cur = stack.pop();
			// is this the parent of the node containing data
			if ((cur.left != null && cur.left.data == data) || //
					(cur.right != null && cur.right.data == data)) {
				// exit the while loop, we're done
				return cur;
			}
			// otherwise, continue traversing
			else {
				if (cur.right != null)
					stack.push(cur.right);
				if (cur.left != null)
					stack.push(cur.left);
			}
		}
		return null;
	}

	@Override
	/**
	 * Find the node containing data by using findParent
	 * 
	 * @param data: the value we're looking for
	 * @return the node that contains the value
	 */
	public Node<MyType> find(MyType data) {
		// if tree is empty
		if (root == null)
			return null;
		// if the data is at the root
		if (root.data == data)
			return root;

		// find the parent of the node
		Node<MyType> parent = findParent(data);
		if (parent == null)
			return null;
		// return the correct child
		if (parent.left != null && parent.left.data == data)
			return parent.left;
		return parent.right;
	}

	/**
	 * Delete the contents of the tree (just set root to null)
	 */
	@Override
	public void clear() {
		root = null;
	}

	/**
	 * Is the tree empty?
	 * 
	 * @return true if root==null
	 */
	@Override
	public boolean isEmpty() {
		return root == null;
	}

	/**
	 * Display the tree graphically
	 */
	@Override
	public void displayTree() {
		Stack<Node<MyType>> globalStack = new Stack<>();
		globalStack.push(root);
		int nBlanks = 32;
		boolean isRowEmpty = false;
		while (!isRowEmpty) {
			Stack<Node<MyType>> localStack = new Stack<>();
			isRowEmpty = true;
			for (int j = 0; j < nBlanks; j++)
				System.out.print(" ");
			while (!globalStack.isEmpty()) {
				Node<MyType> temp = globalStack.pop();
				if (temp != null) {
					System.out.print(temp.data);
					localStack.push(temp.left);
					localStack.push(temp.right);
					if (temp.left != null || temp.right != null)
						isRowEmpty = false;
				} else {
					System.out.print("--");
					localStack.push(null);
					localStack.push(null);
				}
				for (int j = 0; j < nBlanks * 2 - 2; j++)
					System.out.print(" ");
			} // end while globalStack not empty
			System.out.println();
			nBlanks /= 2;
			while (localStack.isEmpty() == false)
				globalStack.push(localStack.pop());
		} // end while isRowEmpty is false
		System.out.println("......................................................................\n");
	} // end displayTree()

}
