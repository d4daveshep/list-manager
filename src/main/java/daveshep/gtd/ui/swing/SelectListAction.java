package daveshep.gtd.ui.swing;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;

import daveshep.gtd.domain.ListKey;

public class SelectListAction extends AbstractAction {

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
		
		ListKey newListKey = dialog.getListKey();
		if (parent.getListKey().equals(newListKey)) {
			return;
		} else {

			parent.displayNewList(newListKey);
			
		}
		
		
	}
}
