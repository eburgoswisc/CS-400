
public class DS_My implements DataStructureADT {

    // TODO may wish to define an inner class 
    // for storing key and value as a pair
    // such a class and its members should be "private"
	
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
    
    public DS_My() {
        this.ls = new Pair[501];
        this.size = 0;
    }

    @Override
    public void insert(Comparable k, Object v) {
        if (this.size == 0) {
        	Pair p = new Pair(k,v);
        	ls[this.size] = p;
        	this.size++;
        
        return;
        }
        else {
        	for (int i = 0; i < this.size; i++) {
        		if (ls[i].key.equals(k)) {throw new RuntimeException();}
        	}
        	Pair p = new Pair(k,v);
        	ls[this.size] = p;
        	this.size++; 	
        }
        
    }

    @Override
    public boolean remove(Comparable k) {
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
    	for (int i = 0; i < this.size; i++) {
    		if (ls[i].key.equals(k)) {
    			return ls[i].value;
    		}
    	}
		return null;
    }

    @Override
    public int size() {
        return this.size;
    }

}

