
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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * ABSTRACT super-class with DataStructureADT JUnit tests.
 * 
 * This class contains methods for testing the basic functionality of a
 * DataStructureADT implementation. Such a d.s. type was designed and tested in
 * Program 1.
 * 
 * This class will now be the super-class of SearchTreeADTTest. This means that
 * SearchTreeADTTest inherit all of tests (public and protect methods) from
 * DataStructureADTTest.
 * 
 * For Program 2, almost all tests from your p1 DataStructureADTTest class can
 * be copied and run here without changes. There are some required changes.
 * 
 * See @DataStructureADT for more details
 * 
 * NOTE: this class has changed the visibility of dataStructureInstance and
 * added dataStructureInstance2, and dataStructureInstance3.
 * 
 * dataStructureInstance is still a DataStructure<String,String>.
 * dataStructureInstance2 is a DataStructure<Integer,String>.
 * dataStructureInstance3 is a DataStructure<Integer,Integer>. DO NOT CHANGE THE
 * TYPES, NAMES, OR VISIBLITY OF THOSE FIELDS
 * 
 * @author Debra Deppeler (deppeler@cs.wisc.edu)
 */
abstract class DataStructureADTTest {

  // CHANGED FROM P1: fields are protected (so they may be accessed from
  // sub-classes)
  protected DataStructureADT<String, String> dataStructureInstance;

  // ADDED FROM P1: added another dataStructureInstance type <Integer,String>
  protected DataStructureADT<Integer, String> dataStructureInstance2;

  // CHANGED FROM P1: methods are protected (so they may be accessed from
  // sub-classes)
  protected abstract DataStructureADT<String, String> createInstance();

  // ADDED FROM P1: added method to create another dataStructureInstance type
  // <Integer,String>
  protected abstract DataStructureADT<Integer, String> createInstance2();

  @BeforeEach
  void setUp() throws Exception {
    dataStructureInstance = createInstance();
    dataStructureInstance2 = createInstance2();
  }

  @AfterEach
  void tearDown() throws Exception {
    dataStructureInstance = null;
    dataStructureInstance2 = null;
  }

  /**
   * Provided Utility Method for comparing List<K> with other List<K>
   * 
   * Helper assert method for comparing lists of various element types. List must
   * have the same number of elements, be of the same type, and have elements that
   * are the same in the same order.
   * 
   * @param list1<?>
   * @param list2<?>
   */
  public void assertEquals(List<?> list1, List<?> list2) {
    assertTrue(list1 != null);
    assertTrue(list2 != null);
    assertTrue(list1.size() == list2.size());
    for (int i = 0; i < list1.size(); i++) {
      assertTrue(list1.get(i).equals(list2.get(i)));
    }
  }

  @Test
  void testDS00_empty_ds_numKeys() {
    // It it works for one test, should work for all
    assertTrue(dataStructureInstance.numKeys() == 0);
    assertTrue(dataStructureInstance2.numKeys() == 0);
  }

  @Test
  void testDS01_insert_one_ds_numKeys() throws IllegalNullKeyException {
    try {
      // It it works for one test, should work for all
      dataStructureInstance.insert("mykey1", "myvalue1");
      dataStructureInstance2.insert(2, "myvalue2");
      System.out.println(dataStructureInstance.numKeys());

      assertTrue(dataStructureInstance.numKeys() == 1);
      assertTrue(dataStructureInstance2.numKeys() == 1);

    } catch (DuplicateKeyException e) {
      e.printStackTrace();
      fail("Unexpected exception DS01: " + e.getMessage());
    }
  }

  /**
   * Test if size is zero
   */
  @Test
  void test00_empty_ds_numKeys() {
    // Check size
    if (dataStructureInstance.numKeys() != 0)
      fail("data structure should be empty, with size=0, but size="
          + dataStructureInstance.numKeys());
  }

  /**
   * After insert, check if size is 1
   * 
   * @throws DuplicateKeyException
   * @throws IllegalNullKeyException
   */
  @Test
  void test01_after_insert_one_size_is_one() throws IllegalNullKeyException, DuplicateKeyException {
    // Insert
    dataStructureInstance.insert("Key1", "Value1");
    // Check size
    if (dataStructureInstance.numKeys() != 1) {
      fail("data structure should have 1 item, but size was " + dataStructureInstance.numKeys());
    }
  }

  /**
   * Inserts, remove, then check if size is 0
   * 
   * @throws KeyNotFoundException
   * @throws IllegalNullKeyException
   * @throws DuplicateKeyException
   */
  @Test
  void test02_after_insert_one_remove_one_size_is_0()
      throws IllegalNullKeyException, KeyNotFoundException, DuplicateKeyException {
    // Insert and remove
    dataStructureInstance.insert("Key1", "Value1");
    dataStructureInstance.remove("Key1");

    // Checks size
    if (dataStructureInstance.numKeys() == 0) {
      // Do nothing, it passed
    }
  }

  /**
   * Test that exception is thrown when given duplicate key
   * 
   * @throws DuplicateKeyException
   * @throws IllegalNullKeyException
   */
  @Test
  void test03_duplicate_exception_is_thrown()
      throws IllegalNullKeyException, DuplicateKeyException {
    dataStructureInstance.insert("Key1", "Value1");

    // Check that Exception occurs
    try {
      dataStructureInstance.insert("Key1", "Value1");
    } catch (DuplicateKeyException e) {
    } // Do nothing if passes
  }

