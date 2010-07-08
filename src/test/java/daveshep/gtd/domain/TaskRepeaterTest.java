package daveshep.gtd.domain;

import java.text.ParseException;
import java.util.Date;


import daveshep.gtd.domain.TaskRepeater.RepeatInterval;
import daveshep.gtd.domain.TaskRepeater.RepeatType;
import daveshep.gtd.util.DateUtils;
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
    		repeater.setSpecialText("this should fail because type = none by default");
    		fail();
    	} catch (IllegalArgumentException e) {
    	}

    	repeater.setRepeatType(RepeatType.SPECIAL);
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

    public void testCalculateNextDueDate() {
    
    	TaskRepeater repeater = new TaskRepeater();
    	
    	try {
    		Date nextDueDate;
    	
    		// test no repeat
    		repeater.setRepeatType(RepeatType.NONE);
    		nextDueDate = repeater.calculateNextDueDate(DateUtils.dateFormat.parse("2010-01-01"));
    		assertTrue(DateUtils.dateFormat.format(nextDueDate).equals("2010-01-01"));
    		
    		// test daily repeat
    		repeater.setRepeatType(RepeatType.SIMPLE);
    		repeater.setRepeatInterval(RepeatInterval.DAILY);
    		nextDueDate = repeater.calculateNextDueDate(DateUtils.dateFormat.parse("2010-01-01"));
    		assertTrue(DateUtils.dateFormat.format(nextDueDate).equals("2010-01-02"));
    		nextDueDate = repeater.calculateNextDueDate(DateUtils.dateFormat.parse("2010-01-31"));
    		assertTrue(DateUtils.dateFormat.format(nextDueDate).equals("2010-02-01"));
    		nextDueDate = repeater.calculateNextDueDate(DateUtils.dateFormat.parse("2009-12-31"));
    		assertTrue(DateUtils.dateFormat.format(nextDueDate).equals("2010-01-01"));

    		// test weekly repeat
    		repeater.setRepeatType(RepeatType.SIMPLE);
    		repeater.setRepeatInterval(RepeatInterval.WEEKLY);
    		nextDueDate = repeater.calculateNextDueDate(DateUtils.dateFormat.parse("2010-01-01"));
    		assertTrue(DateUtils.dateFormat.format(nextDueDate).equals("2010-01-08"));
    		nextDueDate = repeater.calculateNextDueDate(DateUtils.dateFormat.parse("2010-01-31"));
    		assertTrue(DateUtils.dateFormat.format(nextDueDate).equals("2010-02-07"));
    		nextDueDate = repeater.calculateNextDueDate(DateUtils.dateFormat.parse("2009-12-31"));
    		assertTrue(DateUtils.dateFormat.format(nextDueDate).equals("2010-01-07"));
    		
    		// test bi-weekly repeat
    		repeater.setRepeatType(RepeatType.SIMPLE);
    		repeater.setRepeatInterval(RepeatInterval.BI_WEEKLY);
    		nextDueDate = repeater.calculateNextDueDate(DateUtils.dateFormat.parse("2010-01-01"));
    		assertTrue(DateUtils.dateFormat.format(nextDueDate).equals("2010-01-15"));
    		nextDueDate = repeater.calculateNextDueDate(DateUtils.dateFormat.parse("2010-01-31"));
    		assertTrue(DateUtils.dateFormat.format(nextDueDate).equals("2010-02-14"));
    		
    		// test monthly repeat
    		repeater.setRepeatType(RepeatType.SIMPLE);
    		repeater.setRepeatInterval(RepeatInterval.MONTHLY);
    		nextDueDate = repeater.calculateNextDueDate(DateUtils.dateFormat.parse("2010-01-01"));
    		assertTrue(DateUtils.dateFormat.format(nextDueDate).equals("2010-02-01"));
    		nextDueDate = repeater.calculateNextDueDate(DateUtils.dateFormat.parse("2010-01-31"));
    		assertTrue(DateUtils.dateFormat.format(nextDueDate).equals("2010-02-28"));
    		
    		
    	} catch (ParseException e) {
    		e.printStackTrace(System.out);
    	}
    	
    }
    
}
