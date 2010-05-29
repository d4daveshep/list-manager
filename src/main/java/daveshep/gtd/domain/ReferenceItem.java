/**
 * 
 */
package daveshep.gtd.domain;


import java.util.List;

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
	
}
