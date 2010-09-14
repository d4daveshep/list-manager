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

public class EditStatusDialog extends JDialog implements ItemListener, ActionListener {

	private JButton okButton = new JButton("OK");
	private JButton cancelButton = new JButton("Cancel");
	private KeyAction okAction;
	private KeyAction cancelAction;

	private JLabel statusLabel = new JLabel("Status");
	private JComboBox taskStatusComboBox = new JComboBox();
	private JComboBox projectStatusComboBox = new JComboBox();
	private JComboBox goalStatusComboBox = new JComboBox();
	private JComboBox statusComboBox = null;
	
	private JPanel settingsPanel = new JPanel();
	
	private BasicSwingUI frame;
	
	private ListItemType listItemType;
	private String statusString;

	public EditStatusDialog(BasicSwingUI frame) {
		super(frame,"Status",true);
		this.frame = frame;

		new AutoCompleteJComboBoxer(taskStatusComboBox);
		new AutoCompleteJComboBoxer(projectStatusComboBox);
		new AutoCompleteJComboBoxer(goalStatusComboBox);
		
		// construct the settings panel
		settingsPanel.setLayout(new GridLayout(0,2));
		settingsPanel.setBorder(new EmptyBorder(5,5,5,5));

		// populate taskStatusComboBox
		TaskStatus[] taskStatuses = TaskStatus.values();
		for (int i=0;i<taskStatuses.length;i++) {
				taskStatusComboBox.addItem(taskStatuses[i].name());
		}	

		// populate projectStatusComboBox
		ProjectStatus[] projectStatuses = ProjectStatus.values();
		for (int i=0;i<projectStatuses.length;i++) {
			projectStatusComboBox.addItem(projectStatuses[i].name());
		}
		
		// populate goalStatusComboBox
		GoalStatus[] goalStatuses = GoalStatus.values();
		for (int i=0;i<goalStatuses.length;i++) {
			goalStatusComboBox.addItem(goalStatuses[i].name());
		}

		statusComboBox = taskStatusComboBox;
		
		settingsPanel.add(statusLabel);
		settingsPanel.add(statusComboBox);
		
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

		taskStatusComboBox.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0),"OK");
		taskStatusComboBox.getActionMap().put("OK", okAction );
		projectStatusComboBox.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0),"OK");
		projectStatusComboBox.getActionMap().put("OK", okAction );
		goalStatusComboBox.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0),"OK");
		goalStatusComboBox.getActionMap().put("OK", okAction );

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
			
			// read combo boxes
			this.setStatusString( (String) statusComboBox.getSelectedItem());
			
			setVisible(false);			
			
		} else if (event.getActionCommand().equalsIgnoreCase("Cancel")) {
			this.setStatusString(null);
			setVisible(false);			
		}
	}
	@Override
	public void setVisible(boolean arg0) {

		settingsPanel.remove(statusComboBox);

		switch (listItemType) {
		case GOAL:
			statusLabel.setText("Goal Status");
			statusComboBox = goalStatusComboBox;
			break;
		case PROJECT:
			statusLabel.setText("Project Status");
			statusComboBox = projectStatusComboBox;
			break;
		case TASK:
			statusLabel.setText("Task Status");
			statusComboBox = taskStatusComboBox;
			break;
		default:

		}
		settingsPanel.add(statusComboBox);
		
		statusComboBox.grabFocus();
		
		validate();
		
		super.setVisible(arg0);
	}

	public ListItemType getListItemType() {
		return listItemType;
	}
	public void setListItemType(ListItemType listItemType) {
		this.listItemType = listItemType;
	}
	public String getStatusString() {
		return statusString;
	}
	public void setStatusString(String statusString) {
		this.statusString = statusString;
	}
	
}
