package chris.myapplication;

/**
 * Interface for list classes, concrete implementation of these methods is 
 * defined in the ArrayList class
 * @author Thomas Christopher Daniels
 *
 */
public interface LinearList {
	public boolean isEmpty();
    public int size();
    public Object get(int index);
    public int indexOf(Object obj);
    public void add(int index, Object obj);
    public Object remove(int index);
    public String outputList();
}
