package daveshep.gtd.ui.swing;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.DefaultListModel;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import daveshep.gtd.FilterSettings;
import daveshep.gtd.ListManager;
import daveshep.gtd.ViewSettings;
import daveshep.gtd.domain.DefaultSorter;
import daveshep.gtd.domain.DescriptionSorter;
import daveshep.gtd.domain.DueDateSorter;
import daveshep.gtd.domain.FolderSorter;
import daveshep.gtd.domain.GoalStatusSorter;
import daveshep.gtd.domain.InMemoryListManager;
import daveshep.gtd.domain.ListItem;
import daveshep.gtd.domain.ProjectStatusSorter;
import daveshep.gtd.domain.TaskContextSorter;
import daveshep.gtd.domain.TaskStatusSorter;
import daveshep.gtd.util.ToodledoXMLImporter;



public class BasicSwingUI extends JFrame implements KeyListener {

	private JList itemList; // the main list on the screen
	private JLabel statusBar; // the status bar
	private FilterSettings filterSettings = new FilterSettings();
	private FilterDialog filterDialog = new FilterDialog(this);
	private NewItemDialog newItemDialog = new NewItemDialog(this);
	private StatusDialog statusDialog = new StatusDialog(this);
	private SortDialog sortDialog = new SortDialog(this);
	private String findString = ""; // match all on start (set to null to start with empty list)
	private Comparator<ListItem> sorter = new DefaultSorter();
	private ViewSettings viewSettings = new ViewSettings();
	private ViewDialog viewDialog = new ViewDialog(this);
	
	public static Comparator<ListItem> FOLDER_SORTER = new FolderSorter();
	public static Comparator<ListItem> DUE_DATE_SORTER = new DueDateSorter();
	public static Comparator<ListItem> DESCRIPTION_SORTER = new DescriptionSorter();
	public static Comparator<ListItem> TASK_CONTEXT_SORTER = new TaskContextSorter();
	public static Comparator<ListItem> TASK_STATUS_SORTER = new TaskStatusSorter();
	public static Comparator<ListItem> PROJECT_STATUS_SORTER = new ProjectStatusSorter();
	public static Comparator<ListItem> GOAL_STATUS_SORTER = new GoalStatusSorter();
	
	private ListManager listManager = InMemoryListManager.getInstance(); // remote data model

	
	public BasicSwingUI() {
		super("GTD List Manager - Basic Swing UI");
		setSize(1000, 700);

		// load GTD data into data model
		loadTestData();		
		
		// create the main list panel
		itemList = new JList();
		itemList.setModel(new DefaultListModel());
		ListItemCellRenderer renderer = new ListItemCellRenderer();
		renderer.setFrame(this);
		itemList.setCellRenderer(renderer);

		// create the status bar
		statusBar = new JLabel("Ready...");
		
		// add the list to the frame
		JScrollPane ps = new JScrollPane();
		ps.getViewport().add(itemList);
		
		getContentPane().add(ps, BorderLayout.CENTER);
		getContentPane().add(statusBar, BorderLayout.SOUTH);
		
		// key bindings
		// Ctrl-F = find
		itemList.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_F,InputEvent.CTRL_DOWN_MASK),"Find");
		itemList.getActionMap().put("Find", new FindAction(this));
		
		// Ctrl-Alt-F = find + add
		itemList.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_F,InputEvent.CTRL_DOWN_MASK|InputEvent.ALT_DOWN_MASK),"Find+Add");
		itemList.getActionMap().put("Find+Add", new FindAction(this));
		
		// Ctrl-B = blank screen
		itemList.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_B,InputEvent.CTRL_DOWN_MASK),"Blank");
		itemList.getActionMap().put("Blank", new BlankAction(this));
		
		// F2, D = edit description
		itemList.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_F2,0),"Edit_Description");
		itemList.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_D,0),"Edit_Description");
		itemList.getActionMap().put("Edit_Description", new EditDescriptionAction(this));
		
		// S = edit status
		itemList.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_S,0),"Edit_Status");
		itemList.getActionMap().put("Edit_Status", new EditStatusAction(this));
		
		// Ctrl-N = new item
		itemList.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_N,InputEvent.CTRL_DOWN_MASK),"NewItem");
		itemList.getActionMap().put("NewItem", new NewItemAction(this));
		
		// * = toggle star, Ctrl-* = star on, Alt-* = star off
		itemList.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_8,InputEvent.SHIFT_DOWN_MASK),"Toggle_Star");
		itemList.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_8,InputEvent.SHIFT_DOWN_MASK|InputEvent.CTRL_DOWN_MASK),"Toggle_Star");
		itemList.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_8,InputEvent.SHIFT_DOWN_MASK|InputEvent.ALT_DOWN_MASK),"Toggle_Star");
		itemList.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ASTERISK,0),"Toggle_Star");
		itemList.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ASTERISK,InputEvent.CTRL_DOWN_MASK),"Toggle_Star");
		itemList.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ASTERISK,InputEvent.ALT_DOWN_MASK),"Toggle_Star");
		itemList.getActionMap().put("Toggle_Star", new ToggleStarAction(this));
		
		// Ctrl-D, Del = delete
		itemList.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE,0),"Delete");
		itemList.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_D,InputEvent.CTRL_DOWN_MASK),"Delete");
		itemList.getActionMap().put("Delete", new DeleteAction(this));
		
		// F1, Ctrl-H = help
		itemList.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_H,InputEvent.CTRL_DOWN_MASK),"Help");
		itemList.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_F1,0),"Help");
		itemList.getActionMap().put("Help", new HelpAction(this));
		
		// Ctrl-L = filter
		itemList.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_L,InputEvent.CTRL_DOWN_MASK),"Filter");
		itemList.getActionMap().put("Filter", new FilterAction(this));
		
		// Ctrl-T = sort
		itemList.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_T,InputEvent.CTRL_DOWN_MASK),"Sort");
		itemList.getActionMap().put("Sort", new SortAction(this));
		
		// F5 = refresh screen
		itemList.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_F5,0),"Refresh");
		itemList.getActionMap().put("Refresh", new RefreshAction(this));

		// Ctrl-W = view settings
		itemList.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_W,InputEvent.CTRL_DOWN_MASK), "View");
		itemList.getActionMap().put("View", new ViewAction(this));
		
		// Ctrl-+ = increase font size
		itemList.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS,InputEvent.CTRL_DOWN_MASK), "FontSize");
		itemList.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS,InputEvent.CTRL_DOWN_MASK), "FontSize");
		itemList.getActionMap().put("FontSize", new FontSizeAction(this));
		
		refreshList();
		
		// create the command panel