  /**
   * Test that removes pair and check if contain return false
   * 
   * @throws KeyNotFoundException
   * @throws IllegalNullKeyException
   * @throws DuplicateKeyException
   */
  @Test
  void test04_remove_throw_exception_when_key_not_present()
      throws IllegalNullKeyException, KeyNotFoundException, DuplicateKeyException {
    dataStructureInstance.insert("key1", "value1");
    try {
      dataStructureInstance.remove("Not here");

    } catch (KeyNotFoundException e) {
      // do nothing
    }
  }

  /**
   * Test if inserting a null key throws exception
   * 
   * @throws DuplicateKeyException
   * @throws IllegalNullKeyException
   */
  @Test
  void test05_insert_null_catch_exception() throws IllegalNullKeyException, DuplicateKeyException {

    try {
      dataStructureInstance.insert(null, "not here");
      fail("IllegalArgumentException (null key) was not thrown");
    } catch (IllegalNullKeyException e) {
      // Do nothing
    }

  }

  /**
   * Testing if DS increases capacity if overloaded.
   * 
   * @throws DuplicateKeyException
   * @throws IllegalNullKeyException
   */
  @Test
  void test06_insert_over_capacity() throws IllegalNullKeyException, DuplicateKeyException {
    for (int i = 0; i < 1000; i++) {
      dataStructureInstance.insert(Integer.toString(i), "something");
    }
  }

  /**
   * Test overcapacity and at a random point throws null
   * 
   * @throws DuplicateKeyException
   * @throws IllegalNullKeyException
   */
  @Test
  void test07_overcapacity_check_null() throws IllegalNullKeyException, DuplicateKeyException {
    try {

      // Loop which overloads datastructure,
      // Later inserts null and its suppose to catch it
      for (int i = 0; i < 1000; i++) {
        if (i == 700) {
          dataStructureInstance.insert(null, "something");
        }
        dataStructureInstance.insert(Integer.toString(i), "something");
      }

    } catch (IllegalArgumentException e) {
    } // Do nothing, it passed
    catch (IllegalNullKeyException e) {
      // Also do nothing
    }
  }

  /**
   * Test overcapacity and then check duplicate
   * 
   * @throws DuplicateKeyException
   * @throws IllegalNullKeyException
   */
  @Test
  void test08_overcapacity_check_duplicate() throws IllegalNullKeyException, DuplicateKeyException {
    try {

      // Loops until overload,
      // Adds duplicate and should cause exception
      for (int i = 0; i < 1000; i++) {
        if (i == 700) {
          // Add duplicate key
          dataStructureInstance.insert(Integer.toString(500), "something");
        }
        dataStructureInstance.insert(Integer.toString(i), "something");
      }

    } catch (DuplicateKeyException e) {
    } // Do nothing, it passed
  }

  /**
   * Test removing correct key and contain works properly
   * 
   * @throws IllegalNullKeyException
   * @throws DuplicateKeyException
   * @throws KeyNotFoundException
   */
  @Test
  void test09_remove_correct_key_contain_numKeys() {
    try {
      // Insert three different pairs
      dataStructureInstance.insert("Key1", null);
      dataStructureInstance.insert("Key2", null);
      dataStructureInstance.insert("Key3", null);

      // Removes one and checks that contains returns correct boolean
      assertTrue(dataStructureInstance.remove("Key2"));
      assertTrue(dataStructureInstance.contains("Key1"));
      assertFalse(dataStructureInstance.contains("Key2"));

      // Remove another and then check size and contain again
      assertTrue(dataStructureInstance.remove("Key3"));

      if (dataStructureInstance.numKeys() != 1)
        fail("Returned incorrect size");
      assertTrue(dataStructureInstance.contains("Key1"));
    } catch (Exception e) {
      fail("Something went wrong");
    }
  }

  /**
   * Test contain return false when given null key
   */
  @Test
  void test10_contain_return_false_null() {
    // Check that contains return false when given null key
    try {
      assertFalse(dataStructureInstance.contains(null));
    } catch (IllegalNullKeyException e) {
      // Do nothing... it passed
    }
  }

  /**
   * Test return correct value for given key and check size did not change
   * 
   */
  @Test
  void test11_return_correct_value_and_numKeys() {
    try {
      // Insert three different pairs
      dataStructureInstance.insert("Key1", "value1");
      dataStructureInstance.insert("Key2", "value2");
      dataStructureInstance.insert("Key3", "value3");

      if (!dataStructureInstance.get("Key1").equals("value1"))
        fail("Did not get correct value");
      if (dataStructureInstance.numKeys() != 3)
        fail("Did not return correct numKeys");
    } catch (Exception e) {
      fail("Something went wrong");
    }

  }

  /**
   * Insert 50 and remove 25, check size of numKeys
   * 
   */
  @Test
  void test12_insert_50_remove_25_numKeys() {
    try {
      // Add 50 pair objects
      for (int i = 0; i < 50; i++) {
        dataStructureInstance2.insert(i, Integer.toString(i));
      }
      // Remove 25 objects
      for (int i = 0; i < 25; i++) {
        dataStructureInstance2.remove(i);
      }
      // Check size
      Assert.assertEquals(dataStructureInstance2.numKeys(), 25);
    } catch (Exception e) {
      fail("Should not throw exception");
    }
  }

  /**
   * Checks that get throws correct exception if not found in dataStructure
   */
  @Test
  void test_13_check_exception() {
    try {
      // Add 50 pair objects
      for (int i = 0; i < 50; i++) {
        dataStructureInstance2.insert(i, Integer.toString(i));
      }
      // Not in DataStructure
      dataStructureInstance2.get(100);
    } catch (KeyNotFoundException e) {
      // Do nothing
    } catch (IllegalNullKeyException e) {
      // Do nothing
    } catch (Exception e) {
      fail("Something wrong happened");
    }

  }
}
