
/**
 * class MyStack: A stack class implemented by using ArrayList
 * All stack elements are stored in an ArrayList. The top element
 * has index top
 * 
 * @author Singhtaravirak Chea
 * @version 25-Feb-2019 
 */
import java.util.ArrayList;

public class MyStack<E>
{
    private ArrayList<E> list; // used to store elements in stack
    private int top; // the index of top element
    
    /**
     * constructor construct an empty stack
     */
    public MyStack()
    {
        list = new ArrayList<E>(); 
    }
    
    /**
     * push push a given element on the top of the stack
     */
    public void push(E item)
    {
        //add item to top of stack (prepend) 
    	list.add(top, item);
    }
    
    /**
     * isEmpty return true if the stack is empty; false otherwise
     * @return true if the stack is empty; false otherwise
     */
    public boolean isEmpty()
    {
    	//isEmpty is true only if size == 0 
    	return (list.size() == 0); 
    }
    
    /**
     * peek Return the top element
     */
    public E peek()
    {
    	return list.get(top); 
    }
    
    /**
     * pop Remove the top element from the stack. If the stack is empty,nothing happen
     */
    public void pop()
    {
       if(!isEmpty()) {
    	   list.remove(top); 
       }
    }
    
    /**
     * size return the size of the stack
     * @return number of elements in stack
     */
    public int size()
    {
    	return list.size(); 
    }
}

