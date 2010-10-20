package daveshep.gtd.util;

import java.util.Set;
import java.util.SortedMap;
import java.util.logging.Logger;

import daveshep.gtd.GtdListException;
import daveshep.gtd.GtdListManager;
import daveshep.gtd.StaticLists;
import daveshep.gtd.domain.GtdList;
import daveshep.gtd.domain.InMemoryListManager;
import daveshep.gtd.domain.ListKeyTest;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ListKeyUtilsTest extends TestCase {

	private Logger logger = Logger.getLogger("daveshep.gtd");
	private GtdListManager listManager = InMemoryListManager.getInstance();

	public ListKeyUtilsTest( String testName ) {
        super( testName );
    }

    public static Test suite() {
        return new TestSuite( ListKeyUtilsTest.class );
    }

    public void setUp() throws GtdListException {

    	StaticLists.createStaticLists(listManager);

		GtdList agendaMark = listManager.createList("@Agenda", "Mark");
		GtdList agendaSteve = listManager.createList("@Agenda", "Steve");
		GtdList agendaDave = listManager.createList("@Agenda", "Dave");
		
		GtdList homeInside = listManager.createList("@Home","Inside");
		GtdList homeOutside = listManager.createList("@Home","Outside");
		GtdList homeGarage = listManager.createList("@Home","Garage");
		
		GtdList placeMeehans = listManager.createList("@Place", "Meehans");
		GtdList placeNapier = listManager.createList("@Place", "Napier");
		
		GtdList errandsDickSmith = listManager.createList("@Place", "Dick Smith");
		GtdList errandsMitre10 = listManager.createList("@Place", "Mitre10");

    }
    
    public void testBuildSortedMap() {
    	
    	SortedMap<String, Set<String>> map = ListKeyUtils.buildSortedMap(listManager.getListKeys());

    	assertTrue(map.containsKey("@Agenda"));
    	assertTrue(map.containsKey("@Home"));
    	assertTrue(map.containsKey("@Place"));
    	
    	Set<String> agendaSet = map.get("@Agenda");
    	assertTrue(agendaSet.contains("Mark"));
    	assertTrue(agendaSet.contains("Steve"));
    	assertTrue(agendaSet.contains("Dave"));
    	
    	Set<String> homeSet = map.get("@Home");
    	assertTrue(homeSet.contains("Inside"));
    	
    	Set<String> placeSet = map.get("@Place");
    	assertTrue(placeSet.contains("Napier"));
    	
    	logger.info(map.toString());
    	
    }

}
