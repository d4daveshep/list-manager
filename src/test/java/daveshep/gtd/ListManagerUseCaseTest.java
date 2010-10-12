package daveshep.gtd;

import daveshep.gtd.domain.GtdList;
import daveshep.gtd.domain.GtdListItem;
import daveshep.gtd.domain.GtdListItemTest;
import daveshep.gtd.domain.InMemoryListManager;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


public class ListManagerUseCaseTest extends TestCase {

	public ListManagerUseCaseTest( String testName ) {
        super( testName );
    }

    public static Test suite() {
        return new TestSuite( ListManagerUseCaseTest.class );
    }

    public void testCreateStaticLists() {
    	fail();
    }
    
    public void testCreateList() {
    	fail();
    }
    
    public void testGetList() {
    	fail();
    }
    
    public void testCreateNewListItem() {
    	
    	GtdListManager listManager = InMemoryListManager.getInstance();
    	GtdList inList = null;
		GtdList wfList = null;
		try {
	    	StaticLists.createStaticLists(listManager);
			inList = listManager.getList(StaticLists.IN);
			wfList = listManager.getList(StaticLists.WAITING_FOR);
		} catch (GtdListException e1) {
			e1.printStackTrace();
			fail();
		}
    	
    	// create a new list item
    	GtdListItem item = listManager.createListItem("my first list item");
    	assertTrue(item.getDescription().equals("my first list item"));
    	
    	// add it to a list
    	try {
        	inList.add(item);
        	assertTrue(inList.contains(item));
        	assertTrue(inList.size()==1);
        	assertEquals(inList, item.getOwningList());
    	} catch (GtdListException e) {
    		fail(e.toString());
    	}
    	
    	// try to add it to another list
    	try {
        	wfList.add(item);
        	fail("item can't be on more than one list");
    	} catch (GtdListException e) {
        	assertTrue(inList.contains(item));
        	assertTrue(inList.size()==1);
        	assertEquals(inList, item.getOwningList());
    	}
    	
    	// move it to another list
    	try {
    		item.move(wfList);
        	assertFalse(inList.contains(item));
        	assertFalse(inList.size()==1);
        	assertTrue(wfList.contains(item));
        	assertTrue(wfList.size()==1);
        	assertEquals(wfList, item.getOwningList());
    		
    	} catch (GtdListException e) {
    		fail("something bad happened");
    	}
    	
    }
    

    
}
