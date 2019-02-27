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
// Online Sources: https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/AVLTreeST.java.html
//                  - Looking how to compact the leftRight or viceversa algorithm. I had
//                    written out a way to first rotate the child node and then the parent 
//                    by just checking bf but did not know the control flow. This helped.
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
// A BST search tree that maintains its balance using AVL rotations.

/**
 * Class for making an AVL tree. It extends behavior from BST but adds balancing
 * methods. As you insert, tree will rebalance itself.
 * 
 * @author emanuelburgos
 *
 * @param <K> key - key for data pair
 * @param <V> value - value asigned to key in node
 */
public class AVL<K extends Comparable<K>, V> extends BST<K, V> {

  /*
   * Method for inserting into tree
   * 
   * @see BST#insert(java.lang.Comparable, java.lang.Object)
   */
  @Override
  public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {
    // Check if key is null
    if (key == null)
      throw new IllegalNullKeyException();
    // Check for empty root
    if (this.root == null) {
      // Increment numKeys to keep track
      this.numKeys += 1;
      // assign new node to root if null
      this.root = new BSTNode<K, V>(key, value);
      return;
    }
    // Check for Exception cases
    if (contains(key))
      // If not found throw exception
      throw new DuplicateKeyException();
    // If root is not null, call recursive insert to find correct spot
    this.root = helperInsert(this.root, key, value);
    // Always increment numKeys
    this.numKeys += 1;
  }

  /**
   * This method is meant to be called recursively to help insert nodes into AVL
   * tree. Using recursive method I will be able to also check for balance factors
   * and rotate as neccessary. It has control statements for doing leftRightRotate
   * or rightLeftRotate too
   *
   * @param K key - key for pair
   * @param V Value - vlaue associated with key
   * @return BSTNode<K,V> node - returns the root node of tree
   */
  protected BSTNode<K, V> helperInsert(BSTNode<K, V> node, K key, V value) {
    // Comparison with node
    int cmp = node.key.compareTo(key);
    // Go right
    if (cmp < 0) {
      // Check if null
      if (node.right != null) {
        // Recursive call to insert
        node.right = helperInsert(node.right, key, value);
      } else {
        // If null then assign it there
        node.right = new BSTNode<K, V>(key, value);
        return node;
      }

    }
    // Same thing as above but going left is key is less
    else if (cmp > 0) {
      if (node.left != null) {
        node.left = helperInsert(node.left, key, value);
      } else {
        node.left = new BSTNode<K, V>(key, value);
        return node;
      }
    }
    // If the current node is null, assign new node
    // This code is kind of defensive, its uneccessary.
    else if (node == null)
      return new BSTNode<K, V>(key, value);

    // Check if current node needs a rotate. If bf is less than -1, left rotate
    if (balanceFactor(node) < -1) {
      // Check if its right node has bf above 1
      // Why? Because it means that this node is causing the imbalance from right
      // subtree
      // Rotate to right
      if (balanceFactor(node.right) > 0) {
        node.right = rightRotate(node.right);
        // Reassign height
        node.right.height = getHeight(node.right);
      }
      // Do the rotate on current node still
      node = leftRotate(node);
    }

    // Check if current node needs a rotate. If bf is more than 1, right rotate
    if (balanceFactor(node) > 1) {
      // Check if its left node has bf below -1
      // Why? Because it means that this node is causing the imbalance from left
      // subtree
      // Rotate to Left
      if (balanceFactor(node.left) < 0) {
        node.left = leftRotate(node.left);
        // Reassign height
        node.left.height = getHeight(node.left);
      }
      // Do original rotate
      node = rightRotate(node);
    }

    // Re-calculate height
    node.height = getHeight(node);
    return node;
  }

  /**
   * Method for left rotating a subtree.
   * 
   * @param node - BSTNode that needs to be balanced
   * @return rightChild - return the rotated node
   */
  public BSTNode<K, V> leftRotate(BSTNode<K, V> node) {
    // Grab right child
    BSTNode<K, V> rightChild = node.right;
    // Split left subtree of right child, even if null just do it.
    BSTNode<K, V> leftSubTreeRightChild = rightChild.left;
    // Give the left subtree to the right of node
    node.right = leftSubTreeRightChild;
    // Promote the rightChild by giving current node to left of Rchild
    rightChild.left = node;
    // Return it
    return rightChild;
  }

  /**
   * Method for right rotating a subtree
   * 
   * @param node - BSTNode that needs to be balanced
   * @return node - return balanced node
   */
  public BSTNode<K, V> rightRotate(BSTNode<K, V> node) {
    // Grab left child of node
    BSTNode<K, V> leftChild = node.left;
    // Split right subtree of child node
    BSTNode<K, V> rightSubTreeLeftChild = leftChild.right;

    // Set current node.left to the split subtree
    node.left = rightSubTreeLeftChild;

    // Promote child by giving node to its right
    leftChild.right = node;
    // Return balanced node with child as pseudoroot
    return leftChild;
  }

  /**
   * Calculates balance factor for given node. Follows bf = leftNodeHeight -
   * rightNodeHeight
   * 
   * @param node - given node for balance factor
   * @return int - balance factor
   */
  protected int balanceFactor(BSTNode<K, V> node) {
    // If null, return 0
    if (node == null)
      return 0;
    // Start with bf at 0
    int bf = 0;
    // Check if both child nodes are not null
    if (node.left != null && node.right != null) {
      // If not, call get height on both
      bf = getHeight(node.left) - getHeight(node.right);
      // If onlt one child is null
    } else if (node.left != null && node.right == null) {
      bf = getHeight(node.left);
    }
    // If right child is not null, call height on that
    else if (node.right != null && node.left == null) {
      bf = -1 * (getHeight(node.right));
    }
    // Sets attribute of balance factor
    node.balanceFactor = bf;
    return bf;
  }

}
