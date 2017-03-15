package heaps_test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import heaps_src.MaxHeap;

public class MaxHeapTest {

	@Test
	public void testCreateHeapInsertIter() {
		int[] a = new int[] { 9, 10, 8, 7, 5, 1, 2, 7, 4 };
		int[] h = new int[] { 10, 9, 8, 7, 5, 1, 2, 7, 4 };

		MaxHeap heap = new MaxHeap(a.length);
		heap.createHeapUsingInsert(a, 'i');

		assertArrayEquals(h, heap.getHeapArray());
	}

	@Test
	public void testCreateHeapInsertRec() {
		int[] a = new int[] { 9, 10, 8, 7, 5, 1, 2, 7, 4 };
		int[] h = new int[] { 10, 9, 8, 7, 5, 1, 2, 7, 4 };

		MaxHeap heap = new MaxHeap(a.length);
		heap.createHeapUsingInsert(a, 'r');

		assertArrayEquals(h, heap.getHeapArray());
	}

	@Test
	public void testHeapUpIterative() {
		int[] a = new int[] { 100, 9, 8, 7, 5, 1, 2, 7, 10, 4 };
		int[] h = new int[] { 100, 10, 8, 9, 5, 1, 2, 7, 7, 4 };

		MaxHeap heap = new MaxHeap(a.length);
		heap.setArray(a);

		heap.heapUp(8, 'i');
		assertArrayEquals(h, heap.getHeapArray());
	}

	@Test
	public void testHeapUpRecursive() {
		int[] a = new int[] { 100, 9, 8, 7, 5, 1, 2, 7, 10, 4 };
		int[] h = new int[] { 100, 10, 8, 9, 5, 1, 2, 7, 7, 4 };

		MaxHeap heap = new MaxHeap(a.length);
		heap.setArray(a);

		heap.heapUp(8, 'r');
		assertArrayEquals(h, heap.getHeapArray());
	}

	@Test
	public void testCreateHeapIterative_HeapUp_Iterative() {
		int[] a1 = new int[] { 9, 10, 8, 7, 5, 1, 2, 7, 4 };
		int[] h1 = new int[] { 10, 9, 8, 7, 5, 1, 2, 7, 4 };

		MaxHeap heap = new MaxHeap(a1.length);
		heap.createHeapIterative_HeapUp(a1, 'i');

		assertArrayEquals(h1, heap.getHeapArray());

		int[] a2 = new int[] { 100, 9, 8, 7, 5, 1, 2, 7, 10, 4 };
		int[] h2 = new int[] { 100, 10, 8, 9, 5, 1, 2, 7, 7, 4 };

		MaxHeap heap2 = new MaxHeap(a2.length);
		heap2.createHeapIterative_HeapUp(a2, 'i');

		assertArrayEquals(h2, heap2.getHeapArray());
	}

	@Test
	public void testCreateHeapIterative_HeapUp_Recursive() {
		int[] a1 = new int[] { 9, 10, 8, 7, 5, 1, 2, 7, 4 };
		int[] h1 = new int[] { 10, 9, 8, 7, 5, 1, 2, 7, 4 };

		MaxHeap heap = new MaxHeap(a1.length);
		heap.createHeapIterative_HeapUp(a1, 'r');

		assertArrayEquals(h1, heap.getHeapArray());

		int[] a2 = new int[] { 100, 9, 8, 7, 5, 1, 2, 7, 10, 4 };
		int[] h2 = new int[] { 100, 10, 8, 9, 5, 1, 2, 7, 7, 4 };

		MaxHeap heap2 = new MaxHeap(a2.length);
		heap2.createHeapIterative_HeapUp(a2, 'r');

		assertArrayEquals(h2, heap2.getHeapArray());
	}

	@Test
	public void testCreateHeapRecursive_HeapUp_Iterative() {
		int[] a1 = new int[] { 9, 10, 8, 7, 5, 1, 2, 7, 4 };
		int[] h1 = new int[] { 10, 9, 8, 7, 5, 1, 2, 7, 4 };

		MaxHeap heap = new MaxHeap(a1.length);
		heap.createHeapRecursive_HeapUp(a1, 'i');

		assertArrayEquals(h1, heap.getHeapArray());

		int[] a2 = new int[] { 100, 9, 8, 7, 5, 1, 2, 7, 10, 4 };
		int[] h2 = new int[] { 100, 10, 8, 9, 5, 1, 2, 7, 7, 4 };

		MaxHeap heap2 = new MaxHeap(a2.length);
		heap2.createHeapRecursive_HeapUp(a2, 'i');

		assertArrayEquals(h2, heap2.getHeapArray());
	}

