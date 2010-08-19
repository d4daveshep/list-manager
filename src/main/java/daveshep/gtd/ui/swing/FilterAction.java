package daveshep.gtd.ui.swing;

import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import daveshep.gtd.domain.ListItem;

public class FilterAction extends AbstractAction {
	private BasicSwingUI frame;
	
	FilterAction(BasicSwingUI frame) {
		super();
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		System.out.print("Filter... ");
		
		// display a filter dialog box
		FilterDialog filterDialog = frame.getFilterDialog();
		filterDialog.setLocationRelativeTo(frame);
		filterDialog.setVisible(true);
		
		System.out.println(frame.getFilterSettings().toString());
		
		// refresh the main list
		System.out.println("doing refresh now...");
		frame.refreshList();
	}
}
