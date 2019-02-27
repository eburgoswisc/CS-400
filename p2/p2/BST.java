
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
// Online Sources: https://www.journaldev.com/23059/level-order-traversal-breadth-first-tree
//                  - Got an idea of the algorithm for level order traversal, 
//                    then I wrote my own implementation.
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.util.ArrayList;
import java.util.List;

public class BST<K extends Comparable<K>, V> implements BSTADT<K, V> {

  // Tip: Use protected fields so that they may be inherited by AVL
  protected BSTNode<K, V> root;
  protected int numKeys; // number of keys in BST

  // Must have a public, default no-arg constructor
  public BST() {
    this.numKeys = 0;
    this.root = null;
  }

  /*
   * (non-Javadoc)
   * 
   * VLR
   */
  @Override
  public List<K> getPreOrderTraversal() {
    // If root is null, return empty list
    if (this.root == null)
      return new ArrayList<K>();

    // Create list object
    ArrayList<K> ls = new ArrayList<K>();
    // Call recursive
    helperGetPreOrderTraversal(ls, this.root);
    return ls;
  }

  /**
   * Recursive method for performing PreOrder traversal. Traversal is V,L,R
   * 
   * @param ls      - list object to hold keys
   * @param current - node being recursed
   */
  private void helperGetPreOrderTraversal(ArrayList<K> ls, BSTNode<K, V> current) {
    // base case
    if (current == null)
      return;
    // Add current to list
    ls.add(current.key);
    // Visit left
    if (current.left != null)
      helperGetPreOrderTraversal(ls, current.left);
    // Visit right
    if (current.right != null)
      helperGetPreOrderTraversal(ls, current.right);
    return;
  }

  /*
   * (non-Javadoc)
   * 
   * @see SearchTreeADT#getPostOrderTraversal()
   */
  @Override
  public List<K> getPostOrderTraversal() {
    // If root is null, return empty list
    if (this.root == null)
      return new ArrayList<K>();

    // List to hold keys
    ArrayList<K> ls = new ArrayList<K>();
    // Call helper method
    helperGetPostOrderTraversal(ls, this.root);
    return ls;
  }

  /**
   * Recursive method for PostOrder Traversal. Traversal is L,R,V
   * 
   * @param ls      - list to hold keys
   * @param current - node being recursed
   */
  private void helperGetPostOrderTraversal(ArrayList<K> ls, BSTNode<K, V> current) {
    // Visit Left
    if (current.left != null)
      helperGetPostOrderTraversal(ls, current.left);
    // visit right
    if (current.right != null)
      helperGetPostOrderTraversal(ls, current.right);
    // Visit once left and right are null
    ls.add(current.key);
    return;

  }

  /*
   * (non-Javadoc)
   * 
   * 
   */
  @Override
  public List<K> getLevelOrderTraversal() {
    // If root is null, return empty list
    if (this.root == null)
      return new ArrayList<K>();

    // List for holding keys
    ArrayList<K> ls = new ArrayList<K>();
    ls.add(this.root.key);
    // Call helper method
    helperGetLevelOrderTraversal(ls);
    return ls;

  }

  /**
   * Helper method for level order traversal. Gets keys from all levels in order.
   * 
   * @param ls - list to hold keys
   */
  private void helperGetLevelOrderTraversal(ArrayList<K> ls) {
    // index for going through key list
    int i = 0;
    // Temp list to hold NODES< cannot use given ls in parameter
    ArrayList<BSTNode<K, V>> tempLs = new ArrayList<BSTNode<K, V>>();
    // Add root to temp
    tempLs.add(this.root);

    // While temp size is not equal to numKeys
    // Go through tempLs
    while (tempLs.size() != this.numKeys) {
      // Grab node
      BSTNode<K, V> tempNode = tempLs.get(i);
      // Check for children, if so add them to ls
      if (tempNode.left != null) {
        tempLs.add(tempNode.left);
        ls.add(tempNode.left.key);
      }
      if (tempNode.right != null) {
        tempLs.add(tempNode.right);
        ls.add(tempNode.right.key);
      }
      // Increment index to get other node and add their children
      i += 1;

    }

  }

  /*
   * (non-Javadoc)
   * 
   * L, V, R
   */
  @Override
  public List<K> getInOrderTraversal() {
    // If root is null, return empty list
    if (this.root == null)
      return new ArrayList<K>();

    // List for holding keys
    ArrayList<K> ls = new ArrayList<K>();
    // Call helper method
    helperGetInOrderTraversal(ls, this.root);
    return ls;
  }