	@Test
	public void testCreateHeapRecursive_HeapUp_Recursive() {
		int[] a1 = new int[] { 9, 10, 8, 7, 5, 1, 2, 7, 4 };
		int[] h1 = new int[] { 10, 9, 8, 7, 5, 1, 2, 7, 4 };

		MaxHeap heap = new MaxHeap(a1.length);
		heap.createHeapRecursive_HeapUp(a1, 'r');

		assertArrayEquals(h1, heap.getHeapArray());

		int[] a2 = new int[] { 100, 9, 8, 7, 5, 1, 2, 7, 10, 4 };
		int[] h2 = new int[] { 100, 10, 8, 9, 5, 1, 2, 7, 7, 4 };

		MaxHeap heap2 = new MaxHeap(a2.length);
		heap2.createHeapRecursive_HeapUp(a2, 'r');

		assertArrayEquals(h2, heap2.getHeapArray());
	}

	@Test
	public void testHeapDownIterative() {
		int[] a = new int[] { 100, 3, 9, 8, 7, 5, 1, 2, 7, 4 };
		int[] h = new int[] { 100, 8, 9, 7, 7, 5, 1, 2, 3, 4 };

		MaxHeap heap = new MaxHeap(a.length);
		heap.setArray(a);

		heap.heapDown(1, 'i');
		assertArrayEquals(h, heap.getHeapArray());
	}

	@Test
	public void testHeapDownRecursive() {
		int[] a = new int[] { 100, 3, 9, 8, 7, 5, 1, 2, 7, 4 };
		int[] h = new int[] { 100, 8, 9, 7, 7, 5, 1, 2, 3, 4 };

		MaxHeap heap = new MaxHeap(a.length);
		heap.setArray(a);

		heap.heapDown(1, 'r');
		assertArrayEquals(h, heap.getHeapArray());
	}

	@Test
	public void testCreateHeapRecursive_HeapDown_Iterative() {
		int[] a = new int[] { 9, 10, 8, 7, 5, 1, 2, 7, 4 };
		int[] h = new int[] { 10, 9, 8, 7, 5, 1, 2, 7, 4 };

		MaxHeap heap = new MaxHeap(a.length);
		heap.createHeapRecursive_HeapDown(a, 'i');

		assertArrayEquals(h, heap.getHeapArray());
	}

	@Test
	public void testCreateHeapRecursive_HeapDown_Recursive() {
		int[] a = new int[] { 9, 10, 8, 7, 5, 1, 2, 7, 4 };
		int[] h = new int[] { 10, 9, 8, 7, 5, 1, 2, 7, 4 };

		MaxHeap heap = new MaxHeap(a.length);
		heap.createHeapRecursive_HeapDown(a, 'r');

		assertArrayEquals(h, heap.getHeapArray());
	}

	@Test
	public void testCreateHeapIterative_HeapDown_Iterative() {
		int[] a = new int[] { 9, 10, 8, 7, 5, 1, 2, 7, 4 };
		int[] h = new int[] { 10, 9, 8, 7, 5, 1, 2, 7, 4 };

		MaxHeap heap = new MaxHeap(a.length);
		heap.createHeapIterative_HeapDown(a, 'i');

		assertArrayEquals(h, heap.getHeapArray());
	}

	@Test
	public void testCreateHeapIterative_HeapDown_Recursive() {
		int[] a = new int[] { 9, 10, 8, 7, 5, 1, 2, 7, 4 };
		int[] h = new int[] { 10, 9, 8, 7, 5, 1, 2, 7, 4 };

		MaxHeap heap = new MaxHeap(a.length);
		heap.createHeapIterative_HeapDown(a, 'r');

		assertArrayEquals(h, heap.getHeapArray());
	}

