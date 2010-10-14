package daveshep.gtd.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.ListenerNotFoundException;

import daveshep.gtd.DuplicateListException;
import daveshep.gtd.GtdListManager;
import daveshep.gtd.GtdListException;
import daveshep.gtd.util.IdGenerator;

public class InMemoryListManager implements GtdListManager {

	private static GtdListManager listManager = new InMemoryListManager();
	private List<GtdListItem> items;
	private Map<ListKey,GtdList> lists;
	
	public static GtdListManager getInstance() {
		return listManager;
	}
	
	private InMemoryListManager() {
		items = new ArrayList<GtdListItem>();
		lists = new HashMap<ListKey,GtdList>();
	}

	
	@Override
	public GtdList createList(String key1, String key2) {
		// TODO Auto-generated method stub
		return null;
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
	public GtdList getList(ListKey listkey) {
		// TODO Auto-generated method stub
		return null;
	}

}
