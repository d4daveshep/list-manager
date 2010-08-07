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
	public ListItem findItemById(Long id);
	public List<ListItem> findItemsByFolder(String folder);
	public List<ListItem> findItemsByStarflag(boolean flag);
	public List<ListItem> findItemsByDone(boolean done);
	public List<ListItem> findItemsByString(String textToFind); // case insensitive by default
	public List<ListItem> findItemsByString(String textToFind, boolean inclTags, boolean inclNotes, boolean caseSensitive);
	
	// task search
	public List<Task> getTasks();
	public Task findTaskById(Long id);
	public List<Task> findTasksByContext(String context);
	public List<Task> findTasksByStatus(TaskStatus status);
	
	// project search
	public List<Project> getProjects();
	public Project findProjectById(Long id);
	public List<Project> findProjectsByStatus(ProjectStatus status);
	public List<Project> findProjectsWithNoChildren();
	public List<Project> findActiveProjectsWithNoNextAction();
	
	// goal search
	public List<Goal> getGoals();
	
	// remove methods
	public void removeAll();
}