package daveshep.gtd.ui.swing;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;

public class EditContextDialog extends JDialog implements ItemListener, ActionListener {

	private JButton okButton = new JButton("OK");
	private JButton cancelButton = new JButton("Cancel");
	private KeyAction okAction;
	private KeyAction cancelAction;

	private JLabel contextLabel = new JLabel("Context");
	private JComboBox contextComboBox = new JComboBox();
	
	private JPanel settingsPanel = new JPanel();
	
	private BasicSwingUI frame;
	
	private String contextString;

	public EditContextDialog(BasicSwingUI frame) {
		super(frame,"Edit Context",true);
		this.frame = frame;

//		new AutoCompleteJComboBoxer(contextComboBox);
		contextComboBox.setEditable(true);
		
		// construct the settings panel
		settingsPanel.setLayout(new FlowLayout());
		settingsPanel.setBorder(new EmptyBorder(5,5,5,5));

		contextLabel.setDisplayedMnemonic(KeyEvent.VK_S);
		contextLabel.setLabelFor(contextComboBox);
		
		settingsPanel.add(contextLabel);
		settingsPanel.add(contextComboBox);
		
		getContentPane().add(settingsPanel,BorderLayout.CENTER);

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

		contextComboBox.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0),"OK");
		contextComboBox.getActionMap().put("OK", okAction );

		getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0),"Cancel");
		getRootPane().getActionMap().put("Cancel", cancelAction );
//
//		getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_C,InputEvent.ALT_DOWN_MASK),"TaskContext");
//		getRootPane().getActionMap().put("TaskContext", 
//			new AbstractAction() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				taskContextComboBox.requestFocus();
//				taskContextComboBox.showPopup();
//			} 
//		});


//		pack();
	}
	@Override
	public void itemStateChanged(ItemEvent event) {
		// don't need to do anything here
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		System.out.println(event.paramString());
		String command = event.getActionCommand();
		if (command==null) {
			return;
		}

		if (event.getActionCommand().equalsIgnoreCase("OK")) {
			
			// read combo boxes
			this.setContextString( (String) contextComboBox.getSelectedItem());
			
			setVisible(false);			
			
		} else if (event.getActionCommand().equalsIgnoreCase("Cancel")) {
			setContextString(null);
			setVisible(false);			
		}
	}
	
	@Override
	public void setVisible(boolean arg0) {

		// populate the context box combo fields and set the state
		contextComboBox.removeAllItems();
		String[] contexts = frame.getListManager().getTaskContexts();
		for (int i=0; i<contexts.length; i++) {
			contextComboBox.addItem(contexts[i]);
		}
		contextComboBox.setSelectedItem(getContextString());
		
		contextComboBox.grabFocus();
		pack();
		
		super.setVisible(arg0);
	}

	public String getContextString() {
		return contextString;
	}
	public void setContextString(String contextString) {
		this.contextString = contextString;
	}
	
}
