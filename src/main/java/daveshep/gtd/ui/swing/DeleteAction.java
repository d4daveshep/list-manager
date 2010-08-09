package daveshep.gtd.ui.swing;

import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import daveshep.gtd.domain.ListItem;

public class DeleteAction extends AbstractAction {
	private BasicSwingUI frame;
	
	DeleteAction(BasicSwingUI frame) {
		super();
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		System.out.print("Delete... ");

		Object[] selectedItems = frame.getItemList().getSelectedValues();
		int option = JOptionPane.showConfirmDialog(frame, "Delete "+ selectedItems.length + " items?", "Delete", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null);
		System.out.println(option);
		if (option!=0) {
			return;
		} else {
			DefaultListModel model = (DefaultListModel) frame.getItemList().getModel();
			for (int i=0;i<selectedItems.length;i++) {
				ListItem listItem = (ListItem)selectedItems[i];
				model.removeElement(listItem);
				frame.getListManager().remove(listItem);
			}
		}
		System.out.println(selectedItems.length + " items");
		
		// TODO get list to update immediately
	}
}
