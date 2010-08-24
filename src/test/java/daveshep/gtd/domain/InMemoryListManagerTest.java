package daveshep.gtd.domain;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.SortedSet;

import daveshep.gtd.FilterSettings;
import daveshep.gtd.ListManager;
import daveshep.gtd.ViewSettings;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class InMemoryListManagerTest extends TestCase {

    public InMemoryListManagerTest( String testName ) {
        super( testName );
    }

    public static Test suite() {
        return new TestSuite( InMemoryListManagerTest.class );
    }
    
    public void testCreateGoal() {
    	
    	ListManager manager = InMemoryListManager.getInstance();
    	manager.removeAll();
    	
    	Goal goal = manager.createGoal();
    	assertTrue(manager.getGoals().size()==1);
    	
    }
    
    public void testCreateTask() {
    	
    	ListManager manager = InMemoryListManager.getInstance();
    	manager.removeAll();
    	
    	Task task1 = manager.createTask();
    	task1.setDescription("task1");
    	
    	Task task2 = manager.createTask();
    	task2.setDescription("task2");
    	
    	assertTrue(manager.getTasks().size()==2);
    	    	
    }

    public void testCreateProjectAndTasks() {
    	
    	ListManager manager = InMemoryListManager.getInstance();
    	manager.removeAll();
    	
    	Project project1 = manager.createProject();
    	project1.setDescription("project1");
    	assertTrue(manager.getProjects().size()==1);
    	
    	Task task1 = manager.createTask();
    	task1.setDescription("task1");
    	
    	Task task2 = manager.createTask();
    	task2.setDescription("task2");

    	assertTrue(manager.getTasks().size()==2);
    	
    	project1.addSubTask(task1);
    	project1.addSubTask(task2);
    	
    	assertTrue(manager.getTasks().size()==2);
    	
    	assertTrue(project1.getSubProjectsOrTasks().size()==2);

    	Task task3 = manager.createTask();
    	task3.setDescription("task3");

    	Task task4 = manager.createTask();
    	task4.setDescription("task4");
    	
    	Project project2 = manager.createProject();
    	project2.setDescription("project2");
   	
    	project2.addSubTask(task3);
    	project2.addSubTask(task4);
    	project1.addSubProject(project2);
    	
    	assertTrue(manager.getTasks().size()==4);
    	assertTrue(manager.getProjects().size()==2);
    	
    	assertTrue(project1.getSubProjectsOrTasks().size()==3);
    	
    }
    
    public void testFindItemById() {

    	ListManager manager = InMemoryListManager.getInstance();
    	manager.removeAll();
    	
    	Task task1 = manager.createTask();
    	task1.setId(Long.valueOf("12345"));
    	task1.setDescription("task1");
    	
    	Task task2 = manager.createTask();
    	task2.setDescription("task2");
    	task2.setId(Long.valueOf("67890"));
    	
    	ListItem find1 = manager.findItemById(Long.valueOf("12345"));
    	
    	assertTrue(find1.equals(task1));
    	    	
    }
    
    public void testFindByString() {

    	ListManager manager = InMemoryListManager.getInstance();
    	manager.removeAll();
    	
    	Task task1 = manager.createTask();
    	task1.setId(Long.valueOf("12345"));
    	task1.setDescription("this is task1 - isn't it good");
    	
    	Task task2 = manager.createTask();
    	task2.setDescription("this is task2 - isn't it fine");
    	task2.setId(Long.valueOf("67890"));
    	
    	Task task3 = manager.createTask();
    	task3.setDescription("this is task3 - isn't it fine and swell");
    	task3.setId(Long.valueOf("54321"));
    	
    	List<ListItem> find1 = manager.findItemsByString("swell");
    	assertTrue(find1.size()==1);
    	
    	List<ListItem> find2 = manager.findItemsByString("fine");
    	assertTrue(find2.size()==2);
    	
    	List<ListItem> find3 = manager.findItemsByString("task");
    	assertTrue(find3.size()==3);
    	
    }
    
//    public void testFindByStringWithFilter() {
//
//    	ListManager manager = InMemoryListManager.getInstance();
//    	manager.removeAll();
//    	FilterSettings filterSettings = new FilterSettings();
//    	
//    	Task task1 = manager.createTask();
//    	task1.setId(Long.valueOf("12345"));
//    	task1.setDescription("this is task1 - isn't it good");
//    	
//    	Task task2 = manager.createTask();
//    	task2.setDescription("this is task2 - isn't it fine");
//    	task2.setId(Long.valueOf("67890"));
//    	
//    	Task task3 = manager.createTask();
//    	task3.setDescription("this is task3 - isn't it fine and swell");
//    	task3.setId(Long.valueOf("54321"));
//    	
//    	List<ListItem> find1 = manager.findItemsByString("swell");
//    	assertTrue(find1.size()==1);
//    	
//    	List<ListItem> find2 = manager.findItemsByString("fine");
//    	assertTrue(find2.size()==2);
//    	
//    	List<ListItem> find3 = manager.findItemsByString("task");
//    	assertTrue(find3.size()==3);
//    	
//    }
    
    public void testRemove() {

    	ListManager manager = InMemoryListManager.getInstance();
    	manager.removeAll();
    	
    	Task task1 = manager.createTask();
    	task1.setId(Long.valueOf("12345"));
    	task1.setDescription("this is task1 - isn't it good");
    	
    	Task task2 = manager.createTask();
    	task2.setDescription("this is task2 - isn't it fine");
    	task2.setId(Long.valueOf("67890"));
    	
    	Task task3 = manager.createTask();
    	task3.setDescription("this is task3 - isn't it fine and swell");
    	task3.setId(Long.valueOf("54321"));
    	
    	manager.remove(task2);
    	assertTrue(manager.findItemById(Long.valueOf("67890"))==null);
    	
    }
    
    public void testGetFolders() {

    	ListManager manager = InMemoryListManager.getInstance();
    	manager.removeAll();
 
       	Task task1 = manager.createTask();
    	task1.setId(Long.valueOf("12345"));
    	task1.setDescription("this is task1 - isn't it good");
    	task1.setFolder("p.Family");
    	
    	Task task2 = manager.createTask();
    	task2.setDescription("this is task2 - isn't it fine");
    	task2.setId(Long.valueOf("67890"));
    	task2.setFolder("w.HR");
    	
    	Task task3 = manager.createTask();
    	task3.setDescription("this is task3 - isn't it fine and swell");
    	task3.setId(Long.valueOf("54321"));
    	task3.setFolder("h.Finance");
 
    	Task task4 = manager.createTask();
    	task4.setDescription("this is task4 - isn't it boring");
    	task4.setId(Long.valueOf("09876"));
    	task4.setFolder("p.Family");
 
    	String[] folderSet = manager.getFolders();
    	assertTrue(folderSet.length==3);
    	assertTrue(folderSet[1].equals("p.Family"));
    	assertTrue(folderSet[2].equals("w.HR"));
    	assertTrue(folderSet[0].equals("h.Finance"));
    	
    }
    public void testGetContexts() {

    	ListManager manager = InMemoryListManager.getInstance();
    	manager.removeAll();
 
       	Task task1 = manager.createTask();
    	task1.setId(Long.valueOf("12345"));
    	task1.setDescription("this is task1 - isn't it good");
    	task1.setContext("@Home");
    	
    	Task task2 = manager.createTask();
    	task2.setDescription("this is task2 - isn't it fine");
    	task2.setId(Long.valueOf("67890"));
    	task2.setContext("@WorkPC");
    	
    	Task task3 = manager.createTask();
    	task3.setDescription("this is task3 - isn't it fine and swell");
    	task3.setId(Long.valueOf("54321"));
    	task3.setContext("@Office");
 
    	Task task4 = manager.createTask();
    	task4.setDescription("this is task4 - isn't it boring");
    	task4.setId(Long.valueOf("09876"));
    	task4.setContext("@WorkPC");
 
    	String[] contextSet = manager.getTaskContexts();
    	assertTrue(contextSet.length==3);
    	assertTrue(contextSet[1].equals("@Office"));
    	assertTrue(contextSet[2].equals("@WorkPC"));
    	assertTrue(contextSet[0].equals("@Home"));
    	
    }
    
    public void testNestedView() {
    	ListManager manager = InMemoryListManager.getInstance();
    	manager.removeAll();
 
    	Random random = new Random();
    	
    	Project project = manager.createProject();
    	project.setDescription("project - dad");
    	project.setId(random.nextLong());
    	
    	Task task1 = manager.createTask();
    	task1.setDescription("task1 - brother");
    	task1.setId(random.nextLong());
    	project.addSubTask(task1);
    	
    	Task task2 = manager.createTask();
    	task2.setDescription("task2 - sister");
    	task2.setId(random.nextLong());
    	project.addSubTask(task2);
    	
    	Task task21 = manager.createTask();
    	task21.setDescription("task21 - baby sister");
    	task21.setId(random.nextLong());
    	task2.addChildItem(task21);
    	
    	Project project2 = manager.createProject();
    	project2.setId(random.nextLong());
    	project2.setDescription("project2");
    	
    	Task task3 = manager.createTask();
    	task3.setId(random.nextLong());
    	task3.setDescription("task3 - baby");

    	assertTrue(project.hasChildren());
    	assertTrue(project.getChildItems().size()==2);
    	
    	Set foundItems;
    	foundItems = manager.findItemsByString("dad", null, null, null);
    	assertTrue(foundItems.size()==1);
    	assertTrue(foundItems.contains(project));
    
    	ViewSettings view = new ViewSettings();
    	view.showSubItemsNested = false;
    	foundItems = manager.findItemsByString("dad",null,null,view);
    	assertTrue(foundItems.size()==1);
    	assertTrue(foundItems.contains(project));
    	
    	view.showSubItemsNested = true;
    	foundItems = manager.findItemsByString("dad",null,null,view);
    	assertTrue(foundItems.size()==4);
    	assertTrue(foundItems.contains(project));
    	
    	foundItems = manager.findItemsByString("brother",null,null,view);
    	assertTrue(foundItems.size()==4);
    	
    	foundItems = manager.findItemsByString("sister",null,null,view);
    	assertTrue(foundItems.size()==4);
    	
    	foundItems = manager.findItemsByString("baby",null,null,view);
       	System.out.println(foundItems);
    	assertTrue(foundItems.size()==5);
    	
    }
    
}
