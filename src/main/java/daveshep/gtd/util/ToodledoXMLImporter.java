package daveshep.gtd.util;

import java.io.File;
import java.io.FileNotFoundException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import daveshep.gtd.ListManager;
import daveshep.gtd.domain.ListItem;
import daveshep.gtd.domain.Task;
import daveshep.gtd.domain.TaskStatus;

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
			System.out.println(items.getLength() + " items");
			
			Node item;
			for (int i=0;i<items.getLength();i++) {
				item = items.item(i);

				String itemId = "0";
				String itemContext = "";
				String itemTitle;
				String itemStatus = "";
				String itemFolder;
				String itemParentId;
				String itemCompletedDate;
				String itemDueDate;
				String itemGoal;
				String itemTags;
				String itemRepeat;
				String itemStar;
				String itemNotes;
				
				NodeList itemNodes = item.getChildNodes();
				Node itemProperty;
				for (int j=0;j<itemNodes.getLength();j++) {
					itemProperty = itemNodes.item(j);
					
					if (itemProperty.getNodeType()!=Node.ELEMENT_NODE) {
						continue;
					}
					
					String itemPropertyName = itemProperty.getNodeName();
					String itemPropertyValue = itemProperty.getNodeValue();

					
					if ("id".equals(itemPropertyName)) {
						itemId = itemPropertyValue;
					}

					else if ("context".equals(itemPropertyName)) {
						itemContext = itemPropertyValue;
					}
					
					else if ("title".equals(itemPropertyName)) {
						itemTitle = itemPropertyValue;
					}
					
					else if ("folder".equals(itemPropertyName)) {
						itemFolder = itemPropertyValue;
					}
					
					else if ("status".equals(itemPropertyName)) {
						itemStatus = itemPropertyValue;
					}					

					else if ("parent".equals(itemPropertyName)) {
						itemParentId = itemPropertyValue;
					}
					
					else if ("completed".equals(itemPropertyName)) {
						itemCompletedDate = itemPropertyValue;
					}
					
					else if ("duedate".equals(itemPropertyName)) {
						itemDueDate = itemPropertyValue;
					}

					else if ("goal".equals(itemPropertyName)) {
						itemGoal = itemPropertyValue;
					}

					else if ("tag".equals(itemPropertyName)) {
						itemTags = itemPropertyValue;
					}

					else if ("repeat".equals(itemPropertyName)) {
						itemRepeat = itemPropertyValue;
					}

					else if ("star".equals(itemPropertyName)) {
						itemStar = itemPropertyValue;
					}
				
					else if ("note".equals(itemPropertyName)) {
						itemNotes = itemPropertyValue;
					}
				
					// the remaining tags are ignored
					else if ("startdate".equals(itemPropertyName)) {
						continue;
					}
					
					else if ("starttime".equals(itemPropertyName)) {
						continue;
					}
					
					else if ("duedatemodifier".equals(itemPropertyName)) {
						continue;
					}
					
					else if ("duetime".equals(itemPropertyName)) {
						continue;
					}
					
					else if ("priority".equals(itemPropertyName)) {
						continue;
					}
					
					else if ("length".equals(itemPropertyName)) {
						continue;
					}
					
					else if ("timer".equals(itemPropertyName)) {
						continue;
					}
					
					else {
						// unknown tag
						throw new Exception("item " + i + ": unknown tag " + j + ": " + itemPropertyName);
					}
				}
				
				// OK so now we have all the tag info, lets create our list item
				ListItem listItem;
				System.out.println(i + " " + itemStatus);
				if (itemStatus.equals("Reference")) {
					listItem = this.manager.createRefItem();
				}
				
				else if (itemContext.equals("Projects")) {
					listItem = this.manager.createProject();
				}
				
				else {
					listItem = this.manager.createTask();
				}
				
				// and now populate the list item properties
				listItem.setId(Long.valueOf(itemId));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		
	}
	
}
