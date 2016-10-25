package c07_Heaps;

/**
 * Create a max heap
 * 
 * Heap property: parent >= its children, root is max value
 * (min heap is the opposite)
 * Shape property: a heap is a complete binary tree
 * 
 * Zero-indexed array, last element is at location (heap_size-1)
 * 
 * Given parent position p:
 * - left child is at l = 2 * p + 1
 * - right child is at r = 2 * p + 2
 * Given a child position c:
 * - parent is located at p = (c-1)/2
 * 
 * @author adina
 *
 */
public class MaxHeap {
	// underlying array
	private int[] A;
	// maximum capacity of array
	private int capacity;
	//current size of array
	public int heap_size;

	// constructor
	MaxHeap(int capacity) {
		this.capacity = capacity;
		A = new int[capacity];
		heap_size = 0;
	}

	public int getCapacity() {
		return capacity;
	}

	/**
	 * Create heap by calling insert repeatedly
	 * O(nlogn)
	 * 
	 * @param arr
	 */
	public void createHeap_Insert(int[] arr) {
		for (int a : arr)
			insert(a);
	}

	/**
	 * Create heap by calling bubbleDown on every element in the array
	 * O(nlogn)
	 * 
	 * @param A
	 */
	public void createHeapIterativeBubbleDown(int[] A) {
		this.A = A;
		heap_size = A.length;
		capacity = heap_size;
		for (int i = heap_size / 2 - 1; i >= 0; i--)
			bubbleDown(i);
	}

	/**
	 * Create heap by calling bubbleUp on every element in the array
	 * O(nlogn)
	 * 
	 * @param A
	 */
	public void createHeapIterativeBubbleUp(int[] A) {
		this.A = A;
		heap_size = A.length;
		capacity = heap_size;
		for (int i = heap_size / 2 - 1; i >= 0; i--)
			bubbleUp(i);
	}

	/**
	 * Create heap by calling bubbleUp on every element in the array
	 * O(nlogn)
	 * 
	 * @param A
	 */
	public void createHeapRecursiveBubbleUp(int[] A) {
		this.A = A;
		heap_size = A.length;
		capacity = heap_size;
		createHeapRecursiveBubbleUp(0);
	}

	private void createHeapRecursiveBubbleUp(int pos) {
		if (pos > heap_size / 2 - 1)
			return;
		createHeapRecursiveBubbleUp(getLeftChildIndex(pos));
		createHeapRecursiveBubbleUp(getRightChildIndex(pos));
		bubbleUp(pos);
	}

	/**
	 * Create heap by calling bubbleDown on every element in the array
	 * O(nlogn)
	 * 
	 * @param A
	 */
	public void createHeapRecursiveBubbleDown(int[] A) {
		this.A = A;
		heap_size = A.length;
		capacity = heap_size;
		createHeapRecursiveBubbleDown(0);
	}

	private void createHeapRecursiveBubbleDown(int pos) {
		if (pos > heap_size / 2 - 1)
			return;
		createHeapRecursiveBubbleDown(getLeftChildIndex(pos));
		createHeapRecursiveBubbleDown(getRightChildIndex(pos));
		bubbleDown(pos);
	}

	public boolean isEmpty() {
		return heap_size == 0;
	}

	/**
	 * Add the element to end of array and then move up to restore heap property
	 * O(log n) in size of array
	 * 
	 * @param val
	 * @return
	 */

	public void insert(int val) {
		// increase array if the array is full
		if (heap_size == capacity) {
			capacity = capacity * 3 / 2;
			int[] B = new int[capacity];
			for (int i = 0; i < heap_size; i++)
				B[i] = A[i];
			A = B;
		}

		// add item to the end of the array
		A[heap_size] = val;

		// then move it up to its correct position in which the heap property is satisfied
		// (below a node with a larger key and above a node with a smaller key)
		bubbleUp(heap_size);

		// we've added an item
		heap_size++;
	}

