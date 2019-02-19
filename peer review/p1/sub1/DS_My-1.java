// p1:Implement and Test DataStructureADT
// Author: Chenhao Lu
// Lec 001
// Email Address: clu92@wisc.edu
// Due Date: 2/7/2019
public class DS_My implements DataStructureADT {

	/**
	 * Represents a key-value pair
	 * 
	 * @author Chenhao Lu
	 *
	 */
	private class keyValuePair {

		private Comparable key; // stores key
		private Object value; // stores value
		private keyValuePair next; // reference to the next pair

		/**
		 * Initializes the fields
		 * 
		 * @param key   Data for field key
		 * @param value Data for field value
		 */
		private keyValuePair(Comparable key, Object value) {
			this.key = key;
			this.value = value;
			this.next = null;
		}

		/**
		 * Getter for field key
		 * 
		 * @return key
		 */
		private Comparable getKey() {
			return key;
		}

		/**
		 * Getter for value
		 * 
		 * @return value
		 */
		private Object getValue() {
			return value;
		}

		/**
		 * Getter for next reference
		 * 
		 * @return next
		 */
		private keyValuePair getNext() {
			return next;
		}

		/**
		 * Setter for next reference
		 */
		private void setNext(keyValuePair kvp) {
			this.next = kvp;
		}
	}

	private keyValuePair head; // head reference of the linked list
	private int size; // size of the linked list

	/**
	 * Initialize an empty list
	 */
	public DS_My() {
		head = null;
		size = 0;
	}

	/**
	 * Add the key-value pair to the data structure and increases size.
	 */
	@Override
	public void insert(Comparable k, Object v) throws IllegalArgumentException, RuntimeException {
		if (k == null)
			throw new IllegalArgumentException("null key"); // key cannot be null

		if (this.contains(k))
			throw new RuntimeException("duplicate key"); // no duplicates

		if (this.size == 0) {
			head = new keyValuePair(k, v); // set head to the new key if the list is empty
			size++;
		} else {
			keyValuePair last = head;
			while (last.getNext() != null)
				last = last.getNext(); // searching for the last position
			last.setNext(new keyValuePair(k, v)); // inserting the new pair
		}
	}

	/**
	 * If key is found, removes the key from the data structure and decreases size
	 */
	@Override
	public boolean remove(Comparable k) {
		if (k == null)
			throw new IllegalArgumentException("null key"); // key cannot be null

		if (this.size == 0)
			return false; // empty list yields false

		if (this.head.getKey().equals(k)) {
			head = head.getNext();
			size--;
			return true; // if head is the match
		} else {
			keyValuePair current = head;
			while (current.getNext() != null) {
				if (current.getNext().getKey().equals(k)) { // search for the match
					current.setNext(current.getNext().getNext()); // remove the match
					size--;
					return true;
				}
				current = current.getNext(); // iterate through the list
			}
		}
		return false; // if match not found
	}

	/**
	 * Returns true if the key is in the data structure, false otherwise
	 */
	@Override
	public boolean contains(Comparable k) {
		if (k == null)
			return false; // false when null key

		keyValuePair current = head;
		while (current != null) {
			if (current.getKey().equals(k))
				return true; // search for the match
			current = current.getNext(); // iterate through the list
		}
		return false; // false when no match found
	}

	/**
	 * Returns the value associated with the specified key
	 */
	@Override
	public Object get(Comparable k) {
		if (k == null)
			throw new IllegalArgumentException("null key"); // throw exception when null key

		keyValuePair current = head;
		while (current != null) {
			if (current.getKey().equals(k))
				return current.getValue(); // search for the match
			current = current.getNext(); // iterate through the list
		}
		return null; // null when no match found
	}

	/**
	 * Returns the number of elements in the data structure
	 */
	@Override
	public int size() {
		return this.size;
	}

}
