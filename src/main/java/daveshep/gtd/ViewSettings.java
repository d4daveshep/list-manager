package daveshep.gtd;


public class ViewSettings {

	public boolean showSubItemsNested = false;
	public boolean showNotes = true;

	public String toString() {
		StringBuffer viewString = new StringBuffer();
		
		viewString.append("View: ");
		
		if (showSubItemsNested) {
			viewString.append("Nested ");
		} else {
			viewString.append("Flattened ");
		}
		
		return viewString.toString();
		
	}
}
