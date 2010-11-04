package daveshep.gtd.ui.swing;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JRootPane;
import javax.swing.SwingUtilities;

// TODO this class can be deleted 
public class ActivateDefaultButtonListener extends KeyAdapter implements ActionListener
{
	private static Logger logger = Logger.getLogger("daveshep.gtd");

	private JComboBox box;

	/** Creates new ActivateDefaultButtonListener */
	public ActivateDefaultButtonListener(JComboBox box)
	{
		this.box = box;
	}

	public void keyPressed(KeyEvent e)
	{
		logger.info(e.paramString());
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			// Simulate click on default button.
			doClick(e);
		} else if (e.getKeyCode()==KeyEvent.VK_ESCAPE) {
			// Simulate click on close button.
			doCloseDialog(e);
		}
	}

	private void doCloseDialog(KeyEvent e) {
		Component c = (Component)e.getSource();
		JRootPane rootPane = SwingUtilities.getRootPane(c);
		if(rootPane != null) {
			SelectListDialog dialog = (SelectListDialog)rootPane.getParent();
			JButton button = dialog.getCancelButton();
			button.doClick();
		}
		
	}

	public void actionPerformed(ActionEvent e)
	{
		doClick(e);
	}

	private void doClick(java.util.EventObject e)
	{
		Component c = (Component)e.getSource();

		JRootPane rootPane = SwingUtilities.getRootPane(c);
		if(rootPane != null) {
			JButton defaultButton = rootPane.getDefaultButton();

			if(defaultButton != null && !box.isPopupVisible()) {
				defaultButton.doClick();
			}
		}
	}
}