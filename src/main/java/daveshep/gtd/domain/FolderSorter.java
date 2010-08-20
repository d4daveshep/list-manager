package daveshep.gtd.domain;

import java.util.Comparator;

/**
 * Compares folder field on ListItems.
 * A ListItem with no folder set is sorted after a ListItem with a folder
 * If neither ListItem has a folder set then natural sorting takes place (i.e. ListItem.compareTo)
 * 
 * @author david
 *
 */
public class FolderSorter implements Comparator<ListItem> {

	@Override
	public int compare(ListItem item1, ListItem item2) {
	
		if (item1.getFolder()==null || item1.getFolder().length()==0 ) {
			if (item2.getFolder()==null || item2.getFolder().length()==0 ) {
				return (item1.compareTo(item2)); // fall back to natural sorting if neither item has a folder
			} else {
				return 1; // return 1 since item2 folder is set so is greater then item1 with no folder 
			}
		} else if (item2.getFolder()==null || item2.getFolder().length()==0 ) {
			return -1; // return -1 since item1 has a folder but item2 doesn't
			
		} else {
			// both items have a folder so compare folders, but if folders are equal then use natural sorting
			int result = (item1.getFolder().compareToIgnoreCase(item2.getFolder()));
			if ( result == 0 ) {
				return item1.compareTo(item2);
			} else {
				return result;
			}
		}

	}

	@Override
	public String toString() {
		return "Folder Sorter";
	}

}
