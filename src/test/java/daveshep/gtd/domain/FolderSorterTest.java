package daveshep.gtd.domain;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class FolderSorterTest extends TestCase {

    public FolderSorterTest( String testName ) {
        super( testName );
    }

    public static Test suite() {
        return new TestSuite( FolderSorterTest.class );
    }

    
    public void testComparator() {

    	FolderSorter sorter = new FolderSorter();
    	
    	ListItem item1 = new ListItem("Item1");
    	item1.setId(1L);
    	
    	ListItem item2 = new ListItem("Item2");
    	item2.setId(2L);
    	
    	// folders not set so items are sorted using natural sort order (on id)
    	assertTrue(sorter.compare(item1, item2)<0);
    	assertTrue(sorter.compare(item2, item1)>0);
    	
    	// an unset folder should be after any set folder
    	item1.setFolder("a.folder");
    	item2.setFolder(null);
    	assertTrue(sorter.compare(item1, item2)<0);
    	assertTrue(sorter.compare(item2, item1)>0);
    	
    	item1.setFolder(null);
    	item2.setFolder("b.folder");
    	assertTrue(sorter.compare(item1, item2)>0);
    	assertTrue(sorter.compare(item2, item1)<0);
    	
    	// equal folders should be compared on id
    	item1.setFolder("a.folder");
    	item2.setFolder("a.folder");
    	assertTrue(sorter.compare(item1, item2)<0);

    	// test folder ordering
    	item1.setFolder("a.folder");
    	item2.setFolder("b.folder");
    	assertTrue(sorter.compare(item1, item2)<0);

    	item1.setFolder("c.folder");
    	assertTrue(sorter.compare(item1, item2)>0);

    	
    }
    
    
}
