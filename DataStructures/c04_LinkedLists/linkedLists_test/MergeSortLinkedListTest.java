package linkedLists_test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import linkedLists_src.MergeSortLinkedList;

public class MergeSortLinkedListTest {

	MergeSortLinkedList list;
	MergeSortLinkedList empty;
	MergeSortLinkedList one;
	MergeSortLinkedList two;
	MergeSortLinkedList three;

	@Before
	public void setUp() {
		empty = new MergeSortLinkedList();
		one = new MergeSortLinkedList(10);
		two = new MergeSortLinkedList(new Integer[] { 1, 2 });
		three = new MergeSortLinkedList(new Integer[] { 1, 3, 2 });
		list = new MergeSortLinkedList(new Integer[] { 1, 2, 4, 7, 2, 3 });
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
	public void testMergeSortRecursiveMerge() {
		empty.sortRecursiveMerge();
		assertEquals("[]", empty.toString());
		one.sortRecursiveMerge();
		assertEquals("[10]", one.toString());
		two.sortRecursiveMerge();
		assertEquals("[1 -> 2]", two.toString());
		three.sortRecursiveMerge();
		assertEquals("[1 -> 2 -> 3]", three.toString());
		list.sortRecursiveMerge();
		assertEquals("[1 -> 2 -> 2 -> 3 -> 4 -> 7]", list.toString());
	}

	@Test
	public void testMergeSortIterativeMerge() {
		empty.sortIterativeMerge();
		assertEquals("[]", empty.toString());
		one.sortIterativeMerge();
		assertEquals("[10]", one.toString());
		two.sortIterativeMerge();
		assertEquals("[1 -> 2]", two.toString());
		three.sortIterativeMerge();
		assertEquals("[1 -> 2 -> 3]", three.toString());
		list.sortIterativeMerge();
		assertEquals("[1 -> 2 -> 2 -> 3 -> 4 -> 7]", list.toString());
	}

}
