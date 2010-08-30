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

import daveshep.gtd.domain.Goal;
import daveshep.gtd.domain.GoalStatus;
import daveshep.gtd.domain.ListItem;
import daveshep.gtd.domain.ListItemType;
import daveshep.gtd.domain.Project;
import daveshep.gtd.domain.ProjectStatus;
import daveshep.gtd.domain.Task;
import daveshep.gtd.domain.TaskStatus;

public class EditStatusAction extends AbstractAction {
	private BasicSwingUI frame;
	
	EditStatusAction(BasicSwingUI frame) {
		super();
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String title = "Edit Status";
		System.out.print(title+"... ");
		
		int[] selectedIndices = frame.getItemList().getSelectedIndices();
		Object[] selectedItems = frame.getItemList().getSelectedValues();
		if (selectedItems.length==0) {
			System.out.println("Nothing to edit");
			return;
		}
		ListItemType type0 = ((ListItem) selectedItems[0]).getType();
		if (type0==ListItemType.REFERENCE) {
			System.out.println("Can't edit Reference status");
			return;
		}
		if (selectedItems.length>1) {
			// check all items selected are of same type
			for (int i=1;i<selectedItems.length;i++) {
				if (type0 != ((ListItem) selectedItems[i]).getType()) {
					System.out.println("Can't edit status of different types");
					return;
				}
			}
		}
		
		StatusDialog statusDialog = frame.getStatusDialog();
		statusDialog.setListItemType(type0);
		
		statusDialog.setLocationRelativeTo(frame);
		statusDialog.setVisible(true);

		String newStatus = statusDialog.getStatusString();
		if (newStatus==null || newStatus.length()<1) {
			return;
		}
		
		
		// update the status
		for (int i=0;i<selectedItems.length;i++) {
			switch (type0) {
			case GOAL:
				((Goal)selectedItems[i]).setStatus(GoalStatus.valueOf(newStatus));
				break;
			case PROJECT:
				((Project)selectedItems[i]).setStatus(ProjectStatus.valueOf(newStatus));
				break;
			case TASK:
				((Task)selectedItems[i]).setStatus(TaskStatus.valueOf(newStatus));
				break;
			default:
					
			}
		}
		
		frame.refreshList();
		frame.getItemList().setSelectedIndices(selectedIndices);
		
		System.out.println(newStatus);
		
	}
}
