package daveshep.gtd.ui.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;

public class OkAction extends AbstractAction {
	
	private ActionListener listener;
	
	OkAction(ActionListener listener, String command) {
		super(command);
		this.listener = listener;
		this.putValue(Action.ACTION_COMMAND_KEY, command);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		listener.actionPerformed(e);
	}
}

