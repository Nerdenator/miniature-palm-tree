package linkedLists_src;

/**
 * Class to hold node information
 * 
 * @author Adina
 *
 */
public class Node<MyType> {
	/* data contained in node*/
	public MyType data;
	/* link to next element */
	public Node<MyType> next;
	/* link to previous element, for doubly linked lists*/
	public Node<MyType> prev;

	/* empty constructor*/
	public Node() {
	}

	/* constructor */
	public Node(MyType data) {
		this.data = data;
	}
}
