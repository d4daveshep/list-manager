package daveshep.gtd.ui.swing;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;

import daveshep.gtd.domain.ListItem;
import daveshep.gtd.domain.ListItemType;

public class NewItemDialog extends JDialog implements ItemListener, ActionListener {

	private JButton cancelButton = new JButton("Cancel");
	private KeyAction cancelAction;

	private JRadioButton goalRadioButton = new JRadioButton("Goal");
	private JRadioButton projectRadioButton = new JRadioButton("Project");
	private JRadioButton taskRadioButton = new JRadioButton("Task");
	private JRadioButton refRadioButton = new JRadioButton("Reference");
	
	private ListItemType newItemType = null;
	private String newDescription = null;
	private ListItemType parentItemType = null;
	
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
		
//		cancelButton.setMnemonic(KeyEvent.VK_ESCAPE);
//		cancelButton.addActionListener(this);
//		cancelAction = new KeyAction(this, "Cancel");
		
		getContentPane().add(buttonPanel,BorderLayout.SOUTH);

		getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0),"Cancel");
		getRootPane().getActionMap().put("Cancel", new KeyAction(this,"Cancel") );

		getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_P,0),"Project");
		getRootPane().getActionMap().put("Project", new KeyAction(this,"Project") );

		getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_T,0),"Task");
		getRootPane().getActionMap().put("Task", new KeyAction(this,"Task") );

		getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_G,0),"Goal");
		getRootPane().getActionMap().put("Goal", new KeyAction(this,"Goal") );

		getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_R,0),"Reference");
		getRootPane().getActionMap().put("Reference", new KeyAction(this,"Reference") );


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
		
		newItemType = null;

		if (event.getActionCommand().equalsIgnoreCase("Cancel")) {
			setVisible(false);
		} else if (event.getActionCommand().equalsIgnoreCase("Reference")) {
			newItemType = ListItemType.REFERENCE;
		} else if (event.getActionCommand().equalsIgnoreCase("Task")) {
			newItemType = ListItemType.TASK;
		} else if (event.getActionCommand().equalsIgnoreCase("Project")) {
			newItemType = ListItemType.PROJECT;
		} else if (event.getActionCommand().equalsIgnoreCase("Goal")) {
			newItemType = ListItemType.GOAL;
		}
		
		if (newItemType != null) {

			setVisible(false);
			
			newDescription = (String) JOptionPane.showInputDialog(frame,null,"Description",JOptionPane.QUESTION_MESSAGE,null,null,"");
			
		} 
	}

	public ListItemType getNewItemType() {
		return newItemType;
	}

	public String getNewDescription() {
		return newDescription;
	}

	public void setParentItemType(ListItemType parentItemType) {
		this.parentItemType = parentItemType;
	}

	@Override
	public void setVisible(boolean b) {
		
		goalRadioButton.setEnabled(true);
		projectRadioButton.setEnabled(true);
		taskRadioButton.setEnabled(true);
		refRadioButton.setEnabled(true);
		
		if (b && parentItemType!=null) {

			switch (parentItemType) {
			case GOAL:
			case REFERENCE:
				// can't add goals, projects or tasks to goals
				goalRadioButton.setEnabled(false);
				projectRadioButton.setEnabled(false);
				taskRadioButton.setEnabled(false);
				break;
				
			case PROJECT:
				goalRadioButton.setEnabled(false);
				break;
				
			case TASK:
				goalRadioButton.setEnabled(false);
				projectRadioButton.setEnabled(false);
			}
			
		}
		
		super.setVisible(b);
	}
	
}
