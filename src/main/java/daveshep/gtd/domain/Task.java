package daveshep.gtd.domain;

import java.util.Date;
import java.util.List;

import daveshep.gtd.FilterSettings;

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
		StringBuffer output = new StringBuffer(super.toString());
		output.append(getStatus());
		output.append(" | ");
		output.append(getContext());
		output.append(" | ");
		
		return output.toString();
	}

	@Override
	public boolean isDone() {
		return super.isDone();
	}

	@Override
	public void setDone(boolean done) {
		super.setDone(done);
	}

	@Override
	public Date getDueDate() {
		return super.getDueDate();
	}

	@Override
	public void setDueDate(Date dueDate) {
		super.setDueDate(dueDate);
	}

	@Override
	public Date getCompletedDate() {
		return super.getCompletedDate();
	}

	@Override
	public boolean passesFilter(FilterSettings filterSettings) {
		
		if (super.passesFilter(filterSettings)) {

			// check type matches
			if (!filterSettings.showTasks) {
				return false;
			}
			
			// check status matches
			if (!filterSettings.taskStatus.equalsIgnoreCase("all")) {
				if (!(getStatus().name().equals(filterSettings.taskStatus))) {
					return false;
				}
			}			

			// check context matches
			if (filterSettings.taskContext.equalsIgnoreCase("none")) {
				if (getContext()==null || getContext().length()==0) {
					return true;
				} else {
					return false;
				}
			} else
			if (!filterSettings.taskContext.equalsIgnoreCase("all")) {
				if (!(getContext().equals(filterSettings.taskContext))) {
					return false;
				}
			} 
			
		} else {
			return false;
		}

		return true; 
	}
	
}