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

    public void testCreateNewListItem() {
    	
    	GtdListManager listManager = InMemoryListManager.getInstance();
    	
    	// create a new list item
    	GtdListItem item = listManager.createListItem("my first list item");
    	assertTrue(item.getDescription().equals("my first list item"));
    	
    	// add it to a list
    	GtdList inList = listManager.getList(StaticLists.IN);
    	inList.add(item);
    	assertTrue(inList.contains(item));
    }
    

}
