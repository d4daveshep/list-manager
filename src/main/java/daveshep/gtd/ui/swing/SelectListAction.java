package daveshep.gtd.ui.swing;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.util.logging.Logger;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;

import daveshep.gtd.domain.ListKey;

public class SelectListAction extends AbstractAction {

	private static Logger logger = Logger.getLogger("daveshep.gtd");
	private GtdListPanel parent;

	SelectListAction(GtdListPanel parent) {
		super();
		this.parent = parent;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// show description dialog
		
		SelectListDialog dialog = new SelectListDialog(parent.getParent());
		
		dialog.setListKey(parent.getListKey());
		
		dialog.setVisible(true);
		
		ListKey newListKey = dialog.getNewListKey();
		logger.info("newListKey= "+newListKey.toString());
		
		if (parent.getListKey().equals(newListKey)) {
			parent.refreshList();
		} else {
			parent.displayNewList(newListKey);
		}
		
		
	}
}
