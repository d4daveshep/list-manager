package daveshep.gtd.domain;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class DescriptionSorterTest extends TestCase {

    public DescriptionSorterTest( String testName ) {
        super( testName );
    }

    public static Test suite() {
        return new TestSuite( DescriptionSorterTest.class );
    }

    
    public void testComparator() {

    	DescriptionSorter sorter = new DescriptionSorter();
    	
    	ListItem item1 = new ListItem("Item1");
    	item1.setId(1L);
    	
    	ListItem item2 = new ListItem("Item2");
    	item2.setId(2L);
    	
    	// descriptions not set so items are sorted using natural sort order (on id)
    	assertTrue(sorter.compare(item1, item2)<0);
    	assertTrue(sorter.compare(item2, item1)>0);
    	
    	// an unset or empty description should be after any set description
    	item1.setDescription("a.description");
    	item2.setDescription(null);
    	assertTrue(sorter.compare(item1, item2)<0);
    	item2.setDescription("");
    	assertTrue(sorter.compare(item1, item2)<0);
    	
    	item1.setDescription(null);
    	item2.setDescription("b.description");
    	assertTrue(sorter.compare(item1, item2)>0);
    	item1.setDescription("");
    	assertTrue(sorter.compare(item1, item2)>0);
    	
    	// equal descriptions should be compared on id
    	item1.setDescription("a.description");
    	item2.setDescription("a.description");
    	assertTrue(sorter.compare(item1, item2)<0);

    	// test description ordering
    	item1.setDescription("a.description");
    	item2.setDescription("b.description");
    	assertTrue(sorter.compare(item1, item2)<0);

    	item1.setDescription("c.description");
    	assertTrue(sorter.compare(item1, item2)>0);

    	
    }
    
    
}
