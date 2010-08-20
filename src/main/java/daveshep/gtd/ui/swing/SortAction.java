package daveshep.gtd.ui.swing;

import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import daveshep.gtd.domain.ListItem;

public class SortAction extends AbstractAction {
	private BasicSwingUI frame;
	
	SortAction(BasicSwingUI frame) {
		super();
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		System.out.print("Sort... ");
		
		// display a filter dialog box
		SortDialog filterDialog = frame.getSortDialog();
		filterDialog.setLocationRelativeTo(frame);
		filterDialog.setVisible(true);
		
		System.out.println(frame.getSorter().toString());
		
		// refresh the main list
		System.out.println("doing refresh now...");
		frame.refreshList();
	}
}
