package daveshep.gtd.ui.swing;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import daveshep.gtd.domain.ListItem;

public class EditFolderAction extends AbstractAction {
	private BasicSwingUI frame;
	
	EditFolderAction(BasicSwingUI frame) {
		super();
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String title = "Edit Folder";
		System.out.print(title+"... ");
		
		int[] selectedIndices = frame.getItemList().getSelectedIndices();
		Object[] selectedItems = frame.getItemList().getSelectedValues();
		if (selectedItems.length==0) {
			System.out.println("Nothing to edit");
			return;
		}
		
		FolderDialog folderDialog = frame.getFolderDialog();
		
		folderDialog.setLocationRelativeTo(frame);
		folderDialog.setVisible(true);

		String newFolder = folderDialog.getFolderString();
		if (newFolder==null || newFolder.length()<1) {
			return;
		}
		
		// update the folder
		for (int i=0;i<selectedItems.length;i++) {
			((ListItem)selectedItems[i]).setFolder(newFolder);
		}
		
		frame.refreshList();
		frame.getItemList().setSelectedIndices(selectedIndices);
		
		System.out.println(newFolder);
		
	}
}
