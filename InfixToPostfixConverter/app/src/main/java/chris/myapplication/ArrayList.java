package chris.myapplication;

import java.lang.IllegalArgumentException;
import java.lang.IndexOutOfBoundsException;
/**
 * ArrayList class to create concrete methods for the LinearList interface.
 * Creates an ArrayList for use in the ArrayStack data structure
 * @author Thomas Christopher Daniels
 *
 */
public class ArrayList implements LinearList{
	 	protected Object[] objectArray; 	//
	    protected int size;		//Variable to keep track of the array list size
	   
	    /**
	     *Constructor to create class with specified initial capacity.
	     *@param capacity the capacity size required
	     */
	    public ArrayList(int capacity)
	    {
	        if (capacity < 1)
	        {
	            throw new IllegalArgumentException("capacity must be >= 1");
	            
	        }
	            // note that size has a default initial value of 0
	            objectArray = new Object [capacity];
	        
	    }
	    
	     /**
	     * Second constructor for objects of class ArrayList capacity of 10
	     */
	    public ArrayList()
	    {
	    	objectArray = new Object [10];
	    }
	    /**
	     * Test if list is empty
	     * 
	     * @return true if list is empty
	     */
	    public boolean isEmpty()
	    {
	        return size == 0;
	    }
	    /**
	     * Returns size of the list.
	     * 
	     * @return The number of elements currently in the list
	     */
	    public int size()
	    {
	        return size;
	    }
	    /**
	     * checks the specified index is valid  between 0 and size
	     * 
	     * @throws IndexOutOfBoundsException if the index is not within the current array boundary 
	     * @param index The index of the object 
	     */
	    public void checkIndex(int index)
	    {
	        
	        if (index < 0 || index > size)
	        {
	            throw new IndexOutOfBoundsException("index out of bounds, index = " + index + " list size = " + size);
	        }
	    }
	    /**
	     * Returns the object at the specified index after confirming that the index is valid     
	     * 
	     * @param index The index of the object
	     * @return the object at specified index
	     * @throws IndexOutOfBoundsException if the index is not within the current array boundary 
	     */
	    public Object get(int index) throws IndexOutOfBoundsException
	    {
	        checkIndex(index);
	        return objectArray[index];
	    }
	    /**
	     * Returns the index of the specified object's first occurrence
	     * 
	     * @param object  Object for which the index is required
	     * @return the index of the specified object, returns -1 if the object is not in the list
	     */
	    public int indexOf(Object object)
	    {
	        for (int i = 0; i < size; i++) //look through every object in array
	        {
	            if (objectArray[i].equals(object))
	            {
	                //is item in list the same as parameter
	                return i;
	            }
	            
	        }
	        return -1;// not found
	    }
	    /**
	     * Adds an object to the specified index within the arrayList
	     * 
	     * @param index  The index at which to insert the object
	     * @param object  The object to be inserted into the arrayList
	     */
	    public void add(int index, Object object) throws IndexOutOfBoundsException
	    {
	        
	        if(isEmpty())
	        {
	            //adds element if list is empty
	        	objectArray[index] = object;
	            size++;
	         } else{
	            checkIndex(index);	            
	            // checks the index is valid then adds object
	            if (size == objectArray.length)
	            // doubles the capacity of the array as there is no space in list
	            {
	                Object[] newArray = new Object[objectArray.length*2];
	                System.arraycopy(objectArray,0,newArray,0,objectArray.length);   //copies array         
	                objectArray = newArray;
	            }
	            if (index == size)
	            {
	                //adds element if array is equal to size so it is appended onto end of list.
	            	objectArray[index] =object;
	                size++;
	            } else
	            {
	                //moves index of other elements if element is inserted in middle/beginning of list
	                for (int i = size - 1; i > index; i--)
	                {
	                	objectArray[i + 1] = objectArray[i];
	                	objectArray[i] = object;                    
	                }
	                size++;
	            }
	        }
	        
	    }
	    /**
	     * Removes the object at specified index from the arrayList
	     * 
	     * @param index  The index of the object for removal
	     * @return the object that has just been removed from the arrayList
	     */
	    public Object remove(int index)
	    {
	        checkIndex(index);
	        // valid index so element can be removed and other elements with higher index moved down
	        
	        Object removedElement = objectArray[index];
	        //All elements with higher index have their index reduced by 1.
	        for (int i = index + 1; i < size; i++)
	        {
	        	objectArray[i-1] = objectArray[i];
	        }
	        // deletes the duplicate element array
	        objectArray[--size] = null; // garbage collection of array
	        return removedElement;
	        
	    }
	    /**
	     * Outputs the content of the arrayList as a String
	     * @returns string containing elements in the list
	     */
	    public String outputList()
	    {
	        StringBuffer s = new StringBuffer("[");
	        for (int i = 0; i < size; i++)
	        {
	            // adds elements to buffer
	            if (objectArray[i] == null)
	            {
	                s.append("null, ");
	            }
	            else
	            
	            {s.append(objectArray[i].toString() + ", ");}
	            // removes last ", "
	        
	        }
	        if (size > 0)
	            {s.delete(s.length() - 2, s.length());
	            s.append("]");}
	        
	        return new String(s); // string to return
	    }
}
