import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

abstract class DataStructureADTTest<T extends DataStructureADT<String,String>> {
	
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

	
	@Test
	void test00_empty_ds_size() {
		if (dataStructureInstance.size() != 0)
		fail("data structure should be empty, with size=0, but size="+dataStructureInstance.size());
	}
	
	// TODO: implement tests 01 - 04

	// test01_after_insert_one_size_is_one
	@Test
	void test01_after_insert_one_size_is_one() {
		dataStructureInstance.insert("Key1", "Value1");
		
		if (dataStructureInstance.size() != 1) {
			fail("data structure should have 1 item, but size was " + dataStructureInstance.size());
		}
	}
	
	// test02_after_insert_one_remove_one_size_is_0
	@Test
	void test02_after_insert_one_remove_one_size_is_0() {
		dataStructureInstance.insert("Key1", "Value1");
		
		dataStructureInstance.remove("Key1");
		
		assertEquals(dataStructureInstance.size(), 0);
	}
	
	// test03_duplicate_exception_is_thrown
	@Test
	void test03_duplicate_exception_is_thrown() {
		dataStructureInstance.insert("Key1", "Value1");
		try {
			dataStructureInstance.insert("Key1", "Value1");
		} catch (RuntimeException e) {} // Do nothing if passes
	}
	
	// test04_remove_returns_false_when_key_not_present
	@Test
	void test04_remove_returns_false_when_key_not_present() {
		dataStructureInstance.insert("key1", "value1");
		
		if (dataStructureInstance.remove("Not here")) {
			fail("Remove method did not return false when key not present");
		}
	}

	
	// TODO: add tests to ensure that you can detect implementation that fail
	
	// Tip: consider different numbers of inserts and removes and how different combinations of insert and removes


}
