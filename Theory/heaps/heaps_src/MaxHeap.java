package heaps_src;

import java.util.Arrays;

/**
 * Create a max heap
 * 
 * Max heap property: parent >= its children, root is max value
 * Shape property: a heap is a complete binary tree
 * 
 * @author adina
 */
public class MaxHeap {
	// underlying array and its maximum capacity
	private int[] array;
	private int capacity;

	// current size of the heap
	// zero-indexed array, last element is at location (size - 1)
	public int size;

	/**
	 * Constructor: create an array of size capacity to hold the heap
	 * 
	 * @param capacity: heap capacity
	 */
	public MaxHeap(int capacity) {
		this.capacity = capacity;
		array = new int[capacity];
		size = 0;
	}

	/**
	 * Set the underlying array - use only for test
	 * 
	 * @param array - test array
	 */
	public void setArray(int[] array) {
		capacity = array.length;
		size = capacity;
		this.array = array;
	}

	/**
	 * Get the index of the left child of the node at position pos
	 * - left child is at position 2*pos+1
	 * 
	 * @param pos - the position of the parent node
	 * @return index of array[pos]'s left child
	 */
	private int getLeftChildIndex(int pos) {
		int leftIdx = 2 * pos + 1;
		if (leftIdx >= size)
			return -1;
		return leftIdx;
	}

	/**
	 * Get the index of the right child of the node at position pos
	 * - right child is at position 2*pos+2
	 * 
	 * @param pos - the position of the parent node
	 * @return index of array[pos]'s right child
	 */
	private int getRightChildIndex(int pos) {
		int leftIdx = 2 * pos + 2;
		if (leftIdx >= size)
			return -1;
		return leftIdx;
	}

	/**
	 * Get the index of the parent of the node at position pos
	 * - parent node is at position (p-1)/2
	 * 
	 * @param pos - the position of the child node
	 * @return index of array[pos]'s parent
	 */
	private int getParentIndex(int pos) {
		if (pos <= 0)
			return -1;
		return (pos - 1) / 2;
	}

	/**
	 * Create heap by calling insert repeatedly
	 * O(n log n)
	 * 
	 * @param arr - the array to transform into a heap
	 * @param type - 'i' for iterative, 'r' for recursive
	 */
	public void createHeapUsingInsert(int[] arr, char type) {
		for (int a : arr)
			insert(a, type);
	}

	/**
	 * Add the element to end of array and then move up to restore heap
	 * property - O(log n) in size of array
	 *
	 * @param val - value to insert
	 * @param type - 'i' for iterative, 'r' for recursive
	 */
	public void insert(int val, char type) {
		// increase array if the array is full
		if (size == capacity) {
			// use a factor of 3/2
			capacity = capacity * 3 / 2;
			// create new array of new capacity and copy over old array
			int[] B = new int[capacity];
			for (int i = 0; i < size; i++)
				B[i] = array[i];
			// the larger array is now backing the heap
			array = B;
		}

		// add new item to the end of the array
		array[size] = val;

		// move the new item up to the position to satisfy the heap property
		heapUp(size, type);

		// increase array size since we added an item
		size++;
	}

	/**
	 * Iterative Heap-up: call this public method for iterative heap-up
	 *
	 * @param pos - the position of the element we want moved
	 * @param type - 'i' for iterative, 'r' for recursive
	 */
	public void heapUp(int pos, char type) {
		if (type == 'r')
			heapUpRecursive(pos);
		else
			heapUpIterative(pos);
	}

	/**
	 * Iterative Heap-up: Iteratively move the item at position pos up (left)
	 * until all elements satisfy the heap property (node originally at pos
	 * is below nodes with larger keys and above nodes with smaller keys)
	 * O(log n)
	 *
	 * @param pos - the position of the element we want moved
	 */
	private void heapUpIterative(int pos) {
		// get the value of the element at position pos
		int value = array[pos];

		// get the index of the parent of the node at position pos
		int parentIdx = getParentIndex(pos);

		// while there are still parent nodes
		// and the heap property is not still satisfied
		while (pos > 0 && array[parentIdx] < value) {
			// move parent node down to current index
			array[pos] = array[parentIdx];
			// new index is the old parent index
			pos = parentIdx;
			// get the parent of the parent
			parentIdx = getParentIndex(pos);
		}
		// put the saved value of node at the correct position
		array[pos] = value;
	}

	/**
	 * Recursive Heap-up: use this method for recursive heap-up
	 *
	 * @param pos - the position of the element we want moved
	 */
	private void heapUpRecursive(int pos) {
		heapUpRecursive(pos, array[pos]);
	}

	/**
	 * Recursive Heap-up: Recursively move the item at position pos up (left)
	 * until all elements satisfy the heap property (node originally at pos
	 * is below nodes with larger keys and above nodes with smaller keys)
	 * O(log n)
	 *
	 * @param pos - the position of the element we want moved
	 * @param value - the original value of element at position pos
	 */
	private void heapUpRecursive(int pos, int value) {
		// get the parent index for the node at position pos
		int parentIdx = getParentIndex(pos);

		// set the value to the current index if we've run out of nodes or we're
		// satisfying the heap property
		if (pos <= 0 || array[parentIdx] >= value) {
			array[pos] = value;
			return;
		}

		// move parent node down to current index
		array[pos] = array[parentIdx];

		// new current index is the parent index; pass value forward
		heapUpRecursive(parentIdx, value);
	}

