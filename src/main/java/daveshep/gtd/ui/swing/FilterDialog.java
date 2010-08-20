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

import daveshep.gtd.FilterSettings;
import daveshep.gtd.domain.GoalStatus;
import daveshep.gtd.domain.ProjectStatus;
import daveshep.gtd.domain.TaskStatus;

public class FilterDialog extends JDialog implements ItemListener, ActionListener {

	private JButton okButton = new JButton("OK");
	private JButton cancelButton = new JButton("Cancel");
	private KeyAction okAction;
	private KeyAction cancelAction;

	private JCheckBox goalsCheckBox = new JCheckBox("Goals");
	private JCheckBox projectsCheckBox = new JCheckBox("Projects");
	private JCheckBox tasksCheckBox = new JCheckBox("Tasks");
	private JCheckBox refsCheckBox = new JCheckBox("Ref. Items");
	private JCheckBox starCheckBox = new JCheckBox("Star");
	private JCheckBox noStarCheckBox = new JCheckBox("No Star");
	private JCheckBox doneCheckBox = new JCheckBox("Done");
	private JCheckBox notDoneCheckBox = new JCheckBox("Not Done");
	
	private JLabel taskStatusLabel = new JLabel("Task Status");
	private JComboBox taskStatusComboBox = new JComboBox();
	
	private JLabel projectStatusLabel = new JLabel("Project Status");
	private JComboBox projectStatusComboBox = new JComboBox();
	
	private JLabel goalStatusLabel = new JLabel("Goal Status");
	private JComboBox goalStatusComboBox = new JComboBox();

	private JLabel folderLabel = new JLabel("Folder");
	private JComboBox folderComboBox = new JComboBox();
	
	private JLabel taskContextLabel = new JLabel("Task Context");
	private JComboBox taskContextComboBox = new JComboBox();
	
	private BasicSwingUI frame;
	

