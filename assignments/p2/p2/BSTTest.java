
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

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BSTTest extends DataStructureADTTest {

  BST<String, String> bst;
  BST<Integer, String> bst2;

  /**
   * @throws java.lang.Exception
   */
  @BeforeEach
  void setUp() throws Exception {
    // The setup must initialize this class's instances
    // and the super class instances.
    // Because of the inheritance between the interfaces and classes,
    // we can do this by calling createInstance() and casting to the desired type
    // and assigning that same object reference to the super-class fields.
    dataStructureInstance = bst = createInstance();
    dataStructureInstance2 = bst2 = createInstance2();
  }

  /**
   * @throws java.lang.Exception
   */
  @AfterEach
  void tearDown() throws Exception {
    dataStructureInstance = bst = null;
    dataStructureInstance2 = bst2 = null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see DataStructureADTTest#createInstance()
   */
  @Override
  protected BST<String, String> createInstance() {
    return new BST<String, String>();
  }

  /*
   * (non-Javadoc)
   * 
   * @see DataStructureADTTest#createInstance2()
   */
  @Override
  protected BST<Integer, String> createInstance2() {
    return new BST<Integer, String>();
  }

  /**
   * Test that empty trees still produce a valid but empty traversal list for each
   * of the four traversal orders.
   */
  @Test
  void testBST_001_empty_traversal_orders() {
    try {

      // Make empty list
      List<String> expectedOrder = new ArrayList<String>();

      // Get the actual traversal order lists for each type
      List<String> inOrder = bst.getInOrderTraversal();
      List<String> preOrder = bst.getPreOrderTraversal();
      List<String> postOrder = bst.getPostOrderTraversal();
      List<String> levelOrder = bst.getLevelOrderTraversal();

      // Print each traversal
      System.out.println("   EXPECTED: " + expectedOrder);
      System.out.println("   In Order: " + inOrder);
      System.out.println("  Pre Order: " + preOrder);
      System.out.println(" Post Order: " + postOrder);
      System.out.println("Level Order: " + levelOrder);

      // Check them with expected output
      assertEquals(expectedOrder, inOrder);
      assertEquals(expectedOrder, preOrder);
      assertEquals(expectedOrder, postOrder);
      assertEquals(expectedOrder, levelOrder);

      // In case of weird exception
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 002: " + e.getMessage());
    }

  }

  /**
   * Test that trees with one key,value pair produce a valid traversal lists for
   * each of the four traversal orders.
   */
  @Test
  void testBST_002_check_traversals_after_insert_one() {

    try {
      // Expectd output with one key
      List<Integer> expectedOrder = new ArrayList<Integer>();
      expectedOrder.add(10);
      bst2.insert(10, "ten");
      // Check size is correct
      if (bst2.numKeys() != 1)
        fail("added 10, size should be 1, but was " + bst2.numKeys());

      // Get traversal orders
      List<Integer> inOrder = bst2.getInOrderTraversal();
      List<Integer> preOrder = bst2.getPreOrderTraversal();
      List<Integer> postOrder = bst2.getPostOrderTraversal();
      List<Integer> levelOrder = bst2.getLevelOrderTraversal();

      // Print traversals that were returned
      System.out.println("   EXPECTED: " + expectedOrder);
      System.out.println("   In Order: " + inOrder);
      System.out.println("  Pre Order: " + preOrder);
      System.out.println(" Post Order: " + postOrder);
      System.out.println("Level Order: " + levelOrder);

      // Check that they are correct
      assertEquals(expectedOrder, inOrder);
      assertEquals(expectedOrder, preOrder);
      assertEquals(expectedOrder, postOrder);
      assertEquals(expectedOrder, levelOrder);

      // In case of weird exception
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 003: " + e.getMessage());
    }

  }

  /**
   * Test that the in-order traversal order is correct if the items are entered in
   * a way that creates a balanced BST
   * 
   * Insert order: 20-10-30 In-Order traversal order: 10-20-30
   */
  @Test
  void testBST_003_check_inOrder_for_balanced_insert_order() {
    // insert 20-10-30 BALANCED
    try {
      bst2.insert(20, "1st key inserted");
      bst2.insert(10, "2nd key inserted");
      bst2.insert(30, "3rd key inserted");

      // expected inOrder 10 20 30
      List<Integer> expectedOrder = new ArrayList<Integer>();
      expectedOrder.add(10); // L
      expectedOrder.add(20); // V
      expectedOrder.add(30); // R

      // GET IN-ORDER and check
      List<Integer> actualOrder = bst2.getInOrderTraversal();
      assertEquals(expectedOrder, actualOrder);
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 004: " + e.getMessage());
    }
  }

  /**
   * Test that the pre-order traversal order is correct if the items are entered
   * in a way that creates a balanced BST
   * 
   * Insert order: 20-10-30 Pre-Order traversal order: 20-10-30
   */
  @Test
  void testBST_004_check_preOrder_for_balanced_insert_order() {

    try {
      // Insert balanced nodes
      bst2.insert(20, null);
      bst2.insert(10, null);
      bst2.insert(30, null);

      // Check preorder is corrrect
      assertEquals(bst2.getPreOrderTraversal(), new ArrayList<Integer>(Arrays.asList(20, 10, 30)));

    } catch (Exception e) {
      fail("Should not throw exception");
    }

  }

  /**
   * Test that the post-order traversal order is correct if the items are entered
   * in a way that creates a balanced BST
   * 
   * Insert order: 20-10-30 Post-Order traversal order: 10-30-20
   */
  @Test
  void testBST_005_check_postOrder_for_balanced_insert_order() {

    try {
      // make expected output
      ArrayList<Integer> ls = new ArrayList<Integer>(Arrays.asList(10, 30, 20));
      // Insert integers
      bst2.insert(20, null);
      bst2.insert(10, null);
      bst2.insert(30, null);
      // Check that it equals
      assertEquals(bst2.getPostOrderTraversal(), ls);

    } catch (Exception e) {
      fail("Something went wrong");
    }

  }

  /**
   * Test that the level-order traversal order is correct if the items are entered
   * in a way that creates a balanced BST
   * 
   * Insert order: 20-10-30 Level-Order traversal order: 20-10-30
   * 
   */
  @Test
  void testBST_006_check_levelOrder_for_balanced_insert_order() {
    try {
      // Expected output
      ArrayList<Integer> ls = new ArrayList<Integer>(Arrays.asList(20, 10, 30));
      // Insert integers
      bst2.insert(20, null);
      bst2.insert(10, null);
      bst2.insert(30, null);

      assertEquals(ls, bst2.getLevelOrderTraversal());
    } catch (Exception e) {
      fail("Something went wrong");
    }

  }

  /**
   * Test that the in-order traversal order is correct if the items are entered in
   * a way that creates an un-balanced BST
   * 
   * Insert order: 10-20-30 In-Order traversal order: 10-20-30
   * 
   */
  @Test
  void testBST_007_check_inOrder_for_not_balanced_insert_order() {
    try {
      // expected output
      ArrayList<Integer> ls = new ArrayList<Integer>(Arrays.asList(10, 20, 30));
      // Insert integers
      bst2.insert(10, null);
      bst2.insert(20, null);
      bst2.insert(30, null);
      // Check that output is correct
      assertEquals(ls, bst2.getInOrderTraversal());
    } catch (Exception e) {
      fail("Something went wrong");
    }

  }

  /**
   * Test that the pre-order traversal order is correct if the items are entered
   * in a way that creates an un-balanced BST
   * 
   * Insert order: 10-20-30 Pre-Order traversal order: 10-20-30
   * 
   * @throws DuplicateKeyException
   * @throws IllegalNullKeyException
   */
  @Test
  void testBST_008_check_preOrder_for_not_balanced_insert_order() {
    try {
      // expected output
      ArrayList<Integer> ls = new ArrayList<Integer>(Arrays.asList(10, 20, 30));
      // Insert Integers
      bst2.insert(10, null);
      bst2.insert(20, null);
      bst2.insert(30, null);
      // check that its correct output
      assertEquals(ls, bst2.getPreOrderTraversal());

    } catch (Exception e) {
      fail("Something went wrong");
    }

  }

  /**
   * Test that the post-order traversal order is correct if the items are entered
   * in a way that creates an un-balanced BST
   * 
   * Insert order: 10-20-30 Post-Order traversal order: 30-20-10
   * 
   */
  @Test
  void testBST_009_check_postOrder_for_not_balanced_insert_order() {
    try {
      // expected output
      ArrayList<Integer> ls = new ArrayList<Integer>(Arrays.asList(30, 20, 10));
      // Insert Integers
      bst2.insert(10, null);
      bst2.insert(20, null);
      bst2.insert(30, null);
      // check that its correct output
      assertEquals(ls, bst2.getPostOrderTraversal());
    } catch (Exception e) {
      fail("Something went wrong");
    }

  }

  /**
   * Test that the level-order traversal order is correct if the items are entered
   * in a way that creates an un-balanced BST
   * 
   * Insert order: 10-20-30 Level-Order traversal order: 10-20-30 (FIXED ON
   * 2/14/18)
   */
  @Test
  void testBST_010_check_levelOrder_for_not_balanced_insert_order() {
    try {
      // expected output
      ArrayList<Integer> ls = new ArrayList<Integer>(Arrays.asList(10, 20, 30));
      // Insert Integers
      bst2.insert(10, null);
      bst2.insert(20, null);
      bst2.insert(30, null);
      // check that its correct output
      assertEquals(ls, bst2.getLevelOrderTraversal());
    } catch (Exception e) {
      fail("Something went wrong");
    }

  }

  /**
   * Method tests for insert and then get methods
   */
  @Test
  void testBST_011_insert_get() {
    try {
      // <Integer, String>
      for (int i = 0; i < 5; i++) {
        // Insert a bunch of stuff
        this.bst2.insert(i, Integer.toString(i));
      }
      // Get 4 and check its value is correct
      if (!bst2.get(4).equals("4"))
        fail("Did not found correct node");
      // Get something not in tree
      bst2.get(5).equals("5"); // Not in tree

      // Throw fail if exception was not thrown
      fail(" Did not through KeyNotFoundException");

    } catch (KeyNotFoundException e) {
      // Nothing should happen cause it through exception
    } catch (Exception e) {
      fail("Something happened at insert");
    }
    return;
  }

  /**
   * Test insert and remove methods of BST class
   * 
   */
  @Test
  void testBST_012_insert_remove() {
    try {
      // Insert keys
      bst2.insert(5, "1");
      bst2.insert(10, "1");
      bst2.insert(3, "1");
      bst2.insert(2, "1");
      bst2.insert(4, "1");
      bst2.insert(9, "1");

      // Remove 10 should return true
      assertTrue(bst2.remove(10));

      // Removing 100, is not in tree, should return false
      assertFalse(bst2.remove(100));

    } catch (KeyNotFoundException e) {
      // Do nothing
    } catch (Exception e) {
      fail("Something worng happened");
    }
  }

  /**
   * Checks get Left Child/Right Child key method
   * 
   */
  @Test
  void testBST_013_getKey_leftChild_and_RightChild_and_KeyatRoot() {
    try {
      // Insert keys
      bst2.insert(5, "1");
      bst2.insert(10, "1");
      bst2.insert(3, "1");
      bst2.insert(2, "1");
      bst2.insert(4, "1");
      bst2.insert(9, "1");
      // Should return 9
      if (!(bst2.getKeyOfLeftChildOf(10) == 9)) {
        fail("Returned wrong key");
      }
      // Should return 4
      if (!(bst2.getKeyOfRightChildOf(3) == 4)) {
        fail("Returned wrong key");
      }
      // Check key at root
      if (!(bst2.getKeyAtRoot() == 5)) {
        fail("Returned wrong key at root");
      }
    } catch (Exception e) {
      fail("Should not throw error");
    }
  }

  /**
   * Method for testing removing a node with one child
   */
  @Test
  void testBST_014_remove_node_with_oneChildren() {
    try {
      // Insert nodes
      bst2.insert(10, null);
      bst2.insert(9, null);
      bst2.insert(8, null);

      // Remove 9 that has one child, tree should be 10-8
      bst2.remove(9);

      // Check left child of 10
      if (!(bst2.getKeyOfLeftChildOf(10) == 8))
        fail("Did not return expected key of 8");
      // Catch weird exceptions
    } catch (Exception e) {
      fail("Something went wrong");
    }
  }

  /**
   * Method for testing removing a node with two child
   */
  @Test
  void testBST_015_remove_node_with_twoChildren() {
    try {
      // Insert nodes
      bst2.insert(15, null);
      bst2.insert(10, null);
      bst2.insert(9, null);
      bst2.insert(11, null);

      // Remove 10 that has two child
      bst2.remove(10);

      // Check left child of 15
      if (!(bst2.getKeyOfLeftChildOf(15) == 11))
        fail("Did not return expected key of 11");
      // Catch weird exceptions
    } catch (Exception e) {
      fail("Something went wrong");
    }
  }
}
