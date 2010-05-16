package daveshep.gtd;

import java.util.HashSet;
import java.util.Set;

import daveshep.gtd.domain.ListItem;

public class ListManager {
	
	private Set items;
	
	public ListManager() {
		items = new HashSet();
	}
	
	public static void main( String args[] ) {
		System.out.println( "GTD List Manager" );
	}
	
	public Set getItems() {
		return items;
	}

	public void setItems(Set items) {
		this.items = items;
	}

	public void addListItem( ListItem item ) {
		items.add(item);
	}
	
	// TODO: add factory methods for creating list items (set manager in constructor)
}