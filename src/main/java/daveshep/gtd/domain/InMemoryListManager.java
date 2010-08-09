package daveshep.gtd.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import daveshep.gtd.ListManager;


public class InMemoryListManager implements ListManager {

	private static ListManager listManager = new InMemoryListManager();
	private List storage;
	
	public static ListManager getInstance() {
		return listManager;
	}
	
	private InMemoryListManager() {
		storage = new ArrayList<ListItem>();
	}
	
	private void createChildContainer(ListItem item) {
		item.setChildItems(new ArrayList<ListItem>());
	}
	
	private void createTagContainer(ListItem item) {
		item.setTags(new ArrayList<String>());
	}
	
	@Override
	public Goal createGoal() {
		Goal goal = new Goal();
		createChildContainer(goal);
		storage.add(goal);
		return goal;
	}

	@Override
	public List<Goal> getGoals() {
		
		List goals = new ArrayList<Goal>();
		
		for(Iterator<ListItem> iterator = storage.iterator();iterator.hasNext();) {
			ListItem item = iterator.next();
			if( ListItemType.GOAL.equals(item.getType())) {
				goals.add(item);
			}
		}
		return goals;
		
	}

	@Override
	public Project createProject() {
		Project project = new Project();
		createChildContainer(project);
		createTagContainer(project);
		storage.add(project);
		return project;	
	}

	@Override
	public ReferenceItem createRefItem() {
		ReferenceItem refItem = new ReferenceItem();
		createChildContainer(refItem);
		createTagContainer(refItem);
		storage.add(refItem);
		return refItem;
	}

	@Override
	public Task createTask() {
		Task task = new Task();
		createChildContainer(task);
		createTagContainer(task);
		storage.add(task);
		return task;	
	}

	@Override
	public List<Project> findActiveProjectsWithNoNextAction() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ListItem> findItemsByDone(boolean done) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ListItem> findItemsByFolder(String folder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ListItem> findItemsByStarflag(boolean flag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Project> findProjectsByStatus(ProjectStatus status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Project> findProjectsWithNoChildren() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Task> findTasksByContext(String context) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Task> findTasksByStatus(TaskStatus status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Project> getProjects() {
		List projects = new ArrayList<Project>();
		
		for(Iterator<ListItem> iterator = storage.iterator();iterator.hasNext();) {
			ListItem item = iterator.next();
			if( ListItemType.PROJECT.equals(item.getType())) {
				projects.add(item);
			}
		}
		return projects;
		
	}

	@Override
	public List<Task> getTasks() {
		List tasks = new ArrayList<Task>();
		
		for(Iterator<ListItem> iterator = storage.iterator();iterator.hasNext();) {
			ListItem item = iterator.next();
			if( ListItemType.TASK.equals(item.getType())) {
				tasks.add(item);
			}
		}
		return tasks;
	}

	@Override
	public void removeAll() {
		storage.clear();
	}

	@Override
	public ListItem findItemById(Long id) {
		for(Iterator<ListItem> iterator = storage.iterator();iterator.hasNext();) {
			ListItem item = iterator.next();
			if(item.getId().equals(id)) {
				return item;
			}
		}
		return null;
	}

	@Override
	public Task findTaskById(Long id) {
		for(Iterator<ListItem> iterator = storage.iterator();iterator.hasNext();) {
			ListItem item = iterator.next();
			if(item.getId().equals(id) && item.getType()==ListItemType.TASK) {
				return (Task)item;
			}
		}
		return null;
	}

	@Override
	public Project findProjectById(Long id) {
		for(Iterator<ListItem> iterator = storage.iterator();iterator.hasNext();) {
			ListItem item = iterator.next();
			if(item.getId().equals(id) && item.getType()==ListItemType.PROJECT) {
				return (Project)item;
			}
		}
		return null;
	}

	@Override
	public List<ListItem> findItemsByString(String textToFind) {
		List<ListItem> foundItems = new ArrayList<ListItem>();
		
		for (Iterator<ListItem> iterator = storage.iterator();iterator.hasNext();) {
			ListItem item = iterator.next();
			// case insensitive
			if (item.getDescription().toLowerCase().contains(textToFind.toLowerCase())) {
				foundItems.add(item);
			}
		}
		return foundItems;
	}

	@Override
	public List<ListItem> findItemsByString(String textToFind, boolean inclTags, boolean inclNotes, boolean caseSensitive) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(ListItem itemToRemove) {
		storage.remove(itemToRemove);
	}

}
