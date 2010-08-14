package daveshep.gtd.ui.swing;

import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import daveshep.gtd.domain.ListItem;

public class HelpAction extends AbstractAction {
	private BasicSwingUI frame;
	
	HelpAction(BasicSwingUI frame) {
		super();
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		System.out.print("Help... ");
		
		// display a help dialog box
		HelpDialog helpDialog = new HelpDialog(frame);
		helpDialog.setVisible(true);

		System.out.println("done");
	}
}
