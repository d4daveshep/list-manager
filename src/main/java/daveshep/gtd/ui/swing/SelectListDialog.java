package daveshep.gtd.ui.swing;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedMap;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;

import daveshep.gtd.domain.ListKey;
import daveshep.gtd.util.ListKeyUtils;

public class SelectListDialog extends JDialog implements ActionListener {

	private static Logger logger = Logger.getLogger("daveshep.gtd");

	private Set<ListKey> listKeys;

	private JComboBox titleComboBox = new JComboBox();
	private JComboBox subtitleComboBox = new JComboBox();
	
	private String title;
	private String subtitle;
	private ListKey listKey;
	private SortedMap<String,Set<String>> keyMap;

	private JButton okButton = new JButton("OK");
	private JButton cancelButton = new JButton("Cancel");
	private KeyAction okAction;
	private KeyAction cancelAction;

	private BasicSwingUI parent;

	SelectListDialog(BasicSwingUI parent) {
		super(parent,"Select List",true);
		this.parent = parent;

		// construct the combobox panel
		JPanel comboPanel = new JPanel();
		comboPanel.setLayout(new FlowLayout());
		comboPanel.setBorder(new EmptyBorder(5,5,5,5));
		
//		new AutoCompleteComboBox(titleComboBox);
		titleComboBox.setEditable(true);
		titleComboBox.addActionListener(this);
		titleComboBox.getEditor().getEditorComponent().addKeyListener(new ActivateDefaultButtonListener(titleComboBox));
		
//		new AutoCompleteComboBox(subtitleComboBox);
		subtitleComboBox.setEditable(true);
		subtitleComboBox.addActionListener(this);
		subtitleComboBox.getEditor().getEditorComponent().addKeyListener(new ActivateDefaultButtonListener(subtitleComboBox));
		
		comboPanel.add(new JLabel("Title"));
		comboPanel.add(titleComboBox);
		comboPanel.add(new JLabel("Subtitle"));
		comboPanel.add(subtitleComboBox);

		listKeys = parent.getListManager().getListKeys();
		updateTitleComboBox();

		getContentPane().add(comboPanel,BorderLayout.CENTER);
		
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

		getRootPane().setDefaultButton(okButton);
		
		getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0),"OK");
		getRootPane().getActionMap().put("OK", okAction );

		getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0),"Cancel");
		getRootPane().getActionMap().put("Cancel", cancelAction );

		pack();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		logger.info(e.paramString());
		
		if (e.getSource().equals(okButton)||e.getActionCommand().equalsIgnoreCase("OK")) {
			title = (String) titleComboBox.getSelectedItem();
			subtitle = (String) subtitleComboBox.getSelectedItem();
			if (subtitle==null) {
				subtitle = "";
			}
			setListKey(getNewListKey());
			setVisible(false);			
			
		} else if (e.getSource().equals(cancelButton)||e.getActionCommand().equalsIgnoreCase("Cancel")) {
			logger.info("closing");
			this.title = this.listKey.getTitle();
			this.subtitle = this.listKey.getSubtitle();
			setVisible(false);
			
		} else if (e.getSource().equals(titleComboBox)) {
			logger.info("titleComboBox changed to "+titleComboBox.getSelectedItem().toString());
			title = (String) titleComboBox.getSelectedItem();
			
			//populate subtitle combobox
			updateSubtitleComboBox();
			
		} else if (e.getSource().equals(subtitleComboBox)) {
			String selectedItem = (String) subtitleComboBox.getSelectedItem();
			if (selectedItem!=null) {
				logger.info("subtitleComboBox changed to "+selectedItem);
				subtitle = selectedItem;
			}
			
//		} else if (e.getSource().equals(goButton)) {
//			logger.info("Go button pushed with title="+title+" and subtitle="+subtitle);
		}
		

	}

	private void updateTitleComboBox() {
		// populate the combo fields and set the state
		titleComboBox.removeAllItems();
		subtitleComboBox.removeAllItems();

		// get a map of list titles and subtitles
		keyMap = ListKeyUtils.buildSortedMap(listKeys);

		Set<String> titles = keyMap.keySet();
		for (Iterator<String> i=titles.iterator();i.hasNext();) {
			titleComboBox.addItem(i.next());
		}
		
//		if (listKey!=null) {
//			titleComboBox.setSelectedItem(listKey.getTitle());
//		}
		
	}

	private void updateSubtitleComboBox() {
		// populate the combo fields and set the state
		subtitleComboBox.removeAllItems();

		// get a map of list titles and subtitles
		if (keyMap==null) {
			keyMap = ListKeyUtils.buildSortedMap(listKeys);
		}

		Set<String> subtitles = keyMap.get(titleComboBox.getSelectedItem());
		if (subtitles!=null && subtitles.size()>0) {
			for (Iterator<String> i=subtitles.iterator();i.hasNext();) {
				subtitleComboBox.addItem(i.next());
			}
		}
		
	}

	public void setListKey(ListKey listKey) {
		if (listKey!=null) {
			this.listKey = listKey;
			this.title = listKey.getTitle();
			this.subtitle = listKey.getSubtitle();
			titleComboBox.setSelectedItem(listKey.getTitle());
		}
	}

	public ListKey getNewListKey() {
		return new ListKey(title,subtitle);
	}

	public ListKey getListKey() {
		return listKey;
	}

//	@Override
//	public void setVisible(boolean visible) {
//		if (visible) {
//			updateTitleComboBox();
//		}		
//		super.setVisible(visible);
//	}


}
