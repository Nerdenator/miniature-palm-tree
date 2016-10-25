package c04_LinkedLists;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import c04_LinkedLists.ReverseSinglyLinkedList;

public class ReverseSinglyLinkedListTest {

	ReverseSinglyLinkedList<Integer> list;
	ReverseSinglyLinkedList<Integer> empty;
	ReverseSinglyLinkedList<Integer> one;
	ReverseSinglyLinkedList<Integer> two;
	ReverseSinglyLinkedList<Integer> three;

	@Before
	public void setUp() {
		empty = new ReverseSinglyLinkedList<Integer>();
		one = new ReverseSinglyLinkedList<Integer>(10);
		two = new ReverseSinglyLinkedList<Integer>(new Integer[] { 1, 2 });
		three = new ReverseSinglyLinkedList<Integer>(new Integer[] { 1, 3, 2 });
		list = new ReverseSinglyLinkedList<Integer>(new Integer[] { 1, 2, 4, 7, 2, 3 });
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
	public void testReverseUsingStack() throws Exception {
		empty.reverseUsingStack();
		assertEquals("[]", empty.toString());
		one.reverseUsingStack();
		assertEquals("[10]", one.toString());
		two.reverseUsingStack();
		assertEquals("[1 -> 2]", two.toString());
		three.reverseUsingStack();
		assertEquals("[1 -> 3 -> 2]", three.toString());
		list.reverseUsingStack();
		assertEquals("[1 -> 2 -> 4 -> 7 -> 2 -> 3]", list.toString());
	}

	@Test
	public void testReverseUsingQueue() throws Exception {
		empty.reverseUsingQueue();
		assertEquals("[]", empty.toString());
		one.reverseUsingQueue();
		assertEquals("[10]", one.toString());
		two.reverseUsingQueue();
		assertEquals("[1 -> 2]", two.toString());
		three.reverseUsingQueue();
		assertEquals("[1 -> 3 -> 2]", three.toString());
		list.reverseUsingQueue();
		assertEquals("[1 -> 2 -> 4 -> 7 -> 2 -> 3]", list.toString());
	}

	@Test
	public void testReverseUsingOtherList() throws Exception {
		empty.reverseUsingOtherList();
		assertEquals("[]", empty.toString());
		one.reverseUsingOtherList();
		assertEquals("[10]", one.toString());
		two.reverseUsingOtherList();
		assertEquals("[1 -> 2]", two.toString());
		three.reverseUsingOtherList();
		assertEquals("[1 -> 3 -> 2]", three.toString());
		list.reverseUsingOtherList();
		assertEquals("[1 -> 2 -> 4 -> 7 -> 2 -> 3]", list.toString());
	}

	@Test
	public void testReverseListMoveHeadToEndRepeatedly() throws Exception {
		empty.reverseListMoveHeadToEndRepeatedly();
		assertEquals("[]", empty.toString());
		one.reverseListMoveHeadToEndRepeatedly();
		assertEquals("[10]", one.toString());
		two.reverseListMoveHeadToEndRepeatedly();
		assertEquals("[1 -> 2]", two.toString());
		three.reverseListMoveHeadToEndRepeatedly();
		assertEquals("[1 -> 3 -> 2]", three.toString());
		list.reverseListMoveHeadToEndRepeatedly();
		assertEquals("[1 -> 2 -> 4 -> 7 -> 2 -> 3]", list.toString());
	}

	@Test
	public void testReverseListSwapConnectionsIterative() throws Exception {
		empty.reverseListSwapConnectionsIterative();
		assertEquals("[]", empty.toString());
		one.reverseListSwapConnectionsIterative();
		assertEquals("[10]", one.toString());
		two.reverseListSwapConnectionsIterative();
		assertEquals("[1 -> 2]", two.toString());
		three.reverseListSwapConnectionsIterative();
		assertEquals("[1 -> 3 -> 2]", three.toString());
		list.reverseListSwapConnectionsIterative();
		assertEquals("[1 -> 2 -> 4 -> 7 -> 2 -> 3]", list.toString());
	}

	@Test
	public void testReverseListSwapConnectionsRecursive() throws Exception {
		empty.reverseListSwapConnectionsRecursive();
		assertEquals("[]", empty.toString());
		one.reverseListSwapConnectionsRecursive();
		assertEquals("[10]", one.toString());
		two.reverseListSwapConnectionsRecursive();
		assertEquals("[1 -> 2]", two.toString());
		three.reverseListSwapConnectionsRecursive();
		assertEquals("[1 -> 3 -> 2]", three.toString());
		list.reverseListSwapConnectionsRecursive();
		assertEquals("[1 -> 2 -> 4 -> 7 -> 2 -> 3]", list.toString());
	}
}
