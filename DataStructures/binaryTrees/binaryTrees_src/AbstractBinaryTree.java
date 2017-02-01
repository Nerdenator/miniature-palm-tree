package binaryTrees_src;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public abstract class AbstractBinaryTree<MyType> {

	Node<MyType> root;

	/* INSERTION 
	   =================================================================== */
	/**
	 * Insert node containing data
	 * into the tree (depends on tree type)
	 * 
	 * @param data
	 */
	public abstract void insert(MyType data);

	/* DELETION
	   =================================================================== */
	/**
	 * Delete the node containing data
	 * from the tree (depends on tree type)
	 * 
	 * @param data
	 * @return the node that was deleted, null if not found
	 */
	public abstract Node<MyType> delete(MyType data);

	/**
	 * Delete the contents of the tree (just set root to null)
	 */
	public void clear() {
		root = null;
	};

	/**
	 * Is the tree empty?
	 * 
	 * @return true if root==null
	 */
	public boolean isEmpty() {
		return root == null;
	}

	/* FIND 
	   ====================================================================== */
	/**
	 * Find the node containing given data
	 * 
	 * @param data
	 *            Object to find
	 * @return the node containing data
	 */
	public abstract Node<MyType> find(MyType data);

	/* Depth-First TRAVERSAL: all are the same regardless of the type of tree
	   ====================================================================== */
	/**
	 * Pre-order traversal in an iterative fashion
	 * Root is traversed first, then its left child, then its right child
	 * Use a STACK data structure
	 * 
	 * @return a string with the traversal for easy testing
	 */
	public String preOrderTraversal_Iterative() {
		if (root == null)
			return "[ ]";
		StringBuilder sb = new StringBuilder("[ ");
		/*
		 * Add current node to stack, then, while the stack is not empty,
		 * pop the top of the stack and add its left and right children respectively
		 */
		Stack<Node<MyType>> stack = new Stack<>();
		stack.push(root);
		while (!stack.isEmpty()) {
			Node<MyType> cur = stack.pop();
			sb.append(cur.data + " ");
			if (cur.right != null)
				stack.push(cur.right);
			if (cur.left != null)
				stack.push(cur.left);
		}
		return sb + "]";
	};

	/**
	 * Pre-order traversal in a recursive fashion
	 * Root is traversed first, then its left child, then its right child
	 * 
	 * @return a string with the traversal for easy testing
	 */
	public String preOrderTraversal_Recursive() {
		StringBuilder sb = new StringBuilder();
		preOrderTraversal_Recursive(root, sb);
		return "[ " + sb + "]";
	};

	/**
	 * Helper function for Pre-order Recursive
	 */
	private void preOrderTraversal_Recursive(Node<MyType> node, StringBuilder sb) {
		if (node == null)
			return;
		sb.append(node.data + " ");
		preOrderTraversal_Recursive(node.left, sb);
		preOrderTraversal_Recursive(node.right, sb);
	}

	/**
	 * Post-order traversal in an iterative fashion
	 * Left subtree is traversed first, then the right subtree, then the root
	 * 
	 * @return a string with the traversal for easy testing
	 */
	public String postOrderTraversal_Iterative() {
		if (root == null)
			return "[ ]";

		StringBuilder sb = new StringBuilder("[ ");
		Stack<Node<MyType>> stack = new Stack<>();
		Node<MyType> cur = root;
		Node<MyType> lastVisited = null;
		stack.push(cur);

		while (!stack.isEmpty()) {
			/* add the left children to the stack */
			if (cur.left != null) {
				cur = cur.left;
				stack.push(cur);
			} else {
				/* get the top node on the stack */
				Node<MyType> peekNode = stack.peek();
				/* if it has right children that haven't been visited
				    visit the right child of the current top of the stack */
				if (peekNode.right != null && peekNode.right != lastVisited) {
					cur = peekNode.right;
					stack.push(cur);
				}
				/* otherwise, add the top of the stack to the traversal list and 
				   save it as the last visited node */
				else {
					lastVisited = stack.pop();
					sb.append(lastVisited.data + " ");
				}
			}
		}
		return sb + "]";
	};

	/**
	 * Post-order traversal in a recursive fashion
	 * Left subtree is traversed first, then the right subtree, then the root
	 * 
	 * @return a string with the traversal for easy testing
	 */
	public String postOrderTraversal_Recursive() {
		StringBuilder sb = new StringBuilder();
		postOrderTraversal_Recursive(root, sb);
		return "[ " + sb + "]";
	};

	/**
	 * Helper function for Post-order Recursive
	 */
	private void postOrderTraversal_Recursive(Node<MyType> node, StringBuilder sb) {
		if (node == null)
			return;
		postOrderTraversal_Recursive(node.left, sb);
		postOrderTraversal_Recursive(node.right, sb);
		sb.append(node.data + " ");
	}

	/**
	 * In-order traversal in an iterative fashion
	 * Left subtree is traversed first, then the root, then the right subtree
	 * (this gives sorted list for BST)
	 * 
	 * @return a string with the traversal for easy testing
	 */
	public String inOrderTraversal_Iterative() {
		if (root == null)
			return "[ ]";
		StringBuilder sb = new StringBuilder("[ ");
		/* 
		 * Keep traversing left child until reach null, adding to stack
		 * If reached null, pop top of stack and add its right child
		 * Then start traversing left child until null again etc.
		 */
		Stack<Node<MyType>> stack = new Stack<>();
		Node<MyType> cur = root;
		stack.push(cur);

		while (!stack.isEmpty()) {
			/* add left until there are no more children */
			if (cur.left != null) {
				cur = cur.left;
				stack.push(cur);
			}
			/* otherwise, keep popping the top adding to the traversal list, 
			 *  until we find a node that has a right child, and push this child to the stack 
			 */
			else {
				cur = stack.pop();
				sb.append(cur.data + " ");
				// keep popping until there is a right child to add
				while (cur.right == null && !stack.isEmpty()) {
					cur = stack.pop();
					sb.append(cur.data + " ");
				}
				// add the right child
				if (cur.right != null) {
					cur = cur.right;
					stack.push(cur);
				}
			}
		}
		return sb + "]";
	};

	/**
	 * In-order traversal in a recursive fashion
	 * Left subtree is traversed first, then the root, then the right subtree
	 * (this gives sorted list for BST)
	 * 
	 * @return a string with the traversal for easy testing
	 */
	public String inOrderTraversal_Recursive() {
		StringBuilder sb = new StringBuilder();
		inOrderTraversal_Recursive(root, sb);
		return "[ " + sb + "]";
	};

	/**
	 * Helper function for In-order Recursive
	 */
	private void inOrderTraversal_Recursive(Node<MyType> node, StringBuilder sb) {
		if (node == null)
			return;
		inOrderTraversal_Recursive(node.left, sb);
		sb.append(node.data + " ");
		inOrderTraversal_Recursive(node.right, sb);
	}

	/* Breadth-First TRAVERSAL: the same regardless of the type of tree 
	   =================================================================== */
	public String levelOrderTraversal() {
		if (root == null)
			return "[]";
		StringBuilder sb = new StringBuilder();
		/* use a queue to hold the nodes; add null nodes too */
		Queue<Node<MyType>> queue = new LinkedList<>();
		Node<MyType> cur = root;
		//add to queue
		queue.add(cur);
		/* 
		 * keep track of the number of nodes on the current level
		 * and the number of nodes on the next level 
		 * */
		int numNodesCurLevel = 1; //root
		int numNodesNextLevel = 0; //don't know yet

		while (!queue.isEmpty()) {
			cur = queue.remove();
			numNodesCurLevel--;

			/* 
			 * If the current node is not null, enqueue its children (can be null)
			 * and increase the number of nodes on next level 
			 */
			if (cur != null) {
				sb.append(cur.data + "\t");
				queue.add(cur.left);
				queue.add(cur.right);
				numNodesNextLevel += 2;
			} else
				sb.append("-\t");

			/* if already printed all the nodes on this level, advance to the next level */
			if (numNodesCurLevel == 0) {
				sb.append("\n");
				numNodesCurLevel = numNodesNextLevel;
				numNodesNextLevel = 0;
			}
		}
		return "[\n" + sb + "]";
	}

	/* =================================================================== */
	/**
	 * Display the tree graphically
	 */
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