  /**
   * Helper method for performing inOrder traversal. Traversal is L, V, R
   * 
   * @param ls      - list to hold keys
   * @param current - node being recursed
   */
  private void helperGetInOrderTraversal(ArrayList<K> ls, BSTNode<K, V> current) {
    // Visit Left
    if (current.left != null)
      helperGetInOrderTraversal(ls, current.left);
    // Add current
    ls.add(current.key);
    // Visit Right
    if (current.right != null)
      helperGetInOrderTraversal(ls, current.right);
    return;

  }

  /*
   * (non-Javadoc)
   * 
   * @see DataStructureADT#insert(java.lang.Comparable, java.lang.Object)
   */
  @Override
  public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {
    // If key is null throw exception
    if (key == null)
      throw new IllegalNullKeyException();
    // Check for empty root
    if (this.root == null) {
      this.root = new BSTNode<K, V>(key, value);
      numKeys += 1;
      return;
    }
    // Check for Exception cases
    if (contains(key))
      throw new DuplicateKeyException();

    // Start loop for finding null child
    BSTNode<K, V> current = this.root;
    // While current node is not null
    while (current != null) {
      // Go right if key is bigger
      if (current.key.compareTo(key) < 0) {
        // If null then add
        if (current.right == null) {
          current.right = new BSTNode<K, V>(key, value);
          current.right.height += 1;
          this.numKeys += 1;
          return;
        }
        // Move to right if not null
        current.height += 1;
        current = current.right;
      }

      // Go left is key is smaller
      else if (current.key.compareTo(key) > 0) {
        // If null the add
        if (current.left == null) {
          current.left = new BSTNode<K, V>(key, value);
          current.left.height += 1;
          this.numKeys += 1;
          return;
        }

        // Move to left if not null
        current.height += 1;
        current = current.left;
      }
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see DataStructureADT#remove(java.lang.Comparable)
   */
  @Override
  public boolean remove(K key) throws IllegalNullKeyException, KeyNotFoundException {
    // Check if given key is null
    if (key == null)
      throw new IllegalNullKeyException();
    // First check if it contains the key
    else if (!contains(key))
      throw new KeyNotFoundException();

    this.root = helperRemove(this.root, key);
    this.numKeys -= 1;
    return true;
  }

  /**
   * Helper method for removing nodes. Its meant to compare between recursive
   * methods and using loops (contrast to insert)
   * 
   * @param node - node being looked at
   * @param key  - key of node to remove
   * @return node - root node that will have node removed
   */
  private BSTNode<K, V> helperRemove(BSTNode<K, V> node, K key) {
    // Comparison result
    int cmp = node.key.compareTo(key);
    // Remove node
    // Have different control statements in case node to remove has children.
    if (key.equals(node.key)) {
      // If a leaf, no children
      if (numChildren(node) == 0) {
        return null;
      }
      // 1 child
      else if (numChildren(node) == 1) {
        // Check if right is nul
        if (node.right != null)
          return node.right;
        // If left is not null remove that
        else
          return node.left;
      }

      // If 2 children nodes
      if (numChildren(node) == 2) {
        // Find largest of left subtree
        BSTNode<K, V> current = node.right;
        // If largest of left subtree is right child of node
        if (current.left == null) {
          current.left = node.left;
          return current;
        } else {
          //
          BSTNode<K, V> prev = null;
          while (current != null) {
            if (current.left != null) {
              prev = current;
              current = current.left;
            } else if (current.left == null)
              break;
          }
          // Set new child in place of deleted node
          prev.left = null;
          current.right = prev;
          current.left = node.left;
          return current;
        }

      }
    }
    // go right
    else if (cmp < 0) {
      node.right = helperRemove(node.right, key);
      return node;
    }
    // Go left
    else {
      node.left = helperRemove(node.left, key);
      return node;
    }
    return node;
  }

  /**
   * Returns number of children for a given node
   * 
   * @param node - given node
   * @return int - number of children nodes
   */
  private int numChildren(BSTNode<K, V> node) {
    // If both not null
    if (node.left != null && node.right != null)
      return 2;
    // If both null
    if (node.left == null && node.right == null)
      return 0;
    // If one null, and other not null
    else
      return 1;
  }

  /*
   * (non-Javadoc)
   * 
   * @see DataStructureADT#get(java.lang.Comparable)
   */
  @Override
  public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
    // Check for null key
    if (key == null)
      throw new IllegalNullKeyException();
    // Check if key is not in tree
    else if (!this.contains(key))
      throw new KeyNotFoundException();

    // Start with root
    BSTNode<K, V> current = this.root;
    while (current != null) {
      // If equal retrn value
      if (current.key == key)
        return current.value;
      // If key is bigger, go right
      else if (current.key.compareTo(key) < 0)
        current = current.right;
      // Otherwise, go left
      else if (current.key.compareTo(key) > 0)
        current = current.left;
    }
    return null;

  }

  /*
   * (non-Javadoc)
   * 
   * @see DataStructureADT#contains(java.lang.Comparable)
   */
  @Override
  public boolean contains(K key) throws IllegalNullKeyException {
    // Check for null key
    if (key == null)
      throw new IllegalNullKeyException();
    // Start with root
    BSTNode<K, V> current = this.root;
    // Loop through tree
    while (current != null) {
      // If found return true
      if (current.key.compareTo(key) == 0)
        return true;
      // If key is bigger, go right
      else if (current.key.compareTo(key) < 0)
        current = current.right;
      // If less, go left
      else if (current.key.compareTo(key) > 0)
        current = current.left;
    }
    // If not found, return false
    return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see DataStructureADT#numKeys()
   */
  @Override
  public int numKeys() {
    // Return field numKeys
    return this.numKeys;
  }

  /*
   * (non-Javadoc)
   * 
   * @see BSTADT#getKeyAtRoot()
   */
  @Override
  public K getKeyAtRoot() {
    // If root null, return null
    if (this.root == null)
      return null;
    // Otherwise getroot key
    return this.root.key;
  }

  /*
   * (non-Javadoc)
   * 
   * @see BSTADT#getKeyOfLeftChildOf(java.lang.Comparable)
   */
  @Override
  public K getKeyOfLeftChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
    // Check for null key
    if (key == null)
      throw new IllegalNullKeyException();
    // Check for key in tree
    else if (!contains(key))
      throw new KeyNotFoundException();

    // Start with root
    BSTNode<K, V> current = this.root;
    K foundKey = null;

    // Loop through tree
    while (current != null) {
      // If found , change value of foundKey and return it
      if (current.key.compareTo(key) == 0) {
        foundKey = current.left.key;
        return foundKey;
        // If key bigger, go right
      } else if (current.key.compareTo(key) < 0)
        current = current.right;
      // Otherwise go left
      else if (current.key.compareTo(key) > 0)
        current = current.left;

    }
    // Might return a null if node does not have left child
    return foundKey;
  }

  /*
   * (non-Javadoc)
   * 
   * @see BSTADT#getKeyOfRightChildOf(java.lang.Comparable)
   */
  @Override
  public K getKeyOfRightChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
    // Check for null key
    if (key == null)
      throw new IllegalNullKeyException();
    // Check key in tree
    else if (!contains(key))
      throw new KeyNotFoundException();
    // Start with root
    BSTNode<K, V> current = this.root;
    K foundKey = null;
    // Loop through tree
    while (current != null) {
      // If found, change foundKey value and return it
      if (current.key.compareTo(key) == 0) {
        foundKey = current.right.key;
        return foundKey;
        // If key is bigger, go right
      } else if (current.key.compareTo(key) < 0)
        current = current.right;
      // Otherwise go left
      else if (current.key.compareTo(key) > 0)
        current = current.left;
    }
    // If node does not have right child, return null
    return foundKey;
  }

  /*
   * (non-Javadoc)
   * 
   * @see BSTADT#getHeight()
   */
  @Override
  public int getHeight() {
    int left = 1, right = 1;
    // If tree is empty
    if (root == null) {
      return 0;
    }
    // If root children are null
    else if (root.left == null && root.right == null) {
      return 1;
    } else {
      // Call recursive method
      return getHeight(this.root);
    }
  }

  /*
   * This method gets height of given node. Its package level access for AVL
   * class.
   * 
   * @return int - height of deppest subtree
   */
  protected int getHeight(BSTNode<K, V> current) {
    // Set left,right height to 0
    int left = 0, right = 0;
    // If left not null, make recursive call
    if (current.left != null) {
      left = getHeight(current.left);
    }
    // If right not null, make recursive call
    if (current.right != null) {
      right = getHeight(current.right);
    }
    // Basically if current is null, return max height for both sides
    return 1 + Math.max(left, right);
  }

}
