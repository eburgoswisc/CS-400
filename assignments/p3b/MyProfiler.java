/**
 * Filename: MyProfiler.java Project: p3b-201901 
 * Authors: Emanuel Burgos
 * Course: CS 400, Lec 3
 *
 * Semester: Spring 2019 Course: CS400
 * 
 * Due Date: March 28, 10pm
 * 
 * Credits: None
 * 
 * Bugs: None 
 */

// Used as the data structure to test our hash table against
import java.util.TreeMap;

public class MyProfiler<K extends Comparable<K>, V> {

  HashTableADT<K, V> hashtable;
  TreeMap<K, V> treemap;

  /**
   * Constructor for myProfiler
   */
  public MyProfiler() {
    // Instantiate field members
    this.hashtable = new HashTable<K, V>(11, 0.75);
    this.treemap = new TreeMap<>();
  }

  /**
   * Method for inserting key-value pairs into data structures
   * 
   * @param key   - Comparable key
   * @param value - value associated with ket
   */
  public void insert(K key, V value) {
    try {
      // Insert into both ADT
      hashtable.insert(key, value);
      treemap.put(key, value);
      // MAy throw exceptions
    } catch (Exception e) {
      // Do nothing
    }
  }

  /**
   * Method for retrieving key-value pairs from data structures
   * 
   * @param key - Comparable key
   */
  public void retrieve(K key) {
    try {
      // Retrieve with key
      hashtable.get(key);
      treemap.get(key);
      // Catch exceptions by retrieving
    } catch (Exception e) {
      // Do nothing
    }
  }

  /**
   * Method for removing key-value pairs from data structures
   * 
   * @param key - Comparable key
   */
  public void remove(K key) {
    try {
      // Call remove methods of ADT's
      hashtable.remove(key);
      treemap.remove(key);
      // Catch exceptions
    } catch (Exception e) {
      // Do nothing
    }
  }

  /**
   * Main method for class
   * 
   * @param args - command line arguements
   */
  public static void main(String[] args) {
    try {
      // Grab first argument from command line
      int numElements = Integer.parseInt(args[0]);
      
      // Initiate profiler using integer types for key and value
      MyProfiler<Integer, Integer> myProf = new MyProfiler<Integer, Integer>();
      
      // Insert
      for (int i = 0; i < numElements; i++)
        myProf.insert(i, i);
      // Retrieve items
      for (int i = 0; i < numElements; i++)
        myProf.retrieve(i);
      // Remove items
      for (int i = 0; i < numElements; i++)
        myProf.remove(i);

      // Report how many items were retrieved
      String msg = String.format("Inserted, retreived, and removed %d (key,value) pairs", numElements);
      System.out.println(msg);
      // Catch any exception and print help message
    } catch (Exception e) {
      System.out.println("Usage: java MyProfiler <number_of_elements>");
      System.exit(1);
    }
  }
}
