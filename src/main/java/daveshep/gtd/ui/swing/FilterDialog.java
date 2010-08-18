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
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;

import daveshep.gtd.FilterSettings;

public class FilterDialog extends JDialog implements ItemListener, ActionListener {

	private JButton okButton = new JButton("OK");
	private JButton cancelButton = new JButton("Cancel");
	private JCheckBox goalsCheckBox = new JCheckBox("Goals");
	private JCheckBox projectsCheckBox = new JCheckBox("Projects");
	private JCheckBox tasksCheckBox = new JCheckBox("Tasks");
	private JCheckBox refsCheckBox = new JCheckBox("Ref. Items");
	private BasicSwingUI frame;
	

	public FilterDialog(BasicSwingUI frame) {
		super(frame,"Filter",true);
		this.frame = frame;

		// construct the settings panel
		JPanel settingsPanel = new JPanel();
		settingsPanel.setLayout(new GridLayout(4,2));
		settingsPanel.setBorder(new EmptyBorder(5,5,5,5));

		
		goalsCheckBox.setMnemonic(KeyEvent.VK_G);
		goalsCheckBox.addItemListener(this);
		
		projectsCheckBox.setMnemonic(KeyEvent.VK_P);
		projectsCheckBox.addItemListener(this);
		
		tasksCheckBox.setMnemonic(KeyEvent.VK_T);
		tasksCheckBox.addItemListener(this);
		
		refsCheckBox.setMnemonic(KeyEvent.VK_R);
		refsCheckBox.addItemListener(this);
		
		settingsPanel.add(goalsCheckBox);
		settingsPanel.add(projectsCheckBox);
		settingsPanel.add(tasksCheckBox);
		settingsPanel.add(refsCheckBox);
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
			
			System.out.print("ok... ");
			setVisible(false);			
			
			// refresh the main list
			System.out.println("doing refresh now...");
			frame.getItemList().dispatchEvent(new KeyEvent(this,12345,System.currentTimeMillis(),0,KeyEvent.VK_F5,KeyEvent.CHAR_UNDEFINED));
//			frame.getItemList().dispatchEvent(new ActionEvent(this,KeyEvent.VK_F5,"Refresh"));
			
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
		super.setVisible(arg0);
	}
	
}
