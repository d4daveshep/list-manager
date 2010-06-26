package daveshep.gtd.domain;

import daveshep.gtd.domain.ListItem;
import daveshep.gtd.util.DateUtils;

import java.util.ArrayList;
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
    	listItem.setTags(new ArrayList<String>());
    	
    	listItem.addTag("tag1");
    	listItem.addTag("tag2");

    	assertTrue(listItem.getTagsString().equals("tag1, tag2"));
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
    
    
}
