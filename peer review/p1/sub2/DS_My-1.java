
public class DS_My implements DataStructureADT {

    // TODO may wish to define an inner class 
    // for storing key and value as a pair
    // such a class and its members should be "private"
    
    private class DS_My_Node{
    	private Comparable key;
    	private Object value;
    	private DS_My_Node next;
    	
    	private DS_My_Node(Comparable k,Object v) {
    		key = k;
    		value = v;
    		next = null;
    	}
    }
    
     // Private Fields of the class
    // TODO create field(s) here to store data pairs
    
	private DS_My_Node head; // reference to the first Node in this list
    private int size;    
    
    public DS_My() {
        head = null;
        size = 0;
    }

    @Override
    public void insert(Comparable k, Object v) {
      
      DS_My_Node tmp = head;
      
      if (k == null) 
    	  throw new IllegalArgumentException("null key");
          
      while (tmp != null) {
        if (tmp.key.equals(k))
        	throw new RuntimeException("duplicate key"); 
        else
    	  tmp = tmp.next;
      }
      
      DS_My_Node curNode = head;
      
      if (head == null) {
    	  head = new DS_My_Node(k,v);
          size++;
      }
      else {
    	  while (curNode.next != null) {
    		  curNode = curNode.next;
    	  }
          curNode.next = new DS_My_Node(k,v);
          size++;
      }
    }

    @Override
    public boolean remove(Comparable k) {
        DS_My_Node tmp = head;
        
    	if (k == null)
        	throw new IllegalArgumentException ("null key");
    	
        while (!tmp.key.equals(k)) {
        	tmp = tmp.next;
        	if (tmp == null) 
        		return false;
        }
        
        if (tmp.next != null) {
        	while (tmp.next != null) {
        		tmp = tmp.next;
        	}
        	tmp = null;
        	size--;
        }
        else {
        	tmp = null;
        	size--;
        }
        	
        return true;        
    }
    
    @Override
    public boolean contains(Comparable k) {
    	if (k == null)
    		return false;
        DS_My_Node curNode = head;
        
        while (curNode != null) {
            if (curNode.key.equals(k))
            	return true; 
            else
        	  curNode = curNode.next;
          }
    		
        return true;
    }

    @Override
    public Object get(Comparable k) {
        DS_My_Node curNode = head;

    	if (k == null)
        	throw new IllegalArgumentException ("null key");
    	
        while (!curNode.key.equals(k)) {
        	curNode = curNode.next;
        	if (curNode == null)
        		return null;
        } 
        
        	return curNode.value;
    }

    @Override
    public int size() {
        return size;
    }

}
