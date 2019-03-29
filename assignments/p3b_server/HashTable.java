
//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: p3_HashTable
// Files:  HashTable.java, HashTableADT.java,
//         HashTableTest.java, DataStructureADT.java
// Course: CS 400, Spring 2019
//
// Author: Emanuel Burgos
// Email:  eburgos@wisc.edu
// Lecturer's Name: Andy Kuemmel
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    (name of your pair programming partner)
// Partner Email:   (email address of your programming partner)
// Partner Lecturer's Name: (name of your partner's lecturer)
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates, 
// strangers, and others do.  If you received no outside help from either type
//  of source, then please explicitly indicate NONE.
//
// Persons:         (identify each person and describe their help in detail)
// Online Sources:  (identify each URL and describe their assistance in detail)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import java.util.ArrayList;
import java.util.Hashtable;

//
// 1. Collision scheme used is array of arrays. 
// For every index in the table object, if there is an item that 
// calculates an index for this position, an arraylist of KeyValuePairs 
// will be inserted. Then the item will be added. If index already occupied 
// then the item is added to end of arraylist. Its a bucket scheme.
//
// 2. Hashing algorithm is simply index = key.hashcode / tableSize.

//
public class HashTable<K extends Comparable<K>, V> implements HashTableADT<K, V> {

  // Table
  private Object[] table;
  // Metadata about HashTable
  private int numKeys;
  private int tableSize;
  private double loadFactorThreshold;
  private double loadFactor;

  /**
   * Class that represents object type that is inserted into the bucket arrayList
   * of the hashtable. It contains association of key and value along with
   * hashcode value.
   * 
   * @author emanuelburgos
   *
   */
  private class KeyValuePair {
    // Private field members
    private K key; // Key variable
    private V value; // Value associated

    // Each will object will have a hashcode for its key
    private int hashCode;

    /**
     * Argument constructor for class.
     * 
     * @param key   - Comparable object of type K
     * @param value - value associated with key of type V
     */
    private KeyValuePair(K key, V value) {

      // Set values to field members
      this.key = key;
      this.value = value;

      // Get hashcode based on memory address.
      this.hashCode = key.hashCode();

    }
  }

  /**
   * No arguments constructor for HashTable.
   * 
   */
  public HashTable() {

    // Assign values to field members
    this.numKeys = 0;
    this.tableSize = 11;
    this.loadFactorThreshold = 0.75;
    this.loadFactor = 0;

    // Initialize table with bucket space for arrays.
    this.table = new Object[this.tableSize];
  }

  /**
   * Canstructor with arguments for HashTable
   * 
   * @param initialCapacity     int - initial size for table
   * @param loadFactorThreshold double - initial load factor threshold
   */

  public HashTable(int initialCapacity, double loadFactorThreshold) {
    this.tableSize = initialCapacity;
    this.loadFactorThreshold = loadFactorThreshold;
    this.loadFactor = 0;

    // Initialize table with bucket space for arrays.
    this.table = new Object[this.tableSize];

  }

  /*
   * Insert method for hashtable.
   * 
   * (non-Javadoc)
   * 
   * @see DataStructureADT#insert(java.lang.Comparable, java.lang.Object)
   */
  @Override
  public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {

    // Check null or duplicate key
    if (key == null)
      throw new IllegalNullKeyException();

    // Check for duplicate
    try {
      this.get(key);
      // If it finds it, throw exception
      throw new DuplicateKeyException();
    } catch (KeyNotFoundException e) {
      // Do nothing, its expected
    }

    // Initialize item
    KeyValuePair item = new KeyValuePair(key, value);

    // Call helper method
    helperInsert(item, this.table, this.tableSize);

    // Recalculate load factor
    this.calculateLoadFactor();

    return;
  }

  /**
   * Helper method for inserting into a hashtable
   * 
   * @param key   - key for item
   * @param value - value with key
   * @param t     - Array of objects
   * @param size  - size of hashtable
   * @throws DuplicateKeyException
   */
  private void helperInsert(KeyValuePair item, Object[] t, int size) {

    // Get the index from HashCode
    int index = item.hashCode % size;

    // CHECK FOR NEGATIVE index
    if (index < 0)
      index = index * -1;

    // If null add array list here
    if (t[index] == null)
      t[index] = new ArrayList<KeyValuePair>();

    @SuppressWarnings("unchecked")
    ArrayList<KeyValuePair> array = (ArrayList<KeyValuePair>) t[index];

    // Add the item
    array.add(item);

    // Increment numKeys
    this.numKeys += 1;

  }

  /**
   * Private method for resizing table when LoadFactor > LoadFactorThreshold
   */
  @SuppressWarnings("unchecked")
  private void resizeTable() {

    // Make new Array for Objects with more capacity
    Object[] newTable = new Object[(2 * this.tableSize + 1)];

    // Reset numKeys
    this.numKeys = 0;

    // Loop through old table
    for (int i = 0; i < this.tableSize; i++) {
      // Grab array at index
      if (this.table[i] != null) {

        ArrayList<KeyValuePair> array = (ArrayList<KeyValuePair>) this.table[i];
        // Go through current array items
        for (int c = 0; c < array.size(); c++) {
          // Add to new table
          helperInsert(array.get(c), newTable, this.tableSize + 10);

        }
      }
    } // Outer for loop

    // Reassing table
    this.table = newTable;
    // Change size
    this.tableSize = (2 * this.tableSize + 1);

  }