	@Test
	public void testDelete_Iterative() {
		int[] a = new int[] { 9, 10, 8, 7, 5, 1, 2, 7, 4 };
		int[] h = new int[] { 9, 7, 8, 7, 5, 1, 2, 4 };

		MaxHeap heap = new MaxHeap(a.length);
		heap.createHeapIterative_HeapDown(a, 'i');

		int max = heap.delete('i');
		assertEquals(10, max);
		assertArrayEquals(h, heap.getHeapArray());

		MaxHeap empty = new MaxHeap(10);
		max = empty.delete('i');
		assertEquals(Integer.MIN_VALUE, max);
		assertTrue(empty.isEmpty());
	}

	@Test
	public void testDelete_Recursive() {
		int[] a = new int[] { 9, 10, 8, 7, 5, 1, 2, 7, 4 };
		int[] h = new int[] { 9, 7, 8, 7, 5, 1, 2, 4 };

		MaxHeap heap = new MaxHeap(a.length);
		heap.createHeapIterative_HeapDown(a, 'r');

		int max = heap.delete('r');
		assertEquals(10, max);
		assertArrayEquals(h, heap.getHeapArray());

		MaxHeap empty = new MaxHeap(10);
		max = empty.delete('r');
		assertEquals(Integer.MIN_VALUE, max);
		assertTrue(empty.isEmpty());
	}

	@Test
	public void testPeekMax() {
		int[] a = new int[] { 9, 10, 8, 7, 5, 1, 2, 7, 4 };
		int[] h = new int[] { 10, 9, 8, 7, 5, 1, 2, 7, 4 };

		MaxHeap heap = new MaxHeap(a.length);
		heap.createHeapIterative_HeapDown(a, 'r');

		int max = heap.peek();
		assertEquals(10, max);
		assertArrayEquals(h, heap.getHeapArray());

		MaxHeap empty = new MaxHeap(10);
		max = empty.peek();
		assertEquals(Integer.MIN_VALUE, max);
		assertTrue(empty.isEmpty());
	}

	@Test
	public void testChangePriority_Iterative() {
		int[] a = new int[] { 9, 10, 8, 7, 5, 1, 2, 7, 4 };

		MaxHeap heap = new MaxHeap(a.length);
		heap.createHeapIterative_HeapDown(a, 'i');

		char type = 'i';

		// Out of bounds
		assertFalse(heap.changePriority(-10, 3, type));
		assertFalse(heap.changePriority(100, 3, type));

		// No effect
		assertEquals("[10, 9, 8, 7, 5, 1, 2, 7, 4]", heap.toString());
		assertTrue(heap.changePriority(8, 3, type));
		assertEquals("[10, 9, 8, 7, 5, 1, 2, 7, 3]", heap.toString());
		assertTrue(heap.changePriority(0, 100, type));
		assertEquals("[100, 9, 8, 7, 5, 1, 2, 7, 3]", heap.toString());

		// Replace with smaller value
		assertTrue(heap.changePriority(2, 0, type));
		assertEquals("[100, 9, 2, 7, 5, 1, 0, 7, 3]", heap.toString());

		// Replace with larger value
		assertTrue(heap.changePriority(4, 10, type));
		assertEquals("[100, 10, 2, 7, 9, 1, 0, 7, 3]", heap.toString());
	}

	@Test
	public void testChangePriority_Recursive() {
		int[] a = new int[] { 9, 10, 8, 7, 5, 1, 2, 7, 4 };

		MaxHeap heap = new MaxHeap(a.length);
		heap.createHeapIterative_HeapDown(a, 'i');

		char type = 'r';

		// Out of bounds
		assertFalse(heap.changePriority(-10, 3, type));
		assertFalse(heap.changePriority(100, 3, type));

		// No effect
		assertEquals("[10, 9, 8, 7, 5, 1, 2, 7, 4]", heap.toString());
		assertTrue(heap.changePriority(8, 3, type));
		assertEquals("[10, 9, 8, 7, 5, 1, 2, 7, 3]", heap.toString());
		assertTrue(heap.changePriority(0, 100, type));
		assertEquals("[100, 9, 8, 7, 5, 1, 2, 7, 3]", heap.toString());

		// Replace with smaller value
		assertTrue(heap.changePriority(2, 0, type));
		assertEquals("[100, 9, 2, 7, 5, 1, 0, 7, 3]", heap.toString());

		// Replace with larger value
		assertTrue(heap.changePriority(4, 10, type));
		assertEquals("[100, 10, 2, 7, 9, 1, 0, 7, 3]", heap.toString());
	}
}