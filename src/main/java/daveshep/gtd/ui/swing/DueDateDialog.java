package daveshep.gtd.ui.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.util.Date;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;

import daveshep.gtd.domain.Goal;
import daveshep.gtd.domain.GoalStatus;
import daveshep.gtd.domain.ListItemType;
import daveshep.gtd.domain.Project;
import daveshep.gtd.domain.ProjectStatus;
import daveshep.gtd.domain.Task;
import daveshep.gtd.domain.TaskStatus;

public class DueDateDialog extends JDialog implements ItemListener, ActionListener {

	private JButton okButton = new JButton("OK");
	private JButton cancelButton = new JButton("Cancel");
	private KeyAction okAction;
	private KeyAction cancelAction;

	private JLabel dueDateLabel = new JLabel("Due Date");
	private JTextField dueDateTextField = new JTextField(10);
	
	private JPanel settingsPanel = new JPanel();
	
	private BasicSwingUI frame;
	
	private ListItemType listItemType;
	private Date dueDate;

	public DueDateDialog(BasicSwingUI frame) {
		super(frame,"Due Date",true);
		this.frame = frame;

		// construct the settings panel
		settingsPanel.setLayout(new GridLayout(0,2));
		settingsPanel.setBorder(new EmptyBorder(5,5,5,5));

		
		settingsPanel.add(dueDateLabel);
		settingsPanel.add(dueDateTextField);
		
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

		dueDateTextField.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0),"OK");
		dueDateTextField.getActionMap().put("OK", okAction );

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


		pack();
	}
	@Override
	public void itemStateChanged(ItemEvent event) {
		// don't need to do anything here
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		if (command==null) {
			return;
		}
		System.out.println(command);
		
		if (event.getActionCommand().equalsIgnoreCase("OK")) {
			
			// read text field
			
			setVisible(false);			
			
		} else if (event.getActionCommand().equalsIgnoreCase("Cancel")) {
//			this.setStatusString(null);
			setVisible(false);			
		}
	}
	@Override
	public void setVisible(boolean arg0) {

		
		super.setVisible(arg0);
	}

	public ListItemType getListItemType() {
		return listItemType;
	}
	public void setListItemType(ListItemType listItemType) {
		this.listItemType = listItemType;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	
}
