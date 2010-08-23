package daveshep.gtd.domain;

import daveshep.gtd.FilterSettings;
import daveshep.gtd.domain.ListItem;
import daveshep.gtd.util.DateUtils;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TaskTest extends TestCase {

    public TaskTest( String testName ) {
        super( testName );
    }

    public static Test suite() {
        return new TestSuite( TaskTest.class );
    }

    
    public void testPassesFilter() {

    	Task task = new Task();
    	FilterSettings filterSettings = new FilterSettings();
    	
    	filterSettings.showTasks = true; // show task
    	filterSettings.taskStatus = "all";
    	assertTrue(task.passesFilter(filterSettings)); // show item
    	
    	filterSettings.showTasks = false; // don't show tasks
    	assertFalse(task.passesFilter(filterSettings)); // don't show item
    	
    	filterSettings.showTasks = true; // show task
    	task.setStatus(TaskStatus.DEPENDS_ON);
    	filterSettings.taskStatus = TaskStatus.DEPENDS_ON.name();
    	assertTrue(task.passesFilter(filterSettings)); // show item
    	
    	filterSettings.taskStatus = TaskStatus.NEXT_ACTION.name();
    	assertFalse(task.passesFilter(filterSettings));
    	
    	task = new Task();
    	filterSettings = new FilterSettings();
    	
    	filterSettings.taskContext = "None";
    	task.setContext(null);
    	assertTrue(task.passesFilter(filterSettings)); // show item
    	task.setContext("");
    	assertTrue(task.passesFilter(filterSettings)); // show item
    	task.setContext("context");
    	assertFalse(task.passesFilter(filterSettings)); // show item

    }
    
    
}
