package binaryTree_src;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import binaryTree_util.InterfaceBinaryTree;
import binaryTree_util.Node;

public class BinaryTreeTraversals {

	//////////////////////////
	// Depth-First TRAVERSALS
	/////////////////////////

	/**
	 * Pre-order traversal in an iterative fashion
	 * Root is traversed first, then its left child, then its right child
	 * Use a STACK data structure
	 * 
	 * @param binTree the tree to perform the traversal on
	 * @return a string with the traversal for easy testing
	 */
	public static String preOrderTraversalIterative(InterfaceBinaryTree<?> binTree) {
		if (binTree.root() == null)
			return "[ ]";
		StringBuilder sb = new StringBuilder("[ ");

		// use a stack
		Stack<Node<?>> stack = new Stack<>();
		// add root to the stack
		stack.push(binTree.root());

		// while there are still nodes on the stack
		while (!stack.isEmpty()) {
			// pop the top of the stack
			Node<?> cur = stack.pop();
			// add it to the result
			sb.append(cur.data + " ");

			// add the right and left children to stack (if they exist)
			// right first
			if (cur.right != null)
				stack.push(cur.right);
			// left last so it gets popped first
			if (cur.left != null)
				stack.push(cur.left);
		}
		return sb + "]";
	}

	/**
	 * Pre-order traversal in a recursive fashion, by calling recursive function
	 * Root is traversed first, then its left child, then its right child
	 * 
	 * @param binTree the tree to perform the traversal on
	 * @return a string with the traversal for easy testing
	 */
	public static String preOrderTraversalRecursive(InterfaceBinaryTree<?> binTree) {
		StringBuilder sb = new StringBuilder();
		// recursively traverse the tree
		preOrderTraversalRecursive(binTree.root(), sb);
		return "[ " + sb + "]";
	}

	/**
	 * Helper function for pre-order Recursive. Given node, traverse node,
	 * traverse left, then traverse right.
	 * 
	 * @param node current node
	 * @param sb partial result
	 */
	private static void preOrderTraversalRecursive(Node<?> node, StringBuilder sb) {
		if (node == null)
			return;
		sb.append(node.data + " ");
		preOrderTraversalRecursive(node.left, sb);
		preOrderTraversalRecursive(node.right, sb);
	}

	/**
	 * Post-order traversal in an iterative fashion
	 * Left subtree is traversed first, then the right subtree, then the root
	 * 
	 * @param binTree the tree to perform the traversal on
	 * @return a string with the traversal for easy testing
	 */
	public static String postOrderTraversalIterative(InterfaceBinaryTree<?> binTree) {
		if (binTree.root() == null)
			return "[ ]";

		StringBuilder sb = new StringBuilder("[ ");

		// use a stack
		Stack<Node<?>> stack = new Stack<>();
		// add root to the stack
		stack.push(binTree.root());

		// start at root
		Node<?> cur = binTree.root();
		// no node previously visited
		Node<?> lastVisited = null;

		// while there are still nodes on the stack
		while (!stack.isEmpty()) {
			// add the left children to the stack
			if (cur.left != null) {
				cur = cur.left;
				stack.push(cur);
			}

			else {
				// get the top node on the stack;
				// need to visit right children first
				Node<?> peekNode = stack.peek();

				// if it has right children that haven't been visited
				// visit the right child of the current top of the stack
				if (peekNode.right != null && peekNode.right != lastVisited) {
					cur = peekNode.right;
					// add it to the stack
					stack.push(cur);
				}

				// otherwise, add the top of the stack to the traversal list and
				// save it as the last visited node
				else {
					lastVisited = stack.pop();
					sb.append(lastVisited.data + " ");
				}
			}
		}
		return sb + "]";
	}

	/**
	 * Post-order traversal in a recursive fashion, by calling recursive
	 * function
	 * Left subtree is traversed first, then the right subtree, then the root
	 * 
	 * @param binTree the tree to perform the traversal on
	 * @return a string with the traversal for easy testing
	 */
	public static String postOrderTraversalRecursive(InterfaceBinaryTree<?> binTree) {
		StringBuilder sb = new StringBuilder();
		postOrderTraversalRecursive(binTree.root(), sb);
		return "[ " + sb + "]";
	}

