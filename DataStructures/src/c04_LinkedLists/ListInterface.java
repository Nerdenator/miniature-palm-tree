package c04_LinkedLists;

interface ListInterface<MyType> {
	/* Add to the beginning of the list */
	void addFirst(MyType data);

	/* Add to the end of the list */
	void addLast(MyType data);

	/* Remove from the beginning of the list */
	Node<MyType> removeFirst();

	/* Remove from the end of the list */
	Node<MyType> removeLast();

	/* Find the first node containing value*/
	Node<MyType> find(MyType value);

	/* Delete the node containing value */
	Node<MyType> delete(MyType value);
}
