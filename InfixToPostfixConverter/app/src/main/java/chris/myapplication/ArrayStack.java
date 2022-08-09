package chris.myapplication;

import java.util.EmptyStackException;

/**
 *ArrayStack class to create concrete methods for the Stack interface.
 * Creates an ArrayStack abstract data type for use in the InfixConversion class
 * @author Thomas Christopher Daniels
 *
 */
public class ArrayStack implements Stack{
	
    protected int top;    // keeps track where the top of the stack is in relation to the next empty space in the arrayList
    protected LinearList stack;
    

    /**
     * Constructor for objects of class ArrayStack
     */
    
    public ArrayStack()
    {
       stack = new ArrayList(20); //Creates an arrayList class with capacity 20, no requirement for anything larger.
    }
    
    /**
     * Checks if the stack is empty
     * @return true if the stack contains no item
     */
    public boolean isEmpty ()
    {
        return top == 0;
    }
    
    /**
     * Allows user to return the item on top of the stack without removing it
     * @throws EmptyStackException if the stack is empty when peek is called
     * @return object on top of the stack 
     */
    public Object peek() 
    {
        if (isEmpty()) 
        {
            throw new EmptyStackException(); //throw exception if the stack is empty
        }
        int peek = top - 1;  // returns the top item without decrementing top variable
        return stack.get(peek);
    }
    
    /**
     * Place an object on top of the stack
     * @param object the object to be added to the stack
     */
    public void push(Object object)
    { 
            stack.add(top,object); //adds item to the end of the arrayList
            top++;		//increments top variable by 1
    }
    
    /**
     * pops and removes the object from the top of the stack
     * @throws EmptyStackException if the stack is empty
     * @return the object the object being removed from the stack
     */
    public Object pop()
    {
        if (isEmpty()) 
        {
            throw new EmptyStackException();
        }        
        Object topObject = stack.remove(--top);        //decrements the top variable and removes object in that position from the arrayList
        return topObject;

    }
}
