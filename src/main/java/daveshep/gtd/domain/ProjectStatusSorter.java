package daveshep.gtd.domain;

import java.util.Comparator;

/**
 * Compares status field on Projects.
 * A Project is sorted before a non-Project (even if it has no status)
 * A Project with no status is sorted after a Project with a status
 * If neither Project has a status then natural sorting takes place (i.e. Project.compareTo)
 * 
 * @author david
 *
 */
public class ProjectStatusSorter implements Comparator<ListItem> {

	@Override
	public int compare(ListItem item1, ListItem item2) {
		
		if (!(item1 instanceof Project)) {
			if (!(item2 instanceof Project)) {
				return (item1.compareTo(item2)); // fall back to natural sorting if neither item is a project
			} else {
				return 1; // return 1 since item2 is a project but item1 isn't
			}
		} else if (!(item2 instanceof Project)) {
			return -1; // return -1 since item1 is not a project but item2 is
		}
	
		// if we get to here then both items are Projects
		Project project1 = (Project)item1;
		Project project2 = (Project)item2;
		
		// both projects have a status so compare status, but if status are equal then use natural sorting
		int result = (project1.getStatus().compareTo(project2.getStatus()));
		if ( result == 0 ) {
			return project1.compareTo(project2);
		} else {
			return result;
		}

	}

	@Override
	public String toString() {
		return "Project Status Sorter";
	}

}
