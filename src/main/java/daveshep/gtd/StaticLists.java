package daveshep.gtd;

import daveshep.gtd.domain.GtdList;
import daveshep.gtd.domain.InMemoryListManager;

public class StaticLists {
	
	public static GtdList IN;
	public static GtdList WAITING_FOR;

	/**
	 * Create static lists using specified listManager
	 * @param listManager
	 * @throws GtdListException if any of the static lists already exists
	 */
	public static void createStaticLists(GtdListManager listManager) throws GtdListException {
		IN = listManager.createList("IN");
		WAITING_FOR = listManager.createList("Waiting For");
	}
	
}
