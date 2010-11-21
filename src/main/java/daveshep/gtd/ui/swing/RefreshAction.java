package daveshep.gtd.ui.swing;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class RefreshAction extends AbstractAction {
	private GtdListPanel parent;
	
	RefreshAction(GtdListPanel parent) {
		super();
		this.parent = parent;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		parent.refreshList();
	}
}
