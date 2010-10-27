package daveshep.gtd.ui.swing;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.TitlePaneLayout;

import daveshep.gtd.domain.ListKey;
import daveshep.gtd.util.ListKeyUtils;

public class SelectListPanel extends JPanel implements ActionListener {
	
	private static Logger logger = Logger.getLogger("daveshep.gtd");
	private Set<ListKey> listKeys;

	private JDialog parent;
	private MyJComboBox titleComboBox = new MyJComboBox();
	private MyJComboBox subtitleComboBox = new MyJComboBox();
//	private JButton goButton = new JButton("Go");
	
	private String title;
	private String subtitle;
	private ListKey listKey;
	private SortedMap<String,Set<String>> keyMap;
	
	public SelectListPanel(JDialog parent) {
		this.parent = parent;
		
		new AutoCompleteComboBox(titleComboBox);
		titleComboBox.setEditable(true);
		titleComboBox.addActionListener(this);
		titleComboBox.getEditor().getEditorComponent().addKeyListener(new ActivateDefaultButtonListener(titleComboBox));
		
		new AutoCompleteComboBox(subtitleComboBox);
		subtitleComboBox.setEditable(true);
		subtitleComboBox.addActionListener(this);
		subtitleComboBox.getEditor().getEditorComponent().addKeyListener(new ActivateDefaultButtonListener(subtitleComboBox));
		
//		goButton.addActionListener(this);
		
		setLayout(new FlowLayout());
		setBorder(new EmptyBorder(5,5,5,5));
		
		add(new JLabel("Title"));
		add(titleComboBox);
		add(new JLabel("Subtitle"));
		add(subtitleComboBox);
//		add(goButton);
		
		
		
		validate();
		
	}

	public void setListKeys(Set<ListKey> listKeys) {
		this.listKeys = listKeys;
		if (listKeys!=null) {
			updateTitleComboBox();
		}
	}

	public Set<ListKey> getListKeys() {
		return listKeys;
	}

	@Override
	public void setVisible(boolean visible) {
		if (visible) {
			updateTitleComboBox();
		}		
		super.setVisible(visible);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		logger.info(e.paramString());
		if (e.getActionCommand().equalsIgnoreCase("comboBoxEdited")) {
			logger.info("*** Enter detected");
		}
		
		if (e.getSource().equals(titleComboBox)) {
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubtitle() {
		return subtitle;
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
		this.listKey = listKey;
		if (listKey!=null) {
			titleComboBox.setSelectedItem(listKey.getTitle());
		}
	}

	public ListKey getNewListKey() {
		return new ListKey(title,subtitle);
	}

	public JDialog getDialog() {
		return parent;
	}
}
