package c01_sorting;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BubbleSortTest {
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
	public void testBubbleSortBasic() {
		BubbleSort.sortBasic(empty);
		Assert.assertArrayEquals(empty_expected, empty);

		BubbleSort.sortBasic(one);
		Assert.assertArrayEquals(one_expected, one);

		BubbleSort.sortBasic(sorted);
		Assert.assertArrayEquals(sorted_expected, sorted);

		BubbleSort.sortBasic(reverse);
		Assert.assertArrayEquals(reverse_expected, reverse);

		BubbleSort.sortBasic(any);
		Assert.assertArrayEquals(any_expected, any);
	}

	@Test
	public void testBubbleSortImproved() {
		BubbleSort.sortImproved(empty);
		Assert.assertArrayEquals(empty_expected, empty);

		BubbleSort.sortImproved(one);
		Assert.assertArrayEquals(one_expected, one);

		BubbleSort.sortImproved(sorted);
		Assert.assertArrayEquals(sorted_expected, sorted);

		BubbleSort.sortImproved(reverse);
		Assert.assertArrayEquals(sorted_expected, reverse);

		BubbleSort.sortImproved(any);
		Assert.assertArrayEquals(any_expected, any);
	}

	@Test
	public void testBubbleSortBest() {
		BubbleSort.sortBest(empty);
		Assert.assertArrayEquals(empty_expected, empty);

		BubbleSort.sortBest(one);
		Assert.assertArrayEquals(one_expected, one);

		BubbleSort.sortBest(sorted);
		Assert.assertArrayEquals(sorted_expected, sorted);

		BubbleSort.sortBest(reverse);
		Assert.assertArrayEquals(sorted_expected, reverse);

		BubbleSort.sortBest(any);
		Assert.assertArrayEquals(any_expected, any);
	}

}
