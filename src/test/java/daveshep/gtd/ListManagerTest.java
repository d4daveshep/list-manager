package daveshep.gtd;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import daveshep.gtd.domain.ListItem;
import daveshep.gtd.domain.ListItemType;
import daveshep.gtd.domain.Project;
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

    public void test() {
    	assertTrue(true);
    }
    /*
    public void testAddListItem() {
    	ListManager manager = new ListManager();
    	ListItem li = new ListItem();
    	li.setManager(manager);
    	li.setType(ListItemType.TASK);
    	li.setDescription("My first list item");
    	manager.addListItem(li);
    	assertTrue( true );
    }
    
    public void testGetListItems() {
    	ListManager manager = new ListManager();
    	List items = manager.getItems();
    	for(Iterator<ListItem> iter=items.iterator();iter.hasNext();) {
    		System.out.println(iter.next().toString());
    	}
    	assertTrue( true );
    	
    }
    
    public void testAddTask() {
    	ListManager manager = new ListManager();
    	Task t = new Task();
    	t.setManager(manager);
    	t.setDescription("My first task");
    	manager.addListItem(t);
    	assertTrue( true );
    }
*/
/*    
    public void testAddProjectWithSubtasks() {
    	ListManager manager = new ListManager();
    	Project p = new Project("My first project");
    	p.setManager(manager);
    	Task t1 = new Task("My first subtask");
    	Task t2 = new Task("My second subtask");
    	p.addItem(t1);
    	p.addItem(t2);
    	manager.addListItem(p);
    	
    	assertTrue(manager.getItems().size()==3);
    }
*/
}
