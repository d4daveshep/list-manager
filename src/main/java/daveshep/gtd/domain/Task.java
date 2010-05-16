package daveshep.gtd.domain;

public class Task extends ListItem {
	
	public Task() {
		super();
		this.setType(ListItemType.TASK);
	}
	
	public Task(String desc) {
		this();
		this.setDescription(desc);
	}
	
	public String toString() {
		return "Task: " + this.getDescription();
	}
	
}