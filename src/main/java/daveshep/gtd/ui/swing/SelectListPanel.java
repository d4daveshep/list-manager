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

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import daveshep.gtd.domain.ListKey;

public class SelectListPanel extends JPanel implements ActionListener {
	
	private static Logger logger = Logger.getLogger("daveshep.gtd");
	private Set<ListKey> listKeys;

	private JComboBox titleComboBox = new JComboBox();
	private JComboBox subtitleComboBox = new JComboBox();
	
	private String title;
	private String subtitle;
	
	public SelectListPanel(JFrame parent) {
		
		new AutoCompleteComboBox(titleComboBox);
		titleComboBox.setEditable(true);
		
		new AutoCompleteComboBox(subtitleComboBox);
		subtitleComboBox.setEditable(true);
		
		setLayout(new FlowLayout());
		setBorder(new EmptyBorder(5,5,5,5));
		
		add(new JLabel("Title"));
		add(titleComboBox);
		add(new JLabel("Subtitle"));
		add(subtitleComboBox);
		
		setVisible(true);
		
	}

	public void setListKeys(Set<ListKey> listKeys) {
		this.listKeys = listKeys;
	}

	public Set<ListKey> getListKeys() {
		return listKeys;
	}

	@Override
	public void setVisible(boolean aFlag) {
		// populate the combo fields and set the state
		titleComboBox.removeAllItems();
		subtitleComboBox.removeAllItems();
		
// TODO build map using listkeyutils
		
//		String[] contexts = frame.getListManager().getTaskContexts();
//		for (int i=0; i<contexts.length; i++) {
//			contextComboBox.addItem(contexts[i]);
//		}
//		contextComboBox.setSelectedItem(getContextString());
//		
//		contextComboBox.grabFocus();
//		pack();

		super.setVisible(aFlag);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		logger.info(e.paramString());
		
		// TODO Auto-generated method stub
		
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

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

}
