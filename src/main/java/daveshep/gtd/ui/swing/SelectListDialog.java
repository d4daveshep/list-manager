package daveshep.gtd.ui.swing;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import daveshep.gtd.domain.ListKey;

public class SelectListDialog extends JDialog implements ActionListener {

	private static Logger logger = Logger.getLogger("daveshep.gtd");

	private JButton okButton = new JButton("OK");
	private JButton cancelButton = new JButton("Cancel");
	private KeyAction okAction;
	private KeyAction cancelAction;

	private SelectListPanel selectListPanel;
	private BasicSwingUI parent;
	private ListKey listKey;

	SelectListDialog(BasicSwingUI parent) {
		super(parent,"Select List",true);
		this.parent = parent;
		selectListPanel = new SelectListPanel(this);
		selectListPanel.setListKeys(parent.getListManager().getListKeys());

		getContentPane().add(selectListPanel,BorderLayout.CENTER);
		
		// construct the button panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		
		okButton.setMnemonic(KeyEvent.VK_ENTER);
		okButton.addActionListener(this);
		okAction = new KeyAction(this, "OK");
	
		cancelButton.setMnemonic(KeyEvent.VK_ESCAPE);
		cancelButton.addActionListener(this);
		cancelAction = new KeyAction(this, "Cancel");
		
		buttonPanel.add(okButton);
		buttonPanel.add(cancelButton);
		getContentPane().add(buttonPanel,BorderLayout.SOUTH);

		getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0),"OK");
		getRootPane().getActionMap().put("OK", okAction );

		getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0),"Cancel");
		getRootPane().getActionMap().put("Cancel", cancelAction );

		pack();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		logger.info(e.paramString());
		
		if (e.getSource().equals(okButton)) {
			this.listKey = selectListPanel.getNewListKey();
			setVisible(false);			
			
		} else if (e.getSource().equals(cancelButton)) {
			
			setVisible(false);			
		}
	}

	public void setListKey(ListKey listKey) {
		this.listKey = listKey;
		selectListPanel.setListKey(listKey);

	}
	
	public ListKey getListKey() {
		return this.listKey;
	}
	
}
