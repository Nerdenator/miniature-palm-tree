package linkedListsProblems_test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import linkedListsProblems_src.ReverseSinglyLinkedList;
import linkedLists_src.SinglyLinkedList;

public class ReverseSinglyLinkedListTest {

	SinglyLinkedList<Integer> list;
	SinglyLinkedList<Integer> empty;
	SinglyLinkedList<Integer> one;
	SinglyLinkedList<Integer> two;
	SinglyLinkedList<Integer> three;

	@Before
	public void setUp() {
		empty = new SinglyLinkedList<Integer>();
		one = new SinglyLinkedList<Integer>(10);
		two = new SinglyLinkedList<Integer>(new Integer[] { 1, 2 });
		three = new SinglyLinkedList<Integer>(new Integer[] { 1, 3, 2 });
		list = new SinglyLinkedList<Integer>(new Integer[] { 1, 2, 4, 7, 2, 3 });
	}

	@Test
	public void testSetup() {
		assertEquals("[]", empty.toString());
		assertEquals("[10]", one.toString());
		assertEquals("[2 -> 1]", two.toString());
		assertEquals("[2 -> 3 -> 1]", three.toString());
		assertEquals("[3 -> 2 -> 7 -> 4 -> 2 -> 1]", list.toString());
	}

	@Test
	public void testReverseUsingStack() {
		ReverseSinglyLinkedList.reverseUsingStack(empty);
		assertEquals("[]", empty.toString());

		ReverseSinglyLinkedList.reverseUsingStack(one);
		assertEquals("[10]", one.toString());

		ReverseSinglyLinkedList.reverseUsingStack(two);
		assertEquals("[1 -> 2]", two.toString());

		ReverseSinglyLinkedList.reverseUsingStack(three);
		assertEquals("[1 -> 3 -> 2]", three.toString());

		ReverseSinglyLinkedList.reverseUsingStack(list);
		assertEquals("[1 -> 2 -> 4 -> 7 -> 2 -> 3]", list.toString());
	}

	@Test
	public void testReverseUsingQueue() {
		ReverseSinglyLinkedList.reverseUsingQueue(empty);
		assertEquals("[]", empty.toString());

		ReverseSinglyLinkedList.reverseUsingQueue(one);
		assertEquals("[10]", one.toString());

		ReverseSinglyLinkedList.reverseUsingQueue(two);
		assertEquals("[1 -> 2]", two.toString());

		ReverseSinglyLinkedList.reverseUsingQueue(three);
		assertEquals("[1 -> 3 -> 2]", three.toString());

		ReverseSinglyLinkedList.reverseUsingQueue(list);
		assertEquals("[1 -> 2 -> 4 -> 7 -> 2 -> 3]", list.toString());
	}

	@Test
	public void testReverseUsingOtherList() {
		ReverseSinglyLinkedList.reverseUsingOtherList(empty);
		assertEquals("[]", empty.toString());

		ReverseSinglyLinkedList.reverseUsingOtherList(one);
		assertEquals("[10]", one.toString());

		ReverseSinglyLinkedList.reverseUsingOtherList(two);
		assertEquals("[1 -> 2]", two.toString());

		ReverseSinglyLinkedList.reverseUsingOtherList(three);
		assertEquals("[1 -> 3 -> 2]", three.toString());

		ReverseSinglyLinkedList.reverseUsingOtherList(list);
		assertEquals("[1 -> 2 -> 4 -> 7 -> 2 -> 3]", list.toString());
	}

	@Test
	public void testReverseListRepeatedlyMovingHead() {
		ReverseSinglyLinkedList.reverseRepeatedlyMovingHead(empty);
		assertEquals("[]", empty.toString());

		ReverseSinglyLinkedList.reverseRepeatedlyMovingHead(one);
		assertEquals("[10]", one.toString());

		ReverseSinglyLinkedList.reverseRepeatedlyMovingHead(two);
		assertEquals("[1 -> 2]", two.toString());

		ReverseSinglyLinkedList.reverseRepeatedlyMovingHead(three);
		assertEquals("[1 -> 3 -> 2]", three.toString());

		ReverseSinglyLinkedList.reverseRepeatedlyMovingHead(list);
		assertEquals("[1 -> 2 -> 4 -> 7 -> 2 -> 3]", list.toString());
	}

	@Test
	public void testReverseFlippingConnectionsIterative() {
		ReverseSinglyLinkedList.reverseFlippingConnectionsIterative(empty);
		assertEquals("[]", empty.toString());

		ReverseSinglyLinkedList.reverseFlippingConnectionsIterative(one);
		assertEquals("[10]", one.toString());

		ReverseSinglyLinkedList.reverseFlippingConnectionsIterative(two);
		assertEquals("[1 -> 2]", two.toString());

		ReverseSinglyLinkedList.reverseFlippingConnectionsIterative(three);
		assertEquals("[1 -> 3 -> 2]", three.toString());

		ReverseSinglyLinkedList.reverseFlippingConnectionsIterative(list);
		assertEquals("[1 -> 2 -> 4 -> 7 -> 2 -> 3]", list.toString());
	}

	@Test
	public void testReverseFlippingConnectionsRecursive() {
		ReverseSinglyLinkedList.reverseFlippingConnectionsRecursive(empty);
		assertEquals("[]", empty.toString());

		ReverseSinglyLinkedList.reverseFlippingConnectionsRecursive(one);
		assertEquals("[10]", one.toString());

		ReverseSinglyLinkedList.reverseFlippingConnectionsRecursive(two);
		assertEquals("[1 -> 2]", two.toString());

		ReverseSinglyLinkedList.reverseFlippingConnectionsRecursive(three);
		assertEquals("[1 -> 3 -> 2]", three.toString());

		ReverseSinglyLinkedList.reverseFlippingConnectionsRecursive(list);
		assertEquals("[1 -> 2 -> 4 -> 7 -> 2 -> 3]", list.toString());
	}
}
