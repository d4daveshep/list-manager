package daveshep.gtd.domain;

import daveshep.gtd.FilterSettings;
import daveshep.gtd.domain.ListItem;
import daveshep.gtd.util.DateUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ListItemTest extends TestCase {

    public ListItemTest( String testName ) {
        super( testName );
    }

    public static Test suite() {
        return new TestSuite( ListItemTest.class );
    }

    public void testEquals() {
    	
    	ListItem li1 = new ListItem(12);
    	
    	ListItem li2 = new ListItem(13);
    	
    	assertFalse(li1.equals(li2));
    	
    	li2.setId(Long.valueOf(12));
    	
    	assertTrue(li1.equals(li2));
    	
    }
    
    public void testAddRemoveChildItems() {
    	
    	// create an item and add 2 children
    	ListItem li = new ListItem(35);
    	li.setChildItems( new ArrayList());
    	li.setDescription("parent1");
    	
    	ListItem cli1 = new ListItem(12);
    	cli1.setDescription("child1");
    	li.addChildItem(cli1);
    	
    	ListItem cli2 = new ListItem(13);
    	cli2.setDescription("child2");
    	li.addChildItem(cli2);
    	
    	assertTrue(li.getChildItems().size()==2);
    	assertTrue(cli1.getParentItem()==li);
    	assertTrue(li.getChildItems().contains(cli1));
    	assertTrue(cli2.getParentItem()==li);
    	assertTrue(li.getChildItems().contains(cli2));
    	
    	// remove one of the children
    	li.removeChildItem(cli1);
    	
    	assertTrue(li.getChildItems().size()==1);
    	assertFalse(cli1.getParentItem()==li);
    	assertFalse(li.getChildItems().contains(cli1));
    	assertTrue(cli2.getParentItem()==li);
    	assertTrue(li.getChildItems().contains(cli2));
    	
    }
    
    public void testSelfChildItem() {
    	// shouldn't be able to add self to self
    	ListItem li = new ListItem(12);
    	li.setChildItems(new ArrayList());
    	try {
    		li.addChildItem(li);
    		fail("Shouldn't be able to add self child items");
    	} catch (IllegalArgumentException e) {
    		
    	}
    }
    
    public void testIsAncestorOf() {
    	ListItem grandparent = new ListItem(100);
    	grandparent.setDescription("grandparent");
    	grandparent.setChildItems(new ArrayList());

    	ListItem parent = new ListItem(35);
    	parent.setDescription("parent");
    	parent.setChildItems(new ArrayList());

    	ListItem child = new ListItem(5);
    	child.setDescription("child");
    	child.setChildItems(new ArrayList());
    	
    	parent.addChildItem(child);
    	grandparent.addChildItem(parent);
    	
    	assertTrue(grandparent.isAncestorOf(child));
    	assertTrue(parent.isAncestorOf(child));
    	assertTrue(grandparent.isAncestorOf(child));
    	
    	assertFalse(child.isAncestorOf(parent));
    	assertFalse(child.isAncestorOf(grandparent));
    	assertFalse(parent.isAncestorOf(grandparent));
    	
    	
    }
    
    public void testCircularChildItems() {
    	// shouldn't be able to add circular references
    	ListItem li1 = new ListItem(12);
    	li1.setChildItems(new ArrayList());
    	
    	ListItem li2 = new ListItem(13);
    	li2.setChildItems(new ArrayList());
    	
    	try {
    		li1.addChildItem(li2);
    		li2.addChildItem(li1);
    		fail("Shouldn't be able to add circular references");
    	} catch (IllegalArgumentException e) {
    		
    	}
    	
    	
    }
    
    public void testTags() {

    	ListItem listItem = new ListItem();
    	listItem.setTags(new HashSet<String>());
    	
    	listItem.addTag("tag1");
    	listItem.addTag("tag2");

    	assertTrue(listItem.getTagsString().equals("tag2, tag1"));  // could fail since using hash set
    	assertTrue(listItem.getTags().size()==2);
    	
    	listItem.setTagsString("tag3, tag4");
    	
    	assertTrue(listItem.getTags().size()==2);
    	
    	assertFalse(listItem.hasTag("tag1"));
    	assertFalse(listItem.hasTag("tag2"));
    	assertTrue(listItem.hasTag("tag3"));
    	assertTrue(listItem.hasTag("tag4"));
    	
    	listItem.removeTag("tag4");
    	
    	assertTrue(listItem.getTagsString().equals("tag3"));
    	assertTrue(listItem.getTags().size()==1);
    	
    }
    
    public void testDoneAndCompletedDate() {
    	ListItem listItem = new ListItem();
    	
    	assertFalse(listItem.isDone());
    	assertNull(listItem.getCompletedDate());
    	
    	listItem.setDone(true);
    	assertTrue(listItem.isDone());
    	assertTrue(listItem.getCompletedDate().equals(DateUtils.today()));
    	
    }
    
    public void testPassesFilter() {
    	ListItem listItem = new ListItem();
    	FilterSettings filterSettings = new FilterSettings();
    	
    	filterSettings.showNotDone = true; // show not done items.
    	listItem.setDone(false); // item is not done
    	assertTrue(listItem.passesFilter(filterSettings)); // show item
    	
    	filterSettings.showNotDone = false; // don't show not done items
    	assertFalse(listItem.passesFilter(filterSettings)); // don't show item
    	
    	filterSettings.showDone = true; // show done items
    	listItem.setDone(true); // item is done
    	assertTrue(listItem.passesFilter(filterSettings)); // show item
    	
    	filterSettings.showDone = false; // don't show done items
    	assertFalse(listItem.passesFilter(filterSettings)); // don't show item

    	listItem.setDone(false);
    	filterSettings.showNotDone = true;
    	
    	listItem.setStarflag(false); // item has no star
    	filterSettings.showNoStar = true; // show items without star
    	assertTrue(listItem.passesFilter(filterSettings)); // show item
    	
    	filterSettings.showNoStar = false; // don't show items without star
    	assertFalse(listItem.passesFilter(filterSettings)); // don't show item
    	
    	// test folder filter
    	filterSettings = new FilterSettings();
    	listItem.setFolder("w.Delivery");
    	filterSettings.folder = "w.Delivery";
    	assertTrue(listItem.passesFilter(filterSettings));
    	
    	filterSettings.folder = "All";
    	assertTrue(listItem.passesFilter(filterSettings));
    	
    	filterSettings.folder = "h.Finance";
    	assertFalse(listItem.passesFilter(filterSettings));
    	
    	
    	
    }
    
    public void testCompareTo() {
    	
//    	// default comparison is on description field
//    	ListItem item1 = new ListItem("A new list item");
//    	ListItem item2 = new ListItem("The second list item");
//    	ListItem item3 = new ListItem("a new list item");

    	ListItem item1 = new ListItem("item1");
    	ListItem item2 = new ListItem("item2");
    	ListItem item3 = new ListItem("item3");

    	assertTrue(item1.compareTo(item2)==0);  // items are equal if no id set
    	
    	item1.setId(1L);
    	
    	assertTrue(item1.compareTo(item2)<0); // item with id is less than item with no id
    	
    	item2.setId(2L);
    	item3.setId(1L);
    	
    	assertTrue((item1.compareTo(item2))<0);  // less than means item1 appears before item2
    	assertTrue((item1.compareTo(item3))==0); // equals means item1 and item2 are equally sorted
    	assertTrue((item2.compareTo(item3))>0); // greater than means item2 appears after item3
    }
    
    
}
