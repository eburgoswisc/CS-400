import javax.management.RuntimeErrorException;

public class DS_My implements DataStructureADT {
	
	private class Pair  {
		private Comparable key;
		private Object value;
		
		private Pair(Comparable k, Object v) {
			this.key = k;
			this.value = v;
		}
	}
	
	private Pair[] ls;
	private int size;
	private int CAPACITY;
    
    public DS_My() {
    	this.CAPACITY = 500;
        this.ls = new Pair[CAPACITY];
        this.size = 0;
    }

    @Override
    public void insert(Comparable k, Object v) {
    	
    	if (k == null) {throw new IllegalArgumentException("null key");}
    	
    	else if (this.size == 0) {
        	Pair p = new Pair(k,v);
        	ls[this.size] = p;
        	this.size++;
        	return;
        }
        
        else if (this.CAPACITY == this.size){
        	System.out.println("Got here");
        	this.CAPACITY = this.CAPACITY + 100;
        	Pair[] newLs = new Pair[this.CAPACITY];
        	for (int i = 0; i < this.size; i++) {
        		newLs[i] = this.ls[i];
        	}
        	this.ls = newLs;
        	this.ls[size] = new Pair(k,v); // Add the Pair 
        	size++;
        	return;
        }
        
        else {
        	for (int i = 0; i < this.size; i++) {
        		if (ls[i].key.equals(k)) {throw new RuntimeException("duplicate key");}
        	}
        	Pair p = new Pair(k,v);
        	ls[this.size] = p;
        	this.size++; 	
        }
        
    }

    @Override
    public boolean remove(Comparable k) {
    	
    	if (k == null) {throw new IllegalArgumentException("null key");}
    	
    	else {
    	Pair[] newList = new Pair[500];
    	boolean found = false;
    	for (int i = 0; i < this.size; i++) {
    		if (ls[i].key.equals(k)) {
    			found = true;
    			size--;
    			continue;
    		}
    	}
    	this.ls = newList;
        return found;
    	}
    }

    @Override
    public boolean contains(Comparable k) {
    	for (int i = 0; i < this.size; i++) {
    		if (ls[i].key.equals(k)) {
    			return true;
    		}
    	}
        return false;
    }

    @Override
    public Object get(Comparable k) {
    	
    	if (k == null) {throw new IllegalArgumentException("null key");}
    	
    	else {
    	for (int i = 0; i < this.size; i++) {
    		if (ls[i].key.equals(k)) {
    			return ls[i].value;
    		}
    	}
		return null;
    	}
    }

    @Override
    public int size() {
        return this.size;
    }

}

