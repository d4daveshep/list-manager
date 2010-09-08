package daveshep.gtd.ui.swing;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import daveshep.gtd.domain.Goal;
import daveshep.gtd.domain.GoalStatus;
import daveshep.gtd.domain.ListItem;
import daveshep.gtd.domain.ListItemType;
import daveshep.gtd.domain.Project;
import daveshep.gtd.domain.ProjectStatus;
import daveshep.gtd.domain.Task;
import daveshep.gtd.domain.TaskStatus;

public class EditDueDateAction extends AbstractAction {
	private BasicSwingUI frame;
	
	EditDueDateAction(BasicSwingUI frame) {
		super();
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String title = "Edit Due Date";
		System.out.print(title+"... ");
		
		int[] selectedIndices = frame.getItemList().getSelectedIndices();
		Object[] selectedItems = frame.getItemList().getSelectedValues();
		if (selectedItems.length==0) {
			System.out.println("Nothing to edit");
			return;
		}

		DueDateDialog dueDateDialog = frame.getDueDateDialog();
		
		dueDateDialog.setLocationRelativeTo(frame);
		dueDateDialog.setVisible(true);

		Date newDate = dueDateDialog.getDueDate();
		if (newDate==null) {
			return;
		}
		
		// update the due date
		for (int i=0;i<selectedItems.length;i++) {
			((ListItem)selectedItems[i]).setDueDate(newDate);
		}
		
		frame.refreshList();
		frame.getItemList().setSelectedIndices(selectedIndices);
		
		System.out.println(newDate);
		
	}
}
