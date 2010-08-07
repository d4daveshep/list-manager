package daveshep.gtd.ui.swing;

import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import daveshep.gtd.domain.ListItem;

public class ToggleStarAction extends AbstractAction {
	private BasicSwingUI frame;
	
	ToggleStarAction(BasicSwingUI frame) {
		super();
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		System.out.print("Toggle star... ");

		Object[] selectedItems = frame.getItemList().getSelectedValues();
		for (int i=0;i<selectedItems.length;i++) {
			ListItem listItem = (ListItem)selectedItems[i];
			if (listItem.isStarflag()) {
				listItem.setStarflag(false);
			} else {
				listItem.setStarflag(true);
			}
		}
		System.out.println(selectedItems.length + " items");
	}
}
