package daveshep.gtd.domain;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TaskContextSorterTest extends TestCase {

    public TaskContextSorterTest( String testName ) {
        super( testName );
    }

    public static Test suite() {
        return new TestSuite( TaskContextSorterTest.class );
    }

    
    public void testComparator() {

    	TaskContextSorter sorter = new TaskContextSorter();
    	
    	ListItem item1 = new ListItem("Item1");
    	item1.setId(1L);
    	
    	Task task1 = new Task();
    	task1.setId(11L);
    	
    	Task task2 = new Task();
    	task2.setId(12L);
    	
    	
    	// tasks should be sorted above non tasks
    	assertTrue(sorter.compare(item1, task1)>0);
    	
    	// tasks without contexts should be sorted by id
    	assertTrue(sorter.compare(task1, task2)<0);

    	task2.setContext("@context");
    	// task with contexts should be above tasks without contexts
    	assertTrue(sorter.compare(task1, task2)>0);
    	
    	task1.setContext("@Acontext");
    	// task contexts should be alphabetically sorted 
    	assertTrue(sorter.compare(task1, task2)<0);
    	
    	task2.setContext("@acontext");
    	// task contexts are case insensitive (fall back to natural sorting)
    	assertTrue(sorter.compare(task1, task2)<0);
    	
    	
    }
    
    
}
