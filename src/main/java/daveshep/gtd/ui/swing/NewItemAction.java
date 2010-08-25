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
		System.out.print("New... ");
		
		// display a new item dialog box
		NewItemDialog newItemDialog = frame.getNewItemDialog();
		newItemDialog.setLocationRelativeTo(frame);
		newItemDialog.setVisible(true);
		
		System.out.println();
		
		// refresh the main list
		System.out.println("doing refresh now...");
		frame.refreshList();
	}
}
