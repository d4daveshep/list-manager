package daveshep.gtd.ui.swing;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import daveshep.gtd.domain.ListItem;

public class EditDescriptionAction extends AbstractAction {
	private BasicSwingUI frame;
	
	EditDescriptionAction(BasicSwingUI frame) {
		super();
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String title = "Edit Description";
		System.out.print(title+"... ");
		
		if (frame.getItemList().getSelectedIndices().length>1) {
			System.out.println("Can't edit multiple descriptions");
			return;
		}
		
		// get the list item to edit
		ListItem listItem = (ListItem)frame.getItemList().getSelectedValue();
		
		String newDescription = (String) JOptionPane.showInputDialog(frame,null,title,JOptionPane.QUESTION_MESSAGE,null,null,listItem.getDescription());
		if (newDescription==null || newDescription.length()==0) {
			return;
		}
		
		// update the description
		listItem.setDescription(newDescription);
		
		System.out.println(newDescription);
		
	}
}