	/**
	 * Heap-up
	 * Move the item at position pos up (to left) until all elements satisfy the
	 * heap property, iteratively
	 * (node is below a node with a larger key and above a node with a smaller
	 * key)
	 * O(log n)
	 * 
	 * @param pos
	 */
	private void bubbleUp(int pos) {
		bubbleUp_Iterative(pos, A);
	}

	/**
	 * Test only, use bubbleUp
	 * 
	 * @param pos
	 * @param A
	 */
	protected void bubbleUp_Iterative(int pos, int[] A) {
		// get the value at position pos
		int value = A[pos];
		// get the parent index for the node at position pos
		int parentIdx = getParentNodeIndex(pos);

		//while there are still nodes and the heap condition is still violated
		while (pos > 0 && A[parentIdx] < value) {
			//move node down
			A[pos] = A[parentIdx];
			//move index up
			pos = parentIdx;
			//get the parent of the parent
			parentIdx = getParentNodeIndex(pos);
		}
		// put the saved value at the correct position
		A[pos] = value;

	}

	/**
	 * Heap-up
	 * Move the item at position pos up (to left) until all elements satisfy the
	 * heap property, iteratively
	 * (node is below a node with a larger key and above a node with a smaller
	 * key)
	 * O(log n)
	 * Test only, use bubbleUp
	 * 
	 * @param pos
	 */
	protected void bubbleUp_Recursive(int pos, int[] A) {
		bubbleUp_Recursive(pos, A, 0);
	}

	protected void bubbleUp_Recursive(int pos, int[] A, int value) {
		if (pos <= 0) {
			return;
		}
		// get the parent index for the node at position pos
		int parentIdx = getParentNodeIndex(pos);
		if (A[parentIdx] >= value) {
			A[pos] = value;
			return;
		}

		// get the value at position pos
		value = A[pos];

		//move node down
		A[pos] = A[parentIdx];
		//move index up and pass value forward
		bubbleUp_Recursive(parentIdx, A, value);
	}

	/**
	 * Get the index of the left child of the node at the index idx
	 * 
	 * @param idx
	 * @return index of A[idx]'s left child
	 */
	private int getLeftChildIndex(int idx) {
		int childIdx = 2 * idx + 1;
		if (childIdx >= heap_size)
			return -1;
		return childIdx;
	}

	/**
	 * Get the index of the right child of the node at the index idx
	 * 
	 * @param idx
	 * @return index of A[idx]'s right child
	 */
	private int getRightChildIndex(int idx) {
		int childIdx = 2 * idx + 2;
		if (childIdx >= heap_size)
			return -1;
		return childIdx;
	}

	/**
	 * Get the index of the parent of the node at the index idx
	 * 
	 * @param idx
	 * @return index of A[idx]'s parent
	 */
	private int getParentNodeIndex(int idx) {
		if (idx <= 0)
			return -1;
		return (idx - 1) / 2;
	}

	/**
	 * Remove and return the max value from the heap (node at position 0)
	 * Move last node to position 0, and restore the heap property
	 * O(log n)
	 * 
	 * @return max value
	 */
	public int delete() {
		// empty heap, return minimum possible value
		if (heap_size == 0)
			return Integer.MIN_VALUE;

		// the max value is at position 0
		int max = A[0];

		// move last element into first position
		A[0] = A[heap_size - 1];
		heap_size--;

		// the node at the root is too small for that position, so it's moved down the heap into its proper place
		// max heapify:
		bubbleDown(0);
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
		if (heap_size == 0)
			return Integer.MIN_VALUE;
		// max is at position 0
		return A[0];
	}

	/**
	 * Change the priority of node in position pos to value val
	 * 
	 * @param pos
	 * @param val
	 * @return true if successful
	 */
	public boolean changePriority(int pos, int val) {
		// are the arguments valid
		if (pos < 0 || pos >= heap_size)
			return false;
		// replacing with same value
		if (val == A[pos])
			return true;

		// save the old value
		int oldVal = A[pos];
		//assign the new value
		A[pos] = val;

		//if current value is bigger, bubble up
		if (oldVal < val)
			bubbleUp(pos);
		//otherwise bubble down
		else
			bubbleDown(pos);
		return true;
	}