	public FilterDialog(BasicSwingUI frame) {
		super(frame,"Filter",true);
		this.frame = frame;

		// construct the settings panel
		JPanel settingsPanel = new JPanel();
		settingsPanel.setLayout(new GridLayout(0,2));
		settingsPanel.setBorder(new EmptyBorder(5,5,5,5));

		
		goalsCheckBox.setMnemonic(KeyEvent.VK_G);
		goalsCheckBox.addItemListener(this);
		
		projectsCheckBox.setMnemonic(KeyEvent.VK_P);
		projectsCheckBox.addItemListener(this);
		
		tasksCheckBox.setMnemonic(KeyEvent.VK_T);
		tasksCheckBox.addItemListener(this);
		
		refsCheckBox.setMnemonic(KeyEvent.VK_R);
		refsCheckBox.addItemListener(this);
		
		starCheckBox.setMnemonic(KeyEvent.VK_A);
		starCheckBox.addItemListener(this);

		noStarCheckBox.setMnemonic(KeyEvent.VK_N);
		noStarCheckBox.addItemListener(this);
		
		doneCheckBox.setMnemonic(KeyEvent.VK_D);
		doneCheckBox.addItemListener(this);
		
		notDoneCheckBox.setMnemonic(KeyEvent.VK_O);
		notDoneCheckBox.addItemListener(this);
		
		// populate taskStatusComboBox
		taskStatusComboBox.addItem("All");
		TaskStatus[] taskStatuses = TaskStatus.values();
		for (int i=0;i<taskStatuses.length;i++) {
				taskStatusComboBox.addItem(taskStatuses[i].name());
		}	
		taskStatusLabel.setDisplayedMnemonic(KeyEvent.VK_S);
		taskStatusLabel.setDisplayedMnemonicIndex(5);
		taskStatusLabel.setLabelFor(taskStatusComboBox);
		
		// populate projectStatusComboBox
		projectStatusComboBox.addItem("All");
		ProjectStatus[] projectStatuses = ProjectStatus.values();
		for (int i=0;i<projectStatuses.length;i++) {
			projectStatusComboBox.addItem(projectStatuses[i].name());
		}
//		projectStatusLabel.setDisplayedMnemonic(KeyEvent.VK_?);
		projectStatusLabel.setLabelFor(projectStatusComboBox);
		
		// populate goalStatusComboBox
		goalStatusComboBox.addItem("All");
		GoalStatus[] goalStatuses = GoalStatus.values();
		for (int i=0;i<goalStatuses.length;i++) {
			goalStatusComboBox.addItem(goalStatuses[i].name());
		}
//		goalStatusLabel.setDisplayedMnemonic(KeyEvent.VK_?);
		goalStatusLabel.setLabelFor(goalStatusComboBox);

		folderLabel.setDisplayedMnemonic(KeyEvent.VK_F);
		folderLabel.setLabelFor(folderComboBox);
		
		taskContextLabel.setDisplayedMnemonic(KeyEvent.VK_C);
		taskContextLabel.setLabelFor(taskContextComboBox);
		
		settingsPanel.add(goalsCheckBox);
		settingsPanel.add(starCheckBox);
		settingsPanel.add(projectsCheckBox);
		settingsPanel.add(noStarCheckBox);
		settingsPanel.add(tasksCheckBox);
		settingsPanel.add(doneCheckBox);
		settingsPanel.add(refsCheckBox);
		settingsPanel.add(notDoneCheckBox);
		
		settingsPanel.add(folderLabel);
		settingsPanel.add(folderComboBox);

		settingsPanel.add(taskContextLabel);
		settingsPanel.add(taskContextComboBox);
		
		settingsPanel.add(taskStatusLabel);
		settingsPanel.add(taskStatusComboBox);
		
		settingsPanel.add(projectStatusLabel);
		settingsPanel.add(projectStatusComboBox);
		
		settingsPanel.add(goalStatusLabel);
		settingsPanel.add(goalStatusComboBox);
		
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
			
			FilterSettings filterSettings = frame.getFilterSettings();
			
			// read state of checkboxes and update filter settings
			if (goalsCheckBox.getSelectedObjects() != null ) {
				filterSettings.showGoals = true;
			} else {
				filterSettings.showGoals = false;
			}
			
			if (projectsCheckBox.getSelectedObjects() != null ) {
				filterSettings.showProjects = true;
			} else {
				filterSettings.showProjects = false;
			}
			
			if (tasksCheckBox.getSelectedObjects() != null ) {
				filterSettings.showTasks = true;
			} else {
				filterSettings.showTasks = false;
			}
			
			if (refsCheckBox.getSelectedObjects() != null ) {
				filterSettings.showRefs = true;
			} else {
				filterSettings.showRefs = false;
			}
			
			if (starCheckBox.getSelectedObjects() != null ) {
				filterSettings.showStar = true;
			} else {
				filterSettings.showStar = false;
			}
			
			if (noStarCheckBox.getSelectedObjects() != null ) {
				filterSettings.showNoStar = true;
			} else {
				filterSettings.showNoStar = false;
			}
			
			if (doneCheckBox.getSelectedObjects() != null ) {
				filterSettings.showDone = true;
			} else {
				filterSettings.showDone = false;
			}
			
			if (notDoneCheckBox.getSelectedObjects() != null ) {
				filterSettings.showNotDone = true;
			} else {
				filterSettings.showNotDone = false;
			}
			
			// read combo boxes
			filterSettings.taskStatus = (String) taskStatusComboBox.getSelectedItem();
			filterSettings.projectStatus = (String) projectStatusComboBox.getSelectedItem();
			filterSettings.goalStatus = (String) goalStatusComboBox.getSelectedItem();
			filterSettings.folder = (String) folderComboBox.getSelectedItem();
			filterSettings.taskContext = (String) taskContextComboBox.getSelectedItem();
			
			setVisible(false);			
			
		} else if (event.getActionCommand().equalsIgnoreCase("Cancel")) {
			setVisible(false);			
		}
	}
	@Override
	public void setVisible(boolean arg0) {
		// reset the state of the checkboxes
		goalsCheckBox.setSelected(frame.getFilterSettings().showGoals);
		projectsCheckBox.setSelected(frame.getFilterSettings().showProjects);
		tasksCheckBox.setSelected(frame.getFilterSettings().showTasks);
		refsCheckBox.setSelected(frame.getFilterSettings().showRefs);
		starCheckBox.setSelected(frame.getFilterSettings().showStar);
		noStarCheckBox.setSelected(frame.getFilterSettings().showNoStar);
		doneCheckBox.setSelected(frame.getFilterSettings().showDone);
		notDoneCheckBox.setSelected(frame.getFilterSettings().showNotDone);
		
		taskStatusComboBox.setSelectedItem(frame.getFilterSettings().taskStatus);
		projectStatusComboBox.setSelectedItem(frame.getFilterSettings().projectStatus);
		goalStatusComboBox.setSelectedItem(frame.getFilterSettings().goalStatus);
		
		// need to re-populate the folder and task context combo boxes as a new one could have been added
		
		// populate the folder box combo fields and set the state
		folderComboBox.removeAllItems();
		folderComboBox.addItem("All");
		String[] folders = frame.getListManager().getFolders();
		for (int i=0; i<folders.length; i++) {
			folderComboBox.addItem(folders[i]);
		}
		folderComboBox.addItem("None");
		folderComboBox.setSelectedItem(frame.getFilterSettings().folder);
		
		// populate the task context box combo fields and set the state
		taskContextComboBox.removeAllItems();
		taskContextComboBox.addItem("All");
		String[] contexts = frame.getListManager().getTaskContexts();
		for (int i=0; i<contexts.length; i++) {
			taskContextComboBox.addItem(contexts[i]);
		}
		taskContextComboBox.addItem("None");
		taskContextComboBox.setSelectedItem(frame.getFilterSettings().taskContext);
		
		super.setVisible(arg0);
	}
	
}