	/**
	 * Helper function for post-order Recursive. Given node, traverse left, then
	 * traverse right, then traverse node.
	 * 
	 * @param node current node
	 * @param sb partial result
	 */
	private static void postOrderTraversalRecursive(Node<?> node, StringBuilder sb) {
		if (node == null)
			return;

		postOrderTraversalRecursive(node.left, sb);
		postOrderTraversalRecursive(node.right, sb);
		sb.append(node.data + " ");
	}

	/**
	 * In-order traversal in an iterative fashion
	 * Left subtree is traversed first, then the root, then the right subtree
	 * (this gives sorted list for BST)
	 * 
	 * @param binTree the tree to perform the traversal on
	 * @return a string with the traversal for easy testing
	 */
	public static String inOrderTraversalIterative(InterfaceBinaryTree<?> binTree) {
		if (binTree.root() == null)
			return "[ ]";
		StringBuilder sb = new StringBuilder("[ ");

		/*
		 * Keep traversing left child until reach null, adding to stack
		 * If reached null, pop top of stack and add its right child
		 * Then start traversing left child until null again etc.
		 */
		// use a stack
		Stack<Node<?>> stack = new Stack<>();
		// add root to the stack
		stack.push(binTree.root());
		// keep track of current node
		Node<?> cur = binTree.root();

		// while there are still nodes on the stack
		while (!stack.isEmpty()) {
			// add left until there are no more children
			if (cur.left != null) {
				cur = cur.left;
				stack.push(cur);
			}

			else {
				// keep popping the top adding to the result
				cur = stack.pop();
				sb.append(cur.data + " ");
				// keep popping until there is a right child to add
				while (cur.right == null && !stack.isEmpty()) {
					cur = stack.pop();
					sb.append(cur.data + " ");
				}
				// add the right child to the stack
				if (cur.right != null) {
					cur = cur.right;
					stack.push(cur);
				}
			}
		}
		return sb + "]";
	}

	/**
	 * In-order traversal in a recursive fashion
	 * Left subtree is traversed first, then the root, then the right subtree
	 * (this gives sorted list for BST)
	 * 
	 * @param binTree the tree to perform the traversal on
	 * @return a string with the traversal for easy testing
	 */
	public static String inOrderTraversalRecursive(InterfaceBinaryTree<?> binTree) {
		StringBuilder sb = new StringBuilder();
		inOrderTraversalRecursive(binTree.root(), sb);
		return "[ " + sb + "]";
	}

	/**
	 * Helper function for in-order Recursive. Given node, traverse left, then
	 * traverse node, then traverse right.
	 * 
	 * @param node current node
	 * @param sb partial result
	 */
	private static void inOrderTraversalRecursive(Node<?> node, StringBuilder sb) {
		if (node == null)
			return;
		inOrderTraversalRecursive(node.left, sb);
		sb.append(node.data + " ");
		inOrderTraversalRecursive(node.right, sb);
	}

	///////////////////////////
	// Breadth-First TRAVERSAL
	///////////////////////////

	/**
	 * Perform a breadth-first-search on the tree, traversing tree by level
	 * 
	 * @param binTree the tree to perform the traversal on
	 * @return a string with the traversal for easy testing
	 */
	public static String levelOrderTraversal(InterfaceBinaryTree<?> binTree) {
		if (binTree.root() == null)
			return "[ ]";
		StringBuilder sb = new StringBuilder();

		// use a queue to hold the nodes; add null nodes too
		Queue<Node<?>> queue = new LinkedList<>();
		// keep track of current node
		Node<?> cur = binTree.root();
		// add to queue
		queue.add(cur);

		// keep track of the number of nodes on the current level
		// and the number of nodes on the next level
		int numNodesCurLevel = 1; // root
		int numNodesNextLevel = 0; // don't know yet

		while (!queue.isEmpty()) {
			// remove the first node in the queue, now we have less nodes on
			// current level
			cur = queue.remove();
			numNodesCurLevel--;

			// If the current node is not null, enqueue its (possibly null)
			// children and increase the number of nodes on next level
			if (cur != null) {
				sb.append(cur.data + " ");
				queue.add(cur.left);
				queue.add(cur.right);
				numNodesNextLevel += 2;
			}

			// if already printed all the nodes on this level, advance to the
			// next level
			if (numNodesCurLevel == 0 && numNodesNextLevel > 0) {
				sb.append("| ");
				numNodesCurLevel = numNodesNextLevel;
				numNodesNextLevel = 0;
			}
		}
		return "[ " + sb + "]";
	}
}
