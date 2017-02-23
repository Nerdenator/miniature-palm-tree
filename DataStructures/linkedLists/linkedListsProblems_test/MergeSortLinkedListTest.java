package linkedListsProblems_test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import linkedListsProblems_src.MergeSortLinkedList;
import linkedLists_src.SinglyLinkedList;

public class MergeSortLinkedListTest {

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
	public void testMergeSortRecursiveMerge() {
		boolean recursiveMerge = true;
		MergeSortLinkedList.mergeSort(empty, recursiveMerge);
		assertEquals("[]", empty.toString());

		MergeSortLinkedList.mergeSort(one, recursiveMerge);
		assertEquals("[10]", one.toString());

		MergeSortLinkedList.mergeSort(two, recursiveMerge);
		assertEquals("[1 -> 2]", two.toString());

		MergeSortLinkedList.mergeSort(three, recursiveMerge);
		assertEquals("[1 -> 2 -> 3]", three.toString());

		MergeSortLinkedList.mergeSort(list, recursiveMerge);
		assertEquals("[1 -> 2 -> 2 -> 3 -> 4 -> 7]", list.toString());
	}

	@Test
	public void testMergeSortIterativeMerge() {
		boolean recursiveMerge = false;
		MergeSortLinkedList.mergeSort(empty, recursiveMerge);
		assertEquals("[]", empty.toString());

		MergeSortLinkedList.mergeSort(one, recursiveMerge);
		assertEquals("[10]", one.toString());

		MergeSortLinkedList.mergeSort(two, recursiveMerge);
		assertEquals("[1 -> 2]", two.toString());

		MergeSortLinkedList.mergeSort(three, recursiveMerge);
		assertEquals("[1 -> 2 -> 3]", three.toString());

		MergeSortLinkedList.mergeSort(list, recursiveMerge);
		assertEquals("[1 -> 2 -> 2 -> 3 -> 4 -> 7]", list.toString());
	}

}
