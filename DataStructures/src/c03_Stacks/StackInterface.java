package c03_Stacks;

/*
 * LIFO: items added at the top and removed from the top
 */

public interface StackInterface<MyType> {
	// add element to the top of the stack
	public void push(MyType item) throws Exception;

	// remove element from the top of the stack
	public MyType pop();

	// show the element at the top of the stack without removing it
	public MyType peek();

	// is stack empty?
	public boolean isEmpty();

	// size of stack
	public int size();
}
