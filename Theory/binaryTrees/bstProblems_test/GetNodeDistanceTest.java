package bstProblems_test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import binaryTree_src.BinarySearchTree;
import bstProblems_src.GetNodeDistance;

public class GetNodeDistanceTest {
	BinarySearchTree t1;
	BinarySearchTree t2;

	@Before
	public void setUp() throws Exception {
		t1 = new BinarySearchTree(new int[] { 10, 15, 22, 7, 8 });
		t2 = new BinarySearchTree(new int[] { 10, 8, 12, 6, 9, 11, 13, 14, 15, 16, 17, 7 });
	}

	@Test
	public void test() {
		Assert.assertEquals(2, GetNodeDistance.getNodeDistance(t1, 10, 22));

		Assert.assertEquals(8, GetNodeDistance.getNodeDistance(t2, 7, 16));
		Assert.assertEquals(4, GetNodeDistance.getNodeDistance(t2, 11, 15));
		Assert.assertEquals(3, GetNodeDistance.getNodeDistance(t2, 7, 9));
	}

}