	/**
	 * Heapify array by iteratively calling heapUp on every element in the array
	 * O(n log n)
	 *
	 * @param array - the array we want to heapify
	 * @param type - 'i' for iterative, 'r' for recursive
	 */
	public void createHeapIterative_HeapUp(int[] array, char type) {
		// set the heap array to the given array
		this.setArray(array);

		// heapify elements of the array by calling heapUp
		for (int pos = 0; pos < size; pos++) {
			heapUp(pos, type);
		}
	}

	/**
	 * Heapify array by recursively calling heapUp on every element in the array
	 * O(n log n)
	 *
	 * @param array - the array we want to heapify
	 * @param type - 'i' for iterative, 'r' for recursive
	 */
	public void createHeapRecursive_HeapUp(int[] array, char type) {
		// set the heap array to the given array
		this.setArray(array);

		// call the recursive method to heapify the array
		createHeapRecursive_HeapUp(0, type);
	}

	/**
	 * Heapify array by recursively calling heapUp
	 * 
	 * @param pos - the position to start
	 * @param type - 'i' for iterative, 'r' for recursive
	 */
	private void createHeapRecursive_HeapUp(int pos, char type) {
		// stop when reaching an invalid position
		if (pos == -1 || pos >= size)
			return;
		// do the heapUp operation on current position
		heapUp(pos, type);
		// recurse to the left
		createHeapRecursive_HeapUp(getLeftChildIndex(pos), type);
		// recurse to the right
		createHeapRecursive_HeapUp(getRightChildIndex(pos), type);
	}

	/**
	 * Iterative Heap-down: call this public method for iterative heap-down
	 *
	 * @param pos - the position of the element we want moved
	 * @param type - 'i' for iterative, 'r' for recursive
	 */
	public void heapDown(int pos, char type) {
		if (type == 'r')
			heapDownRecursive(pos);
		else
			heapDownIterative(pos);
	}

	/**
	 * Iterative Heap-down: Iteratively move the item at position pos down
	 * (right) until all elements satisfy the heap property (node originally at
	 * pos is below nodes with larger keys and above nodes with smaller keys)
	 * O(log n)
	 *
	 * @param pos - the position of the element we want moved
	 */
	private void heapDownIterative(int pos) {
		// compare the current element at pos with its two children
		// and swap the parent node with the larger child
		int value = array[pos];

		// while the node still has children (will stop when both leftIdx and
		// rightIdx are -1 below) and the heap property is still violated
		while (true) {
			// get left and right child indices
			int leftIdx = getLeftChildIndex(pos);
			int rightIdx = getRightChildIndex(pos);

			// if no child node, stop
			if (leftIdx == -1 && rightIdx == -1)
				break;

			// index of max child
			int maxIdx = -1;

			// if there is only a right child
			if (leftIdx == -1)
				maxIdx = rightIdx;
			// if there is only a left child
			else if (rightIdx == -1)
				maxIdx = leftIdx;

			// otherwise, maxIdx is the position of the largest child
			else
				maxIdx = (array[rightIdx] > array[leftIdx]) ? rightIdx : leftIdx;

			// if value is greater than the max child value, the array satisfies
			// the heap property, so stop
			if (value >= array[maxIdx])
				break;

			// otherwise move the biggest child node up to the parent
			array[pos] = array[maxIdx];
			// and the new index is the index of the max child
			pos = maxIdx;
		}
		// put element value at final position
		array[pos] = value;
	}

	/**
	 * Recursive Heap-down: use this method for recursive heap-up
	 *
	 * @param pos - the position of the element we want moved
	 */
	private void heapDownRecursive(int pos) {
		heapDownRecursive(pos, array[pos]);
	}

	/**
	 * Recursive Heap-down: Recursively move the item at position pos down
	 * (right) until all elements satisfy the heap property (node originally at
	 * pos is below nodes with larger keys and above nodes with smaller keys)
	 * O(log n)
	 *
	 * @param pos - the position of the element we want moved
	 * @param value - the original value that we're moving
	 */
	private void heapDownRecursive(int pos, int value) {
		// get left and right child indices
		int leftIdx = getLeftChildIndex(pos);
		int rightIdx = getRightChildIndex(pos);

		// if no child node, stop
		if (leftIdx == -1 && rightIdx == -1)
			return;

		// index of max child
		int maxIdx = -1;

		// if there is only a right child
		if (leftIdx == -1)
			maxIdx = rightIdx;
		// if there is only a left child
		else if (rightIdx == -1)
			maxIdx = leftIdx;
		// otherwise, maxIdx is the position of the largest child
		else
			maxIdx = (array[rightIdx] > array[leftIdx]) ? rightIdx : leftIdx;

		// if value is greater than the max child value, the array satisfies
		// the heap property, so stop
		if (value >= array[maxIdx])
			return;

		// swap
		int temp = array[pos];
		array[pos] = array[maxIdx];
		array[maxIdx] = temp;

		// recursively do the same for maxIdx, passing down the value
		heapDownRecursive(maxIdx, array[maxIdx]);
	}

