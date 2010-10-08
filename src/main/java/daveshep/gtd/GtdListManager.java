package daveshep.gtd;

import daveshep.gtd.domain.GtdList;
import daveshep.gtd.domain.GtdListItem;

public interface GtdListManager {
	
	// factory methods
	public GtdListItem createListItem();
	public GtdListItem createListItem(String description);
	public GtdList createList(String key1, String key2);
	
}