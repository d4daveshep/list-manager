package daveshep.gtd;

public class FilterSettings {

	public boolean showTasks = true;
	public boolean showProjects = true;
	public boolean showGoals = true;
	public boolean showRefs = true;
	
	public boolean showStar = true;
	public boolean showNoStar = true;
	
	public boolean showDone = false;
	public boolean showNotDone = true;

	public String toString() {
		StringBuffer filterString = new StringBuffer();
		
		if (showProjects) {
			filterString.append("P.");
		} else {
			filterString.append("_.");
		}
		
		if (showGoals) {
			filterString.append("G.");
		} else {
			filterString.append("_.");
		}
		
		if (showTasks) {
			filterString.append("T.");
		} else {
			filterString.append("_.");
		}
		
		if (showRefs) {
			filterString.append("R ");
		} else {
			filterString.append("_ ");
		}
		
		if (showStar) {
			filterString.append("* ");
		} else {
			filterString.append("_ ");
		}
		
		if (showDone) {
			filterString.append("+");
		} else {
			filterString.append("-");
		}
		
		return filterString.toString();
		
	}
}
