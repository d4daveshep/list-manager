package daveshep.gtd.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import daveshep.gtd.ListManager;
import daveshep.gtd.domain.InMemoryListManager;
import daveshep.gtd.domain.ListItem;
import daveshep.gtd.domain.ListItemType;
import daveshep.gtd.domain.Project;
import daveshep.gtd.domain.ProjectStatus;
import daveshep.gtd.domain.Task;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ToodledoXMLImporterTest extends TestCase {

    public ToodledoXMLImporterTest( String testName ) {
        super( testName );
    }

    public static Test suite() {
        return new TestSuite( ToodledoXMLImporterTest.class );
    }

    public void testDoImport() {
    	
    	ListManager manager = InMemoryListManager.getInstance();
    	File xmlFile = new File("src/test/resources/data/toodledo.20100608.xml");
    	System.out.println(xmlFile.getAbsolutePath());
    	
    	try {
    		ToodledoXMLImporter importer = new ToodledoXMLImporter(manager,xmlFile);
    		importer.doImport();

    		// test a task with no parent
    		Task task = manager.findTaskById(Long.valueOf("97069063"));
    		assertTrue(task!=null);
    		assertTrue(task.getDescription().equals("Discuss Richard Nguyen's hours"));
    		assertTrue(task.hasTag("Ker Win"));
    		assertTrue(task.getContext().equals("@Agenda"));	
    		assertTrue(task.getFolder().equals("w.HR"));	
    		assertTrue(task.getNotes().equals("18/5 start 9am"));
    		assertFalse(task.isDone());
    		assertFalse(task.isStarflag());
    		assertTrue(task.getDueDate()==null);
    		
    		// test a child task
    		task = manager.findTaskById(Long.valueOf("94866093"));
    		assertTrue(task!=null);
    		ListItem parent = task.getParentItem();
    		assertTrue(parent!=null);
    		assertTrue(parent.getType()==ListItemType.PROJECT);
    		assertTrue(parent.getId().equals(Long.valueOf("82857917")));
    		assertTrue(task.getDescription().equals("Do some Integrit-e training"));
    		assertFalse(task.hasTags());
    		assertTrue(task.getContext().equals("@Work PC"));	
    		assertTrue(task.getFolder().equals("w.Career"));	
    		assertTrue(task.getNotes().isEmpty());
    		assertFalse(task.isDone());
    		assertFalse(task.isStarflag());
    		assertTrue(task.getDueDate().equals(DateUtils.xmlDateFormat.parse("2010-06-14")));
    		
    		// test a project with subtasks
    		Project project = manager.findProjectById(Long.valueOf("99076819"));
    		assertTrue(project!=null);
    		assertTrue(project.hasChildren());
    		List subTasks = project.getSubProjectsOrTasks();
    		task = manager.findTaskById(Long.valueOf("99076827"));
    		assertTrue(task!=null);
    		assertTrue(subTasks.contains(task));
    		task = manager.findTaskById(Long.valueOf("99076825"));
    		assertTrue(task!=null);
    		assertTrue(subTasks.contains(task));
    		assertTrue(task.isDone());
    		assertTrue(task.getCompletedDate().equals(DateUtils.xmlDateFormat.parseObject("2010-05-10")));
    		
    		assertTrue(project.getStatus()==ProjectStatus.ACTIVE);
    		assertFalse(project.isDone());
    		
    	
    	} catch (Exception e) {
    		e.printStackTrace(System.out);
    		fail();
    	}
    	
    }
    
    
    
}
