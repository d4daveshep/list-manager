package daveshep.gtd.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import daveshep.gtd.ListManager;



public class ListItem {
	
	private Long id;
	private ListItemType type;
	private String description;
	private List childItems;
	private ListItem parentItem;
	
	public ListItem() {
	}
	
	public ListItem( String desc ) {
		this();
		setDescription( desc );
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public ListItemType getType() {
		return type;
	}

	public void setType(ListItemType type) {
		this.type = type;
	}

	public void setDescription( String desc ) {
		description = desc;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String toString() {
		return this.getId() + " " + this.getType() + " " + description;
	}

	@Override
	public boolean equals(Object obj) {

		ListItem li = (ListItem)obj;
		return this.getId().equals(li.getId());
	}

	@Override
	public int hashCode() {
		return this.getId().intValue();
	}


	public List getChildItems() {
		return childItems;
	}

	public void setChildItems(List childItems) {
		this.childItems = childItems;
	}

	public boolean isAncestorOf(ListItem child) {
		// is this item an ancestor of param child item
		System.out.println(child.toString());
		ListItem parent = this.getParentItem();
		while (parent!=null) {
			System.out.println(parent.toString());
			if (parent.equals(child)) {
				return true;
			}
			parent = parent.getParentItem();
		}
		return false;
		
	}
	
	public void addChildItem(ListItem childItem) {
		if (childItem==null) {
			throw new IllegalArgumentException("Null child item");
		}
		if (childItem==this) {
			throw new IllegalArgumentException("Can't add self to self");
		}
		if (childItem.getParentItem()!=null) {
			childItem.getParentItem().removeChildItem(childItem);
		}
		childItem.setParentItem(this);
		getChildItems().add(childItem);
	}

	public void removeChildItem(ListItem childItem) {
		if (childItem==null) {
			throw new IllegalArgumentException("Null child item");
		}
		if (getChildItems()==null || getChildItems().contains(childItem)==false) {
			throw new IllegalArgumentException("Not a child item of this item");
		}
		childItem.setParentItem(null);
		getChildItems().remove(childItem);
	}
	
	public ListItem getParentItem() {
		return parentItem;
	}

	public void setParentItem(ListItem parentItem) {
		this.parentItem = parentItem;
	}

	
}