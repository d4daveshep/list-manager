package daveshep.gtd.util;

import java.io.File;
import java.io.FileNotFoundException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import daveshep.gtd.ListManager;
import daveshep.gtd.domain.ListItem;
import daveshep.gtd.domain.Project;
import daveshep.gtd.domain.ProjectStatus;
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
				item.normalize();

				String itemId="0";
				String itemContext="";
				String itemTitle="";
				String itemStatus="";
				String itemFolder="";
				String itemParentId="0";
				String itemCompletedDate="";
				String itemDueDate="";
				String itemGoal;
				String itemTags="";
				String itemRepeat;
				String itemStar="";
				String itemNotes="";
				
				NodeList itemNodes = item.getChildNodes();
				Node itemProperty;
				for (int j=0;j<itemNodes.getLength();j++) {
					itemProperty = itemNodes.item(j);

					String itemPropertyName = itemProperty.getNodeName();
					String itemPropertyValue = itemProperty.getTextContent();

					// ignore #text properties
					if ("#text".equals(itemPropertyName)) {
						continue;
					}
					
//					System.out.println("item " + i + " property " + j + ": " + itemPropertyName + " with value '" + itemPropertyValue + "'");

					// process the tags we're interested in
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
						throw new Exception("item " + i + ": unknown tag " + j + ": " + itemPropertyName + " with value '" + itemPropertyValue + "'");
					}
				}
				
				// OK so now we have all the tag info, lets create our list item
				ListItem listItem;
//				System.out.println(i + " " + itemStatus);
				if (itemStatus.equals("Reference")) {
					listItem = this.manager.createRefItem();
				}
				
				else if (itemContext.equals("Projects")) {
					listItem = this.manager.createProject();
					if (itemStatus.equals("Active")) {
						((Project)listItem).setStatus(ProjectStatus.ACTIVE);
					}
					else if (itemStatus.equals("Delegated")) {
						((Project)listItem).setStatus(ProjectStatus.DELEGATED);
					}
					else if (itemStatus.equals("Someday")) {
						((Project)listItem).setStatus(ProjectStatus.SOMEDAY);
					}
					else if (itemStatus.equals("Waiting") || itemStatus.equals("Hold")) {
						((Project)listItem).setStatus(ProjectStatus.DEPENDS_ON);
					}
					if(!itemCompletedDate.equals("0000-00-00")) {
						((Project)listItem).setDone(true);
					}
					if (!itemDueDate.equals("0000-00-00")) {
						// TODO parse and set date, check if this affects status 
					}
				}
				
				else {
					listItem = this.manager.createTask();
					((Task)listItem).setContext(itemContext);
//					((Task)listItem).setStatus(status)
					if (itemStatus.equals("Next Action")) {
						((Task)listItem).setStatus(TaskStatus.NEXT_ACTION);
					}
					else if (itemStatus.equals("Delegated") || itemStatus.equals("Waiting")) {
						((Task)listItem).setStatus(TaskStatus.WAITING_FOR);
					}
					else if (itemStatus.equals("Hold")) {
						((Task)listItem).setStatus(TaskStatus.DEPENDS_ON);
					}
					else if (itemStatus.equals("Postponed")) {
						((Task)listItem).setStatus(TaskStatus.CALENDAR);
					}
					if (!itemDueDate.equals("0000-00-00")) {
						// TODO parse and set date, check if this affects status 
					}
					if(!itemCompletedDate.equals("0000-00-00")) {
						((Task)listItem).setDone(true);
					}

				}
				
				// and now populate the list item properties
				listItem.setId(Long.valueOf(itemId));
				listItem.setDescription(itemTitle);
				listItem.setFolder(itemFolder);
				listItem.setNotes(itemNotes);
				listItem.setStarflag(itemStar.equals("1"));
				listItem.setTagsString(itemTags);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		
	}
	
}
