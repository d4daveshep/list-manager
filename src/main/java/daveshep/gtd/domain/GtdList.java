package daveshep.gtd.domain;

import java.util.Iterator;

import daveshep.gtd.GtdListException;

public interface GtdList {
	
	String getTitle();
	String getSubtitle();
	ListKey getKey();

	/**
	 * Add an item to a list
	 * @param item
	 * @return true if item was added, false if it already existed on the list
	 * @throws GtdListException if item is already on a list
	 */
	boolean add(GtdListItem item) throws GtdListException;
	void clear();
	boolean contains(Object o);
	boolean isEmpty();
	Iterator<GtdListItem> iterator();
	boolean remove(Object o);
	int size();

}
