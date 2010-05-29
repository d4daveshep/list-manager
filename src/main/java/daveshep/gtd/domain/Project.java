package daveshep.gtd.domain;

import java.util.List;

/**
 * @author shephd
 *
 * A project is just a collection of tasks.  
 * You can't do a project you can only do tasks
 */
public class Project extends ListItem {

	private ProjectStatus status;
	
	public Project() {
		super();
		this.setType(ListItemType.PROJECT);
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
	

}
