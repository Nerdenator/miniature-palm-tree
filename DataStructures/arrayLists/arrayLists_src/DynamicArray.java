package arrayLists_src;
/**
 * Implement an ArrayList that allows resizing of the array
 * and constant element access and constant (amortized) insertion
 * 
 * ArrayList properties
 * - Getting or setting the value at a particular index (constant time)
 * - Iterating over the elements in order (linear time, good cache performance)
 * - Inserting or deleting an element in the middle of the array (linear time)
 * - Inserting or deleting an element at the end of the array (constant amortized time)
 * 
 * @author adina
 *
 */
public class DynamicArray {
	// underlying object is an array of size arrSz
	private Object [] array;
	private int arrSize;
	// index up to which the array has been filled
	private int lastFilledIdx;
	// resize factor
	private double factor;
	// default size and factor
	private final static int defaultArrSize = 5;
	private final static double defaultFactor = 2;

	/**
	 * Get around this() having to be called first in a method. 
	 * Construct an DynamicArray given an array size and a factor
	 * @param arrSize
	 * @param factor
	 * @throws Exception
	 */
	private void constructDynamicArray(int arrSize, double factor) throws Exception{
		if(factor <= 0) throw new Exception("factor has to be > 0");
		if(arrSize < 0) throw new Exception ("arrSize has to be >= 0");
		
		this.lastFilledIdx = -1;
		this.arrSize = arrSize;
		this.array = new Object[arrSize];
		this.factor = factor;
	}
	
	/**
	 * Constructor: construct an empty DynamicArray with given size and factor
	 * @param maxSz: size of the back-up array
	 * @param factor: factor for resizing
	 * @throws Exception 
	 */
	public DynamicArray(int arrSize, double factor) throws Exception{
		constructDynamicArray(arrSize, factor);
	}
	
	/**
	 * Constructor: construct an empty DynamicArray with given array size and default factor
	 * @param maxSz
	 * @throws Exception 
	 */
	public DynamicArray(int arrSize) throws Exception{
		this(arrSize, defaultFactor);
	}
	
	/**
	 * Constructor: construct an empty DynamicArray with default array size and default factor
	 * @throws Exception 
	 */
	public DynamicArray() throws Exception{
		this(defaultArrSize);
	}
	
	/**
	 * 
	 * Constructor: construct an DynamicArray from a given array
	 * @param A: the back-up array
	 * @param factor: factor for resizing
	 * @throws Exception 
	 */
	public DynamicArray(Object [] A, double factor) throws Exception{
		if(A == null) throw new Exception("Array can't be null");
		constructDynamicArray(A.length, factor);
		this.array = A;
		this.lastFilledIdx = arrSize-1;
	}
	
	/**
	 * Constructor: construct an DynamicArray from a given array, with default factor
	 * @param A
	 * @throws Exception 
	 */
	public DynamicArray(Object [] A) throws Exception{
		this(A, defaultFactor);
	}

	/**
	 * Set the value of the element at index in O(1)
	 * @param index
	 * @param element
	 * @throws Exception if index is < 0 or index > lastFilledIdx
	 */
	public void set(int index, Object element) throws Exception{
		if(index < 0 || index > lastFilledIdx)
			throw new IndexOutOfBoundsException("Index has to be >= 0 and <= lastFilledIdx");
		
		array[index] = element;
	}
	
	/**
	 * Get the element at index in O(1)
	 * @param index
	 * @return element at index
	 * @throws Exception if index is out of bounds
	 */
	public Object get(int index) throws Exception{
		if(index > lastFilledIdx || index < 0)
			throw new IndexOutOfBoundsException("Index has to be >= 0 and <= lastFilledIdx");
		
		return array[index];
	}
	
	/**
	 * Print the elements in order in O(n)
	 */
	public String print(){
		StringBuilder sb = new StringBuilder("[");
		if(lastFilledIdx<0){
			sb.append("]");
			return sb.toString();
		}
		for(int i=0;i<=lastFilledIdx-1;i++)
			sb.append(array[i]+" ");
		return sb.append(array[lastFilledIdx] + "]").toString();
	}
	
	/**
	 * Insert an element at the end of the array (constant amortized time O(1))
	 * Append the element at the last index
	 * @param element
	 */
	public void add(Object element){
		//if we ran out of space, increase the size
		if(array.length == ++lastFilledIdx){
			increaseSize();
		}
		//otherwise just stick the element in there
		array[lastFilledIdx] = element;
	}
	
	/**
	 * Increase the size of the array by a factor
	 */
	private void increaseSize(){
		//if the array was originally empty, make it size = defaultSize
		arrSize = (int) ((arrSize==0) ? defaultArrSize : arrSize*factor);
		Object [] array_new = new Object[arrSize];
		for(int i=0; i<lastFilledIdx; i++)
			array_new[i]=array[i];
		array=array_new;
	}
	
	/**
	 * Delete an element from the end of the array (constant amortized time O(1))
	 * Delete the element at the last index and decrease index
	 */
	public void delete(){
		if(lastFilledIdx < 0) return;
		//set the element at lastFilledIndex to null
		array[lastFilledIdx] = null;
		// decrease lastFiledIdx
		lastFilledIdx--;
	}
	
	/**
	 * Insert at index (linear time because all elements to the right of index
	 * need to be shifted right)
	 * @param idx index to insert at
	 * @param val value to insert
	 */
	public void insertAt(int idx, Object val){
		if(idx > lastFilledIdx || idx < 0)
			throw new IndexOutOfBoundsException("Index has to be >= 0 and <= lastFilledIdx");
		// last filled index increases, 
		// and array size is increased if we've reached max
		if(array.length == ++lastFilledIdx)
			increaseSize();
		// move everything over to the right by 1 starting at idx
		for(int i=lastFilledIdx; i>idx; i--)
			array[i] = array[i-1];
		// set the element at idx to the value val
		array[idx] = val;
	}

	/**
	 * Delete at index (linear time, because all elements to the right of index
	 * need to be shifted left)
	 * @param idx index to delete at
	 */
	public void deleteAt(int idx){
		if(idx > lastFilledIdx || idx < 0)
			throw new IndexOutOfBoundsException("Index has to be >= 0 and <= lastFilledIdx");
		for(int i=idx; i<lastFilledIdx; i++)
			array[i] = array[i+1];
		//set the element at lastFilledIndex to null
		array[lastFilledIdx] = null;
		// decrease lastFiledIdx
		lastFilledIdx--;
	}
	
	public int getLastIndex(){
		return lastFilledIdx;
	}
	
	public int getArraySize(){
		return arrSize;
	}
}
