package daveshep.gtd.domain;

import java.util.ArrayList;
import java.util.List;

import daveshep.gtd.GtdListManager;
import daveshep.gtd.util.IdGenerator;

public class InMemoryListManager implements GtdListManager {

	private static GtdListManager listManager = new InMemoryListManager();
	private List<GtdListItem> storage;
	
	public static GtdListManager getInstance() {
		return listManager;
	}
	
	private InMemoryListManager() {
		storage = new ArrayList<GtdListItem>();
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

}
