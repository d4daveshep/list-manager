package daveshep.gtd.domain;

import java.text.ParseException;
import java.util.Date;

import daveshep.gtd.util.DateUtils;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class DueDateParserTest extends TestCase {

    public DueDateParserTest( String testName ) {
        super( testName );
    }

    public static Test suite() {
        return new TestSuite( DueDateParserTest.class );
    }


    public void testParser_dd() {
    	
    	DueDateParser parser = new DueDateParser();
    	
    	try {
        	parser.setToday(DateUtils.xmlDateFormat.parse("2010-09-07"));

        	Date dueDate = parser.parse("10");
    		assertTrue(DateUtils.xmlDateFormat.format(dueDate).equals("2010-09-10"));

    		dueDate = parser.parse("2");
    		assertTrue(DateUtils.xmlDateFormat.format(dueDate).equals("2010-10-02"));
    	} catch (ParseException e) {
    		e.printStackTrace();
    	}
    }
    
    public void testParser_ddMM() {
    	
    	DueDateParser parser = new DueDateParser();
    	
    	try {
        	parser.setToday(DateUtils.xmlDateFormat.parse("2010-09-07"));

        	Date dueDate = parser.parse("11/12");
    		assertTrue(DateUtils.xmlDateFormat.format(dueDate).equals("2010-12-11"));
    	} catch (ParseException e) {
    		e.printStackTrace();
    	}
    }
    
}
