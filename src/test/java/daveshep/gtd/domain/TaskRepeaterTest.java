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

    public void testCalculateNextDueDate_Simple() {
    
    	TaskRepeater repeater = new TaskRepeater();
    	
    	try {
    		Date nextDueDate;
    	
    		// test no repeat
    		repeater.setRepeatType(RepeatType.NONE);
    		nextDueDate = repeater.calculateNextDueDate(DateUtils.xmlDateFormat.parse("2010-01-01"));
    		assertTrue(DateUtils.xmlDateFormat.format(nextDueDate).equals("2010-01-01"));
    		
    		// test daily repeat
    		repeater.setRepeatType(RepeatType.SIMPLE);
    		repeater.setRepeatInterval(RepeatInterval.DAILY);
    		nextDueDate = repeater.calculateNextDueDate(DateUtils.xmlDateFormat.parse("2010-01-01"));
    		assertTrue(DateUtils.xmlDateFormat.format(nextDueDate).equals("2010-01-02"));
    		nextDueDate = repeater.calculateNextDueDate(DateUtils.xmlDateFormat.parse("2010-01-31"));
    		assertTrue(DateUtils.xmlDateFormat.format(nextDueDate).equals("2010-02-01"));
    		nextDueDate = repeater.calculateNextDueDate(DateUtils.xmlDateFormat.parse("2009-12-31"));
    		assertTrue(DateUtils.xmlDateFormat.format(nextDueDate).equals("2010-01-01"));

    		// test weekly repeat
    		repeater.setRepeatType(RepeatType.SIMPLE);
    		repeater.setRepeatInterval(RepeatInterval.WEEKLY);
    		nextDueDate = repeater.calculateNextDueDate(DateUtils.xmlDateFormat.parse("2010-01-01"));
    		assertTrue(DateUtils.xmlDateFormat.format(nextDueDate).equals("2010-01-08"));
    		nextDueDate = repeater.calculateNextDueDate(DateUtils.xmlDateFormat.parse("2010-01-31"));
    		assertTrue(DateUtils.xmlDateFormat.format(nextDueDate).equals("2010-02-07"));
    		nextDueDate = repeater.calculateNextDueDate(DateUtils.xmlDateFormat.parse("2009-12-31"));
    		assertTrue(DateUtils.xmlDateFormat.format(nextDueDate).equals("2010-01-07"));
    		
    		// test bi-weekly repeat
    		repeater.setRepeatType(RepeatType.SIMPLE);
    		repeater.setRepeatInterval(RepeatInterval.BI_WEEKLY);
    		nextDueDate = repeater.calculateNextDueDate(DateUtils.xmlDateFormat.parse("2010-01-01"));
    		assertTrue(DateUtils.xmlDateFormat.format(nextDueDate).equals("2010-01-15"));
    		nextDueDate = repeater.calculateNextDueDate(DateUtils.xmlDateFormat.parse("2010-01-31"));
    		assertTrue(DateUtils.xmlDateFormat.format(nextDueDate).equals("2010-02-14"));
    		
    		// test monthly repeat
    		repeater.setRepeatType(RepeatType.SIMPLE);
    		repeater.setRepeatInterval(RepeatInterval.MONTHLY);
    		nextDueDate = repeater.calculateNextDueDate(DateUtils.xmlDateFormat.parse("2010-01-01"));
    		assertTrue(DateUtils.xmlDateFormat.format(nextDueDate).equals("2010-02-01"));
    		nextDueDate = repeater.calculateNextDueDate(DateUtils.xmlDateFormat.parse("2010-01-31"));
    		assertTrue(DateUtils.xmlDateFormat.format(nextDueDate).equals("2010-02-28"));
    		
    		// test bi-monthly repeat
    		repeater.setRepeatType(RepeatType.SIMPLE);
    		repeater.setRepeatInterval(RepeatInterval.BI_MONTHLY);
    		nextDueDate = repeater.calculateNextDueDate(DateUtils.xmlDateFormat.parse("2010-01-01"));
    		assertTrue(DateUtils.xmlDateFormat.format(nextDueDate).equals("2010-03-01"));
    		nextDueDate = repeater.calculateNextDueDate(DateUtils.xmlDateFormat.parse("2010-01-31"));
    		assertTrue(DateUtils.xmlDateFormat.format(nextDueDate).equals("2010-03-31"));
    		
    		// test quarterly repeat
    		repeater.setRepeatType(RepeatType.SIMPLE);
    		repeater.setRepeatInterval(RepeatInterval.QUARTERLY);
    		nextDueDate = repeater.calculateNextDueDate(DateUtils.xmlDateFormat.parse("2010-01-01"));
    		assertTrue(DateUtils.xmlDateFormat.format(nextDueDate).equals("2010-04-01"));
    		nextDueDate = repeater.calculateNextDueDate(DateUtils.xmlDateFormat.parse("2010-01-31"));
    		assertTrue(DateUtils.xmlDateFormat.format(nextDueDate).equals("2010-04-30"));
    		
    		// test half yearly repeat
    		repeater.setRepeatType(RepeatType.SIMPLE);
    		repeater.setRepeatInterval(RepeatInterval.HALF_YEARLY);
    		nextDueDate = repeater.calculateNextDueDate(DateUtils.xmlDateFormat.parse("2010-01-01"));
    		assertTrue(DateUtils.xmlDateFormat.format(nextDueDate).equals("2010-07-01"));
    		nextDueDate = repeater.calculateNextDueDate(DateUtils.xmlDateFormat.parse("2010-01-31"));
    		assertTrue(DateUtils.xmlDateFormat.format(nextDueDate).equals("2010-07-31"));
    		
    		// test yearly repeat
    		repeater.setRepeatType(RepeatType.SIMPLE);
    		repeater.setRepeatInterval(RepeatInterval.YEARLY);
    		nextDueDate = repeater.calculateNextDueDate(DateUtils.xmlDateFormat.parse("2010-01-01"));
    		assertTrue(DateUtils.xmlDateFormat.format(nextDueDate).equals("2011-01-01"));
    		nextDueDate = repeater.calculateNextDueDate(DateUtils.xmlDateFormat.parse("2010-01-31"));
    		assertTrue(DateUtils.xmlDateFormat.format(nextDueDate).equals("2011-01-31"));
    		
    		
    	} catch (ParseException e) {
    		e.printStackTrace(System.out);
    	}
    	
    }

    public void testCalculateNextDueDate_Special() {
        
    	TaskRepeater repeater = new TaskRepeater();
    	
    	try {
    		Date nextDueDate;
    	
    		repeater.setRepeatType(RepeatType.SPECIAL);

    		// test 1. "every <weekday>"
    		repeater.setSpecialText("every monday");
    		nextDueDate = repeater.calculateNextDueDate(DateUtils.xmlDateFormat.parse("2010-01-01"));
    		assertTrue(DateUtils.xmlDateFormat.format(nextDueDate).equals("2010-01-04"));
    		repeater.setSpecialText("every sunday");
    		nextDueDate = repeater.calculateNextDueDate(DateUtils.xmlDateFormat.parse("2010-01-01"));
    		assertTrue(DateUtils.xmlDateFormat.format(nextDueDate).equals("2010-01-03"));
    		repeater.setSpecialText("every friday");
    		nextDueDate = repeater.calculateNextDueDate(DateUtils.xmlDateFormat.parse("2010-01-01"));
    		assertTrue(DateUtils.xmlDateFormat.format(nextDueDate).equals("2010-01-08"));

    		// test 2. "every <X> <T>"
    		repeater.setSpecialText("every 2 days");
    		nextDueDate = repeater.calculateNextDueDate(DateUtils.xmlDateFormat.parse("2010-01-01"));
    		assertTrue(DateUtils.xmlDateFormat.format(nextDueDate).equals("2010-01-03"));
    		nextDueDate = repeater.calculateNextDueDate(DateUtils.xmlDateFormat.parse("2009-12-31"));
    		assertTrue(DateUtils.xmlDateFormat.format(nextDueDate).equals("2010-01-02"));
    		
    		repeater.setSpecialText("every 3 weeks");
    		nextDueDate = repeater.calculateNextDueDate(DateUtils.xmlDateFormat.parse("2010-01-01"));
    		assertTrue(DateUtils.xmlDateFormat.format(nextDueDate).equals("2010-01-22"));
    		
    		repeater.setSpecialText("every 5 months");
    		nextDueDate = repeater.calculateNextDueDate(DateUtils.xmlDateFormat.parse("2010-01-01"));
    		assertTrue(DateUtils.xmlDateFormat.format(nextDueDate).equals("2010-06-01"));
    		
    		// test 3. "the <N> <W> of every month"
    		repeater.setSpecialText("the first monday of every month");
    		nextDueDate = repeater.calculateNextDueDate(DateUtils.xmlDateFormat.parse("2010-01-01"));
    		assertTrue(DateUtils.xmlDateFormat.format(nextDueDate).equals("2010-01-04"));
    		nextDueDate = repeater.calculateNextDueDate(DateUtils.xmlDateFormat.parse("2010-01-06"));
    		assertTrue(DateUtils.xmlDateFormat.format(nextDueDate).equals("2010-02-01"));
    		
    		repeater.setSpecialText("the second saturday of every month");
    		nextDueDate = repeater.calculateNextDueDate(DateUtils.xmlDateFormat.parse("2010-01-01"));
    		assertTrue(DateUtils.xmlDateFormat.format(nextDueDate).equals("2010-01-09"));
    		nextDueDate = repeater.calculateNextDueDate(DateUtils.xmlDateFormat.parse("2010-01-20"));
    		assertTrue(DateUtils.xmlDateFormat.format(nextDueDate).equals("2010-02-13"));
    		
    		
    	} catch (IllegalArgumentException e) {
    		e.printStackTrace(System.out);
    	} catch (ParseException e) {
    		e.printStackTrace(System.out);
    	}
    }
}
