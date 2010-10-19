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

import daveshep.gtd.GtdListException;
import daveshep.gtd.domain.GtdListItem;

public class NewItemAction extends AbstractAction {
	private GtdListPanel parent;

	NewItemAction(GtdListPanel parent) {
		super();
		this.parent = parent;
	}

	@Override
	public void actionPerformed(ActionEvent event) {

		//		ListItem parent = null;
		//		ListItemType parentType = null;
		//		boolean sub = ((event.getModifiers()&ActionEvent.SHIFT_MASK) == ActionEvent.SHIFT_MASK);
		String title = "";
		//		if (sub) {
		//			title = "New Sub-Item";
		//			Object[] selectedItems = frame.getItemList().getSelectedValues();
		//			if (selectedItems==null || selectedItems.length==0) {
		//				JOptionPane.showMessageDialog(frame, "Please select an item to become the parent of a new item", "Error", JOptionPane.ERROR_MESSAGE);
		//				return;
		//			} else if (selectedItems.length>1) {
		//				JOptionPane.showMessageDialog(frame, "Can't add an item to multiple parent - please select single parent", "Error", JOptionPane.ERROR_MESSAGE);
		//				return;
		//			} else {
		//				parent = (ListItem)selectedItems[0];
		//				parentType = parent.getType();
		//				
		//			}
		//
		//		} else {
		title = "New Item";
		//		}

		System.out.print(title+"... ");

		// show description dialog
		String description = (String) JOptionPane.showInputDialog(parent,null,"Add new item",JOptionPane.QUESTION_MESSAGE,null,null,"");
		if (description==null || description.length()<1) {
			return;
		}

		// create a new list item
		GtdListItem newItem = parent.getListManager().createListItem();
		newItem.setDescription(description);
		
		// add it to the list model and list display
		try {
			parent.getGtdList().add(newItem);

			//			if (sub) {
			//				parent.addChildItem(newItem);
			//				newItem.setFolder(parent.getFolder());
			//			}

			JList itemList = parent.getItemList();
			DefaultListModel itemListModel = (DefaultListModel) itemList.getModel();
			itemListModel.addElement(newItem);
			int index = itemListModel.indexOf(newItem);
			itemList.setSelectedIndex(index);
			itemList.ensureIndexIsVisible(index);

		} catch (GtdListException e) {
			JOptionPane.showMessageDialog(parent, e.getMessage(), "GtdListException", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

		System.out.println();

	}
}
