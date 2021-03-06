package daveshep.gtd.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import daveshep.gtd.GtdListException;

public class DefaultGtdList implements GtdList {
	
	private Collection<GtdListItem> list;
	private ListKey listKey;
	
	public DefaultGtdList(String title) {
		list = new ArrayList<GtdListItem>();
		listKey = new ListKey(title,"");
	}
	
	public DefaultGtdList(String title, String subtitle) {
		list = new ArrayList<GtdListItem>();
		listKey = new ListKey(title,subtitle);
	}
	
	public String getTitle() {
		return listKey.getTitle();
	}

	public String getSubtitle() {
		return listKey.getSubtitle();
	}

	@Override
	public boolean add(GtdListItem item) throws GtdListException {
		if (item.getOwningList()!=null && item.getOwningList()!=this) {
			throw new GtdListException("Item is already on list: " + item.getOwningList().getKey().toString());
		}
		item.setOwningList(this);
		return list.add(item);
	}

	@Override
	public void clear() {
		list.clear();
	}

	@Override
	public boolean contains(Object o) {
		return list.contains(o);
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public Iterator<GtdListItem> iterator() {
		return list.iterator();
	}

	@Override
	public boolean remove(Object o) {
		return list.remove(o);
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public ListKey getKey() {
		return listKey;
	}

	@Override
	public boolean equals(Object obj) {
		GtdList aList = (GtdList)obj;
		if (aList == null) {
			return false;
		} else {
			return this.getKey().equals(aList.getKey());
		}
	}

	@Override
	public boolean addAll(GtdList theList) throws GtdListException {
		boolean listChanged = false;
		for (Iterator<GtdListItem> i=theList.iterator();i.hasNext();) {
			listChanged = listChanged | this.add(i.next());
		}
		return listChanged;
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		return getKey().toString();
	}

	@Override
	public Collection<GtdListItem> findItemsByString(String findString) {

		List<GtdListItem> foundItems = new ArrayList<GtdListItem>();
		
		for (Iterator<GtdListItem> iterator = list.iterator();iterator.hasNext();) {
			GtdListItem item = iterator.next();

			// empty find string matches all
			if (findString.length()==0 ) {
				foundItems.add(item);
				
			} else
				
			// case insensitive
			if (item.getDescription().toLowerCase().contains(findString.toLowerCase())) {
				foundItems.add(item);
			}
		}
		return foundItems;

	}
	
	

}
