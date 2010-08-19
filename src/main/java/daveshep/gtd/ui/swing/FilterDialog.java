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
	private JCheckBox goalsCheckBox = new JCheckBox("Goals");
	private JCheckBox projectsCheckBox = new JCheckBox("Projects");
	private JCheckBox tasksCheckBox = new JCheckBox("Tasks");
	private JCheckBox refsCheckBox = new JCheckBox("Ref. Items");
	private JCheckBox starCheckBox = new JCheckBox("Star");
	private JCheckBox noStarCheckBox = new JCheckBox("No Star");
	private JCheckBox doneCheckBox = new JCheckBox("Done");
	private JCheckBox notDoneCheckBox = new JCheckBox("Not Done");
	
	private JComboBox taskStatusComboBox = new JComboBox();
	private JComboBox projectStatusComboBox = new JComboBox();
	private JComboBox goalStatusComboBox = new JComboBox();

	private JComboBox folderComboBox = new JComboBox();
	private JComboBox taskContextComboBox = new JComboBox();
	
	private BasicSwingUI frame;
	

	public FilterDialog(BasicSwingUI frame) {
		super(frame,"Filter",true);
		this.frame = frame;

		// construct the settings panel
		JPanel settingsPanel = new JPanel();
		settingsPanel.setLayout(new GridLayout(4,4));
		settingsPanel.setBorder(new EmptyBorder(5,5,5,5));

		
		goalsCheckBox.setMnemonic(KeyEvent.VK_G);
		goalsCheckBox.addItemListener(this);
		
		projectsCheckBox.setMnemonic(KeyEvent.VK_P);
		projectsCheckBox.addItemListener(this);
		
		tasksCheckBox.setMnemonic(KeyEvent.VK_T);
		tasksCheckBox.addItemListener(this);
		
		refsCheckBox.setMnemonic(KeyEvent.VK_R);
		refsCheckBox.addItemListener(this);
		
		starCheckBox.setMnemonic(KeyEvent.VK_S);
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
		
		// populate projectStatusComboBox
		projectStatusComboBox.addItem("All");
		ProjectStatus[] projectStatuses = ProjectStatus.values();
		for (int i=0;i<projectStatuses.length;i++) {
			projectStatusComboBox.addItem(projectStatuses[i].name());
		}
		
		// populate goalStatusComboBox
		goalStatusComboBox.addItem("All");
		GoalStatus[] goalStatuses = GoalStatus.values();
		for (int i=0;i<goalStatuses.length;i++) {
			goalStatusComboBox.addItem(goalStatuses[i].name());
		}
		
		settingsPanel.add(goalsCheckBox);
		settingsPanel.add(starCheckBox);
		settingsPanel.add(projectsCheckBox);
		settingsPanel.add(noStarCheckBox);
		settingsPanel.add(tasksCheckBox);
		settingsPanel.add(doneCheckBox);
		settingsPanel.add(refsCheckBox);
		settingsPanel.add(notDoneCheckBox);
		
		settingsPanel.add(taskStatusComboBox);
		settingsPanel.add(projectStatusComboBox);
		settingsPanel.add(goalStatusComboBox);
		
		settingsPanel.add(folderComboBox);
		settingsPanel.add(taskContextComboBox);
		
		getContentPane().add(settingsPanel,BorderLayout.CENTER);

		// construct the button panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		
		okButton.setMnemonic(KeyEvent.VK_ENTER);
		okButton.addActionListener(this);
		
		cancelButton.setMnemonic(KeyEvent.VK_ESCAPE);
		cancelButton.addActionListener(this);
		
		buttonPanel.add(okButton);
		buttonPanel.add(cancelButton);
		getContentPane().add(buttonPanel,BorderLayout.SOUTH);

//		FilterDialogAction cancelDialogAction = new FilterDialogAction(frame, "Cancel");
//		FilterDialogAction okDialogAction = new FilterDialogAction(frame, "OK");
//		
//        okButton.addActionListener(okDialogAction);
//        cancelButton.addActionListener(cancelDialogAction);
//        
//		getRootPane().getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0),"Cancel");
//		getRootPane().getActionMap().put("Cancel", cancelDialogAction);
//
//		getRootPane().getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0),"OK");
//		getRootPane().getActionMap().put("OK", okDialogAction);

		
		
		pack();
	}
/*
	class FilterDialogAction extends AbstractAction {
		private JFrame frame;
		private String command;
		
		FilterDialogAction(JFrame frame, String command) {
			super();
			this.frame = frame;
			this.command = command;
		}
		public void actionPerformed(ActionEvent event) {

			String actionCommand = event.getActionCommand();
			if (command.equals("Cancel") || (actionCommand!= null && actionCommand.equals("Cancel"))) {
				System.out.print("cancel... ");
				setVisible(false);
			}
			if (command.equals("OK") || (actionCommand!= null && actionCommand.equals("OK"))) {
				System.out.print("ok... ");
				setVisible(false);
			}
		}
	}
*/
	@Override
	public void itemStateChanged(ItemEvent event) {
		// don't need to do anything here
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();
		if (source == okButton) {
			
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
			
			System.out.print("ok... ");
			setVisible(false);			
			
			
		} else if (source == cancelButton) {
			System.out.print("cancel... ");
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