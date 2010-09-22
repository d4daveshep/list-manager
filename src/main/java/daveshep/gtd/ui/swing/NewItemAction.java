package daveshep.gtd.ui.swing;

import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.AbstractAction;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;

import daveshep.gtd.domain.ListItem;
import daveshep.gtd.domain.ListItemType;

public class NewItemAction extends AbstractAction {
	private BasicSwingUI frame;
	
	NewItemAction(BasicSwingUI frame) {
		super();
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent event) {

		ListItem parent = null;
		ListItemType parentType = null;
		boolean sub = ((event.getModifiers()&ActionEvent.SHIFT_MASK) == ActionEvent.SHIFT_MASK);
		String title = "";
		if (sub) {
			title = "New Sub-Item";
			Object[] selectedItems = frame.getItemList().getSelectedValues();
			if (selectedItems==null || selectedItems.length==0) {
				JOptionPane.showMessageDialog(frame, "Please select an item to become the parent of a new item", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			} else if (selectedItems.length>1) {
				JOptionPane.showMessageDialog(frame, "Can't add an item to multiple parent - please select single parent", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			} else {
				parent = (ListItem)selectedItems[0];
				parentType = parent.getType();
				
			}

		} else {
			title = "New Item";
		}

		System.out.print(title+"... ");
		
		// display a new item dialog box
		NewItemDialog newItemDialog = frame.getNewItemDialog();
		newItemDialog.setLocationRelativeTo(frame);
		newItemDialog.setTitle(title);
		newItemDialog.setParentItemType(parentType);
		newItemDialog.setVisible(true);
		
		// dialog has been closed
		ListItemType newItemType = newItemDialog.getNewItemType();
		if (newItemType!=null) {
			ListItem newItem=null;
			String description = newItemDialog.getNewDescription();
			switch (newItemType) {
			case GOAL:
				newItem = frame.getListManager().createGoal();
				break;
			case PROJECT:
				newItem = frame.getListManager().createProject();
				break;
			case TASK:
				newItem = frame.getListManager().createTask();
				break;
			case REFERENCE:
				newItem = frame.getListManager().createRefItem();
				break;
			}
			newItem.setId(new Random().nextLong());
			newItem.setDescription(description);
			
			if (sub) {
				parent.addChildItem(newItem);
				newItem.setFolder(parent.getFolder());
			}

			JList itemList = frame.getItemList();
			DefaultListModel itemListModel = (DefaultListModel) itemList.getModel();
			itemListModel.addElement(newItem);
			int index = itemListModel.indexOf(newItem);
			itemList.setSelectedIndex(index);
			itemList.ensureIndexIsVisible(index);
			
			
		}
		
		System.out.println();
		
//		// refresh the main list
//		System.out.println("doing refresh now...");
//		frame.refreshList();
	}
}
