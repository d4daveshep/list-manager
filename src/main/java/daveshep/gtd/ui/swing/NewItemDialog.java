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
import javax.swing.JLabel;
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

public class NewItemDialog extends JDialog implements ItemListener, ActionListener {

	private JButton cancelButton = new JButton("Cancel");
	private KeyAction cancelAction;

	private JRadioButton goalRadioButton = new JRadioButton("Goal");
	private JRadioButton projectRadioButton = new JRadioButton("Project");
	private JRadioButton taskRadioButton = new JRadioButton("Task");
	private JRadioButton refRadioButton = new JRadioButton("Reference");
	
	private BasicSwingUI frame;
	
	public NewItemDialog(BasicSwingUI frame) {
		super(frame,"New",true);
		this.frame = frame;
		
		// construct the settings panel
		JPanel settingsPanel = new JPanel();
		settingsPanel.setLayout(new GridLayout(0,2));
		settingsPanel.setBorder(new EmptyBorder(5,5,5,5));
		
		goalRadioButton.setMnemonic(KeyEvent.VK_G);
		goalRadioButton.addActionListener(this);
		
		projectRadioButton.setMnemonic(KeyEvent.VK_P);
		projectRadioButton.addActionListener(this);
		
		taskRadioButton.setMnemonic(KeyEvent.VK_T);
		taskRadioButton.addActionListener(this);
		
		refRadioButton.setMnemonic(KeyEvent.VK_R);
		refRadioButton.addActionListener(this);
		
		ButtonGroup newItemButtonGroup = new ButtonGroup();
		newItemButtonGroup.add(goalRadioButton);
		newItemButtonGroup.add(projectRadioButton);
		newItemButtonGroup.add(taskRadioButton);
		newItemButtonGroup.add(refRadioButton);
		
		settingsPanel.add(goalRadioButton);
		settingsPanel.add(projectRadioButton);
		settingsPanel.add(taskRadioButton);
		settingsPanel.add(refRadioButton);

		getContentPane().add(settingsPanel,BorderLayout.CENTER);

		// construct the button panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		
		cancelButton.setMnemonic(KeyEvent.VK_ESCAPE);
		cancelButton.addActionListener(this);
		cancelAction = new KeyAction(this, "Cancel");
		
		getContentPane().add(buttonPanel,BorderLayout.SOUTH);

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
		
		if (event.getActionCommand().equalsIgnoreCase("Reference")) {

			setVisible(false);			
			
			NewReferenceDialog newRefDialog = new NewReferenceDialog(this.frame);
			newRefDialog.setVisible(true);
			
		} else if (event.getActionCommand().equalsIgnoreCase("Cancel")) {
			setVisible(false);			
		}
	}
	
}
