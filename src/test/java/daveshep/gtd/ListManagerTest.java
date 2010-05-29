package daveshep.gtd;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import daveshep.gtd.domain.ListItem;
import daveshep.gtd.domain.ListItemType;
import daveshep.gtd.domain.Project;
import daveshep.gtd.domain.Task;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ListManagerTest extends TestCase {

    public ListManagerTest( String testName ) {
        super( testName );
    }

    public static Test suite() {
        return new TestSuite( ListManagerTest.class );
    }

    public void test() {
    	assertTrue(true);
    }

}
