package c04_LinkedLists;

/**
 * Linked list with links to next and prev nodes
 * Also double-ended with tail pointing to end of list
 * 
 * @author adina
 *
 * @param <MyType>
 */
public class LinkedListDoublyLinked<MyType> implements ListInterface<MyType> {
	private Node<MyType> head;
	private Node<MyType> tail;

	// empty constructor
	public LinkedListDoublyLinked() {
		head = null;
		tail = null;
	}

	// constructor for one node
	public LinkedListDoublyLinked(MyType data) {
		head = new Node<>(data);
		tail = head;
	}

	// constructor from an array of nodes
	public LinkedListDoublyLinked(MyType[] array) {
		for (MyType data : array)
			addFirst(data);
	}

	/**
	 * Add to the beginning of the list in O(1)
	 * 
	 * @param data
	 */
	@Override
	public void addFirst(MyType data) {
		// if the list was empty
		if (head == null) {
			head = new Node<>(data);
			tail = head;
			return;
		}
		// otherwise
		Node<MyType> temp = new Node<>(data);
		temp.next = head;
		head.prev = temp;
		head = temp;
	}

	/**
	 * Remove from the beginning of the list in O(1)
	 * 
	 * @return the removed node
	 */
	@Override
	public Node<MyType> removeFirst() {
		//if the list was emoty, no changes needed
		if (head == null)
			return null;
		//save the return node
		Node<MyType> temp = head;
		//if only one node, clear list
		if (head.next == null) {
			head = null;
			tail = null;
			return temp;
		}
		//advance head
		head = head.next;
		//sever connection to prev
		head.prev = null;

		return temp;
	}

	/**
	 * Add to the end of the list in O(1)
	 * 
	 * @param data
	 */
	@Override
	public void addLast(MyType data) {
		//if the list was empty
		if (head == null) {
			head = new Node<>(data);
			tail = head;
			return;
		}
		//add new node after the tail
		Node<MyType> temp = new Node<>(data);
		tail.next = temp;
		//keep connections
		temp.prev = tail;
		//reset tail
		tail = temp;
	}

	/**
	 * Remove from the end of list in O(1) time
	 * 
	 * @return the last node
	 */
	@Override
	public Node<MyType> removeLast() {
		//either null or last node, will return
		Node<MyType> temp = tail;
		//if the list is either empty or has 1 element
		if (head == null || tail == head) {
			tail = null;
			head = null;
			return temp;
		}
		//otherwise, move tail to next-to-last element
		tail = tail.prev;
		//sever connection to last node
		tail.next = null;
		return temp;
	}

	/**
	 * Find the first node containing value in O(n) time
	 * 
	 * @param value
	 * @return the node containing value
	 */
	@Override
	public Node<MyType> find(MyType value) {
		//either null or the node we're looking for
		Node<MyType> cur = head;

		while (cur != null && !cur.data.equals(value))
			cur = cur.next;

		return cur;
	}

	/**
	 * Delete the node that contains value in O(n)
	 * 
	 * @param value
	 * @return the deleted Node
	 */
	@Override
	public Node<MyType> delete(MyType value) {
		Node<MyType> cur = find(value);
		if (cur == null)
			return null;

		//if it's at the head
		if (cur == head) {
			//if there's only one element in the list
			if (head == tail) {
				head = null;
				tail = null;
				return cur;
			}
			//otherwise advance head
			head = head.next;
			//and cut connection
			head.prev = null;
			return cur;
		}
		//if it's at the tail
		if (cur == tail) {
			tail = tail.prev;
			tail.next = null;
			return cur;
		}
		//otherwise if it's not at the head
		cur.prev.next = cur.next;
		cur.next.prev = cur.prev;
		return cur;
	}

	/**
	 * Convert the list to a string, starting at head
	 */
	@Override
	public String toString() {
		if (head == null)
			return "[]";

		StringBuilder sb = new StringBuilder();

		// iterate from head to node before tail
		Node<MyType> cur = head;
		while (cur.next != null) {
			sb.append(cur.data + " -> ");
			cur = cur.next;
		}
		// append tail
		sb.append(cur.data);
		return "[" + sb + "]";
	}

	/**
	 * Get the tail for tests
	 * 
	 * @return the tail node
	 */
	protected Node<MyType> getTail() {
		return tail;
	}

	/**
	 * Get the head for tests
	 * 
	 * @return the head node
	 */
	protected Node<MyType> getHead() {
		return head;
	}
}
