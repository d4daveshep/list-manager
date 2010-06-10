package daveshep.gtd.util;

import java.io.File;
import java.io.FileNotFoundException;

import daveshep.gtd.ListManager;
import daveshep.gtd.domain.InMemoryListManager;
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
    	
    		assertTrue(true);	
    	
    	} catch (FileNotFoundException e) {
    		e.printStackTrace(System.out);
    		fail();
    	}
    	
    }
    
    
    
}
