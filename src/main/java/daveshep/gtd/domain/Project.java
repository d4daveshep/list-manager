package daveshep.gtd.domain;

public class Project extends ListItem {

	public Project() {
		super();
		this.setType(ListItemType.PROJECT);
	}
	
	public Project(String desc) {
		this();
		this.setDescription(desc);
	}
	
	public void addItem(ListItem item) {
		this.getManager().addListItem(item);
	}
	
	
	
}
