package daveshep.gtd.ui.swing;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.AbstractAction;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import daveshep.gtd.domain.GtdListItem;


public class EditDescriptionAction extends AbstractAction {
	private static Logger logger = Logger.getLogger("daveshep.gtd");
	private GtdListPanel parent;
	
	EditDescriptionAction(GtdListPanel parent) {
		super();
		this.parent = parent;

	}

	@Override
	public void actionPerformed(ActionEvent event) {
		
		logger.info("Editing Item Description...");

		if (parent.getItemList().getSelectedIndices().length>1) {
			logger.warning("Can't edit multiple descriptions");
			return;
		}
		
		// get the list item to edit
		GtdListItem listItem = (GtdListItem)parent.getItemList().getSelectedValue();

		logger.fine("Old description="+listItem.getDescription());
		
		String newDescription = (String) JOptionPane.showInputDialog(parent,null,"Edit Description",JOptionPane.QUESTION_MESSAGE,null,null,listItem.getDescription());
		if (newDescription==null || newDescription.length()==0) {
			return;
		}
		
		// update the description
		listItem.setDescription(newDescription);
		
		logger.fine("New description="+newDescription);
		
	}
}
