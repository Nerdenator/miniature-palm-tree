package binarySearch;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BinarySearchTest {
	int[] empty;
	int[] one;
	int[] arr;
	
	@Before
	public void setUp(){
		empty = new int[]{};
		one = new int[]{15};
		arr = new int[]{1,2,10,11,12,13,14,14,15};
	}
	@Test
	public void testBinarySearch_Empty() {
		int expected = -1;
		Assert.assertEquals(expected, BinarySearch.binarySearchRecursive(empty, 10));
		Assert.assertEquals(expected, BinarySearch.binarySearchIterative(empty, 10));
	}

	@Test
	public void testBinarySearch_OneGood() {
		int expected = 0;
		Assert.assertEquals(expected, BinarySearch.binarySearchRecursive(one, 15));
		Assert.assertEquals(expected, BinarySearch.binarySearchIterative(one, 15));
	}

	@Test
	public void testBinarySearch_OneBad() {
		int expected = -1;
		Assert.assertEquals(expected, BinarySearch.binarySearchRecursive(one, 1));
		Assert.assertEquals(expected, BinarySearch.binarySearchIterative(one, 1));
	}
	
	@Test
	public void testBinarySearch_First() {
		int expected = 0;
		Assert.assertEquals(expected, BinarySearch.binarySearchRecursive(arr, 1));
		Assert.assertEquals(expected, BinarySearch.binarySearchIterative(arr, 1));
	}
	
	@Test
	public void testBinarySearch_Last() {
		int expected = arr.length-1;
		Assert.assertEquals(expected, BinarySearch.binarySearchRecursive(arr, 15));
		Assert.assertEquals(expected, BinarySearch.binarySearchIterative(arr, 15));
	}
	
	@Test
	public void testBinarySearch_Middle() {
		int expected = 4;
		Assert.assertEquals(expected, BinarySearch.binarySearchRecursive(arr, 12));
		Assert.assertEquals(expected, BinarySearch.binarySearchIterative(arr, 12));
	}
	
	@Test
	public void testBinarySearch_Half1() {
		int expected = 2;
		Assert.assertEquals(expected, BinarySearch.binarySearchRecursive(arr, 10));
		Assert.assertEquals(expected, BinarySearch.binarySearchIterative(arr, 10));
	}
	
	@Test
	public void testBinarySearch_Half2() {
		int expected = 5;
		Assert.assertEquals(expected, BinarySearch.binarySearchRecursive(arr, 13));
		Assert.assertEquals(expected, BinarySearch.binarySearchIterative(arr, 13));
	}
}
