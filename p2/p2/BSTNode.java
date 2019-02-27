//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: p2 Assignment
// Files: AVL.java, AVLTest.java, BST.java, BSTADT.java, BSTNode.java, 
//        BSTTest.java, DataStructureADT.java, DataStructureADTTest.java,
//        DuplicateKeyException.java, IllegalNullKeyException.java, 
//        KeyNotFoundException.java, SearchTreeADT.java
//
// Course: CS 400, Spring 2019
//
// Author: Emanuel Burgos
// Email:  eburgos@wisc.edu
// Lecturer's Name: Andy Khummel
//
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates, 
// strangers, and others do.  If you received no outside help from either type
//  of source, then please explicitly indicate NONE.
//
// Persons: None
// Online Sources: 
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

/**
 * Class for nodes of BST tree.
 * 
 * @author emanuelburgos
 *
 * @param <K> type for key
 * @param <V> type for value associated with key
 */
class BSTNode<K, V> {

  K key; // Key for pair
  V value; // Value paired with key
  BSTNode<K, V> left;
  BSTNode<K, V> right;
  int balanceFactor;
  int height;

  /**
   * Constructor for BSTNode given these parameters
   * 
   * @param key
   * @param value
   * @param leftChild
   * @param rightChild
   */
  BSTNode(K key, V value, BSTNode<K, V> leftChild, BSTNode<K, V> rightChild) {
    this.key = key;
    this.value = value;
    this.left = leftChild;
    this.right = rightChild;
    this.height = 0;
    this.balanceFactor = 0;
  }

  /**
   * Simpler constructor
   * 
   * @param key   - key for node
   * @param value - value associated with key
   */
  BSTNode(K key, V value) {
    this(key, value, null, null);
  }

  /**
   * Getter for height
   * 
   * @return int - height
   */
  protected int getHeight() {
    return this.height;
  }

  /**
   * Getter for Balance Factor
   * 
   * @return int - balance factor
   */
  protected int getBalanceFactor() {
    return this.balanceFactor;
  }

}