  /*
   * Method for removing items from hashtable
   * 
   * @return boolean - true if removed succesfully, false otherwise (non-Javadoc)
   * 
   * @see DataStructureADT#remove(java.lang.Comparable)
   */
  @Override
  public boolean remove(K key) throws IllegalNullKeyException {
    // Check for null
    if (key == null)
      throw new IllegalNullKeyException();

    // Get index
    int index = key.hashCode() % this.tableSize;
    // Check for negative index
    if (index < 0)
      index = index * -1;
    // Check for array at index
    if (this.table[index] != null) {
      // Grab array at index and remove item
      @SuppressWarnings("unchecked")
      ArrayList<KeyValuePair> array = (ArrayList<KeyValuePair>) this.table[index];
      for (int i = 0; i < array.size(); i++) {
        if (array.get(i).key.equals(key)) {
          // Remove KeyValuePair and adjust field members
          array.remove(i);
          // Decrease numKeys field
          this.numKeys--;
          return true;
        }
      }
    }
    // If key wasnt found
    return false;
  }

  /*
   * Method for grabbing values associated with key in hashtable (non-Javadoc)
   * 
   * @param K key - key for finding data
   * 
   * @return V value - value associated with Key
   * 
   * @see DataStructureADT#get(java.lang.Comparable)
   */
  @Override
  public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
    // Check for null
    if (key == null)
      throw new IllegalNullKeyException();

    // Calculate index
    int index = (key.hashCode()) % this.tableSize;
    // Check for negative index
    if (index < 0)
      index = index * -1;
    // If not null, grab array
    if (this.table[index] != null) {
      @SuppressWarnings("unchecked")
      ArrayList<KeyValuePair> array = (ArrayList<KeyValuePair>) this.table[index];
      // Loop through array and find item
      for (int i = 0; i < array.size(); i++) {
        // If equal, return the value
        KeyValuePair item = array.get(i);
        if (item.key.equals(key))
          return item.value;
      } // If key is not found
    }

    throw new KeyNotFoundException();

  }

  /*
   * Getter for numKeys
   * 
   * @return int - number of keys in table (non-Javadoc)
   * 
   * @see DataStructureADT#numKeys()'
   * 
   * 
   */
  @Override
  public int numKeys() {
    return this.numKeys;
  }

  /*
   * Getter for loadFactorThreshold of HashTable
   * 
   * @return double - load factor threshold of hashtable (non-Javadoc)
   * 
   * @see HashTableADT#getLoadFactorThreshold()
   */
  @Override
  public double getLoadFactorThreshold() {
    return this.loadFactorThreshold;
  }

  /**
   * Calculates load factor into hashtable object field member. Calls resize if
   * neccessary.
   */
  private void calculateLoadFactor() {
    // Calculates Load factor
    this.loadFactor = (double) this.numKeys / (double) this.tableSize;
    // Checks if resizing is needed
    if (this.loadFactor >= this.loadFactorThreshold) {
      // After resizing, it recalculates new load factor
      this.resizeTable();
      this.loadFactor = (double) this.numKeys / (double) this.tableSize;
    }

  }

  /*
   * Getter for current load factor
   * 
   * @return double - current load factor of hashtable (non-Javadoc)
   * 
   * @see HashTableADT#getLoadFactor()
   */
  @Override
  public double getLoadFactor() {
    return this.loadFactor;
  }

  /*
   * Getter for table capacity
   * 
   * @return int - table size (non-Javadoc)
   * 
   * @see HashTableADT#getCapacity()
   */
  @Override
  public int getCapacity() {
    return this.tableSize;
  }

  /*
   * Returns collision resolution number of 4, for array of arrays
   * 
   * @return int - collision scheme (non-Javadoc)
   * 
   * @see HashTableADT#getCollisionResolution()
   */
  @Override
  public int getCollisionResolution() {
    return 4;
  }

  /**
   * Prints out objects in array
   */
  private void visualizer() {
    for (int i = 0; i < this.tableSize; i++) {
      if (table[i] != null) {
        @SuppressWarnings("unchecked")
        ArrayList<KeyValuePair> array = (ArrayList<KeyValuePair>) this.table[i];
        for (int c = 0; c < array.size(); c++) {
          KeyValuePair item = array.get(c);
          String message = "Item at table array " + i + " in arraylist index " + c + " has "
                  + item.key + " and " + item.value;
          System.out.println(message);

        }
      }
    }
  }

  public static void main(String[] args) {
    Hashtable<Integer, Integer> test = new Hashtable<Integer, Integer>();
  }

}
