package daveshep.gtd.ui.swing;

import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import daveshep.gtd.domain.ListItem;

public class ViewAction extends AbstractAction {
	private BasicSwingUI frame;
	
	ViewAction(BasicSwingUI frame) {
		super();
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		System.out.print("View... ");
		
		// display a filter dialog box
		ViewDialog filterDialog = frame.getViewDialog();
		filterDialog.setLocationRelativeTo(frame);
		filterDialog.setVisible(true);
		
		// refresh the main list
		System.out.println("doing refresh now...");
		frame.refreshList();
	}
}
