package binaryTree_util;

public class Node<MyType> {
	public MyType data;
	public Node<MyType> left;
	public Node<MyType> right;

	Node() {
	}

	public Node(MyType data) {
		this.data = data;
	}
}
