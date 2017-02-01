package heaps_test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import heaps_src.MaxHeap;

public class MaxHeapTest {
	MaxHeap heap;
	MaxHeap empty;

	@Before
	public void setUp() {
		heap = new MaxHeap(10);
		heap.createHeap_Insert(new int[] { 9, 10, 8, 7, 5, 1, 2, 7, 4 });

		empty = new MaxHeap(5);
	}

	@Test
	public void testCreateHeapInsert() {
		MaxHeap heap = new MaxHeap(10);
		heap.createHeap_Insert(new int[] { 9, 10, 8, 7, 5, 1, 2, 7, 4 });
		assertEquals("[ 10 9 8 7 5 1 2 7 4 ]", heap.toString());
	}

	@Test
	public void testCreateHeapBubbleDown_Iterative() {
		MaxHeap heap = new MaxHeap(10);
		heap.createHeapIterativeBubbleDown(new int[] { 9, 10, 8, 7, 5, 1, 2, 7, 4 });
		assertEquals("[ 10 9 8 7 5 1 2 7 4 ]", heap.toString());
	}

	@Test
	public void testCreateHeapBubbleDown_Recursive() {
		MaxHeap heap = new MaxHeap(10);
		heap.createHeapRecursiveBubbleDown(new int[] { 9, 10, 8, 7, 5, 1, 2, 7, 4 });
		assertEquals("[ 10 9 8 7 5 1 2 7 4 ]", heap.toString());
	}

	@Test
	public void testCreateHeapBubbleUp_Iterative() {
		MaxHeap heap = new MaxHeap(10);
		heap.createHeapIterativeBubbleUp(new int[] { 9, 10, 8, 7, 5, 1, 2, 7, 4 });
		assertEquals("[ 10 9 8 7 5 1 2 7 4 ]", heap.toString());
	}

	@Test
	public void testCreateHeapBubbleUp_Recursive() {
		MaxHeap heap = new MaxHeap(10);
		heap.createHeapRecursiveBubbleUp(new int[] { 9, 10, 8, 7, 5, 1, 2, 7, 4 });
		assertEquals("[ 10 9 8 7 5 1 2 7 4 ]", heap.toString());
	}

	@Test
	public void testBubbleDown_Iterative() {
		int[] A = { 100, 3, 9, 8, 7, 5, 1, 2, 7, 4 };
		heap.bubbleDown_Iterative(1, A);
		assertEquals("[100, 8, 9, 7, 7, 5, 1, 2, 3, 4]", Arrays.toString(A));
	}

	@Test
	public void testBubbleDown_Recursive() {
		int[] A = { 100, 3, 9, 8, 7, 5, 1, 2, 7, 4 };
		heap.bubbleDown_Recursive(1, A);
		assertEquals("[100, 8, 9, 7, 7, 5, 1, 2, 3, 4]", Arrays.toString(A));

	}

	@Test
	public void testBubbleUp_Iterative() {
		int[] A = { 100, 9, 8, 7, 5, 1, 2, 7, 10, 4 };
		heap.bubbleUp_Iterative(8, A);
		assertEquals("[100, 10, 8, 9, 5, 1, 2, 7, 7, 4]", Arrays.toString(A));
	}

	//	@Test
	//	public void testBubbleUp_Recursive() {
	//		int[] A = { 100, 9, 8, 7, 5, 1, 2, 7, 10, 4 };
	//		heap.bubbleUp_Recursive(8, A);
	//		assertEquals("[100, 10, 8, 9, 5, 1, 2, 7, 7, 4]", Arrays.toString(A));
	//	}

	@Test
	public void testInsert() {
		MaxHeap heap = new MaxHeap(5);
		assertEquals(5, heap.getCapacity());
		heap.insert(8);
		assertEquals("[ 8 ]", heap.toString());
		heap.insert(20);
		assertEquals("[ 20 8 ]", heap.toString());
		heap.insert(16);
		assertEquals("[ 20 8 16 ]", heap.toString());
		heap.insert(40);
		assertEquals("[ 40 20 16 8 ]", heap.toString());
		heap.insert(6);
		assertEquals("[ 40 20 16 8 6 ]", heap.toString());
		assertEquals(5, heap.getCapacity());
		heap.insert(15);
		assertEquals(7, heap.getCapacity());
		assertEquals("[ 40 20 16 8 6 15 ]", heap.toString());
	}

	@Test
	public void testDelete() {
		assertEquals("[ 10 9 8 7 5 1 2 7 4 ]", heap.toString());
		int max = heap.delete();
		assertEquals(10, max);
		assertEquals("[ 9 7 8 7 5 1 2 4 ]", heap.toString());

		max = empty.delete();
		assertEquals(Integer.MIN_VALUE, max);
		assertEquals("[ ]", empty.toString());

	}

	@Test
	public void testPeekMax() {
		assertEquals("[ 10 9 8 7 5 1 2 7 4 ]", heap.toString());
		int max = heap.peek();
		assertEquals(10, max);
		assertEquals("[ 10 9 8 7 5 1 2 7 4 ]", heap.toString());

		max = empty.peek();
		assertEquals(Integer.MIN_VALUE, max);
		assertEquals("[ ]", empty.toString());
	}

	@Test
	public void testChangePriority() {
		// Out of bounds
		assertFalse(heap.changePriority(-10, 3));
		assertFalse(heap.changePriority(100, 3));

		// No effect
		assertEquals("[ 10 9 8 7 5 1 2 7 4 ]", heap.toString());
		assertTrue(heap.changePriority(8, 3));
		assertEquals("[ 10 9 8 7 5 1 2 7 3 ]", heap.toString());
		assertTrue(heap.changePriority(0, 100));
		assertEquals("[ 100 9 8 7 5 1 2 7 3 ]", heap.toString());

		// Replace with smaller value
		assertTrue(heap.changePriority(2, 0));
		assertEquals("[ 100 9 2 7 5 1 0 7 3 ]", heap.toString());

		// Replace with larger value
		assertTrue(heap.changePriority(4, 10));
		assertEquals("[ 100 10 2 7 9 1 0 7 3 ]", heap.toString());
	}
}