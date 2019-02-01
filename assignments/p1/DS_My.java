
public class DS_My implements DataStructureADT {
	
	private Pair[] items;
	private int size;

    private class Pair<K, V> {
    	private K key;
    	private V value;
    	
    	private Pair(K key, V value) {
    		this.key = key;
    		this.value = value;
    	}
    	
    }

    // Private Fields of the class
    // TODO create field(s) here to store data pairs
    
    public DS_My() {
        this.items = new Pair[500];
        this.size = 0;
    }

    @Override
    public void insert(Comparable k, Object v) {
        items[size] = new Pair<t>(k,v);
        
    }

    @Override
    public boolean remove(Comparable k) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean contains(Comparable k) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Object get(Comparable k) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return 0;
    }

}
