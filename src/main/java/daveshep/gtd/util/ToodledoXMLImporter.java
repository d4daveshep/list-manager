package daveshep.gtd.util;

import java.io.File;
import java.io.FileNotFoundException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import daveshep.gtd.ListManager;

/**
 * @author david
 *
 * This class imports from Toodledo XML into a ListManager
 */
public class ToodledoXMLImporter {

	private ListManager manager;
	private File file;
	
	public ToodledoXMLImporter(ListManager manager, File file) throws FileNotFoundException {
		// verify that file exists
		if( file==null) {
			throw new IllegalArgumentException("File is null");
		}
		if( !file.exists() ) {
			throw new FileNotFoundException("File does not exist");
		}
		if(manager==null) {
			throw new IllegalArgumentException("List manager is null");
		}
		
		this.manager = manager;
		this.file = file;
		
	}
	
	public void doImport() {
		
		manager.removeAll();
		try {
			DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			
			Document document = documentBuilder.parse(file);
			
			NodeList items = document.getElementsByTagName("item");
			Node item;
			for (int i=0;i<items.getLength();i++) {
				item = items.item(i);
				
				NodeList itemNodes = item.getChildNodes();
				
				
			}
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		
	}
	
}
