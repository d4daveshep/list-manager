package daveshep.gtd.domain;

import java.util.Comparator;

/**
 * Compares status field on Tasks.
 * A Task is sorted before a non-Task (even if it has no status)
 * A Task with no status is sorted after a Task with a status
 * If neither Task has a status then natural sorting takes place (i.e. Task.compareTo)
 * 
 * @author david
 *
 */
public class TaskStatusSorter implements Comparator<ListItem> {

	@Override
	public int compare(ListItem item1, ListItem item2) {
		
		if (!(item1 instanceof Task)) {
			if (!(item2 instanceof Task)) {
				return (item1.compareTo(item2)); // fall back to natural sorting if neither item is a task
			} else {
				return 1; // return 1 since item2 is a task but item1 isn't
			}
		} else if (!(item2 instanceof Task)) {
			return -1; // return -1 since item1 is not a task but item2 is
		}
	
		// if we get to here then both items are Tasks
		Task task1 = (Task)item1;
		Task task2 = (Task)item2;
		
		// both tasks have a status so compare status, but if status are equal then use natural sorting
		int result = (task1.getStatus().compareTo(task2.getStatus()));
		if ( result == 0 ) {
			return task1.compareTo(task2);
		} else {
			return result;
		}

	}

	@Override
	public String toString() {
		return "Task Status Sorter";
	}

}