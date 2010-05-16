package daveshep.gtd.domain;

public class Task {
	
	private Long id;
	private String description;
	
	public Task() {
		
	}
	
	public Task( String desc ) {
		this();
		setDescription( desc );
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDescription( String desc ) {
		description = desc;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String toString() {
		return "Task: " + description;
	}
	
}