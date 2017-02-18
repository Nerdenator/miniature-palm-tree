package binaryTree_test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import binaryTree_src.BinaryTreeInsertRandomDirection;
import binaryTree_util.Node;

public class BinaryTreeInsertRandomDirectionTest {
	BinaryTreeInsertRandomDirection<Integer> t;
	int[] arr = new int[] { 10, 15, 5, 4, 12, 8, 17, 1, 3, 20, 22, 16 };

	@Before
	public void setUp() throws Exception {
		t = new BinaryTreeInsertRandomDirection<>();
		t.insert(10);
		t.insertRight(t.root, 15);
		t.insertLeft(t.root, 5);
		t.insertRight(t.root.left, 4);
		t.insertRight(t.root.right, 12);
		t.insertRight(t.root.right.right, 24);
		t.insertLeft(t.root.right.right, 17);
		t.insertLeft(t.root.left.right, 35);
	}

	@Test
	public void testInsert() {
		Assert.assertEquals((Integer) 10, t.root.data);
		Assert.assertEquals((Integer) 5, t.root.left.data);
		Assert.assertEquals((Integer) 4, t.root.left.right.data);
		Assert.assertEquals((Integer) 35, t.root.left.right.left.data);

		Assert.assertEquals((Integer) 15, t.root.right.data);
		Assert.assertEquals((Integer) 12, t.root.right.right.data);
		Assert.assertEquals((Integer) 24, t.root.right.right.right.data);
		Assert.assertEquals((Integer) 17, t.root.right.right.left.data);
	}

	@Test
	public void testDeleteNoneAndLeavesAndRoot() {
		Node<Integer> del;
		// non-existing node
		del = t.delete(100);
		Assert.assertNull(del);

		// leaves
		Assert.assertEquals((Integer) 35, t.root.left.right.left.data);
		del = t.delete(35);
		Assert.assertEquals((Integer) 35, del.data);
		Assert.assertNull(t.root.left.right.left);

		Assert.assertEquals((Integer) 24, t.root.right.right.right.data);
		del = t.delete(24);
		Assert.assertEquals((Integer) 24, del.data);
		Assert.assertNull(t.root.right.right.right);

		// root
		Assert.assertEquals((Integer) 10, t.root.data);
		t.delete(10);
		Assert.assertEquals((Integer) 5, t.root.data);
		Assert.assertEquals((Integer) 15, t.root.right.right.data);
	}

	@Test
	public void testDeleteChildren() {
		Node<Integer> del;

		// a node with 2 children
		Assert.assertEquals((Integer) 12, t.root.right.right.data);
		del = t.delete(12);
		t.displayTree();
		Assert.assertEquals((Integer) 12, del.data);
		Assert.assertEquals((Integer) 24, t.root.right.right.data);
		Assert.assertEquals((Integer) 17, t.root.right.left.data);

		// a node with one child
		Assert.assertEquals((Integer) 4, t.root.left.right.data);
		del = t.delete(4);
		Assert.assertEquals((Integer) 4, del.data);
		Assert.assertEquals((Integer) 35, t.root.left.right.data);
		Assert.assertNull(t.root.left.right.left);
	}

	@Test
	public void testFind() {
		Assert.assertNull(t.find(190));
		Assert.assertEquals((Integer) 12, t.find(12).data);
	}
}
