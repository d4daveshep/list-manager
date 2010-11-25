package daveshep.gtd;

import java.util.Collection;
import java.util.Set;

import daveshep.gtd.domain.GtdList;
import daveshep.gtd.domain.GtdListItem;
import daveshep.gtd.domain.ListKey;

public interface GtdListManager {
	
	// factory methods
	public GtdListItem createListItem();
	public GtdListItem createListItem(String description);
	
	/**
	 * Create a new list
	 * @param title
	 * @return a new empty list
	 * @throws GtdListException if the list already exists
	 */
	public GtdList createList(String title) throws DuplicateListException;
	public GtdList createList(String title, String subtitle) throws DuplicateListException;
	public GtdList createTemporaryList(String title);
	
	// list retrieval
	/**
	 * Get a reference to a list, assumes the list already exists in the list of lists
	 * @param list
	 * @return this list
	 * @throws GtdListException if the list doesn't exist
	 */
	public GtdList getList(GtdList list) throws GtdListException;

	/**
	 * Get a reference to a list, creates the list if it doesn't exist and adds it to the list of lists
	 * @param listkey
	 * @return the list matching the key, empty list if not found
	 * @throws IllegalArgumentException if listkey is null
	 */
	public GtdList getList(ListKey listkey) throws IllegalArgumentException;
	
	/**
	 * Get a reference to a temporary list containing items from the list requested plus all sub list items
	 * @param list
	 * @param includeSubListItems
	 * @return the list
	 * @throws GtdListException if the list doesn't exist
	 */
//	public GtdList getList(GtdList list, boolean includeSubListItems) throws GtdListException;
	
	// get stats
	int getListCount();
	Set<ListKey> getListKeys();
	
	// find methods
	/**
	 * @param findString
	 * @return Collection of items found containing findString
	 */
	
	public Collection<GtdListItem> findItemsByString(String findString);
	
	/**
	 * Remove all lists and list items
	 */
	public void removeAll();
	
	/**
	 * Copy an item to another list
	 * @param item
	 * @param listKey
	 */
	public void copyTo(GtdListItem item, ListKey listKey);
	
	/**
	 * Move an item to another list
	 * @param item
	 * @param listKey
	 */
	public void moveTo(GtdListItem item, ListKey listKey);
	
}