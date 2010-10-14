package daveshep.gtd.domain;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ListKeyTest extends TestCase {

	public ListKeyTest( String testName ) {
        super( testName );
    }

    public static Test suite() {
        return new TestSuite( ListKeyTest.class );
    }

    public void testImmutable() {
    	fail();
    }

}
