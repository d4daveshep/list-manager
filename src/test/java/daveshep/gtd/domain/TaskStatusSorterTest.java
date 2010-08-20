package daveshep.gtd.domain;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TaskStatusSorterTest extends TestCase {

    public TaskStatusSorterTest( String testName ) {
        super( testName );
    }

    public static Test suite() {
        return new TestSuite( TaskStatusSorterTest.class );
    }

    
    public void testComparator() {

    	TaskStatusSorter sorter = new TaskStatusSorter();
    	
    	Task task1 = new Task();
    	task1.setId(11L);
    	
    	Task task2 = new Task();
    	task2.setId(12L);
    	
    	ListItem item1 = new ListItem();
    	item1.setId(1L);
    	
    	ListItem item2 = new ListItem();
    	item2.setId(2L);
    	
    	// neither are right type to compare so fall back to natural sorting
    	assertTrue(sorter.compare(item1,item2)<0);

    	// tasks are the same type with same (default NONE) status so use natural sorting
    	assertTrue(sorter.compare(task1,task2)<0);
    	
    	task1.setStatus(TaskStatus.CALENDAR);
    	assertTrue(sorter.compare(task1,task2)<0);

    	task2.setStatus(TaskStatus.NEXT_ACTION);
    	assertTrue(sorter.compare(task1, task2)>0);
    	
        	
    }
    
    
}
