package daveshep.gtd.ui.swing;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
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

import daveshep.gtd.ListManager;
import daveshep.gtd.domain.InMemoryListManager;
import daveshep.gtd.util.ToodledoXMLImporter;



public class BasicSwingUI extends JFrame {

	JList itemList;

	
	private ListManager manager = InMemoryListManager.getInstance();

	public BasicSwingUI() {
		super("GTD List Manager - Basic Swing UI");
		setSize(800, 600);

		// load GTD data
		loadTestData();		
		
		// create the main list panel
		itemList = new JList(new String[] {"item 1","item 2","item 3"});

		// add the list to the frame
		JScrollPane ps = new JScrollPane();
		ps.getViewport().add(itemList);
		getContentPane().add(ps, BorderLayout.CENTER);
		
		// key bindings
		// Ctrl-F = find
		itemList.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_F,InputEvent.CTRL_DOWN_MASK),"Find");
		itemList.getActionMap().put("Find", new FindAction());		
		
		// create the command panel
//		CommandPanel commandPanel = new CommandPanel(this);
//		getContentPane().add(commandPanel, BorderLayout.PAGE_END);

//		pack();
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
    		ToodledoXMLImporter importer = new ToodledoXMLImporter(manager,xmlFile);
    		importer.doImport();
    	} catch (Exception e) {
    		e.printStackTrace(System.out);
    	}

	}
}

// move this to a separate class file
class FindAction extends AbstractAction {

	@Override
	public void actionPerformed(ActionEvent event) {
		System.out.print("Find... ");
		// display a find dialog box
		String findString = JOptionPane.showInputDialog("Find what..."); 		
		System.out.println(findString);
	}
}