//		CommandPanel commandPanel = new CommandPanel(this);
//		getContentPane().add(commandPanel, BorderLayout.PAGE_END);

//		pack();
		
		itemList.addKeyListener(this);
	}

	public static void main(String argv[]) {
		BasicSwingUI frame = new BasicSwingUI();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}
	
	private void loadTestData() {

    	File xmlFile = new File("src/test/resources/data/toodledo.20100608.xml");
    	System.out.println(xmlFile.getAbsolutePath());
    	
    	try {
    		ToodledoXMLImporter importer = new ToodledoXMLImporter(listManager,xmlFile);
    		importer.doImport();
    	} catch (Exception e) {
    		e.printStackTrace(System.out);
    	}

	}

	public JList getItemList() {
		return itemList;
	}

	public ListManager getListManager() {
		return listManager;
	}

	public JLabel getStatusBar() {
		return statusBar;
	}

	public FilterSettings getFilterSettings() {
		return filterSettings;
	}

	public FilterDialog getFilterDialog() {
		return filterDialog;
	}

	public String getFindString() {
		return findString;
	}

	public void setFindString(String findString) {
		this.findString = findString;
	}

	public void refreshList() {
		// refresh the screen according to filter, sort & view settings and find string (if there is one)
		
		// clear the list
		DefaultListModel model = (DefaultListModel)getItemList().getModel();
		model.removeAllElements();

		// get items for the current view
		// find in GTD model
		Collection<ListItem> foundItems = getListManager().findItemsByString(getFindString(),getFilterSettings(),sorter,viewSettings);
		System.out.println("found: "+ foundItems.size());
		
		for (Iterator<ListItem> i=foundItems.iterator();i.hasNext();) {
			ListItem item = i.next();
			model.addElement(item);
		}
		
		// update the status bar
		getStatusBar().setText("Refresh... \"" + getFindString() + "\" " + foundItems.size() + " found\t\tFilters: " + getFilterSettings().toString());

		
	}

	public Comparator getSorter() {
		return sorter;
	}

	public SortDialog getSortDialog() {
		return sortDialog;
	}

	public void setSorter(Comparator sorter) {
		this.sorter = sorter;
	}

	public ViewSettings getViewSettings() {
		return viewSettings;
	}

	public void setViewSettings(ViewSettings viewSettings) {
		this.viewSettings = viewSettings;
	}

	public ViewDialog getViewDialog() {
		return viewDialog;
	}

	public NewItemDialog getNewItemDialog() {
		return newItemDialog;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		System.out.println(e.paramString());
		// TODO Auto-generated method stub
		
	}

	public StatusDialog getStatusDialog() {
		return statusDialog;
	}
	
}

