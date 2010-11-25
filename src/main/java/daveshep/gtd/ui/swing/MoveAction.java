package daveshep.gtd.ui.swing;

import java.awt.event.ActionEvent;
import java.util.logging.Logger;

import javax.swing.AbstractAction;

import daveshep.gtd.domain.GtdListItem;
import daveshep.gtd.domain.ListKey;

public class MoveAction extends AbstractAction {

	private static Logger logger = Logger.getLogger("daveshep.gtd");
	private GtdListPanel parent;

	public MoveAction(GtdListPanel parent) {
		super();
		this.parent = parent;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		logger.info("Move...");

		// check that we have something selected
		if (parent.getItemList().getSelectedIndices().length==0) {
			return;
		}

		// get the selected items
		Object[] selectedItems = parent.getItemList().getSelectedValues();

		// get the list to move to
		SelectListDialog dialog = new SelectListDialog(parent.getParent());
		dialog.setListKey(parent.getListKey());
		dialog.setLocationRelativeTo(parent);
		dialog.setTitle("Move items to list");
		dialog.setVisible(true);
		if (dialog.isCancelled()) {
			return;
		}
		ListKey newListKey = dialog.getNewListKey();
		
		// do the move
		logger.info("about to move "+selectedItems.length+" to list: "+newListKey.toString());
		for (int i=0;i<selectedItems.length;i++) {
			GtdListItem item = (GtdListItem) selectedItems[i];
			parent.getListManager().moveTo(item,newListKey);
		}
		
		parent.refreshList();
		
	}

}
