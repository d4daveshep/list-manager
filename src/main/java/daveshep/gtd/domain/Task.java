package daveshep.gtd.domain;

import java.util.List;

/**
 * @author shephd
 *
 * A task is a unit of work, it can't have sub tasks or sub projects
 * A task can be part of a project of tasks.
 * A task can have sub reference items.
 */
public class Task extends ListItem {
	
	private TaskStatus status;
	private TaskEnergy energy;
	private TaskSize size;
	private String context;
	private TaskRepeater repeat;
	
	public Task() {
		super();
		this.setType(ListItemType.TASK);
		this.setEnergy(TaskEnergy.NORMAL);
		this.setStatus(TaskStatus.NONE);
	}
	
	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	public TaskEnergy getEnergy() {
		return energy;
	}

	public void setEnergy(TaskEnergy energy) {
		this.energy = energy;
	}

	public TaskSize getSize() {
		return size;
	}

	public void setSize(TaskSize size) {
		this.size = size;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String toString() {
		return super.toString();
	}

	@Override
	public boolean isDone() {
		// TODO Auto-generated method stub
		return super.isDone();
	}

	@Override
	public void setDone(boolean done) {
		// TODO Auto-generated method stub
		super.setDone(done);
	}
	
}