package daveshep.gtd.domain;

import java.util.Comparator;

/**
 * Compares status field on Goals.
 * A Goal is sorted before a non-Goal (even if it has no status)
 * A Goal with no status is sorted after a Goal with a status
 * If neither Goal has a status then natural sorting takes place (i.e. Goal.compareTo)
 * 
 * @author david
 *
 */
public class GoalStatusSorter implements Comparator<ListItem> {

	@Override
	public int compare(ListItem item1, ListItem item2) {
		
		if (!(item1 instanceof Goal)) {
			if (!(item2 instanceof Goal)) {
				return (item1.compareTo(item2)); // fall back to natural sorting if neither item is a goal
			} else {
				return 1; // return 1 since item2 is a goal but item1 isn't
			}
		} else if (!(item2 instanceof Goal)) {
			return -1; // return -1 since item1 is not a goal but item2 is
		}
	
		// if we get to here then both items are Goals
		Goal goal1 = (Goal)item1;
		Goal goal2 = (Goal)item2;
		
		// both goals have a status so compare status, but if status are equal then use natural sorting
		int result = (goal1.getStatus().compareTo(goal2.getStatus()));
		if ( result == 0 ) {
			return goal1.compareTo(goal2);
		} else {
			return result;
		}

	}

	@Override
	public String toString() {
		return "Goal Status Sorter";
	}

}
