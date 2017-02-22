package queues_test;

import org.junit.Assert;
import org.junit.Test;

import queues_src.InterfaceQueue;
import queues_src.QueueList;

public class QueueListTest {
	InterfaceQueue<Integer> queue = new QueueList<Integer>();

	@Test
	public void testIsEmpty() throws Exception {
		Assert.assertTrue(queue.isEmpty());
		queue.add(10);
		Assert.assertFalse(queue.isEmpty());
	}

	@Test
	public void testSize() throws Exception {
		Assert.assertEquals(0, queue.size());
		queue.add(10);
		Assert.assertEquals(1, queue.size());
		queue.add(10);
		Assert.assertEquals(2, queue.size());
		queue.add(10);
		Assert.assertEquals(3, queue.size());
		queue.add(10);
		Assert.assertEquals(4, queue.size());
	}

	@Test
	public void testAddSuccess() throws Exception {
		Assert.assertEquals("[]", queue.toString());

		queue.add(10);
		Assert.assertEquals("[10]", queue.toString());
		queue.add(20);
		queue.add(40);
		queue.add(30);
		Assert.assertEquals("[10, 20, 40, 30]", queue.toString());
	}

	@Test
	public void testRemoveSuccess() throws Exception {
		Assert.assertEquals("[]", queue.toString());

		queue.add(10);
		queue.add(20);
		queue.add(40);
		queue.add(30);

		///
		Assert.assertEquals(new Integer(10), queue.remove());
		Assert.assertEquals(new Integer(20), queue.remove());
		Assert.assertEquals(new Integer(40), queue.remove());
		Assert.assertEquals(new Integer(30), queue.remove());
	}

	@Test
	public void testRemoveEmpty() {
		Assert.assertEquals("[]", queue.toString());

		///
		Assert.assertNull(queue.remove());
	}

	@Test
	public void testPeekSuccess() throws Exception {
		Assert.assertEquals("[]", queue.toString());

		queue.add(10);
		queue.add(20);
		queue.add(40);
		queue.add(30);

		///
		Assert.assertEquals(new Integer(10), queue.peek());
		Assert.assertEquals(new Integer(10), queue.peek());
	}

	@Test
	public void testPeekEmpty() {
		Assert.assertEquals("[]", queue.toString());

		///
		Assert.assertNull(queue.peek());
	}
}
