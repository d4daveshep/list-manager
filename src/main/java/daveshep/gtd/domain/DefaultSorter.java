package daveshep.gtd.domain;

import java.util.Comparator;

/**
 * Compares ListItems using their natural sorting (i.e. ListItem.compareTo)
 * 
 * @author david
 *
 */
public class DefaultSorter implements Comparator<ListItem> {

	@Override
	public int compare(ListItem item1, ListItem item2) {
	
		return item1.compareTo(item2);

	}

	@Override
	public String toString() {
		
		return "Default Sorter";
	}

}
