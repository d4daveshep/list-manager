/**
 * 
 */
package daveshep.gtd.domain;

import daveshep.gtd.FilterSettings;

/**
 * @author shephd
 *
 */
public class Goal extends ListItem {

	private GoalType goalType;
	private GoalStatus status;
	
	Goal() {
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

	@Override
	public boolean passesFilter(FilterSettings filterSettings) {
		if (super.passesFilter(filterSettings)) {

			// check type matches
			if (!filterSettings.showGoals) {
				return false;
			}

			// check status matches
			if (!filterSettings.goalStatus.equalsIgnoreCase("all")) {
				if (!(getStatus().name().equals(filterSettings.goalStatus))) {
					return false;
				}
			}			
	
			
		} else {
			return false;
		}

		return true; 
	}
	
}
