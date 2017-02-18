package binaryTree_util;

public interface InterfaceBinaryTree<MyType> {
	// return the root
	public Node<MyType> root();

	// insert a node into the tree
	public void insert(MyType data);

	// delete a node from the tree
	public Node<MyType> delete(MyType data);

	// delete all nodes from the tree
	public void clear();

	// is this tree empty
	public boolean isEmpty();

	// find the node with node.data=data in the tree
	public Node<MyType> find(MyType data);

	// display the tree for debugging purposes
	public void displayTree();
}
