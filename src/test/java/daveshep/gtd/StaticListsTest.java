package daveshep.gtd;

import daveshep.gtd.domain.GtdList;
import daveshep.gtd.domain.InMemoryListManager;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class StaticListsTest extends TestCase {

	public StaticListsTest( String testName ) {
        super( testName );
    }

    public static Test suite() {
        return new TestSuite( StaticListsTest.class );
    }


	public void testCreateStaticLists() {
		GtdListManager listManager = InMemoryListManager.getInstance();
    	try {
			GtdList staticList = listManager.getList(StaticLists.IN);
			staticList = listManager.getList(StaticLists.IN);
			assertNotNull(staticList);
			assertEquals(0,staticList.size());
		} catch (GtdListException e) {
			e.printStackTrace();
			fail();
		}

		
		
	}

}
