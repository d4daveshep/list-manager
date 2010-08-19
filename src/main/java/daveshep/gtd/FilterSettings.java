package daveshep.gtd;

import daveshep.gtd.domain.GoalStatus;
import daveshep.gtd.domain.ProjectStatus;
import daveshep.gtd.domain.TaskStatus;

public class FilterSettings {

	public boolean showTasks = true;
	public boolean showProjects = true;
	public boolean showGoals = true;
	public boolean showRefs = true;
	
	public boolean showStar = true;
	public boolean showNoStar = true;
	
	public boolean showDone = false;
	public boolean showNotDone = true;
	
	public String taskStatus = "All";
	public String projectStatus = "All";
	public String goalStatus = "All";
	public String taskContext = "ALL";
	public String folder = "ALL";

	public String toString() {
		StringBuffer filterString = new StringBuffer();
		
		if (showGoals) {
			filterString.append("GL ");
		} else {
			filterString.append("_ ");
		}
		
		if (showProjects) {
			filterString.append("PRJ ");
		} else {
			filterString.append("_ ");
		}
		
		if (showTasks) {
			filterString.append("TSK ");
		} else {
			filterString.append("_ ");
		}
		
		if (showRefs) {
			filterString.append("REF ");
		} else {
			filterString.append("_ ");
		}
		
		if (showStar) {
			filterString.append("ST ");
		} else {
			filterString.append("_ ");
		}
		
		if (showNoStar) {
			filterString.append("!ST ");
		} else {
			filterString.append("_ ");
		}
		
		if (showDone) {
			filterString.append("DN ");
		} else {
			filterString.append("_ ");
		}
		
		if (showNotDone) {
			filterString.append("!DN ");
		} else {
			filterString.append("_ ");
		}
		
		filterString.append("FDR:"+folder + " ");
		filterString.append("CTX:"+taskContext+" ");
		filterString.append("TS:"+taskStatus+" ");
		filterString.append("PS:"+projectStatus+" ");
		filterString.append("GS:"+goalStatus+" ");
		
		return filterString.toString();
		
	}
}
