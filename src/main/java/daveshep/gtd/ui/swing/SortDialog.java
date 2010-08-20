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
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;

import daveshep.gtd.FilterSettings;
import daveshep.gtd.domain.GoalStatus;
import daveshep.gtd.domain.ProjectStatus;
import daveshep.gtd.domain.TaskStatus;

public class SortDialog extends JDialog implements ItemListener, ActionListener {

	private JRadioButton folderSortButton = new JRadioButton("Folder");
	private JRadioButton dueDateSortButton = new JRadioButton("Due Date");
	private JRadioButton descriptionSortButton = new JRadioButton("Description");
	private JRadioButton taskContextSortButton = new JRadioButton("Task Context");
	private JRadioButton taskStatusSortButton = new JRadioButton("Task Status");
	private JRadioButton projectStatusSortButton = new JRadioButton("Project Status");
	private JRadioButton goalStatusSortButton = new JRadioButton("Goal Status");

	private JButton okButton = new JButton("OK");
	private JButton cancelButton = new JButton("Cancel");
	private KeyAction okAction;
	private KeyAction cancelAction;
	
	private BasicSwingUI frame;
	

	public SortDialog(BasicSwingUI frame) {
		super(frame,"Sort",true);
		this.frame = frame;

		// construct the settings panel
		JPanel settingsPanel = new JPanel();
		settingsPanel.setLayout(new GridLayout(4,4));
		settingsPanel.setBorder(new EmptyBorder(5,5,5,5));

		folderSortButton.setMnemonic(KeyEvent.VK_F);
		folderSortButton.addActionListener(this);
		
		dueDateSortButton.setMnemonic(KeyEvent.VK_D);
		dueDateSortButton.addActionListener(this);
		
		descriptionSortButton.setMnemonic(KeyEvent.VK_E);
		descriptionSortButton.addActionListener(this);
		
		taskContextSortButton.setMnemonic(KeyEvent.VK_C);
		taskContextSortButton.addActionListener(this);
		
		taskStatusSortButton.setMnemonic(KeyEvent.VK_S);
		taskStatusSortButton.addActionListener(this);
		
		projectStatusSortButton.setMnemonic(KeyEvent.VK_P);
		projectStatusSortButton.addActionListener(this);
		
		goalStatusSortButton.setMnemonic(KeyEvent.VK_G);
		goalStatusSortButton.addActionListener(this);
		
		ButtonGroup sortButtonGroup = new ButtonGroup();
		sortButtonGroup.add(folderSortButton);
		sortButtonGroup.add(dueDateSortButton);
		sortButtonGroup.add(descriptionSortButton);
		sortButtonGroup.add(taskContextSortButton);
		sortButtonGroup.add(taskStatusSortButton);
		sortButtonGroup.add(projectStatusSortButton);
		sortButtonGroup.add(goalStatusSortButton);
		
		settingsPanel.add(folderSortButton);
		settingsPanel.add(dueDateSortButton);
		settingsPanel.add(descriptionSortButton);
		settingsPanel.add(taskContextSortButton);
		settingsPanel.add(taskStatusSortButton);
		settingsPanel.add(projectStatusSortButton);
		settingsPanel.add(goalStatusSortButton);
		
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

			// do some stuff
			setVisible(false);			
			
		} else if (event.getActionCommand().equalsIgnoreCase("Cancel")) {
			setVisible(false);			
			
		} else if (event.getActionCommand().equalsIgnoreCase("Folder")) {
			frame.setSorter(frame.FOLDER_SORTER);
			
		} else if (event.getActionCommand().equalsIgnoreCase("Due Date")) {
			frame.setSorter(frame.DUE_DATE_SORTER);
			
		} else if (event.getActionCommand().equalsIgnoreCase("Description")) {
			frame.setSorter(frame.DESCRIPTION_SORTER);
			
		} else if (event.getActionCommand().equalsIgnoreCase("Task Context")) {
			frame.setSorter(frame.TASK_CONTEXT_SORTER);
			
		} else if (event.getActionCommand().equalsIgnoreCase("Task Status")) {
			frame.setSorter(frame.TASK_STATUS_SORTER);
			
		} else if (event.getActionCommand().equalsIgnoreCase("Project Status")) {
			frame.setSorter(frame.PROJECT_STATUS_SORTER);
			
		} else if (event.getActionCommand().equalsIgnoreCase("Goal Status")) {
			frame.setSorter(frame.GOAL_STATUS_SORTER);
			
		}
	}
	@Override
	public void setVisible(boolean arg0) {
		
		super.setVisible(arg0);
	}
	
}
