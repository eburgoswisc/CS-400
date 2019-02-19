import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//p1:Implement and Test DataStructureADT
//Author: Chenhao Lu
//Lec 001
//Email Address: clu92@wisc.edu
//Due Date: 2/7/2019

abstract class DataStructureADTTest<T extends DataStructureADT<String, String>> {

	private T dataStructureInstance;

	protected abstract T createInstance();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		dataStructureInstance = createInstance();
	}

	@AfterEach
	void tearDown() throws Exception {
		dataStructureInstance = null;
	}

	/**
	 * Test whether size is 0 when list is empty
	 */
	@Test
	void test00_empty_ds_size() {
		if (dataStructureInstance.size() != 0)
			fail("data structure should be empty, with size=0, but size=" + dataStructureInstance.size());
	}

	/**
	 * Test whether size is one after inserting one pair
	 */
	@Test
	void test01_after_insert_one_size_is_one() {
		try {
			dataStructureInstance.insert("key1", "value1");
		} catch (RuntimeException e) {
			fail("should not have exception thrown"); // no exceptions should be thrown
		}
		if (dataStructureInstance.size() != 1)
			fail("size should be one, but size = " + dataStructureInstance.size());
	}

	/**
	 * Test whether size is 0 after inserting and removing
	 */
	@Test
	void test02_after_insert_one_remove_one_size_is_0() {
		try {
			dataStructureInstance.insert("key1", "value1");
			dataStructureInstance.remove("key1");
		} catch (RuntimeException e) {
			fail("should not have an exception thrown"); // no exceptions should be thrown
		}
		if (dataStructureInstance.size() != 0)
			fail("size should be 0, but size = " + dataStructureInstance.size());
	}

	/**
	 * Test whether exception is thrown when inserting duplicates
	 */
	@Test
	void test03_duplicate_exception_is_thrown() {
		try {
			dataStructureInstance.insert("key1", "value1");
		} catch (RuntimeException e) {
			fail("no exceptions should be thrown"); // no exceptions should be thrown
		}
		try {
			dataStructureInstance.insert("key1", "value2");
			fail("should throw runtime exception when duplicates are inserted"); // if no exception thrown
		} catch (RuntimeException e) {
			if (!e.getMessage().equals("duplicate key")) {
				fail("wrong exception message"); // if message is wrong
			}
		}
	}

	/**
	 * Test whether remove method returns false when removing a key not present
	 */
	@Test
	void test04_remove_returns_false_when_key_not_present() {
		try {
			dataStructureInstance.insert("key1", "value1");
		} catch (RuntimeException e) {
			fail("no exceptions should be thrown"); // no exceptions should be thrown
		}
		boolean result = dataStructureInstance.remove("key2"); // key2 not in the list
		if (result)
			fail("should return false when removing a non-existing key"); // false if the result is true
	}

	/**
	 * Test whether IllegalArgumentException is thrown without changing size when
	 * inserting a null key
	 */
	@Test
	void test05_IAException_thrown_size_unchanged_when_inserting_null() {
		try {
			dataStructureInstance.insert(null, "value");
			fail("should throw IllegalArgumentException when inserting a null key"); // if no exception thrown
		} catch (IllegalArgumentException e) {
			if (!e.getMessage().equals("null key"))
				fail("wrong exception message"); // if wrong exception message
			if (dataStructureInstance.size() != 0)
				fail("size should not change when inserting null key");
		}
	}

	/**
	 * Tests whether IllegalArgumentException is thrown without decreasing size when
	 * removing a null key
	 */
	@Test
	void test06_IAException_thrown_size_unchanged_when_removing_null() {
		try {
			dataStructureInstance.insert("key1", "value1");

			dataStructureInstance.remove(null);
			fail("should throw IllegalArgumentException when removing a null key");// if no exception thrown
		} catch (IllegalArgumentException e) {
			if (!e.getMessage().equals("null key"))
				fail("wrong exception message"); // if wrong exception message
			if (dataStructureInstance.size() != 1)
				fail("size should not change when removing a null key"); // if size changes when removing null
		} catch (RuntimeException e) {
			fail("should not have other exceptions thrown");
		}
	}

	/**
	 * Test whether get method returns the correct value when getting a present key
	 */
	@Test
	void test07_get_returns_correct_value_for_existing_key() {
		try {
			dataStructureInstance.insert("key1", "value1");
		} catch (RuntimeException e) {
			fail("no exceptions should be thrown"); // no exceptions should be thrown
		}
		Object obj = dataStructureInstance.get("key1");
		if (obj == null)
			fail("result shouldn't be null"); // fail if the result is null
		boolean result = obj.equals("value1");
		if (!result)
			fail("should return true when getting a present key");
	}

	/**
	 * Test whether IllegalArgumentException is thrown without changing size when
	 * getting a null key
	 */
	@Test
	void test08_IAException_thrown_size_unchanged_when_getting_null() {
		try {
			dataStructureInstance.insert("key1", "value1");

			dataStructureInstance.get(null);
			fail("should throw IllegalArgumentException when getting a null key"); // if no exception thrown
		} catch (IllegalArgumentException e) {
			if (!e.getMessage().equals("null key"))
				fail("wrong exception message"); // if wrong exception message
			if (dataStructureInstance.size() != 1)
				fail("size should not change when getting a null key"); // if size changes
		} catch (RuntimeException e) {
			fail("No other exceptions should be thrown"); // no exceptions should be thrown other than IAException
		}
	}

	/**
	 * Test whether size changes when inserting duplicates
	 */
	@Test
	void test09_size_unchanged_inserting_duplicates() {
		try {
			dataStructureInstance.insert("key1", "value1");
		} catch (RuntimeException e) {
			fail("no exception should be thrown"); // no exceptions should be thrown
		}
		try {
			dataStructureInstance.insert("key1", "value2"); // duplicate
		} catch (RuntimeException e) {
			if (dataStructureInstance.size() != 1)
				fail("size should not change when inserting duplicates");
		}
	}

	/**
	 * Test whether contains method work under all conditions
	 */
	@Test
	void test10_contains_returns_correct_result() {
		try {
			dataStructureInstance.insert("key1", "value1");
		} catch (RuntimeException e) {
			fail("should not have exceptions thrown"); // no exceptions should be thrown
		}
		boolean result1 = dataStructureInstance.contains("key1"); // should be true
		boolean result2 = dataStructureInstance.contains("key2"); // should be false
		boolean result3 = dataStructureInstance.contains(null); // should be false
		if (!result1)
			fail("should return true when checking for a present key");
		if (result2)
			fail("should return false when checking for a not present key");
		if (result3)
			fail("should return false when checking for a null key");
	}

	/**
	 * Test that a key can be re-added if removed
	 */
	@Test
	void test11_readd_key() {
		try {
			dataStructureInstance.insert("key1", "value1");
			dataStructureInstance.remove("key1");

			dataStructureInstance.insert("key1", "value1"); // re-insert a key
		} catch (RuntimeException e) {
			fail("should be able to re-insert the key.");
		}
	}
	// Tip: consider different numbers of inserts and removes and how different
	// combinations of insert and removes

}
