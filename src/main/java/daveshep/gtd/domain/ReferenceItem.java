/**
 * 
 */
package daveshep.gtd.domain;


import java.util.List;

import daveshep.gtd.FilterSettings;
import daveshep.gtd.domain.ListItem;;

/**
 * @author shephd
 *
 */
public class ReferenceItem extends ListItem {
	
	public ReferenceItem() {
		super();
		this.setType(ListItemType.REFERENCE);
	}

	public void addSubRefItem(ReferenceItem ref) {
		this.addChildItem(ref);
	}
	
	public void removeSubRefItem(ReferenceItem ref) {
		this.removeChildItem(ref);
	}
	
	public List getSubRefItems() {
		return this.getChildItems();
	}

	@Override
	public boolean passesFilter(FilterSettings filterSettings) {
		if (super.passesFilter(filterSettings)) {

			// check type matches
			if (!filterSettings.showRefs) {
				return false;
			}
			
		} else {
			return false;
		}

		return true; 
	}
	
}
