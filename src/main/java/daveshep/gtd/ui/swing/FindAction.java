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
	private FindType findType;

	public FindAction(GtdListPanel parent, FindType type) {
		super();
		this.parent = parent;
		this.findType = type;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		logger.info(event.getActionCommand());
		boolean add = ((event.getModifiers()&ActionEvent.SHIFT_MASK) == ActionEvent.SHIFT_MASK);
		String title = "";
		if (add) {
			title="Find + Add (" + findType.toString().toLowerCase()+")";
			logger.info(findType.toString()+" Find+Add...");
		} else {
			title="Find (" + findType.toString().toLowerCase()+")";
			logger.info(findType.toString()+" Find...");
		}

		// show find dialog
		String findString = (String) JOptionPane.showInputDialog(parent,null,title,JOptionPane.QUESTION_MESSAGE,null,null,"");
		if (findString==null) {
			return;
		}

		Collection<GtdListItem> foundItems;

		if (findType==FindType.LOCAL) {
			// find in this list
			foundItems = parent.getGtdList().findItemsByString(findString);
		} else if (findType==FindType.GLOBAL) {
			// find in any list
			foundItems = parent.getListManager().findItemsByString(findString);
		} else {
			throw new RuntimeException("don't know what type of find action to perform");
		}

		logger.info("found: "+ foundItems.size());
		
		// clear the list then add found items
		DefaultListModel model = (DefaultListModel)parent.getItemList().getModel();
		if (!add) {
			model.removeAllElements();
		}
		for (Iterator<GtdListItem> i=foundItems.iterator();i.hasNext();) {
			model.addElement(i.next());
		}
		if (add) {
			parent.setTitleText(parent.getTitleText() + "+\""+ findString +"\"");
		} else {
			parent.setTitleText("Find ("+findType.toString().toLowerCase()+") \"" + findString +"\"");
		}
	}

}
