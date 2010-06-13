package daveshep.gtd.util;

import java.io.File;
import java.io.FileNotFoundException;

import daveshep.gtd.ListManager;
import daveshep.gtd.domain.InMemoryListManager;
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

    		Task task = manager.findTaskById(Long.valueOf("97069063"));
    		assertTrue(task!=null);
    		
    		assertTrue(task.getDescription().equals("Discuss Richard Nguyen's hours"));
    		System.out.println("tags = " + task.getTagsString());
    		assertTrue(task.hasTag("Ker Win"));
    		assertTrue(task.getContext().equals("@Agenda"));	
    		assertTrue(task.getFolder().equals("w.HR"));	
    		assertTrue(task.getNotes().equals("18/5 start 9am"));
    		assertFalse(task.isDone());
    		assertFalse(task.isStarflag());
    	
    	} catch (FileNotFoundException e) {
    		e.printStackTrace(System.out);
    		fail();
    	}
    	
    }
    
    
    
}
