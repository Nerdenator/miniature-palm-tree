package stacks_test;

import org.junit.Assert;
import org.junit.Test;

import stacks_src.InterfaceStack;
import stacks_src.StackArray;

public class StackArrayTest {
	InterfaceStack<Integer> stack = new StackArray<>(4);

	@Test
	public void testIsEmpty() throws Exception {
		Assert.assertTrue(stack.isEmpty());
		stack.push(10);
		Assert.assertFalse(stack.isEmpty());
	}

	@Test
	public void testSize() throws Exception {
		Assert.assertEquals(0, stack.size());
		stack.push(10);
		Assert.assertEquals(1, stack.size());
		stack.push(10);
		Assert.assertEquals(2, stack.size());
		stack.push(10);
		Assert.assertEquals(3, stack.size());
		stack.push(10);
		Assert.assertEquals(4, stack.size());
	}

	@Test
	public void testPushSuccess() throws Exception {
		Assert.assertEquals("[]", stack.toString());

		stack.push(10);
		Assert.assertEquals("[10]", stack.toString());
		stack.push(20);
		stack.push(40);
		stack.push(30);
		Assert.assertEquals("[10, 20, 40, 30]", stack.toString());
	}

	@Test
	public void testPopSuccess() throws Exception {
		Assert.assertEquals("[]", stack.toString());

		stack.push(10);
		stack.push(20);
		stack.push(40);
		stack.push(30);

		///
		Assert.assertEquals(new Integer(30), stack.pop());
		Assert.assertEquals(new Integer(40), stack.pop());
		Assert.assertEquals(new Integer(20), stack.pop());
		Assert.assertEquals(new Integer(10), stack.pop());
	}

	@Test
	public void testPopEmpty() {
		Assert.assertEquals("[]", stack.toString());

		///
		Assert.assertNull(stack.pop());
	}

	@Test
	public void testPeekSuccess() throws Exception {
		Assert.assertEquals("[]", stack.toString());

		stack.push(10);
		stack.push(20);
		stack.push(40);
		stack.push(30);

		///
		Assert.assertEquals(new Integer(30), stack.peek());
		Assert.assertEquals(new Integer(30), stack.peek());
	}

	@Test
	public void testPeekEmpty() {
		Assert.assertEquals("[]", stack.toString());

		///
		Assert.assertNull(stack.peek());
	}
}
