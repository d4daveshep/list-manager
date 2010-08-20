package daveshep.gtd.domain;

import java.text.ParseException;

import daveshep.gtd.util.DateUtils;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class DueDateSorterTest extends TestCase {

    public DueDateSorterTest( String testName ) {
        super( testName );
    }

    public static Test suite() {
        return new TestSuite( DueDateSorterTest.class );
    }

    
    public void testComparator() {

    	DueDateSorter sorter = new DueDateSorter();
    	
    	ListItem item1 = new ListItem("Item1");
    	item1.setId(1L);
    	
    	ListItem item2 = new ListItem("Item2");
    	item2.setId(2L);
    	
    	// dates not set so items are sorted using natural sort order (on id)
    	assertTrue(sorter.compare(item1, item2)<0);
    	assertTrue(sorter.compare(item2, item1)>0);
    	
    	// an unset date should be after any set date
    	item1.setDueDate(DateUtils.today());
    	assertTrue(sorter.compare(item1, item2)<0);
    	assertTrue(sorter.compare(item2, item1)>0);
    	
    	item1.setDueDate(null);
    	item2.setDueDate(DateUtils.today());
    	assertTrue(sorter.compare(item1, item2)>0);
    	assertTrue(sorter.compare(item2, item1)<0);
    	
    	// equal dates should be sorted on id
    	item1.setDueDate(DateUtils.today());
    	item2.setDueDate(DateUtils.today());
    	assertTrue(sorter.compare(item1, item2)<0);

    	// test date ordering
    	try {
    		item1.setDueDate(DateUtils.dateFormat.parse("2010-08-20"));
    		item2.setDueDate(DateUtils.dateFormat.parse("2010-08-21"));
    		assertTrue(sorter.compare(item1, item2)<0);

    		item1.setDueDate(DateUtils.dateFormat.parse("2010-08-22"));
    		assertTrue(sorter.compare(item1, item2)>0);

    	} catch (ParseException e) {
    		e.printStackTrace();
    	}
    	
    }
    
    
}
