package daveshep.gtd.domain;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import daveshep.gtd.FilterSettings;
import daveshep.gtd.util.DateUtils;

public class ListItem implements Comparable {
	
	private Long id;
	private ListItemType type;
	private String description;
	private List<ListItem> childItems;
	private ListItem parentItem;
	private String folder;
	private boolean starflag;
	private Date dueDate;
	private Date completedDate;
	private String notes;
	private List<String> tags;
	private TaskRepeater repeater;
	
	ListItem() {
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
	
	public String getTypeCode() {
		if (type != null) {
			switch (type) {
			case GOAL:
				return "G___";
			case PROJECT:
				return "_P__";
			case TASK:
				return "__T_";
			case REFERENCE:
				return "___R";
			}
			return "unknown";
		} else {
			return "null";
		}
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

	public boolean isDone() {
		if (this.completedDate==null) {
			return false;
		}
		else {
			return true;
		}
	}

	protected void setDone(boolean done) {
		if (done==true) {
			this.completedDate = DateUtils.today();
		}
		else {
			this.completedDate = null;
		}
	}
	
	protected Date getCompletedDate() {
		return completedDate;
	}

	public Date getDueDate() {
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
		StringBuffer output = new StringBuffer();
		if (!isDone()) {
			output.append("- ");
		} else {
			output.append("+ ");
		}
		output.append(" | ");
		
		if (isStarflag()) {
			output.append("* ");
		}
		output.append(" | ");
			
		output.append(getTypeCode());
		output.append(" | ");
		output.append(getDescription());
		output.append(" | ");
		output.append(getFolder());
		output.append(" | ");
		
		if (getDueDate() != null) {
			output.append(DateUtils.xmlDateFormat.format(getDueDate()));
		}
		output.append(" | ");
//		output.append(getId());
//		output.append(" | ");

		return output.toString();
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


	public List<ListItem> getChildItems() {
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

	public void addRefItem(ReferenceItem refItem) {
		this.addChildItem(refItem);
	}

	@Deprecated
	/**
	 * Not intended for use by GUI
	 * @param childItem
	 */
	public void addChildItem(ListItem childItem) {
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

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	void setParentItem(ListItem parentItem) {
		this.parentItem = parentItem;
	}

	public List<String> getTags() {
		return tags;
	}

	public void addTag(String tag) {
		tags.add(tag);
	}
	
	public String getTagsString() {
		String tagString = "";
		for (int i=0; i<tags.size(); i++) {
			if (i>0) {
				tagString += ", ";
			}
			tagString += tags.get(i);
		}
		return tagString;
	}
	
	public void setTagsString(String tagString) {
		tags.clear();
		StringTokenizer tokens = new StringTokenizer(tagString, ",", false);
		while(tokens.hasMoreElements()) {
			tags.add(tokens.nextToken().trim());
		}
		
	}
	
	public void removeTag(String tag) {
		tags.remove(tag);
	}

	public void removeTags() {
		tags.clear();
	}

	public boolean hasTag(String tag) {
		return this.tags.contains(tag);
	}
	
	public boolean hasTags() {
		return !this.tags.isEmpty();
	}
	
	public boolean hasChildren() {
		return !this.childItems.isEmpty();
	}
	
	/**
	 * @param filterSettings
	 * @return true if item matches the filter settings
	 */
	public boolean passesFilter(FilterSettings filterSettings) {

		// TODO add the remainder of the filters
		
		if (isDone() && !filterSettings.showDone) {
			return false;
		}
		if (!isDone() && !filterSettings.showNotDone) {
			return false;
		}

		if (isStarflag() && !filterSettings.showStar) {
			return false;
		}
		if (!isStarflag() && !filterSettings.showNoStar) {
			return false;
		}
		
		if (getFolder()!=null) {
			if (!filterSettings.folder.equalsIgnoreCase("all") && !(getFolder().equals(filterSettings.folder))) {
				return false;
			}
		}

		return true;		
	}

	/* Default comparison is on id field
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Object o) {
		ListItem item = (ListItem)o;
		
		if (getId()==null) {
			if (item.getId()==null) {
				return 0; // items are equal if neither has an id
			} else {
				return 1; // return 1 since item id is set so is greater then this with no id 
			}
		} else if (item.getId()==null) {
			return -1; // return -1 since this has id set but a item doesn't
			
		} else {
			// both items have a id so compare 
			return getId().compareTo(((ListItem)o).getId());
		}

	}

}