package quickSort;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class QuickSortTest {
	int[] empty;
	int[] empty_expected;
	int[] one;
	int[] one_expected;
	int[] sorted;
	int[] sorted_expected;
	int[] reverse;
	int[] reverse_expected;
	int[] any;
	int[] any_expected;

	@Before
	public void setUp() {
		empty = new int[] {};
		empty_expected = new int[] {};
		one = new int[] { 1 };
		one_expected = new int[] { 1 };
		sorted = new int[] { 1, 2, 3 };
		sorted_expected = new int[] { 1, 2, 3 };
		reverse = new int[] { 3, 2, 1 };
		reverse_expected = new int[] { 1, 2, 3 };
		any = new int[] { 3, 7, 8, 2, 5, 1, 2, 9, 5, 4 };
		any_expected = new int[] { 1, 2, 2, 3, 4, 5, 5, 7, 8, 9 };
	}

	@Test
	public void testQuicksortNaive() {
		QuickSort.sortNaive(empty);
		Assert.assertArrayEquals(empty_expected, empty);

		QuickSort.sortNaive(one);
		Assert.assertArrayEquals(one_expected, one);

		QuickSort.sortNaive(sorted);
		Assert.assertArrayEquals(sorted_expected, sorted);

		QuickSort.sortNaive(reverse);
		Assert.assertArrayEquals(reverse_expected, reverse);

		QuickSort.sortNaive(any);
		Assert.assertArrayEquals(any_expected, any);
	}

	@Test
	public void testQuickSort() {
		QuickSort.sort(empty);
		Assert.assertArrayEquals(empty_expected, empty);

		QuickSort.sort(one);
		Assert.assertArrayEquals(one_expected, one);

		QuickSort.sort(sorted);
		Assert.assertArrayEquals(sorted_expected, sorted);

		QuickSort.sort(reverse);
		Assert.assertArrayEquals(reverse_expected, reverse);

		QuickSort.sort(any);
		Assert.assertArrayEquals(any_expected, any);
	}
}
