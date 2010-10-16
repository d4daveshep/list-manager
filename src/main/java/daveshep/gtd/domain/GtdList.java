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
	
	/**
	 * Add all the items of the specified list to this list
	 * @param theList
	 * @return true if items were added to this list
	 * @throws GtdListException if an item is already on the list
	 */
	boolean addAll(GtdList theList) throws GtdListException;
	void clear();
	boolean contains(Object o);
	boolean isEmpty();
	Iterator<GtdListItem> iterator();
	boolean remove(Object o);
	int size();
	int sizeInclSublists();

}
