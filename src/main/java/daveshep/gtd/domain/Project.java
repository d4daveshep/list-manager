package daveshep.gtd.domain;

import java.util.List;

import daveshep.gtd.FilterSettings;

/**
 * @author shephd
 *
 * A project is just a collection of tasks.  
 * You can't do a project you can only do tasks
 */
public class Project extends ListItem {

	private ProjectStatus status;
	
	Project() {
		super();
		this.setType(ListItemType.PROJECT);
		this.setStatus(ProjectStatus.ACTIVE);
	}
	
	Project(String description) {
		this();
		setDescription(description);
	}

	public ProjectStatus getStatus() {
		return status;
	}

	public void setStatus(ProjectStatus status) {
		this.status = status;
	}

	public void addSubProject(Project subProject) {
		this.addChildItem(subProject);
	}
	
	public void removeSubProject(Project subProject) {
		this.removeChildItem(subProject);
	}
	
	public List getSubProjectsOrTasks() {
		return this.getChildItems();
	}
	
	public void addSubTask(Task subTask) {
		this.addChildItem(subTask);
	}

	public void removeSubTask(Task subTask) {
		this.removeChildItem(subTask);
	}

	@Override
	public boolean isDone() {
		// TODO Auto-generated method stub
		return super.isDone();
	}

	@Override
	public void setDone(boolean done) {
		// TODO Auto-generated method stub
		super.setDone(done);
	}

	@Override
	public String toString() {
		StringBuffer output = new StringBuffer(super.toString());
		output.append(getStatus());
		output.append(" | ");
		
		return output.toString();
	}

	@Override
	public boolean passesFilter(FilterSettings filterSettings) {
		if (super.passesFilter(filterSettings)) {

			// check type matches
			if (!filterSettings.showProjects) {
				return false;
			}
			
			// check status matches
			if (!filterSettings.projectStatus.equalsIgnoreCase("all")) {
				if (!(getStatus().name().equals(filterSettings.projectStatus))) {
					return false;
				}
			}			
	
		} else {
			return false;
		}

		return true; 
	}
	

}
