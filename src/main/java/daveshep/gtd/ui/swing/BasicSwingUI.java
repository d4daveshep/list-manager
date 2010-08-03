package daveshep.gtd.ui.swing;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import daveshep.gtd.ListManager;
import daveshep.gtd.domain.InMemoryListManager;
import daveshep.gtd.util.ToodledoXMLImporter;



public class BasicSwingUI extends JFrame {

	private JList itemList;

	
	private ListManager manager = InMemoryListManager.getInstance();

	public BasicSwingUI() {
		super("GTD List Manager - Basic Swing UI");
		setSize(800, 600);

		// create the main list panel
		itemList = new JList(new String[] {"item 1","item 2","item 3"});

		// add the list to the frame
		JScrollPane ps = new JScrollPane();
		ps.getViewport().add(itemList);
		getContentPane().add(ps, BorderLayout.CENTER);
		
		// create the command panel
		CommandPanel commandPanel = new CommandPanel();
		
		loadTestData();		
		
		getContentPane().add(commandPanel, BorderLayout.PAGE_END);

		pack();
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

