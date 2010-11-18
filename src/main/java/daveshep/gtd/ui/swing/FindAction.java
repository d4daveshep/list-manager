package daveshep.gtd.ui.swing;

import java.awt.event.ActionEvent;
import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Logger;

import javax.swing.AbstractAction;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import daveshep.gtd.domain.GtdListItem;

public class FindAction extends AbstractAction {

	private static Logger logger = Logger.getLogger("daveshep.gtd");
	private GtdListPanel parent;

	FindAction(GtdListPanel parent) {
		super();
		this.parent = parent;
	}
	

	
	@Override
	public void actionPerformed(ActionEvent e) {
		logger.info("Find...");

		// show find dialog
		String findString = (String) JOptionPane.showInputDialog(parent,null,"Find",JOptionPane.QUESTION_MESSAGE,null,null,"");
		if (findString==null) {
			return;
		}

		// find in GTD model
		Collection<GtdListItem> foundItems = parent.getListManager().findItemsByString(findString);
		logger.info("found: "+ foundItems.size());
		
		// clear the list then add found items
		DefaultListModel model = (DefaultListModel)parent.getItemList().getModel();
//		if (!add) {
			model.removeAllElements();
//		}
		for (Iterator<GtdListItem> i=foundItems.iterator();i.hasNext();) {
			model.addElement(i.next());
		}
	}

}
