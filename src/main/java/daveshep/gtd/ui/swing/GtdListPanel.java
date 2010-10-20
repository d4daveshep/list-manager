package daveshep.gtd.ui.swing;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.ListModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import daveshep.gtd.GtdListManager;
import daveshep.gtd.domain.GtdList;
import daveshep.gtd.domain.GtdListItem;

public class GtdListPanel extends JPanel {

	// GtdListPanel has...
	// a link to the parent frame
	private BasicSwingUI parent;
	// a label with title and subtitle
	private JLabel titleLabel = new JLabel();
	// a list
	private JList itemList;
	// a status bar?
	
	// a GtdList that it's displaying
	private GtdList gtdList;
	
	GtdListPanel(BasicSwingUI parent, GtdList gtdList) {
		super();
		this.parent = parent;
		this.gtdList = gtdList;
		itemList = new JList();
		itemList.setModel(new DefaultListModel());
		
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(5,5,5,5));

		// add the title to the panel
		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 32));
		titleLabel.setText(gtdList.getName());
		add(titleLabel,BorderLayout.NORTH);
		
		// add the list to the panel
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.getViewport().add(itemList);
		add(scrollPane,BorderLayout.CENTER);

		doKeyBindings();

		refreshList();
	}

	private void doKeyBindings() {
		// Ctrl-N = new item
		itemList.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_N,InputEvent.CTRL_DOWN_MASK),"NewItem");
		itemList.getActionMap().put("NewItem", new NewItemAction(this));
		
		
	}

	public GtdList getGtdList() {
		return gtdList;
	}

	public void refreshList() {
		// refresh the screen according to filter, sort & view settings and find string (if there is one)


		// clear the list

		DefaultListModel model = (DefaultListModel) itemList.getModel();
		model.removeAllElements();

		// get items for the current view
		for (Iterator<GtdListItem> i=getGtdList().iterator();i.hasNext();) {
			GtdListItem item = i.next();
			model.addElement(item);
		}


	}

	public JList getItemList() {
		return itemList;
	}
	
	public GtdListManager getListManager() {
		return parent.getListManager();
	}
	
}
