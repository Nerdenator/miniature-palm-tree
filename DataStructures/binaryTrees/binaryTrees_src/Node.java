package binaryTrees_src;

public class Node<MyType> {
	public MyType data;
	public Node<MyType> left;
	public Node<MyType> right;

	Node() {
	}

	Node(MyType data) {
		this.data = data;
	}
}
