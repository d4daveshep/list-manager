package daveshep.gtd.domain;

import java.util.Date;
import java.util.List;

public class ListItem {
	
	private Long id;
	private ListItemType type;
	private String description;
	private List childItems;
	private ListItem parentItem;
	private String folder;
	private boolean starflag;
	private boolean done;
	private Date dueDate;
	private String notes;
	
	public ListItem() {
	}

	//package level constructors for testing purposes
	ListItem(Long id) {
		this.setId(id);
	}
	
	ListItem(long id) {
		this.setId(Long.valueOf(id));
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
	
	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

	public boolean isStarflag() {
		return starflag;
	}

	public void setStarflag(boolean starflag) {
		this.starflag = starflag;
	}

	protected boolean isDone() {
		return done;
	}

	protected void setDone(boolean done) {
		this.done = done;
	}

	protected Date getDueDate() {
		return dueDate;
	}

	protected void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
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


	List getChildItems() {
		return childItems;
	}

	void setChildItems(List childItems) {
		this.childItems = childItems;
	}

	boolean isAncestorOf(ListItem child) {
		// is this item an ancestor of param child item
		ListItem ancestor = child.getParentItem();
		while (ancestor!=null) {
			if (this.equals(ancestor)) {
				return true;
			}
			ancestor = ancestor.getParentItem();
		}
		return false;
		
	}
	
	void addChildItem(ListItem childItem) {
		if (childItem==null) {
			throw new IllegalArgumentException("Null child item");
		}
		if (childItem==this) {
			throw new IllegalArgumentException("Can't add self to self");
		}
		if (childItem.isAncestorOf(this)) {
			throw new IllegalArgumentException("Can't add circular references");
		}
		
		if (childItem.getParentItem()!=null) {
			childItem.getParentItem().removeChildItem(childItem);
		}
		childItem.setParentItem(this);
		getChildItems().add(childItem);
	}

	void removeChildItem(ListItem childItem) {
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

	void setParentItem(ListItem parentItem) {
		this.parentItem = parentItem;
	}

	
}