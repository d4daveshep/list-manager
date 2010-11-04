package daveshep.gtd.ui.swing;

import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import javax.swing.AbstractAction;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;

import daveshep.gtd.GtdListException;
import daveshep.gtd.domain.GtdListItem;

public class NewItemAction extends AbstractAction {
	private static Logger logger = Logger.getLogger("daveshep.gtd");
	private GtdListPanel parent;

	NewItemAction(GtdListPanel parent) {
		super();
		this.parent = parent;
	}

	@Override
	public void actionPerformed(ActionEvent event) {

		logger.info("Creating New Item...");

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

	}
}
