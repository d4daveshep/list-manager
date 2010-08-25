package daveshep.gtd.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import daveshep.gtd.FilterSettings;
import daveshep.gtd.ListManager;
import daveshep.gtd.ViewSettings;


public class InMemoryListManager implements ListManager {

	private static ListManager listManager = new InMemoryListManager();
	private List<ListItem> storage;
	
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
		return findItemsByString(textToFind,null);
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

	@Override
	public List<ListItem> findItemsByString(String textToFind, FilterSettings filterSettings) {
		System.out.println("textToFind = " + textToFind);

		List<ListItem> foundItems = new ArrayList<ListItem>();
		
		for (Iterator<ListItem> iterator = storage.iterator();iterator.hasNext();) {
			ListItem item = iterator.next();

			// empty find string matches all
			if (textToFind.length()==0 ) {
				if (filterSettings==null) {
					foundItems.add(item);
				} else if (item.passesFilter(filterSettings)) {
					foundItems.add(item);
				}
				
			} else
				
			// case insensitive
			if (item.getDescription().toLowerCase().contains(textToFind.toLowerCase())) {

				if (filterSettings==null) {
					foundItems.add(item);
				} else if (item.passesFilter(filterSettings)) {
					foundItems.add(item);
				}
			}
		}
		return foundItems;
	}

	@Override
	public String[] getFolders() {
		
		SortedSet<String> folderSet = new TreeSet<String>();

		for (Iterator<ListItem> iterator = storage.iterator(); iterator.hasNext();) {
			ListItem item = iterator.next();
			
			String folder = item.getFolder();
			if (folder != null && folder.length()>0) {
				if (!folderSet.contains(folder)) {
					folderSet.add(folder);
				}
			}
		}
		
		return folderSet.toArray(new String[0]);
	}

	@Override
	public String[] getTaskContexts() {
		SortedSet<String> contextSet = new TreeSet<String>();

		for (Iterator<ListItem> iterator = storage.iterator(); iterator.hasNext();) {
			ListItem item = iterator.next();

			if( ListItemType.TASK.equals(item.getType())) {
			
				String context = ((Task)item).getContext();
				if (context != null && context.length()>0) {
					if (!contextSet.contains(context)) {
						contextSet.add(context);
					}
				}
			}
		}
		
		return contextSet.toArray(new String[0]);
	}

	@Override
	public Collection<ListItem> findItemsByString(String textToFind,
			FilterSettings filterSettings, Comparator<ListItem> sortSettings, ViewSettings viewSettings) {
		System.out.println("textToFind = " + textToFind);

		Collection<ListItem> foundItems;
		if (viewSettings==null || viewSettings.showSubItemsNested==false) {
			foundItems = new TreeSet<ListItem>(sortSettings);
		} else {
			foundItems = new ArrayList<ListItem>();
		}
		
		for (Iterator<ListItem> iterator = storage.iterator();iterator.hasNext();) {
			ListItem item = iterator.next();

			// empty find string matches all
			if (textToFind.length()==0 ) {
				if (filterSettings==null) {
					foundItems.add(item);
				} else if (item.passesFilter(filterSettings)) {
					foundItems.add(item);
				}
				
			} else
				
			// case insensitive
			if (item.getDescription().toLowerCase().contains(textToFind.toLowerCase())) {
				boolean addIt = false;
				if (filterSettings==null) {
					addIt = true;
				} else if (item.passesFilter(filterSettings)) {
					addIt = true;
				}
				if (addIt) {
					if (viewSettings==null || viewSettings.showSubItemsNested==false) {
						foundItems.add(item);
					} else {
						if (!foundItems.contains(item)) {
							ListItem parent = findParent(item);
							if (parent!=null) {
								foundItems.add(parent);
								addSubItemsOf(parent, foundItems,filterSettings);
							} else {
								foundItems.add(item);
								addSubItemsOf(item, foundItems,filterSettings);
							}
						}
					}
				}
			}
		}
		return foundItems;
	}

	private void addSubItemsOf(ListItem parent, Collection<ListItem> foundItems, FilterSettings filterSettings) {
		System.out.println("adding subitems of " + parent.getDescription());
		if (!parent.hasChildren()) {
			return;
		}
		
		for (Iterator<ListItem> i = parent.getChildItems().iterator();i.hasNext();) {
			ListItem child = i.next();
			if (filterSettings==null ) {
				foundItems.add(child);
			} else {
				if (!child.isDone() && filterSettings.showNotDone ) {
					foundItems.add(child);
				} else if (child.isDone() && filterSettings.showDone) {
					foundItems.add(child);
				}
			}
			
			// do this recursively to handle multiple levels of nesting
			addSubItemsOf(child, foundItems, filterSettings);
		}
		
	}

	/**
	 * @param item
	 * @return the top level ancestor of item
	 */
	private ListItem findParent(ListItem item) {
		ListItem currentItem = item;
		ListItem oldItem = null;
		
		while (currentItem!=null) {
			oldItem = currentItem;
			currentItem = oldItem.getParentItem();
			
		}
		
		return oldItem;
		
	}
	

}