	/**
	 * Heapify array by iteratively calling heapDown on every element in the
	 * array in O(n log n)
	 *
	 * @param array - the array we want to heapify
	 * @param type - 'i' for iterative, 'r' for recursive
	 */
	public void createHeapIterative_HeapDown(int[] array, char type) {
		// set the heap array to the given array
		this.setArray(array);

		// heapify elements of the array by calling heapDown
		// the last element that might have a child is at position size/2-1
		for (int i = size / 2 - 1; i >= 0; i--)
			heapDown(i, type);
	}

	/**
	 * Heapify array by recursively calling heapDown on every element in the
	 * array in O(n log n)
	 *
	 * @param array - the array we want to heapify
	 * @param type - 'i' for iterative, 'r' for recursive
	 */
	public void createHeapRecursive_HeapDown(int[] array, char type) {
		// set the heap array to the given array
		this.setArray(array);

		// call the recursive method to heapify the array
		createHeapRecursive_HeapDown(0, type);
	}

	/**
	 * Recursive method to heapify the array
	 * O(n log n)
	 *
	 * @param array - the array we want to heapify
	 * @param type - 'i' for iterative, 'r' for recursive
	 */
	private void createHeapRecursive_HeapDown(int pos, char type) {
		// the last element that might have a child is at position size/2-1
		if (pos > size / 2 - 1)
			return;
		// heapify the left side
		createHeapRecursive_HeapDown(getLeftChildIndex(pos), type);
		// heapify the right side
		createHeapRecursive_HeapDown(getRightChildIndex(pos), type);
		// move the element to its correct position
		heapDown(pos, type);
	}

	/**
	 * Is this an empty heap
	 * 
	 * @return true if empty, false if not
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Remove and return the max value from the heap (node at position 0) and
	 * then move the new root to its correct position down the heap
	 * O(log n)
	 * 
	 * @param type - 'i' for iterative, 'r' for recursive
	 * @return max value
	 */
	public int delete(char type) {
		// empty heap, return minimum possible value
		if (size == 0)
			return Integer.MIN_VALUE;

		// the max value is at position 0
		int max = array[0];

		// move last element into position zero
		array[0] = array[size - 1];
		size--;

		// max heapify: if the node at the root is too small for that position,
		// it's moved down the heap into its proper place
		heapDown(0, type);

		// return the removed node
		return max;
	}

	/**
	 * Get the value of the maximum node without removing it
	 * O(1)
	 *
	 * @return max value
	 */
	public int peek() {
		// empty heap, return minimum possible value
		if (size == 0)
			return Integer.MIN_VALUE;

		// max is at position 0
		return array[0];
	}

	/**
	 * Change the priority of node in position pos to value val
	 *
	 * @param pos - the position of the node
	 * @param val - the value we're setting for array[pos]
	 * @param type - 'i' for iterative, 'r' for recursive
	 * 
	 * @return true if successful
	 */
	public boolean changePriority(int pos, int val, char type) {
		// are the arguments valid
		if (pos < 0 || pos >= size)
			return false;

		// replacing with same value
		if (val == array[pos])
			return true;

		// save the old value
		int oldVal = array[pos];

		// assign the new value
		array[pos] = val;

		// if current value is bigger, heap up
		if (oldVal < val)
			heapUp(pos, type);
		// otherwise heap down
		else
			heapDown(pos, type);
		return true;
	}

	// ------------------------------------------------------------
	/**
	 * @return the size of the underlying array
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * @return the heap as an array of size size
	 */
	public int[] getHeapArray() {
		return Arrays.copyOf(array, size);
	}

	/**
	 * Get the heap as a string array
	 * 
	 * @return the heap as an array [ e1 e2 ... en ]
	 */
	@Override
	public String toString() {// get the heap as String array
		return Arrays.toString(getHeapArray());
	}

	/**
	 * DISPLAY HEAP as a tree
	 */
	public void displayHeap() {
		// heap format
		int nBlanks = 64;
		int itemsPerRow = 1;
		int column = 0;
		int j = 0; // current item
		String dots = "...............................";
		System.out.println(dots + dots + dots + dots); // dotted top line
		while (size > 0) { // for each heap item
			if (column == 0) // first item in row?
				for (int k = 0; k < nBlanks; k++) // preceding blanks
					System.out.print(' ');
			// display item
			System.out.print(array[j]);
			if (++j == size) // done?
				break;
			if (++column == itemsPerRow) // end of row?
			{
				nBlanks /= 2; // half the blanks
				itemsPerRow *= 2; // twice the items
				column = 0; // start over on
				System.out.println(); // new row
			} else // next item on row
				for (int k = 0; k < nBlanks * 2 - 2; k++)
					System.out.print(" "); // interim blanks
		} // end for
			// dotted bottom line
		System.out.println("\n" + dots + dots + dots + dots);
	} // end displayHeap()
}
