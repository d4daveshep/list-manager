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
    
    public void testSpecialTextValidation() {
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
    	try {
    		repeater.setSpecialText("every weekday");
    		fail();
    	} catch (IllegalArgumentException e) {
    	}
    	
    	repeater.setSpecialText("every 2 days");
    	repeater.setSpecialText("every 3 months");
    	
    	repeater.setSpecialText("the first Monday of every month");
    	repeater.setSpecialText("the second Tuesday of every month");
    	repeater.setSpecialText("the third wednesday of every month");
    	repeater.setSpecialText("the last friday of every month");

    	
    }

}
