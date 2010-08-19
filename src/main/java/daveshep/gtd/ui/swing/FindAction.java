package daveshep.gtd.ui.swing;

import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import daveshep.gtd.domain.ListItem;

public class FindAction extends AbstractAction {
	private BasicSwingUI frame;
	
	FindAction(BasicSwingUI frame) {
		super();
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		boolean add = ((event.getModifiers()&ActionEvent.ALT_MASK) == ActionEvent.ALT_MASK);
		String title = "";
		if (add) {
			title = "Find+Add";
		} else {
			title = "Find";
		}
		System.out.print(title+"... ");
		
		// display a find dialog box
		String findString = JOptionPane.showInputDialog(frame,null,title,JOptionPane.QUESTION_MESSAGE);
		if (findString!=null) {
			frame.setFindString(findString);
		} else {
			return;
		}
		
		System.out.println(findString);
		
		// find in GTD model
		List<ListItem> foundItems = frame.getListManager().findItemsByString(findString,frame.getFilterSettings());
		System.out.println("found: "+ foundItems.size());
		
		// clear the list then add found items
		DefaultListModel model = (DefaultListModel)frame.getItemList().getModel();
		if (!add) {
			model.removeAllElements();
		}
		for (Iterator<ListItem> i=foundItems.iterator();i.hasNext();) {
			model.addElement(i.next());
		}
		
		// update the status bar
		frame.getStatusBar().setText(title +"... \"" + findString + "\" " + foundItems.size() + " found\t\tFilters: " + frame.getFilterSettings().toString());
	}
}
