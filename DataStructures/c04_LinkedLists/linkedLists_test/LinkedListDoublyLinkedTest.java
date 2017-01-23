package linkedLists_test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import linkedLists_src.LinkedListDoublyLinked;
import linkedLists_src.Node;

public class LinkedListDoublyLinkedTest {

	LinkedListDoublyLinked<Integer> list;
	LinkedListDoublyLinked<Integer> empty;
	LinkedListDoublyLinked<Integer> one;

	@Before
	public void setUp() {
		empty = new LinkedListDoublyLinked<>();
		one = new LinkedListDoublyLinked<>(10);
		list = new LinkedListDoublyLinked<>(new Integer[] { 1, 2, 4, 7, 2, 3 });
	}

	@Test
	public void testSetup() {
		assertEquals("[]", empty.toString());
		assertNull(empty.getTail());
		assertEquals("[10]", one.toString());
		assertEquals((Integer) 10, one.getTail().data);
		assertEquals("[3 -> 2 -> 7 -> 4 -> 2 -> 1]", list.toString());
		assertEquals(list.getHead(), list.getHead().next.prev);
		assertEquals(list.getTail(), list.getTail().prev.next);
		assertEquals((Integer) 1, list.getTail().data);
	}

	@Test
	public void testAddFirst() {
		empty.addFirst(10);
		assertEquals("[10]", empty.toString());
		assertEquals((Integer) 10, empty.getTail().data);

		one.addFirst(20);
		assertEquals("[20 -> 10]", one.toString());
		assertEquals((Integer) 10, one.getTail().data);

		list.addFirst(10);
		assertEquals("[10 -> 3 -> 2 -> 7 -> 4 -> 2 -> 1]", list.toString());
		assertEquals((Integer) 1, list.getTail().data);
	}

	@Test
	public void testAddLast() {
		empty.addLast(10);
		assertEquals("[10]", empty.toString());
		assertEquals((Integer) 10, empty.getTail().data);

		one.addLast(20);
		assertEquals("[10 -> 20]", one.toString());
		assertEquals((Integer) 20, one.getTail().data);
		assertEquals((Integer) 10, one.getTail().prev.data);

		list.addLast(10);
		assertEquals("[3 -> 2 -> 7 -> 4 -> 2 -> 1 -> 10]", list.toString());
		assertEquals((Integer) 10, list.getTail().data);
		assertEquals((Integer) 1, list.getTail().prev.data);
	}

	@Test
	public void testRemoveFirst() {
		Node<Integer> rem = null;

		rem = empty.removeFirst();
		assertEquals("[]", empty.toString());
		assertNull(rem);
		assertNull(empty.getTail());

		rem = one.removeFirst();
		assertEquals("[]", one.toString());
		assertEquals((Integer) 10, rem.data);
		assertNull(one.getTail());

		rem = list.removeFirst();
		assertEquals("[2 -> 7 -> 4 -> 2 -> 1]", list.toString());
		assertEquals((Integer) 3, rem.data);
		assertNull(list.getHead().prev);
		assertEquals((Integer) 1, list.getTail().data);
	}

	@Test
	public void testRemoveLast() {
		Node<Integer> rem = null;

		rem = empty.removeLast();
		assertEquals("[]", empty.toString());
		assertNull(rem);
		assertNull(empty.getTail());

		rem = one.removeLast();
		assertEquals("[]", one.toString());
		assertEquals((Integer) 10, rem.data);
		assertNull(one.getTail());

		rem = list.removeLast();
		assertEquals("[3 -> 2 -> 7 -> 4 -> 2]", list.toString());
		assertEquals((Integer) 1, rem.data);
		assertEquals((Integer) 2, list.getTail().data);
		assertNull(list.getTail().next);
	}

	@Test
	public void testFindValue() {
		Node<Integer> found = null;

		found = empty.find(10);
		assertEquals("[]", empty.toString());
		assertNull(found);
		assertNull(empty.getHead());
		assertNull(empty.getTail());

		found = one.find(20);
		assertEquals("[10]", one.toString());
		assertNull(found);
		assertEquals((Integer) 10, one.getTail().data);
		assertEquals((Integer) 10, one.getHead().data);

		found = one.find(10);
		assertEquals("[10]", one.toString());
		assertEquals((Integer) 10, found.data);
		assertEquals((Integer) 10, one.getTail().data);
		assertEquals((Integer) 10, one.getHead().data);

		found = list.find(10);
		assertEquals("[3 -> 2 -> 7 -> 4 -> 2 -> 1]", list.toString());
		assertNull(found);
		assertEquals((Integer) 1, list.getTail().data);
		assertEquals((Integer) 3, list.getHead().data);

		found = list.find(3);
		assertEquals("[3 -> 2 -> 7 -> 4 -> 2 -> 1]", list.toString());
		assertEquals((Integer) 3, found.data);
		assertEquals((Integer) 1, list.getTail().data);
		assertEquals((Integer) 3, list.getHead().data);

		found = list.find(7);
		assertEquals("[3 -> 2 -> 7 -> 4 -> 2 -> 1]", list.toString());
		assertEquals((Integer) 7, found.data);
		assertEquals((Integer) 1, list.getTail().data);
		assertEquals((Integer) 3, list.getHead().data);

		found = list.find(1);
		assertEquals("[3 -> 2 -> 7 -> 4 -> 2 -> 1]", list.toString());
		assertEquals((Integer) 1, found.data);
		assertEquals((Integer) 1, list.getTail().data);
		assertEquals((Integer) 3, list.getHead().data);
	}

	@Test
	public void testDeleteValue() {
		Node<Integer> rem = null;

		rem = empty.delete(10);
		assertEquals("[]", empty.toString());
		assertNull(rem);

		rem = one.delete(20);
		assertEquals("[10]", one.toString());
		assertNull(rem);

		rem = one.delete(10);
		assertEquals("[]", one.toString());
		assertEquals((Integer) 10, rem.data);
		assertNull(one.getHead());
		assertNull(one.getTail());

		rem = list.delete(20);
		assertEquals("[3 -> 2 -> 7 -> 4 -> 2 -> 1]", list.toString());
		assertNull(rem);

		rem = list.delete(4);
		assertEquals("[3 -> 2 -> 7 -> 2 -> 1]", list.toString());
		assertEquals((Integer) 4, rem.data);

		rem = list.delete(1);
		assertEquals("[3 -> 2 -> 7 -> 2]", list.toString());
		assertEquals((Integer) 1, rem.data);
		assertEquals((Integer) 2, list.getTail().data);
		assertNull(list.getTail().next);

		rem = list.delete(2);
		assertEquals("[3 -> 7 -> 2]", list.toString());
		assertEquals((Integer) 2, rem.data);

		rem = list.delete(2);
		assertEquals("[3 -> 7]", list.toString());
		assertEquals((Integer) 2, rem.data);
		assertEquals((Integer) 7, list.getTail().data);
		assertNull(list.getTail().next);

		rem = list.delete(3);
		assertEquals("[7]", list.toString());
		assertEquals((Integer) 3, rem.data);
		assertEquals((Integer) 7, list.getTail().data);
		assertEquals((Integer) 7, list.getHead().data);
		assertNull(list.getHead().prev);

		rem = list.delete(7);
		assertEquals("[]", list.toString());
		assertEquals((Integer) 7, rem.data);
		assertNull(list.getHead());
		assertNull(list.getTail());
	}
}