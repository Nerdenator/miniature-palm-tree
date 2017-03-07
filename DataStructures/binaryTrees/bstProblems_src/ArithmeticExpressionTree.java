package bstProblems_src;

import java.util.Stack;

import binaryTree_src.BinaryTreeInsertRandomDirection;
import binaryTree_util.Node;

public class ArithmeticExpressionTree {

	public static int evaluateTree(BinaryTreeInsertRandomDirection<String> tree) {
		if (tree.root == null)
			return 0;
		int result = 0;
		Stack<String> stack = new Stack<>();
		evaluateTree(tree.root, stack);

		while (!stack.isEmpty()) {
			// first two are ints in well formed tree
			String op = evaluateOperation(stack.pop(), stack.pop(), stack.pop());
			if (stack.isEmpty())
				return Integer.parseInt(op);
			stack.push(op);
		}
		return result;
	}

	private static int evaluateTree(Node<String> cur, Stack<String> stack) {
		int result = 0;

		// if it's an operation
		if (!isInteger(cur.data)) {
			// evaluate numbers leafs with operations between them
			if (isInteger(cur.left.data) && isInteger(cur.right.data)) {
				stack.push(evaluateOperation(cur.left.data, cur.right.data, cur.data));
				System.out.println(stack);
			}
			// evaluate when they're not both numbers
			else {
				// push the data (operation)
				stack.push(cur.data);
				System.out.println(cur.data);
				// the left is an integer, the right is an operation
				if (isInteger(cur.left.data)) {
					// push the left
					stack.push(cur.left.data);
					System.out.println(stack);
					// continue to the right
					evaluateTree(cur.right, stack);
				}
				// the left is an operation, the right is an integer
				else {
					// push the right
					stack.push(cur.right.data);
					System.out.println(stack);
					// continue to the left
					evaluateTree(cur.left, stack);
				}
			}
		}
		return result;
	}

	private static String evaluateOperation(String left, String right, String op) {
		int x = Integer.parseInt(left);
		int y = Integer.parseInt(right);
		int result = 0;
		switch (op) {
		case "+":
			result = x + y;
			break;
		case "-":
			result = x - y;
			break;
		case "*":
			result = x * y;
			break;
		case "/":
			result = x / y;
			break;
		}
		return Integer.toString(result);
	}

	private static boolean isInteger(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}
}
