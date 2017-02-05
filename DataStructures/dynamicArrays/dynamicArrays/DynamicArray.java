package dynamicArrays;

/**
 * Implement an ArrayList that allows resizing of the array, constant element
 * access and constant (amortized) insertion
 * 
 * @author adina
 */
public class DynamicArray {
	// underlying object is an array of size capacity
	private Object[] array;
	private int capacity;

	// index up to which the array has been filled
	private int lastFilledIdx;

	// resize factor
	private double factor;

	// default array capacity and resize factor
	private final static int defaultCapacity = 5;
	private final static double defaultFactor = 2;

	/**
	 * Construct empty DynamicArray with given capacity and factor
	 * 
	 * @param capacity: capacity of the back-up array
	 * @param factor: factor for resizing
	 * @throws Exception
	 */
	public DynamicArray(int capacity, double factor) throws Exception {
		constructDynamicArray(capacity, factor);
	}

	/**
	 * Construct empty DynamicArray with given array capacity and default factor
	 * 
	 * @param capacity: capacity of the back-up array
	 * @throws Exception
	 */
	public DynamicArray(int capacity) throws Exception {
		this(capacity, defaultFactor);
	}

	/**
	 * Construct empty DynamicArray with default array capacity and default
	 * factor
	 * 
	 * @throws Exception
	 */
	public DynamicArray() throws Exception {
		this(defaultCapacity);
	}

	/**
	 * Construct a DynamicArray from a given array and given factor
	 * 
	 * @param A: the back-up array
	 * @param factor: factor for resizing
	 * @throws Exception
	 */
	public DynamicArray(Object[] A, double factor) throws Exception {
		if (A == null)
			throw new Exception("Array can't be null");
		constructDynamicArray(A.length, factor);
		this.array = A;
		this.lastFilledIdx = capacity - 1;
	}

	/**
	 * Construct DynamicArray from given array, with default factor
	 * 
	 * @param A: the back-up array
	 * @throws Exception
	 */
	public DynamicArray(Object[] A) throws Exception {
		this(A, defaultFactor);
	}

	/**
	 * Construct an DynamicArray given an array capacity and a factor
	 * 
	 * @param capacity: capacity of the back-up array
	 * @param factor: factor for resizing
	 * @throws Exception
	 */
	private void constructDynamicArray(int capacity, double factor) throws Exception {
		if (factor <= 0)
			throw new Exception("factor has to be > 0");
		if (capacity < 0)
			throw new Exception("capacity has to be >= 0");

		this.lastFilledIdx = -1;
		this.capacity = capacity;
		this.array = new Object[capacity];
		this.factor = factor;
	}

	// =============================================================

	/**
	 * Set the value of the element at given index in O(1)
	 * 
	 * @param index
	 * @param value to set element at index to
	 * @throws Exception if index is < 0 or index > lastFilledIdx
	 */
	public void set(int index, Object value) throws Exception {
		if (index < 0 || index > lastFilledIdx)
			throw new IndexOutOfBoundsException("Index has to be >= 0 and <= lastFilledIdx");

		array[index] = value;
	}

	/**
	 * Get the value of the element at given index in O(1)
	 * 
	 * @param index
	 * @return value of element at index
	 * @throws Exception if index is out of bounds
	 */
	public Object get(int index) throws Exception {
		if (index > lastFilledIdx || index < 0)
			throw new IndexOutOfBoundsException("Index has to be >= 0 and <= lastFilledIdx");

		return array[index];
	}

	/**
	 * Insert an element at the end of the array (constant amortized time O(1))
	 * 
	 * @param element
	 */
	public void add(Object element) {
		// if we ran out of space, increase the capacity
		if (array.length == ++lastFilledIdx) {
			increaseCapacity();
		}
		// add the element at the last index
		array[lastFilledIdx] = element;
	}

	/**
	 * Increase the capacity of the array by the current factor
	 */
	private void increaseCapacity() {
		// if the array was originally empty, make it capacity = defaultCapacity
		capacity = (int) ((capacity == 0) ? defaultCapacity : capacity * factor);
		Object[] array_new = new Object[capacity];
		// copy the current array over
		for (int i = 0; i < lastFilledIdx; i++)
			array_new[i] = array[i];
		// use the new array
		array = array_new;
	}

	/**
	 * Delete the element at the end of the array (constant amortized time O(1))
	 */
	public void delete() {
		if (lastFilledIdx < 0)
			return;
		// set the element at lastFilledIndex to null
		array[lastFilledIdx] = null;
		// decrease lastFiledIdx
		lastFilledIdx--;
	}

	/**
	 * Insert new value at given index (linear time because all elements to the
	 * right of index need to be shifted right)
	 * 
	 * @param idx index to insert at
	 * @param val value to insert
	 */
	public void insertAt(int idx, Object val) {
		if (idx > lastFilledIdx || idx < 0)
			throw new IndexOutOfBoundsException("Index has to be >= 0 and <= lastFilledIdx");
		// last filled index increases,
		// and array capacity is increased if we've reached max
		if (array.length == ++lastFilledIdx)
			increaseCapacity();
		// move everything over to the right by 1 starting at idx
		for (int i = lastFilledIdx; i > idx; i--)
			array[i] = array[i - 1];
		// set the element at idx to the value val
		array[idx] = val;
	}

	/**
	 * Delete the element at index (linear time, because all elements to the
	 * right of index need to be shifted left)
	 * 
	 * @param idx index at which to delete
	 */
	public void deleteAt(int idx) {
		if (idx > lastFilledIdx || idx < 0)
			throw new IndexOutOfBoundsException("Index has to be >= 0 and <= lastFilledIdx");
		for (int i = idx; i < lastFilledIdx; i++)
			array[i] = array[i + 1];
		// set the element at lastFilledIndex to null
		array[lastFilledIdx] = null;
		// decrease lastFiledIdx
		lastFilledIdx--;
	}

	/**
	 * Get the last index filled in the array
	 * 
	 * @return the last filled index
	 */
	public int getLastIndex() {
		return lastFilledIdx;
	}

	/**
	 * Get the current array capacity
	 * 
	 * @return current array capacity
	 */
	public int getArrayCapacity() {
		return capacity;
	}

	/**
	 * Convert to string in O(n)
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("[");
		if (lastFilledIdx < 0) {
			sb.append("]");
			return sb.toString();
		}
		for (int i = 0; i <= lastFilledIdx - 1; i++)
			sb.append(array[i] + ", ");
		return sb.append(array[lastFilledIdx] + "]").toString();
	}
}
