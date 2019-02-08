//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: p1_Implement and Test DataStructureADT
// Files:  DS_My.java, DataStructureADTTEST.java, DataStructureADT.java        
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
/**
 * Personal implementation of DataStructureADT. Uses an Pair[] array to store
 * Pair objects which themselves have hey and value private fields.
 * 
 * @author emanuelburgos
 *
 */
public class DS_My implements DataStructureADT {

  /**
   * Class for pair that has key and value. Is used to store this format of data
   * that goes into DS_MY
   * 
   * @author emanuelburgos
   *
   */
  private class Pair {
    private Comparable key; // Key of pair
    private Object value; // Value associated

    /**
     * Constructor
     * 
     * @param k - Key
     * @param v - value
     */
    private Pair(Comparable k, Object v) {
      // St field values
      this.key = k;
      this.value = v;
    }
  }

  private Pair[] ls; // Array of Pair objects
  private int size; // Number of pairs in ls
  private int CAPACITY; // Capacity for pairs

  /**
   * Constructor for DS_My
   */
  public DS_My() {
    // Set field variables
    this.CAPACITY = 500;
    this.ls = new Pair[CAPACITY];
    this.size = 0;
  }

  /*
   * Method for inserting pair objects into Pair[]. Checks for null key and throws
   * IllegalArgumentException if so. Otherwise, extends Pair[] in ls to more
   * capacity if full
   * 
   * @see DataStructureADT#insert(java.lang.Comparable, java.lang.Object)
   * 
   * @return void
   */
  @Override
  public void insert(Comparable k, Object v) {

    if (k == null) { // Check for null key
      throw new IllegalArgumentException("null key");
    }

    // If size 0, just add pair
    else if (this.size == 0) {
      ls[this.size] = new Pair(k, v);
      this.size++;
      return;
    }
    // If # of items reached capacity
    else if (this.CAPACITY == this.size) {
      Pair[] newLs = new Pair[this.CAPACITY + 100];

      // Deep copy of previous Pair[]
      for (int i = 0; i < this.size; i++) {
        if (ls[i].key.equals(k)) {
          throw new RuntimeException("duplicate key");
        }
        newLs[i] = this.ls[i]; // Change this.ls value
      }
      this.ls = newLs; // Change field to new Pair[]
      this.ls[size] = new Pair(k, v); // Add the Pair
      this.CAPACITY += 100; // Increase capacity
      size++;
      return;

    } else {
      // Loop to check for duplicate exception
      for (int i = 0; i < this.size; i++) {
        if (ls[i].key.equals(k)) {
          throw new RuntimeException("duplicate key");
        }
      }

      // Add pair to array
      ls[this.size] = new Pair(k, v);
      this.size++;
    }

  }

  /*
   * Method for removing Pair objects from the array
   * 
   * @see DataStructureADT#remove(java.lang.Comparable)
   */
  @Override
  public boolean remove(Comparable k) {

    boolean found = false; // Boolean for found key

    // check is null
    if (k == null) {
      throw new IllegalArgumentException("null key");
    }

    else {
      // Temporary array to hold Pairs
      Pair[] newList = new Pair[this.CAPACITY];

      // Loop over Pairs
      for (int i = 0; i < this.size; i++) {

        // If found the one to remove...
        if (ls[i].key.equals(k)) {

          found = true; // Change to true
          this.size--; // Decrease size

          // Inner loop to skip item
          for (int j = i; j < this.size; j++) {
            newList[j] = ls[j + 1];
          }

          // Change value of this.ls to reference newList
          this.ls = newList;

          return found;

        } else {
          // If not yet found, add to newList
          newList[i] = ls[i];
        }
      }

      // Same thing in reassigning
      this.ls = newList;
      return found;
    }
  }

  /*
   * Method for checking if key is found in array
   * 
   * @see DataStructureADT#contains(java.lang.Comparable)
   * 
   * @return boolean - true if found, false otherwise
   */
  @Override
  public boolean contains(Comparable k) {

    // Loop over items and check
    for (int i = 0; i < this.size; i++) {
      if (ls[i].key.equals(k)) {
        return true;
      }
    }

    // Return false if not found
    return false;
  }

  /*
   * Method for retrieving the value associated with given key from array.
   * 
   * @see DataStructureADT#get(java.lang.Comparable) return Object - value
   * associated with key
   */
  @Override
  public Object get(Comparable k) {

    // Check if null
    if (k == null) {
      throw new IllegalArgumentException("null key");
    }

    else {

      // Loop to find key
      for (int i = 0; i < this.size; i++) {
        if (ls[i].key.equals(k)) {
          return ls[i].value; // Return key value if found
        }
      }
      return null; // Null if not found
    }
  }

  /*
   * Returns number of items in array
   * 
   * @see DataStructureADT#size()
   * 
   * @return int - number of pairs in array
   */
  @Override
  public int size() {
    return this.size; // Returns value in size field
  }

}
