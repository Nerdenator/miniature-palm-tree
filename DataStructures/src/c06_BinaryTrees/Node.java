package c06_BinaryTrees;

public class Node<MyType> {
	MyType data;
	Node<MyType> left;
	Node<MyType> right;

	Node() {
	}

	Node(MyType data) {
		this.data = data;
	}
}
