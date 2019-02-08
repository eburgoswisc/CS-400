
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
// Online Sources:  gradescope p1 * I used the method name of a test to guide 
//                      one of my test implementations (test12)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class that contains test methods for testing implementations of
 * DataStructureADT. Methods perform a variety of functions that test different
 * aspects of the DS
 * 
 * @author emanuelburgos
 *
 * @param <T> - generic type that extends comparabale
 */
abstract class DataStructureADTTest<T extends DataStructureADT<String, String>> {

  private T dataStructureInstance; // DS from each implementation

  protected abstract T createInstance(); // Constructor for DS

  /**
   * Set anything before any test
   * 
   * @throws Exception
   */
  @BeforeAll
  static void setUpBeforeClass() throws Exception {
  }

  /**
   * Tear down anything before any test
   * 
   * @throws Exception
   */
  @AfterAll
  static void tearDownAfterClass() throws Exception {
  }

  /**
   * Set up before each individual test
   * 
   * @throws Exception
   */
  @BeforeEach
  void setUp() throws Exception {
    // Allows for testing different DS implementations
    dataStructureInstance = createInstance();
  }

  /**
   * Resets everything before each test
   * 
   * @throws Exception
   */
  @AfterEach
  void tearDown() throws Exception {
    dataStructureInstance = null;
  }

  /**
   * Test if size is zero
   */
  @Test
  void test00_empty_ds_size() {
    // Check size
    if (dataStructureInstance.size() != 0)
      fail("data structure should be empty, with size=0, but size=" + dataStructureInstance.size());
  }

  /**
   * After insert, check if size is 1
   */
  @Test
  void test01_after_insert_one_size_is_one() {
    // Insert
    dataStructureInstance.insert("Key1", "Value1");
    // Check size
    if (dataStructureInstance.size() != 1) {
      fail("data structure should have 1 item, but size was " + dataStructureInstance.size());
    }
  }

  /**
   * Inserts, remove, then check if size is 0
   */
  @Test
  void test02_after_insert_one_remove_one_size_is_0() {
    // Insert and remove
    dataStructureInstance.insert("Key1", "Value1");
    dataStructureInstance.remove("Key1");

    // Checks size
    assertEquals(dataStructureInstance.size(), 0);
  }

  /**
   * Test that exception is thrown when given duplicate key
   */
  @Test
  void test03_duplicate_exception_is_thrown() {
    dataStructureInstance.insert("Key1", "Value1");

    // Check that Exception occurs
    try {
      dataStructureInstance.insert("Key1", "Value1");
    } catch (RuntimeException e) {
    } // Do nothing if passes
  }

  /**
   * Test that removes pair and check if contain return false
   */
  @Test
  void test04_remove_returns_false_when_key_not_present() {
    dataStructureInstance.insert("key1", "value1");

    if (dataStructureInstance.remove("Not here")) {
      fail("Remove method did not return false when key not present");
    }
  }

  /**
   * Test if inserting a null key throws exception
   */
  @Test
  void test05_insert_null_catch_exception() {

    try {
      dataStructureInstance.insert(null, "not here");
      fail("IllegalArgumentException (null key) was not thrown");
    } catch (IllegalArgumentException e) {
      // do nothing
    }

  }

  /**
   * Testing if DS increases capacity if overloaded.
   */
  @Test
  void test06_insert_over_capacity() {
    for (int i = 0; i < 1000; i++) {
      dataStructureInstance.insert(Integer.toString(i), "something");
    }
  }

  /**
   * Test overcapacity and at a random point throws null
   */
  @Test
  void test07_overcapacity_check_null() {
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
  }

  /**
   * Test overcapacity and then check duplicate
   */
  @Test
  void test08_overcapacity_check_duplicate() {
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

    } catch (RuntimeException e) {
    } // Do nothing, it passed
  }

  /**
   * Test removeing correct key and contin works properly
   */
  @Test
  void test09_remove_correct_key_contain_size() {
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

    assertEquals(dataStructureInstance.size(), 1);
    assertTrue(dataStructureInstance.contains("Key1"));
  }

  /**
   * Test contain return false when given null key
   */
  @Test
  void test10_contain_return_false_null() {
    // Check that contains return false when given null key
    assertFalse(dataStructureInstance.contains(null));
  }

  /**
   * Test return correct value for given key and check size did not change
   */
  @Test
  void test11_return_correct_value_and_size() {
    // Insert three different pairs
    dataStructureInstance.insert("Key1", "value1");
    dataStructureInstance.insert("Key2", "value2");
    dataStructureInstance.insert("Key3", "value3");

    assertEquals(dataStructureInstance.get("Key1"), "value1");
    assertEquals(dataStructureInstance.size(), 3);
  }

  /**
   * Insert 50 and remove 25, check size
   */
  @Test
  void test12_insert_50_remove_25_size() {
    // Add 50 pair objects
    for (int i = 0; i < 50; i++) {
      dataStructureInstance.insert(Integer.toString(i), Integer.toString(i));
    }
    // Remove 25 objects
    for (int i = 0; i < 25; i++) {
      dataStructureInstance.remove(Integer.toString(i));
    }
    // Check size
    assertEquals(dataStructureInstance.size(), 25);
  }

}
