package daveshep.gtd.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javax.management.ListenerNotFoundException;

import daveshep.gtd.DuplicateListException;
import daveshep.gtd.GtdListManager;
import daveshep.gtd.GtdListException;
import daveshep.gtd.StaticLists;
import daveshep.gtd.util.IdGenerator;

public class InMemoryListManager implements GtdListManager {

	private static Logger logger = Logger.getLogger("daveshep.gtd");

	private static GtdListManager listManager = new InMemoryListManager();
//	private List<GtdListItem> items;
	private Map<ListKey,GtdList> lists;
	
	public static GtdListManager getInstance() {
		return listManager;
	}
	
	private InMemoryListManager() {
//		items = new ArrayList<GtdListItem>();
		lists = new HashMap<ListKey,GtdList>();
	}

	
	@Override
	public GtdList createList(String title, String subtitle) throws DuplicateListException {
		ListKey listKey = new ListKey(title,subtitle);
		if (lists.containsKey(listKey)) {
			throw new DuplicateListException("List: " + title + " | " + subtitle + " already exists");
		} else {
			GtdList list = new DefaultGtdList(title,subtitle);
			lists.put(list.getKey(), list);
			return list;
		}
	}

	@Override
	public GtdListItem createListItem() {
		GtdListItem listItem = new DefaultGtdListItem();
		listItem.setId(IdGenerator.getInstance().generateId());
		return listItem;
	}

	@Override
	public GtdListItem createListItem(String description) {
		GtdListItem listItem = createListItem();
		listItem.setDescription(description);
		return listItem;
	}

	@Override
	public GtdList createList(String title) throws DuplicateListException {
		ListKey listKey = new ListKey(title,"");
		if (lists.containsKey(listKey)) {
			throw new DuplicateListException("List: " + title + " already exists");
		} else {
			GtdList list = new DefaultGtdList(title);
			lists.put(list.getKey(), list);
			return list;
		}
	}

	@Override
	public GtdList getList(GtdList list) throws GtdListException {
		if (list==null) {
			throw new GtdListException("trying to get null list");
		}
		GtdList theList = lists.get(list.getKey());
		if (theList==null) {
			throw new GtdListException("list not found: " + list.getKey().toString());
		}
		return theList;
		
	}

	@Override
	public GtdList getList(ListKey listkey) throws IllegalArgumentException {
		if (listkey==null) {
			throw new IllegalArgumentException("trying to get null list");
		}
		GtdList thisList = lists.get(listkey);
		if (thisList==null) {
			thisList = new DefaultGtdList(listkey.getTitle(), listkey.getSubtitle());
			lists.put(listkey, thisList);
		}
		return thisList;
	}

	/*
	@Override
	public GtdList getList(GtdList list, boolean includeSubListItems)
			throws GtdListException {
		GtdList theList = getList(list);
		if (!includeSubListItems) {
			return theList;
		} else {
			GtdList tempList = createTemporaryList(list.getTitle());
			Set<ListKey> keys = lists.keySet();
			for (Iterator<ListKey> i=keys.iterator();i.hasNext();) {
				ListKey key = i.next();
				if (key.getTitle().equalsIgnoreCase(list.getTitle())) {
					tempList.addAll(lists.get(key));
				}
			}
			return tempList;
		}
	}
	*/

	@Override
	public GtdList createTemporaryList(String title) {
		GtdList list = new DefaultGtdList(title, "*");
		return list;
	}

	@Override
	public int getListCount() {
		return lists.size();
	}

	@Override
	public Set<ListKey> getListKeys() {
		return lists.keySet();
	}

	@Override
	public Collection<GtdListItem> findItemsByString(String findString) {
		logger.info("textToFind = " + findString);

		List<GtdListItem> foundItems = new ArrayList<GtdListItem>();
		
		// call findItemsByString on each list, adding the results to foundItems
		for (Iterator<GtdList> iterator=lists.values().iterator();iterator.hasNext();) {
			GtdList list = iterator.next();
			foundItems.addAll(list.findItemsByString(findString));
		}
		
		return foundItems;
	}

	@Override
	public void removeAll() {
		
		try {
			lists.clear();
			StaticLists.createStaticLists(this);
		} catch (GtdListException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void copyTo(GtdListItem item, ListKey listKey) {

		// make a copy of the item
		GtdListItem newItem = item.copy();
		
		// add it to the list (will create list if necessary)
		GtdList list = getList(listKey);
		try {
			list.add(newItem);
		} catch (GtdListException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void moveTo(GtdListItem item, ListKey listKey) {

		// remove the item from it's current list
		item.getOwningList().remove(item);
		item.setOwningList(null);
		
		// get the list to move to (will create the list if necessary)
		GtdList list = getList(listKey);
		
		// add the item to it's new list
		try {
			list.add(item);
		} catch (GtdListException e) {
			e.printStackTrace();
			logger.severe("cloned item was already on list - check IDs");
		}
		
	}

}