	/**
	 * Max-heapify / heap-down
	 * Move the item down (to right) until all elements satisfy the heap
	 * property, iteratively
	 * Check which child is larger, then swap the target node with the larger
	 * child
	 * O(log n)
	 * 
	 * @param pos
	 */
	private void bubbleDown(int pos) {
		bubbleDown_Iterative(pos, A);
	}

	/**
	 * For testing only, use bubbleDown in general
	 * 
	 * @param pos
	 * @param A
	 */
	protected void bubbleDown_Iterative(int pos, int[] A) {//
		// compare the current element at pos with its two children
		int value = A[pos];

		// while the node still has children, won't have past half
		while (pos < A.length) {
			// get left and right child indices
			int leftIdx = getLeftChildIndex(pos);
			int rightIdx = getRightChildIndex(pos);

			// if no child node, stop
			if (leftIdx == -1 && rightIdx == -1)
				break;

			// index of max element
			int maxIdx = Integer.MIN_VALUE;

			// if there is only a right child
			if (leftIdx == -1)
				maxIdx = rightIdx;
			// if there is only a left child
			else if (rightIdx == -1)
				maxIdx = leftIdx;
			// otherwise, there's both a left and right child, find position of max
			else if (A[rightIdx] > A[leftIdx])
				maxIdx = rightIdx;
			else
				maxIdx = leftIdx;

			// if value is greater than the max value, stop
			if (A[maxIdx] <= value)
				break;

			//otherwise move node up
			A[pos] = A[maxIdx];
			// and move index down
			pos = maxIdx;
		}
		// put value at final position
		A[pos] = value;
	}

	/**
	 * Max-heapify / heap-down
	 * Move the item down (to right) until all elements satisfy the heap
	 * property, recursively
	 * Check which child is larger, then swap the target node with the larger
	 * child
	 * O(log n)
	 * 
	 * For testing only
	 * 
	 * @param pos
	 */
	protected void bubbleDown_Recursive(int pos, int[] A) {
		int leftIdx = getLeftChildIndex(pos);
		int rightIdx = getRightChildIndex(pos);
		int maxIdx = 0;
		// get the index of the largest child
		if (leftIdx == -1 && rightIdx == -1)
			return;
		if (leftIdx == -1)
			maxIdx = rightIdx;
		else if (rightIdx == -1)
			maxIdx = leftIdx;
		else if (A[leftIdx] >= A[rightIdx])
			maxIdx = leftIdx;
		else
			maxIdx = rightIdx;

		// switch item at pos with item at maxIdx
		int aux = A[pos];
		A[pos] = A[maxIdx];
		A[maxIdx] = aux;

		// recursively do the same for maxIdx
		bubbleDown_Recursive(maxIdx, A);
	}

	//------------------------------------------------------------	
	/**
	 * Get the heap as a string array
	 * 
	 * @return the heap as an array [ e1 e2 ... en ]
	 */
	@Override
	public String toString() {//get the heap as String array
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < heap_size; i++) {
			sb.append(A[i] + " ");
		}

		return "[ " + sb + "]";
	}

	/**
	 * DISPLAY HEAP as a tree
	 */
	public void displayHeap() {
		// heap format
		int nBlanks = 32;
		int itemsPerRow = 1;
		int column = 0;
		int j = 0; // current item
		String dots = "...............................";
		System.out.println(dots + dots); // dotted top line
		while (heap_size > 0) { // for each heap item
			if (column == 0) // first item in row?
				for (int k = 0; k < nBlanks; k++) // preceding blanks
					System.out.print(' ');
			// display item
			System.out.print(A[j]);
			if (++j == heap_size) // done?
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
		System.out.println("\n" + dots + dots); // dotted bottom line
	} // end displayHeap()
}
