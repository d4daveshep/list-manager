package daveshep.gtd.domain;

import java.util.Comparator;

/**
 * Compares description field on ListItems.
 * A ListItem with no description is sorted after a ListItem with a description
 * If neither ListItem has a description then natural sorting takes place (i.e. ListItem.compareTo)
 * 
 * @author david
 *
 */
public class DescriptionSorter implements Comparator<ListItem> {

	@Override
	public int compare(ListItem item1, ListItem item2) {
	
		if (item1.getDescription()==null || item1.getDescription().length()==0 ) {
			if (item2.getDescription()==null || item2.getDescription().length()==0 ) {
				return (item1.compareTo(item2)); // fall back to natural sorting if neither item has a description
			} else {
				return 1; // return 1 since item2 description is set so is greater then item1 with no description 
			}
		} else if (item2.getDescription()==null || item2.getDescription().length()==0 ) {
			return -1; // return -1 since item1 has a description but item2 doesn't
			
		} else {
			// both items have a description so compare descriptions, but if descriptions are equal then use natural sorting
			int result = (item1.getDescription().compareToIgnoreCase(item2.getDescription()));
			if ( result == 0 ) {
				return item1.compareTo(item2);
			} else {
				return result;
			}
		}

	}

	@Override
	public String toString() {
		
		return "Description Sorter";
	}

}
