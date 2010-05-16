package daveshep.gtd.domain;

import java.util.HashSet;
import java.util.Set;

import daveshep.gtd.ListManager;



public class ListItem {
	
	private Long id;
	private ListItemType type;
	private String description;
	private ListManager manager;
	
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
	
	public ListManager getManager() {
		return manager;
	}

	public void setManager(ListManager manager) {
		this.manager = manager;
	}

	public String toString() {
		return this.getType() + " " + description;
	}
	
}