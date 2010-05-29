/**
 * 
 */
package daveshep.gtd.domain;

/**
 * @author shephd
 *
 */
public class Goal extends ListItem {

	private GoalType goalType;
	private GoalStatus status;
	
	public Goal() {
		super();
		this.setType(ListItemType.GOAL);
	}

	public GoalType getGoalType() {
		return goalType;
	}

	public void setGoalType(GoalType goalType) {
		this.goalType = goalType;
	}

	public GoalStatus getStatus() {
		return status;
	}

	public void setStatus(GoalStatus status) {
		this.status = status;
	}
	
}
