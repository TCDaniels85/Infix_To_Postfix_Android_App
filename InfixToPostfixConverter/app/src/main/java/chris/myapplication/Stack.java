package chris.myapplication;

/**
 * Interface for Stack class, concrete implementation of these methods is 
 * defined in the ArrayStack class
 * 
 * @author Thomas Christopher Daniels
 *
 */
public interface Stack {
	public boolean isEmpty ();
    public Object peek(); 
    public void push(Object object);
    public Object pop();
}
