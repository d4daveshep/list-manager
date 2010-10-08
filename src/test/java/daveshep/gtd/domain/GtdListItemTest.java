package daveshep.gtd.domain;

//import daveshep.gtd.FilterSettings;
import daveshep.gtd.domain.GtdListItem;
import daveshep.gtd.util.DateUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class GtdListItemTest extends TestCase {

    public GtdListItemTest( String testName ) {
        super( testName );
    }

    public static Test suite() {
        return new TestSuite( GtdListItemTest.class );
    }

    public void testEquals() {
    	
    	GtdListItem li1 = new DefaultGtdListItem(12);
    	
    	GtdListItem li2 = new DefaultGtdListItem(13);
    	
    	assertFalse(li1.equals(li2));
    	
    	li2.setId(Long.valueOf(12));
    	
    	assertTrue(li1.equals(li2));
    	
    }
    
    public void testAddRemoveChildren() {
    	
    	// create an item and add 2 children
    	GtdListItem li = new DefaultGtdListItem(35);
    	li.setChildren( new HashSet<GtdListItem>());
    	li.setDescription("parent1");
    	
    	GtdListItem cli1 = new DefaultGtdListItem(12);
    	cli1.setDescription("child1");
    	li.addChild(cli1);
    	
    	GtdListItem cli2 = new DefaultGtdListItem(13);
    	cli2.setDescription("child2");
    	li.addChild(cli2);
    	
    	assertTrue(li.getChildren().size()==2);
    	assertTrue(cli1.getParent()==li);
    	assertTrue(li.getChildren().contains(cli1));
    	assertTrue(cli2.getParent()==li);
    	assertTrue(li.getChildren().contains(cli2));
    	
    	// remove one of the children
    	li.removeChild(cli1);
    	
    	assertTrue(li.getChildren().size()==1);
    	assertFalse(cli1.getParent()==li);
    	assertFalse(li.getChildren().contains(cli1));
    	assertTrue(cli2.getParent()==li);
    	assertTrue(li.getChildren().contains(cli2));
    	
    }
    
    public void testSelfChildItem() {
    	// shouldn't be able to add self to self
    	GtdListItem li = new DefaultGtdListItem(12);
    	li.setChildren(new HashSet<GtdListItem>());
    	try {
    		li.addChild(li);
    		fail("Shouldn't be able to add self child items");
    	} catch (IllegalArgumentException e) {
    		
    	}
    }
    
    public void testIsAncestorOf() {
    	GtdListItem grandparent = new DefaultGtdListItem(100);
    	grandparent.setDescription("grandparent");
    	grandparent.setChildren(new HashSet<GtdListItem>());

    	GtdListItem parent = new DefaultGtdListItem(35);
    	parent.setDescription("parent");
    	parent.setChildren(new HashSet<GtdListItem>());

    	GtdListItem child = new DefaultGtdListItem(5);
    	child.setDescription("child");
    	child.setChildren(new HashSet<GtdListItem>());
    	
    	parent.addChild(child);
    	grandparent.addChild(parent);
    	
    	assertTrue(grandparent.isAncestorOf(child));
    	assertTrue(parent.isAncestorOf(child));
    	assertTrue(grandparent.isAncestorOf(child));
    	
    	assertFalse(child.isAncestorOf(parent));
    	assertFalse(child.isAncestorOf(grandparent));
    	assertFalse(parent.isAncestorOf(grandparent));
    	
    	
    }
    
    public void testCircularChildItems() {
    	// shouldn't be able to add circular references
    	GtdListItem li1 = new DefaultGtdListItem(12);
    	li1.setChildren(new HashSet<GtdListItem>());
    	
    	GtdListItem li2 = new DefaultGtdListItem(13);
    	li2.setChildren(new HashSet<GtdListItem>());
    	
    	try {
    		li1.addChild(li2);
    		li2.addChild(li1);
    		fail("Shouldn't be able to add circular references");
    	} catch (IllegalArgumentException e) {
    		
    	}
    	
    	
    }
    
    public void testTags() {

    	GtdListItem listItem = new DefaultGtdListItem();
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
    	GtdListItem listItem = new DefaultGtdListItem();
    	
    	assertFalse(listItem.isDone());
    	assertNull(listItem.getCompletedDate());
    	
    	listItem.setDone(true);
    	assertTrue(listItem.isDone());
    	assertTrue(listItem.getCompletedDate().equals(DateUtils.today()));
    	
    }
/*    
    public void testPassesFilter() {
    	GtdListItem listItem = new GtdListItem();
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
*/    
    public void testCompareTo() {
    	
//    	// default comparison is on description field
//    	GtdListItem item1 = new GtdListItem("A new list item");
//    	GtdListItem item2 = new GtdListItem("The second list item");
//    	GtdListItem item3 = new GtdListItem("a new list item");

    	GtdListItem item1 = new DefaultGtdListItem("item1");
    	GtdListItem item2 = new DefaultGtdListItem("item2");
    	GtdListItem item3 = new DefaultGtdListItem("item3");

    	assertTrue(item1.compareTo(item2)==0);  // items are equal if no id set
    	
    	item1.setId(1L);
    	
    	assertTrue(item1.compareTo(item2)<0); // item with id is less than item with no id
    	
    	item2.setId(2L);
    	item3.setId(1L);
    	
    	assertTrue((item1.compareTo(item2))<0);  // less than means item1 appears before item2
    	assertTrue((item1.compareTo(item3))==0); // equals means item1 and item2 are equally sorted
    	assertTrue((item2.compareTo(item3))>0); // greater than means item2 appears after item3
    }
    
    public void testTwoWayDependency() {
    	
    	// simple dependencies
    	GtdListItem dependor1 = new DefaultGtdListItem(11, "dependor1");
    	GtdListItem dependent1 = new DefaultGtdListItem(21, "dependent1");
    	
    	// add dependent to item
    	dependor1.addDependent(dependent1);
    	
    	// check relationships are set up correctly
    	assertTrue(dependor1.hasDependent(dependent1));
    	assertTrue(dependent1.isDependentOn(dependor1));
    	assertFalse(dependor1.isDependentOn(dependent1));
    	assertFalse(dependent1.hasDependent(dependor1));
    	
    	// circular dependencies are illegal
    	try {
    		dependent1.addDependent(dependor1);
    		fail("Circular dependencies should be illegal");
    	} catch (IllegalArgumentException e) {
    	}
    	
    	GtdListItem dependor2 = new DefaultGtdListItem(12, "dependor2");
    	GtdListItem dependent2 = new DefaultGtdListItem(22, "dependent2");
    	
    	// add a dependor to an item
    	dependent2.addDependor(dependor2);
    	
    	// check relationships are set up correctly
    	assertTrue(dependor2.hasDependent(dependent2));
    	assertTrue(dependent2.isDependentOn(dependor2));
    	assertFalse(dependor2.isDependentOn(dependent2));
    	assertFalse(dependent2.hasDependent(dependor2));
    	
    	// circular dependencies are illegal
    	try {
    		dependor2.addDependor(dependor2);
    		fail("Circular dependencies should be illegal");
    	} catch (IllegalArgumentException e) {
    	}
    	
    	// check items having multiple dependents
    	dependor1.addDependent(dependent2);
    	
    	// check relationships are set up correctly
    	assertTrue(dependor1.hasDependent(dependent1));
    	assertTrue(dependent1.isDependentOn(dependor1));
    	assertTrue(dependor2.hasDependent(dependent2));
    	assertTrue(dependent2.isDependentOn(dependor2));
    	assertTrue(dependor1.hasDependent(dependent2));
    	assertTrue(dependent2.isDependentOn(dependor1));
    	
    	// check items having multiple dependors
    	GtdListItem dependent3 = new DefaultGtdListItem(23, "dependent3");
    	
    	dependent3.addDependor(dependor2);
    	assertTrue(dependor2.hasDependent(dependent3));
    	assertTrue(dependent3.isDependentOn(dependor2));
    	
    	// remove some dependencies
    	dependent2.removeDependor(dependor1);
    	assertFalse(dependent2.isDependentOn(dependor1));
    	assertFalse(dependor1.hasDependent(dependent2));
    	
    	dependor2.removeDependent(dependent2);
    	assertFalse(dependent2.isDependentOn(dependor2));
    	assertFalse(dependor2.hasDependent(dependent2));
    	
    	// dependent2 should now be dependent on nothing
    	assertFalse(dependent2.hasDependors());
    	
    	// remove another dependency
    	dependent3.removeDependor(dependor2);
 
    	// dependor2 should now have no dependents
    	assertFalse(dependor2.hasDependents());
    	
    	// what a mission!
    }
    
}
