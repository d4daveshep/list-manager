package daveshep.gtd;

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
	
	// list retrieval
	/**
	 * Get a reference to a list, assumes the list already exists
	 * @param list
	 * @return this list
	 * @throws GtdListException if the list doesn't exist
	 */
	public GtdList getList(GtdList list) throws GtdListException;
	public GtdList getList(ListKey listkey);
	
}