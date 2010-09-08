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

    		dueDate = parser.parse("31");
    		assertTrue(DateUtils.xmlDateFormat.format(dueDate).equals("2010-09-30"));
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

        	dueDate = parser.parse("1/1");
    		assertTrue(DateUtils.xmlDateFormat.format(dueDate).equals("2011-01-01"));

    		try {
    			dueDate = parser.parse("29/2");
    		} catch ( ParseException e) {
    			assertTrue(true);
    		}

    		try {
    			dueDate = parser.parse("31/6");
    		} catch ( ParseException e) {
    			assertTrue(true);
    		}

    	} catch (ParseException e) {
    		e.printStackTrace();
    		fail();
    	}
    }
    
    public void testParser_ddMMyy() {
    	
    	DueDateParser parser = new DueDateParser();
    	
    	try {
        	parser.setToday(DateUtils.xmlDateFormat.parse("2010-09-07"));

        	Date dueDate = parser.parse("11/12/10");
    		assertTrue(DateUtils.xmlDateFormat.format(dueDate).equals("2010-12-11"));

        	dueDate = parser.parse("7/9/10");
    		assertTrue(DateUtils.xmlDateFormat.format(dueDate).equals("2010-09-07"));

    		try {
    			dueDate = parser.parse("1/1/10");
    		} catch (ParseException e) {
    			assertTrue(true);
    		}

    		try {
    			dueDate = parser.parse("06/09/10");
    		} catch (ParseException e) {
    			assertTrue(true);
    		}

    	} catch (ParseException e) {
    		e.printStackTrace();
    		fail();
    	}
    }
    
    public void testParser_ddMMyyyy() {
    	
    	DueDateParser parser = new DueDateParser();
    	
    	try {
        	parser.setToday(DateUtils.xmlDateFormat.parse("2010-09-07"));

        	Date dueDate = parser.parse("11/12/2010");
    		assertTrue(DateUtils.xmlDateFormat.format(dueDate).equals("2010-12-11"));

        	dueDate = parser.parse("7/9/2010");
    		assertTrue(DateUtils.xmlDateFormat.format(dueDate).equals("2010-09-07"));

    		try {
    			dueDate = parser.parse("1/1/2010");
    		} catch (ParseException e) {
    			assertTrue(true);
    		}

    		try {
    			dueDate = parser.parse("06/09/2010");
    		} catch (ParseException e) {
    			assertTrue(true);
    		}

    	} catch (ParseException e) {
    		e.printStackTrace();
    		fail();
    	}
    }
    
    public void testParser_EEE() {
    	
    	DueDateParser parser = new DueDateParser();
    	
    	try {
        	parser.setToday(DateUtils.xmlDateFormat.parse("2010-09-07")); // tuesday

        	Date dueDate = parser.parse("tue");
    		assertTrue(DateUtils.xmlDateFormat.format(dueDate).equals("2010-09-07"));

        	dueDate = parser.parse("wed");
    		assertTrue(DateUtils.xmlDateFormat.format(dueDate).equals("2010-09-08"));

        	dueDate = parser.parse("thu");
    		assertTrue(DateUtils.xmlDateFormat.format(dueDate).equals("2010-09-09"));

        	dueDate = parser.parse("thurs");
    		assertTrue(DateUtils.xmlDateFormat.format(dueDate).equals("2010-09-09"));

        	dueDate = parser.parse("monday");
    		assertTrue(DateUtils.xmlDateFormat.format(dueDate).equals("2010-09-13"));

    		try {
    			dueDate = parser.parse("blah");
    		} catch (ParseException e) {
    			assertTrue(true);
    		}

    		try {
    			dueDate = parser.parse("th");
    		} catch (ParseException e) {
    			assertTrue(true);
    		}

    	} catch (ParseException e) {
    		e.printStackTrace();
    		fail();
    	}
    }

    public void testParser_MMM() {
    	
    	DueDateParser parser = new DueDateParser();
    	
    	try {
        	parser.setToday(DateUtils.xmlDateFormat.parse("2010-09-07")); // september

        	Date dueDate = parser.parse("oct");
    		assertTrue(DateUtils.xmlDateFormat.format(dueDate).equals("2010-10-01"));

        	dueDate = parser.parse("Jan");
    		assertTrue(DateUtils.xmlDateFormat.format(dueDate).equals("2011-01-01"));

        	dueDate = parser.parse("MARCH");
    		assertTrue(DateUtils.xmlDateFormat.format(dueDate).equals("2011-03-01"));

    		try {
    			dueDate = parser.parse("blah");
    		} catch (ParseException e) {
    			assertTrue(true);
    		}

    	} catch (ParseException e) {
    		e.printStackTrace();
    		fail();
    	}
    }
    
    public void testParser_yyyy() {
    	
    	DueDateParser parser = new DueDateParser();
    	
    	try {
        	parser.setToday(DateUtils.xmlDateFormat.parse("2010-09-07")); // 2010

        	Date dueDate = parser.parse("2012");
    		assertTrue(DateUtils.xmlDateFormat.format(dueDate).equals("2012-01-01"));

    		try {
            	dueDate = parser.parse("2010");
    		} catch (ParseException e) {
    			assertTrue(true);
    		}

    		try {
    			dueDate = parser.parse("123");
    		} catch (ParseException e) {
    			assertTrue(true);
    		}

    		try {
    			dueDate = parser.parse("54321");
    		} catch (ParseException e) {
    			assertTrue(true);
    		}

    	} catch (ParseException e) {
    		e.printStackTrace();
    		fail();
    	}
    }
    
    public void testParser_ndwmy() {
    	
    	DueDateParser parser = new DueDateParser();
    	
    	try {
        	parser.setToday(DateUtils.xmlDateFormat.parse("2010-09-07"));

        	Date dueDate = parser.parse("2d"); // add 2 days
    		assertTrue(DateUtils.xmlDateFormat.format(dueDate).equals("2010-09-09"));

        	dueDate = parser.parse("20d"); // add 20 days
    		assertTrue(DateUtils.xmlDateFormat.format(dueDate).equals("2010-09-27"));

        	dueDate = parser.parse("3w"); // add 3 weeks
    		assertTrue(DateUtils.xmlDateFormat.format(dueDate).equals("2010-09-28"));

        	dueDate = parser.parse("6m"); // add 6 months
    		assertTrue(DateUtils.xmlDateFormat.format(dueDate).equals("2011-03-07"));

    		try {
            	dueDate = parser.parse("2010");
    		} catch (ParseException e) {
    			assertTrue(true);
    		}

    		try {
    			dueDate = parser.parse("123");
    		} catch (ParseException e) {
    			assertTrue(true);
    		}

    		try {
    			dueDate = parser.parse("54321");
    		} catch (ParseException e) {
    			assertTrue(true);
    		}

    	} catch (ParseException e) {
    		e.printStackTrace();
    		fail();
    	}
    }
}
