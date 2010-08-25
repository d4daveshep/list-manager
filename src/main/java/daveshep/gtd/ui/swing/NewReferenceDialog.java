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

public class NewReferenceDialog extends JDialog implements ItemListener, ActionListener {

	private JButton okButton = new JButton("OK");
	private JButton cancelButton = new JButton("Cancel");
	private KeyAction okAction;
	private KeyAction cancelAction;

	private JTextField descriptionTextField = new JTextField(30);
	private JComboBox folderComboBox = new JComboBox();
	private JTextField dueDateTextField = new JTextField(10);
	private JTextField tagsTextField = new JTextField(20);
	private JTextArea notesTextArea = new JTextArea(3,30);


	private BasicSwingUI frame;
	
	public NewReferenceDialog(BasicSwingUI frame) {
		super(frame,"New Reference",true);
		this.frame = frame;
		
		// construct the settings panel
		JPanel descriptionPanel = new JPanel();
		JLabel descriptionLabel = new JLabel("Description");
		descriptionLabel.setDisplayedMnemonic(KeyEvent.VK_D);
		descriptionLabel.setLabelFor(descriptionTextField);
		descriptionPanel.add(descriptionLabel);
		descriptionPanel.add(descriptionTextField);

		JPanel folderPanel = new JPanel();
		JLabel folderLabel = new JLabel("Folder");
		folderLabel.setDisplayedMnemonic(KeyEvent.VK_F);
		folderLabel.setLabelFor(folderComboBox);
		folderPanel.add(folderLabel);
		folderPanel.add(folderComboBox);

		JPanel dueDatePanel = new JPanel();
		JLabel dueDateLabel = new JLabel("Due Date");
		dueDateLabel.setDisplayedMnemonic(KeyEvent.VK_D);
		dueDateLabel.setLabelFor(dueDateTextField);
		dueDatePanel.add(dueDateLabel);
		dueDatePanel.add(dueDateTextField);

		JPanel tagsPanel = new JPanel();
		JLabel tagsLabel = new JLabel("Tags");
		tagsLabel.setDisplayedMnemonic(KeyEvent.VK_T);
		tagsLabel.setLabelFor(tagsTextField);
		tagsPanel.add(tagsLabel);
		tagsPanel.add(tagsTextField);

		JPanel notesPanel = new JPanel();
		JLabel notesLabel = new JLabel("Notes");
		notesLabel.setDisplayedMnemonic(KeyEvent.VK_T);
		notesLabel.setLabelFor(notesTextArea);
		notesPanel.add(notesLabel);
		notesPanel.add(notesTextArea);

		JPanel settingsPanel = new JPanel();
		settingsPanel.setLayout(new GridLayout(0,1));
		settingsPanel.add(descriptionPanel);
		settingsPanel.add(folderPanel);
		settingsPanel.add(dueDatePanel);
		settingsPanel.add(tagsPanel);
		settingsPanel.add(notesPanel);
		
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

			setVisible(false);			
			
		} else if (event.getActionCommand().equalsIgnoreCase("Cancel")) {
			setVisible(false);			
		}
	}

	@Override
	public void setVisible(boolean b) {

		// populate the folder box combo fields and set the state
		folderComboBox.removeAllItems();
		String[] folders = frame.getListManager().getFolders();
		for (int i=0; i<folders.length; i++) {
			folderComboBox.addItem(folders[i]);
		}
		folderComboBox.setSelectedItem(frame.getFilterSettings().folder);

		super.setVisible(b);
	}
	
}
