package daveshep.gtd.domain;

import java.util.Comparator;

/**
 * Compares context field on Tasks.
 * A Task is sorted before a non-Task (even if it has no context)
 * A Task with no context is sorted after a Task with a context
 * If neither Task has a context then natural sorting takes place (i.e. Task.compareTo)
 * 
 * @author david
 *
 */
public class TaskContextSorter implements Comparator<ListItem> {

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
		
		if (task1.getContext()==null || task1.getContext().length()==0) {
			if (task2.getContext()==null || task2.getContext().length()==0) {
				return (task1.compareTo(task2)); // fall back to natural sorting if neither task has a context
			} else {
				return 1; // return 1 since task2 context is set so is greater then task1 with no context 
			}
		} else if (task2.getContext()==null || task2.getContext().length()==0) {
			return -1; // return -1 since task1 has a context but task2 doesn't
			
		} else {
			// both tasks have a context so compare contexts, but if contexts are equal then use natural sorting
			int result = (task1.getContext().compareToIgnoreCase(task2.getContext()));
			if ( result == 0 ) {
				return task1.compareTo(task2);
			} else {
				return result;
			}
		}

	}

	@Override
	public String toString() {
		return "Task Context Sorter";
	}

}
