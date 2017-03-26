package stacks_src;

/**
 * LIFO: items are added to the top of the stack and removed from the top of the
 * stack
 * 
 * @author adina
 *
 * @param <MyType>
 */
public interface InterfaceStack<MyType> {
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
