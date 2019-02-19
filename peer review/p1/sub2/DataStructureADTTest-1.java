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
	void test01_after_imsert_one_size_is_one() {
	  String key = "a";
      String value = "1";
      
	  dataStructureInstance.insert(key, value);
	  if (dataStructureInstance.size() != 1)
        fail("size of data structure should be 1, but size =" +dataStructureInstance.size());
	}
	
	// test02_after_insert_one_remove_one_size_is_0
	@Test
	void test02_after_insert_one_remove_one_size_is_zero() {
      String key = "a";
	  String value = "1";
	  
	  dataStructureInstance.insert(key, value);
	  dataStructureInstance.remove(key);
	  
	  if (dataStructureInstance.size() != 0)
        fail("size of data structure should be 0, but size =" +dataStructureInstance.size());
	}
	
	// test03_duplicate_exception_is_thrown
	@Test
	void test03_duplicate_exception_is_thrown() {
		try {
          String key = "a";
          String value = "1";
          String key1 = "a";
		  dataStructureInstance.insert(key, value);
	      dataStructureInstance.insert(key1, value);
		} catch (RuntimeException e) {return;}	    
		fail("exception should be thorwed when the key is already in the data structure");
	}
	
	// test04_remove_returns_false_when_key_not_present
	@Test
    void test04_remove_returns_false_when_key_not_present() {
    	String key = "a";
        String value = "1";
        String key1 = "b";
        dataStructureInstance.insert(key, value);
        if (dataStructureInstance.remove(key1))
          fail("remove() should return false when key isn't found, but return true");
    }
	
	// TODO: add tests to ensure that you can detect implementation that fail
    
    // test05_ get_return_correct_value
	@Test
    void test05() {
      String key = "a";
      String value = "1";
      dataStructureInstance.insert(key, value);
      if (dataStructureInstance.get(key).equals(1))
    	  fail("get() should return '1', but return " +dataStructureInstance.get(key));
    }
    
    // test06_contain_return_false_when_key_not_present
	@Test
    void test06() {    	
      String key = "a";
      if (dataStructureInstance.contains(null))
        fail("contain() should return false because key is null, but return true");
    }
    
    // test07_insert_IllegalArgumentException
	@Test
    void test07() {
      String key = null;
      String value = "1";
      try {
        dataStructureInstance.insert(null,value);
      } catch(IllegalArgumentException e) {return;}
    	  fail ("exception should be thorwed when the key is null");
      
    }
	
    // test08_remove_IllegalArgumentException
	@Test
    void test08() {
    	       
        try {
        	dataStructureInstance.remove(null);
        } catch (IllegalArgumentException e){return;}
            fail ("exception should be thorwed when the key is null");
    }
    
    // test09_IllegalArgumentException_get_method
	@Test
    void test09() {
      String key = "A";
      try {
    	  dataStructureInstance.get(null);
      } catch (IllegalArgumentException e) {return;}
          fail ("exception should be thorwed when the key is null");
    }
    
    // test10_size_after_insert_remove_insert_insert
	@Test
    void test10() {
      String key = "A";
      String value = "1";
      String key1 = "B";
      String value1 = "2";
      String key2 = "C";
      String key3 = "D";
      
      dataStructureInstance.insert(key1, value1);
      dataStructureInstance.insert(key, value);
      dataStructureInstance.remove(key1);
      dataStructureInstance.insert(key2, value);
      dataStructureInstance.insert(key3, value);
      if (dataStructureInstance.size() != 3)
    	  fail ("size should be 3, but return: " +dataStructureInstance.size());
    }
	// Tip: consider different numbers of inserts and removes and how different combinations of insert and removes

}
