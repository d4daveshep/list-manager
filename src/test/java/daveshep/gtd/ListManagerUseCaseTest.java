package daveshep.gtd;

import daveshep.gtd.domain.GtdList;
import daveshep.gtd.domain.GtdListItem;
import daveshep.gtd.domain.GtdListItemTest;
import daveshep.gtd.domain.InMemoryListManager;
import daveshep.gtd.domain.ListKey;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


public class ListManagerUseCaseTest extends TestCase {

	public ListManagerUseCaseTest( String testName ) {
        super( testName );
    }

    public static Test suite() {
        return new TestSuite( ListManagerUseCaseTest.class );
    }

    GtdListManager listManager = null;
    
    @Override
	protected void setUp() throws Exception {
		super.setUp();
    	listManager = InMemoryListManager.getInstance();
		StaticLists.createStaticLists(listManager);
	}
    
    public void testCreateList() {
    	try {
			GtdList list1 = listManager.createList("list1");
			assertNotNull(list1);
			assertEquals(0,list1.size());
		} catch (GtdListException e) {
			e.printStackTrace();
	    	fail();
		}

		try {
			GtdList list11 = listManager.createList("list1");
			fail();
		} catch (DuplicateListException e) {
			assertTrue(true);
		}
    }
    
    public void testGetListByList() {
		try {
			GtdList inList = listManager.getList(StaticLists.IN);
			assertNotNull(inList);
		} catch (GtdListException e1) {
			e1.printStackTrace();
			fail();
		}

		GtdList findThisList = null;
		try {
			GtdList aList = listManager.getList(findThisList);
			fail();
		} catch (GtdListException e) {
		}
		
    }

    public void testGetListByKey() {
    	GtdList inList = listManager.getList(new ListKey("IN"));
    	assertNotNull(inList);
    	
    	GtdList newList = listManager.getList(new ListKey("new list"));
    	assertNotNull(newList);
    	
    	GtdList newListToo = listManager.getList(new ListKey("new list"));
    	assertEquals(newList, newListToo);
    }
    
    public void testCreateNewListItem() {
    	
    	GtdList inList = null;
		GtdList wfList = null;
		try {
			inList = listManager.getList(StaticLists.IN);
			wfList = listManager.getList(StaticLists.WAITING_FOR);
		} catch (GtdListException e1) {
			e1.printStackTrace();
			fail();
		}
    	
    	// create a new list item
    	GtdListItem item = listManager.createListItem("my first list item");
    	assertTrue(item.getDescription().equals("my first list item"));
    	
    	// add it to a list
    	try {
        	inList.add(item);
        	assertTrue(inList.contains(item));
        	assertTrue(inList.size()==1);
        	assertEquals(inList, item.getOwningList());
    	} catch (GtdListException e) {
    		fail(e.toString());
    	}
    	
    	// try to add it to another list
    	try {
        	wfList.add(item);
        	fail("item can't be on more than one list");
    	} catch (GtdListException e) {
        	assertTrue(inList.contains(item));
        	assertTrue(inList.size()==1);
        	assertEquals(inList, item.getOwningList());
    	}
    	
    	// move it to another list
    	try {
    		item.move(wfList);
        	assertFalse(inList.contains(item));
        	assertFalse(inList.size()==1);
        	assertTrue(wfList.contains(item));
        	assertTrue(wfList.size()==1);
        	assertEquals(wfList, item.getOwningList());
    		
    	} catch (GtdListException e) {
    		fail("something bad happened");
    	}
    	
    }
    
    public void testDeleteListItem() {
    	try {
			GtdList myList = listManager.createList("my list");
			GtdListItem item1 = listManager.createListItem("item 1");
			GtdListItem item2 = listManager.createListItem("item 2");
			GtdListItem item3 = listManager.createListItem("item 3");
			GtdListItem item4 = listManager.createListItem("item 4");
			myList.add(item1);
			myList.add(item2);
			myList.add(item3);
			myList.add(item4);
			assertEquals(4,myList.size());
			myList.remove(item1);
			assertEquals(3,myList.size());
			myList.remove(item1);
			assertEquals(3,myList.size());
			
		} catch (DuplicateListException e) {
			e.printStackTrace();
		} catch (GtdListException e) {
			e.printStackTrace();
		}
    	
    }

    public void testGetListItemsInclSublists() {
    	
    	try {
			// set up a typical @Agenda list structure
			GtdList agendaTopLevel = listManager.createList("Agenda");
			GtdList agendaMark = listManager.createList("Agenda","Mark");
			GtdList agendaSteve = listManager.createList("Agenda","Steve");
			
			// add some typical items to the lists
			agendaTopLevel.add(listManager.createListItem("standard agenda checklist"));
			GtdListItem mark1 = listManager.createListItem("ISIS BAU progress update"); 
			agendaMark.add(mark1);
			agendaMark.add(listManager.createListItem("Is Marcelo happy doing support?"));
			GtdListItem steve1 = listManager.createListItem("LoyaltyNZ progress update"); 
			agendaSteve.add(steve1);
			agendaSteve.add(listManager.createListItem("Is Project21 capitalised?"));
			
			// retrieve the @Agenda list (which should include the sublist items
			assertEquals(1,agendaTopLevel.size());
			assertEquals(5,agendaTopLevel.sizeInclSublists());
			
			GtdList allAgendaItems = listManager.getList(agendaTopLevel,true);
			assertEquals(5,allAgendaItems.size());
			assertTrue(allAgendaItems.contains(mark1));
			assertTrue(allAgendaItems.contains(steve1));
			
		} catch (DuplicateListException e) {
			e.printStackTrace();
		} catch (GtdListException e) {
			e.printStackTrace();
		}    	
    }
    
}
