package daveshep.gtd.domain;

import java.util.Comparator;

/**
 * Compares due date field on ListItems.
 * A ListItem with no date set is sorted after a ListItem with a date
 * If neither ListItem has a date set then natural sorting takes place (i.e. ListItem.compareTo)
 * 
 * @author david
 *
 */
public class DueDateSorter implements Comparator<ListItem> {

	@Override
	public int compare(ListItem item1, ListItem item2) {
	
		if (item1.getDueDate()==null) {
			if (item2.getDueDate()==null) {
				return (item1.compareTo(item2)); // fall back to natural sorting if neither item has a date
			} else {
				return 1; // return 1 since item2 date is set so is greater then item1 with no date 
			}
		} else if (item2.getDueDate()==null) {
			return -1; // return -1 since item1 has a date but item2 doesn't
			
		} else {
			// both items have a date so compare dates, but if dates are equal, use natural sorting
			int result = (item1.getDueDate().compareTo(item2.getDueDate()));
			if ( result == 0 ) {
				return item1.compareTo(item2);
			} else {
				return result;
			}

		}

	}

	@Override
	public String toString() {
		return "Due Date Sorter";
	}

}
