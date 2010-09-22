package daveshep.gtd.ui.swing;

import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import daveshep.gtd.domain.ListItem;

public class NewItemAction extends AbstractAction {
	private BasicSwingUI frame;
	
	NewItemAction(BasicSwingUI frame) {
		super();
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent event) {

		boolean sub = ((event.getModifiers()&ActionEvent.SHIFT_MASK) == ActionEvent.SHIFT_MASK);
		String title = "";
		if (sub) {
			title = "New Sub";
			Object[] selectedItems = frame.getItemList().getSelectedValues();
			if (selectedItems==null || selectedItems.length==0) {
				JOptionPane.showMessageDialog(frame, "Please select an item to become the parent of a new item", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			} else if (selectedItems.length>1) {
				JOptionPane.showMessageDialog(frame, "Can't add an item to multiple parent - please select single parent", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			} else {
				ListItem parent = (ListItem)selectedItems[0];
				
			}

		} else {
			title = "New";
		}

		System.out.print(title+"... ");
		
		
		// display a new item dialog box
		NewItemDialog newItemDialog = frame.getNewItemDialog();
		newItemDialog.setLocationRelativeTo(frame);
		newItemDialog.setVisible(true);
		
		System.out.println();
		
//		// refresh the main list
//		System.out.println("doing refresh now...");
//		frame.refreshList();
	}
}
