package daveshep.gtd;

import java.util.List;

import daveshep.gtd.domain.Goal;
import daveshep.gtd.domain.ListItem;
import daveshep.gtd.domain.Project;
import daveshep.gtd.domain.ProjectStatus;
import daveshep.gtd.domain.ReferenceItem;
import daveshep.gtd.domain.Task;
import daveshep.gtd.domain.TaskStatus;

public interface ListManager {
	
	// factory methods
	public Task createTask();
	public Project createProject();
	public Goal createGoal();
	public ReferenceItem createRefItem();

	// search methods
	
	// general search
	public List<ListItem> findItemsByFolder(String folder);
	public List<ListItem> findItemsByStarflag(boolean flag);
	public List<ListItem> findItemsByDone(boolean done);
	
	// task search
	public List<Task> getTasks();
	public List<Task> findTasksByContext(String context);
	public List<Task> findTasksByStatus(TaskStatus status);
	
	// project search
	public List<Project> getProjects();
	public List<Project> findProjectsByStatus(ProjectStatus status);
	public List<Project> findProjectsWithNoChildren();
	public List<Project> findActiveProjectsWithNoNextAction();
}