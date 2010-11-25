package daveshep.gtd.ui.swing;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Logger;

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
import daveshep.gtd.domain.ListKey;

public class GtdListPanel extends JPanel {

	private static Logger logger = Logger.getLogger("daveshep.gtd");

	// GtdListPanel has...
	// a link to the parent frame
	private BasicSwingUI parent;
	// a label with title and subtitle
	private JLabel titleLabel = new JLabel();
	// a list
	private JList itemList;
	// a status bar?
	private ListCellPanel cellPanel;
	
	
	// a list selection panel
//	private SelectListPanel selectListPanel = new SelectListPanel();
	
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

		// add the list selection panel to the panel
//		selectListPanel.setListKeys(getListManager().getListKeys());
//		selectListPanel.setListKey(gtdList.getKey());
//		add(selectListPanel,BorderLayout.SOUTH);
		
		doKeyBindings();

		refreshList();
	}

	private void doKeyBindings() {
		// Ctrl-N = new item
		itemList.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_N,InputEvent.CTRL_DOWN_MASK),"NewItem");
		itemList.getActionMap().put("NewItem", new NewItemAction(this));
		
		// D = edit item
		itemList.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_D,0),"EditDescription");
		itemList.getActionMap().put("EditDescription", new EditDescriptionAction(this));
		
		// Ctrl-L = select list
		itemList.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_L,InputEvent.CTRL_DOWN_MASK),"SelectList");
		itemList.getActionMap().put("SelectList", new SelectListAction(this));
		
		// Ctrl-F = local find
		itemList.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_F,InputEvent.CTRL_DOWN_MASK),"Local Find");
		itemList.getActionMap().put("Local Find", new FindAction(this,FindType.LOCAL));
		
		// Ctrl-G = global find
		itemList.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_G,InputEvent.CTRL_DOWN_MASK),"Global Find");
		itemList.getActionMap().put("Global Find", new FindAction(this,FindType.GLOBAL));
		
		// Ctrl-Shift-F = local find + add
		itemList.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_F,InputEvent.CTRL_DOWN_MASK|InputEvent.SHIFT_DOWN_MASK),"Local Find+Add");
		itemList.getActionMap().put("Local Find+Add", new FindAction(this,FindType.LOCAL));
		
		// Ctrl-Shift-G = global find + add
		itemList.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_G,InputEvent.CTRL_DOWN_MASK|InputEvent.SHIFT_DOWN_MASK),"Global Find+Add");
		itemList.getActionMap().put("Global Find+Add", new FindAction(this,FindType.GLOBAL));
		
		// F5 = refresh screen
		itemList.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_F5,0),"Refresh");
		itemList.getActionMap().put("Refresh", new RefreshAction(this));
		
		// Ctrl-C = clone
		itemList.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_C,InputEvent.CTRL_DOWN_MASK), "Clone");
		itemList.getActionMap().put("Clone", new CloneAction(this));

		// Ctrl-M = move
		itemList.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_M,InputEvent.CTRL_DOWN_MASK), "Move");
		itemList.getActionMap().put("Move", new MoveAction(this));


		
	}

	public GtdList getGtdList() {
		return gtdList;
	}
	
	public ListKey getListKey() {
		return gtdList.getKey();
	}

	public void refreshList() {
		// refresh the screen according to filter, sort & view settings and find string (if there is one)

		titleLabel.setText(gtdList.getName());
		titleLabel.repaint();

		// clear the list

		DefaultListModel model = (DefaultListModel) itemList.getModel();
		model.removeAllElements();

		// get items for the current view
		for (Iterator<GtdListItem> i=gtdList.iterator();i.hasNext();) {
			GtdListItem item = i.next();
			model.addElement(item);
			
			// for testing purposes only
//			add(new ListCellPanel(item),BorderLayout.SOUTH);
		}

		itemList.repaint();

	}

	public JList getItemList() {
		return itemList;
	}
	
	public GtdListManager getListManager() {
		return parent.getListManager();
	}

	@Override
	public void setVisible(boolean visible) {
		if (visible) {

		}
		super.setVisible(visible);
	}

	public BasicSwingUI getParent() {
		return parent;
	}

	public void displayNewList(ListKey newListKey) {
		
		this.gtdList = parent.getListManager().getList(newListKey);

		refreshList();
		
	}
	
	public void setTitleText(String newTitle) {
		titleLabel.setText(newTitle);
	}

	public String getTitleText() {
		return titleLabel.getText();
	}
}
