
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
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeAll;

//@SuppressWarnings("rawtypes")
public class AVLTest extends BSTTest {

  AVL<String, String> avl;
  AVL<Integer, String> avl2;

  /**
   * @throws java.lang.Exception
   */
  @BeforeEach
  void setUp() throws Exception {
    dataStructureInstance = bst = avl = createInstance();
    dataStructureInstance2 = bst2 = avl2 = createInstance2();
  }

  /**
   * @throws java.lang.Exception
   */
  @AfterEach
  void tearDown() throws Exception {
    avl = null;
    avl2 = null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see DataStructureADTTest#createInstance()
   */
  @Override
  protected AVL<String, String> createInstance() {
    return new AVL<String, String>();
  }

  /*
   * (non-Javadoc)
   * 
   * @see DataStructureADTTest#createInstance2()
   */
  @Override
  protected AVL<Integer, String> createInstance2() {
    return new AVL<Integer, String>();
  }

  /**
   * Insert three values in sorted order and then check the root, left, and right
   * keys to see if rebalancing occurred.
   * 
   * Example: 10-20-30, then root should be 20 instead of 10
   */
  @Test
  void testAVL_001_insert_sorted_order_simple() {
    try {
      avl2.insert(10, "10");
      if (!avl2.getKeyAtRoot().equals(10))
        fail("avl insert at root does not work");

      avl2.insert(20, "20");
      if (!avl2.getKeyOfRightChildOf(10).equals(20))
        fail("avl insert to right child of root does not work");

      avl2.insert(30, "30");
      Integer k = avl2.getKeyAtRoot();
      if (!k.equals(20))
        fail("avl rotate does not work");

      // IF rebalancing is working,
      // the tree should have 20 at the root
      // and 10 as its left child and 30 as its right child

      Assert.assertEquals(avl2.getKeyAtRoot(), new Integer(20));
      Assert.assertEquals(avl2.getKeyOfLeftChildOf(20), new Integer(10));
      Assert.assertEquals(avl2.getKeyOfRightChildOf(20), new Integer(30));

    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception AVL 001: " + e.getMessage());
    }
  }

  /**
   * Insert three values in reverse sorted order and then check the root, left,
   * and right keys to see if rebalancing occurred in the other direction.
   * 
   * Example: 30-20-10, root should be 20 again
   */
  @Test
  void testAVL_002_insert_reversed_sorted_order_simple() {

    try {
      // Insert and do checks
      avl2.insert(30, "10"); // Root
      if (!avl2.getKeyAtRoot().equals(30))
        fail("avl insert at root does not work");

      avl2.insert(20, "20"); // Right
      if (!avl2.getKeyOfLeftChildOf(30).equals(20))
        fail("avl insert to right child of root does not work");

      avl2.insert(10, "30"); // Right of 20

      if (!avl2.getKeyAtRoot().equals(20))
        fail("avl rotate does not work");

      // If rebalancing is working, through rightRotate
      // the tree should have 20 at the root
      // and 10 as its left child and 30 as its right child

      Assert.assertEquals(avl2.getKeyAtRoot(), new Integer(20));
      Assert.assertEquals(avl2.getKeyOfLeftChildOf(20), new Integer(10));
      Assert.assertEquals(avl2.getKeyOfRightChildOf(20), new Integer(30));

    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception AVL 002: " + e.getMessage());
    }

  }

  /**
   * Insert three values so that a right-left rotation is needed to fix the
   * balance.
   * 
   * Example: 10-30-20
   * 
   * Then check the root, left, and right keys to see if rebalancing occurred in
   * the other direction.
   */
  @Test
  void testAVL_003_insert_smallest_largest_middle_order_simple() {

    try {
      // Insert and check root
      avl2.insert(10, null);
      if (!avl2.getKeyAtRoot().equals(10))
        fail("root insert not working");

      // Insert and check child
      avl2.insert(30, null);
      if (!avl2.getKeyOfRightChildOf(10).equals(30))
        fail("did not insert correctly");

      // Insert and then will check the rotate is correct
      avl2.insert(20, null);
      if (!(avl2.getKeyAtRoot() == 20))
        fail("Incorrect root key");

    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected error at AVL 003");
    }

  }

  /**
   * Insert three values so that a left-right rotation is needed to fix the
   * balance.
   * 
   * Example: 30-10-20
   * 
   * Then check the root, left, and right keys to see if rebalancing occurred in
   * the other direction.
   */
  @Test
  void testAVL_003_insert_largest_smallest_middle_order_simple() {
    try {
      avl2.insert(30, null); // Root
      avl2.insert(10, null); // Left
      avl2.insert(20, null); // Right of 10

      // Check correct rotate
      if (!(avl2.getKeyAtRoot() == 20))
        throw new Exception();
      if (!(avl2.getKeyOfLeftChildOf(20) == 10))
        throw new Exception();
      if (!(avl2.getKeyOfRightChildOf(20) == 30))
        throw new Exception();
    } catch (Exception e) {
      fail("Rotate did not work correctly");
    }

  }

  /**
   * Method for testing normal methods used by AVL. Will test, insert, get,
   * contain, and remove.
   */
  @Test
  void testAVL_004_check_insert_remove_get_contain() {
    try {
      avl2.insert(50, null);
      avl2.insert(60, null);
      avl2.insert(40, null);
      avl2.insert(68, null);
      avl2.insert(72, "3");

      // Check remove method
      if (!avl2.remove(40))
        fail("Remove did not return True");

      // Check contain returns true
      if (!avl2.contains(68))
        fail("Contain did not return true");

      // Check get returns correct key
      String val = avl2.get(72);
      if (!val.equals("3"))
        fail("Get did not return correct value");
    } catch (Exception e) {
      fail("Something wrong happened");
    }
  }

  /**
   * This test is meant to do simple cases of rotate but on bigger trees. Test
   * right rotate
   */
  @Test
  void testAVL_005_rotate_right_biggerTree() {
    try {
      // Insert integer keys
      avl2.insert(100, null);
      avl2.insert(150, null);
      avl2.insert(90, null);
      avl2.insert(160, null);
      avl2.insert(95, null);
      avl2.insert(140, null);
      avl2.insert(85, null);
      avl2.insert(145, null);
      avl2.insert(135, null);
      avl2.insert(130, null);

      // Check rotate for 150 which has bf of 2
      // Check by looking at node 140 have right child of 150
      if (avl2.getKeyOfRightChildOf(140) != 150)
        fail("Did not do proper rotate");

    } catch (Exception e) {
      fail("Exception: " + e.getMessage() + " was thrown");
    }
  }

  /**
   * This test is meant to do simple cases of rotate but on bigger trees. Test
   * left rotate.
   */
  @Test
  void testAVL_006_rotate_left_biggerTree() {
    try {
      // Insert integer keys
      avl2.insert(100, null);
      avl2.insert(150, null);
      avl2.insert(90, null);
      avl2.insert(160, null);
      avl2.insert(95, null);
      avl2.insert(140, null);
      avl2.insert(85, null);
      avl2.insert(75, null);
      avl2.insert(86, null);
      avl2.insert(74, null);

      // Check rotate for 85 which has bf of 2
      // Check by looking at node 85 have left child of 75
      if (avl2.getKeyOfLeftChildOf(85) != 75)
        fail("Did not do proper rotate");

    } catch (Exception e) {
      fail("Exception: " + e.getMessage() + " was thrown");
    }
  }

  // TESTS FOR NOT BALANCED INSERTIONS WILL FAIL. AVL ARE ALWAYS BALANCED

}
