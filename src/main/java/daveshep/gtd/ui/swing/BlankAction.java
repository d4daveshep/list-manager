package daveshep.gtd.ui.swing;

import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import daveshep.gtd.domain.ListItem;

public class BlankAction extends AbstractAction {
	private BasicSwingUI frame;
	
	BlankAction(BasicSwingUI frame) {
		super();
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		System.out.print("Blank... ");
		
		// clear the list
		DefaultListModel model = (DefaultListModel)frame.getItemList().getModel();
		model.removeAllElements();
		
		// update the status bar
		frame.getStatusBar().setText("Ready...");
	}
}
