package dataStructures;

import java.util.ArrayList;
import java.util.Arrays;

public class ArraysAndArrayLists {
	public static void main(String[] args) {
		// create an array of capacity 10
		int[] arr = new int[10];
		// fill the array with only 10s
		Arrays.fill(arr, 10);

		// create an array with set values
		int[] arr2 = new int[] { 6, 1, 20, 4, 3 };
		// sort the array
		Arrays.sort(arr2);
		// find the position of 3 in the sorted array (returns 1)
		int pos = Arrays.binarySearch(arr2, 3);

		// create an array list of capacity 100
		ArrayList<Integer> arList = new ArrayList<Integer>(100);
		// add 1, 10 to it
		arList.addAll(Arrays.asList(1, 10));
		// get the 2nd element (the one at index 1)
		int temp = arList.get(1);
		// convert an ArrayList to an array
		Integer[] list2Arr = arList.toArray(new Integer[arList.size()]);
	}
}
