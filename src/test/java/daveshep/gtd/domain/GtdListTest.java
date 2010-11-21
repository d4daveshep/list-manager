package daveshep.gtd.domain;

import java.util.Collection;

import daveshep.gtd.DuplicateListException;
import daveshep.gtd.GtdListException;
import daveshep.gtd.GtdListManager;
import daveshep.gtd.StaticLists;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class GtdListTest extends TestCase {

	public GtdListTest(String testName) {
		super(testName);
	}
	
    public static Test suite() {
        return new TestSuite( GtdListTest.class );
    }

    GtdListManager listManager = null;
    
    @Override
	protected void setUp() throws Exception {
		super.setUp();
    	listManager = InMemoryListManager.getInstance();
    	listManager.removeAll();
		StaticLists.createStaticLists(listManager);
	}
  
    public void testFindByString() {
    	
    	// set up a list
		try {
			GtdList home = listManager.createList("Home");

	    	// add some items to the lists
			home.add(listManager.createListItem("this is item1 - isn't it good"));
			home.add(listManager.createListItem("this is item2 - isn't it fine"));
	    	
	    	home.add(listManager.createListItem("this is item3 - isn't it fine and swell"));
	    	home.add(listManager.createListItem("this is item4 - isn't it oh so nice"));
	    	
	    	// search and check results
	    	Collection<GtdListItem> find1 = home.findItemsByString("swell");
	    	assertEquals(1,find1.size());
	    	
	    	Collection<GtdListItem> find2 = home.findItemsByString("fine");
	    	assertEquals(2,find2.size());
	    	
	    	Collection<GtdListItem> find3 = home.findItemsByString("item");
	    	assertEquals(4,find3.size());

			
			
			
		} catch (DuplicateListException e) {
			e.printStackTrace();
			fail(e.getMessage());
		} catch (GtdListException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
    	
    }


}
