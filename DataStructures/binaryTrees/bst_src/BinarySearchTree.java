package bst_src;

import binaryTree_src.BinaryTreeInsertAny;
import binaryTree_util.InterfaceBinaryTree;
import binaryTree_util.Node;

/**
 * A Binary Search Tree (BST) is a special kind of binary tree where,
 * for every node,
 * the left subtree is always <= parent
 * and the right subtree is always > parent
 *
 */
public class BinarySearchTree extends BinaryTreeInsertAny<Integer> implements InterfaceBinaryTree<Integer> {
	/**
	 * Empty constructor
	 */
	public BinarySearchTree() {
	}

	/**
	 * Constructor with only one element
	 * 
	 * @param data: the data to insert
	 */
	public BinarySearchTree(int data) {
		insertIterative(data);
	}

	/**
	 * Constructor from an array
	 * 
	 * @param arr: the array of elements to insert
	 */
	public BinarySearchTree(int[] arr) {
		insert(arr);
	}

	///////////////////////////////
	// INSERT
	//////////////////////////////

	/**
	 * Insert all the elements of given array
	 * 
	 * @param arr: the array of elements to insert
	 */
	public void insert(int[] arr) {
		for (int a : arr)
			insert(a);
	}

	/**
	 * Set the interface insert to be the iterative insert
	 * O(log n) average, O(n) worst; space O(1)
	 */
	@Override
	public void insert(Integer data) {
		insertIterative(data);
	}

	/**
	 * Iterative insert
	 * Find the spot to insert, moving left if data <= cur
	 * and right if data > cur
	 * O(log n) average, O(n) worst; space O(1)
	 * 
	 * @param data
	 */
	public void insertIterative(Integer data) {
		// add a new root with data
		if (root == null) {
			root = new Node<Integer>(data);
			return;
		}

		// start at the root
		Node<Integer> cur = root;

		// continue until we reach a leaf (next node is null)
		while (true) {
			// move left for values <= current
			if (data <= cur.data) {
				// insert and stop if it was a leaf
				if (cur.left == null) {
					cur.left = new Node<Integer>(data);
					return;
				}
				// continue otherwise
				cur = cur.left;
			}
			// move right for values > current
			else {
				// insert and stop if it was a leaf
				if (cur.right == null) {
					cur.right = new Node<Integer>(data);
					return;
				}
				// continue otherwise
				cur = cur.right;
			}
		}
	}

	/**
	 * Recursive insert
	 * O(log n) average, O(n) worst; space O(1)
	 * 
	 * @param data: the data to insert
	 */
	public void insertRecursive(Integer data) {
		// add a new root with data
		if (root == null) {
			root = new Node<Integer>(data);
			return;
		}
		// recursively add all other nodes
		else {
			insertRecursive(root, new Node<Integer>(data));
		}
	}

	/**
	 * Insert node as left child of cur if node.data <= cur.data
	 * and as right child if node.data > cur.data
	 * O(log n) average, O(n) worst; space O(1)
	 * 
	 * @param cur: the current node
	 * @param node: the node to insert
	 */
	private void insertRecursive(Node<Integer> cur, Node<Integer> node) {
		// insert left for values <= current
		if (node.data <= cur.data) {
			if (cur.left != null)
				insertRecursive(cur.left, node);
			else
				cur.left = node;
		}
		// insert right for values > current
		else {
			if (cur.right != null)
				insertRecursive(cur.right, node);
			else
				cur.right = node;
		}
	}

	///////////////////////////////
	// DELETE
	//////////////////////////////

	/**
	 * Find the node containing the data, and then delete it, performing tree
	 * rotations as necessary
	 * There are 3 possibilities:
	 * 1. delete leaf (no children)
	 * 2. delete node with one child
	 * 3. delete node with two children
	 * 
	 * @param data: the data we're looking for
	 * @return the deleted node
	 */
	@Override
	public Node<Integer> delete(Integer data) {
		// empty tree, don't have anything to remove
		if (root == null)
			return null;

		// find the node containing the data
		// and its parent
		Node<Integer> cur = root;
		Node<Integer> curParent = null;

		// is cur par's left child?
		boolean isLeftChild = false;

		// while we're not done traversing the tree in BST order
		// and we haven't found the data
		while (cur != null && cur.data != data) {
			// save the parent
			curParent = cur;

			// go left
			if (data <= cur.data) {
				cur = cur.left;
				isLeftChild = true;
			}

			// or go right
			else {
				cur = cur.right;
				isLeftChild = false;
			}
		}

		// didn't find the node, nothing to delete
		if (cur == null)
			return null;

		// 1. if cur is a leaf, delete it
		if (cur.left == null && cur.right == null) {
			// if it's at the root, delete the root
			if (cur == root)
				root = null;
			// if cur is par's left child, delete cur
			else if (isLeftChild)
				curParent.left = null;
			// if cur is par's right child, delete cur
			else
				curParent.right = null;
			return cur;
		}

		// 2. if cur only has one child, then replace cur with that child
		// 2.1 only a left child
		if (cur.right == null) {
			// if node's at the root, replace root
			if (cur == root)
				root = cur.left;
			// if cur is par's left child
			else if (isLeftChild)
				curParent.left = cur.left;
			// if cur is par's right child
			else
				curParent.right = cur.left;
			return cur;
		}
		// 2.2 only a right child
		if (cur.left == null) {
			// if node's at the root, replace root
			if (cur == root)
				root = cur.right;
			// if cur is par's left child
			else if (isLeftChild)
				curParent.left = cur.right;
			// if cur is par's right child
			else
				curParent.right = cur.right;
			return cur;
		}

		// 3. if node has two children then replace cur
		// with its in-order successor
		// (leftmost child leaf of the right subtree)

		// find in-order successor, starting at the right node
		Node<Integer> successor = cur.right;
		// the parent of the in-order successor
		Node<Integer> sucParent = cur;

		// if successor has no left children, then we found the in-order
		// successor
		// otherwise, look to the left

		// find the leftmost leaf child and its parent
		while (successor.left != null) {
			sucParent = successor;
			successor = successor.left;
		}

		// break the connection from suc's parent to suc (we went left)
		sucParent.left = successor.right;
		// move the right child of the deleted node to the right child of
		// the successor
		successor.right = cur.right;

		// if the current node is the root, replace it with successor
		if (root == cur)
			root = successor;
		// if cur is the left child
		else if (isLeftChild)
			curParent.left = successor;
		// if cur is the right child
		else
			curParent.right = successor;

		// reconnect cur's left child to the successor
		successor.left = cur.left;

		return cur;
	}

