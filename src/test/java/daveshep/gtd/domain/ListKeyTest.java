package daveshep.gtd.domain;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ListKeyTest extends TestCase {

	public ListKeyTest( String testName ) {
        super( testName );
    }

    public static Test suite() {
        return new TestSuite( ListKeyTest.class );
    }

    public void testConstructors() {
    	try {
			ListKey key1 = new ListKey(null); // not allowed
			fail();
		} catch (IllegalArgumentException e) {
		}

		try {
			ListKey key1 = new ListKey(""); // not allowed
			fail();
		} catch (IllegalArgumentException e) {
		}
		
		try {
			ListKey key1 = new ListKey("title",null); // not allowed
			fail();
		} catch (IllegalArgumentException e) {
		}

		try {
			ListKey key1 = new ListKey("title",""); // allowed
		} catch (IllegalArgumentException e) {
			fail();
		}
    }
    
    public void testImmutable() {
    	ListKey key1 = new ListKey("title", "subtitle");
    	ListKey key2 = new ListKey("title", "subtitle");
    	assertEquals(key1,key2);

    	ListKey key3 = new ListKey("TITLE", "SuBtITlE");
    	assertEquals(key1,key3);
    	
    	ListKey key4 = new ListKey("title");
    	assertTrue(key1.equals(key4)==false);
    	
    	ListKey key5 = new ListKey("TITLE");
    	assertEquals(key4,key5);
    	
    	ListKey key6 = new ListKey("title","subtitle1");
    	assertTrue(key1.equals(key6)==false);
    	
    }

}
