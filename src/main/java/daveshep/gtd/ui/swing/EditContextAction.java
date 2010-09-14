package daveshep.gtd.ui.swing;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import daveshep.gtd.domain.ListItem;
import daveshep.gtd.domain.Task;

public class EditContextAction extends AbstractAction {
	private BasicSwingUI frame;
	
	EditContextAction(BasicSwingUI frame) {
		super();
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String title = "Edit Context";
		System.out.print(title+"... ");
		
		int[] selectedIndices = frame.getItemList().getSelectedIndices();
		Object[] selectedItems = frame.getItemList().getSelectedValues();
		if (selectedItems.length==0) {
			System.out.println("Nothing to edit");
			return;
		}

		// check we have at least one task selected
		boolean noTasks = true;
		for (int i=0; i<selectedItems.length; i++) {
			if (selectedItems[i] instanceof Task) {
				noTasks = false;
				break;
			}
		}
		if (noTasks) {
			System.out.println("Nothing to edit");
			return;
		}
		
		
		EditContextDialog contextDialog = frame.getContextDialog();

		if (selectedItems.length==1 && selectedItems[0] instanceof Task) {
			contextDialog.setContextString(((Task)selectedItems[0]).getContext());
		} else {
			contextDialog.setContextString(null);
		}
		

		contextDialog.setLocationRelativeTo(frame);
		contextDialog.setVisible(true);

		String newContext = contextDialog.getContextString();
		if (newContext==null || newContext.length()<1) {
			return;
		}
		
		// update the context
		for (int i=0;i<selectedItems.length;i++) {
			((Task)selectedItems[i]).setContext(newContext);
		}
		
		frame.refreshList();
		frame.getItemList().setSelectedIndices(selectedIndices);
		
		System.out.println(newContext);
		
	}
}