	///////////////////////////////
	// FIND
	//////////////////////////////

	/**
	 * Set the interface find to be findIterative
	 * O(log n) average, O(n) worst; space O(1)
	 * 
	 * @param data: the value we're looking for
	 * @return the node that contains the value
	 */
	@Override
	public Node<Integer> find(Integer data) {
		return findIterative(data);
	}

	/**
	 * Find iterative: find the node containing data by iteratively traversing
	 * the tree
	 * O(log n) average, O(n) worst; space O(1)
	 * 
	 * @param data: the value we're looking for
	 * @return the node that contains the value
	 */
	public Node<Integer> findIterative(Integer data) {
		// start looking from the root
		Node<Integer> cur = root;

		// loop until we either find data or we reach a null node
		while (cur != null) {
			// found it
			if (cur.data == data)
				return cur;
			// look left
			if (data < cur.data)
				cur = cur.left;
			// look right
			else
				cur = cur.right;
		}
		return cur;
	}

	/**
	 * Find recursive, find the node containing data by recursing
	 * O(log n) average, O(n) worst; space O(1)
	 * 
	 * @param data: the value we're looking for
	 * @return the node that contains the value
	 */
	public Node<Integer> findRecursive(Integer data) {
		return findRecursive(root, data);
	}

	/**
	 * Find recursive, find the node containing data by recursing on the correct
	 * side
	 * O(log n) average, O(n) worst; space O(1)
	 * 
	 * @param cur: the current node
	 * @param data: the value we're looking for
	 * @return the node that contains the value
	 */
	private Node<Integer> findRecursive(Node<Integer> cur, Integer data) {
		// empty sub-tree, data not found
		if (cur == null)
			return null;

		// data is at current node
		if (cur.data == data)
			return cur;

		// search in left subtree
		if (data < cur.data)
			return findRecursive(cur.left, data);

		// search in right subtree
		return findRecursive(cur.right, data);
	}

	/**
	 * Find the parent of the node containing the data
	 * Returns null if no such node exists or if it's at the root
	 * O(log n) average, O(n) worst; space O(1)
	 * 
	 * @param data: the data we're looking for
	 * @return the parent node of the node containing the data, or null
	 */
	public Node<Integer> findParentIterative(Integer data) {
		if (root == null || root.data == data)
			return null;

		Node<Integer> cur = root;
		while (true) {
			// is this the parent of the node we were looking for?
			if ((cur.left != null && cur.left.data == data) || //
					(cur.right != null && cur.right.data == data))
				// exit the while loop, we're done
				return cur;

			// go left if data < cur.data and there's a left node
			if (data < cur.data && cur.left != null)
				cur = cur.left;

			// go right if data > cur.data and there's a right node
			else if (data > cur.data && cur.right != null)
				cur = cur.right;

			// we didn't find the data
			else
				return null;
		}
	}

	/**
	 * Find the parent of the node containing the data by recursively finding
	 * the parent
	 * Returns null if no such node exists or if it's at the root
	 * O(log n) average, O(n) worst; space O(1)
	 * 
	 * @param data: the data we're looking for
	 * @return the parent node of the node containing the data, or null
	 */
	public Node<Integer> findParentRecursive(Integer data) {
		if (root == null || root.data == data)
			return null;
		return findParentRecursive(root, data);
	}

	/**
	 * Find the parent of the node containing the data starting at given node
	 * Returns null if no such node exists or if it's at the root
	 * O(log n) average, O(n) worst; space O(1)
	 * 
	 * @param cur: the current node
	 * @param data: the data we're looking for
	 * @return the parent node of the node containing the data, or null
	 */
	private Node<Integer> findParentRecursive(Node<Integer> cur, Integer data) {
		// we found it
		if ((cur.left != null && cur.left.data == data) || //
				(cur.right != null && cur.right.data == data))
			return cur;

		// go left if data < cur.data and there's a left node
		if (data < cur.data && cur.left != null)
			return findParentRecursive(cur.left, data);

		// go right if data > cur.data and there's a right node
		else if (data > cur.data && cur.right != null)
			return findParentRecursive(cur.right, data);

		// didn't find it
		return null;
	}

	/**
	 * Find the node containing the data
	 * Use findParent to find the parent and then check if right child or
	 * left child == data
	 * O(log n) average, O(n) worst; space O(1)
	 * 
	 * @param data: the value we're looking for
	 * @return the node that contains the value or null
	 */
	public Node<Integer> findUsingFindParentIterative(Integer data) {
		// if empty, not found
		if (root == null)
			return null;

		// if it's at the root, return it
		else if (root.data == data)
			return root;

		// find the parent node
		Node<Integer> parent = findParentIterative(data);

		// if parent is null, data is not in tree
		if (parent == null)
			return null;

		// otherwise, check if left child and return
		if (parent.left != null && parent.left.data == data)
			return parent.left;

		// or if right child and return
		return parent.right;
	}

}
