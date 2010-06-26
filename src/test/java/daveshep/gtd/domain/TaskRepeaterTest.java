package daveshep.gtd.domain;

import daveshep.gtd.domain.TaskRepeater.RepeatType;
import daveshep.gtd.util.ToodledoXMLImporterTest;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TaskRepeaterTest extends TestCase {

    public TaskRepeaterTest( String testName ) {
        super( testName );
    }

    public static Test suite() {
        return new TestSuite( TaskRepeaterTest.class );
    }
    
    public void testValidation() {
    	TaskRepeater repeater = new TaskRepeater();
    	try {
    		repeater.setSpecialText("this should fail");
    		fail();
    	} catch (IllegalArgumentException e) {
    	}

    	repeater.setType(RepeatType.SPECIAL);
    	try {
    		repeater.setSpecialText("this should fail validation");
    		fail();
    	} catch (IllegalArgumentException e) {
    	}

    	repeater.setSpecialText("every monday");
    	
    	
    }

}
