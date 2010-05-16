package daveshep.gtd;

import daveshep.gtd.domain.Task;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ListManagerTest extends TestCase {

    public ListManagerTest( String testName ) {
        super( testName );
    }

    public static Test suite() {
        return new TestSuite( ListManagerTest.class );
    }

    public void testAddTask() {

    	ListManager manager = new ListManager();
    	manager.addTask( new Task("My first task") );
    	assertTrue( true );
    	
    }
}
